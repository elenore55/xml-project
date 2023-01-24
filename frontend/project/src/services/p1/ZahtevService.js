import axios from 'axios';

const save = (xmlString) => {
    const config = {
        headers: {
            'Content-Type': 'application/xml'
        }
    }
    return axios.post('http://localhost:8002/patent/zahtev/save', xmlString, config);
}

export default { save }