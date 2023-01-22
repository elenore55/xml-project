<template>
    <div>
        <div class="flex-container">
            <select class="log-select" v-model="rows[0].logical">
                <option value="0">-</option>
                <option value="1">NE</option>
            </select>
            <select v-model="rows[0].data">
                <option v-for="(m, i) in metadata" :key="m.id" :value="i">{{ m }}</option>
            </select>
            <select v-model="rows[0].cmp">
                <option value="0">&le;</option>
                <option value="1">&lt;</option>
                <option value="2">&ge;</option>
                <option value="3">&gt;</option>
                <option value="4">=</option>
                <option value="5">!=</option>
            </select>
            <input type="text" v-model="rows[0].value"/>
            <button type="button" @click="addRow">+</button>
        </div>
        <div v-for="(r, i) in rows" :key="i">
            <div class="flex-container" v-if="i > 0">
                <select class="log-select" v-model="rows[i].logical">
                    <option value="0">I</option>
                    <option value="1">ILI</option>
                    <option value="2">I NE</option>
                    <option value="3">ILI NE</option>
                </select>
                <select v-model="rows[i].data">
                    <option v-for="(m, j) in metadata" :key="m.id" :value="j">{{ m }}</option>
                </select>
                <select v-model="rows[i].cmp">
                    <option value="0">&le;</option>
                    <option value="1">&lt;</option>
                    <option value="2">&ge;</option>
                    <option value="3">&gt;</option>
                    <option value="4">=</option>
                    <option value="5">!=</option>
                </select>
                <input type="text" v-model="rows[i].value"/>
                <button type="button" @click="addRow">+</button>
                <button type="button" @click="removeRow(i)">-</button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'FilterUnos',
        data() {
            return {
                numRows: 1,
                rows: [{
                    logical: 0,
                    data: 0,
                    cmp: 0,
                    value: ''
                }],
                metadata: ['Broj', 'Ime', 'Prezime']
            }
        },
        methods: {
            addRow() {
                this.numRows++;
                this.rows.push({
                    logical: 0,
                    data: 0,
                    cmp: 0,
                    value: ''
                });
            },
            removeRow(i) {
                this.rows.splice(i, 1);
            },
            updateFilter() {

            }
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