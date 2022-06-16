<template>
    <div class="order-list">
        <Table :columns="tableHeader" :data="tableData">
            <template slot-scope="{ row, index }" slot="status">
                {{ statusMap[row.status || 0] }}
            </template>
        </Table>
    </div>
</template>

// <script type="text/javascript" src="iview.min.js"></script>
<script>
import { getOrderList } from '@/apis/order.js'

export default {
    name: 'CarList',
    data() {
        return {
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
                    title: 'start time',
                    key: 'startTime'
                },
                {
                    title: 'estimated end time',
                    key: 'estimatedEndTime'
                },
                {
                    title: 'actual end time',
                    key: 'actualEndTime'
                },
                {
                    title: 'status',
                    key: 'status',
                    slot: 'status'
                }
            ],
            tableData: [],
            statusMap: {
                1: 'active',
                2: 'finished'
            }
        }
    },
    created() {
        let param = {
            userId: JSON.parse(sessionStorage.getItem('userInfo') || '').id
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
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.order-list {
  widows: 95%;
  padding-right: 3vw;
}
</style>
