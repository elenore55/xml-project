package com.xml.autorsko_pravo.model;

import java.util.Comparator;

public class ZahtevComparator implements Comparator<Zahtev> {

    @Override
    public int compare(Zahtev o1, Zahtev o2) {
        return o1.getPopunjavaZavod().getDatumPodnosenja().compareTo(o2.getPopunjavaZavod().getDatumPodnosenja());
    }
}
