package com.xml.zig.model;

import java.util.Comparator;

public class ZahtevComparator implements Comparator<Zahtev> {

    @Override
    public int compare(Zahtev o1, Zahtev o2) {
        var cmp = o1.getPopunjavaZavod().getDatumPodnosenja().compareTo(o2.getPopunjavaZavod().getDatumPodnosenja());
        if (cmp != 0) return cmp;
        return Integer.compare(o1.getPopunjavaZavod().getBrojPrijaveZiga().getId(), o2.getPopunjavaZavod().getBrojPrijaveZiga().getId());
    }
}
