<template>
    <div>
        <h2 class="item">Pronalazač</h2>

        <input type="checkbox" v-model="neZeliBitiNaveden" @input="updatePronalazac"/>
        <label>Pronalazač ne želi da bude naveden u prijavi</label>

        <div v-if="!neZeliBitiNaveden">
            <h3>Osnovni podaci</h3>
            <LicniPodaci ref="licniPodaci" @updateLicniPodaci="updateLicniPodaci($event)"></LicniPodaci>

            <div>
                <h3>Adresa</h3>
                <AdresaUnos drzavaPotrebna="true" ref="adresaUnos" @updateAdresa="updateAdresa($event)"></AdresaUnos>
            </div>

            <div>
                <KontaktPodaci faksPotreban="true" ref="kontaktPodaci" @updateKontaktPodaci="updateKontaktPodaci($event)"></KontaktPodaci>
            </div>
        </div>
    </div>
</template>

<script>
    import LicniPodaci from './LicniPodaci.vue';
    import AdresaUnos from './AdresaUnos.vue';
    import KontaktPodaci from './KontaktPodaci.vue';

    export default {
        name: 'PronalazacUnos',
        components: {
            LicniPodaci,
            AdresaUnos,
            KontaktPodaci
        },
        data() {
            return {
                ime: '',
                prezime: '',
                adresa: {},
                telefon: '',
                email: '',
                faks: '',
                neZeliBitiNaveden: false
            }
        },
        methods: {
            updatePronalazac() {
                this.$emit('updatePronalazac', {
                    ime: this.ime,
                    prezime: this.prezime,
                    adresa: this.adresa,
                    telefon: this.telefon,
                    email: this.email,
                    faks: this.faks,
                    neZeliBitiNaveden: this.neZeliBitiNaveden
                });
            },
            updateLicniPodaci(podaci) {
                this.ime = podaci.ime;
                this.prezime = podaci.prezime;
                this.updatePronalazac();
            },
            updateAdresa(adresa) {
                this.adresa = adresa;
                this.updatePronalazac();
            },
            updateKontaktPodaci(podaci) {
                this.email = podaci.email;
                this.telefon = podaci.telefon;
                this.faks = podaci.faks;
                this.updatePronalazac();
            },
            clear() {
                this.poslovnoIme = '';
                this.$refs.licniPodaci.clear();
                this.$refs.adresaUnos.clear();
                this.$refs.kontaktPodaci.clear();
                this.updatePronalazac();
            }
        }
    }
</script>

<style scoped>
    h2 {
        margin-top: 50px;
    }
    h3 {
        margin-top: 30px;
    }
    .flex-container {
        display: flex;
        gap: 0 20px;
        margin-bottom: 10px;
        align-items: flex-end;
    }
    .flex-container-v {
        display: flex;
        flex-direction: column;
        gap: 10px;
    }
    .item {
        flex: 1
    }
</style>