<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE stylesheet [
        <!ENTITY nbsp "&#160;">
        ]>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:z1="http://www.ftn.com/z1" version="2.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Z1</title>
                <style type="text/css">
                    body {
                    font-family: Arial, Helvetica, sans-serif;
                    }
                    .padded {
                    padding: 10px;
                    }
                    .border-bot {
                    border-bottom: 1px solid black;
                    }
                    .border-r {
                    border-right: 1px solid black;
                    }
                    h1, h2, h3, h4 {
                    text-align: center;
                    }
                    table {
                    margin-left: auto;
                    margin-right: auto;
                    width: 90%;
                    }
                    table, td, th {
                    border-collapse: collapse;
                    }
                    table td, table th {
                    border: 1px solid black;
                    }
                    table tr:first-child th {
                    border-top: 0;
                    }
                    table tr:last-child td {
                    border-bottom: 0;
                    }
                    table tr td:first-child,
                    table tr th:first-child {
                    border-left: 0;
                    }
                    table tr td:last-child,
                    table tr th:last-child {
                    border-right: 0;
                    }
                    .shifted {
                    margin-top: 40px;
                    }
                    td {
                    padding: 10px;
                    font-size: 16px;
                    }
                    th {
                    font-size: 16px;
                    padding: 10px;
                    }
                    td.name {
                    text-align: center;
                    font-size: 18px;
                    }
                    .aligned-right {
                    text-align: right;
                    padding-right: 20px;
                    }
                    p {
                    margin: 0;
                    }
                </style>
            </head>
            <body>
                <h3 style="margin-top: 20px">ZAHTEV ZA PRIZNANJE ŽIGA</h3>
                <h4>Zavod za intelektualnu svojinu, Kneginje Ljubice 5, 11000 Beograd</h4>

                <xsl:variable name="cnt1" select="2 + count(//z1:Punomocnik)"/>
                <xsl:variable name="cnt2" select="$cnt1 + count(//z1:Punomocnik)"/>
                <xsl:variable name="cnt3" select="$cnt2 + 2 + count(//z1:Transliteracija)"/>
                <xsl:variable name="cnt4" select="$cnt3 + count(//z1:Prevod)"/>
                <xsl:variable name="rows" select="2 + count(//z1:Transliteracija) + count(//z1:Prevod)"/>

                <div style="border-top: 1px solid black; border-right: 1px solid black; border-left: 1px solid black; width: 90%; margin: auto">
                    <div class="border-bot">
                        <p class="border-bot padded">
                            <strong>
                                1. Podnosioci prijave:
                            </strong>
                        </p>
                        <table style="margin: 10px auto">
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
                                                <td class="name" rowspan="3" style="border-bottom: 0px">
                                                    <xsl:value-of select="z1:Poslovno_ime"/>
                                                </td>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <td class="name" rowspan="3" style="border-bottom: 0px"><xsl:value-of
                                                        select="z1:Ime"/>&nbsp;<xsl:value-of
                                                        select="z1:Prezime"/>
                                                </td>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                        <td>
                                            <xsl:value-of select="z1:Adresa/z1:Ulica"/>&nbsp;<xsl:value-of
                                                select="z1:Adresa/z1:Broj"/>
                                        </td>
                                        <td>tel:
                                            <xsl:value-of select="z1:Telefon"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <xsl:value-of select="z1:Adresa/z1:Postanski_broj"/>&nbsp;<xsl:value-of
                                                select="z1:Adresa/z1:Mesto"/>
                                        </td>
                                        <td>email:
                                            <xsl:value-of select="z1:E_mail"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <xsl:value-of select="z1:Adresa/z1:Drzava"/>
                                        </td>
                                        <td>faks:
                                            <xsl:value-of select="z1:Faks"/>
                                        </td>
                                    </tr>
                                </xsl:for-each>
                            </tbody>
                        </table>
                    </div>

                    <xsl:if test="//z1:Punomocnik">
                        <div class="border-bot">
                            <p class="border-bot padded">
                                <strong>
                                    2. Punomoćnik:
                                </strong>
                            </p>
                            <table style="margin: 10px auto">
                                <thead>
                                    <th style="width: 25%">Ime i prezime/Poslovno ime</th>
                                    <th style="width: 32%">Adresa</th>
                                    <th>Kontakt</th>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="name" rowspan="3" style="border-bottom: 0px">
                                            <xsl:choose>
                                                <xsl:when test="//z1:Punomocnik/z1:Poslovno_ime">
                                                    <xsl:value-of select="//z1:Punomocnik/z1:Poslovno_ime"/>
                                                </xsl:when>
                                                <xsl:otherwise>
                                                    <xsl:value-of select="//z1:Punomocnik/z1:Ime"/>&nbsp;<xsl:value-of
                                                        select="//z1:Punomocnik/z1:Prezime"/>
                                                </xsl:otherwise>
                                            </xsl:choose>
                                        </td>
                                        <td>
                                            <xsl:value-of select="//z1:Punomocnik/z1:Adresa/z1:Ulica"/>&nbsp;<xsl:value-of
                                                select="//z1:Punomocnik/z1:Adresa/z1:Broj"/>
                                        </td>
                                        <td>tel:
                                            <xsl:value-of select="//z1:Punomocnik/z1:Telefon"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <xsl:value-of select="//z1:Punomocnik/z1:Adresa/z1:Postanski_broj"/>&nbsp;<xsl:value-of
                                                select="//z1:Punomocnik/z1:Adresa/z1:Mesto"/>
                                        </td>
                                        <td>email:
                                            <xsl:value-of select="//z1:Punomocnik/z1:E_mail"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <xsl:value-of select="//z1:Punomocnik/z1:Adresa/z1:Drzava"/>
                                        </td>
                                        <td>faks:
                                            <xsl:value-of select="//z1:Punomocnik/z1:Faks"/>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </xsl:if>


                    <xsl:if test="//z1:Zajednicki_predstavnik">
                        <div class="border-bot">
                            <p class="border-bot padded">
                                <strong>
                                    <xsl:value-of select="$cnt1"/>. Zajednički predstavnik:
                                </strong>
                            </p>
                            <table style="margin: 10px auto">
                                <thead>
                                    <th style="width: 25%">Ime i prezime/Poslovno ime</th>
                                    <th style="width: 32%">Adresa</th>
                                    <th>Kontakt</th>
                                </thead>
                                <tbody>
                                    <tr>
                                        <xsl:choose>
                                            <xsl:when test="//z1:Zajednicki_predstavnik/z1:Poslovno_ime">
                                                <td class="name" rowspan="3" style="border-bottom: 0px">
                                                    <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Poslovno_ime"/>
                                                </td>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <td class="name" rowspan="3" style="border-bottom: 0px">
                                                    <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Ime"/>&nbsp;<xsl:value-of
                                                        select="//z1:Zajednicki_predstavnik/z1:Prezime"/>
                                                </td>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                        <td>
                                            <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Adresa/z1:Ulica"/>&nbsp;<xsl:value-of
                                                select="//z1:Zajednicki_predstavnik/z1:Adresa/z1:Broj"/>
                                        </td>
                                        <td>tel:
                                            <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Telefon"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <xsl:value-of
                                                    select="//z1:Zajednicki_predstavnik/z1:Adresa/z1:Postanski_broj"/>&nbsp;<xsl:value-of
                                                select="//z1:Zajednicki_predstavnik/z1:Adresa/z1:Mesto"/>
                                        </td>
                                        <td>email:
                                            <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:E_mail"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Adresa/z1:Drzava"/>
                                        </td>
                                        <td>faks:
                                            <xsl:value-of select="//z1:Zajednicki_predstavnik/z1:Faks"/>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </xsl:if>

                    <table style="width: 100%">
                        <tbody>
                            <tr>
                                <td width="50%">
                                    <strong>
                                        <xsl:value-of select="$cnt2"/>. Prijava se podnosi za
                                    </strong>
                                </td>
                                <td rowspan="3">
                                    <strong>
                                        <xsl:value-of select="$cnt2 + 1"/>. Naznačenje boja iz kojih se znak sastoji:
                                    </strong>
                                    <ul>
                                        <xsl:for-each select="//z1:Boja">
                                            <li>
                                                <xsl:value-of select="current()"/>
                                            </li>
                                        </xsl:for-each>
                                    </ul>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <strong>a)</strong>
                                    <xsl:choose>
                                        <xsl:when test="//z1:Tip_ziga = 'INDIVIDUALNI_ZIG'">
                                            individualni žig
                                        </xsl:when>
                                        <xsl:when test="//z1:Tip_ziga = 'KOLEKTIVNI_ZIG'">
                                            kolektivni žig
                                        </xsl:when>
                                        <xsl:otherwise>
                                            žig garancije
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <strong>b)</strong>&nbsp;<xsl:value-of select="//z1:Vrsta_znaka"/>&nbsp;znak
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <strong>c) Izgled znaka:</strong>&nbsp;<xsl:value-of select="//z1:Izgled"/>
                                </td>
                                <td rowspan="{$rows}">
                                    <strong>
                                        <xsl:value-of select="$cnt4 + 1"/>. Brojevi klasa robe i usluga prema
                                        Ničanskoj klasifikaciji
                                    </strong>
                                    <ul>
                                        <xsl:for-each select="//z1:Nicanska_klasifikacija/z1:Klasa">
                                            <li>
                                                <xsl:value-of select="current()"/>
                                            </li>
                                        </xsl:for-each>
                                    </ul>
                                </td>
                            </tr>
                            <xsl:if test="//z1:Transliteracija">
                                <tr>
                                    <td>
                                        <strong>
                                            <xsl:value-of select="$cnt2 + 2"/>. Transliteracija znaka:
                                        </strong>
                                        <xsl:value-of select="//z1:Transliteracija"/>
                                    </td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="//z1:Prevod">
                                <tr>
                                    <td>
                                        <strong>
                                            <xsl:value-of select="$cnt3"/>. Prevod znaka
                                        </strong>
                                        <xsl:value-of select="//z1:Prevod"/>
                                    </td>
                                </tr>
                            </xsl:if>
                            <tr>
                                <td style="border-bottom: 1px solid black">
                                    <strong>
                                        <xsl:value-of select="$cnt4"/>. Opis znaka
                                    </strong>
                                    <p class="padded">
                                        <xsl:value-of select="//z1:Opis"/>
                                    </p>
                                </td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="padded border-bot">
                        <p>
                            <strong>
                                <xsl:value-of select="$cnt4 + 2"/>. Zatraženo pravo prvenstva i osnov:
                            </strong>
                        </p>
                        <p class="padded">
                            <xsl:value-of select="//z1:Pravo_prvenstva"/>
                        </p>
                    </div>
                </div>

                <table class="shifted" style="border: 1px solid black">
                    <thead>
                        <th style="text-align: left" class="padded">
                            <strong><xsl:value-of select="$cnt4 + 3"/>. Plaćene takse
                            </strong>
                        </th>
                        <th class="padded">
                            <strong>Dinara</strong>
                        </th>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="padded">
                                <strong>Osnovna taksa</strong>
                            </td>
                            <td class="aligned-right padded">
                                <xsl:value-of select="//z1:Osnovna_taksa"/>
                            </td>
                        </tr>
                        <xsl:for-each select="//z1:Placene_takse/z1:Klasa">
                            <tr>
                                <td class="padded">
                                    <strong>Za &nbsp;<xsl:value-of select="z1:Naziv"/>
                                    </strong>
                                </td>
                                <td class="aligned-right padded">
                                    <xsl:value-of select="z1:Vrednost"/>
                                </td>
                            </tr>
                        </xsl:for-each>
                        <xsl:if test="//z1:Graficko_resenje">
                            <tr>
                                <td class="padded">
                                    <strong>Grafičko rešenje</strong>
                                </td>
                                <td class="aligned-right padded">
                                    <xsl:value-of select="//z1:Graficko_resenje"/>
                                </td>
                            </tr>
                        </xsl:if>
                        <tr>
                            <td class="padded">
                                <strong>UKUPNO</strong>
                            </td>
                            <td class="aligned-right padded">
                                <strong>
                                    <xsl:value-of select="//z1:Ukupno"/>
                                </strong>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <table class="shifted" style="border: 1px solid black">
                    <thead>
                        <th colspan="3">
                            <h3>Popunjava zavod</h3>
                        </th>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="2">
                                <strong>Prilozi uz zahtev</strong>
                            </td>
                            <td rowspan="9">
                                <div style="text-align: center; margin: 5px">
                                    <h3>Ž -
                                        <xsl:value-of select="//z1:Broj_prijave_ziga/z1:Id"/> /
                                        <xsl:value-of select="//z1:Broj_prijave_ziga/z1:Godina"/>
                                    </h3>
                                </div>
                                <div style="text-align: center">
                                    Datum podnošenja
                                </div>
                                <div style="text-align: center">
                                    <xsl:value-of select="//z1:Datum_podnosenja"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 30%">Primerak znaka</td>
                            <td>
                                <xsl:value-of select="//z1:Primerak_znaka"/>
                            </td>
                        </tr>
                        <xsl:if test="//z1:Punomocje">
                            <tr>
                                <td>Punomocje</td>
                                <td>
                                    <xsl:value-of select="//z1:Punomocje"/>
                                </td>
                            </tr>
                        </xsl:if>
                        <xsl:if test="//z1:Generalno_punomocje">
                            <tr>
                                <td>Generalno punomocje</td>
                                <td>
                                    <xsl:value-of select="//z1:Generalno_punomocje"/>
                                </td>
                            </tr>
                        </xsl:if>
                        <xsl:if test="//z1:Opsti_akt">
                            <tr>
                                <td>Opšti akt o kolektivnom žigu/žigu garancije</td>
                                <td>
                                    <xsl:value-of select="//z1:Opsti_akt"/>
                                </td>
                            </tr>
                        </xsl:if>
                        <tr>
                            <td>Dokaz o pravu prvenstva</td>
                            <td>
                                <xsl:value-of select="//z1:Dokaz_o_pravu_prvenstva"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Dokaz o uplati takse</td>
                            <td>
                                <xsl:value-of select="//z1:Dokaz_o_uplati_takse"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Spisak robe i usluga</td>
                            <td>
                                <ul>
                                    <xsl:for-each select="//z1:Roba_ili_usluga">
                                        <li>
                                            <xsl:value-of select="current()"/>
                                        </li>
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
