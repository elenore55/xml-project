<template>
    <div>
        <h2>Plaćene takse</h2>
        <div class="grid-container">
            <label id="osnovna-taksa-lbl">Osnovna taksa (RSD)</label>
            <input type="number" min="0" v-model="osnovnaTaksa" id="osnovna-taksa-input" @input="updatePlaceneTakse"/>

            <label id="graf-lbl">Grafičko rešenje (RSD)</label>
            <input type="number" min="0" id="graf-input" v-model="grafickoResenje" @input="updatePlaceneTakse"/>
            
            <label id="klasa-lbl">Klasa</label>
            <input type="text" min="0" id="klasa-input" v-model="klasa"/>
            
            <label id="klasa-iznos-lbl">Iznos (RSD)</label>
            <input type="number" min="0" v-model="klasaIznos" id="klasa-iznos-input" @input="updatePlaceneTakse"/>
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
            updatePlaceneTakse() {
                if (!this.osnovnaTaksa) this.osnovnaTaksa = 0;
                if (!this.grafickoResenje) this.grafickoResenje = 0;
                if (!this.klasaIznos) this.klasaIznos = 0;
                this.$emit('updatePlaceneTakse', {
                    osnovnaTaksa: this.osnovnaTaksa,
                    grafickoResenje: this.grafickoResenje,
                    klasa: {naziv: this.klasa, iznos: this.klasaIznos},
                    ukupno: this.ukupno
                });
            },
            clear() {
                this.osnovnaTaksa = 0;
                this.klasa = '';
                this.klase = [];
                this.klasaIznos = 0;
                this.grafickoResenje = 0;
                this.updatePlaceneTakse();
            }
        },
        computed: {
            ukupno() {
                return parseInt(this.osnovnaTaksa) + parseInt(this.grafickoResenje) + parseInt(this.klasaIznos);
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