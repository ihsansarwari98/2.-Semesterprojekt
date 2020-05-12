package com.mycompany.creditsystem.persistence;

public class CreditAndRole {
    private Credit credit;
    private Role role;

    public CreditAndRole (Credit credit, Role role) {
        this.credit = credit;
        this.role = role;
    }

    public Credit getCredit() {
        return credit;
    }

    public Role getRole() {
        return role;
    }
}
