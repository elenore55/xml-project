<template>
    <div class="centered">
        <h3>Dokumenti koji imaju referencu na Zahtev-{{ $route.params.id }}:</h3>
        <div v-if="docs.length == 0" style="margin-left: 30px">
            Nema rezultata
        </div>
        <div v-else-if="samoResenje" class="flex-container" >
            <div class="flex-container-v item">
                <a class="centered" :href="`#/referencirajuci/${$route.params.id}/${$route.params.servis}`" @click="prikaziResenje">
                    Resenje_Zahtev-{{ $route.params.id }}
                </a>
            </div>
            <div class="item-big">
                <ResenjeZahteva v-if="resenjePrikaz" :datum="resenje.datumResenja" :imeSluzbenika="resenje.imeSluzbenika" :prezimeSluzbenika="resenje.prezimeSluzbenika"
                    :referenca="resenje.referenca" :obrazlozenje="resenje.obrazlozenje" :sifra="resenje.sifra" :servis="$route.params.servis">
                </ResenjeZahteva>
            </div>
        </div>
        <div class="flex-container" v-else>
            <div class="flex-container-v item">
                <div v-for="d in docs" :key="d.id" class="centered">
                    <a v-if="d.broj != null" :href="`#/referencirajuci/${$route.params.id}/${$route.params.servis}`" @click="prikaziZahtev(d.broj)">
                        Zahtev-{{ d.broj }}
                    </a>
                    <a v-else class="centered" :href="`#/referencirajuci/${$route.params.id}/${$route.params.servis}`" @click="prikaziResenjeP1(d)">Resenje</a>
                </div>
            </div>
            <div class="item-big">
                <div v-if="htmlContent != ''" v-html="htmlContent" id="div-html"></div>
                <ResenjeZahteva v-if="resenjePrikazP1" :datum="resenje.datumResenja" :imeSluzbenika="resenje.imeSluzbenika" :prezimeSluzbenika="resenje.prezimeSluzbenika"
                    :referenca="resenje.referenca" :obrazlozenje="resenje.obrazlozenje" :sifra="resenje.sifra" :servis="$route.params.servis">
                </ResenjeZahteva>
            </div>
        </div>
    </div>
</template>

<script>
    import * as xml2js from 'xml2js';
    import CommonsService from '@/services/CommonsService';
    import ResenjeZahteva from './ResenjeZahteva.vue';
    import ZahtevService from '@/services/p1/ZahtevService';

    export default {
        name: 'ReferencirajuciDocs',
        components: {
            ResenjeZahteva
        },
        mounted() {
            this.samoResenje = this.$route.params.servis != 'P1';
            if (this.samoResenje) this.getResenje();
            else this.getP1Docs();
        },
        data() {
            return {
                docs: [],
                resenje: {
                    sifra: '',
                    prezimeSluzbenika: ''
                },
                samoResenje: false,
                resenjePrikaz: false,
                resenjePrikazP1: false,
                htmlContent: '',
                selectedZahtevId: ''
            }
        },
        methods: {
            prikaziResenje() {
                this.resenjePrikaz = true;
            },
            getResenje() {
                CommonsService.getReferencirajuciDokumenti(this.$route.params.id, this.$route.params.servis).then((response) =>{
                    xml2js.parseString(response.data, (_err, result) => {
                        this.extractResenjeData(result); 
                    });
                }).catch((err) => {
                    console.log(err);
                });
            },
            getP1Docs() {
                CommonsService.getReferencirajuciDokumenti(this.$route.params.id, this.$route.params.servis).then((response) => {
                    xml2js.parseString(response.data, (_err, result) => {
                        console.log(result);
                        for (let r of result.List.item) {
                            let obj = {tip: 'zahtev', broj: r.popunjavaZavod[0].brojPrijave[0]};
                            this.docs.push(obj);
                        }
                    })
                }).catch((err) => {
                    console.log(err);
                });
                ZahtevService.getResenje(this.$route.params.id).then((response) => {
                    xml2js.parseString(response.data, (_err, result) => {
                        this.extractResenjeData(result); 
                    });
                }).catch((err) => {
                    console.log(err);
                })
            },
            prikaziZahtev(broj) {
                this.resenjePrikazP1 = false;
                this.selectedZahtevId = broj;
                CommonsService.getOne(broj, "http://localhost:8002/patent").then((response) => {
                    this.htmlContent = response.data;
                }).catch((err) => {
                    console.log(err);
                });
            },
            extractResenjeData(result) {
                let obj;
                if (result.OdobrenZahtev) {
                    obj = result.OdobrenZahtev;
                    this.resenje.datumResenja = obj.datumResenja[0];
                    this.resenje.imeSluzbenika = obj.imeSluzbenika[0];
                    this.resenje.prezimeSluzbenika = obj.prezimeSluzbenika[0];
                    this.resenje.referenca = obj.referenca[0];
                    this.resenje.sifra = obj.sifra[0];
                    this.docs.push(this.resenje);
                } else if (result.OdbijenZahtev) {
                    obj = result.OdbijenZahtev;
                    this.resenje.obrazlozenje = obj.obrazlozenje[0];
                    this.resenje.datumResenja = obj.datumResenja[0];
                    this.resenje.imeSluzbenika = obj.imeSluzbenika[0];
                    this.resenje.prezimeSluzbenika = obj.prezimeSluzbenika[0];
                    this.resenje.referenca = obj.referenca[0];
                    this.docs.push(this.resenje);
                } 
            },
            prikaziResenjeP1(resenje) {
                this.resenje = resenje;
                this.resenjePrikazP1 = true;
                this.htmlContent = '';
            }
        }
    }
</script>

<style scoped>
    #div-html {
        border: 2px solid black;
        padding: 10px;
    }
    .centered {
        width: 95%;
        margin: 5px 10px;
    }
    h3 {
        margin: 20px 0 40px 20px;
    }
    .flex-container {
        display: flex;
        gap: 0 30px;
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
        flex: 4
    }
</style>