import axios from 'axios';
import store from '@/store';

const osnovnaPretraga = (tekst, matchCase) => {
    return axios.get(`${store.state.host}/zahtev/search/${tekst}/${matchCase}`);
}

const getOne = (brojZahteva) => {
    return axios.get(`${store.state.host}/zahtev/htmlString/Zahtev${brojZahteva}.xml`);
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

export default { osnovnaPretraga, getOne, downloadZahtevPDF, downloadZahtevHTML, generateReport, getZahtevMetadata, getResenjeMetadata }