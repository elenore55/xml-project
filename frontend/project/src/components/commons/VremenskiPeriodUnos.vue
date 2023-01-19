<template>
    <div class="flex-container">
        <div class="item flex-container-v">
            <label>Početni datum</label>
            <input type="date" v-model="start" @input="updatePeriod" />
        </div>
        <div class="item flex-container-v">
            <label>Krajnji datum</label>
            <input type="date" v-model="end" @input="updatePeriod" />
        </div>
    </div>
</template>

<script>
    export default {
        name: 'VremenskiPeriodUnos', 
        data() {
            return {
                start: '',
                end: ''
            }
        },
        methods: {
            updatePeriod() {
                this.$emit('updatePeriod', {
                    start: this.start,
                    end: this.end
                })
            }
        }
    }
</script>

<style scoped>
    .flex-container {
        display: flex;
        gap: 20px;
        margin-bottom: 10px;
        align-items: flex-end;
        margin-top: 30px;
    }
    .flex-container-v {
        display: flex;
        flex-direction: column;
        gap: 10px;
    }
    .item {
        flex: 1;
        margin: 0 30px;
    }
</style>