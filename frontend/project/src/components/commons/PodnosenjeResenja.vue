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
                    <textarea rows="4"></textarea>
                    <button type="button" @click="naprednaPretraga">Napredna pretraga</button>
                </div>
            </div>
        </div>
        <hr/>
        <div class="fullscreen flex-container">
            <div class="item">
                <div v-for="(r, i) in zahtevi" :key="r.id">
                    <div class="link flex-container-sm">
                        <a href="#" @click="prikaziZahtev(r.popunjavaZavod[0].brojPrijave[0])" >Zahtev-{{ r.popunjavaZavod[0].brojPrijave[0] }}</a>
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
                            <textarea v-model="razlogOdbijanja" rows="3"></textarea>
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

    export default {
        name: 'PodnosenjeResenja',
        components: {
            UserNavbar
        },
        mounted() {
            let that = this;
            CommonsService.getNereseniZahtevi().then((response) => {
                xml2js.parseString(response.data, function(_err, result) {
                    for (let i of result.List.item) {
                        that.zahtevi.push(JSON.parse(JSON.stringify(i)));
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
                razlogOdbijanja: ''
            }
        },
        methods: {
            osnovnaPretraga() {
                this.zahtevi = [];
                let that = this;
                CommonsService.osnovnaPretraga(this.tekst, this.matchCase)
                .then((response) => {
                    xml2js.parseString(response.data, function(_err, result) {
                        for (let i of result.List.item) {
                            that.zahtevi.push(JSON.parse(JSON.stringify(i)));
                        }
                    });
                })
                .catch((err) => {
                    console.log(err);
                });
            },
            naprednaPretraga() {

            },
            prikaziZahtev(broj) {
                CommonsService.getOne(broj).then((response) => {
                    console.log(response.data);
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
            },
            setOdbijenSelected(value) {
                this.odbijenSelected = value;
            },
            submitResenje(i) {
                let resenje = {
                    imeSluzbenika: localStorage.getItem('name'),
                    prezimeSluzbenika: localStorage.getItem('surname'),
                    nazivDokumenta: `Zahtev${this.zahtevi[i].popunjavaZavod[0].brojPrijave[0]}`
                }
                if (this.odbijenSelected) {
                    resenje['obrazlozenje'] = this.razlogOdbijanja;
                    CommonsService.odbijZahtev(js2xml.parse('resenje', resenje)).then((response) => {
                        alert('Zahtev odbijen!');
                        this.zahtevi.splice(i, 1);
                        this.deactivateZahtev();
                    }).catch((err) => {
                        console.log(err);
                    });
                } else {
                    CommonsService.odobriZahtev(js2xml.parse('resenje', resenje)).then((response) => {
                        alert('Zahtev odobren!');
                        this.zahtevi.splice(i, 1);
                        this.deactivateZahtev();
                    }).catch((err) => {
                        console.log(err);
                    });
                }
            }
        }
    }
</script>

<style scoped>
    textarea, label[for="razlog"] {
        display: block;
    }
    textarea {
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
    textarea {
        width: 250px;
    }
    .centered {
        margin: auto;
        width: 60%;
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
        flex: 1
    }
    .item-big {
        flex: 3
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
        font-size: 16px;
    }
    button.small {
        font-size: 14px;
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