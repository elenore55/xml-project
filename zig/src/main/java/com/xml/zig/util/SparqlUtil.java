package com.xml.zig.util;

public class SparqlUtil {

    private static final String DROP_ALL = "DROP ALL";
    private static final String DROP_GRAPH_TEMPLATE = "DROP GRAPH <%s>";
    private static final String UPDATE_TEMPLATE = "INSERT DATA { %s }";
    private static final String UPDATE_TEMPLATE_NAMED_GRAPH = "INSERT DATA { GRAPH <%1$s> { %2$s } }";
    private static final String SELECT_NAMED_GRAPH_TEMPLATE = "SELECT * FROM <%1$s> WHERE { %2$s }";
    public static final String NTRIPLES = "N-TRIPLES";
    public static final String RDF_XML = "RDF/XML";

    public static String dropAll() {
        return DROP_ALL;
    }

    public static String dropGraph(String graphURI) {
        return String.format(DROP_GRAPH_TEMPLATE, graphURI);
    }

    public static String insertData(String ntriples) {
        return String.format(UPDATE_TEMPLATE, ntriples);
    }

    public static String insertData(String graphURI, String ntriples) {
        return String.format(UPDATE_TEMPLATE_NAMED_GRAPH, graphURI, ntriples);
    }

    public static String selectData(String graphURI, String sparqlCondition) {
        return String.format(SELECT_NAMED_GRAPH_TEMPLATE, graphURI, sparqlCondition);
    }
}
