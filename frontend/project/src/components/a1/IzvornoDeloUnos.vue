<template>
    <div>
        <h3>Podaci o izvornom delu</h3>
        <PodaciONaslovu ref="naslov" @updateNaslov="updateNaslov($event)"></PodaciONaslovu>
        <h3 v-if="autorList.length > 0">Autori:</h3>
        <div v-for="(autor, i) in autorList" :key="autor.id" class="indented flex-container">
            <button type="button" @click="autorList.splice(i, 1)" class="btn-delete">-</button>
            <AutorPrikaz :ime="autor.ime" :prezime="autor.prezime" :godinaSmrti="autor.godinaSmrti" :drzavljanstvo="autor.drzavljanstvo" 
                :adresa="autor.adresa" class="item"></AutorPrikaz>
        </div>
        
        <AutorUnos @updateAutor="updateAutor($event)" @addAutor="addAutor" title="Autor izvornog dela"></AutorUnos>
    </div>
</template>

<script>
    import PodaciONaslovu from '@/components/a1/PodaciONaslovu.vue';
    import AutorUnos from '@/components/a1/AutorUnos.vue';
    import AutorPrikaz from '@/components/a1/AutorPrikaz.vue';

    export default {
        name: 'IzvornoDeloUnos',
        components: {
            PodaciONaslovu,
            AutorPrikaz,
            AutorUnos
        },
        data() {
            return {
                naslov: '',
                altNaslov: '',
                autor: {},
                autorList: []
            }
        },
        methods: {
            updateNaslov(naslov) {
                this.naslov = naslov.naslov;
                this.altNaslov = naslov.altNaslov;
                this.updateIzvornoDelo();
            },
            addAutor() {
                this.autorList.push(this.autor);
                this.updateIzvornoDelo();
            },
            updateAutor(autor) {
                this.autor = autor;
            },
            updateIzvornoDelo() {
                this.$emit('updateIzvornoDelo', {
                    naslov: this.naslov,
                    altNaslov: this.altNaslov,
                    autori: {autori: this.autorList} 
                });
            },
            clear() {
                this.$refs.naslov.clear();
                this.autor = {};
                this.autorList = [];
                this.updateIzvornoDelo();
            }
        }
    }
</script>

<style scoped>
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