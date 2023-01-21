import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router';
import LoginPage from './components/user/LoginPage.vue';
import RegistrationPage from './components/user/RegistrationPage.vue';
import PodnosenjeZahteva from './components/a1/PodnosenjeZahteva.vue';
import ZahtevPretraga from './components/commons/ZahtevPretraga.vue';
import GenerisanjeIzvestaja from './components/commons/GenerisanjeIzvestaja.vue';
import PocetnaStranica from './components/user/PocetnaStranica.vue';
import PodnosenjeResenja from './components/commons/PodnosenjeResenja';
import PodnosenjeZahtevaZ1 from './components/z1/PodnosenjeZahtevaZ1';
import store from './store';

Vue.config.productionTip = false
Vue.use(VueRouter);


const router = new VueRouter({
  mode: 'hash',
  routes: [
    { path: '/', component: LoginPage },
    { path: '/register', component: RegistrationPage },
    { path: '/a1/podnosenjeZahteva', component: PodnosenjeZahteva },
    { path: '/zahtevPretraga', component: ZahtevPretraga },
    { path: '/generisanjeIzvestaja', component: GenerisanjeIzvestaja },
    { path: '/home', component: PocetnaStranica },
    { path: '/podnosenjeResenja', component: PodnosenjeResenja },
    { path: '/z1/podnosenjeZahteva', component: PodnosenjeZahtevaZ1 }
  ]
})

new Vue({
  router,
  render: h => h(App),
  store,
}).$mount('#app')
