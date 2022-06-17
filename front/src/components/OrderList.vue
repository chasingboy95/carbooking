<template>
    <div class="order-list">
        <Table :columns="tableHeader" :data="tableData">
            <template slot-scope="{ row, index }" slot="status">
                {{ statusMap[row.status || 0] }}
            </template>
            <template slot-scope="{ row, index }" slot="actualEndTime">
                {{ row.actualEndTime || '/' }}
            </template>
            <template slot-scope="{ row, index }" slot="action">
                <!-- <Button size="small">Default</Button> -->
                <Button
                    type="error"
                    size="small"
                    @click="finish(row.id)"
                    :disabled="row.status !== 1"
                    >Finish</Button
                >
            </template>
        </Table>
    </div>
</template>

// <script type="text/javascript" src="iview.min.js"></script>
<script>
import { getOrderList, finishOrder, getOrderBill } from '@/apis/order.js'

export default {
    name: 'CarList',
    data() {
        return {
            showModal: false,
            bill: '',
            tableHeader: [
                {
                    title: 'ID',
                    key: 'id'
                },
                {
                    title: 'car brand',
                    key: 'brandName'
                },
                {
                    title: 'car name',
                    key: 'carName'
                },
                {
                    title: 'plate number',
                    key: 'plateNumber'
                },
                {
                    title: 'start',
                    key: 'startTime'
                },
                {
                    title: 'estimated end',
                    key: 'estimatedEndTime'
                },
                {
                    title: 'actual end',
                    key: 'actualEndTime',
                    slot: 'actualEndTime'
                },
                {
                    title: 'status',
                    key: 'status',
                    slot: 'status'
                },
                {
                    title: 'operation',
                    key: 'action',
                    slot: 'action'
                }
            ],
            tableData: [],
            statusMap: {
                1: 'active',
                2: 'finished'
            }
        }
    },
    mounted() {
        const userInfo = sessionStorage.getItem('userInfo')
        if (!userInfo) {
            this.$router.push('/login')
        } else {
            let param = {
                userId: JSON.parse(userInfo).id || ''
            }
            getOrderList(param).then(
                (res) => {
                    if (res.code === 200) {
                        this.tableData = res.data
                    } else {
                    }
                },
                (err) => {
                    console.log(err)
                }
            )
        }
    },
    methods: {
        async finish(id) {
            const bill = await getOrderBill(id)
            const { code, message, data } = bill
            if (code === 200) {
                this.bill = data
                this.$Modal.confirm({
                    title: 'Pay For This Booking Order',
                    content: '<p>you should pay: ' + this.bill + '</p>',
                    loading: true,
                    onOk: () => {
                        this.$Message.loading('check payment results...', 0)
                        setTimeout(() => {
                            this.$Modal.remove()
                            this.confirmFinish(id)
                        }, 2000)
                    }
                })
            } else {
                this.$Message.error(message)
            }
        },
        confirmFinish(id) {
            finishOrder(id).then(
                (res) => {
                    if (res.code === 200) {
                        this.$router.go(0)
                    } else {
                        this.$Message.error(res.message)
                    }
                },
                (err) => {
                    console.log(err)
                }
            )
        }
    }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.order-list {
    widows: 95%;
    padding-right: 3vw;
}
</style>
