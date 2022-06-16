<template>
  <div class="car-list">
    <Table :columns="tableHeader"
           :data="tableData">
      <template slot-scope="{ row, index }"
                slot="status">
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
  data () {
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
        },
        {
          title: 'operation',
          key: 'action',
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h('Button', {
                props: {
                  type: 'primary',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    this.detail(params.index)
                  }
                }
              }, 'detail'),
              h('Button', {
                props: {
                  type: 'success',
                  size: 'small',
                  disabled: this.tableData[params.index].status !== 0
                },
                on: {
                  click: () => {
                    this.booking(params.index)
                  }
                }
              }, 'booking')
            ])
          }
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
    detail (index) {
      this.$Message.success('detail is coming ~ ')
    },
    booking (index) {
      let carId = this.tableData[index].index
    }
  },
  created () {
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
