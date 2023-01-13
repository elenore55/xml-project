@XmlSchema(
        xmlns = {
                @XmlNs(prefix = "xsi", namespaceURI = XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI),
                @XmlNs(prefix = "k", namespaceURI = "http://www.ftn.com/korisnici")
        },
        namespace = "http://www.ftn.com/korisnici",
        elementFormDefault = XmlNsForm.QUALIFIED
)

package com.xml.users.model;

import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlSchema;

import javax.xml.XMLConstants;