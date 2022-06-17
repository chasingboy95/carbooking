// 导入axios实例
import httpRequest from '@/request/index'
import {
	getLoginToken
} from '@/utils/utils'

// 获取车辆list
export function getCarList(param) {
	return httpRequest({
		url: '/car/list',
		method: 'get',
		data: param,
		headers: {
			token: getLoginToken()
		}
	})
}

// 获取不可选日期list
export function getBookedDateList(id) {
	return httpRequest({
		url: `/car/${id}/booked/date`,
		method: 'get',
		headers: {
			token: getLoginToken()
		}
	})
}
