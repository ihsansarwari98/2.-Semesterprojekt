package com.mycompany.creditsystem.domain.interfaces;

import com.mycompany.creditsystem.domain.logic.Credit;

import java.util.List;

public interface ICreditHandler {
    public List<Credit> getCredits();
    public Credit getCredit(int id);
    public void createCredit (Credit Credit);
    public void deleteCredit (int id);
    public void updateCredit (int id);

}
