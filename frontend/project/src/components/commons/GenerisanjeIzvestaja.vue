<template>
    <div class="centered">
        <h2 class="centered-text">Generisanje izveštaja</h2>
        <vremenskiPeriodUnos></vremenskiPeriodUnos>
        <button>Generiši</button>
    </div>
</template>

<script>
    import VremenskiPeriodUnos from './VremenskiPeriodUnos.vue';

    export default {
        name: 'GenerisanjeIzvestaja',
        components: {
            VremenskiPeriodUnos
        },
        data() {
            return {
                
            }
        }
    }
</script>

<style scoped>
    h2 {
        margin: 50px 0 70px 0;
    }
    button {
        margin: 10px 30px;
        font-size: 18px;
    }
    .centered-text {
        text-align: center;
    }
    .centered {
        margin: auto;
        width: 60%;
    }
</style>