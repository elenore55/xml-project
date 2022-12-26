@XmlSchema(
        xmlns = {
                @XmlNs(prefix = "xsi", namespaceURI = XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI),
                @XmlNs(prefix = "p1", namespaceURI = "http://www.ftn.com/p1")
        },
        namespace = "http://www.ftn.com/p1",
        elementFormDefault = XmlNsForm.QUALIFIED
)

package com.xml.patent.model;

import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlSchema;

import javax.xml.XMLConstants;