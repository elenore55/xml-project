<template>
    <div class="centered">
        <PodnosilacPrijave title="Podnosioci prijave"></PodnosilacPrijave>
        <PodnosilacPrijave title="Punomoćnik"></PodnosilacPrijave>
        <PodnosilacPrijave v-if="podnosioci.length > 1" title="Zajednički predstavnik"></PodnosilacPrijave>
        <PodaciOZigu></PodaciOZigu>
        <PlaceneTakse></PlaceneTakse>
    </div>
</template>

<script>
    import PodnosilacPrijave from './PodnosilacPrijave.vue';
    import PodaciOZigu from './PodaciOZigu.vue';
    import PlaceneTakse from './PlaceneTakse.vue';

    export default {
        name: 'PodnosenjeZahtevaZ1',
        components: {
            PodnosilacPrijave,
            PodaciOZigu,
            PlaceneTakse
        },
        data() {
            return {
                podnosioci: []
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
        width: 75%;
    }
</style>