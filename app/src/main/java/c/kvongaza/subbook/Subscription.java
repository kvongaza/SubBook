package c.kvongaza.subbook;

import java.util.Date;

/**
 * Created by Kvongaza on 2018-02-03.
 */

public class Subscription {
    private String name;
    private Date dateStart;
    private double monthlyCharge;
    private String comment;


    // default constructor
    public Subscription() {
        name = "";
        dateStart = new Date();
        monthlyCharge = 0.0;
        comment = "";
    }

    // constructor
    public Subscription(String newName, Date newDate, double amount, String newComment) {
        name = newName;
        dateStart = newDate;
        monthlyCharge = amount;
        comment = newComment;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }

    public void setDate(Date newDate) {
        dateStart = newDate;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setMonthlyCharge(double amount) {
        monthlyCharge = amount;
    }

    public double getMonthlyCharge() {
        return monthlyCharge;
    }

    public void setComment(String newComment) {
        comment = newComment;
    }

    public String getComment() {
        return comment;
    }

}
