package minisoccerfieldmanagement.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Customer {
    private int id;
    private int memberShipId;
    private String name;
    private String phoneNumber;
    private BigDecimal totalSpend;
    private String image;
    private Boolean isDeleted;
    private Timestamp createAt;
    private Timestamp updateAt;

    public Customer() {
    }

    public Customer(String name, String image) {
        this.name = name;
        this.image = image;
    }
    
    public Customer(int id, int memberShipId, String name, String phoneNumber, BigDecimal totalSpend, String image, Boolean isDeleted, Timestamp createAt, Timestamp updateAt) {
        this.id = id;
        this.memberShipId = memberShipId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.totalSpend = totalSpend;
        this.image = image;
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

    public int getMemberShipId() {
        return memberShipId;
    }

    public void setMemberShipId(int memberShipId) {
        this.memberShipId = memberShipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(BigDecimal totalSpend) {
        this.totalSpend = totalSpend;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
