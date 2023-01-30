package com.xml.autorsko_pravo.model;

import java.util.Comparator;

public class ZahtevComparator implements Comparator<Zahtev> {

    @Override
    public int compare(Zahtev o1, Zahtev o2) {
        return Integer.compare(o1.getPopunjavaZavod().getBrojPrijave(), o2.getPopunjavaZavod().getBrojPrijave());
    }
}
