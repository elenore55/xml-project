import axios from 'axios';

const save = (xmlString) => {
    const config = {
        headers: {
            'Content-Type': 'application/xml'
        }
    }
    return axios.post('http://localhost:8002/patent/zahtev/save', xmlString, config);
}

const getResenje = (broj) => {
    return axios.get(`http://localhost:8002/patent/resenje/one/Resenje_Zahtev${broj}.xml`);
}

export default { save, getResenje }