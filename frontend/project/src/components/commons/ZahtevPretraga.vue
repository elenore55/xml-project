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
            <!-- <div class="item">
                <div v-for="r in rezultati" :key="r.id" class="link flex-container-sm">
                        <a href="#/zahtevPretraga" @click="prikaziZahtev(getId(r))" >Zahtev-{{ getId(r) }}</a>
                        <button type="button" class="small" @click="downloadHTML(getId(r))">HTML</button>
                        <button type="button" class="small" @click="downloadPDF(getId(r))">PDF</button>
                </div>
            </div> -->
            <div class="item">
                <table style="border: 1px solid white;">
                    <tr v-for="r in rezultati" :key="r.id" style="border: 1px solid white" class="link">
                        <td class="zahtev-name">
                            <a href="#/zahtevPretraga" @click="prikaziZahtev(getId(r))" >Zahtev-{{ getId(r) }}</a>
                        </td>
                        <td style="border: 1px solid white">
                            <button type="button" class="small" @click="downloadHTML(getId(r))">HTML</button>
                        </td>
                        <td style="border: 1px solid white">
                            <button type="button" class="small" @click="downloadPDF(getId(r))">PDF</button>
                        </td>
                    </tr>
                </table>
            </div>
            <div v-if="htmlContent != ''" class="item-big">
                <a target="_blank" rel="noreferrer" :href="`#/referencirajuci/${selectedZahtevId}/${servis}`" id="ref-docs">
                    Referencirajući dokumenti
                </a>
                <div v-html="htmlContent" id="div-html"></div>
            </div>
        </div>
    </div>
</template>

<script>
    import * as xml2js from 'xml2js';
    import CommonsService from '@/services/CommonsService';
    import UserNavbar from '../user/UserNavbar.vue';
    import FilterUnos from './FilterUnos.vue';

    export default {
        name: 'ZahtevPretraga',
        components: {
            UserNavbar,
            FilterUnos
        },
        data() {
            return {
                tekst: '',
                upit: '',
                matchCase: false,
                rezultati: [],
                htmlContent: '',
                rows: [],
                selectedZahtevId: ''
            }
        },
        methods: {
            osnovnaPretraga() {
                this.rezultati = [];
                CommonsService.osnovnaPretraga(this.tekst, this.matchCase)
                .then((response) => {
                    xml2js.parseString(response.data, (_err, result) => {
                        console.log(result);
                        for (let i of result.List.item) {
                            this.rezultati.push(JSON.parse(JSON.stringify(i)));
                        }
                    });
                    this.htmlContent = '';
                })
                .catch((err) => {
                    console.log(err);
                });
            },
            naprednaPretraga() {
                this.rezultati = [];
                CommonsService.naprednaPretraga(this.rows).then((response) => {
                    xml2js.parseString(response.data, (_err, result) => {
                        for (let i of result.List.item) {
                            this.rezultati.push(JSON.parse(JSON.stringify(i)));
                        }
                    });
                    this.htmlContent = '';
                }).catch((err) => {
                    console.log(err);
                });
            },
            prikaziZahtev(broj) {
                this.selectedZahtevId = broj;
                CommonsService.getOne(broj).then((response) => {
                    this.htmlContent = response.data;
                }).catch((err) => {
                    console.log(err);
                });
            },
            downloadPDF(broj) {
                CommonsService.downloadZahtevPDF(broj);
            },
            downloadHTML(broj) {
                CommonsService.downloadZahtevHTML(broj);
            },
            updateFilter(rows) {
                this.rows = rows;
            },
            getId(zahtev) {
                return CommonsService.getId(zahtev);
            }
        },
        computed: {
            servis() {
                return CommonsService.getServis();
            }
        }
    }
</script>

<style scoped>
    td {
        padding: 1px 2px 20px 1px;    
    }
    .zahtev-name {
        padding-right: 20px;
        text-align: right;
        border: 1px solid white;
        min-width: 120px;
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
        font-size: 16px;
    }
    .small {
        font-size: 14px;
    }
    .link {
        padding: 30px 0;
    }
    #div-html {
        border: 2px solid black;
        padding: 10px;
    }
    #ref-docs {
        margin: 10px 0;
    }
</style>