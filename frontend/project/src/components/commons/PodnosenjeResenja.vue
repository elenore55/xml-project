<template>
    <div>
        <UserNavbar></UserNavbar>
        <div class="centered">
            <div class="flex-container">
                <div class="flex-container-v item">
                    <h3>Tekstualni sadržaj</h3>
                    <input type="text" v-model="tekst"/>
                    <div>
                        <input type="checkbox" v-model="matchCase" />Match case
                    </div>
                    <button type="button" @click="osnovnaPretraga">Osnovna pretraga</button>
                </div>
                <div class="flex-container-v item">
                    <h3>Upit</h3>
                    <FilterUnos isZahtev="true" @updateFilter="updateFilter($event)"></FilterUnos>
                    <button type="button" @click="naprednaPretraga">Napredna pretraga</button>
                </div>
            </div>
        </div>
        <hr/>
        <div class="fullscreen flex-container">
            <div class="item">
                <div v-for="(r, i) in zahtevi" :key="r.id">
                    <div class="link flex-container-sm">
                        <div style="min-width: 130px; text-align: right;">
                            <a href="#" @click="prikaziZahtev(getId(r))" >Zahtev-{{ getId(r) }}</a>
                        </div>
                        <button v-if="activeZahtev == -1" type="button" @click="activateZahtev(i)">Podnesi rešenje</button>
                        <button v-else-if="activeZahtev != i" type="button" disabled>Podnesi rešenje</button>
                        <button v-else type="button" @click="deactivateZahtev">Odustani</button>
                    </div>
                    <div v-if="activeZahtev == i">
                        <div class="flex-container resenjeChoice">
                            <div>
                                <input type="radio" name="resenje" @change="setOdbijenSelected(false)"/>
                                <label>Odobren</label>
                            </div>
                            <div>
                                <input type="radio" name="resenje" @change="setOdbijenSelected(true)"/>
                                <label>Odbijen</label>
                            </div>
                        </div>
                        <div v-if="odbijenSelected">
                            <label for="razlog">Razlog odbijanja</label>
                            <textarea v-model="razlogOdbijanja" rows="3" id="ta-razlog-odbijanja"></textarea>
                        </div>
                        <button type="button" class="btn-potvrdi" @click="submitResenje(i)">Potvrdi</button>
                    </div>
                </div>
                
            </div>
            <div v-if="htmlContent != ''" class="item-big">
                <div v-html="htmlContent" id="div-html"></div>
            </div>
        </div>
    </div>
</template>

<script>
    import UserNavbar from '../user/UserNavbar.vue';
    import CommonsService from '@/services/CommonsService';
    import * as xml2js from 'xml2js';
    import * as js2xml from 'js2xmlparser';
    import FilterUnos from './FilterUnos.vue';

    export default {
        name: 'PodnosenjeResenja',
        components: {
            UserNavbar,
            FilterUnos
        },
        mounted() {
            CommonsService.getNereseniZahtevi().then((response) => {
                xml2js.parseString(response.data, (_err, result) => {
                    for (let i of result.List.item) {
                        this.zahtevi.push(JSON.parse(JSON.stringify(i)));
                    }
                });
            }).catch((err) => {
                console.log(err);
            });
        },
        data() {
            return {
                zahtevi: [],
                tekst: '',
                upit: '',
                matchCase: false,
                htmlContent: '',
                activeZahtev: -1,
                odbijenSelected: false,
                razlogOdbijanja: '',
                rows: []
            }
        },
        methods: {
            osnovnaPretraga() {
                this.zahtevi = [];
                CommonsService.osnovnaPretragaBezResenja(this.tekst, this.matchCase).then((response) => {
                    xml2js.parseString(response.data, (_err, result) => {
                        for (let i of result.List.item) {
                            this.zahtevi.push(JSON.parse(JSON.stringify(i)));
                        }
                    });
                    this.htmlContent = '';
                }).catch((err) => {
                    console.log(err);
                });
            },
            naprednaPretraga() {
                this.zahtevi = [];
                CommonsService.naprednaPretragaBezResenja(this.rows).then((response) => {
                    xml2js.parseString(response.data, (_err, result) => {
                        for (let i of result.List.item) {
                            this.zahtevi.push(JSON.parse(JSON.stringify(i)));
                        }
                    });
                    this.htmlContent = '';
                }).catch((err) => {
                    console.log(err);
                });
            },
            prikaziZahtev(broj) {
                CommonsService.getOne(broj).then((response) => {
                    this.htmlContent = response.data;
                }).catch((err) => {
                    console.log(err);
                });
            },
            activateZahtev(i) {
                this.activeZahtev = i;
            },
            deactivateZahtev() {
                this.activeZahtev = -1;
                this.odbijenSelected = false;
                this.razlogOdbijanja = '';
                this.htmlContent = '';
            },
            setOdbijenSelected(value) {
                this.odbijenSelected = value;
            },
            submitResenje(i) {
                let resenje = {
                    imeSluzbenika: localStorage.getItem('name'),
                    prezimeSluzbenika: localStorage.getItem('surname'),
                    nazivDokumenta: `Zahtev${this.getId(this.zahtevi[i])}`
                }
                if (this.odbijenSelected) {
                    resenje['obrazlozenje'] = this.razlogOdbijanja;
                    CommonsService.odbijZahtev(js2xml.parse('resenje', resenje)).then((_response) => {
                        alert('Zahtev odbijen!');
                        this.zahtevi.splice(i, 1);
                        this.deactivateZahtev();
                    }).catch((err) => {
                        console.log(err);
                    });
                } else {
                    let string = js2xml.parse('resenje', resenje);
                    console.log(string);
                    CommonsService.odobriZahtev(string).then((_response) => {
                        alert('Zahtev odobren!');
                        this.zahtevi.splice(i, 1);
                        this.deactivateZahtev();
                    }).catch((err) => {
                        console.log(err);
                    });
                }
            },
            updateFilter(rows) {
                this.rows = rows;
            },
            getId(zahtev) {
                return CommonsService.getId(zahtev);
            }
        }
    }
</script>

<style scoped>
    #ta-razlog-odbijanja, label[for="razlog"] {
        display: block;
    }
    #ta-razlog-odbijanja {
        width: 250px;
        margin-top: 5px;
    }
    .btn-potvrdi {
        margin: 10px 0;
    }
    label {
        margin-left: 5px;
    }
    .centered-text {
        text-align: center;
    }
    .centered {
        margin: auto;
        width: 90%;
    }
    .flex-container {
        display: flex;
        gap: 0 70px;
        margin-bottom: 10px;
        margin-top: 20px;
    }
    .flex-container-sm {
        display: flex;
        gap: 0 25px;
        margin-bottom: 10px;
        margin-top: 20px;
    }
    .flex-container-v {
        display: flex;
        flex-direction: column;
        gap: 15px;
    }
    .item {
        flex: 2
    }
    .item-big {
        flex: 5
    }
    .fullscreen {
        width: 90%;
        margin: auto;
        margin-top: 30px;
    }
    h3 {
        margin-bottom: 5px;
    }
    button {
        font-size: 15px;
    }
    .link {
        margin: 20px 0;
    }
    #div-html {
        border: 2px solid black;
        padding: 10px;
    }
    a {
        font-size: 18px;
        margin: 5px 0;
    }
</style>