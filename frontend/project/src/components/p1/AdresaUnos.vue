<template>
    <div>
        <div class="flex-container">
            <div id="street" class="flex-container-v item">
                <label for="street-input" class="item">Ulica</label>
                <input id="street-input" type="text" :class="valid.ulica?'item':'item red-border'" v-model="ulica" @input="validateUlica"/>
            </div>
            <div id="num" class="flex-container-v">
                <label for="num-input">Broj objekta</label>
                <input id="num-input" type="number" :class="valid.broj?'':'red-border'" v-model="broj" @input="validateBroj"/>
            </div>
        </div>
        <div class="flex-container">
            <div id="city" class="flex-container-v item">
                <label for="city-input" class="item">Mesto</label>
                <input id="city-input" type="text" :class="valid.mesto?'item':'item red-border'" v-model="mesto" @input="validateMesto"/>
            </div>
            <div id="zip" class="flex-container-v">
                <label for="zip-input">Poštanski broj</label>
                <input id="zip-input" type="number" :class="valid.postanskiBroj?'':'red-border'" v-model="postanskiBroj" @input="validatePostanskiBroj"/>
            </div>
        </div>
        <div v-if="drzavaPotrebna" class="flex-container-v">
            <label for="country-input">Država</label>
            <input id="country-input" :class="valid.drzava?'':'red-border'" v-model="drzava" @input="validateDrzava"/>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'AdresaUnos',
        props: ['drzavaPotrebna', 'optional'],
        data() {
            return {
                ulica: '',
                broj: '',
                mesto: '',
                postanskiBroj: '',
                drzava: '',
                valid: {
                    ulica: true,
                    broj: true,
                    mesto: true,
                    postanskiBroj: true,
                    drzava: true
                }
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
            },
            validateUlica() {
                this.valid.ulica = (this.optional || this.ulica != '');
                this.updateAdresa();
            },
            validateBroj() {
                this.valid.broj = (this.optional || this.broj != '');
                this.updateAdresa();
            },
            validateMesto() {
                this.valid.mesto = (this.optional || this.mesto != '');
                this.updateAdresa();
            },
            validatePostanskiBroj() {
                this.valid.postanskiBroj = (this.optional || this.postanskiBroj != '');
                this.updateAdresa();
            },
            validateDrzava() {
                this.valid.drzava = (!this.drzavaPotrebna || this.drzava != '');
                this.updateAdresa();
            },
            isValidInput() {
                if (this.optional) return true;
                this.validateUlica();
                this.validateBroj();
                this.validateMesto();
                this.validatePostanskiBroj();
                this.validateDrzava();
                return this.valid.ulica && this.valid.broj && this.valid.mesto && this.valid.postanskiBroj && this.valid.drzava;
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