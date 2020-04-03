package com.mycompany.tv2guitest.domain;

import java.util.ArrayList;
import java.util.Date;

public class Production implements Comparable<Production> {

    private String title;
    private Date deadline;
    private Status status;
    private ArrayList<Credit> credits = new ArrayList<>();
    private ArrayList<Producer> assosiatedProducers = new ArrayList<>();

    public Production(String title, Status status) {
        this.title = title;
        this.deadline = new Date();
        this.status = status;
    }

    public void addCredit(Credit credit) {
        credits.add(credit);
    }

    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(Production o) {
        if (this.deadline.compareTo(o.getDeadline()) > 0) {
            return -1;
        } else if (this.deadline.compareTo(o.getDeadline()) < 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public enum Status {
        Green,
        Yellow,
        Red
    }

    public Status getStatus() {
        return status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public ArrayList getCredits() {
        return credits;
    }
}
