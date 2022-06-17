<template>
    <div class="container">
        <Form ref="bookingOrder" :model="bookingOrder" inline>
            <div>
                <FormItem label="choose time">
                    <Date-picker
                        type="daterange"
                        :options="disableOption"
                        format="yyyy-MM-dd"
                        @on-change="getChoosedTime"
                        placeholder="choose start time and end time"
                        style="width: 300px"
                    ></Date-picker>
                </FormItem>
            </div>
            <div class="submit-button">
                <FormItem>
                    <Button
                        type="success"
                        @click="submitBooking()"
                        :disabled="!bookingOrder.startTime"
                        >confirm</Button
                    >
                </FormItem>
            </div>
        </Form>
    </div>
</template>

<script type="text/javascript" src="iview.min.js"></script>
<script>
import { addOrder } from '@/apis/order.js'
import { getBookedDateList } from '@/apis/car.js'
import { getDateString } from '@/utils/utils'
export default {
    name: 'AddOrder',
    data() {
        return {
            bookedDates: [],
            bookingOrder: {
                carId: '',
                userId: JSON.parse(sessionStorage.getItem('userInfo')).id,
                startTime: '',
                estimatedEndTime: ''
            }
        }
    },
    computed: {
        disableOption() {
            const { bookedDates } = this
            return {
                disabledDate(date) {
                    const _date = getDateString(date)
                    return (
                        bookedDates.indexOf(_date) !== -1 ||
                        date.valueOf() < Date.now() - 86400000
                    )
                }
            }
        }
    },
    created() {
        let carId = this.$route.query.carId
        this.bookingOrder.carId = carId
        getBookedDateList(carId).then(
            (res) => {
                if (res.code === 200) {
                    this.bookedDates = res.data
                } else {
                    this.$Message.error(res.message)
                }
            },
            (err) => {
                console.log(err)
            }
        )
    },
    mounted() {},
    methods: {
        submitBooking() {
            if (
                !this.bookingOrder.startTime ||
                !this.bookingOrder.estimatedEndTime
            ) {
                this.$Message.error('please choose time')
                return
            }
            addOrder(this.bookingOrder).then(
                (res) => {
                    if (res.code === 200) {
                        this.$router.push('/orders')
                        this.$Message.success('booking success')
                    } else {
                        this.$Message.error('booking failed, ' + res.message)
                    }
                },
                (err) => {
                    console.log(err)
                }
            )
        },
        getChoosedTime(param) {
            let date0 = new Date(param[0])
            let date1 = new Date(param[1])
            let dateRangeLegal = true
            for (let bookedDateString of this.bookedDates) {
                let bookedDate = new Date(bookedDateString)
                if (bookedDate > date0 && bookedDate < date1) {
                    this.$Message.error("date range can't contains booked date")
                    this.bookingOrder.startTime = ''
                    this.bookingOrder.estimatedEndTime = ''
                    dateRangeLegal = false
                    break
                }
            }
            if (dateRangeLegal) {
                this.bookingOrder.startTime = param[0]
                this.bookingOrder.estimatedEndTime = param[1]
            }
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
