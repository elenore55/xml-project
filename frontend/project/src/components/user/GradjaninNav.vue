<template>
    <div class="topnav">
        <div class="dropdown">
            <a class="dropdown-link">Servis: {{ servis }}</a>
            <div class="dropdown-content">
                <a href="#" @click="setA1Servis" :class="servis == 'A1'? 'active': 'none'">Autorska prava</a>
                <a href="#" @click="setP1Servis" :class="servis == 'P1'? 'active': 'none'">Patenti</a>
                <a href="#" @click="setZ1Servis" :class="servis == 'Ž1'? 'active': 'none'">Žigovi</a>
            </div>
        </div>
        <a>Podnesi zahtev</a>
        <a>Pretraga rešenja</a>
        <a @click="logout">Logout</a>
    </div>
</template>

<script>
    export default {
        name: 'GradjaninNav',
        data() {
            return {
                selected: 1,
                servis: 'A1'
            }
        },
        methods: {
            setA1Servis() {
                this.servis = 'A1';
            },
            setP1Servis() {
                this.servis = 'P1';
            },
            setZ1Servis() {
                this.servis = 'Ž1';
            },
            logout() {
                localStorage.removeItem('username');
                localStorage.removeItem('email');
                localStorage.removeItem('name');
                localStorage.removeItem('surname');
                localStorage.removeItem('role');
                this.$router.push('/');
            }
        }
    }
</script>

<style scoped>
    .topnav {
        background-color: #504e66;
        overflow: hidden;
    }

    /* Style the links inside the navigation bar */
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