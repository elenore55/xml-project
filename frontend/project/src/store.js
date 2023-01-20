import Vue from 'vue';
import Vuex from 'vuex';
import VuexPersistence from 'vuex-persist';

Vue.use(Vuex);

const vuexLocal = new VuexPersistence({
    storage: window.localStorage,
});

const store = new Vuex.Store({
    state: {
        servis: 'A1',
        host: 'http://localhost:8001/autorsko-pravo'
    },
    plugins: [vuexLocal.plugin],
});
export default store;