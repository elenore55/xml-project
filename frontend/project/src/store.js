import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        servis: 'A1',
        host: 'http://localhost:8001/autorsko_pravo'
    },
});