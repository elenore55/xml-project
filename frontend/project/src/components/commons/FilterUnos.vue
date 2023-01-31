<template>
    <div>
        <div class="flex-container">
            <select class="log-select" v-model="rows[0].logical" @change="updateFilter">
                <option>-</option>
                <option>NE</option>
            </select>
            <select v-model="rows[0].data" @change="setSelectedMetadata(0)" class="meta">
                <option v-for="m in metadata" :key="m.id">{{ m.name }}</option>
            </select>
            <select v-model="rows[0].cmp" @change="updateFilter">
                <option>=</option>
                <option>!=</option>
                <option>&ge;</option>
                <option>&gt;</option>
                <option>&le;</option>
                <option>&lt;</option>
            </select>

            <input v-if="selectedMetadata[0].type == 'DATE'" type="date" v-model="rows[0].value" @input="updateFilter" />
            <input v-else-if="selectedMetadata[0].type == 'NUM'" type="number" min="1" v-model="rows[0].value" @input="updateFilter" />
            <select v-else-if="selectedMetadata[0].type == 'BOOL'" v-model="rows[0].value" @change="updateFilter" class="bool">
                <option>true</option>
                <option>false</option>
            </select>
            <input v-else type="text" v-model="rows[0].value" @input="updateFilter" />

            <button type="button" @click="addRow">+</button>
        </div>
        <div v-for="(r, i) in rows" :key="i">
            <div class="flex-container" v-if="i > 0">
                <select class="log-select" v-model="r.logical" @change="updateFilter">
                    <option>I</option>
                    <option>ILI</option>
                    <option>I NE</option>
                    <option>ILI NE</option>
                </select>
                <select v-model="r.data" @change="setSelectedMetadata(i)" class="meta">
                    <option v-for="m in metadata" :key="m.id">{{ m.name}}</option>
                </select>
                <select v-model="r.cmp" @change="updateFilter">
                    <option>=</option>
                    <option>!=</option>
                    <option>&ge;</option>
                    <option>&gt;</option>
                    <option>&le;</option>
                    <option>&lt;</option>
                </select>

                <input v-if="selectedMetadata[i].type == 'DATE'" type="date" v-model="r.value" @input="updateFilter" />
                <input v-else-if="selectedMetadata[i].type == 'NUM'" type="number" min="1" v-model="r.value" @input="updateFilter" />
                <select v-else-if="selectedMetadata[i].type == 'BOOL'" v-model="r.value" @change="updateFilter" class="bool">
                    <option>true</option>
                    <option>false</option>
                </select>
                <input v-else type="text" v-model="r.value" @input="updateFilter" />
                
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
                        this.metadata.push({
                            name: item.name[0],
                            type: item.type[0]
                        });
                    }
                });

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
                selectedMetadata: [{name: '', type: ''}]   
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
                this.selectedMetadata.push({name: '', type: ''})
                this.updateFilter();
            },
            removeRow(i) {
                this.rows.splice(i, 1);
                this.selectedMetadata.splice(i, 1);
                this.updateFilter();
            },
            updateFilter() {
                this.$emit('updateFilter', this.rows);
            },
            setSelectedMetadata(row) {
                let index = document.getElementsByClassName('meta')[row].selectedIndex;
                if (index != -1) {
                    console.log(this.metadata[index]);
                    this.selectedMetadata[row] = this.metadata[index];
                    this.updateFilter();
                }
            }
        }
    }
</script>

<style scoped>
    input[type='date'] {
        width: 228px;
    }
    select.bool {
        width: 233px;
    }
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