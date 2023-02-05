package com.xml.autorsko_pravo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AuthUtil {

    static public class ExistConnectionProperties {

        private static final String CONNECTION_URI = "xmldb:exist://%1$s:%2$s/exist/xmlrpc";
        public String host;
        public int port;
        public String user;
        public String password;
        public String driver;
        public String uri;

        public ExistConnectionProperties(Properties props) {
            super();
            user = props.getProperty("conn.user").trim();
            password = props.getProperty("conn.password").trim();
            host = props.getProperty("conn.host").trim();
            port = Integer.parseInt(props.getProperty("conn.port"));
            uri = String.format(CONNECTION_URI, host, port);
            driver = props.getProperty("conn.driver").trim();
        }
    }

    public static class FusekiConnectionProperties {

        public String endpoint;
        public String dataset;
        public String queryEndpoint;
        public String updateEndpoint;
        public String dataEndpoint;
        public String user;
        public String password;

        public FusekiConnectionProperties(Properties props) {
            user = props.getProperty("conn.user").trim();
            password = props.getProperty("conn.password").trim();
            dataset = props.getProperty("conn.dataset").trim();
            endpoint = props.getProperty("conn.endpoint").trim();
            queryEndpoint = String.join("/", endpoint, dataset, props.getProperty("conn.query").trim());
            updateEndpoint = String.join("/", endpoint, dataset, props.getProperty("conn.update").trim());
            dataEndpoint = String.join("/", endpoint, dataset, props.getProperty("conn.data").trim());
        }
    }

    public static ExistConnectionProperties loadProperties() throws IOException {
        String propsName = "exist.properties";
        InputStream propsStream = openStream(propsName);
        if (propsStream == null)
            throw new IOException("Could not read properties " + propsName);
        Properties props = new Properties();
        props.load(propsStream);
        return new ExistConnectionProperties(props);
    }

    public static FusekiConnectionProperties loadFusekiProperties() throws IOException {
        String propsName = "fuseki.properties";
        InputStream propsStream = openStream(propsName);
        if (propsStream == null)
            throw new IOException("Could not read properties " + propsName);
        Properties props = new Properties();
        props.load(propsStream);
        return new FusekiConnectionProperties(props);
    }

    public static InputStream openStream(String fileName) {
        return Util.class.getClassLoader().getResourceAsStream(fileName);
    }
}
