<template>
    <div class="centered">
        <PodnosilacPrijave ref="podnosilac" @updatePodnosilac="updatePodnosilac($event)" title="Podnosioci prijave"></PodnosilacPrijave>
        <button type="button" @click="dodajPodnosioca">Dodaj podnosioca</button>
        <div>
            <p>Dodati podnosioci:</p>
            <ul>
                <li v-for="p in podnosioci" :key="p.id">
                    <div class="flex-container">
                    <p v-if="p.ime">{{ p.ime }} {{ p.prezime }}, {{ p.adresa.ulica }} {{ p.adresa.broj }}, {{ p.adresa.postanskiBroj }} {{ p.adresa.mesto }}</p>
                    <p v-else>{{ p.poslovnoIme }}, {{ p.adresa.ulica }} {{ p.adresa.broj }}, {{ p.adresa.postanskiBroj }} {{ p.adresa.mesto }}</p>
                    <button type="button" @click="ukloniPodnosioca" class="btn-del">X</button>
                    </div>
                </li>
            </ul>
        </div>
        <PodnosilacPrijave @updatePodnosilac="updatePunomocnik($event)" title="Punomoćnik"></PodnosilacPrijave>
        <PodnosilacPrijave @updatePodnosilac="updatePredstavnik($event)" v-if="podnosioci.length > 1" title="Zajednički predstavnik"></PodnosilacPrijave>
        <PodaciOZigu @updateZig="updateZig($event)"></PodaciOZigu>
        <PlaceneTakse @updatePlaceneTakse="updatePlaceneTakse($event)"></PlaceneTakse>
        <PriloziUnos @updatePrilozi="updatePrilozi($event)"></PriloziUnos>
        <button type="button" @click="submit">Podnesi zahtev</button>
    </div>
</template>

<script>
    import ZahtevService from '@/services/z1/ZahtevService';
    import PodnosilacPrijave from './PodnosilacPrijave.vue';
    import PodaciOZigu from './PodaciOZigu.vue';
    import PlaceneTakse from './PlaceneTakse.vue';
    import PriloziUnos from './PriloziUnos.vue';
    import * as js2xml from 'js2xmlparser';

    export default {
        name: 'PodnosenjeZahtevaZ1',
        components: {
            PodnosilacPrijave,
            PodaciOZigu,
            PlaceneTakse,
            PriloziUnos
        },
        data() {
            return {
                podnosioci: [],
                punomocnik: {},
                zajednickiPredstavnik: {},
                zig: {},
                placeneTakse: {},
                prilozi: {},
                podnosilac: {}
            }
        },
        methods: {
            updatePrilozi(prilozi) {
                this.prilozi = prilozi;
            },
            updateZig(zig) {
                this.zig = zig;
            },
            updatePlaceneTakse(takse) {
                this.placeneTakse = takse;
            },
            updatePodnosilac(osoba) {
                this.podnosilac = osoba;
            },
            updatePunomocnik(osoba) {
                this.punomocnik = osoba;
            },
            updatePredstavnik(osoba) {
                this.predstavnik = osoba;
            },
            dodajPodnosioca() {
                this.podnosioci.push(this.podnosilac);
                this.$refs.podnosilac.clear();
            },
            ukloniPodnosioca(i) {
                this.podnosioci.splice(i, 1);
            },
            submit() {
                let formData = new FormData();
                formData.append('izgledZnaka', this.zig.izgledZnaka);
                formData.append('primerakZnaka', this.prilozi.primerakZnaka);
                formData.append('spisakRobeIUsluga', this.prilozi.spisakRobeIUsluga);
                formData.append('punomocje', this.prilozi.punomocje);
                formData.append('opstiAkt', this.prilozi.opstiAkt);
                formData.append('dokazOPravuPrvenstva', this.prilozi.dokazOPravuPrvenstva);
                formData.append('dokazOUplatiTakse', this.prilozi.dokazOUplatiTakse);

                const xmlString = js2xml.parse('zahtev', {
                    podnosiociList: {podnosioci:this.podnosioci},
                    punomocnik: this.punomocnik,
                    zajednickiPredstavnik: this.zajednickiPredstavnik,
                    placeneTakse: this.placeneTakse,
                    zig: this.zig,
                    punomocjeRanijePrilozeno: this.prilozi.generalnoPunomocjeRanijePrilozeno,
                    punomocjeNaknadnoDostavljeno: this.prilozi.punomocjeNaknadnoDostavljeno
                });
                console.log(xmlString);
                formData.append('dto', xmlString);

                ZahtevService.save(formData).then((response) => {
                    console.log(response.data);
                }).catch((err) => {
                    console.log(err);
                });
            }
        }
    }
</script>

<style scoped>
    h2 {
        margin-top: 40px;
    }
    h3 {
        margin-top: 30px;
    }
    .centered-text {
        text-align: center;
    }
    .centered {
        margin: auto;
        width: 75%;
    }
    button {
        font-size: 18px;
        margin: 20px 0;
    }
    p {
        font-size: 18px;
    }
    .flex-container {
        display: flex;
        gap: 0 20px;
    }
    .btn-del {
        margin-top: 15px;
        height: 25px;
    }
</style>