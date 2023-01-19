<template>
    <div>
        <h4>{{ ime }} {{ prezime }}</h4>
        <div v-if="adresa">
            <h4>
                Adresa: 
            </h4>
            {{ adresa.ulica }} {{ adresa.broj }}, {{ adresa.postanskiBroj }} {{ adresa.mesto }}
            <br/>
            <h4>Državljanstvo:</h4>
            {{ drzavljanstvo }}
        </div>
        <div v-else class="flex-container">
            <h4>Godina smrti: </h4>
            <span>{{ this.godinaSmrti }}</span>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'AutorPrikaz',
        props: ['ime', 'prezime', 'adresa', 'godinaSmrti', 'drzavljanstvo']
    }
</script>

<style scoped>
    h4 {
        margin-top: 5px;
        margin-bottom: 5px;
        display: inline;
    }
</style>