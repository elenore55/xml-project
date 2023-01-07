<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                xmlns:z1="http://www.ftn.com/z1"
                xmlns:pred="http://www.ftn.com/z1/pred/"
>
    <xsl:template match="/">
        <rdf:RDF>
            <xsl:variable name="Zahtev_id">http://www.ftn.com/z1/Zahtev-<xsl:value-of
                    select="//z1:Broj_prijave_ziga/z1:Id"/>-<xsl:value-of
                    select="//z1:Broj_prijave_ziga/z1:Godina"/>
            </xsl:variable>
            <rdf:Description rdf:about="{$Zahtev_id}">
                <pred:Naziv_fajla>Zahtev<xsl:value-of select="//z1:Popunjava_zavod/z1:Broj_prijave_ziga/z1:Id"/>-<xsl:value-of
                        select="//z1:Popunjava_zavod/z1:Broj_prijave_ziga/z1:Godina"/>.xml
                </pred:Naziv_fajla>
                <pred:Id_prijave_ziga>
                    <xsl:value-of select="//z1:Popunjava_zavod/z1:Broj_prijave_ziga/z1:Id"/>
                </pred:Id_prijave_ziga>
                <pred:Godina_prijave_ziga>
                    <xsl:value-of select="//z1:Popunjava_zavod/z1:Broj_prijave_ziga/z1:Godina"/>
                </pred:Godina_prijave_ziga>
                <pred:Datum_podnosenja>
                    <xsl:value-of select="//z1:Popunjava_zavod/z1:Datum_podnosenja"/>
                </pred:Datum_podnosenja>
                <pred:Tip_ziga>
                    <xsl:value-of select="//z1:Tip_ziga"/>
                </pred:Tip_ziga>
                <xsl:for-each select="//z1:Podnosilac_prijave">
                    <xsl:choose>
                        <xsl:when test="z1:Ime">
                            <pred:Ime_podnosioca>
                                <xsl:value-of select="z1:Ime"/>
                            </pred:Ime_podnosioca>
                            <pred:Prezime_podnosioca>
                                <xsl:value-of select="z1:Prezime"/>
                            </pred:Prezime_podnosioca>
                        </xsl:when>
                        <xsl:otherwise>
                            <pred:Poslovno_ime_podnosioca>
                                <xsl:value-of select="z1:Poslovno_ime"/>
                            </pred:Poslovno_ime_podnosioca>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:for-each>
            </rdf:Description>
        </rdf:RDF>
    </xsl:template>
</xsl:stylesheet>