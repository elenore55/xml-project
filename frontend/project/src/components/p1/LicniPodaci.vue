<template>
    <div class="flex-container">
        <div id="name" class="flex-container-v item">
            <label for="name-input" class="item">Ime</label>
            <input id="name-input" type="text" :class="valid.ime?'item':'item red-border'" v-model="ime" @input="validateIme"/>
        </div>
        <div id="surname" class="flex-container-v item">
            <label for="surname-input">Prezime</label>
            <input id="surname-input" type="text" :class="valid.prezime?'item':'item red-border'" v-model="prezime" @input="validatePrezime"/>
        </div>
        <div v-if="drzavljanstvoPotrebno" id="citizenship" class="flex-container-v item">
            <label for="citizenship-input">Državljanstvo</label>
            <input id="citizenship-input" type="text" :class="valid.drzavljanstvo?'':'red-border'" v-model="drzavljanstvo" @input="validateDrzavljanstvo"/>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'LicniPodaci',
        props: ['drzavljanstvoPotrebno'],
        data() {
            return {
                ime: '',
                prezime: '',
                drzavljanstvo: '',
                valid: {
                    ime: true,
                    prezime: true,
                    drzavljanstvo: true
                }
            }
        },
        methods: {
            updateLicniPodaci() {
                this.prezime = this.prezime.toUpperCase();
                this.$emit('updateLicniPodaci', {
                    ime: this.ime,
                    prezime: this.prezime,
                    drzavljanstvo: this.drzavljanstvo
                })
            },
            clear() {
                this.ime = '';
                this.prezime = '';
                this.drzavljanstvo = '';
                this.updateLicniPodaci();
            },
            validateIme() {
                this.valid.ime = (this.ime != '');
                this.updateLicniPodaci();
            },
            validatePrezime() {
                this.valid.prezime = (this.prezime != '');
                this.updateLicniPodaci();
            },
            validateDrzavljanstvo() {
                this.valid.drzavljanstvo = (!this.drzavljanstvoPotrebno || this.drzavljanstvo != '');
                this.updateLicniPodaci();
            },
            isValidInput() {
                this.validateIme();
                this.validatePrezime();
                this.validateDrzavljanstvo();
                return this.valid.ime && this.valid.prezime && this.valid.drzavljanstvo;
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