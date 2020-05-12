package com.mycompany.creditsystem.domain.logic;

import com.mycompany.creditsystem.persistence.Credit;
import com.mycompany.creditsystem.persistence.CreditHandler;
import com.mycompany.creditsystem.persistence.CreditWithRole;

import java.util.ArrayList;

public class CreditLogic{

    public ArrayList<Credit> getCredits() {
        return CreditHandler.getInstance().getCredits();
    }

    public Credit getCredit(int id) {
        return CreditHandler.getInstance().getCredit(id);
    }

    public boolean createCredit(Credit credit) {
        return CreditHandler.getInstance().createCredit(credit);
    }

    public boolean createStringCredit(String name){
        return CreditHandler.getInstance().createStringCredit(name);
    }
    public boolean updateCreditName(String name, int id) {
        return CreditHandler.getInstance().updateCreditName(name, id);
    }

    public boolean deleteCreditRelation(int credit_id, int production_id) {
        return CreditHandler.getInstance().deleteCreditRelation(credit_id, production_id);
    }

    public boolean deleteCreditFromSystem(int id) {
        return CreditHandler.getInstance().deleteCreditFromSystem(id);
    }

    public boolean addCreditRelation(int production_id, int credit_id, int role_id) {
        return CreditHandler.getInstance().addCreditRelation(production_id, credit_id, role_id);
    }

    public ArrayList<Credit> getCredits(int production_id) {
        return CreditHandler.getInstance().getCredits(production_id);
    }

    public boolean removeAllCreditsFromProduction (int production_id) {
        return CreditHandler.getInstance().removeAllCreditsFromProduction(production_id);
    }

    public ArrayList<Credit> getCredits(String namePart) {
        return CreditHandler.getInstance().getCredits(namePart);
    }

    public ArrayList<CreditWithRole> getCreditWithRole(int production_id) {
        return CreditHandler.getInstance().getCreditWithRole(production_id);
    }
}
