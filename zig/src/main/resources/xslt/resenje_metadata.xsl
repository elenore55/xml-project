<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                xmlns:z1="http://www.ftn.com/z1"
                xmlns:pred="http://www.ftn.com/z1/pred/"
>
    <xsl:template match="/">
        <rdf:RDF>
            <xsl:variable name="Resenje_id">http://www.ftn.com/z1/Resenje-<xsl:value-of select="//z1:Referenca"/>
            </xsl:variable>
            <rdf:Description rdf:about="{$Resenje_id}">
                <pred:Naziv_fajla>Resenje_<xsl:value-of select="//z1:Referenca"/>.xml</pred:Naziv_fajla>
                <pred:Datum_resenja>
                    <xsl:value-of select="substring(//z1:Datum_resenja, 0, 11)"/>
                </pred:Datum_resenja>
                <pred:Ime_sluzbenika>
                    <xsl:value-of select="//z1:Ime_sluzbenika"/>
                </pred:Ime_sluzbenika>
                <pred:Prezime_sluzbenika>
                    <xsl:value-of select="//z1:Prezime_sluzbenika"/>
                </pred:Prezime_sluzbenika>
                <pred:Odobren>
                    <xsl:choose>
                        <xsl:when test="//z1:OdobrenZahtev">true</xsl:when>
                        <xsl:otherwise>false</xsl:otherwise>
                    </xsl:choose>
                </pred:Odobren>
            </rdf:Description>
        </rdf:RDF>
    </xsl:template>
</xsl:stylesheet>