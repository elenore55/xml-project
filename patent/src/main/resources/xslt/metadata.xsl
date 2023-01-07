<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                xmlns:p1="http://www.ftn.com/p1"
                xmlns:pred="http://www.ftn.com/p1/pred/"
>
    <xsl:template match="/">
        <rdf:RDF>
            <xsl:variable name="Zahtev_id">http://www.ftn.com/p1/Zahtev-<xsl:value-of
                    select="//p1:Broj_prijave"/>
            </xsl:variable>
            <rdf:Description rdf:about="{$Zahtev_id}">
                <pred:Naziv_fajla>Zahtev<xsl:value-of select="//p1:Popunjava_zavod/p1:Broj_prijave"/>.xml</pred:Naziv_fajla>
                <pred:Broj_prijave>
                    <xsl:value-of select="//p1:Broj_prijave"/>
                </pred:Broj_prijave>
                <pred:Datum_podnosenja>
                    <xsl:value-of select="//p1:Datum_podnosenja"/>
                </pred:Datum_podnosenja>
                <pred:Datum_prijema>
                    <xsl:value-of select="//p1:Datum_prijema"/>
                </pred:Datum_prijema>
                <pred:Tip_prijave>
                    <xsl:value-of select="//p1:Zahtev/@tip_prijave"/>
                </pred:Tip_prijave>
                <xsl:choose>
                    <xsl:when test="//p1:Podnosilac_prijave/p1:Ime">
                        <pred:Ime_podnosioca>
                            <xsl:value-of select="//p1:Podnosilac_prijave/p1:Ime"/>
                        </pred:Ime_podnosioca>
                        <pred:Prezime_podnosioca>
                            <xsl:value-of select="//p1:Podnosilac_prijave/p1:Prezime"/>
                        </pred:Prezime_podnosioca>
                    </xsl:when>
                    <xsl:otherwise>
                        <pred:Poslovno_ime_podnosioca>
                            <xsl:value-of select="//p1:Podnosilac_prijave/p1:Poslovno_ime"/>
                        </pred:Poslovno_ime_podnosioca>
                    </xsl:otherwise>
                </xsl:choose>
            </rdf:Description>
        </rdf:RDF>
    </xsl:template>
</xsl:stylesheet>