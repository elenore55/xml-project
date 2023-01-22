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
                <div v-for="r in rezultati" :key="r.id" class="link flex-container-sm">
                        <a href="#/resenjePretraga" @click="prikaziResenje(r.referenca[0])" >Resenje-{{ r.referenca[0] }}</a>
                </div>
            </div>
            <ResenjeZahteva v-if="resenje.referenca" :referenca="resenje.referenca" :imeSluzbenika="resenje.imeSluzbenika" :sifra="resenje.sifra"
                :prezimeSluzbenika="resenje.prezimeSluzbenika" :obrazlozenje="resenje.obrazlozenje" :datum="resenje.datumResenja">
            </ResenjeZahteva>
        </div>
    </div>
</template>

<script>
    import * as xml2js from 'xml2js';
    import CommonsService from '@/services/CommonsService';
    import UserNavbar from '../user/UserNavbar.vue';
    import ResenjeZahteva from './ResenjeZahteva.vue';

    export default {
        name: 'ResenjePretraga',
        components: {
            UserNavbar,
            ResenjeZahteva
        },
        data() {
            return {
                tekst: '',
                upit: '',
                matchCase: false,
                rezultati: [],
                resenje: {}
            }
        },
        methods: {
            osnovnaPretraga() {
                this.rezultati = [];
                let that = this;
                CommonsService.osnovnaPretragaResenje(this.tekst, this.matchCase)
                .then((response) => {
                    console.log(response.data);
                    xml2js.parseString(response.data, function(_err, result) {
                        for (let i of result.List.item) {
                            that.rezultati.push(JSON.parse(JSON.stringify(i)));
                        }
                    });
                })
                .catch((err) => {
                    console.log(err);
                });
            },
            naprednaPretraga() {

            },
            prikaziResenje(broj) {
                CommonsService.getOneResenje(broj).then((response) => {
                    xml2js.parseString(response.data, (_err, result) => {
                        if (result['OdbijenZahtev']) {
                            let odbijen = result['OdbijenZahtev'];
                            this.resenje = {
                                referenca: odbijen['referenca'][0],
                                datumResenja: odbijen['datumResenja'][0],
                                obrazlozenje: odbijen['obrazlozenje'][0],
                                imeSluzbenika: odbijen['imeSluzbenika'][0],
                                prezimeSluzbenika: odbijen['prezimeSluzbenika'][0]
                            }
                        } else {
                            let odobren = result['OdobrenZahtev'];
                            this.resenje = {
                                referenca: odobren['referenca'][0],
                                datumResenja: odobren['datumResenja'][0],
                                sifra: odobren['sifra'][0],
                                imeSluzbenika: odobren['imeSluzbenika'][0],
                                prezimeSluzbenika: odobren['prezimeSluzbenika'][0]
                        }
                    }});
                    console.log(this.resenje);
                }).catch((err) => {
                    console.log(err);
                });
            },
        }
    }
</script>

<style scoped>
    .centered-text {
        text-align: center;
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
    #ref-docs {
        margin: 10px 0;
    }
</style>