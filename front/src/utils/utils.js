export const getLoginToken = () => {
    return sessionStorage.getItem('token') || ''
}
