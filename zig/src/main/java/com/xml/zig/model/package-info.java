@XmlSchema(
        xmlns = {
                @XmlNs(prefix = "xsi", namespaceURI = XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI),
                @XmlNs(prefix = "z1", namespaceURI = "http://www.ftn.com/z1")
        },
        namespace = "http://www.ftn.com/z1",
        elementFormDefault = XmlNsForm.QUALIFIED
)

package com.xml.zig.model;

import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlSchema;

import javax.xml.XMLConstants;