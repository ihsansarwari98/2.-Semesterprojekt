package com.mycompany.creditsystem.domain.interfaces;

import com.mycompany.creditsystem.domain.logic.Credit;

import java.util.List;

public interface ICreditHandler {
    public List<Credit> getCredits();
    public Credit getCredit(int id);
    public boolean createCredit (Credit credit);
    public boolean updateCredit (String name, int id);
    public boolean deleteCreditRelation (int credit_id, int production_id);
    public boolean deleteCreditFromSystem (int id);
}
