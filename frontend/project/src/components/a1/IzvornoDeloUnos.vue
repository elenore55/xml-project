<template>
    <div>
        <h3>Podaci o izvornom delu</h3>
        <PodaciONaslovu @updateNaslov="updateNaslov($event)"></PodaciONaslovu>
        <h3 v-if="autori.length > 0">Autori:</h3>
        <div v-for="(autor, i) in autori" :key="autor.id" class="indented flex-container">
            <button type="button" @click="autori.splice(i, 1)" class="btn-delete">-</button>
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
                autori: []
            }
        },
        methods: {
            updateNaslov(naslov) {
                this.naslov = naslov.naslov;
                this.altNaslov = naslov.altNaslov;
                this.updateIzvornoDelo();
            },
            addAutor() {
                this.autori.push(this.autor);
                this.updateIzvornoDelo();
            },
            updateAutor(autor) {
                this.autor = autor;
            },
            updateIzvornoDelo() {
                this.$emit('updateIzvornoDelo', {
                    naslov: this.naslov,
                    altNaslov: this.altNaslov,
                    autori: this.autori
                });
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