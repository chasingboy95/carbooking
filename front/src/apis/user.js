// 导入axios实例
import httpRequest from '@/request/index'
import {
	getLoginToken
} from '@/utils/utils'

// 登录
export function doLogin(param) {
	return httpRequest({
		url: `/login`,
		method: 'post',
		data: param,
	})
}

// 获取用户信息
export function getUserInfo(id, token) {
	return httpRequest({
		url: `/user/${id}`,
		method: 'get',
		headers: {
			token: getLoginToken()
		}
	})
}
