<template>
    <div class="flex-container">
        <div id="naslov" class="flex-container-v item">
            <label for="naslov-input" class="item">Naslov</label>
            <input id="naslov-input" type="text" :class="this.isNaslovValid?'item':'item red-border'" v-model="naslov" @input="validateNaslov"/>
        </div>
        <div id="alt-naslov" class="flex-container-v item">
            <label for="alt-naslov-input">Alternativni naslov</label>
            <input id="alt-naslov-input" type="text" v-model="altNaslov" @input="updateNaslov"/>
        </div>
    </div>
</template>

<script>

    export default {
        name: 'PodaciONaslovu',
        data() {
            return {
                naslov: '',
                altNaslov: '',
                isNaslovValid: true
            }
        },
        methods: {
            updateNaslov() {
                this.$emit('updateNaslov', {
                    naslov: this.naslov,
                    altNaslov: this.altNaslov
                });
            },
            clear() {
                this.naslov = '';
                this.altNaslov = '';
                this.updateNaslov();
            },
            validateNaslov() {
                this.isNaslovValid = (this.naslov != '');
                this.updateNaslov();
            },
            isValidInput() {
                this.validateNaslov();
                return this.naslov != '';
            }
        }
    }

</script>

<style scoped>
    .flex-container {
        display: flex;
        gap: 0 20px;
        margin-bottom: 10px;
        align-items: flex-end;
    }
    .flex-container-v {
        display: flex;
        flex-direction: column;
        gap: 10px;
    }
    .item {
        flex: 1
    }
</style>