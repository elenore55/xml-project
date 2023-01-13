package com.xml.users.repository;

import com.xml.users.exception.AlreadyRegistered;
import com.xml.users.model.Korisnik;
import com.xml.users.util.AuthUtil;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import javax.xml.transform.OutputKeys;
import java.io.OutputStream;

@Component
public class KorisnikRepository {

    static final String collectionId = "/db/project/korisnici";

    Collection col;
    XMLResource res;
    Marshalling marshalling;

    @Autowired
    public KorisnikRepository(Marshalling marshalling) {
        this.marshalling = marshalling;
    }

    public void register(Korisnik korisnik) throws Exception {
        String documentName = String.format("%s.xml", korisnik.getKorisnickoIme());
        if (get(documentName) != null)
            throw new AlreadyRegistered();
        try (var out = marshalling.marshallToOutputStream(korisnik)) {
            saveResource(documentName, out);
        }
    }

    public Korisnik get(String documentName) throws Exception {
        var resource = getResource(documentName);
        if (resource == null) {
            return null;
        } else {
            return marshalling.unmarshallContent(resource.getContentAsDOM());
        }
    }

    private XMLResource getResource(String documentName) throws Exception {
        var conn = AuthUtil.loadProperties();
        setup(conn.driver);
        res = null;
        try {
            col = getOrCreateCollection(conn);
            col.setProperty(OutputKeys.INDENT, "yes");
            res = (XMLResource) col.getResource(documentName);
            return res;
        } finally {
            cleanUp(res, col);
        }
    }

    private void saveResource(String documentName, OutputStream out) throws Exception {
        var conn = AuthUtil.loadProperties();
        setup(conn.driver);
        try {
            col = getOrCreateCollection(conn);
            res = (XMLResource) col.createResource(documentName, XMLResource.RESOURCE_TYPE);
            res.setContent(out);
            col.storeResource(res);
        } finally {
            cleanUp(res, col);
        }
    }

    private void setup(String driver) throws Exception {
        Database database = (Database) Class.forName(driver).getDeclaredConstructor().newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
    }

    private Collection getOrCreateCollection(AuthUtil.ExistConnectionProperties conn) throws XMLDBException {
        return getOrCreateCollection(conn, collectionId, 0);
    }

    private Collection getOrCreateCollection(AuthUtil.ExistConnectionProperties conn, String collectionUri, int pathSegmentOffset) throws XMLDBException {
        Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);
        if (col == null) {
            if (collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }
            String[] pathSegments = collectionUri.split("/");
            if (pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();
                for (int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/").append(pathSegments[i]);
                }
                Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);
                if (startCol == null) {
                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath, conn.user, conn.password);
                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);
                    col.close();
                    parentCol.close();

                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(conn, collectionUri, ++pathSegmentOffset);
        } else {
            return col;
        }
    }

    private void cleanUp(XMLResource res, Collection col) {
        if (res != null) {
            try {
                ((EXistResource) res).freeResources();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
        if (col != null) {
            try {
                col.close();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
    }
}
