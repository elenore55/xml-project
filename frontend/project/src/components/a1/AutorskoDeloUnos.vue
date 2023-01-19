<template>
    <div>
        <h2>Podaci o autorskom delu</h2>
            <div>
                <PodaciONaslovu @updateNaslov="updateNaslov($event)"></PodaciONaslovu>

                <div class="flex-container">
                    <div id="vrsta" class="flex-container-v item">
                        <label for="vrsta-input" class="item">Vrsta autorskog dela</label>
                        <select v-model="vrstaChoice" @change="updateAutorskoDelo">
                            <option value="1">Književno delo</option>
                            <option value="2">Muzičko delo</option>
                            <option value="3">Likovno delo</option>
                            <option value="4">Računarski program</option>
                            <option value="5">Ostalo</option>
                        </select>
                        <input v-if="vrstaChoice == 5" id="vrsta-input" type="text" class="item" v-model="vrsta" @input="updateAutorskoDelo"/>
                    </div>
                    <div id="forma-zapisa" class="flex-container-v item">
                        <label for="forma-zapisa-input">Forma zapisa</label>
                        <select v-model="formaChoice" @change="updateAutorskoDelo">
                            <option value="1">Štampani tekst</option>
                            <option value="2">Optički disk</option>
                            <option value="3">Ostalo</option>
                        </select>
                        <input v-if="formaChoice == 3" id="forma-zapisa-input" type="text" v-model="formaZapisa" @input="updateAutorskoDelo"/>
                    </div>
                    <div id="nacin-koriscenja" class="flex-container-v item">
                        <label for="nacin-koriscenja-input">Način korišćenja</label>
                        <input id="nacin-koriscenja-input" type="text" v-model="nacinKoriscenja" @input="updateAutorskoDelo"/>
                    </div>
                </div>
            </div>

            <div class="flex-container">
                <div class="flex-container item">
                    <input type="checkbox" v-model="radniOdnos" @input="updateAutorskoDelo"/>
                    <span>Autorsko delo je stvoreno u radnom odnosu</span>
                </div>
                <div class="flex-container item">
                    <input type="checkbox" v-model="deloPrerade" @input="updateAutorskoDelo"/>
                    <span>U pitanju je delo prerade</span>
                </div>
            </div>

            <div v-if="deloPrerade">
                <IzvornoDeloUnos @updateIzvornoDelo="updateIzvornoDelo($event)"></IzvornoDeloUnos>
            </div>
    </div>
</template>

<script>
    import PodaciONaslovu from '@/components/a1/PodaciONaslovu.vue';
    import IzvornoDeloUnos from '@/components/a1/IzvornoDeloUnos.vue';

    export default {
        name: 'AutorskoDeloUnos',
        components: {
            PodaciONaslovu,
            IzvornoDeloUnos
        },
        data() {
            return {
                naslov: '',
                altNaslov: '',
                vrsta: '',
                vrstaChoice: 1,
                formaZapisa: '',
                formaChoice: 1,
                nacinKoriscenja: '',
                radniOdnos: false,
                deloPrerade: false,
                ostaloVrstaIzabrano: false,
                izvornoDelo: {}
            }
        },
        methods: {
            updateNaslov(naslov) {
                this.naslov = naslov.naslov;
                this.altNaslov = naslov.altNaslov;
                this.updateAutorskoDelo();
            },
            updateIzvornoDelo(delo) {
                this.izvornoDelo = delo;
                this.updateAutorskoDelo();
            },
            updateAutorskoDelo() {
                this.$emit('updateAutorskoDelo', {
                    naslov: this.naslov,
                    altNaslov: this.altNaslov,
                    vrsta: this.vrsta,
                    formaZapisa: this.formaZapisa,
                    nacinKoriscenja: this.nacinKoriscenja,
                    radniOdnos: this.radniOdnos,
                    deloPrerade: this.deloPrerade,
                    izvornoDelo: this.izvornoDelo
                });
            }
        }
    }
</script>

<style scoped>
    h2 {
        margin-top: 50px;
    }
    h3 {
        margin-top: 30px;
    }
    .centered-text {
        text-align: center;
    }
    .centered {
        margin: auto;
        width: 60%;
    }
    .flex-container {
        display: flex;
        gap: 0 20px;
        margin-bottom: 20px;
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
    span {
        margin-left: -10px;
    }
</style>