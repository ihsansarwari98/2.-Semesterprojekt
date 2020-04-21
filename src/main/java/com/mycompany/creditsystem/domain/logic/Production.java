package com.mycompany.creditsystem.domain.logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Production implements Comparable<Production> {

    private String title;
    private Date deadline;
    private Status status;
    private ArrayList<Credit> credits;
    private ArrayList<Producer> assosiatedProducers;
    private int id;

    public Production(String title) {
        this.title = title;
        this.deadline = new Date();
        this.status = Status.Red;
        credits = new ArrayList<>();
        assosiatedProducers = new ArrayList<>();

    }

    public void addCredit(Credit credit) {
        credits.add(credit);
    }

    @Override
    public String toString() {
        return title ;
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
        Green, Yellow, Red
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getDeadlineString() {
        String pattern1 = "dd-MM-yyyy | HH:mm:ss";
        String pattern2 = "EEEEE dd MMMMM yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern1, new Locale("da", "DK"));
        return simpleDateFormat.format(deadline);
    }

    public ArrayList<Credit> getCredits() {
        return credits;
    }

    public ArrayList<Producer> getAssosiatedProducers(){
        return assosiatedProducers;
    }

    public void addAssosiatedProducers(Producer producer){
        assosiatedProducers.add(producer);
    }

     public void addCredits(Credit credit) {
        credits.add(credit);
    }


}
