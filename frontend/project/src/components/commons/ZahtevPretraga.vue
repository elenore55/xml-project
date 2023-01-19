<template>
    <div>
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
                <div v-for="r in rezultati" :key="r.id" class="link">
                        <a href="#" @click="prikaziZahtev(r.popunjavaZavod[0].brojPrijave[0])" >Zahtev-{{ r.popunjavaZavod[0].brojPrijave[0] }}</a>
                </div>
            </div>
            <div v-html="htmlContent" id="div-html" class="item-big"></div>
        </div>
    </div>
</template>

<script>
    import * as xml2js from 'xml2js';
    import CommonsService from '@/services/CommonsService';

    export default {
        name: 'ZahtevPretraga',
        data() {
            return {
                tekst: '',
                upit: '',
                matchCase: false,
                rezultati: [],
                htmlContent: ''
            }
        },
        methods: {
            osnovnaPretraga() {
                this.rezultati = [];
                let that = this;
                CommonsService.osnovnaPretraga(this.tekst, this.matchCase)
                .then((response) => {
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
            prikaziZahtev(broj) {
                CommonsService.getOne(broj).then((response) => {
                    console.log(response.data);
                    this.htmlContent = response.data;
                }).catch((err) => {
                    console.log(err);
                })
            }
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
    .link {
        margin: 20px 0;
    }
</style>