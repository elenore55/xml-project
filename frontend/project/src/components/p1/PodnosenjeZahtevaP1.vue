<template> 
    <div>
        <GradjaninNav></GradjaninNav>
        <div class="centered">
            <h2 class="text-centered">ZAHTEV ZA PRIZNANJE PATENTA</h2>
            <PodnosilacPrijave ref="podnosilac" @updatePodnosilac="updatePodnosilac($event)"></PodnosilacPrijave>
            <Pronalazac ref="pronalazac" @updatePronalazac="updatePronalazac($event)"></Pronalazac>
            <PunomocnikUnos ref="punomocnik" @updatePunomocnik="updatePunomocnik($event)"></PunomocnikUnos>
            <div>
                <h2>Podaci o dostavljanju</h2>
                <h3 class="adresa">Adresa dostavljanja</h3>
                <p>(Ovo polje se popunjava ako podnosilac prijave, zajednički predstavnik ili punomoćnik želi da se dostavljanje podnesaka 
                    vrši na drugoj adresi od njegove navedene adrese)</p>
                <AdresaUnos optional="true" ref="adresaDostavljanja" @updateAdresa="updateAdresa($event)"></AdresaUnos>
                <h3>Način dostavljanja</h3>
                <div>
                    <input type="radio" name="nacin" @change="elektronskiSelected" checked/>
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
                        <input type="number" :class="valid.brojPrvobitne?'':'red-border'" v-model="brojPrvobitnePrijave" @input="validateBrojPrvobitnePrijave" />
                    </div>
                    <div class="flex-container-v item">
                        <label>Datum podnošenja prvobitne prijave / osnovne prijave</label>
                        <input type="date" :class="valid.datumPrvobitne?'':'red-border'" v-model="datumPrvobitnePrijave" @input="validateDatumPrvobitnePrijave" />
                    </div>
                </div>
            </div>
            <RanijePrijave ref="ranijePrijave" @updateXonomyData="updateXonomyData($event)"></RanijePrijave>
            <button type="button" @click="submit">Podnesi zahtev</button>
        </div>
    </div>
</template>

<script>
    import PodnosilacPrijave from './PodnosilacPrijave.vue';
    import Pronalazac from './Pronalazac.vue';
    import PunomocnikUnos from './PunomocnikUnos.vue';
    import AdresaUnos from './AdresaUnos.vue';
    import RanijePrijave from './RanijePrijave.vue';
    import * as xml2js from 'xml2js';
    import * as js2xml from 'js2xmlparser';
    import ZahtevService from '@/services/p1/ZahtevService';
    import GradjaninNav from '../user/GradjaninNav.vue';

    export default {
        name: 'PodnosenjeZahtevaP1',
        components: {
            PodnosilacPrijave,
            Pronalazac,
            PunomocnikUnos,
            AdresaUnos,
            RanijePrijave,
            GradjaninNav
        },
        data() {
            return {
                podnosilac: {},
                pronalazac: {},
                punomocnik: {},
                adresaDostavljanja: {},
                nacinDostavljanja: 'ELEKTRONSKI_DOKUMENT',
                vrstaPrijave: 'IZDVOJENA',
                brojPrvobitnePrijave: '',
                datumPrvobitnePrijave: '',
                nazivSrpski: '',
                nazivEngleski: '',
                xonomyData: '',
                ranijePrijave: [],
                valid: {
                    brojPrvobitne: true,
                    datumPrvobitne: true
                }
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
                if (this.isValidInput()) {
                    const xmlString = js2xml.parse('zahtev', {
                        nazivSrpski: this.nazivSrpski,
                        nazivEngleski: this.nazivEngleski,
                        adresaDostavljanja: this.adresaDostavljanja,
                        vrstaPrijave: this.vrstaPrijave,
                        datumPrvobitnePrijave: this.datumPrvobitnePrijave,
                        brojPrvobitnePrijave: this.brojPrvobitnePrijave,
                        nacinDostavljanja: this.nacinDostavljanja,
                        podnosilac: this.podnosilac,
                        pronalazac: this.pronalazac,
                        punomocnik: this.punomocnik,
                        ranijePrijaveList: {ranijePrijave: this.ranijePrijave}
                    });
                    console.log(xmlString);
                    ZahtevService.save(xmlString).then((_response) => {
                        alert('Zahtev je uspešno podnesen!');
                        this.clear();
                    }).catch((err) => {
                        console.log(err);
                        alert('Greška!');
                    });
                } else {
                    alert('Zahtev nije ispravno popunjen!');
                }
            },
            parseXonomy() {
                xml2js.parseString(this.xonomyData, (_err, result) => {
                    if (!result) return false;
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
                    this.ranijePrijave = [];
                    let prijave = result.Podaci_o_patentu.Priznanje_prvenstva_iz_ranijih_prijava[0].Prijava;
                    for (let p of prijave) {
                        if (p) {
                            this.ranijePrijave.push({
                                brojPrijave: +p.Broj_prijave[0],
                                datumPodnosenja: p.Datum_podnosenja[0],
                                oznaka: p.Dvoslovna_oznaka_organizacije[0]
                            });
                        }
                    }
                });
            },
            clear() {
                this.$refs.podnosilac.clear();
                this.$refs.punomocnik.clear();
                this.$refs.pronalazac.clear();
                this.$refs.ranijePrijave.clear();
                this.$refs.adresaDostavljanja.clear();
                this.nacinDostavljanja = 'ELEKTRONSKI_DOKUMENT';
                this.vrstaPrijave = 'IZDVOJENA';
                this.brojPrvobitnePrijave = '';
                this.datumPrvobitnePrijave = '';
                this.nazivSrpski = '';
                this.nazivEngleski = '';
                this.xonomyData = '';
                this.ranijePrijave = [];
            },
            validateBrojPrvobitnePrijave() {
                this.valid.brojPrvobitne = (this.vrstaPrijave == 'IZDVOJENA' || this.brojPrvobitnePrijave != '');
            },
            validateDatumPrvobitnePrijave() {
                this.valid.datumPrvobitne = (this.vrstaPrijave == 'IZDVOJENA' || this.datumPrvobitnePrijave != '');
            },
            isValidInput() {
                this.validateBrojPrvobitnePrijave();
                this.validateDatumPrvobitnePrijave();
                let podnosilac = this.$refs.podnosilac.isValidInput();
                let punomocnik = this.$refs.punomocnik.isValidInput();
                let pronalazac = this.$refs.pronalazac.isValidInput();
                let naziv = this.nazivSrpski != '' && this.nazivEngleski != '';
                return podnosilac && punomocnik && pronalazac && naziv && this.valid.brojPrvobitne && this.valid.datumPrvobitne;
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