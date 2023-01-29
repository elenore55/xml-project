<template>
    <div>
        <div class="flex-container">    
            <h2 class="item">Punomoćnik</h2>
            <select v-model="tipPunomocnika">
                <option value="1">PUNOMOĆNIK ZA ZASTUPANJE</option>
                <option value="2">PUNOMOĆNIK ZA PRIJEM PISMENA</option>
                <option value="3">ZAJEDNIČKI PREDSTAVNIK</option>
            </select>
            <select v-model="tipPodnosioca">
                <option value="1">Fizičko lice</option>
                <option value="2">Pravno lice</option>
            </select>
        </div>

        <h3>Osnovni podaci</h3>
        <div v-if="tipPodnosioca == 1">
            <LicniPodaci ref="licniPodaci" @updateLicniPodaci="updateLicniPodaci($event)"></LicniPodaci>
        </div>
        <div v-else>
            <div class="flex-container">
                <div id="business-name" class="flex-container-v item">
                    <label for="business-name-input" class="item">Poslovno ime</label>
                    <input id="business-name-input" type="text" :class="isValidPoslovnoIme?'item':'item red-border'" v-model="poslovnoIme" @input="validatePoslovnoIme"/>
                </div>
            </div>
        </div>

        <div>
            <h3>Adresa</h3>
            <AdresaUnos ref="adresaUnos" @updateAdresa="updateAdresa($event)"></AdresaUnos>
        </div>

        <div>
            <KontaktPodaci ref="kontaktPodaci" @updateKontaktPodaci="updateKontaktPodaci($event)"></KontaktPodaci>
        </div>
    </div>
</template>

<script>
    import LicniPodaci from './LicniPodaci.vue';
    import AdresaUnos from './AdresaUnos.vue';
    import KontaktPodaci from './KontaktPodaci.vue';

    export default {
        name: 'PunomocnikUnos',
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
                tipPunomocnika: 1,
                isValidPoslovnoIme: true
            }
        },
        methods: {
            updatePunomocnik() {
                this.poslovnoIme = this.poslovnoIme.toUpperCase();
                this.$emit('updatePunomocnik', {
                    ime: this.ime,
                    prezime: this.prezime,
                    poslovnoIme: this.poslovnoIme,
                    adresa: this.adresa,
                    telefon: this.telefon,
                    email: this.email,
                    fizickoLice: this.tipPodnosioca == 1,
                    tipPunomocnika: this.tipPunomocnika
                });
            },
            updateLicniPodaci(podaci) {
                this.ime = podaci.ime;
                this.prezime = podaci.prezime;
                this.updatePunomocnik();
            },
            updateAdresa(adresa) {
                this.adresa = adresa;
                this.updatePunomocnik();
            },
            updateKontaktPodaci(podaci) {
                this.email = podaci.email;
                this.telefon = podaci.telefon;
                this.faks = podaci.faks;
                this.updatePunomocnik();
            },
            clear() {
                this.poslovnoIme = '';
                this.ime = '';
                this.prezime = '';
                if (this.tipPodnosioca == 1) this.$refs.licniPodaci.clear();
                this.$refs.adresaUnos.clear();
                this.$refs.kontaktPodaci.clear();
                this.tipPodnosioca = 1;
                this.tipPunomocnika = 1;
                this.updatePunomocnik();
            },
            validatePoslovnoIme() {
                this.isValidPoslovnoIme = (this.poslovnoIme != '');
                this.updatePunomocnik();
            },
            isValidInput() {
                let licniPodaci;
                if (this.tipPodnosioca == 1) licniPodaci = this.$refs.licniPodaci.isValidInput();
                else {
                    this.validatePoslovnoIme();
                    licniPodaci = this.isValidPoslovnoIme;
                }
                let adresa = this.$refs.adresaUnos.isValidInput();
                let kontakt = this.$refs.kontaktPodaci.isValidInput();
                return licniPodaci && adresa && kontakt;
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