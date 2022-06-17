// 导入axios实例
import httpRequest from '@/request/index'
import {
	getLoginToken
} from '@/utils/utils'

// 获取订单
export function getOrderList(param) {
	return httpRequest({
		url: '/booking/list',
		method: 'get',
		params: param,
		headers: {
			token: getLoginToken()
		}
	})
}

// 新增订单
export function addOrder(param) {
	return httpRequest({
		url: `/booking`,
		method: 'post',
		data: param,
		headers: {
			token: getLoginToken()
		}
	})
}

// 结束订单
export function finishOrder(id) {
	return httpRequest({
		url: `/booking/${id}/finish`,
		method: 'put',
		headers: {
			token: getLoginToken()
		}
	})
}

// 获取订单账单
export function getOrderBill(id) {
	return httpRequest({
		url: `/booking/${id}/bill`,
		method: 'get',
		headers: {
			token: getLoginToken()
		}
	})
}
