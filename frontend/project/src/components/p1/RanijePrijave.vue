<template>
    <div id="ranijePrijave" style="margin-bottom: 40px; margin-top: 50px"></div>
</template>

<script>
    export default {
        name: "RanijePrijave",
        mounted() {
            /* eslint-disable */
            this.start();
        },
        data() {
            return {
                doSpec: {
                    onchange: () => {
                        this.updateXonomyData();
                    },
                    elements: {
                        "Priznanje_prvenstva_iz_ranijih_prijava": {
                            menu: [{
                                caption: "Dodaj <Prijavu>",
                                action: Xonomy.newElementChild,
                                actionParameter: "<Prijava></Prijava>"
                            }]
                        },
                        "Prijava": {
                            menu: [
                                {
                                    caption: "Dodaj <Datum_podnosenja>",
                                    action: Xonomy.newElementChild,
                                    actionParameter: "<Datum_podnosenja></Datum_podnosenja>",
                                    hideIf: function(jsElement){
                                        return jsElement.hasChildElement("Datum_podnosenja");
                                    }
                                },
                                {
                                    caption: "Dodaj <Broj_prijave>",
                                    action: Xonomy.newElementChild,
                                    actionParameter: "<Broj_prijave></Broj_prijave>",
                                    hideIf: function(jsElement){
                                        return jsElement.hasChildElement("Broj_prijave");
                                    }
                                },
                                {
                                    caption: "Dodaj <Dvoslovnu_oznaku_organizacije>",
                                    action: Xonomy.newElementChild,
                                    actionParameter: "<Dvoslovna_oznaka_organizacije></Dvoslovna_oznaka_organizacije>",
                                    hideIf: function(jsElement){
                                        return jsElement.hasChildElement("Dvoslovna_oznaka_organizacije");
                                    }
                                },
                                {
                                    caption: "Ukloni ovu <Prijavu>",
                                    action: Xonomy.deleteElement
                                }
                            ],
                            canDropTo: ["Priznanje_prvenstva_iz_ranijih_prijava"],
                        },
                        "Datum_podnosenja": {
                            hasText: true,
                            oneliner: true,
                            asker: this.askDate
                        },
                        "Broj_prijave": {
                            hasText: true,
                            oneliner: true,
                            asker: this.askNum
                        },
                        "Dvoslovna_oznaka_organizacije": {
                            hasText: true,
                            oneliner: true,
                            asker: this.askOznaka
                        },
                        "Podaci_o_nazivu": {},
                        "Naziv": {
                            hasText: true,
                            inlineMenu: [
                                {
                                    caption: "Make <b>BOLD</b>",
                                    action: Xonomy.wrap,
                                    actionParameter: {template: "<b>$</b>", placeholder: "$"}

                                },
                                {
                                    caption: "Make <i>italic</i>",
                                    action: Xonomy.wrap,
                                    actionParameter: {template: "<i>$</i>", placeholder: "$"}

                                },
                            ],
                            canDropTo: ["Podaci_o_nazivu"]
                        },
                        "Podaci_o_patentu": {},
                        "b": {
                            hasText: true,
                            menu: [{
                                caption: "Remove <b>BOLD</b>",
                                action: Xonomy.unwrap
                            }]
                        },
                        "i": {
                            hasText: true,
                            menu: [{
                                caption: "Remove <i>italic</i>",
                                action: Xonomy.unwrap
                            }]
                        },
                    }
                }
            }
        },
        methods: {
            start() {
                let xml = `<Podaci_o_patentu><Podaci_o_nazivu><Naziv jezik='srpski'></Naziv><Naziv jezik='engleski'></Naziv></Podaci_o_nazivu>
                            <Priznanje_prvenstva_iz_ranijih_prijava><Prijava></Prijava></Priznanje_prvenstva_iz_ranijih_prijava></Podaci_o_patentu>`;
                var editor = document.getElementById("ranijePrijave");
                Xonomy.render(xml, editor, this.doSpec);
            },
            updateXonomyData() {
                this.$emit("updateXonomyData", Xonomy.harvest());
            },
            askNum(defaultString) {
                var html="";
                html+="<form onsubmit='Xonomy.answer(this.val.value); return false'>";
                html+="<input name='val' type='number' min='1' class='textbox focusme'value='"+Xonomy.xmlEscape(defaultString)+"'/>";
                html+="<input type='submit' value='OK'>";
                html+="</form>";
                return html;
            },
            askDate(defaultString) {
                var html="";
                html+="<form onsubmit='Xonomy.answer(this.val.value); return false'>";
                html+=`<input name='val' type='date' min='1950-01-01' max='${new Date().toISOString().split("T")[0]}' class='textbox focusme'value='${Xonomy.xmlEscape(defaultString)}'/>`;
                html+="<input type='submit' value='OK'>";
                html+="</form>";
                return html;
            },
            askOznaka(defaultString) {
                var html="";
                html+="<form onsubmit='Xonomy.answer(this.val.value); return false'>";
                html+=`<input name='val' type='text' minLength='2' maxLength='2' pattern='[A-Za-z]{2}' class='textbox focusme'value='${Xonomy.xmlEscape(defaultString)}'/>`;
                html+="<input type='submit' value='OK'>";
                html+="</form>";
                return html;
            }
        }
    }
</script>