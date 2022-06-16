<template>
  <div class="container">
    <Form ref="bookingOrder"
          :model="bookingOrder"
          :rules="ruleInline"
          inline>
      <div>
        <FormItem label="choose time">
          <Date-picker type="datetimerange"
                       format="yyyy-MM-dd HH:mm"
                       @on-ok="getChoosedTime"
                       placeholder="choose start time and end time"
                       style="width: 300px"></Date-picker>
        </FormItem>
      </div>
      <div class="submit-button">
        <FormItem>
          <Button type="success"
                  @click="submitBooking()">confirm</Button>
        </FormItem>
      </div>
    </Form>
  </div>
</template>

<script type="text/javascript" src="iview.min.js"></script>
<script>
import { doLogin, getUserInfo } from '@/apis/user.js'
export default {
  name: 'AddOrder',
  data () {
    return {
      bookingOrder: {
        carId: '',
        userId: JSON.parse(sessionStorage.getItem('userInfo')).id,
        startTime: '',
        estimatedEndTime: ''
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
    submitBooking () {
    },
    getChoosedTime (param) {
      console.log(param)
    }
  }
}
</script>
<style scoped >
.container {
  display: flex;
  justify-content: center;
  margin-top: 10vh;
}
.login-button {
  display: flex;
  justify-content: center;
}
</style>
