<template>
    <div>
        <h2>Plaćene takse</h2>
        <div class="grid-container">
            <label id="osnovna-taksa-lbl">Osnovna taksa (RSD)</label>
            <input type="number" v-model="osnovnaTaksa" id="osnovna-taksa-input" @input="updatePlaceneTakse"/>

            <label id="graf-lbl">Grafičko rešenje (RSD)</label>
            <input type="number" id="graf-input" v-model="grafickoResenje" @input="updatePlaceneTakse"/>
            
            <label id="klasa-lbl">Klasa</label>
            <input type="text" id="klasa-input" v-model="klasa"/>
            
            <label id="klasa-iznos-lbl">Iznos (RSD)</label>
            <input type="number" v-model="klasaIznos" id="klasa-iznos-input"/>

            <button type="button" id="btn-dodaj-klasu" v-if="klasa && klasaIznos > 0" @click="dodajKlasu">Dodaj klasu</button>
            <button type="button" id="btn-dodaj-klasu" v-else disabled>Dodaj klasu</button>

            <p id="klase-list-p" v-if="klase.length > 0">Dodate klase:</p>
            <ul id="klase-list-ul">
                <li v-for="(k, i) in klase" :key="k.id">
                    {{ k.klasa }}: {{ k.iznos }} RSD 
                    <button type="button" @click="ukloniKlasu(i)">X</button>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'PlaceneTakse',
        data() {
            return {
                osnovnaTaksa: 0,
                klase: [],
                grafickoResenje: 0,
                klasa: '',
                klasaIznos: 0
            }
        },
        methods: {
            dodajKlasu() {
                if (this.klasa && this.klasaIznos) {
                    this.klase.push({klasa: this.klasa, iznos: this.klasaIznos});
                    this.klasa = '';
                    this.klasaIznos = 0;
                    this.updatePlaceneTakse();
                }
            },
            ukloniKlasu(i) {
                this.klase.splice(i, 1);
                this.updatePlaceneTakse();
            },
            updatePlaceneTakse() {
                this.$emit('updatePlaceneTakse', {
                    osnovnaTakse: this.osnovnaTaksa,
                    grafickoResenje: this.grafickoResenje,
                    klase: this.klase,
                    ukupno: this.ukupno
                });
            }
        },
        computed: {
            ukupno() {
                let suma = this.osnovnaTaksa + this.grafickoResenje;
                for (const k in this.klase) {
                    suma += this.klase[k];
                }
                return suma;
            }
        }
    }
</script>

<style scoped>
    h2 {
        margin-top: 50px;
    }
    h3 {
        margin-top: 30px;
    }
    .grid-container {
        display: grid;
        column-gap: 15px;
        row-gap: 10px;
    }
    .grid-container label {
        text-align: end;
    }
    #osnovna-taksa-lbl {
        grid-row: 1;
        grid-column: 1;
    }
    #osnovna-taksa-input {
        grid-row: 1;
        grid-column: 2;
    }
    #graf-lbl {
        grid-row: 2;
        grid-column: 1;
    }
    #graf-input {
        grid-row: 2;
        grid-column: 2;
    }
    #klasa-lbl {
        grid-row: 3;
        grid-column: 1;
    }
    #klasa-input {
        grid-row: 3;
        grid-column: 2;
    }
    #klasa-iznos-lbl {
        grid-row: 3;
        grid-column: 3;
    }
    #klasa-iznos-input {
        grid-row: 3;
        grid-column: 4;
    }
    #btn-dodaj-klasu {
        grid-row: 3;
        grid-column: 5;
        font-size: 16px;
    }
    #klase-list-p {
        grid-row: 4;
        grid-column: 1;
        font-size: 18px;
        margin-left: auto;
    }
    #klase-list-ul {
        grid-row: 4;
        grid-column: 2;
        font-size: 18px;
        margin-left: 0;
    }
    .flex-container {
        display: flex;
        gap: 0 20px;
        margin-bottom: 10px;
    }
    .flex-container-sm {
        display: flex;
        gap: 0 5px;
        margin-bottom: 10px;
    }
    .flex-container-v {
        display: flex;
        flex-direction: column;
        gap: 10px;
    }
    li {
        margin-bottom: 10px;
    }
</style>