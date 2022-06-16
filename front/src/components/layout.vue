<template>
    <div class="layput-main">
        <nav-header />
        <div class="main-content">
            <div class="slider">
                <slider />
            </div>
            <div class="container">
                <router-view />
            </div>
        </div>
    </div>
</template>

<script>
import Header from './header.vue'
import Slider from './slider.vue'

export default {
    name: 'LayOut',
    components: {
        'nav-header': Header,
        Slider
    }
}
</script>

<style scoped >
.main-content {
    display: flex;
}
.slider {
    width: 240px;
}
.container {
    flex: 1;
}
</style>
