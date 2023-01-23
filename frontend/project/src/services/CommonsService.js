import axios from 'axios';
import store from '@/store';

const osnovnaPretraga = (tekst, matchCase) => {
    return axios.get(`${store.state.host}/zahtev/search/${tekst}/${matchCase}`);
}

const osnovnaPretragaResenje = (tekst, matchCase) => {
    return axios.get(`${store.state.host}/resenje/search/${tekst}/${matchCase}`);
}

const osnovnaPretragaBezResenja = (tekst, matchCase) => {
    return axios.get(`${store.state.host}/zahtev/bezResenja/search/${tekst}/${matchCase}`);
}

const naprednaPretraga = (rows) => {
    let query = buildQuery(rows);
    return axios.get(`${store.state.host}/metadata/advancedSearch/${query}`);
}

const naprednaPretragaResenje = (rows) => {
    let query = buildQuery(rows);
    console.log(query);
    return axios.get(`${store.state.host}/resenje/metadata/advancedSearch/${query}`);
}

const naprednaPretragaBezResenja = (rows) => {
    let query = buildQuery(rows);
    return axios.get(`${store.state.host}/bezResenja/metadata/advancedSearch/${query}`);
}

const getOne = (brojZahteva) => {
    return axios.get(`${store.state.host}/zahtev/htmlString/Zahtev${brojZahteva}.xml`);
}

const getOneResenje = (referenca) => {
    return axios.get(`${store.state.host}/resenje/one/Resenje_${referenca}.xml`);
}

const downloadZahtevPDF = (broj) => {
    axios({
        url: `${store.state.host}/zahtev/pdf/Zahtev${broj}.xml`,
        method: 'GET',
        responseType: 'blob',
    }).then((response) => {
        const href = URL.createObjectURL(response.data);
        const link = document.createElement('a');
        link.href = href;
        link.setAttribute('download', 'zahtev.pdf');
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        URL.revokeObjectURL(href);
    });
}

const downloadZahtevHTML = (broj) => {
    axios({
        url: `${store.state.host}/zahtev/html/Zahtev${broj}.xml`,
        method: 'GET',
        responseType: 'blob',
    }).then((response) => {
        const href = URL.createObjectURL(response.data);
        const link = document.createElement('a');
        link.href = href;
        link.setAttribute('download', 'zahtev.html');
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        URL.revokeObjectURL(href);
    });
}

const generateReport = (period) => {
    axios({
        url: `${store.state.host}/resenje/report`,
        method: 'POST',
        data: period,
        headers: {
            'Content-Type': 'application/xml'
        },
        responseType: 'blob',
    }).then((response) => {
        const href = URL.createObjectURL(response.data);
        const link = document.createElement('a');
        link.href = href;
        link.setAttribute('download', 'zahtev.pdf');
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        URL.revokeObjectURL(href);
    });
}

const getZahtevMetadata = (type) => {
    axios({
        url: `${store.state.host}/metadata/all/${type}`,
        method: 'GET',
        responseType: 'blob',
    }).then((response) => {
        const href = URL.createObjectURL(response.data);
        const link = document.createElement('a');
        link.href = href;
        link.setAttribute('download', `metadata.${type}`);
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        URL.revokeObjectURL(href);
    });
}

const getResenjeMetadata = (type) => {
    axios({
        url: `${store.state.host}/resenje/metadata/all/${type}`,
        method: 'GET',
        responseType: 'blob',
    }).then((response) => {
        const href = URL.createObjectURL(response.data);
        const link = document.createElement('a');
        link.href = href;
        link.setAttribute('download', `metadata.${type}`);
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        URL.revokeObjectURL(href);
    });
}

const getNereseniZahtevi = () => {
    return axios.get(`${store.state.host}/zahtev/bezResenja`);
}

const odobriZahtev = (xmlString) => {
    const config = {
        headers: {
            'Content-Type': 'application/xml'
        }
    }
    return axios.post(`${store.state.host}/resenje/accept`, xmlString, config);
} 

const odbijZahtev = (xmlString) => {
    const config = {
        headers: {
            'Content-Type': 'application/xml'
        }
    }
    return axios.post(`${store.state.host}/resenje/reject`, xmlString, config);
}

const getZahtevMetadataVars = () => {
    return axios.get(`${store.state.host}/metadata/vars`);
}

const getResenjeMetadataVars = () => {
    return axios.get(`${store.state.host}/resenje/metadata/vars`);
}

const buildQuery = (rows) => {
    let result = []
    if (rows[0].logical === 'NE') result.push('NE');
    if (rows[0].data !== '' && rows[0].cmp !== '' && rows[0].value !== '') {
        result.push(rows[0].data);
        result.push(rows[0].cmp);
        result.push(rows[0].value);
        for (let i = 1; i < rows.length; i++) {
            if (rows[i].logical !== '' && rows[i].data !== '' && rows[i].cmp !== '' && rows[i].value !== '') {
                result.push(rows[i].logical);
                result.push(rows[i].data);
                result.push(rows[i].cmp);
                result.push(rows[i].value);
            }
        }
    }
    return result.join(' ');
}

export default { osnovnaPretraga, getOne, downloadZahtevPDF, downloadZahtevHTML, generateReport, getZahtevMetadata, 
    getResenjeMetadata, getNereseniZahtevi, odobriZahtev, odbijZahtev, osnovnaPretragaBezResenja, getOneResenje, osnovnaPretragaResenje,
    getResenjeMetadataVars, getZahtevMetadataVars, naprednaPretragaResenje }