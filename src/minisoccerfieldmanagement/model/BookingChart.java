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
public class BookingChart {
    private int numberOfBooking;
    private java.sql.Date date;

    public int getNumberOfBooking() {
        return numberOfBooking;
    }

    public void setNumberOfBooking(int numberOfBooking) {
        this.numberOfBooking = numberOfBooking;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BookingChart(int numberOfBooking, Date date) {
        this.numberOfBooking = numberOfBooking;
        this.date = date;
    }

    public BookingChart() {
    }
    
}
