package com.xml.zig.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AuthUtil {

    private static final String CONNECTION_URI = "xmldb:exist://%1$s:%2$s/exist/xmlrpc";

    static public class ConnectionProperties {

        public String host;
        public int port;
        public String user;
        public String password;
        public String driver;
        public String uri;

        public ConnectionProperties(Properties props) {
            super();
            user = props.getProperty("conn.user").trim();
            password = props.getProperty("conn.password").trim();
            host = props.getProperty("conn.host").trim();
            port = Integer.parseInt(props.getProperty("conn.port"));
            uri = String.format(CONNECTION_URI, host, port);
            driver = props.getProperty("conn.driver").trim();
        }
    }

    public static ConnectionProperties loadProperties() throws IOException {
        String propsName = "exist.properties";
        InputStream propsStream = openStream(propsName);
        if (propsStream == null)
            throw new IOException("Could not read properties " + propsName);
        Properties props = new Properties();
        props.load(propsStream);
        return new ConnectionProperties(props);
    }

    public static InputStream openStream(String fileName) {
        return Util.class.getClassLoader().getResourceAsStream(fileName);
    }
}
