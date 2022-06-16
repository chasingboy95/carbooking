// 导入axios实例
import httpRequest from '@/request/index'
import {
	getLoginToken
} from '@/utils/utils'

// 获取用户信息
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
