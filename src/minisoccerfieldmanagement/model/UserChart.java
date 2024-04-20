/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author trank
 */
public class UserChart {
    private BigDecimal sumTotal;
    private String name;
    private int id;
    private java.sql.Date date;

    public UserChart() {
    }

    
    public UserChart(BigDecimal sumTotal, String name, int id, Date date) {
        this.sumTotal = sumTotal;
        this.name = name;
        this.id = id;
        this.date = date;
    }

    public BigDecimal getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(BigDecimal sumTotal) {
        this.sumTotal = sumTotal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
