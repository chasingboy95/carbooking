<template>
    <div>
        <template>
            <div>
                <Form ref="user" :model="user" :rules="ruleInline" inline>
                    <div>
                        <FormItem prop="id">
                            <Input
                                type="text"
                                v-model="user.id"
                                placeholder="user id"
                            >
                                <Icon
                                    type="ios-person-outline"
                                    slot="prepend"
                                ></Icon>
                            </Input>
                        </FormItem>
                    </div>
                    <div>
                        <FormItem prop="password">
                            <Input
                                type="password"
                                v-model="user.password"
                                placeholder="password"
                            >
                                <Icon type="ios-lock-outline" slot="prepend" />
                            </Input>
                        </FormItem>
                    </div>
                    <div>
                        <FormItem>
                            <Button type="primary" @click="login()"
                                >登录</Button
                            >
                        </FormItem>
                    </div>
                </Form>
            </div>
        </template>
    </div>
</template>

<script type="text/javascript" src="iview.min.js"></script>
<script>
import { doLogin, getUserInfo } from '@/apis/user.js'
export default {
    name: 'Login',
    data() {
        return {
            user: {
                id: '',
                password: ''
            },
            ruleInline: {
                id: [
                    {
                        required: true,
                        message: "id can't be empty",
                        trigger: 'blur'
                    }
                ],
                passWd: [
                    {
                        required: true,
                        message: "password can' be empty",
                        trigger: 'blur'
                    }
                ]
            }
        }
    },
    methods: {
        async login() {
            const { user } = this
            if (user.id === '' || user.password === '') {
                return false
            }
            const loginRes = await doLogin(user)
            const { code, data } = loginRes
            if (code === 200) {
                window.sessionStorage.setItem('token', data)
            }
            const userRes = await getUserInfo(user.id, data)
            if (userRes.code === 200) {
                window.sessionStorage.setItem('userInfo', JSON.stringify(userRes.data))
                this.$router.push('/orders')
            }
        }
    }
}
</script>
