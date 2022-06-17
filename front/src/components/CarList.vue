<template>
    <div class="car-list">
        <Table :columns="tableHeader" :data="tableData">
            <template slot-scope="{ row, index }" slot="status">
                {{ statusMap[row.status || 0] }}
            </template>
            <template slot-scope="{ row, index }" slot="action">
                <!-- <Button size="small">Default</Button> -->
                <Button
                    type="primary"
                    size="small"
                    @click="booking(row)"
                    :disabled="row.status > 0"
                    >Booking</Button
                >
            </template>
        </Table>
    </div>
</template>

// <script type="text/javascript" src="iview.min.js"></script>
<script>
import { getCarList } from '@/apis/car.js'

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
                    title: 'car name',
                    key: 'name'
                },
                {
                    title: 'plate number',
                    key: 'plateNumber'
                },
                {
                    title: 'status',
                    key: 'status',
                    slot: 'status'
                },
                {
                    title: 'operation',
                    key: 'action',
                    align: 'center',
                    slot: 'action'
                }
            ],
            tableData: [],
            statusMap: {
                0: 'available',
                1: 'renting',
                2: 'in maintenance'
            }
        }
    },
    methods: {
        booking(item) {
            const { id } = item
            this.$router.push({
                path: '/car/booking',
                query: {
                    carId: id
                }
            })
        }
    },
    mounted() {
        getCarList().then(
            (res) => {
                if (res.code === 200) {
                    this.tableData = res.data.list
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
.car-list {
    widows: 95%;
    padding-right: 3vw;
}
</style>
