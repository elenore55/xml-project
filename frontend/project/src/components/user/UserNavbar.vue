<template>
    <div>
        <GradjaninNav v-if="getRole() == 'GRADJANIN'"></GradjaninNav>
        <SluzbenikNav v-else></SluzbenikNav>
    </div>
</template>

<script>
    import SluzbenikNav from './SluzbenikNav.vue';
    import GradjaninNav from './GradjaninNav.vue';

    export default {
        name: 'UserNavbar',
        mounted() {

        },
        methods: {
            getRole() {
                return localStorage.getItem('role');
            }
        },
        components: {
            SluzbenikNav,
            GradjaninNav
        }
    }
</script>