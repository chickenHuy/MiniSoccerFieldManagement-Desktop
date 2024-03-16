/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

public class PriceList {
    private int id;
    private Time startTime;
    private Time endTime;
    private String typeField;
    private String dateOfWeek;
    private BigDecimal unitPricePer30Minutes;
    private Boolean isDeleted;
    private Timestamp createAt;
    private Timestamp updateAt;

    public PriceList() {
    }

    public PriceList(int id, Time startTime, Time endTime, String typeField, String dateOfWeek, BigDecimal unitPricePer30Minutes, Boolean isDeleted, Timestamp createAt, Timestamp updateAt) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.typeField = typeField;
        this.dateOfWeek = dateOfWeek;
        this.unitPricePer30Minutes = unitPricePer30Minutes;
        this.isDeleted = isDeleted;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getTypeField() {
        return typeField;
    }

    public void setTypeField(String typeField) {
        this.typeField = typeField;
    }

    public String getDateOfWeek() {
        return dateOfWeek;
    }

    public void setDateOfWeek(String dateOfWeek) {
        this.dateOfWeek = dateOfWeek;
    }

    public BigDecimal getUnitPricePer30Minutes() {
        return unitPricePer30Minutes;
    }

    public void setUnitPricePer30Minutes(BigDecimal unitPricePer30Minutes) {
        this.unitPricePer30Minutes = unitPricePer30Minutes;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }
}
