package com.mycompany.creditsystem.persistence;

public class CreditWithRole {
    private Credit credit;
    private Role role;

    public CreditWithRole(Credit credit, Role role) {
        this.credit = credit;
        this.role = role;
    }

    public Credit getCredit() {
        return credit;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return credit.getName() + " | " + role.getTitle();
    }
}
