<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE stylesheet [
        <!ENTITY nbsp "&#160;">
        ]>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p1="http://www.ftn.com/p1" version="2.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>P1</title>
                <style type="text/css">
                    body {
                    font-family: Arial, Helvetica, sans-serif;
                    }
                    table {
                    margin-left: auto;
                    margin-right: auto;
                    width: 85%;
                    }
                    .shifted {
                    margin-top: 40px;
                    }
                    td {
                    padding-left: 10px;
                    font-size: 16px;
                    padding-top: 5px;
                    padding-bottom: 5px;
                    }
                    th {
                    font-size: 16px;
                    padding-top: 5px;
                    padding-bottom: 5px;
                    }
                    td.name {
                    text-align: center;
                    font-size: 18px;
                    }
                    table, td, th {
                    border: 1px solid black;
                    border-collapse: collapse;
                    }
                    td:hover {
                    font-style: italic;
                    background-color: #cbd4f2;
                    }
                    thead {
                    background-color: #9baefa;
                    }
                    .centered {
                    text-align: center;
                    }
                </style>
            </head>
            <body>
                <div class="margin-bottom: 40px">
                    <table style="margin-left: 10px; width: 60%; float: left;">
                        <thead>
                            <th colspan="2">Popunjava zavod</th>
                        </thead>
                        <tbody>
                            <tr>
                                <td colspan="2" style="padding: 10px 0 10px 10px">
                                    <span>Broj prijave:</span>
                                    <span style="font-weight: bold; font-size: 20px;">
                                        P -
                                        <xsl:value-of select="//p1:Popunjava_zavod/p1:Broj_prijave"/>
                                    </span>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding: 10px 0 0 10px">
                                    <span style="margin-top: 10px">Datum prijema:</span>
                                    <p style="font-size: 18px;">
                                        <xsl:value-of select="//p1:Popunjava_zavod/p1:Datum_prijema"/>
                                    </p>
                                </td>
                                <td style="padding: 10px 0 0 10px">
                                    <span style="margin-top: 10px">Priznati datum podnošenja:</span>
                                    <p style="font-size: 18px;">
                                        <xsl:value-of select="//p1:Popunjava_zavod/p1:Datum_podnosenja"/>
                                    </p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div style="float: left; width: 40%; text-align: right;">
                        <h4>OBRAZAC P-1</h4>
                    </div>
                </div>

                <h1 class="shifted centered" style="padding-top: 40px; clear: both;">
                    <xsl:value-of select="//p1:Naziv_pronalaska[@jezik='srpski']"/>
                </h1>
                <h2 class="centered" style="font-style: italic">
                    <xsl:value-of select="//p1:Naziv_pronalaska[@jezik='engleski']"/>
                </h2>

                <h2 class="shifted centered">Podnosilac prijave</h2>
                <table>
                    <thead>
                        <xsl:choose>
                            <xsl:when test="//p1:Podnosilac_prijave/p1:Ime">
                                <th>Ime i prezime</th>
                            </xsl:when>
                            <xsl:otherwise>
                                <th>Poslovno ime</th>
                            </xsl:otherwise>
                        </xsl:choose>
                        <th>Adresa</th>
                        <xsl:if test="//p1:Podnosilac_prijave/p1:Ime">
                            <th>Državljanstvo</th>
                        </xsl:if>
                        <th>Kontakt</th>
                    </thead>
                    <tbody>
                        <tr>
                            <td rowspan="3" class="name">
                                <xsl:choose>
                                    <xsl:when test="//p1:Podnosilac_prijave/p1:Ime">
                                        <xsl:value-of select="//p1:Podnosilac_prijave/p1:Ime"/>&nbsp;<xsl:value-of
                                            select="//p1:Podnosilac_prijave/p1:Prezime"/>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="//p1:Podnosilac_prijave/p1:Poslovno_ime"/>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td>
                                <xsl:value-of select="//p1:Podnosilac_prijave//p1:Ulica"/>&nbsp;<xsl:value-of
                                    select="//p1:Podnosilac_prijave//p1:Broj"/>
                            </td>
                            <xsl:if test="//p1:Podnosilac_prijave/p1:Ime">
                                <td rowspan="3">
                                    <xsl:value-of select="//p1:Podnosilac_prijave/p1:Drzavljanstvo"/>
                                </td>
                            </xsl:if>
                            <td>tel:&nbsp;<xsl:value-of select="//p1:Podnosilac_prijave/p1:Broj_telefona"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <xsl:value-of select="//p1:Podnosilac_prijave//p1:Postanski_broj"/>&nbsp;<xsl:value-of
                                    select="//p1:Podnosilac_prijave//p1:Mesto"/>
                            </td>
                            <td>email:&nbsp;<xsl:value-of select="//p1:Podnosilac_prijave/p1:E_posta"/>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;<xsl:value-of select="//p1:Podnosilac_prijave//p1:Drzava"/>
                            </td>
                            <td>faks:&nbsp;<xsl:value-of select="//p1:Podnosilac_prijave/p1:Broj_faksa"/>
                            </td>
                        </tr>
                        <xsl:if test="//p1:Podnosilac_prijave/@pronalazac='true'">
                            <tr style="border: 1px solid white">
                                <td colspan="4" style="border: 1px solid white">*Podnosilac prijave je pronalazač</td>
                            </tr>
                        </xsl:if>
                    </tbody>
                </table>

                <xsl:if test="//p1:Pronalazac">
                    <h2 class="shifted centered">Pronalazač</h2>
                    <table>
                        <thead>
                            <th>Ime i prezime</th>
                            <th>Adresa</th>
                            <th>Kontakt</th>
                        </thead>
                        <tbody>
                            <tr>
                                <td rowspan="3" class="name">
                                    <xsl:value-of select="//p1:Pronalazac/p1:Ime"/>&nbsp;<xsl:value-of
                                        select="//p1:Pronalazac/p1:Prezime"/>
                                </td>
                                <td>
                                    <xsl:value-of select="//p1:Pronalazac//p1:Ulica"/>&nbsp;<xsl:value-of
                                        select="//p1:Pronalazac//p1:Broj"/>
                                </td>
                                <td>
                                    tel:&nbsp;<xsl:value-of select="//p1:Pronalazac/p1:Broj_telefona"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <xsl:value-of select="//p1:Pronalazac//p1:Postanski_broj"/>&nbsp;<xsl:value-of
                                        select="//p1:Pronalazac//p1:Mesto"/>
                                </td>
                                <td>
                                    email:&nbsp;<xsl:value-of select="//p1:Pronalazac/p1:E_posta"/>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;<xsl:value-of select="//p1:Pronalazac//p1:Drzava"/>
                                </td>
                                <td>
                                    faks:&nbsp;<xsl:value-of select="//p1:Pronalazac/p1:Broj_faksa"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </xsl:if>

                <h2 class="shifted centered">
                    <xsl:choose>
                        <xsl:when test="//p1:Punomocnik/@vrsta='PUNOMOCNIK_ZA_ZASTUPANJE'">
                            Punomoćnik za zastupanje
                        </xsl:when>
                        <xsl:when test="//p1:Punomocnik/@vrsta='PUNOMOCNIK_ZA_PRIJEM_PISMENA'">
                            Punomoćnik za prijem pismena
                        </xsl:when>
                        <xsl:otherwise>
                            Zajednički predstavnik
                        </xsl:otherwise>
                    </xsl:choose>
                </h2>
                <table>
                    <thead>
                        <th>
                            <xsl:choose>
                                <xsl:when test="//p1:Punomocnik/p1:Ime">
                                    Ime i prezime
                                </xsl:when>
                                <xsl:otherwise>
                                    Poslovno ime
                                </xsl:otherwise>
                            </xsl:choose>
                        </th>
                        <th>Adresa</th>
                        <th>Kontakt</th>
                    </thead>
                    <tbody>
                        <tr>
                            <td rowspan="2" class="name">
                                <xsl:choose>
                                    <xsl:when test="//p1:Punomocnik/p1:Ime">
                                        <xsl:value-of select="//p1:Punomocnik/p1:Ime"/>&nbsp;<xsl:value-of
                                            select="//p1:Punomocnik/p1:Prezime"/>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="//p1:Punomocnik/p1:Poslovno_ime"/>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td>
                                <xsl:value-of select="//p1:Punomocnik//p1:Ulica"/>&nbsp;<xsl:value-of
                                    select="//p1:Punomocnik//p1:Broj"/>
                            </td>
                            <td>
                                tel:&nbsp;<xsl:value-of select="//p1:Punomocnik/p1:Broj_telefona"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <xsl:value-of select="//p1:Punomocnik//p1:Postanski_broj"/>&nbsp;<xsl:value-of
                                    select="//p1:Punomocnik//p1:Mesto"/>
                            </td>
                            <td>
                                email:&nbsp;<xsl:value-of select="//p1:Punomocnik/p1:E_posta"/>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <xsl:if test="//p1:Zahtev_za_priznanje_prvenstva">
                    <h2 class="shifted centered">
                        Zahtev za priznanje prava prvenstva iz ranijih prijava
                    </h2>
                    <table>
                        <thead>
                            <th>Datum podnošenja ranije prijave</th>
                            <th>Broj ranije prijave</th>
                            <th style="width: 30%">
                                Dvoslovna oznaka države, regionalne ili međunarodne organizacije
                            </th>
                        </thead>
                        <tbody>
                            <xsl:for-each select="//p1:Ranija_prijava">
                                <tr>
                                    <td class="centered">
                                        <xsl:value-of select="p1:Datum_podnosenja"/>
                                    </td>
                                    <td class="centered">
                                        <xsl:value-of select="p1:Broj"/>
                                    </td>
                                    <td class="centered">
                                        <xsl:value-of select="p1:Oznaka"/>
                                    </td>
                                </tr>
                            </xsl:for-each>
                        </tbody>
                    </table>
                </xsl:if>

                <div class="shifted" style="margin-left: 20px">
                    <h3>Podaci o dostavljanju</h3>
                    <ul>
                        <li>
                            Adresa:&nbsp;<xsl:value-of
                                select="//p1:Podaci_o_dostavljanju//p1:Ulica"/>&nbsp;<xsl:value-of
                                select="//p1:Podaci_o_dostavljanju//p1:Broj"/>, &nbsp;<xsl:value-of
                                select="//p1:Podaci_o_dostavljanju//p1:Postanski_broj"/>&nbsp;<xsl:value-of
                                select="//p1:Podaci_o_dostavljanju//p1:Mesto"/>
                        </li>
                        <li>Način:&nbsp;<xsl:value-of select="//p1:Podaci_o_dostavljanju/p1:Nacin"/>
                        </li>
                    </ul>
                </div>

                <xsl:if test="//p1:Zahtev/@tip_prijave='DOPUNSKA'">
                    <div class="shifted" style="margin-left: 20px">
                        <h3>Podaci o prvobitnoj prijavi</h3>
                        <ul>
                            <li>
                                Broj prvobitne prijave:&nbsp;<xsl:value-of select="//p1:Prvobitna_prijava/p1:Broj"/>
                            </li>
                            <li>Datum podnošenja prvobitne prijave:&nbsp;<xsl:value-of
                                    select="//p1:Prvobitna_prijava/p1:Datum_podnosenja"/>
                            </li>
                        </ul>
                    </div>
                </xsl:if>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>