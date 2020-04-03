package com.mycompany.tv2guitest.domain;

import java.util.ArrayList;

public class Producer extends User {
    private ArrayList<Production> ownedProductions;
    private boolean canEdit;

    public Producer(String name, String username, String password) {
        super(name, username, password);
    }

    public ArrayList<Production> getOwnedProductions() {
        return ownedProductions;
    }

    public void addOwnedProduction(Production production) {
        ownedProductions.add(production);
    }

    public void removeOwnedProduction(Production production) {
        ownedProductions.remove(production);
    }

    public void setOwnedProductions(ArrayList<Production> ownedProductions) {
        this.ownedProductions = ownedProductions;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }
}
