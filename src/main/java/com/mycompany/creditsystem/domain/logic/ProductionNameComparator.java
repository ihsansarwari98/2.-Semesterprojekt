package com.mycompany.creditsystem.domain.logic;

import com.mycompany.creditsystem.persistence.Production;

import java.util.Comparator;

public class ProductionNameComparator implements Comparator<Production> {
    @Override
    public int compare(Production o1, Production o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
