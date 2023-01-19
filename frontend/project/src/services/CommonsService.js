import axios from 'axios';

const osnovnaPretraga = (tekst, matchCase) => {
    return axios.get(`http://localhost:8001/autorsko-pravo/zahtev/search/${tekst}/${matchCase}`);
}

const getOne = (brojZahteva) => {
    return axios.get(`http://localhost:8001/autorsko-pravo/zahtev/htmlString/Zahtev${brojZahteva}.xml`);
}

export default { osnovnaPretraga, getOne }