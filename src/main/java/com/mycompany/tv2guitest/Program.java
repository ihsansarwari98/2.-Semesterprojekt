package com.mycompany.tv2guitest;

import java.util.Date;

public class Program implements Comparable<Program> {

    private String title;
    private Date deadline;
    private Status status;

    public Program(String title, Status status) {
        this.title = title;
        this.deadline = new Date();
        this.status = status;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(Program o) {
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
}
