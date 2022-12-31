<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE stylesheet [
        <!ENTITY nbsp "&#160;">
        ]>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:z1="http://www.ftn.com/z1" version="2.0">
    <xsl:template match="/" >
        <html >
            <head>
                <title>Z1</title>
                <style type="text/css">
                    body {
                        font-family: Arial, Helvetica, sans-serif;
                    }

                    h1, h2, h3 {
                        text-align: center;
                    }

                    table {
                        margin-left: auto;
                        margin-right: auto;
                        width: 90%;
                    }

                    table, th, td {
                        border-collapse: collapse;
                        border: 1px solid black;
                    }

                    .shifted {
                        margin-top: 40px;
                    }

                    td {
                        padding-left: 10px;
                        font-size: 16px;
                    }

                    th {
                        font-size: 16px;
                    }

                    td.name {
                        text-align: center;
                        font-size: 18px;
                    }

                    td:hover {
                        font-style: italic;
                        background-color: #cbd4f2;
                    }

                    thead {
                        background-color: #9baefa;
                    }

                    .aligned-right {
                        text-align: right;
                        padding-right: 20px;
                    }

                    td {
                        padding-top: 3px;
                        padding-bottom: 3px;
                    }
                </style>
            </head>
            <body>
                <h1>ZAHTEV ZA PRIZNANJE ŽIGA</h1>
                <h3>Zavod za intelektualnu svojinu, Kneginje Ljubice 5, 11000 Beograd</h3>
                <div>
                    <h2 class="shifted">Podnosioci prijave</h2>
                    <table>
                        <thead>
                            <th style="width: 25%">Ime i prezime/Poslovno ime</th>
                            <th style="width: 32%">Adresa</th>
                            <th>Kontakt</th>
                        </thead>
                        <tbody>
                            <xsl:for-each select="z1:Zahtev//z1:Podnosilac_prijave">
                                <tr>
                                    <xsl:choose>
                                        <xsl:when test="z1:Poslovno_ime">
                                            <td class="name" rowspan="3"><xsl:value-of select="z1:Poslovno_ime"/></td>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <td class="name" rowspan="3"><xsl:value-of select="z1:Ime"/>&nbsp;<xsl:value-of select="z1:Prezime"/></td>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                    <td>
                                        <xsl:value-of select="z1:Adresa/z1:Ulica"/>&nbsp;<xsl:value-of select="z1:Adresa/z1:Broj"/>
                                    </td>
                                    <td>tel: <xsl:value-of select="z1:Telefon"/></td>
                                </tr>
                                <tr>
                                    <td>
                                        <xsl:value-of select="z1:Adresa/z1:Postanski_broj"/>&nbsp;<xsl:value-of select="z1:Adresa/z1:Mesto"/>
                                    </td>
                                    <td>email: <xsl:value-of select="z1:E_mail"/></td>
                                </tr>
                                <tr>
                                    <td><xsl:value-of select="z1:Adresa/z1:Drzava"/></td>
                                    <td>faks: <xsl:value-of select="z1:Faks"/></td>
                                </tr>
                            </xsl:for-each>
                        </tbody>
                    </table>
                </div>

                <xsl:if test="//z1:Punomocnik">
                    <div>
                        <h2 class="shifted">Punomoćnik</h2>
                        <table>
                            <thead>
                                <th style="width: 25%">Ime i prezime/Poslovno ime</th>
                                <th style="width: 32%">Adresa</th>
                                <th>Kontakt</th>
                            </thead>
                            <tbody>
                                <tr>
                                    <xsl:choose>
                                        <xsl:when test="//z1:Punomocnik/z1:Poslovno_ime">
                                            <td class="name" rowspan="3"><xsl:value-of select="//z1:Punomocnik/z1:Poslovno_ime"/></td>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <td class="name" rowspan="3"><xsl:value-of select="//z1:Punomocnik/z1:Ime"/>&nbsp;<xsl:value-of select="//z1:Punomocnik/z1:Prezime"/></td>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                    <td>
                                        <xsl:value-of select="//z1:Punomocnik/z1:Adresa/z1:Ulica"/>&nbsp;<xsl:value-of select="//z1:Punomocnik/z1:Adresa/z1:Broj"/>
                                    </td>
                                    <td>tel: <xsl:value-of select="//z1:Punomocnik/z1:Telefon"/></td>
                                </tr>
                                <tr>
                                    <td>
                                        <xsl:value-of select="//z1:Punomocnik/z1:Adresa/z1:Postanski_broj"/>&nbsp;<xsl:value-of select="//z1:Punomocnik/z1:Adresa/z1:Mesto"/>
                                    </td>
                                    <td>email: <xsl:value-of select="//z1:Punomocnik/z1:E_mail"/></td>
                                </tr>
                                <tr>
                                    <td><xsl:value-of select="//z1:Punomocnik/z1:Adresa/z1:Drzava"/></td>
                                    <td>faks: <xsl:value-of select="//z1:Punomocnik/z1:Faks"/></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </xsl:if>

                <xsl:if test="//z1:Zajednicki_predstavnik">
                    <div>
                        <h2 class="shifted">Zajednički predstavnik</h2>
                        <table>
                            <thead>
                                <th style="width: 25%">Ime i prezime/Poslovno ime</th>
                                <th style="width: 32%">Adresa</th>
                                <th>Kontakt</th>
                            </thead>
                            <tbody>
                                <tr>
                                    <xsl:choose>
                                        <xsl:when test="//z1:Zajednicki_predstavnik/z1:Poslovno_ime">
                                            <td class="name" rowspan="3">
                                                <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Poslovno_ime"/>
                                            </td>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <td class="name" rowspan="3">
                                                <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Ime"/>&nbsp;<xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Prezime"/>
                                            </td>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                    <td>
                                        <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Adresa/z1:Ulica"/>&nbsp;<xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Adresa/z1:Broj"/>
                                    </td>
                                    <td>tel: <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Telefon"/></td>
                                </tr>
                                <tr>
                                    <td>
                                        <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Adresa/z1:Postanski_broj"/>&nbsp;<xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Adresa/z1:Mesto"/>
                                    </td>
                                    <td>email: <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:E_mail"/></td>
                                </tr>
                                <tr>
                                    <td><xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Adresa/z1:Drzava"/></td>
                                    <td>faks: <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Faks"/></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </xsl:if>

                <table class="shifted">
                    <thead>
                        <th colspan="3"><h3>Podaci o žigu</h3></th>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="3" style="text-align: center">
                                <xsl:choose>
                                    <xsl:when test="//z1:Tip_ziga = 'INDIVIDUALNI_ZIG'">
                                        INDIVIDUALNI ŽIG
                                    </xsl:when>
                                    <xsl:when test="//z1:Tip_ziga = 'KOLEKTIVNI_ZIG'">
                                        KOLEKTIVNI ŽIG
                                    </xsl:when>
                                    <xsl:otherwise>
                                        ŽIG GARANCIJE
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 30%"><strong>Vrsta znaka</strong></td>
                            <td style="width: 30%"><xsl:value-of select="//z1:Vrsta_znaka" /></td>
                            <td rowspan="2">
                                <strong>Boje:</strong>
                                <ul>
                                    <xsl:for-each select="//z1:Boja">
                                        <li><xsl:value-of select="current()"/></li>
                                    </xsl:for-each>
                                </ul>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>Opis znaka</strong></td>
                            <td><xsl:value-of select="//z1:Opis" /></td>
                        </tr>
                        <tr>
                            <td><strong>Izgled znak</strong></td>
                            <td><xsl:value-of select="//z1:Izgled" /></td>
                            <td rowspan="2">
                                <strong>Klase robe i usluga prema Ničanskoj klasifikaciji:</strong>
                                <ul>
                                    <xsl:for-each select="//z1:Nicanska_klasifikacija/z1:Klasa">
                                        <li><xsl:value-of select="current()"/></li>
                                    </xsl:for-each>
                                </ul>
                            </td>
                        </tr>
                        <xsl:if test="//z1:Prevod">
                            <tr>
                                <td><strong>Prevod</strong></td>
                                <td><xsl:value-of select="//z1:Prevod" /></td>
                            </tr>
                        </xsl:if>
                        <xsl:if test="//z1:Transliteracija">
                            <tr>
                                <td><strong>Transliteracija</strong></td>
                                <td><xsl:value-of select="//z1:Transliteracija" /></td>
                            </tr>
                        </xsl:if>
                    </tbody>
                </table>

                <table class="shifted">
                    <thead>
                        <th><h3>Plaćene takse</h3></th>
                        <th><h3>Dinara</h3></th>
                    </thead>
                    <tbody>
                        <tr>
                            <td><h4>Osnovna taksa</h4></td>
                            <td class="aligned-right"><xsl:value-of select="//z1:Osnovna_taksa" /></td>
                        </tr>
                        <xsl:for-each select="//z1:Placene_takse/z1:Klasa">
                            <tr>
                                <td><h4>Za &nbsp;<xsl:value-of select="z1:Naziv"/></h4></td>
                                <td class="aligned-right"><xsl:value-of select="z1:Vrednost"/></td>
                            </tr>
                        </xsl:for-each>
                        <xsl:if test="//z1:Graficko_resenje">
                            <tr>
                                <td><h4>Grafičko rešenje</h4></td>
                                <td class="aligned-right"><xsl:value-of select="//z1:Graficko_resenje"/></td>
                            </tr>
                        </xsl:if>
                        <tr>
                            <td><h4>UKUPNO</h4></td>
                            <td class="aligned-right"><strong><xsl:value-of select="//z1:Ukupno"/></strong></td>
                        </tr>
                    </tbody>
                </table>

                <table class="shifted">
                    <thead>
                        <th colspan="3"><h2>Popunjava zavod</h2></th>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="2"><strong>Prilozi uz zahtev</strong></td>
                            <td rowspan="9">
                                <div style="text-align: center; margin: 5px">
                                    <h3>Ž - <xsl:value-of select="//z1:Broj_prijave_ziga/z1:Id" /> /
                                        <xsl:value-of select="//z1:Broj_prijave_ziga/z1:Godina" /></h3>
                                </div>
                                <div style="text-align: center">
                                    Datum podnošenja
                                </div>
                                <div style="text-align: center">
                                    <xsl:value-of select="//z1:Datum_podnosenja" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Primerak znaka</td>
                            <td><xsl:value-of select="//z1:Primerak_znaka"/></td>
                        </tr>
                        <xsl:if test="//z1:Punomocje">
                            <tr>
                                <td>Punomocje</td>
                                <td><xsl:value-of select="//z1:Punomocje"/></td>
                            </tr>
                        </xsl:if>
                        <xsl:if test="//z1:Generalno_punomocje">
                            <tr>
                                <td>Generalno punomocje</td>
                                <td><xsl:value-of select="//z1:Generalno_punomocje"/></td>
                            </tr>
                        </xsl:if>
                        <xsl:if test="//z1:Opsti_akt">
                            <tr>
                                <td>Opšti akt o kolektivnom žigu/žigu garancije</td>
                                <td><xsl:value-of select="//z1:Opsti_akt"/></td>
                            </tr>
                        </xsl:if>
                        <tr>
                            <td>Dokaz o pravu prvenstva</td>
                            <td><xsl:value-of select="//z1:Dokaz_o_pravu_prvenstva"/></td>
                        </tr>
                        <tr>
                            <td>Dokaz o uplati takse</td>
                            <td><xsl:value-of select="//z1:Dokaz_o_uplati_takse"/></td>
                        </tr>
                        <tr>
                            <td>Spisak robe i usluga</td>
                            <td>
                                <ul>
                                    <xsl:for-each select="//z1:Roba_ili_usluga">
                                        <li><xsl:value-of select="current()"/></li>
                                    </xsl:for-each>
                                </ul>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
