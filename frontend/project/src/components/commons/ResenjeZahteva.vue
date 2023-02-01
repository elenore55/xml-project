<template>
    <div class="bordered">
        <h2 class="centered-text">Rešenje zahteva za unošenje u evidenciju i deponovanje autorskog dela</h2>
        <strong>
            <label>Status: 
                <span v-if="!obrazlozenje">ODOBREN</span>
                <span v-else>ODBIJEN</span>
            </label>
        </strong>
        <label>Datum rešenja: {{ datumResenja }}</label>
        
        <label>Službenik: {{ imeSluzbenika }} {{ prezimeSluzbenika }}</label>
        
        <label>Referenca na zahtev:<a target="_blank" :href="`${getHost()}/zahtev/htmlString/${referenca}.xml`"> {{ referenca }}</a></label>
        
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
                    if (this.servis === 'A1') port = '8001/autorsko_pravo';
                    else if (this.servis === 'P1') port = '8002/patent';
                    else port = '8003/zig';
                    return `http://localhost:${port}`;
                }
                return this.$store.state.host; 
            }
        },
        computed: {
            datumResenja() {
                let date = new Date(Date.parse(this.datum));
                date.setDate(date.getDate() + 1);
                return date.toISOString().split('T')[0];
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