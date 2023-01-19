<template>
    <div>
        <h2 class="centered-text">ZAHTEV ZA UNOŠENJE U EVIDENCIJU I DEPONOVANJE AUTORSKIH DELA</h2>
        <div class="centered">
            <PodnosilacPrijaveUnos @updatePodnosilac="updatePodnosilac($event)"></PodnosilacPrijaveUnos>

            <AutorskoDeloUnos @updateAutorskoDelo="updateAutorskoDelo($event)"></AutorskoDeloUnos>

            <h2>Autori dela</h2>
            <div v-for="(autor, i) in autori" :key="autor.id" class="indented flex-container">
                <button type="button" @click="autori.splice(i, 1)" class="btn-delete">-</button>
                <AutorPrikaz :ime="autor.ime" :prezime="autor.prezime" :godinaSmrti="autor.godinaSmrti" :drzavljanstvo="autor.drzavljanstvo" 
                    :adresa="autor.adresa" class="item"></AutorPrikaz>
            </div>
            
            <AutorUnos @updateAutor="updateAutor($event)" @addAutor="addAutor"></AutorUnos>
        </div>
    </div>
</template>

<script>
    import PodnosilacPrijaveUnos from '@/components/a1/PodnosilacPrijaveUnos.vue';
    import AutorskoDeloUnos from '@/components/a1/AutorskoDeloUnos.vue';
    import AutorUnos from '@/components/a1/AutorUnos.vue';
    import AutorPrikaz from '@/components/a1/AutorPrikaz.vue';

    // prilozi upload

    export default {
        name: 'PodnosenjeZahteva',
        components: {
            AutorskoDeloUnos,
            PodnosilacPrijaveUnos,
            AutorUnos,
            AutorPrikaz
        },
        data() {
            return {
                podnosilacPrijave: {},
                autorskoDelo: {},
                autori: [],
                autor: {}
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
                this.autori.push(this.autor);
            },
            updateAutor(autor) {
                this.autor = autor;
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