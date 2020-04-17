package com.mycompany.creditsystem.domain.interfaces;

import com.mycompany.creditsystem.domain.logic.Credit;

public interface ICreditHandler {
    public Credit getCredit(int id);
    public void createCredit (Credit Credit);
    public void deleteCredit (int id);
    public void updateCredit (int id);

}
