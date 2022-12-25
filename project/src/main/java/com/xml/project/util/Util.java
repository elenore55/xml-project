package com.xml.project.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Util {

    public static String DATE_FORMAT = "dd/MM/yyyy";

    private static final String connectionUri = "xmldb:exist://%1$s:%2$s/exist/xmlrpc";

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

            uri = String.format(connectionUri, host, port);

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

    public static String dateToStr(Date date) {
        SimpleDateFormat sdt = new SimpleDateFormat(DATE_FORMAT);
        return sdt.format(date);
    }

}
