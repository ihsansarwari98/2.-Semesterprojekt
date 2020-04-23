package com.mycompany.creditsystem.domain.interfaces;

import com.mycompany.creditsystem.domain.logic.Credit;

import java.util.ArrayList;

public interface ICreditHandler {
    public ArrayList<Credit> getCredits();
    public Credit getCredit(int id);
    public boolean createCredit (Credit credit);
    public boolean updateCreditName (String name, int id);
    public boolean deleteCreditRelation (int credit_id, int production_id);
    public boolean deleteCreditFromSystem (int id);
    public boolean addCreditRelation(int production_id, int credit_id, int role_id);
    public ArrayList<Credit> getCredits(int production_id);
}
