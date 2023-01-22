import axios from "axios";

const save = (formData) => {
    return axios.post('http://localhost:8003/zig/zahtev/save', formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
}

export default { save }