<template>
    <div>
        <div class="flex-container">
            <select class="log-select" v-model="rows[0].logical" @change="updateFilter">
                <option>-</option>
                <option>NE</option>
            </select>
            <select v-model="rows[0].data" @change="updateFilter">
                <option v-for="m in metadata" :key="m.id">{{ m }}</option>
            </select>
            <select v-model="rows[0].cmp" @change="updateFilter">
                <option>=</option>
                <option>!=</option>
                <option>&ge;</option>
                <option>&gt;</option>
                <option>&le;</option>
                <option>&lt;</option>
            </select>
            <input type="text" v-model="rows[0].value" @input="updateFilter"/>
            <button type="button" @click="addRow">+</button>
        </div>
        <div v-for="(r, i) in rows" :key="i">
            <div class="flex-container" v-if="i > 0">
                <select class="log-select" v-model="rows[i].logical" @change="updateFilter">
                    <option>I</option>
                    <option>ILI</option>
                    <option>I NE</option>
                    <option>ILI NE</option>
                </select>
                <select v-model="rows[i].data" @change="updateFilter">
                    <option v-for="m in metadata" :key="m.id">{{ m }}</option>
                </select>
                <select v-model="rows[i].cmp" @change="updateFilter">
                    <option>=</option>
                    <option>!=</option>
                    <option>&ge;</option>
                    <option>&gt;</option>
                    <option>&le;</option>
                    <option>&lt;</option>
                </select>
                <input type="text" v-model="rows[i].value" @input="updateFilter"/>
                <button type="button" @click="addRow">+</button>
                <button type="button" @click="removeRow(i)">-</button>
            </div>
        </div>
    </div>
</template>

<script>
    import CommonsService from '@/services/CommonsService';
    import * as xml2js from 'xml2js';

    export default {
        name: 'FilterUnos',
        props: ['isZahtev'],
        mounted() {
            let fn = this.isZahtev? CommonsService.getZahtevMetadataVars : CommonsService.getResenjeMetadataVars;
            fn().then((response) => {
                xml2js.parseString(response.data, (_err, result) => {
                    for (let item of result.List.item) {
                        this.metadata.push(item);
                    }
                });
                this.rows[0].data = this.metadata[0];
            }).catch((err) => {
                console.log(err);
            });
        },  
        data() {
            return {
                numRows: 1,
                metadata: [],
                rows: [{
                    logical: '-',
                    data: '',
                    cmp: '=',
                    value: ''
                }],   
            }
        },
        methods: {
            addRow() {
                this.numRows++;
                this.rows.push({
                    logical: '',
                    data: '',
                    cmp: '',
                    value: ''
                });
                this.updateFilter();
            },
            removeRow(i) {
                this.rows.splice(i, 1);
                this.updateFilter();
            },
            updateFilter() {
                this.$emit('updateFilter', this.rows);
            },
        }
    }
</script>

<style scoped>
    .flex-container {
        display: flex;
        gap: 0 10px;
        margin-bottom: 10px;
    }
    .item {
        flex: 1
    }
    .log-select {
        width: 80px;
    }
</style>