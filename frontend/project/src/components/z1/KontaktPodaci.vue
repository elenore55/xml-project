<template>
    <div>
        <h3>Kontakt</h3>
        <div class="flex-container">
            <div id="phone" class="flex-container-v item">
                <label for="phone-input" class="item">Telefon</label>
                <input id="phone-input" type="text" class="item" v-model="telefon" @input="updateKontaktPodaci"/>
            </div>
            <div id="email" class="flex-container-v item">
                <label for="email-input">E-mail</label>
                <input id="email-input" type="text" v-model="email" @input="updateKontaktPodaci"/>
            </div>
            <div id="fax" class="flex-container-v item">
                <label for="fax-input">Faks</label>
                <input id="fax-input" type="text" v-model="faks" @input="updateKontaktPodaci"/>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'KontaktPodaci',
        data() {
            return {
                telefon: '',
                email: '',
                faks: ''
            }
        },
        methods: {
            updateKontaktPodaci() {
                this.$emit('updateKontaktPodaci', {
                    telefon: this.telefon,
                    email: this.email,
                    faks: this.faks
                });
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