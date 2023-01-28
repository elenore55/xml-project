<template>
    <div>
        <div class="flex-container">
            <h2 class="item">Podnosilac prijave</h2>
            <select v-model="tipPodnosioca">
                <option value="1">Fizičko lice</option>
                <option value="2">Pravno lice</option>
            </select>
        </div>

        <input type="checkbox" v-model="pronalazac" name="pronalazac" @input="checkPronalazac($event)"/>
        <label>Podnosilac prijave je i pronalazač</label>

        <h3>Osnovni podaci</h3>
        <div v-if="tipPodnosioca == 1">
            <LicniPodaci ref="licniPodaci" drzavljanstvoPotrebno="true" @updateLicniPodaci="updateLicniPodaci($event)"></LicniPodaci>
        </div>
        <div v-else>
            <div class="flex-container">
                <div id="business-name" class="flex-container-v item">
                    <label for="business-name-input" class="item">Poslovno ime</label>
                    <input id="business-name-input" type="text" class="item" v-model="poslovnoIme" @input="updatePodnosilac"/>
                </div>
            </div>
        </div>

        <div>
            <h3>Adresa</h3>
            <AdresaUnos drzavaPotrebna="true" ref="adresaUnos" @updateAdresa="updateAdresa($event)"></AdresaUnos>
        </div>

        <div>
            <KontaktPodaci faksPotreban="true" ref="kontaktPodaci" @updateKontaktPodaci="updateKontaktPodaci($event)"></KontaktPodaci>
        </div>
    </div>
</template>

<script>
    import LicniPodaci from './LicniPodaci.vue';
    import AdresaUnos from './AdresaUnos.vue';
    import KontaktPodaci from './KontaktPodaci.vue';

    export default {
        name: 'PodnosilacPrijave',
        components: {
            LicniPodaci,
            AdresaUnos,
            KontaktPodaci
        },
        data() {
            return {
                tipPodnosioca: 1,
                ime: '',
                prezime: '',
                poslovnoIme: '',
                adresa: {},
                telefon: '',
                email: '',
                faks: '',
                pronalazac: false,
                drzavljanstvo: ''
            }
        },
        methods: {
            updatePodnosilac() {
                this.poslovnoIme = this.poslovnoIme.toUpperCase();
                this.$emit('updatePodnosilac', {
                    ime: this.ime,
                    prezime: this.prezime,
                    poslovnoIme: this.poslovnoIme,
                    adresa: this.adresa,
                    telefon: this.telefon,
                    email: this.email,
                    faks: this.faks,
                    fizickoLice: this.tipPodnosioca == 1,
                    pronalazac: this.pronalazac,
                    drzavljanstvo: this.drzavljanstvo
                });
            },
            updateLicniPodaci(podaci) {
                this.ime = podaci.ime;
                this.prezime = podaci.prezime;
                this.drzavljanstvo = podaci.drzavljanstvo;
                this.updatePodnosilac();
            },
            updateAdresa(adresa) {
                this.adresa = adresa;
                this.updatePodnosilac();
            },
            updateKontaktPodaci(podaci) {
                this.email = podaci.email;
                this.telefon = podaci.telefon;
                this.faks = podaci.faks;
                this.updatePodnosilac();
            },
            clear() {
                this.poslovnoIme = '';
                this.ime = '';
                this.prezime = '';
                if (this.tipPodnosioca === 1) this.$refs.licniPodaci.clear();
                this.$refs.adresaUnos.clear();
                this.$refs.kontaktPodaci.clear();
                this.tipPodnosioca = 1;
                this.updatePodnosilac();
            },
            checkPronalazac(event) {
                this.pronalazac = event.target.checked;
                this.updatePodnosilac();
            }
        }
    }
</script>

<style scoped>
    #business-name-input {
        width: 65%;
    }
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