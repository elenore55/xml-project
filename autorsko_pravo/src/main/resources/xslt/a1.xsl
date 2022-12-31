<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE stylesheet [
        <!ENTITY nbsp "&#160;">
        ]>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:a1="http://www.ftn.com/a1" version="2.0">
    <xsl:template match="/" >
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
                    .shifted {
                        margin-top: 40px;
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
                        background-color: #cbd4f2;
                    }
                    thead {
                        background-color: #9baefa;
                    }
                </style>
            </head>
            <body>
                <div style="width: 100%">
                    <div style="display: inline-block; width: 80%">
                        <h2>ZAVOD ZA INTELEKTUALNU SVOJINU</h2>
                        <h3>Beograd, Kneginje Ljubice 5</h3>
                    </div>
                    <div style="display: inline-block; text-align: right;">
                        <h2>OBRAZAC A-1</h2>
                    </div>
                </div>
                <h1 style="text-align: center;">ZAHTEV ZA UNOŠENJE U EVIDENCIJU I DEPONOVANJE AUTORSKIH DELA</h1>

                <h2 class="shifted" style="text-align: center">Podnosilac prijave</h2>
                <xsl:choose>
                    <xsl:when test="//a1:Podnosilac/a1:Poslovno_ime">
                        <table>
                            <thead>
                                <th>Poslovno ime</th>
                                <th>Sedište</th>
                                <th>Kontakt</th>
                            </thead>
                            <tbody>
                                <tr>
                                    <td rowspan="2" class="name">
                                        <xsl:value-of select="//a1:Podnosilac/a1:Poslovno_ime" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Ulica" />&nbsp;<xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Broj" />
                                    </td>
                                    <td>tel: &nbsp;<xsl:value-of select="//a1:Podnosilac/a1:Telefon" /></td>
                                </tr>
                                <tr>
                                    <td>
                                        <xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Postanski_broj" />&nbsp;<xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Mesto" />
                                    </td>
                                    <td>email: &nbsp;<xsl:value-of select="//a1:Podnosilac/a1:E_mail" /></td>
                                </tr>
                            </tbody>
                        </table>
                    </xsl:when>

                    <xsl:when test="//a1:Podnosilac/a1:Drzavljanstvo">
                        <table>
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
                                        <xsl:value-of select="//a1:Podnosilac/a1:Ime" />&nbsp;<xsl:value-of select="//a1:Podnosilac/a1:Prezime" />
                                    </td>
                                    <xsl:if test="//a1:Podnosilac/a1:Pseudonim">
                                        <td rowspan="2">
                                            <xsl:value-of select="//a1:Podnosilac/a1:Pseudonim" />
                                        </td>
                                    </xsl:if>
                                    <td>
                                        <xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Ulica" />&nbsp;<xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Broj" />
                                    </td>
                                    <td rowspan="2">
                                        <xsl:value-of select="//a1:Podnosilac/a1:Drzavljanstvo"/>
                                    </td>
                                    <td>tel: &nbsp;<xsl:value-of select="//a1:Podnosilac/a1:Telefon" /></td>
                                </tr>
                                <tr>
                                    <td>
                                        <xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Postanski_broj" />&nbsp;<xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Mesto" />
                                    </td>
                                    <td>email: &nbsp;<xsl:value-of select="//a1:Podnosilac/a1:E_mail" /></td>
                                </tr>
                            </tbody>
                        </table>
                    </xsl:when>

                    <xsl:otherwise>
                        <table>
                            <thead>
                                <th>Ime i prezime</th>
                                <th>Adresa</th>
                                <th>Kontakt</th>
                            </thead>
                            <tbody>
                                <tr>
                                    <td rowspan="2" class="name">
                                        <xsl:value-of select="//a1:Podnosilac/a1:Ime" />&nbsp;<xsl:value-of select="//a1:Podnosilac/a1:Prezime" />
                                    </td>
                                    <td>
                                        <xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Ulica" />&nbsp;<xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Broj" />
                                    </td>
                                    <td>tel: &nbsp;<xsl:value-of select="//a1:Podnosilac/a1:Telefon" /></td>
                                </tr>
                                <tr>
                                    <td>
                                        <xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Postanski_broj" />&nbsp;<xsl:value-of select="//a1:Podnosilac/a1:Adresa/a1:Mesto" />
                                    </td>
                                    <td>email: &nbsp;<xsl:value-of select="//a1:Podnosilac/a1:E_mail" /></td>
                                </tr>
                            </tbody>
                        </table>
                    </xsl:otherwise>
                </xsl:choose>

                <table class="shifted">
                    <thead>
                        <th colspan="2"><h2 style="margin-bottom: -5px">Autorsko delo</h2> &nbsp;<xsl:if test="//a1:Izvorno_delo">
                            (delo prerade)
                        </xsl:if></th>
                    </thead>
                    <tbody>
                        <tr>
                            <td style="width: 40%"><strong>Naslov</strong></td>
                            <td><xsl:value-of select="//a1:Autorsko_delo//a1:Naslov" /></td>
                        </tr>
                        <xsl:if test="//a1:Autorsko_delo//a1:Alternativni_naslov">
                            <tr>
                                <td><strong>Alternativni naslov</strong></td>
                                <td><xsl:value-of select="//a1:Autorsko_delo//a1:Alternativni_naslov" /></td>
                            </tr>
                        </xsl:if>
                        <tr>
                            <td><strong>Vrsta</strong></td>
                            <td><xsl:value-of select="//a1:Autorsko_delo/a1:Vrsta" /></td>
                        </tr>
                        <tr>
                            <td><strong>Forma zapisa</strong></td>
                            <td><xsl:value-of select="//a1:Autorsko_delo/a1:Forma_zapisa" /></td>
                        </tr>
                        <tr>
                            <td><strong>Način korišćenja</strong></td>
                            <td><xsl:value-of select="//a1:Autorsko_delo/a1:Nacin_koriscenja" /></td>
                        </tr>
                        <xsl:if test="//a1:Zahtev/a1:Popunjava_podnosilac/a1:Autori">
                            <tr>
                                <td colspan="2">
                                    <p><strong>Autori:</strong></p>
                                    <ul>
                                        <xsl:for-each select="//a1:Zahtev/a1:Popunjava_podnosilac/a1:Autori/a1:Autor">
                                            <li>
                                                <p><strong>
                                                    <xsl:value-of select="a1:Ime" />&nbsp;<xsl:value-of select="a1:Prezime" />
                                                </strong></p>
                                                <ul>
                                                    <xsl:if test="a1:Pseudonim">
                                                        <li>
                                                            Pseudonim: &nbsp;<xsl:value-of select="a1:Pseudonim" />
                                                        </li>
                                                    </xsl:if>

                                                    <xsl:choose>
                                                        <xsl:when test="a1:Adresa">
                                                            <li>
                                                                Adresa:
                                                                <xsl:value-of select="a1:Adresa/a1:Ulica"/>&nbsp;<xsl:value-of select="a1:Adresa/a1:Broj"/>,&nbsp;<xsl:value-of select="a1:Adresa/a1:Postanski_broj"/>&nbsp;<xsl:value-of select="a1:Adresa/a1:Mesto"/>
                                                            </li>
                                                            <li>
                                                                Državljanstvo:
                                                                <xsl:value-of select="a1:Drzavljanstvo"/>
                                                            </li>
                                                        </xsl:when>
                                                        <xsl:otherwise>
                                                            <li>Godina smrti: &nbsp;<xsl:value-of select="a1:Godina_smrti" /></li>
                                                        </xsl:otherwise>
                                                    </xsl:choose>
                                                </ul>
                                            </li>
                                        </xsl:for-each>
                                    </ul>
                                </td>
                            </tr>
                        </xsl:if>
                        <xsl:if test="//a1:Autorsko_delo/@stvoreno_u_radnom_odnosu='true'">
                            <tr style="border: 1px solid white">
                                <td colspan="2" style="border: 1px solid white">* Delo je stvoreno u radnom odnosu</td>
                            </tr>
                        </xsl:if>
                    </tbody>
                </table>
                <xsl:if test="//a1:Izvorno_delo">
                    <table style="margin-top: 10px">
                        <thead>
                            <th colspan="2"><h3>Izvorno delo</h3></th>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="width: 40%"><strong>Naslov</strong></td>
                                <td><xsl:value-of select="//a1:Izvorno_delo//a1:Naslov" /></td>
                            </tr>
                            <xsl:if test="//a1:Izvorno_delo//a1:Alternativni_naslov">
                                <tr>
                                    <td><strong>Alternativni naslov</strong></td>
                                    <td><xsl:value-of select="//a1:Izvorno_delo//a1:Alternativni_naslov" /></td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="//a1:Izvorno_delo//a1:Autori">
                                <tr>
                                    <td colspan="2">
                                        <p><strong>Autori:</strong></p>
                                        <ul>
                                            <xsl:for-each select="//a1:Izvorno_delo//a1:Autori/a1:Autor">
                                                <li>
                                                    <p><strong>
                                                        <xsl:value-of select="a1:Ime" />&nbsp;<xsl:value-of select="a1:Prezime" />
                                                    </strong></p>
                                                    <ul>
                                                        <xsl:if test="a1:Pseudonim">
                                                            <li>
                                                                Pseudonim: &nbsp;<xsl:value-of select="a1:Pseudonim" />
                                                            </li>
                                                        </xsl:if>

                                                        <xsl:choose>
                                                            <xsl:when test="a1:Adresa">
                                                                <li>
                                                                    Adresa:
                                                                    <xsl:value-of select="a1:Adresa/a1:Ulica"/>&nbsp;<xsl:value-of select="a1:Adresa/a1:Broj"/>,&nbsp;<xsl:value-of select="a1:Adresa/a1:Postanski_broj"/>&nbsp;<xsl:value-of select="a1:Adresa/a1:Mesto"/>
                                                                </li>
                                                                <li>
                                                                    Državljanstvo:
                                                                    <xsl:value-of select="a1:Drzavljanstvo"/>
                                                                </li>
                                                            </xsl:when>
                                                            <xsl:otherwise>
                                                                <li>Godina smrti: &nbsp;<xsl:value-of select="a1:Godina_smrti" /></li>
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
                </xsl:if>

                <div style="margin-left: 20px; margin-top: 40px;">
                    <h3>Prilozi uz prijavu:</h3>
                    <xsl:choose>
                        <xsl:when test="//a1:Opis">
                            <p>Opis autorskog dela: &nbsp;<xsl:value-of select="//a1:Opis" /></p>
                        </xsl:when>
                        <xsl:otherwise>
                            <p>Primer autorskog dela: &nbsp;<xsl:value-of select="//a1:Primer" /></p>
                        </xsl:otherwise>
                    </xsl:choose>
                </div>

                <table style="margin-left: auto; margin-right: 20px; width: 30%;">
                    <tbody>
                        <tr>
                            <td style="font-size: 20px; padding: 15px 0 5px 15px; border-bottom: white">Broj prijave</td>
                        </tr>
                        <tr>
                            <td style="font-size: 22px; font-weight: bold; padding: 5px 0 15px 15px; border-top: white">
                                A - <xsl:value-of select="//a1:Broj_prijave" />
                            </td>
                        </tr>
                        <tr>
                            <td style="font-size: 20px; padding: 15px 0 5px 15px; border-bottom: white">Datum podnošenja:</td>
                        </tr>
                        <tr>
                            <td style="font-size: 22px; padding: 5px 0 15px 15px; border-top: white">
                                <xsl:value-of select="//a1:Datum_podnosenja" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>