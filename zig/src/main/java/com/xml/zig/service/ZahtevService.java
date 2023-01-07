package com.xml.zig.service;

import com.xml.zig.model.Zahtev;
import com.xml.zig.repository.MetadataRepository;
import com.xml.zig.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ZahtevService {

    ZahtevRepository zahtevRepository;
    MetadataRepository metadataRepository;

    @Autowired
    public ZahtevService(ZahtevRepository zahtevRepository, MetadataRepository metadataRepository) {
        this.zahtevRepository = zahtevRepository;
        this.metadataRepository = metadataRepository;
    }

    public Zahtev getOne(String name) throws Exception {
        return zahtevRepository.get(name);
    }

    public List<Zahtev> getAll() throws Exception {
        return zahtevRepository.getAll();
    }

    public List<Zahtev> search(String text, boolean matchCase) throws Exception {
        return zahtevRepository.search(text, matchCase);
    }

    public void extractMetadata(String name) throws Exception {
        metadataRepository.extract(name);
    }

    public List<Zahtev> simpleMetadataSearch(String name, String value) throws Exception {
        return metadataRepository.simpleMetadataSearch(name, value);
    }

    public List<Zahtev> advancedMetadataSearch(String rawInput) throws Exception {
        var tokens = getTokens(rawInput);
        var operators = new ArrayList<String>();
        var statements = new ArrayList<List<String>>();

        var tempArr = new ArrayList<String>();
        boolean inverse = false;
        for (var token : tokens) {
            if (token.equals("I") || token.equals("ILI")) {
                operators.add(getLogicalOp(token));
                statements.add(tempArr);
                tempArr = new ArrayList<>();
            } else if (token.equals("NE")) {
                inverse = true;
            } else if (getOperators().containsKey(token)) {
                if (inverse) tempArr.add(getInverseOp(token));
                else tempArr.add(token);
            } else {
                tempArr.add(token);
            }
        }
        statements.add(tempArr);
        return metadataRepository.advancedMetadataSearch(operators, statements);
    }

    private List<String> getTokens(String rawInput) {
        var tokens = new ArrayList<String>();
        var matcher = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(rawInput);
        while (matcher.find())
            tokens.add(matcher.group(1));
        return tokens;
    }

    private HashMap<String, String> getOperators() {
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
        return getOperators().get(op);
    }

    private String getLogicalOp(String op) {
        if (op.equals("I")) return "&&";
        return "||";
    }
}
