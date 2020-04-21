package com.mycompany.creditsystem.domain.interfaces;

import com.mycompany.creditsystem.domain.logic.Credit;

import java.util.List;

public interface ICreditHandler {
    public List<Credit> getCredits();
    public Credit getCredit(int id);
    public boolean createCredit (Credit credit);
    public boolean deleteCredit (int id);
    public boolean updateCredit (Credit credit);

}
