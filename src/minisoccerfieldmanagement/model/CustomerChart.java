/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.model;

import java.sql.Date;

/**
 *
 * @author 84931
 */
public class CustomerChart {
    private int numberOfCustomer;
    private java.sql.Date date;

    public int getNumberOfCustomer() {
        return numberOfCustomer;
    }

    public void setNumberOfCustomer(int numberOfCustomer) {
        this.numberOfCustomer = numberOfCustomer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CustomerChart(int numberOfCustomer, Date date) {
        this.numberOfCustomer = numberOfCustomer;
        this.date = date;
    }

    public CustomerChart() {
    }
    
    
}
