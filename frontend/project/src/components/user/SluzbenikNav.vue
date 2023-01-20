<template>
    <div class="topnav">
        <div class="dropdown">
            <a class="dropdown-link">Servis: {{ getServis }}</a>
            <div class="dropdown-content">
                <a @click="setA1Servis" :class="getServis == 'A1'? 'active': 'none'">Autorska prava</a>
                <a @click="setP1Servis" :class="getServis == 'P1'? 'active': 'none'">Patenti</a>
                <a @click="setZ1Servis" :class="getServis == 'Ž1'? 'active': 'none'">Žigovi</a>
            </div>
        </div>
        <a @click="podnesiResenje">Podnesi rešenje</a>
        <div class="dropdown">
            <a class="dropdown-link">Pretraga</a>
            <div class="dropdown-content">
                <a @click="goToZahtevPretraga">Zahtevi</a>
                <a>Rešenja</a>
            </div>
        </div>
        <div class="dropdown">
            <a class="dropdown-link">Metapodaci zahteva</a>
            <div class="dropdown-content">
                <a @click="downloadZahtevMetadata('json')">JSON</a>
                <a @click="downloadZahtevMetadata('rdf')">RDF</a>
            </div>
        </div>
        <div class="dropdown">
            <a class="dropdown-link">Metapodaci rešenja</a>
            <div class="dropdown-content">
                <a @click="downloadResenjeMetadata('json')">JSON</a>
                <a @click="downloadResenjeMetadata('rdf')">RDF</a>
            </div>
        </div>
        <a @click="goToIzvestaji">Generisanje izveštaja</a>
        <a @click="logout">Logout</a>
    </div>
</template>

<script>
    import CommonsService from '@/services/CommonsService';

    export default {
        name: 'SluzbenikNav',
        data() {
            return {
                selected: 1,
            }
        },
        methods: {
            setA1Servis() {
                this.$store.state.servis = 'A1';
                this.$store.state.host = 'http://localhost:8001/autorsko-pravo';
            },
            setP1Servis() {
                this.$store.state.servis = 'P1';
                this.$store.state.host = 'http://localhost:8002/patent';
            },
            setZ1Servis() {
                this.$store.state.servis = 'Ž1';
                this.$store.state.host = 'http://localhost:8003/zig';
            },
            logout() {
                localStorage.removeItem('username');
                localStorage.removeItem('email');
                localStorage.removeItem('name');
                localStorage.removeItem('surname');
                localStorage.removeItem('role');
                this.$router.push('/');
            },
            goToIzvestaji() {
                this.$router.push('/generisanjeIzvestaja');
            },
            downloadZahtevMetadata(type) {
                CommonsService.getZahtevMetadata(type);
            },
            downloadResenjeMetadata(type) {
                CommonsService.getResenjeMetadata(type);
            },
            goToZahtevPretraga() {
                this.$router.push('/zahtevPretraga');
            },
            podnesiResenje() {
                this.$router.push('/podnosenjeResenja');
            }
        },
        computed: {
            getServis() {
                return this.$store.state.servis;
            },
        }
    }
</script>

<style scoped>
    .topnav {
        background-color: #504e66;
        overflow: hidden;
    }
    .topnav a {
        float: left;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        font-size: 17px;
    }
    .topnav a:hover {
        background-color: #ddd;
        color: black;
    }
    .topnav a.active {
        background-color: #706d85;
        color: white;
    }
    .dropdown {
        float: left;
        overflow: hidden;
    }
    .dropdown .dropdown-link {
        font-size: 16px;
        border: none;
        outline: none;
        color: white;
        padding: 14px 16px;
        background-color: inherit;
        font-family: inherit;
        margin: 0;
    }
    .dropdown-content {
        display: none;
        position: absolute;
        background-color: #f9f9f9;
        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1;
        margin-top: 45px;
    }
    .dropdown-content a {
        float: none;
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
        text-align: left;
    }
    .dropdown-content a:hover {
        background-color: #ddd;
    }
    .dropdown:hover .dropdown-content {
        display: block;
    }
</style>