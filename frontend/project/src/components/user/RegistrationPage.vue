<template>
    <div>
        <div class="centered bordered">
            <h2 class="centered-text">Registracija</h2>
            <div class="grid-container">
                <label id="username-label">Korisničko ime</label>
                <input id="username-input" v-model="username" type="text" :class="(!isUsernameValid)?'red-border':'none'" @input="validateUsername" />
                <p class="error" id="username-error" v-if="!isUsernameValid">Korisničko ime je obavezno</p>

                <label id="pw-label">Lozinka</label>
                <input id="pw-input" type="password" v-model="password" :class="(!isPasswordValid)?'red-border':'none'" @input="validatePassword" />
                <p class="error" id="pw-error" v-if="!isPasswordValid">Lozinka je obaveznа</p>

                <label id="email-label">E-mail</label>
                <input id="email-input" type="text" v-model="email" :class="(!isEmailValid)?'red-border':'none'" @input="validateEmail"/>
                <p class="error" id="email-error" v-if="!isEmailValid">E-mail je obavezan</p>

                <label id="name-label">Ime</label>
                <input id="name-input" type="text" v-model="name" :class="(!isNameValid)?'red-border':'none'" @input="validateName"/>
                <p class="error" id="name-error" v-if="!isNameValid">Ime je obavezno</p>

                <label id="surname-label">Prezime</label>
                <input id="surname-input" type="text" v-model="surname" :class="(!isSurnameValid)?'red-border':'none'" @input="validateSurname"/>
                <p class="error" id="surname-error" v-if="!isSurnameValid">Prezime je obavezno</p>
            </div>
            <button id="btn-reg" @click="submit">Registrujte se</button>
            <p>Već imate nalog? <a href="/" class="centered-text">Prijavite se ovde</a></p>
        </div>

    </div>
</template>

<script>
    import UserService from '@/services/UserService';

    export default {
        name: 'RegistrationPage',
        data() {
            return {
                username: '',
                password: '',
                email: '',
                name: '',
                surname: '',
                isUsernameValid: true,
                isPasswordValid: true,
                isEmailValid: true,
                isNameValid: true,
                isSurnameValid: true
            }
        },
        methods: {
            submit() {
                this.validate();
                if (this.isEmailValid && this.isUsernameValid && this.isPasswordValid && this.isNameValid && this.isSurnameValid) {
                    console.log('valid');
                    UserService.register({username: this.username, password: this.password, email: this.email, name: this.name, surname: this.surname})
                    .then((_response) => {
                        alert('Registration successful!');
                        this.$router.push('/'); 
                    }).catch((err) => {
                        alert(JSON.stringify(err));
                    });
                }
            },

            validate() {
                this.validateUsername();
                this.validatePassword();
                this.validateEmail();
                this.validateName();
                this.validateSurname();
            },
            validateUsername() {
                this.isUsernameValid = (this.username != '');
            },
            validatePassword() {
                this.isPasswordValid = (this.password != '');
            },
            validateEmail() {
                this.isEmailValid = (this.email != '');
            },
            validateName() {
                this.isNameValid = (this.name != '');
            },
            validateSurname() {
                this.isSurnameValid = (this.surname != '');
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
        transform: translate(-50%, -47%);
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
    label {
        grid-column: 1;
        text-align: end;
        font-size: 20px;
    }
    #username-label { 
        grid-row: 1;
    }
    #pw-label {
        grid-row: 3;
    }
    #email-label {
        grid-row: 5;
    }
    #name-label {
        grid-row: 7;
    }
    #surname-label {
        grid-row: 9;
    }
    input {
        grid-column: 2;
        font-size: 20px;
    }
    #username-input {
        grid-row: 1;
    }
    #pw-input {
        grid-row: 3;
    }
    #email-input {
        grid-row: 5;
    }
    #name-input {
        grid-row: 7;
    }
    #surname-input {
        grid-row: 9;
    }
    #btn-reg {
        font-size: 18px;
        padding: 3px;
        margin-bottom: 25px;
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
    #email-error {
        grid-row: 6;
    }
    #name-error {
        grid-row: 8;
    }
    #surname-error {
        grid-row: 10;
    }
</style>