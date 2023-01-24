<template>
    <div id="nazivPronalaska"></div>
</template>

<!-- <script>
    export default {
        name: 'NazivPronalaska',
        mounted() {
            /* eslint-disable */
            this.start();
        },
        data() {
            return {
                doSpec: {
                    elements: {
                        "Podaci_o_nazivu_pronalaska": {

                        },
                        "Naziv": {

                        }
                    }
                }
            }
        },
        methods: {
            updateNaziv() {
                this.$emit("updateNaziv", Xonomy.harvest());
            },
            start() {
                let xml = "<Podaci_o_nazivu_pronalaska><Naziv></Naziv><Naziv></Naziv></Podaci_o_nazivu_pronalaska>";
                var editor = document.getElementById("nazivPronalaska");
                Xonomy.render(xml, editor, this.doSpec);
            }
        }
    }
</script> -->