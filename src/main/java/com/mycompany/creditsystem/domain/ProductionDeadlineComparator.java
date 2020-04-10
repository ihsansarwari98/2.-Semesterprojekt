package com.mycompany.creditsystem.domain;

import java.util.Comparator;

public class ProductionDeadlineComparator implements Comparator<Production> {
    @Override
    public int compare(Production o1, Production o2) {
        if (o1.getDeadline().compareTo(o2.getDeadline()) > 0) {
            return -1;
        } else if (o1.getDeadline().compareTo(o2.getDeadline()) < 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
