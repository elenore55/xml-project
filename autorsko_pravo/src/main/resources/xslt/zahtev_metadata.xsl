<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                xmlns:a1="http://www.ftn.com/a1"
                xmlns:pred="http://www.ftn.com/a1/pred/"
>
    <xsl:template match="/">
        <rdf:RDF>
            <xsl:variable name="Zahtev_id">http://www.ftn.com/a1/Zahtev-<xsl:value-of
                    select="//a1:Broj_prijave"/>
            </xsl:variable>
            <rdf:Description rdf:about="{$Zahtev_id}">
                <pred:Naziv_fajla>Zahtev<xsl:value-of select="//a1:Popunjava_zavod/a1:Broj_prijave"/>.xml</pred:Naziv_fajla>
                <pred:Broj_prijave>
                    <xsl:value-of select="//a1:Broj_prijave"/>
                </pred:Broj_prijave>
                <pred:Datum_podnosenja>
                    <xsl:value-of select="//a1:Datum_podnosenja"/>
                </pred:Datum_podnosenja>
                <xsl:choose>
                    <xsl:when test="//a1:Podnosilac/a1:Ime != ''">
                        <pred:Ime_podnosioca>
                            <xsl:value-of select="//a1:Podnosilac/a1:Ime"/>
                        </pred:Ime_podnosioca>
                        <pred:Prezime_podnosioca>
                            <xsl:value-of select="//a1:Podnosilac/a1:Prezime"/>
                        </pred:Prezime_podnosioca>
                    </xsl:when>
                    <xsl:otherwise>
                        <pred:Poslovno_ime_podnosioca>
                            <xsl:value-of select="//a1:Podnosilac/a1:Poslovno_ime"/>
                        </pred:Poslovno_ime_podnosioca>
                    </xsl:otherwise>
                </xsl:choose>
                <pred:Stvoreno_u_radnom_odnosu>
                    <xsl:value-of select="//a1:Autorsko_delo/@stvoreno_u_radnom_odnosu"/>
                </pred:Stvoreno_u_radnom_odnosu>
            </rdf:Description>
        </rdf:RDF>
    </xsl:template>
</xsl:stylesheet>