<template>
    <div>
        <h3>Dokumenti koji imaju referencu na Zahtev-{{ $route.params.id }}:</h3>
        <div v-if="docs.length == 0" style="margin-left: 30px">
            Nema rezultata
        </div>
        <div v-else class="flex-container">
            <div class="flex-container-v item">
                <a class="centered" v-if="samoResenje" :href="`#/referencirajuci/${$route.params.id}/${$route.params.servis}`" @click="prikaziResenje">
                    Resenje_Zahtev-{{ $route.params.id }}
                </a>
            </div>
            <div class="item-big">
                <ResenjeZahteva v-if="resenjePrikaz" :datum="resenje.datumResenja" :imeSluzbenika="resenje.imeSluzbenika" :prezimeSluzbenika="resenje.prezimeSluzbenika"
                    :referenca="resenje.referenca" :obrazlozenje="resenje.obrazlozenje" :sifra="resenje.sifra">
                </ResenjeZahteva>
            </div>
        </div>
    </div>
</template>

<script>
    import * as xml2js from 'xml2js';
    import CommonsService from '@/services/CommonsService';
    import ResenjeZahteva from './ResenjeZahteva.vue';

    export default {
        name: 'ReferencirajuciDocs',
        components: {
            ResenjeZahteva
        },
        mounted() {
            this.samoResenje = this.$route.params.servis != 'P1';
            CommonsService.getReferencirajuciDokumenti(this.$route.params.id, this.$route.params.servis).then((response) =>{
                xml2js.parseString(response.data, (_err, result) => {
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
                });
            }).catch((err) => {
                console.log(err);
            })
        },
        data() {
            return {
                docs: [],
                resenje: {
                    sifra: '',
                    prezimeSluzbenika: ''
                },
                samoResenje: false,
                resenjePrikaz: false
            }
        },
        methods: {
            prikaziResenje() {
                this.resenjePrikaz = true;
            }
        }
    }
</script>

<style scoped>
    .centered {
        margin: 5px auto;
    }
    h3 {
        margin: 20px 0 40px 20px;
    }
    .flex-container {
        display: flex;
        gap: 0 70px;
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
</style>