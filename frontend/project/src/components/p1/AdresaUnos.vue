<template>
    <div>
        <div class="flex-container">
            <div id="street" class="flex-container-v item">
                <label for="street-input" class="item">Ulica</label>
                <input id="street-input" type="text" class="item" v-model="ulica" @input="updateAdresa"/>
            </div>
            <div id="num" class="flex-container-v">
                <label for="num-input">Broj objekta</label>
                <input id="num-input" type="number" v-model="broj" @input="updateAdresa"/>
            </div>
        </div>
        <div class="flex-container">
            <div id="city" class="flex-container-v item">
                <label for="city-input" class="item">Mesto</label>
                <input id="city-input" type="text" class="item" v-model="mesto" @input="updateAdresa"/>
            </div>
            <div id="zip" class="flex-container-v">
                <label for="zip-input">Poštanski broj</label>
                <input id="zip-input" type="number" v-model="postanskiBroj" @input="updateAdresa"/>
            </div>
        </div>
        <div v-if="drzavaPotrebna" class="flex-container-v">
            <label for="country-input">Država</label>
            <input id="country-input" v-model="drzava" @input="updateAdresa"/>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'AdresaUnos',
        props: ['drzavaPotrebna'],
        data() {
            return {
                ulica: '',
                broj: '',
                mesto: '',
                postanskiBroj: '',
                drzava: ''
            }
        },
        methods: {
            updateAdresa() {
                this.$emit('updateAdresa', {
                    ulica: this.ulica,
                    broj: this.broj,
                    mesto: this.mesto,
                    postanskiBroj: this.postanskiBroj,
                    drzava: this.drzava
                })
            },
            clear() {
                this.ulica = '';
                this.broj = '';
                this.mesto = '';
                this.postanskiBroj = '';
                this.drzava = '';
                this.updateAdresa();
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