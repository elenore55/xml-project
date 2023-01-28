<template>
    <div>
        <div class="flex-container">
            <h2 class="item">Podnosilac prijave</h2>
            <select v-model="tipPodnosioca">
                <option value="1">Fizičko lice</option>
                <option value="2">Pravno lice</option>
                <option value="3">Punomoćnik</option>
            </select>
        </div>
        

        <h3>Osnovni podaci</h3>
        <div v-if="tipPodnosioca != 2">
            <LicniPodaci :drzavljanstvoPotrebno="tipPodnosioca == 1" @updateLicniPodaci="updateLicniPodaci($event)" ref="licniPodaci"></LicniPodaci>
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
            <AdresaUnos ref="adresa" @updateAdresa="updateAdresa($event)"></AdresaUnos>
        </div>

        <div>
            <h3>Kontakt</h3>
            <div class="flex-container">
                <div id="phone" class="flex-container-v item">
                    <label for="phone-input" class="item">Telefon</label>
                    <input id="phone-input" type="text" class="item" v-model="telefon" @input="updatePodnosilac"/>
                </div>
                <div id="email" class="flex-container-v item">
                    <label for="email-input">E-mail</label>
                    <input id="email-input" type="text" v-model="email" @input="updatePodnosilac"/>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import AdresaUnos from '@/components/a1/AdresaUnos.vue';
    import LicniPodaci from '@/components/a1/LicniPodaci.vue';

    export default {
        name: 'PodnosilacPrijaveUnos',
        components: {
            AdresaUnos, 
            LicniPodaci
        },
        data() {
            return {
                tipPodnosioca: 1,
                ime: '',
                prezime: '',
                drzavljanstvo: '',
                poslovnoIme: '',
                adresa: {},
                telefon: '',
                email: '',
            }
        },
        methods: {
            updatePodnosilac() {
                this.$emit('updatePodnosilac', {
                    ime: this.ime,
                    prezime: this.prezime,
                    poslovnoIme: this.poslovnoIme,
                    drzavljanstvo: this.drzavljanstvo,
                    adresa: this.adresa,
                    telefon: this.telefon,
                    email: this.email,
                    tip: this.tipPodnosioca 
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
            clear() {
                if (this.tipPodnosioca != 2) this.$refs.licniPodaci.clear();
                this.$refs.adresa.clear();
                this.poslovnoIme = '';
                this.ime = '';
                this.prezime = '';
                this.drzavljanstvo = '';
                this.email = '';
                this.telefon = '';
                this.tipPodnosioca = 1;
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