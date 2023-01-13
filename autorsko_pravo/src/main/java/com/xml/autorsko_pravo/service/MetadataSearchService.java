package com.xml.autorsko_pravo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class MetadataSearchService {

    List<String> operators;
    List<List<String>> statements;

    public void generate(String rawInput) {
        var tokens = getTokens(rawInput);
        operators = new ArrayList<>();
        statements = new ArrayList<>();

        var tempArr = new ArrayList<String>();
        boolean inverse = false;
        for (var token : tokens) {
            if (token.equals("I") || token.equals("ILI")) {
                operators.add(getLogicalOp(token));
                statements.add(tempArr);
                tempArr = new ArrayList<>();
            } else if (token.equals("NE")) {
                inverse = true;
            } else if (getOperatorsMap().containsKey(token)) {
                if (inverse) tempArr.add(getInverseOp(token));
                else tempArr.add(token);
            } else {
                tempArr.add(token);
            }
        }
        statements.add(tempArr);
    }

    public List<String> getOperators() {
        return operators;
    }

    public List<List<String>> getStatements() {
        return statements;
    }

    private List<String> getTokens(String rawInput) {
        var tokens = new ArrayList<String>();
        var matcher = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(rawInput);
        while (matcher.find())
            tokens.add(matcher.group(1));
        return tokens;
    }

    private HashMap<String, String> getOperatorsMap() {
        return new HashMap<>() {{
            put(">", "<=");
            put("<", ">=");
            put(">=", "<");
            put("<=", ">");
            put("=", "!=");
            put("!=", "=");
        }};
    }

    private String getInverseOp(String op) {
        return getOperatorsMap().get(op);
    }

    private String getLogicalOp(String op) {
        if (op.equals("I")) return "&&";
        return "||";
    }
}
