<template>
    <div>
        <h2>Podaci o žigu</h2>
        <div class="flex-container">
            <div class="flex-container-v item">
                <label class="item">Tip žiga</label>
                <select class="item" v-model="tipZigaChoice" @change="updateZig">
                    <option value="1">Individualni žig</option>
                    <option value="2">Kolektivni žig</option>
                    <option value="3">Žig garancije</option>
                </select>
            </div>
            <div class="flex-container-v item">
                <label>Vrsta znaka</label> 
                <input type="text" list="vrsta" v-model="vrstaZnaka" @change="updateZig" />
                <datalist id="vrsta">
                    <option>Verbalni znak</option>
                    <option>Grafički znak</option>
                    <option>Kombinovani znak</option>
                    <option>Trodimenzionalni znak</option>
                </datalist>
            </div>
            <div class="flex-container-v item">
                <label>Izgled znaka</label>
                <input type="file" style="width: 95%" @change="uploadSliku"/>
            </div>
        </div>
        
        <div class="flex-container">
            <div class="flex-container-v item">
                <label class="item">Transliteracija znaka</label>
                <input type="text" class="item" v-model="transliteracija" @input="updateZig"/>
            </div>
            <div class="flex-container-v item">
                <label>Prevod znaka</label>
                <input type="text" v-model="prevod" @input="updateZig"/>
            </div>
            <div class="flex-container-v item">
                <label>Opis znaka</label>
                <input type="text" v-model="opis" @input="updateZig"/>
            </div>
        </div>

        <div>
            <div class="flex-container">
                <label>Boje iz kojih se znak sastoji:</label>
                <div class="flex-container-sm" v-for="(b, i) in boje" :key="b.id">
                    <label>{{ b }}</label>
                    <button @click="ukloniBoju(i)">X</button>
                </div>
            </div>
            <div class="flex-container">
                <input type="text" v-model="boja"/>
                <button v-if="boja" type="button" @click="dodajBoju">Dodaj boju</button>
                <button v-else type="button" disabled>Dodaj boju</button>
            </div>
        </div>

        <div>
            <div class="flex-container">
                <label style="margin-top: 10px">Klase robe i usluga prema Ničanskoj klasifijaciji:</label>
                <div class="flex-container-sm" v-for="(k, i) in nicanskaKlasifikacija" :key="k.id">
                    <label>{{ k }}</label>
                    <button @click="ukloniKlasifikaciju(i)">X</button>
                </div>
            </div>
            <table>
                <tbody>
                    <tr>
                        <td v-for="num in half1" :key="num">
                            <button v-if="!nicanskaKlasifikacija.includes(num)" class="btn-num" @click="dodajKlasifikaciju(num)">{{ num }}</button>
                            <button v-else disabled class="btn-num">{{ num }}</button>
                        </td>
                    </tr>
                    <tr>
                        <td v-for="num in half2" :key="num">
                            <button v-if="!nicanskaKlasifikacija.includes(num)" class="btn-num" @click="dodajKlasifikaciju(num)">{{ num }}</button>
                            <button v-else disabled class="btn-num">{{ num }}</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div>
            <label>Pravo prvenstva i osnov</label>
            <input type="text" v-model="pravoPrvenstva" style="width: 100%" @input="updateZig"/>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'PodaciOZigu',
        data() {
            return {
                tipZigaChoice: 1,
                vrstaZnakaChoice: '',
                vrstaZnaka: '',
                vrstaZnakaInput: '',
                izgledZnaka: '',
                transliteracija: '',
                prevod: '',
                opis: '',
                boje: [],
                nicanskaKlasifikacija: [],
                pravoPrvenstva: '',
                boja: '',
                half1: [...Array(23).keys()].map(i => i + 1),
                half2: [...Array(22).keys()].map(i => i + 24),
            }
        },
        methods: {
            updateZig() {
                this.$emit('updateZig', {
                    tipZiga: this.tipZiga,
                    vrstaZnaka: this.vrstaZnaka,
                    izgledZnaka: this.izgledZnaka,
                    transliteracija: this.transliteracija,
                    prevod: this.prevod,
                    opis: this.opis,
                    bojeList: {boje: this.boje},
                    nicanskaKlasifikacijaList: {nicanskaKlasifikacija: this.nicanskaKlasifikacija},
                    pravoPrvenstva: this.pravoPrvenstva
                });
            },
            dodajBoju() {
                if (this.boja) {
                    this.boje.push(this.boja);
                    this.boja = '';
                    this.updateZig();
                }
            },
            dodajKlasifikaciju(num) {
                this.nicanskaKlasifikacija.push(num);
                this.updateZig();
            },
            ukloniBoju(i) {
                this.boje.splice(i, 1);
                this.updateZig();
            },
            ukloniKlasifikaciju(i) {
                this.nicanskaKlasifikacija.splice(i, 1);
                this.updateZig();
            },
            uploadSliku(event) {
                this.izgledZnaka = event.target.files[0];
                this.updateZig();
            } 
        },
        computed: {
            tipZiga() {
                if (this.tipZigaChoice == 1) return 'INDIVIDUALNI_ZIG';
                else if (this.tipZigaChoice == 2) return 'KOLEKTIVNI_ZIG';
                return 'ZIG_GARANCIJE';
            }
        }
    }
</script>

<style scoped> 
    .btn-num {
        width: 35px;
    }
    h2 {
        margin-top: 50px;
    }
    h3 {
        margin-top: 30px;
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
        flex-wrap: wrap;
    }
    .flex-container-v {
        display: flex;
        flex-direction: column;
        gap: 10px;
    }
    .item {
        flex: 1
    }
    label, select, input {
        display: block;
    }
    button {
        font-size: 16px;
    }
    table {
        width: 100%;
    }
    td {
        text-align: center;
        padding: 5px 0;
    }
    input, select, table {
        margin-bottom: 10px;
    }
</style>