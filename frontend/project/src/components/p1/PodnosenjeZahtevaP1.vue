<template> 
    <div class="centered">
        <h2 class="text-centered">ZAHTEV ZA PRIZNANJE PATENTA</h2>
        <PodnosilacPrijave @updatePodnosilac="updatePodnosilac($event)"></PodnosilacPrijave>
        <Pronalazac @updatePronalazac="updatePronalazac($event)"></Pronalazac>
        <PunomocnikUnos @updatePunomocnik="updatePunomocnik($event)"></PunomocnikUnos>
        <div>
            <h2>Podaci o dostavljanju</h2>
            <h3 class="adresa">Adresa dostavljanja</h3>
            <p>(Ovo polje se popunjava ako podnosilac prijave, zajednički predstavnik ili punomoćnik želi da se dostavljanje podnesaka 
                vrši na drugoj adresi od njegove navedene adrese)</p>
            <AdresaUnos updateAdresa="updateAdresa($event)"></AdresaUnos>
            <h3>Način dostavljanja</h3>
            <div>
                <input type="radio" name="nacin" @change="elektronskiSelected"/>
                <label>Elektronskim putem u formi elektronskog dokumenta</label>
                <input type="radio" name="nacin" class="second-radio" @change="papirSelected"/>
                <label>U papirnoj formi</label>
            </div>
        </div>
        <div>
            <h2>Podaci o prijavi</h2>
            <input type="radio" name="vrsta" @change="izdvojenaSelected" checked/>
            <label>IZDVOJENA PRIJAVA</label>
            <input type="radio" name="vrsta" class="second-radio" @change="dopunskaSelected"/>
            <label>DOPUNSKA PRIJAVA</label>
            <div v-if="vrstaPrijave == 'DOPUNSKA'" class="flex-container">
                <div class="flex-container-v item">
                    <label>Broj prvobitne prijave / osnovne prijave</label>
                    <input type="number" v-model="brojPrvobitnePrijave" />
                </div>
                <div class="flex-container-v item">
                    <label>Datum podnošenja prvobitne prijave / osnovne prijave</label>
                    <input type="date" v-model="datumPrvobitnePrijave" />
                </div>
            </div>
        </div>
        <RanijePrijave @updateXonomyData="updateXonomyData($event)"></RanijePrijave>
        <button type="button" @click="submit">Podnesi zahtev</button>
    </div>
</template>

<script>
    import PodnosilacPrijave from './PodnosilacPrijave.vue';
    import Pronalazac from './Pronalazac.vue';
    import PunomocnikUnos from './PunomocnikUnos.vue';
    import AdresaUnos from './AdresaUnos.vue';
    import RanijePrijave from './RanijePrijave.vue';
    import * as xml2js from 'xml2js';

    export default {
        name: 'PodnosenjeZahtevaP1',
        components: {
            PodnosilacPrijave,
            Pronalazac,
            PunomocnikUnos,
            AdresaUnos,
            RanijePrijave
        },
        data() {
            return {
                podnosilac: {},
                pronalazac: {},
                punomocnik: {},
                adresaDostavljanja: {},
                nacinDostavljanja: '',
                vrstaPrijave: 'IZDVOJENA',
                brojPrvobitnePrijave: '',
                datumPrvobitnePrijave: '',
                nazivSrpski: '',
                nazivEngleski: '',
                xonomyData: '',
                ranijePrijave: []
            }
        },
        methods: {
            updatePodnosilac(osoba) {
                this.podnosilac = osoba;
            },
            updatePronalazac(osoba) {
                this.pronalazac = osoba;
            },
            updatePunomocnik(osoba) {
                this.punomocnik = osoba;
            },
            updateAdresa(adresa) {
                this.adresaDostavljanja = adresa;
            },
            elektronskiSelected() {
                this.nacinDostavljanja = 'ELEKTRONSKI_DOKUMENT';
            },
            papirSelected() {
                this.nacinDostavljanja = 'PAPIRNI_DOKUMENT';
            },
            izdvojenaSelected() {
                this.vrstaPrijave = 'IZDVOJENA';
            },
            dopunskaSelected() {
                this.vrstaPrijave = 'DOPUNSKA';
            },
            updateXonomyData(data) {
                this.xonomyData = data.replaceAll(" xml:space='preserve'", "").replaceAll("<b>", "").replaceAll("</b>", "").replaceAll("<i>", "").replaceAll("</i>", "");
                
            },
            submit() {
                this.parseXonomy();
            },
            parseXonomy() {
                xml2js.parseString(this.xonomyData, (_err, result) => {
                    let naziv1 = result.Podaci_o_patentu.Podaci_o_nazivu[0].Naziv[0]['_'];
                    let jezik1 = result.Podaci_o_patentu.Podaci_o_nazivu[0].Naziv[0]['$'].jezik;
                    let naziv2 = result.Podaci_o_patentu.Podaci_o_nazivu[0].Naziv[1]['_'];
                    if (jezik1 === 'srpski') {
                        this.nazivSrpski = naziv1;
                        this.nazivEngleski = naziv2;
                    } else {
                        this.nazivSrpski = naziv2;
                        this.nazivEngleski = naziv1;
                    }
                    let prijave = result.Podaci_o_patentu.Priznanje_prvenstva_iz_ranijih_prijava[0].Prijava;
                    for (let p of prijave) {
                        this.ranijePrijave.push({
                            brojPrijave: +p.Broj_prijave[0],
                            datumPodnosenja: p.Datum_podnosenja[0],
                            oznaka: p.Dvoslovna_oznaka_organizacije[0]
                        });
                    }
                });
            }
        }
    }
</script>

<style scoped>
    button {
        font-size: 18px;
        margin-bottom: 30px;
    }
    .text-centered {
        text-align: center;
    }
    .centered {
        margin: auto;
        width: 75%;
    }
    h2 {
        margin-top: 30px;
    }
    p {
        font-size: 12px;
        margin-bottom: 25px;
    }
    h3.adresa {
        margin-bottom: 0px;
    }
    .second-radio {
        margin-left: 70px;
    }

    .flex-container {
        display: flex;
        gap: 0 50px;
        margin-top: 30px;
        align-items: flex-end;
    }
    .flex-container-v {
        display: flex;
        flex-direction: column;
        gap: 10px;
    }
    .item {
        flex: 1
    }
</style>