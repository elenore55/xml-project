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
        
        <label>Referenca na zahtev: {{ referenca }}</label>
        
        <label v-if="sifra">Šifra zahteva: {{ sifra }}</label>

        <label v-if="obrazlozenje">Obrazloženje: {{ obrazlozenje }}</label>
    </div>
</template>

<script>
    export default {
        name: 'ResenjeZahteva',
        props: ['datum', 'imeSluzbenika', 'prezimeSluzbenika', 'referenca', 'sifra', 'obrazlozenje']
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