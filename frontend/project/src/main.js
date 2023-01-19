import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router';
import LoginPage from './components/user/LoginPage.vue';
import RegistrationPage from './components/user/RegistrationPage.vue';
import PodnosenjeZahteva from './components/a1/PodnosenjeZahteva.vue';

Vue.config.productionTip = false
Vue.use(VueRouter);


const router = new VueRouter({
  mode: 'hash',
  routes: [
    { path: '/', component: LoginPage },
    { path: '/register', component: RegistrationPage },
    { path: '/a1/podnosenjeZahteva', component: PodnosenjeZahteva},
  ]
})

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
