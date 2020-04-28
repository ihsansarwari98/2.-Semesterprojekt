package com.mycompany.creditsystem.domain.logic;

import com.mycompany.creditsystem.domain.logicinterface.ICreditLogic;
import com.mycompany.creditsystem.persistence.Credit;
import com.mycompany.creditsystem.persistence.CreditHandler;

import java.util.ArrayList;

public class CreditLogic implements ICreditLogic {

    @Override
    public ArrayList<Credit> getCredits() {
        return CreditHandler.getInstance().getCredits();
    }

    @Override
    public Credit getCredit(int id) {
        return CreditHandler.getInstance().getCredit(id);
    }

    @Override
    public boolean createCredit(Credit credit) {
        return CreditHandler.getInstance().createCredit(credit);
    }

    @Override
    public boolean updateCreditName(String name, int id) {
        return CreditHandler.getInstance().updateCreditName(name, id);
    }

    @Override
    public boolean deleteCreditRelation(int credit_id, int production_id) {
        return CreditHandler.getInstance().deleteCreditRelation(credit_id, production_id);
    }

    @Override
    public boolean deleteCreditFromSystem(int id) {
        return CreditHandler.getInstance().deleteCreditFromSystem(id);
    }

    @Override
    public boolean addCreditRelation(int production_id, int credit_id, int role_id) {
        return CreditHandler.getInstance().addCreditRelation(production_id, credit_id, role_id);
    }

    @Override
    public ArrayList<Credit> getCredits(int production_id) {
        return CreditHandler.getInstance().getCredits(production_id);
    }

    @Override
    public ArrayList<Credit> getCredits(String namePart) {
        return CreditHandler.getInstance().getCredits(namePart);
    }


}
