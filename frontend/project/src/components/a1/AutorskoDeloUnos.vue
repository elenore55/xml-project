<template>
    <div>
        <h2>Podaci o autorskom delu</h2>
            <div>
                <PodaciONaslovu @updateNaslov="updateNaslov($event)"></PodaciONaslovu>

                <div class="flex-container">
                    <div class="flex-container-v item">
                        <label>Vrsta autorskog dela</label> 
                        <input type="text" list="vrsta" v-model="vrsta" @change="updateAutorskoDelo" />
                        <datalist id="vrsta">
                            <option>Književno delo</option>
                            <option>Muzičko delo</option>
                            <option>Likovno delo</option>
                            <option>Računarski program</option>
                        </datalist>
                    </div>
                    <div id="forma-zapisa" class="flex-container-v item">
                        <label for="forma-zapisa-input">Forma zapisa</label>
                        <input type="text" list="forma" v-model="formaZapisa" @change="updateAutorskoDelo" />
                        <datalist id="forma">
                            <option>Štampani tekst</option>
                            <option>Optički disk</option>
                        </datalist>
                    </div>
                    <div id="nacin-koriscenja" class="flex-container-v item">
                        <label for="nacin-koriscenja-input">Način korišćenja</label>
                        <input id="nacin-koriscenja-input" type="text" v-model="nacinKoriscenja" @input="updateAutorskoDelo"/>
                    </div>
                </div>
            </div>

            <div class="flex-container">
                <div class="flex-container item">
                    <input type="checkbox" v-model="radniOdnos" @input="checkRadniOdnos($event)"/>
                    <span>Autorsko delo je stvoreno u radnom odnosu</span>
                </div>
                <div class="flex-container item">
                    <input type="checkbox" v-model="deloPrerade" @input="checkDeloPrerade($event)"/>
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
                console.log(this.deloPrerade);
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
            },
            checkDeloPrerade(event) {
                this.deloPrerade = event.target.checked;
                this.updateAutorskoDelo();
            },
            checkRadniOdnos(event) {
                this.radniOdnos = event.target.checked;
                this.updateAutorskoDelo();
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