<template>
    <div>
        <div class="flex-container">
            <div id="street" class="flex-container-v item">
                <label for="street-input" class="item">Ulica</label>
                <input id="street-input" type="text" :class="validation.ulica?'item':'item red-border'" v-model="ulica" @input="updateUlica"/>
            </div>
            <div id="num" class="flex-container-v">
                <label for="num-input">Broj objekta</label>
                <input id="num-input" type="number" :class="validation.broj?'none':'red-border'" v-model="broj" @input="updateBroj"/>
            </div>
        </div>
        <div class="flex-container">
            <div id="city" class="flex-container-v item">
                <label for="city-input" class="item">Mesto</label>
                <input id="city-input" type="text" :class="validation.mesto?'item':'item red-border'" v-model="mesto" @input="updateMesto"/>
            </div>
            <div id="zip" class="flex-container-v">
                <label for="zip-input">Poštanski broj</label>
                <input id="zip-input" type="number" :class="validation.postanskiBroj?'none':'red-border'" v-model="postanskiBroj" @input="updatePostanskiBroj"/>
            </div>
        </div>
    </div>
</template>

<script>

    export default {
        name: 'AdresaUnos',
        data() {
            return {
                ulica: '',
                broj: '',
                mesto: '',
                postanskiBroj: '',
                validation: {
                    ulica: true,
                    broj: true,
                    mesto: true,
                    postanskiBroj: true
                }
            }
        },
        methods: {
            updateUlica() {
                this.validation.ulica = (this.ulica != '');
                this.updateAdresa();
            },
            updateBroj() {
                this.validation.broj = (this.broj != '' && this.broj != 0);
                this.updateAdresa();
            },
            updateMesto() {
                this.validation.mesto = (this.mesto != '');
                this.updateAdresa();
            },
            updatePostanskiBroj() {
                this.validation.postanskiBroj = (this.postanskiBroj != '' && this.postanskiBroj != 0);
                this.updateAdresa();
            },
            updateAdresa() {
                this.$emit('updateAdresa', {
                    ulica: this.ulica,
                    broj: this.broj,
                    mesto: this.mesto,
                    postanskiBroj: this.postanskiBroj
                })
            },
            clear() {
                this.ulica = '';
                this.broj = '';
                this.mesto = '';
                this.postanskiBroj = '';
                this.updateAdresa();
            },
            isValidInput() {
                this.updateUlica();
                this.updateBroj();
                this.updateMesto();
                this.updatePostanskiBroj();
                return this.validation.ulica && this.validation.broj && this.validation.mesto && this.validation.postanskiBroj;
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