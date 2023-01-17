<template>
    <div class="container">
        <h2 class="centered-text title">ZAVOD ZA INTELEKTUALNU SVOJINU</h2>
        <div class="centered bordered">
            <h2 class="centered-text">Molimo Vas da se ulogujete</h2>
            <div class="grid-container">
                <label id="username-label">Korisničko ime</label>
                <input id="username-input" v-model="username" type="text" :class="(!isUsernameValid)?'red-border':'none'" @input="validateUsername" />
                <p class="error" id="username-error" v-if="!isUsernameValid">Korisničko ime je obavezno</p>
                <label id="pw-label">Lozinka</label>
                <input id="pw-input" type="password" v-model="password" :class="(!isPasswordValid)?'red-border':'none'" @input="validatePassword" />
                <p class="error" id="pw-error" v-if="!isPasswordValid">Lozinka je obaveznа</p>
            </div>
            <button id="btn-login" @click="submit">Login</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import * as xml2js from 'xml2js';

export default {
    name: 'LoginPage',
    data() {
        return {
            username: '',
            password: '',
            isUsernameValid: true,
            isPasswordValid: true
        }
    },
    methods: {
        submit() {
            this.validate();
            if (this.isUsernameValid && this.isPasswordValid) {
                const config = {
                    headers: {
                        'Content-Type': 'application/xml'
                    }
                }
                const xmlString = `<?xml version="1.0" encoding="UTF-8"?>
                <LoginDTO>
                    <korisnickoIme>${this.username}</korisnickoIme>
                    <lozinka>${this.password}</lozinka>
                </LoginDTO>`

                axios.post("http://localhost:8000/korisnici/login", xmlString, config).then((response) => {
                    xml2js.parseString(response.data, function (err, result) {
                        localStorage.setItem('username', result.KorisnikDTO.korisnickoIme);
                        localStorage.setItem('email', result.KorisnikDTO.email);
                        localStorage.setItem('name', result.KorisnikDTO.ime);
                        localStorage.setItem('surname', result.KorisnikDTO.prezime);
                        localStorage.setItem('role', result.KorisnikDTO.uloga);
                    });
                }).catch((err) => {
                    alert(JSON.stringify(err));
                });
            }
            
        },
        validate() {
            this.validateUsername();
            this.validatePassword();
        },
        validateUsername() {
            if (this.username === '')
                this.isUsernameValid = false;
            else
                this.isUsernameValid = true;
        },
        validatePassword() {
            if (this.password === '')
                this.isPasswordValid = false;
            else
                this.isPasswordValid = true;
        }
    }
}
</script>

<style scoped>
    .centered {
        margin: 0;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 60%;
        text-align: center;
    }
    .bordered {
        border: 2px solid gray;
    }
    .title {
        color: dark-gray;
    }
    .centered-text {
        text-align: center;
        margin-top: 50px;
    }
    .grid-container {
        display: grid;
        column-gap: 15px;
        row-gap: 10px;
        margin: 50px auto;
        width: 75%;
        grid-template-columns: max-content auto;
    }
    #username-label {
        grid-column: 1;
        grid-row: 1;
        text-align: end;
        font-size: 20px;
    }
    #pw-label {
        grid-column: 1;
        grid-row: 3;
        text-align: end;
        font-size: 20px;
    }
    #username-input {
        grid-column: 2;
        grid-row: 1;
        font-size: 20px;
    }
    #pw-input {
        grid-column: 2;
        grid-row: 3;
        font-size: 20px;
    }
    #btn-login {
        font-size: 18px;
        padding: 3px;
        margin-bottom: 50px;
        width: 75%;
    }
    .error {
        color: red;
        text-align: start;
        grid-column: 2;
        margin-top: 0;
    }
    #username-error {
        grid-row: 2;
    }
    #pw-error {
        grid-row: 4;
    }
    .red-border {
        border: 1px solid red;
    }
</style>