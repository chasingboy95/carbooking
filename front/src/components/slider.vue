<template>
    <div>
        <Menu :theme="theme3" active-name="/orders" @on-select="changeRouter">
            <MenuGroup title="Menu">
                <MenuItem name="/orders">
                    <Icon type="md-document" />
                    booking order
                </MenuItem>
                <MenuItem name="/cars">
                    <Icon type="md-chatbubbles" />
                    cars
                </MenuItem>
            </MenuGroup>
        </Menu>
    </div>
</template>
<script>
export default {
    data() {
        return {
            theme3: 'light'
        }
    },
    methods: {
        changeRouter(route) {
            this.$router.push(route)
        }
    }
}
</script>
