<template>
    <div>
        <GradjaninNav></GradjaninNav>
        <div>
            <h2 class="centered-text">ZAHTEV ZA UNOŠENJE U EVIDENCIJU I DEPONOVANJE AUTORSKIH DELA</h2>
            <div class="centered">
                <PodnosilacPrijaveUnos ref="podnosilac" @updatePodnosilac="updatePodnosilac($event)"></PodnosilacPrijaveUnos>

                <AutorskoDeloUnos ref="autorskoDelo" @updateAutorskoDelo="updateAutorskoDelo($event)"></AutorskoDeloUnos>

                <h2>Autori dela</h2>
                <div v-for="(autor, i) in autorList" :key="autor.id" class="indented flex-container">
                    <button type="button" @click="autorList.splice(i, 1)" class="btn-delete">-</button>
                    <AutorPrikaz :ime="autor.ime" :prezime="autor.prezime" :godinaSmrti="autor.godinaSmrti" :drzavljanstvo="autor.drzavljanstvo" 
                        :adresa="autor.adresa" class="item"></AutorPrikaz>
                </div>
                
                <AutorUnos @updateAutor="updateAutor($event)" @addAutor="addAutor"></AutorUnos>

                <div>
                    <h2>Prilozi uz prijavu</h2>
                    <div class="flex-container">
                        <div class="flex-container item">
                            <input type="radio" name="prilog" checked @change="opisDelaSelected" />
                            <span>Opis autorskog dela</span>
                        </div>
                        <div class="flex-container item">
                            <input type="radio" name="prilog" @change="primerSelected"/>
                            <span>Primer autorskog dela</span>
                        </div>
                    </div>
                    <input v-if="opisDelaFlag" type="text" :class="isValidOpis?'':'red-border'" v-model="opisDela" @input="validateOpis" />
                    <input v-else type="file" ref="file" id="file" @change="handleFileUpload($event)" />
                </div>
                <button type="button" id="btn-submit" @click="submit">Podnesi zahtev</button>
            </div>
        </div>
    </div>
</template>

<script>
    import ZahtevService from '@/services/a1/ZahtevService';
    import PodnosilacPrijaveUnos from '@/components/a1/PodnosilacPrijaveUnos.vue';
    import AutorskoDeloUnos from '@/components/a1/AutorskoDeloUnos.vue';
    import AutorUnos from '@/components/a1/AutorUnos.vue';
    import AutorPrikaz from '@/components/a1/AutorPrikaz.vue';
    import GradjaninNav from '../user/GradjaninNav.vue';
    import * as js2xml from 'js2xmlparser';

    export default {
        name: 'PodnosenjeZahteva',
        components: {
            AutorskoDeloUnos,
            PodnosilacPrijaveUnos,
            AutorUnos,
            AutorPrikaz,
            GradjaninNav
        },
        data() {
            return {
                podnosilacPrijave: {},
                autorskoDelo: {},
                autorList: [],
                autor: {},
                opisDelaFlag: true,
                opisDela: '',
                prilog: '',
                isValidOpis: true
            }
        },
        methods: {
            updatePodnosilac(podnosilac) {
                this.podnosilacPrijave = podnosilac;
            },
            updateNaslov(naslov) {
                this.naslov = naslov.naslov;
                this.altNaslov = naslov.altNaslov;
            },
            updateAutorskoDelo(delo) {
                this.autorskoDelo = delo;
            },
            addAutor() {
                this.autorList.push(this.autor);
            },
            updateAutor(autor) {
                this.autor = autor;
            },
            opisDelaSelected() {
                this.opisDelaFlag = true;
            },
            primerSelected() {
                this.opisDelaFlag = false;
            },
            handleFileUpload(event) {
                this.prilog = event.target.files[0];
            },
            submit() {
                if (this.isValidInput()) {
                    if (this.opisDelaFlag || this.prilog == '') {
                        this.submitWithoutPrilog();
                    } else {
                        this.submitWithPrilog();
                    }
                } else {
                    alert('Zahtev nije ispravno popunjen!');
                }
            },
            clear() {
                this.$refs.podnosilac.clear();
                this.$refs.autorskoDelo.clear();
                this.autor = {};
                this.autorList = [];
                this.opisDela = '';
                this.primerDela = '';
            },
            isValidInput() {
                let podnosilacValid = this.$refs.podnosilac.isValidInput(); 
                let deloValid = this.$refs.autorskoDelo.isValidInput();
                return podnosilacValid && deloValid;
            },
            validateOpis() {
                this.isValidOpis = (this.opisDela != '');
            },
            submitWithPrilog() {
                let formData = new FormData();
                formData.append('prilog', this.prilog);
                const xmlString = js2xml.parse("zahtev", {
                    podnosilac: this.podnosilacPrijave,
                    autorskoDelo: this.autorskoDelo,
                    autori: {
                        autori: this.autorList
                    }
                });
                formData.append('dto', xmlString);
                ZahtevService.saveWithPrilog(formData).then(() => {
                    alert('Zahtev je uspešno podnet!');
                    this.clear();
                }).catch((err) => {
                    console.log(err);
                    alert('Greška!');
                });
            },
            submitWithoutPrilog() {
                const xmlString = js2xml.parse("zahtev", {
                    podnosilac: this.podnosilacPrijave,
                    autorskoDelo: this.autorskoDelo,
                    autori: {
                        autori: this.autorList
                    },
                    opisDela: this.opisDela
                });
                ZahtevService.save(xmlString).then(() => {
                    alert('Zahtev je uspešno podnet!');
                    this.clear();
                }).catch((err) => {
                    console.log(err);
                    alert('Greška!');
                });
            }
        }
    }
</script>

<style scoped>
    #btn-submit {
        font-size: 18px;
        margin: 30px 0;
    }
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
        width: 70%;
    }
    .flex-container {
        display: flex;
        gap: 0 20px;
        margin-bottom: 10px;
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
    input, label {
        display:block;
    }
    #street, #city {
        width: 50%;
    }
    .indented {
        margin: 15px;
    }
    .btn-delete {
        margin-bottom: 15px;
        font-size: 24px;
        padding: 0 10px;
        font-weight: bold;
        border-radius: 50%;

    }
</style>