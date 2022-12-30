package com.xml.zig.repository;

import com.xml.zig.model.Zahtev;
import com.xml.zig.util.AuthUtil;
import org.exist.xmldb.EXistResource;
import org.w3c.dom.Node;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import javax.xml.transform.OutputKeys;
import java.util.ArrayList;
import java.util.List;

public class ZahtevRepository {

    static final String collectionId = "/db/project/zig";
    Collection col;
    XMLResource res;
    Marshalling marshalling;

    public ZahtevRepository() throws Exception {
        marshalling = new Marshalling();
    }

    public void save(Zahtev zahtev, String documentName) throws Exception {
        var conn = AuthUtil.loadProperties();
        setup(conn.driver);
        try {
            col = getOrCreateCollection(conn);
            res = (XMLResource) col.createResource(documentName, XMLResource.RESOURCE_TYPE);
            try (var out = marshalling.marshallToOutputStream(zahtev)) {
                res.setContent(out);
                col.storeResource(res);
            }
        } finally {
            cleanUp(res, col);
        }
    }

    public Zahtev get(String documentName) throws Exception {
        var conn = AuthUtil.loadProperties();
        setup(conn.driver);
        res = null;
        try {
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");
            res = (XMLResource) col.getResource(documentName);
            if (res == null) {
                System.out.println("[WARNING] Document '" + documentName + "' cannot be found!");
                return null;
            } else {
                return marshalling.unmarshallContent(res.getContentAsDOM());
            }
        } finally {
            cleanUp(res, col);
        }
    }

    public Node getAsNode(String documentName) throws Exception {
        var conn = AuthUtil.loadProperties();
        setup(conn.driver);
        res = null;
        try {
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");
            res = (XMLResource) col.getResource(documentName);
            if (res == null) {
                System.out.println("[WARNING] Document '" + documentName + "' cannot be found!");
                return null;
            } else {
                return res.getContentAsDOM();
            }
        } finally {
            cleanUp(res, col);
        }
    }

    public List<Zahtev> getAll() throws Exception {
        var conn = AuthUtil.loadProperties();
        setup(conn.driver);
        res = null;
        var result = new ArrayList<Zahtev>();
        try {
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");
            for (var documentName : col.listResources()) {
                res = (XMLResource) col.getResource(documentName);
                if (res != null)
                    result.add(marshalling.unmarshallContent(res.getContentAsDOM()));
            }
            return result;
        } finally {
            cleanUp(res, col);
        }
    }

    private void setup(String driver) throws Exception {
        Database database = (Database) Class.forName(driver).getDeclaredConstructor().newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
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

    private Collection getOrCreateCollection(AuthUtil.ConnectionProperties conn) throws XMLDBException {
        return getOrCreateCollection(conn, collectionId, 0);
    }

    private Collection getOrCreateCollection(AuthUtil.ConnectionProperties conn, String collectionUri, int pathSegmentOffset) throws XMLDBException {
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
}
