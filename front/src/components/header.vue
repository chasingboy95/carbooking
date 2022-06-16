<template>
  <div v-if="isLogin"
       class="user-info">
    Welcome <Dropdown trigger="click"
              style="margin-left: 20px"
              @on-click="clickDropDown">
      <a href="javascript:void(0)">
        {{name}}
        <Icon type="arrow-down-b"></Icon>
      </a>
      <Dropdown-menu slot="list">
        <Dropdown-item name="logout">logout</Dropdown-item>
      </Dropdown-menu>
    </Dropdown>
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      name: '',
      isLogin: true
    }
  },
  mounted: function () {
    if (sessionStorage.getItem('userInfo')) {
      const userInfo = JSON.parse(sessionStorage.getItem('userInfo') || '')
      this.name = userInfo.name
    } else {
      this.isLogin = false
    }
  },
  methods: {
    clickDropDown (param) {
      console.log(param)
      switch (param) {
        case 'logout':
          this.logout()
          break
      }
    },
    logout () {
      sessionStorage.removeItem('userInfo')
      sessionStorage.removeItem('token')
      this.$router.push('/login')
    }
  }
}
</script>
<style scoped >
.user-info {
  display: flex;
  height: 10vh;
  margin-right: 3vw;
  margin-top: 2vh;
  justify-content: flex-end;
}
</style>
