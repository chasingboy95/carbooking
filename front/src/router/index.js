import Vue from 'vue'
import Router from 'vue-router'
import LayOut from '@/components/layout'
import CarList from '@/components/carList'
import OrderList from '@/components/orderList'
import Login from '@/components/login'
import AddOrder from '@/components/addOrder'

Vue.use(Router)
// 获取原型对象上的push函数
const originalPush = Router.prototype.push
// 修改原型对象中的push方法
Router.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}
export default new Router({
  routes: [
    {
      path: '/',
      name: 'LayOut',
      component: LayOut,
      children: [
        {
          path: '/cars',
          name: 'CarList',
          component: CarList
        },
        {
          path: '/orders',
          name: 'OrderList',
          component: OrderList
        },
        {
          path: '/car/booking',
          name: 'AddOrder',
          component: AddOrder
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
