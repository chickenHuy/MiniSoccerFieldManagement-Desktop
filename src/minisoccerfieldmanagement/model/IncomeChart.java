/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.model;

/**
 *
 * @author trank
 */

import java.math.BigDecimal;
import java.sql.Date;
public class IncomeChart {
    BigDecimal income;
    Date date;
    String type;

    public IncomeChart(BigDecimal income, Date date, String type) {
        this.income = income;
        this.date = date;
        this.type = type;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

