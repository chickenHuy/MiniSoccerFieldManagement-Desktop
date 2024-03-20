package minisoccerfieldmanagement.model;

import java.sql.Timestamp;

public class Field {

    private int id;
    private String name;
    private String status;
    private String type;
    private String image;
    private int combineField1;
    private int combineField2;
    private int combineField3;
    private Boolean isDeleted;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Field() {
        
    }

    public Field(int id, String name, String status, String type, String image, Boolean isDeleted, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.type = type;
        this.image = image;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCombineField1() {
        return combineField1;
    }

    public void setCombineField1(int combineField1) {
        this.combineField1 = combineField1;
    }

    public int getCombineField2() {
        return combineField2;
    }

    public void setCombineField2(int combineField2) {
        this.combineField2 = combineField2;
    }

    public int getCombineField3() {
        return combineField3;
    }

    public void setCombineField3(int combineField3) {
        this.combineField3 = combineField3;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
