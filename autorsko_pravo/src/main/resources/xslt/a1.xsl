<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE stylesheet [
        <!ENTITY nbsp "&#160;">
        ]>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:a1="http://www.ftn.com/a1" version="2.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>A1</title>
                <style type="text/css">
                    body {
                    font-family: Arial, Helvetica, sans-serif;
                    }
                    table {
                    margin-left: auto;
                    margin-right: auto;
                    width: 80%;
                    }
                    .shifted-top-l {
                    margin-top: 45px;
                    }
                    .shifted-top-sm {
                    margin-top: 20px;
                    }
                    .shifted-left {
                    margin-left: 0;
                    }
                    td {
                    padding-left: 10px;
                    font-size: 16px;
                    padding-top: 3px;
                    padding-bottom: 3px;
                    }
                    th {
                    font-size: 16px;
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
                    }
                    .centered {
                    text-align: center
                    }
                </style>
            </head>
            <body>
                <div style="width: 95%; border: 1px solid black; margin: auto">
                    <div style="border-bottom: 1px solid black; padding: 5px">
                        <div style="width: 100%; margin-left: 10px">
                            <div style="display: inline-block; width: 70%">
                                <h3>ZAVOD ZA INTELEKTUALNU SVOJINU</h3>
                                <h4>Beograd, Kneginje Ljubice 5</h4>
                            </div>
                            <div style="display: inline-block; text-align: right;">
                                <h3>OBRAZAC A-1</h3>
                            </div>
                        </div>
                        <h3 style="text-align: center; padding-bottom: 30px">ZAHTEV ZA UNOŠENJE U EVIDENCIJU I
                            DEPONOVANJE AUTORSKIH DELA
                        </h3>
                    </div>
                    <div style="padding: 10px">
                        <div class="shifted-top-sm">
                            <p class="shifted-left">1) Podnosilac prijave:</p>
                            <table class="shifted-top-sm">
                                <xsl:choose>
                                    <xsl:when test="//a1:Podnosilac/a1:Poslovno_ime">
                                        <thead>
                                            <th>Poslovno ime</th>
                                            <th>Sedište</th>
                                            <th>Kontakt</th>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td rowspan="2" class="name">
                                                    <xsl:value-of select="//a1:Podnosilac/a1:Poslovno_ime"/>
                                                </td>
                                                <td>
                                                    <xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Ulica"/>&nbsp;<xsl:value-of
                                                        select="//a1:Podnosilac/a1:Adresa/a1:Broj"/>
                                                </td>
                                                <td>tel: &nbsp;<xsl:value-of select="//a1:Podnosilac/a1:Telefon"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Postanski_broj"/>&nbsp;<xsl:value-of
                                                        select="//a1:Podnosilac/a1:Adresa/a1:Mesto"/>
                                                </td>
                                                <td>email: &nbsp;<xsl:value-of select="//a1:Podnosilac/a1:E_mail"/>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </xsl:when>

                                    <xsl:when test="//a1:Podnosilac/a1:Drzavljanstvo">
                                        <thead>
                                            <th>Ime i prezime</th>
                                            <xsl:if test="//a1:Podnosilac/a1:Pseudonim">
                                                <th>Pseudonim</th>
                                            </xsl:if>
                                            <th>Adresa</th>
                                            <th>Državljanstvo</th>
                                            <th>Kontakt</th>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td rowspan="2" class="name">
                                                    <xsl:value-of select="//a1:Podnosilac/a1:Ime"/>&nbsp;<xsl:value-of
                                                        select="//a1:Podnosilac/a1:Prezime"/>
                                                </td>
                                                <xsl:if test="//a1:Podnosilac/a1:Pseudonim">
                                                    <td rowspan="2">
                                                        <xsl:value-of select="//a1:Podnosilac/a1:Pseudonim"/>
                                                    </td>
                                                </xsl:if>
                                                <td>
                                                    <xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Ulica"/>&nbsp;<xsl:value-of
                                                        select="//a1:Podnosilac/a1:Adresa/a1:Broj"/>
                                                </td>
                                                <td rowspan="2">
                                                    <xsl:value-of select="//a1:Podnosilac/a1:Drzavljanstvo"/>
                                                </td>
                                                <td>tel: &nbsp;<xsl:value-of select="//a1:Podnosilac/a1:Telefon"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Postanski_broj"/>&nbsp;<xsl:value-of
                                                        select="//a1:Podnosilac/a1:Adresa/a1:Mesto"/>
                                                </td>
                                                <td>email: &nbsp;<xsl:value-of select="//a1:Podnosilac/a1:E_mail"/>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </xsl:when>

                                    <xsl:otherwise>
                                        <thead>
                                            <th>Ime i prezime</th>
                                            <th>Adresa</th>
                                            <th>Kontakt</th>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td rowspan="2" class="name">
                                                    <xsl:value-of select="//a1:Podnosilac/a1:Ime"/>&nbsp;<xsl:value-of
                                                        select="//a1:Podnosilac/a1:Prezime"/>
                                                </td>
                                                <td>
                                                    <xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Ulica"/>&nbsp;<xsl:value-of
                                                        select="//a1:Podnosilac/a1:Adresa/a1:Broj"/>
                                                </td>
                                                <td>tel: &nbsp;<xsl:value-of select="//a1:Podnosilac/a1:Telefon"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Postanski_broj"/>&nbsp;<xsl:value-of
                                                        select="//a1:Podnosilac/a1:Adresa/a1:Mesto"/>
                                                </td>
                                                <td>email: &nbsp;<xsl:value-of select="//a1:Podnosilac/a1:E_mail"/>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </table>
                        </div>

                        <xsl:variable name="cnt1" select="2 + count(//a1:Pseudonim)"/>

                        <xsl:if test="//a1:Pseudonim">
                            <div>
                                <p class="shifted-left">2) Pseudonim ili znak autora</p>
                                <p class="centered">
                                    <xsl:value-of select="//a1:Pseudonim"/>
                                </p>
                            </div>
                        </xsl:if>

                        <div class="shifted-top-l">
                            <p class="shifted-left">
                                <xsl:value-of select="$cnt1"/>) Naslov autorskog dela, odnosno alternativni naslov, ako
                                ga ima, po kome autorsko delo može da se identifikuje:
                            </p>
                            <table>
                                <tbody>
                                    <tr>
                                        <td style="width:30%">
                                            <strong>Naslov</strong>
                                        </td>
                                        <td class="centered">
                                            <xsl:value-of select="//a1:Autorsko_delo//a1:Naslov"/>
                                        </td>
                                    </tr>
                                    <xsl:if test="//a1:Autorsko_delo//a1:Alternativni_naslov">
                                        <tr>
                                            <td style="width:30%">
                                                <strong>Alternativni naslov</strong>
                                            </td>
                                            <td class="centered">
                                                <xsl:value-of select="//a1:Autorsko_delo//a1:Alternativni_naslov"/>
                                            </td>
                                        </tr>
                                    </xsl:if>
                                </tbody>
                            </table>
                        </div>

                        <xsl:variable name="cnt2" select="$cnt1 + 1 + count(//a1:Izvorno_delo)"/>

                        <xsl:if test="//a1:Izvorno_delo">
                            <div class="shifted-top-l">
                                <p class="shifted-left"><xsl:value-of select="$cnt1 + 1"/>) Podaci o naslovu autorskog
                                    dela na kome se zasniva delo prerade, kao i podatak o autoru izvornog dela:
                                </p>
                                <table style="margin-top: 10px">
                                    <tbody>
                                        <tr>
                                            <td style="width: 30%">
                                                <strong>Naslov</strong>
                                            </td>
                                            <td class="centered">
                                                <xsl:value-of select="//a1:Izvorno_delo//a1:Naslov"/>
                                            </td>
                                        </tr>
                                        <xsl:if test="//a1:Izvorno_delo//a1:Alternativni_naslov">
                                            <tr>
                                                <td>
                                                    <strong>Alternativni naslov</strong>
                                                </td>
                                                <td class="centered">
                                                    <xsl:value-of select="//a1:Izvorno_delo//a1:Alternativni_naslov"/>
                                                </td>
                                            </tr>
                                        </xsl:if>
                                        <xsl:if test="//a1:Izvorno_delo//a1:Autori">
                                            <tr>
                                                <td colspan="2">
                                                    <p>
                                                        <strong>Autori:</strong>
                                                    </p>
                                                    <ul>
                                                        <xsl:for-each select="//a1:Izvorno_delo//a1:Autori/a1:Autor">
                                                            <li>
                                                                <p>
                                                                    <strong>
                                                                        <xsl:value-of select="a1:Ime"/>&nbsp;<xsl:value-of
                                                                            select="a1:Prezime"/>
                                                                    </strong>
                                                                </p>
                                                                <ul>
                                                                    <xsl:if test="a1:Pseudonim">
                                                                        <li>
                                                                            Pseudonim: &nbsp;<xsl:value-of
                                                                                select="a1:Pseudonim"/>
                                                                        </li>
                                                                    </xsl:if>

                                                                    <xsl:choose>
                                                                        <xsl:when test="a1:Adresa">
                                                                            <li>
                                                                                Adresa:
                                                                                <xsl:value-of
                                                                                        select="a1:Adresa/a1:Ulica"/>&nbsp;<xsl:value-of
                                                                                    select="a1:Adresa/a1:Broj"/>,&nbsp;<xsl:value-of
                                                                                    select="a1:Adresa/a1:Postanski_broj"/>&nbsp;<xsl:value-of
                                                                                    select="a1:Adresa/a1:Mesto"/>
                                                                            </li>
                                                                            <li>
                                                                                Državljanstvo:
                                                                                <xsl:value-of
                                                                                        select="a1:Drzavljanstvo"/>
                                                                            </li>
                                                                        </xsl:when>
                                                                        <xsl:otherwise>
                                                                            <li>Godina smrti: &nbsp;<xsl:value-of
                                                                                    select="a1:Godina_smrti"/>
                                                                            </li>
                                                                        </xsl:otherwise>
                                                                    </xsl:choose>
                                                                </ul>
                                                            </li>
                                                        </xsl:for-each>
                                                    </ul>
                                                </td>
                                            </tr>
                                        </xsl:if>
                                    </tbody>
                                </table>
                            </div>
                        </xsl:if>

                        <div class="shifted-top-l">
                            <p class="shifted-left"><xsl:value-of select="$cnt2"/>) Podaci o vrsti autorskog dela
                                (književno
                                delo, muzičko delo, likovno delo, računarski program i dr.):
                                <span style="font-size: 18px">
                                    <strong>
                                        <xsl:value-of select="//a1:Autorsko_delo/a1:Vrsta"/>
                                    </strong>
                                </span>
                            </p>
                        </div>

                        <div class="shifted-top-l">
                            <p class="shifted-left"><xsl:value-of select="$cnt2 + 1"/>) Podaci o formi zapisa autorskog
                                dela
                                (štampani tekst, optički disk i slično):
                                <span style="font-size: 18px">
                                    <strong>
                                        <xsl:value-of select="//a1:Autorsko_delo/a1:Forma_zapisa"/>
                                    </strong>
                                </span>
                            </p>
                        </div>

                        <xsl:variable name="cnt3"
                                      select="$cnt2 + 2 + count(//a1:Zahtev/a1:Popunjava_podnosilac/a1:Autori)"/>

                        <xsl:if test="//a1:Zahtev/a1:Popunjava_podnosilac/a1:Autori">
                            <p class="shifted-left shifted-top-l"><xsl:value-of select="$cnt2 + 2"/>) Podaci o autorima:
                            </p>
                            <ul style="margin-left: 40px">
                                <xsl:for-each select="//a1:Zahtev/a1:Popunjava_podnosilac/a1:Autori/a1:Autor">
                                    <li>
                                        <p>
                                            <strong>
                                                <xsl:value-of select="a1:Ime"/>&nbsp;<xsl:value-of select="a1:Prezime"/>
                                            </strong>
                                        </p>
                                        <ul>
                                            <xsl:if test="a1:Pseudonim">
                                                <li>
                                                    Pseudonim: &nbsp;<xsl:value-of select="a1:Pseudonim"/>
                                                </li>
                                            </xsl:if>

                                            <xsl:choose>
                                                <xsl:when test="a1:Adresa">
                                                    <li>
                                                        Adresa:
                                                        <xsl:value-of select="a1:Adresa/a1:Ulica"/>&nbsp;<xsl:value-of
                                                            select="a1:Adresa/a1:Broj"/>,&nbsp;<xsl:value-of
                                                            select="a1:Adresa/a1:Postanski_broj"/>&nbsp;<xsl:value-of
                                                            select="a1:Adresa/a1:Mesto"/>
                                                    </li>
                                                    <li>
                                                        Državljanstvo:
                                                        <xsl:value-of select="a1:Drzavljanstvo"/>
                                                    </li>
                                                </xsl:when>
                                                <xsl:otherwise>
                                                    <li>Godina smrti: &nbsp;<xsl:value-of select="a1:Godina_smrti"/>
                                                    </li>
                                                </xsl:otherwise>
                                            </xsl:choose>
                                        </ul>
                                    </li>
                                </xsl:for-each>
                            </ul>
                        </xsl:if>

                        <div class="shifted-top-l">
                            <p class="shifted-left"><xsl:value-of select="$cnt3"/>) Podatak da li je u pitanju autorsko
                                delo stvoreno u radnom odnosu:
                            </p>
                            <p class="centered" style="font-size: 18px">
                                <strong>
                                    <xsl:choose>
                                        <xsl:when test="//a1:Autorsko_delo/@stvoreno_u_radnom_odnosu='true'">
                                            Delo je stvoreno u radnom odnosu
                                        </xsl:when>
                                        <xsl:otherwise>
                                            Delo nije stvoreno u radnom odnosu
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </strong>
                            </p>
                        </div>

                        <div class="shifted-top-l">
                            <p class="shifted-left">
                                <xsl:value-of select="$cnt3 + 1"/>) Način korišćenja autorskog dela:
                                <span style="font-size: 18px">
                                    <strong>
                                        <xsl:value-of select="//a1:Autorsko_delo/a1:Nacin_koriscenja"/>
                                    </strong>
                                </span>
                            </p>
                        </div>

                        <div class="shifted-top-l">
                            <p class="shifted-left">
                                <xsl:value-of select="$cnt3 + 2"/>) Prilozi koji se podnose uz zahtev:
                            </p>
                            <p style="margin-left: 40px">
                                <xsl:choose>
                                    <xsl:when test="//a1:Opis">
                                        Opis autorskog dela: &nbsp;<strong>
                                        <xsl:value-of select="//a1:Opis"/>
                                    </strong>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        Primer autorskog dela:&nbsp;
                                        <a target="_blank">
                                            <xsl:attribute name="href">
                                                <xsl:value-of select="concat('http://localhost:8001/prilozi/', //a1:Primer)"/>
                                            </xsl:attribute>
                                            <xsl:value-of select="//a1:Primer"/>
                                        </a>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </p>
                        </div>

                        <table style="margin: 80px 20px 20px auto; width: 40%;">
                            <tbody>
                                <tr>
                                    <td style="font-size: 20px; padding: 15px 0 5px 15px; border-bottom: white">Broj prijave</td>
                                </tr>
                                <tr>
                                    <td style="font-size: 22px; font-weight: bold; padding: 5px 0 15px 15px; border-top: white">
                                        A -
                                        <xsl:value-of select="//a1:Broj_prijave"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size: 20px; padding: 15px 0 5px 15px; border-bottom: white">Datum podnošenja:</td>
                                </tr>
                                <tr>
                                    <td style="font-size: 22px; padding: 5px 0 15px 15px; border-top: white">
                                        <xsl:value-of select="substring(//a1:Datum_podnosenja, 0, 11)"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>