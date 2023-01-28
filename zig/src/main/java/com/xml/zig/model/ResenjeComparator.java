package com.xml.zig.model;

import java.util.Comparator;

public class ResenjeComparator implements Comparator<Resenje> {

    @Override
    public int compare(Resenje o1, Resenje o2) {
        return o1.getDatumResenja().compareTo(o2.getDatumResenja());
    }
}
