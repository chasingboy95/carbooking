export const getLoginToken = () => {
    return sessionStorage.getItem('token') || ''
}

export const getDateString = (date) => {
    var year = date.getFullYear() // get year
    var month = date.getMonth() + 1 // get month
    var day = date.getDate() // get day
    if (month < 10) {
        month = '0' + month
    }
    if (day < 10) {
        day = '0' + day
    }
    var dateString = year + '-' + month + '-' + day
    return dateString
}
