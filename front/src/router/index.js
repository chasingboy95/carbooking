import Vue from 'vue'
import Router from 'vue-router'
import LayOut from '@/components/layout'
import CarList from '@/components/CarList'
import OrderList from '@/components/OrderList'
import Login from '@/components/Login'

Vue.use(Router)

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
