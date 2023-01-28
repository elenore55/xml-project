import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        servis: localStorage.getItem('servis'),
        host: localStorage.getItem('host')
    },
    mutations: {
        setA1(state) {
            localStorage.setItem('servis', 'A1');
            localStorage.setItem('host', 'http://localhost:8001/autorsko_pravo');
            state.servis = 'A1';
            state.host = 'http://localhost:8001/autorsko_pravo';
        },
        setP1(state) {
            localStorage.setItem('servis', 'P1');
            localStorage.setItem('host', 'http://localhost:8002/patent');
            state.servis = 'P1';
            state.host = 'http://localhost:8002/patent';
        },
        setZ1(state) {
            localStorage.setItem('servis', 'Ž1');
            localStorage.setItem('host', 'http://localhost:8003/zig');
            state.servis = 'Ž1';
            state.host = 'http://localhost:8003/zig';
        }
    }
});