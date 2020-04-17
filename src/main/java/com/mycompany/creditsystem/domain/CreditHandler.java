package com.mycompany.creditsystem.domain;

public interface CreditHandler {
    public Credit getCredit(int id);
    public void createCredit (Credit Credit);
    public void deleteCredit (int id);
    public void updateCredit (int id);

}
