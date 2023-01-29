<template>
    <div>
        <h3>Kontakt</h3>
        <div class="flex-container">
            <div id="phone" class="flex-container-v item">
                <label for="phone-input" class="item">Telefon</label>
                <input id="phone-input" type="text" :class="valid.telefon?'item':'item red-border'" v-model="telefon" @input="validateTelefon"/>
            </div>
            <div id="email" class="flex-container-v item">
                <label for="email-input">E-mail</label>
                <input id="email-input" type="text" :class="valid.email?'item':'item red-border'" v-model="email" @input="validateEmail"/>
            </div>
            <div v-if="faksPotreban" id="fax" class="flex-container-v item">
                <label for="fax-input">Faks</label>
                <input id="fax-input" type="text" :class="valid.faks?'item':'item red-border'" v-model="faks" @input="validateFaks"/>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'KontaktPodaci',
        props: ['faksPotreban'],
        data() {
            return {
                telefon: '',
                email: '',
                faks: '',
                valid: {
                    telefon: true,
                    email: true,
                    faks: true
                }
            }
        },
        methods: {
            updateKontaktPodaci() {
                this.$emit('updateKontaktPodaci', {
                    telefon: this.telefon,
                    email: this.email,
                    faks: this.faks
                });
            },
            clear() {
                this.telefon = '';
                this.email = '';
                this.faks = '';
                this.updateKontaktPodaci();
            },
            validateTelefon() {
                this.valid.telefon = (this.telefon != '');
                this.updateKontaktPodaci();
            },
            validateEmail() {
                this.valid.email = (this.email != '');
                this.updateKontaktPodaci();
            },
            validateFaks() {
                this.valid.faks = (!this.faksPotreban || this.faks != '');
                this.updateKontaktPodaci();
            },
            isValidInput() {
                this.validateTelefon();
                this.validateEmail();
                this.validateFaks();
                return this.valid.telefon && this.valid.email && this.valid.faks;
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