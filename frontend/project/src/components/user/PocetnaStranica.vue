<template>
    <div style="text-align: center">
        <UserNavbar></UserNavbar>
        <img src="zavod.png" alt="Zavod za intelektualnu svojinu" width="350px" style="margin-top: 70px"/>
    </div>
</template>

<script>
    import UserNavbar from './UserNavbar.vue';

    export default {
        name: 'PocetnaStranica',
        components: {
            UserNavbar
        }
    }
</script>