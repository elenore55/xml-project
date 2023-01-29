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
    </div>
</template>

<script>
    export default {
        name: 'LicniPodaci',
        data() {
            return {
                ime: '',
                prezime: '',
                valid: {
                    ime: true,
                    prezime: true
                }
            }
        },
        methods: {
            updateLicniPodaci() {
                this.$emit('updateLicniPodaci', {
                    ime: this.ime,
                    prezime: this.prezime
                })
            },
            clear() {
                this.ime = '';
                this.prezime = '';
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
            isValidInput() {
                this.validateIme();
                this.validatePrezime();
                return this.valid.ime && this.valid.prezime;
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