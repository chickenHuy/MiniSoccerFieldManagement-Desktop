/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class MemberShip {
    private int id;
    private String name;
    private int discountRate;
    private BigDecimal minimumSpendAmount;
    private Boolean isDeleted;
    private Timestamp createAt;
    private Timestamp updateAt;

    public MemberShip() {
    }

    public MemberShip(int id, String name, int discountRate, BigDecimal minimumSpendAmount, Boolean isDeleted, Timestamp createAt, Timestamp updateAt) {
        this.id = id;
        this.name = name;
        this.discountRate = discountRate;
        this.minimumSpendAmount = minimumSpendAmount;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getMinimumSpendAmount() {
        return minimumSpendAmount;
    }

    public void setMinimumSpendAmount(BigDecimal minimumSpendAmount) {
        this.minimumSpendAmount = minimumSpendAmount;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
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
