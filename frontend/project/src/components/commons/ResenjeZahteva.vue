<template>
    <div class="bordered">
        <h2 class="centered-text">Rešenje zahteva za unošenje u evidenciju i deponovanje autorskog dela</h2>
        <strong>
            <label>Status: 
                <span v-if="!obrazlozenje">ODOBREN</span>
                <span v-else>ODBIJEN</span>
            </label>
        </strong>
        <label>Datum rešenja: {{ datum }}</label>
        
        <label>Službenik: {{ imeSluzbenika }} {{ prezimeSluzbenika }}</label>
        
        <label>Referenca na zahtev:<a target="_blank" :href="`${getHost()}/zahtevi/${referenca}.html`"> {{ referenca }}</a></label>
        
        <label v-if="sifra">Šifra zahteva: {{ sifra }}</label>

        <label v-if="obrazlozenje">Obrazloženje: {{ obrazlozenje }}</label>
    </div>
</template>

<script>
    export default {
        name: 'ResenjeZahteva',
        props: ['datum', 'imeSluzbenika', 'prezimeSluzbenika', 'referenca', 'sifra', 'obrazlozenje', 'servis'],
        methods: {
            getHost() {
                if (this.servis) {
                    let port;
                    if (this.servis === 'A1') port = 8001;
                    else if (this.servis === 'P1') port = 8002;
                    else port = 8003;
                    return `http://localhost:${port}`;
                }
                let full = this.$store.state.host; 
                return full.substring(0, full.lastIndexOf('/'));   
            }
        }
    }
</script>

<style scoped>
    .bordered {
        border: 2px solid black;
        padding: 30px;
    }
    .centered-text {
        text-align: center;
    }
    label {
        display: block;
        margin: 15px;
    }
    h2 {
        margin-bottom: 40px;
    }
</style>