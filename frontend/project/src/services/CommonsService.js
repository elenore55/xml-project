import axios from 'axios';

const osnovnaPretraga = (tekst, matchCase) => {
    return axios.get(`${this.$store.state.host}/zahtev/search/${tekst}/${matchCase}`);
}

const getOne = (brojZahteva) => {
    return axios.get(`${this.$store.state.host}/zahtev/htmlString/Zahtev${brojZahteva}.xml`);
}

const downloadZahtevPDF = (broj) => {
    axios({
        url: `${this.$store.state.host}/zahtev/pdf/Zahtev${broj}.xml`,
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
        url: `${this.$store.state.host}/zahtev/html/Zahtev${broj}.xml`,
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
        url: `${this.$store.state.host}/resenje/report`,
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

export default { osnovnaPretraga, getOne, downloadZahtevPDF, downloadZahtevHTML, generateReport }