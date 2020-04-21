package com.mycompany.creditsystem.domain.logic;

import java.util.HashMap;

public class Producer extends User {

    private HashMap<Production, Boolean> ownedProductons = new HashMap<>();

    public Producer(String name, String username, String password) {
        super(name, username, password);
        super.setAccessRole(AccessRole.producer);
    }

    public HashMap<Production, Boolean> getOwnedProductions() {
        return ownedProductons;
    }

    public void addOwnedProduction(Production production) {
        ownedProductons.put(production, true);
    }

    public void removeOwnedProduction(Production production) {
        ownedProductons.remove(production);
    }

    public void setCanEdit (Production production, Boolean canEdit) {
        ownedProductons.replace(production, canEdit);
    }

    public boolean isCanEdit(Production production) {
        return ownedProductons.get(production);

    }

}
