<template>
    <div>
        <h2>Prilozi</h2>
        <div class="flex-container">
            <div class="flex-container-v item">
                <label>Primerak znaka</label>
                <input type="file" @change="primerakZnakaChanged"/>
            </div>
            <div class="flex-container-v item">
                <label>Opšti akt o kolektivnom žigu/žigu garancije</label>
                <input type="file" @change="opstiAktChanged"/>
            </div>
        </div>

        <div class="flex-container">
            <div class="flex-container-v item">
                <label>Punomoćje</label>
                <input type="file" @change="punomocjeChanged"/>
            </div>
            <div class="item cb">
                <input type="checkbox" v-model="generalnoPunomocjeRanijePrilozeno" @input="checkRanijePrilozeno($event)"/>
                <label>Generalno punomoćje ranije priloženo</label>
            </div>
            <div class="item cb">
                <input type="checkbox" v-model="punomocjeNaknadnoDostavljeno" @input="checkNaknadnoDostavljeno($event)"/>
                <label>Punomoćje će biti naknadno dostavljeno</label>
            </div>    
        </div>

        <div class="flex-container">
            <div class="flex-container-v item">
                <label>Spisak robe i usluga</label>
                <input type="file" @change="spisakRobeChanged"/>
            </div>
            <div class="flex-container-v item">
                <label>Dokaz o pravu prvenstva</label>
                <input type="file" @change="dokazPravoChanged"/>
            </div>
            <div class="flex-container-v item">
                <label>Dokaz o uplati takse</label>
                <input type="file" @change="dokazUplataChanged"/>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'PriloziUnos',
        data() {
            return {
                primerakZnaka: '',
                spisakRobe: '',
                punomocje: '',
                generalnoPunomocjeRanijePrilozeno: false,
                punomocjeNaknadnoDostavljeno: false,
                opstiAkt: '',
                dokazOPravuPrvenstva: '',
                dokazOUplatiTakse: ''
            }
        },
        methods: {
            updatePrilozi() {
                this.$emit('updatePrilozi', {
                    primerakZnaka: this.primerakZnaka,
                    spisakRobeIUsluga: this.spisakRobe,
                    punomocje: this.punomocje,
                    generalnoPunomocjeRanijePrilozeno: this.generalnoPunomocjeRanijePrilozeno,
                    punomocjeNaknadnoDostavljeno: this.punomocjeNaknadnoDostavljeno,
                    opstiAkt: this.opstiAkt,
                    dokazOPravuPrvenstva: this.dokazOPravuPrvenstva,
                    dokazOUplatiTakse: this.dokazOUplatiTakse
                })
            },
            primerakZnakaChanged(event) {
                this.primerakZnaka = event.target.files[0];
                this.updatePrilozi();
            },
            opstiAktChanged(event) {
                this.opstiAkt = event.target.files[0];
                this.updatePrilozi();
            },
            punomocjeChanged(event) {
                this.punomocje = event.target.files[0];
                this.updatePrilozi();
            },
            spisakRobeChanged(event) {
                this.spisakRobe = event.target.files[0];
                this.updatePrilozi();
            },
            dokazPravoChanged(event) {
                this.dokazOPravuPrvenstva = event.target.files[0];
                this.updatePrilozi();
            },
            dokazUplataChanged(event) {
                this.dokazOUplatiTakse = event.target.files[0];
                this.updatePrilozi();
            },
            checkRanijePrilozeno(event) {
                this.generalnoPunomocjeRanijePrilozeno = event.target.checked;
                this.updatePrilozi();
            },
            checkNaknadnoDostavljeno(event) {
                this.punomocjeNaknadnoDostavljeno = event.target.checked;
                this.updatePrilozi();
            }
        }
    }
</script>

<style scoped>
    .flex-container {
        display: flex;
        gap: 0 20px;
        margin-bottom: 45px;
    }
    .flex-container-v {
        display: flex;
        flex-direction: column;
        gap: 10px;
    }
    .item {
        flex: 1
    }
    .cb {
        padding-top: 10px;
    }
</style>