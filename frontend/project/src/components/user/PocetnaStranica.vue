<template>
    <div>
        <UserNavbar></UserNavbar>
        <XonomyTest></XonomyTest>
    </div>
</template>

<script>
    import UserNavbar from './UserNavbar.vue';
    import XonomyTest from '@/components/p1/XonomyTest.vue';

    export default {
        name: 'PocetnaStranica',
        components: {
            UserNavbar,
            XonomyTest
        }
    }
</script>