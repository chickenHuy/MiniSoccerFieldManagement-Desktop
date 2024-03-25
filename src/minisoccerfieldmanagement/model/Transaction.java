/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author trank
 */

public class Transaction {
    private int id;
    private int userID;
    private int serviceUsageId;
    private String type;
    private BigDecimal totalAmount;
    private BigDecimal additionalFee;
    private BigDecimal discountAmount;
    private BigDecimal finalAmount;
    private Boolean isDeleted;
    private Timestamp createAt;
    private Timestamp updateAt;

    public Transaction() {
    }

    public Transaction(int id, int userID, int serviceUsageId, String type, BigDecimal totalAmount, BigDecimal additionalFee, BigDecimal discountAmount, BigDecimal finalAmount, Boolean isDeleted, Timestamp createAt, Timestamp updateAt) {
        this.id = id;
        this.userID = userID;
        this.serviceUsageId = serviceUsageId;
        this.type = type;
        this.totalAmount = totalAmount;
        this.additionalFee = additionalFee;
        this.discountAmount = discountAmount;
        this.finalAmount = finalAmount;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getServiceUsageId() {
        return serviceUsageId;
    }

    public void setServiceUsageId(int serviceUsageId) {
        this.serviceUsageId = serviceUsageId;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getAdditionalFee() {
        return additionalFee;
    }

    public void setAdditionalFee(BigDecimal additionalFee) {
        this.additionalFee = additionalFee;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
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