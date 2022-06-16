<template>
    <div class="carList">
        <Table :columns="tableHeader" :data="tableData">
            <template slot-scope="{ row, index }" slot="status">
                {{ statusMap[row.status || 0] }}
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
                    slot: 'status',
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
    created() {
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
<style scoped></style>
