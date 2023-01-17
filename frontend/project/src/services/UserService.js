import axios from 'axios';

const login = (username, password) => {
    const config = {
        headers: {
            'Content-Type': 'application/xml'
        }
    }
    const xmlString = `<?xml version="1.0" encoding="UTF-8"?>
    <LoginDTO>
        <korisnickoIme>${username}</korisnickoIme>
        <lozinka>${password}</lozinka>
    </LoginDTO>`

    return axios.post("http://localhost:8000/korisnici/login", xmlString, config);
}

const register = (userData) => {
    const config = {
        headers: {
            'Content-Type': 'application/xml'
        }
    }
    const xmlString = `<?xml version="1.0" encoding="UTF-8"?>
    <RegistrationDTO>
        <email>${userData.email}</email>
        <ime>${userData.name}</ime>
        <korisnickoIme>${userData.username}</korisnickoIme>
        <lozinka>${userData.password}</lozinka>
        <prezime>${userData.surname}</prezime>
    </RegistrationDTO>
    `
    return axios.post("http://localhost:8000/korisnici/register", xmlString, config);
}

export default {login, register};