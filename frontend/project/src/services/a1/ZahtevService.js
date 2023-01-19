import axios from 'axios';

const saveWithPrilog = (formData) => {
    return axios.post('http://localhost:8001/autorsko-pravo/zathev/saveWithPrilog', formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
}

const save = (xmlString) => {
    const config = {
        headers: {
            'Content-Type': 'application/xml'
        }
    }
    return axios.post('http://localhost:8001/autorsko-pravo/zahtev/save', xmlString, config);
}

export default { saveWithPrilog, save }