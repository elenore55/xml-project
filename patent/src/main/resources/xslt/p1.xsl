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
                    .centered {
                    text-align: center;
                    }
                    .padded {
                    padding: 10px;
                    }
                    .flex-container {
                    display: flex;
                    }
                    .flex-child {
                    flex: 1;
                    flex-grow: 2;
                    }
                    .flex-child-sm {
                    flex: 1;
                    flex-grow: 1;
                    }
                </style>
            </head>
            <body>

                <div class="margin-bottom: 40px">
                    <div style="display: inline-block; width: 60%;">
                        <table style="margin-left: 10px; float: left;">
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
                                    <td style="padding: 10px 0 0 10px; width: 50%">
                                        <span style="margin-top: 10px">Datum prijema:</span>
                                        <p style="font-size: 18px;">
                                            <xsl:value-of select="//p1:Popunjava_zavod/p1:Datum_prijema"/>
                                        </p>
                                    </td>
                                    <td style="padding: 10px 0 0 10px; width: 50%">
                                        <span style="margin-top: 10px">Priznati datum podno??enja:</span>
                                        <p style="font-size: 18px;">
                                            <xsl:value-of select="//p1:Popunjava_zavod/p1:Datum_podnosenja"/>
                                        </p>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div style="margin-left: 20px; margin-top: 10px">
                        Republika Srbija
                        <br/>
                        Zavod za intelektualnu svojinu
                        <br/>
                        Kneginje Ljubice broj 5
                        <br/>
                        11000 Beograd
                    </div>
                </div>

                <h3 class="centered">ZAHTEV ZA PRIZNANJE PATENTA</h3>
                <div style="border: 2px solid black; width: 90%; margin: auto">
                    <div style="border-bottom: 2px solid black">
                        <p style="border-bottom: 1px solid black; padding: 0 15px 10px 15px">
                            <strong>
                                Polje broj I &nbsp;&nbsp;&nbsp;&nbsp;NAZIV PRONALASKA
                            </strong>
                        </p>
                        <p style="padding: 0 15px">
                            Na srpskom jeziku:
                            <strong>
                                <xsl:value-of select="//p1:Naziv_pronalaska[@jezik='srpski']"/>
                            </strong>
                        </p>
                        <p style="padding: 0 15px">
                            Na engleskom jeziku:
                            <strong>
                                <xsl:value-of select="//p1:Naziv_pronalaska[@jezik='engleski']"/>
                            </strong>
                        </p>
                    </div>

                    <div style="border-bottom: 2px solid black">
                        <p style="border-bottom: 1px solid black; padding: 0 15px 10px 15px; margin-bottom: 0px">
                            <strong>
                                Polje broj II &nbsp;&nbsp;&nbsp;&nbsp;PODNOSILAC PRIJAVE
                            </strong>
                            <xsl:if test="//p1:Podnosilac_prijave/@pronalazac='true'">
                                &nbsp;&nbsp;&nbsp;&nbsp;*Podnosilac prijave je i pronalaza??
                            </xsl:if>
                        </p>
                        <div class="flex-container">
                            <div class="flex-container flex-child" style="border-bottom: 1px solid black">
                                <div style="border-right: 1px solid black" class="padded flex-child">
                                    Ime i prezime / Poslovno ime:
                                    <p class="centered" style="font-size: 18px; margin-top: 30px">
                                        <xsl:choose>
                                            <xsl:when test="//p1:Podnosilac_prijave/p1:Ime">
                                                <xsl:value-of select="//p1:Podnosilac_prijave/p1:Ime"/>&nbsp;<xsl:value-of
                                                    select="//p1:Podnosilac_prijave/p1:Prezime"/>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <xsl:value-of select="//p1:Podnosilac_prijave/p1:Poslovno_ime"/>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </p>
                                </div>
                                <div class="padded flex-child">
                                    Ulica i broj, po??tanski broj, mesto i dr??ava:
                                    <p style="margin-top: 20px; font-size: 18px">
                                        <xsl:value-of select="//p1:Podnosilac_prijave//p1:Ulica"/>&nbsp;<xsl:value-of
                                            select="//p1:Podnosilac_prijave//p1:Broj"/>
                                    </p>
                                    <p style="font-size: 18px">
                                        <xsl:value-of select="//p1:Podnosilac_prijave//p1:Postanski_broj"/>&nbsp;<xsl:value-of
                                            select="//p1:Podnosilac_prijave//p1:Mesto"/>
                                    </p>
                                    <p style="font-size: 18px">
                                        <xsl:value-of select="//p1:Podnosilac_prijave//p1:Drzava"/>
                                    </p>
                                </div>
                            </div>

                            <div class="flex-child-sm">
                                <div style="border-bottom: 1px solid black; border-left: 1px solid black"
                                     class="padded">
                                    Broj telefona:
                                    <p>
                                        <xsl:value-of select="//p1:Podnosilac_prijave/p1:Broj_telefona"/>
                                    </p>
                                </div>
                                <div style="border-bottom: 1px solid black; border-left: 1px solid black"
                                     class="padded">
                                    Broj faksa:
                                    <p>
                                        <xsl:value-of select="//p1:Podnosilac_prijave/p1:Broj_faksa"/>
                                    </p>
                                </div>
                                <div style="border-bottom: 1px solid black; border-left: 1px solid black"
                                     class="padded">
                                    E-po??ta:
                                    <p>
                                        <xsl:value-of select="//p1:Podnosilac_prijave/p1:E_posta"/>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <xsl:if test="//p1:Podnosilac_prijave//p1:Drzavljanstvo">
                            <div style="padding: 10px 10px 0 10px">
                                Dr??avljanstvo:
                                <p>
                                    <xsl:value-of select="//p1:Podnosilac_prijave//p1:Drzavljanstvo"/>
                                </p>
                            </div>
                        </xsl:if>
                    </div>

                    <xsl:choose>
                        <xsl:when test="//p1:Zahtev/@pronalazac_ne_zeli_biti_naveden='true'">
                            <div style="border-bottom: 2px solid black">
                                <p style="border-bottom: 1px solid black; padding: 0 15px 10px 15px; margin-bottom: 0px">
                                    <strong>
                                        Polje broj III &nbsp;&nbsp;&nbsp;&nbsp;PRONALAZA??
                                        &nbsp;&nbsp;&nbsp;&nbsp;*Pronalaza?? ne ??eli da bude naveden u prijavi
                                    </strong>
                                </p>
                            </div>
                        </xsl:when>
                        <xsl:when test="//p1:Pronalazac">
                            <div style="border-bottom: 2px solid black">
                                <p style="border-bottom: 1px solid black; padding: 0 15px 10px 15px; margin-bottom: 0px">
                                    <strong>
                                        Polje broj III &nbsp;&nbsp;&nbsp;&nbsp;PRONALAZA??
                                    </strong>
                                </p>
                                <div class="flex-container">
                                    <div class="flex-container flex-child" style="border-bottom: 1px solid black">
                                        <div style="border-right: 1px solid black" class="padded flex-child">
                                            Ime i prezime:
                                            <p class="centered" style="font-size: 18px; margin-top: 30px">
                                                <xsl:value-of select="//p1:Pronalazac/p1:Ime"/>&nbsp;<xsl:value-of
                                                    select="//p1:Pronalazac/p1:Prezime"/>
                                            </p>
                                        </div>
                                        <div class="padded flex-child">
                                            Ulica i broj, po??tanski broj, mesto i dr??ava:
                                            <p style="margin-top: 20px; font-size: 18px">
                                                <xsl:value-of select="//p1:Pronalazac//p1:Ulica"/>&nbsp;<xsl:value-of
                                                    select="//p1:Pronalazac//p1:Broj"/>
                                            </p>
                                            <p style="font-size: 18px">
                                                <xsl:value-of select="//p1:Pronalazac//p1:Postanski_broj"/>&nbsp;<xsl:value-of
                                                    select="//p1:Pronalazac//p1:Mesto"/>
                                            </p>
                                            <p style="font-size: 18px">
                                                <xsl:value-of select="//p1:Pronalazac//p1:Drzava"/>
                                            </p>
                                        </div>
                                    </div>

                                    <div class="flex-child-sm">
                                        <div style="border-bottom: 1px solid black; border-left: 1px solid black"
                                             class="padded">
                                            Broj telefona:
                                            <p>
                                                <xsl:value-of select="//p1:Pronalazac/p1:Broj_telefona"/>
                                            </p>
                                        </div>
                                        <div style="border-bottom: 1px solid black; border-left: 1px solid black"
                                             class="padded">
                                            Broj faksa:
                                            <p>
                                                <xsl:value-of select="//p1:Pronalazac/p1:Broj_faksa"/>
                                            </p>
                                        </div>
                                        <div style="border-bottom: 1px solid black; border-left: 1px solid black"
                                             class="padded">
                                            E-po??ta:
                                            <p>
                                                <xsl:value-of select="//p1:Pronalazac/p1:E_posta"/>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </xsl:when>
                    </xsl:choose>

                    <xsl:if test="//p1:Punomocnik">
                        <div style="border-bottom: 2px solid black">
                            <p style="border-bottom: 1px solid black; padding: 0 15px 10px 15px; margin-bottom: 0px">
                                <strong>
                                    Polje broj IV &nbsp;&nbsp;&nbsp;&nbsp;<xsl:choose>
                                    <xsl:when test="//p1:Punomocnik/@vrsta='PUNOMOCNIK_ZA_ZASTUPANJE'">
                                        PUNOMO??NIK ZA ZASTUPANJE
                                    </xsl:when>
                                    <xsl:when test="//p1:Punomocnik/@vrsta='PUNOMOCNIK_ZA_PRIJEM_PISMENA'">
                                        PUNOMO??NIK ZA PRIJEM PISMENA
                                    </xsl:when>
                                    <xsl:otherwise>
                                        ZAJEDNI??KI PREDSTAVNIK
                                    </xsl:otherwise>
                                </xsl:choose>
                                </strong>
                            </p>
                            <div class="flex-container">
                                <div class="flex-child padded" style="border-right: 1px solid black">
                                    Ime i prezime / Poslovno ime:
                                    <p class="centered" style="font-size: 18px; margin-top: 30px">
                                        <xsl:choose>
                                            <xsl:when test="//p1:Punomocnik/p1:Ime">
                                                <xsl:value-of select="//p1:Punomocnik/p1:Ime"/>&nbsp;<xsl:value-of
                                                    select="//p1:Punomocnik/p1:Prezime"/>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <xsl:value-of select="//p1:Punomocnik/p1:Poslovno_ime"/>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </p>
                                </div>
                                <div class="flex-child padded" style="border-right: 1px solid black">
                                    Ulica i broj, po??tanski broj i mesto:
                                    <p style="margin-top: 20px; font-size: 18px">
                                        <xsl:value-of select="//p1:Punomocnik//p1:Ulica"/>&nbsp;<xsl:value-of
                                            select="//p1:Punomocnik//p1:Broj"/>
                                    </p>
                                    <p style="font-size: 18px">
                                        <xsl:value-of select="//p1:Punomocnik//p1:Postanski_broj"/>&nbsp;<xsl:value-of
                                            select="//p1:Punomocnik//p1:Mesto"/>
                                    </p>
                                </div>
                                <div class="flex-child-sm">
                                    <div style="border-bottom: 1px solid black"
                                         class="padded">
                                        Broj telefona:
                                        <p>
                                            <xsl:value-of select="//p1:Punomocnik/p1:Broj_telefona"/>
                                        </p>
                                    </div>

                                    <div style="border-bottom: 1px solid black"
                                         class="padded">
                                        E-po??ta:
                                        <p>
                                            <xsl:value-of select="//p1:Punomocnik/p1:E_posta"/>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </xsl:if>

                    <div style="border-bottom: 2px solid black">
                        <p style="border-bottom: 1px solid black; padding: 0 15px 10px 15px; margin-bottom: 0px">
                            <strong>Polje broj V &nbsp;&nbsp;&nbsp;&nbsp;ADRESA ZA DOSTAVLJANJE</strong>
                        </p>
                        <div class="padded">
                            Ulica i broj, po??tanski broj i mesto:
                            <p style="font-size: 18px">
                                <xsl:value-of select="//p1:Podaci_o_dostavljanju//p1:Ulica"/>&nbsp;<xsl:value-of
                                    select="//p1:Podaci_o_dostavljanju//p1:Broj"/>,
                                <xsl:value-of select="//p1:Podaci_o_dostavljanju//p1:Postanski_broj"/>&nbsp;<xsl:value-of
                                    select="//p1:Podaci_o_dostavljanju//p1:Mesto"/>
                            </p>
                        </div>
                    </div>

                    <div style="border-bottom: 2px solid black">
                        <p style="border-bottom: 1px solid black; padding: 0 15px 10px 15px; margin-bottom: 0px">
                            <strong>Polje broj VI &nbsp;&nbsp;&nbsp;&nbsp;NA??IN DOSTAVLJANJA</strong>
                        </p>
                        <div class="padded">
                            <xsl:choose>
                                <xsl:when test="//p1:Podaci_o_dostavljanju/p1:Nacin='PAPIRNI_DOKUMENT'">
                                    Podnosilac prijave je saglasan da Zavod vr??i dostavljanje pismena u papirnoj formi
                                </xsl:when>
                                <xsl:otherwise>
                                    Podnosilac prijave je saglasan da Zavod vr??i dostavljanje pismena isklju??ivo
                                    elektronskim putem u formi elektronskog dokumenta
                                </xsl:otherwise>
                            </xsl:choose>
                        </div>
                    </div>

                    <div style="border-bottom: 2px solid black">
                        <p style="border-bottom: 1px solid black; padding: 0 15px 10px 15px; margin-bottom: 0px">
                            <strong>Polje broj VI &nbsp;&nbsp;&nbsp;&nbsp;<xsl:choose>
                                <xsl:when test="//p1:Zahtev/@tip_prijave='DOPUNSKA'">DOPUNSKA PRIJAVA</xsl:when>
                                <xsl:otherwise>IZDVOJENA PRIJAVA</xsl:otherwise>
                            </xsl:choose>
                            </strong>
                        </p>
                        <xsl:if test="//p1:Zahtev/@tip_prijave='DOPUNSKA'">
                            <p style="border-bottom: 1px solid black;" class="padded">
                                Broj prvobitne prijave / osnovne prijave: &nbsp;<xsl:value-of
                                    select="//p1:Prvobitna_prijava/p1:Broj"/>
                            </p>
                            <p class="padded">
                                Datum podno??enja prvobitne prijave / osnovne prijave: &nbsp;<xsl:value-of
                                    select="//p1:Prvobitna_prijava/p1:Datum_podnosenja"/>
                            </p>
                        </xsl:if>
                    </div>

                    <xsl:if test="//p1:Zahtev_za_priznanje_prvenstva">
                        <div style="border-bottom: 2px solid black">
                            <p style="border-bottom: 1px solid black; padding: 0 15px 10px 15px; margin-bottom: 0px">
                                <strong>Polje broj VIII &nbsp;&nbsp;&nbsp;&nbsp;ZAHTEV ZA PRIZNANJE PRAVA PRVENSTVA IZ
                                    RANIJIH PRIJAVA:
                                </strong>
                            </p>

                        </div>
                        <table style="margin-top: 20px; margin-bottom: 20px">
                            <thead>
                                <th colspan="2">Datum podno??enja ranije prijave</th>
                                <th>Broj ranije prijave</th>
                                <th style="width: 30%">
                                    Dvoslovna oznaka dr??ave, regionalne ili me??unarodne organizacije
                                </th>
                            </thead>
                            <tbody>
                                <xsl:for-each select="//p1:Ranija_prijava">
                                    <tr>
                                        <td>
                                            <strong><xsl:value-of select="position()"/>.
                                            </strong>
                                        </td>
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
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>