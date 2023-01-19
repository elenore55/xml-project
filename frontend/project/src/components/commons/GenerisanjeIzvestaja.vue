<template>
    <div class="centered">
        <h2 class="centered-text">Generisanje izveštaja</h2>
        <VremenskiPeriodUnos @updatePeriod="updatePeriod($event)"></VremenskiPeriodUnos>
        <button type="button" @click="generate">Generiši</button>
    </div>
</template>

<script>
    import * as js2xml from 'js2xmlparser';
    import CommonsService from '@/services/CommonsService';
    import VremenskiPeriodUnos from './VremenskiPeriodUnos.vue';

    export default {
        name: 'GenerisanjeIzvestaja',
        components: {
            VremenskiPeriodUnos
        },
        data() {
            return {
                period: {}
            }
        },
        methods: {
            updatePeriod(period) {
                this.period = period; 
            },
            generate() {
                if (this.period.start && this.period.end) {
                    const xmlString = js2xml.parse('period', this.period);
                    CommonsService.generateReport(xmlString);
                }
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