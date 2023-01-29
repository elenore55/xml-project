<template>
    <div class="flex-container">
        <div id="name" class="flex-container-v item">
            <label for="name-input" class="item">Ime</label>
            <input id="name-input" type="text" :class="validation.ime?'item': 'item red-border'" v-model="ime" @input="validateIme"/>
        </div>
        <div id="surname" class="flex-container-v item">
            <label for="surname-input">Prezime</label>
            <input id="surname-input" type="text" :class="validation.prezime?'item': 'item red-border'" v-model="prezime" @input="validatePrezime"/>
        </div>
        <div v-if="drzavljanstvoPotrebno" id="citizen" class="flex-container-v item">
            <label for="citizen-input">Državljanstvo</label>
            <input id="citizen-input" type="text" :class="validation.drzavljanstvo?'item': 'item red-border'" v-model="drzavljanstvo" @input="validateDrzavljanstvo"/>
        </div>
        <div v-if="godinaSmrtiPotrebna" class="flex-container-v item">
            <label for="smrt-input">Godina smrti</label>
            <input id="smrt-input" type="number" :max="new Date().getFullYear() " :class="validation.godinaSmrti?'item': 'item red-border'" v-model="godinaSmrti" @input="validateGodinaSmrti"/>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'LicniPodaci',
        props: ['drzavljanstvoPotrebno', 'godinaSmrtiPotrebna'],
        data() {
            return {
                ime: '',
                prezime: '',
                drzavljanstvo: '',
                godinaSmrti: '',
                validation: {
                    ime: true,
                    prezime: true,
                    drzavljanstvo: true,
                    godinaSmrti: true
                }
            }
        },
        methods: {
            updateLicniPodaci() {
                this.$emit('updateLicniPodaci', {
                    ime: this.ime,
                    prezime: this.prezime,
                    drzavljanstvo: this.drzavljanstvo,
                    godinaSmrti: this.godinaSmrti
                });
            },
            clear() {
                this.ime = '';
                this.prezime = '';
                this.drzavljanstvo = '';
                this.godinaSmrti = '';
                this.updateLicniPodaci();
            },
            isValidInput() {
                this.validateIme();
                this.validatePrezime();
                this.validateDrzavljanstvo();
                this.validateGodinaSmrti();
                return this.validation.ime && this.validation.prezime && this.validation.drzavljanstvo && this.validation.godinaSmrti;
            },
            validateIme() {
                this.validation.ime = (this.ime != '');
                this.updateLicniPodaci();
            },
            validatePrezime() {
                this.validation.prezime = (this.prezime != '');
                this.updateLicniPodaci();
            },
            validateDrzavljanstvo() {
                this.validation.drzavljanstvo = !this.drzavljanstvoPotrebno || (this.drzavljanstvo != '');
                this.updateLicniPodaci();
            },
            validateGodinaSmrti() {
                this.validation.godinaSmrti = !this.godinaSmrtiPotrebna || (this.godinaSmrti != '' && this.godinaSmrti <= new Date().getFullYear());
                this.updateLicniPodaci();
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
</style>