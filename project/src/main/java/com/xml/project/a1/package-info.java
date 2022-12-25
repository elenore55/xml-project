@XmlSchema(
        xmlns = {
                @XmlNs(prefix = "xsi", namespaceURI = XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI),
                @XmlNs(prefix = "a1", namespaceURI = "http://www.ftn.com/a1")
        },
        namespace = "http://www.ftn.com/a1",
        elementFormDefault = XmlNsForm.QUALIFIED
)

package com.xml.project.a1;

import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlSchema;

import javax.xml.XMLConstants;