<template>
    <div>
        <h2>Podaci o autorskom delu</h2>
            <div>
                <PodaciONaslovu ref="naslov" @updateNaslov="updateNaslov($event)"></PodaciONaslovu>

                <div class="flex-container">
                    <div class="flex-container-v item">
                        <label>Vrsta autorskog dela</label> 
                        <input type="text" list="vrsta" :class="validation.vrsta?'':'red-border'" v-model="vrsta" @change="validateVrsta" />
                        <datalist id="vrsta">
                            <option>Književno delo</option>
                            <option>Muzičko delo</option>
                            <option>Likovno delo</option>
                            <option>Računarski program</option>
                        </datalist>
                    </div>
                    <div id="forma-zapisa" class="flex-container-v item">
                        <label for="forma-zapisa-input">Forma zapisa</label>
                        <input type="text" list="forma" :class="validation.formaZapisa?'':'red-border'" v-model="formaZapisa" @change="validateFormaZapisa" />
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
                <IzvornoDeloUnos ref="izvornoDelo" @updateIzvornoDelo="updateIzvornoDelo($event)"></IzvornoDeloUnos>
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
                formaZapisa: '',
                nacinKoriscenja: '',
                radniOdnos: false,
                deloPrerade: false,
                izvornoDelo: {},
                validation: {
                    vrsta: true,
                    formaZapisa: true
                }
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
            },
            clear() {
                this.$refs.naslov.clear();
                if (this.deloPrerade) {
                    this.$refs.izvornoDelo.clear();
                }
                this.vrsta = '';
                this.formaZapisa = '';
                this.nacinKoriscenja = '';
                this.radniOdnos = false;
                this.deloPrerade = false;
            },
            validateVrsta() {
                this.validation.vrsta = (this.vrsta != '');
                this.updateAutorskoDelo();
            },
            validateFormaZapisa() {
                this.validation.formaZapisa = (this.formaZapisa != '');
                this.updateAutorskoDelo();
            },
            isValidInput() {
                let izvornoDeloValid = true;
                if (this.deloPrerade) izvornoDeloValid = this.$refs.izvornoDelo.isValidInput();
                let naslovValid = this.$refs.naslov.isValidInput();
                this.validateVrsta();
                this.validateFormaZapisa();
                return izvornoDeloValid && this.validation.vrsta && this.validation.formaZapisa && naslovValid;
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