package com.mycompany.creditsystem.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Production implements Comparable<Production>  {

    private String title;
    private Date deadline;
    private Status status;
    private int id;

    public Production(String title) {
        this.title = title;
        this.status = Status.Red;
        deadline = new Date();
    }

    public Production(String title, String deadline) {
        this.title = title;
        this.status = Status.Red;
        this.deadline = setDeadline(deadline);
    }

    public Production(int id, String title, Date deadline, int status) {
        this.id = id;
        this.title = title;
        this.status = calcStatus(status);
        this.deadline = deadline;
    }

    public Status calcStatus (int statusNumber) {
        if (statusNumber == 1) {
            return Status.Yellow;
        } else if (statusNumber == 2) {
            return Status.Green;
        }
        return Status.Red;
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
        Red, Yellow, Green
    }

    public int getStatusInt() {
        if (status == Status.Red) {
            return 0;
        } else if (status == Status.Yellow) {
            return 1;
        } else if (status == Status.Green) {
            return 2;
        } else {
            return -1;
        }
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
        String pattern1 = "dd-MM-yyyy | HH:mm";
        String pattern2 = "EEEEE dd MMMMM yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern1, new Locale("da", "DK"));
        return simpleDateFormat.format(deadline);
    }

    public Date setDeadline(String deadline) {
        String pattern1 = "dd-MM-yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern1, new Locale("da", "DK"));
        try {
            return simpleDateFormat.parse(deadline);
        } catch (ParseException e) {
            return null;
        }
    }

    public int getId() {
        return id;
    }
}
