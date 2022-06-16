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
