<template>
    <div id="app">
        <!-- <div v-if="isLogin">
            <div id="header">Welcome: {{ name }}</div>
            <Button type="primary" @click="logout()">logout</Button>
        </div> -->
        <router-view />
    </div>
</template>

<script>
export default {
    name: 'App',
    data() {
        return {
            name: '',
            isLogin: true
        }
    },
    mounted: function () {
        if (sessionStorage.getItem('userInfo')) {
            console.log(sessionStorage.getItem('userInfo'))
            const userInfo = JSON.parse(sessionStorage.getItem('userInfo') || '')
            this.name = userInfo.name
        } else {
            this.isLogin = false
        }
    },
    methods: {
        logout() {
            sessionStorage.removeItem('userInfo')
            sessionStorage.removeItem('token')
            this.$router.go(0)
        }
    }
}
</script>
