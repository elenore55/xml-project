<template>
    <div>
        <div class="flex-container">
            <h3 class="item">{{this.title}}</h3>
            <select v-model="zivChoice">
                <option value="1">Živ autor</option>
                <option value="2">Pokojni autor</option>
            </select>
        </div>
    
        <div class="flex-container">
            <LicniPodaci ref="licniPodaci" :drzavljanstvoPotrebno="zivChoice == 1" :godinaSmrtiPotrebna="zivChoice == 2" 
                @updateLicniPodaci="updateLicniPodaci($event)" class="item"></LicniPodaci>
        </div>
        <div id="pseudonim" class="flex-container-v item">
            <label for="pseudonim-input" class="item">Pseudonim</label>
            <input id="pseudonim-input" type="text" class="item" v-model="pseudonim" @input="updateAutor"/>
        </div>
        <div v-if="zivChoice == 1">
            <h3>Adresa</h3>
            <AdresaUnos ref="adresaUnos" @updateAdresa="updateAdresa($event)"></AdresaUnos>
        </div>
        <button type="button" @click="addAutor">Dodaj autora</button>
    </div>
</template>

<script>
    import LicniPodaci from '@/components/a1/LicniPodaci.vue';
    import AdresaUnos from '@/components/a1/AdresaUnos.vue';

    export default {
        name: 'AutorUnos',
        props: ['title'],
        components: {
            LicniPodaci,
            AdresaUnos
        },
        data() {
            return {
                ime: '',
                prezime: '',
                pseudonim: '',
                adresa: {},
                drzavljanstvo: '',
                godinaSmrti: -1,
                zivChoice: 1,
            }
        },
        methods: {
            updateLicniPodaci(podaci) {
                this.ime = podaci.ime;
                this.prezime = podaci.prezime;
                this.drzavljanstvo = podaci.drzavljanstvo;
                this.godinaSmrti = podaci.godinaSmrti;
                this.updateAutor();
            },
            updateAdresa(adresa) {
                this.adresa = adresa;
                this.updateAutor();
            },
            updateAutor() {
                if (this.zivChoice == 2) {
                    this.$emit('updateAutor', {
                        ime: this.ime,
                        prezime: this.prezime,
                        pseudonim: this.pseudonim,
                        godinaSmrti: this.godinaSmrti,
                        ziv: false
                    });
                }
                else {
                    this.$emit('updateAutor', {
                        ime: this.ime,
                        prezime: this.prezime,
                        pseudonim: this.pseudonim,
                        adresa: this.adresa,
                        drzavljanstvo: this.drzavljanstvo,
                        ziv: true
                    });
                }
            },
            addAutor() {
                this.$emit('addAutor');
                this.ime = '';
                this.prezime = '';
                this.pseudonim = '';
                this.adresa = {};
                this.drzavljanstvo = '';
                this.godinaSmrti = -1;
                this.zivChoice = 1;
                this.$refs.licniPodaci.clear();
                this.$refs.adresaUnos.clear();
            }
        }
    }
</script>

<style scoped>
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
    h3 {
        margin-top: 30px;
    }
    select {
        margin-bottom: 15px;
    }
    button {
        margin: 7px 0;
        font-size: 16px;
        padding: 2px 5px;
    }
</style>