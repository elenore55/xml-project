<template>
    <div class="flex-container">
        <div id="name" class="flex-container-v item">
            <label for="name-input" class="item">Ime</label>
            <input id="name-input" type="text" class="item" v-model="ime" @input="updateLicniPodaci"/>
        </div>
        <div id="surname" class="flex-container-v item">
            <label for="surname-input">Prezime</label>
            <input id="surname-input" type="text" v-model="prezime" @input="updateLicniPodaci"/>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'LicniPodaci',
        data() {
            return {
                ime: '',
                prezime: ''
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