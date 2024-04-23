package minisoccerfieldmanagement.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Service {

    private int id;
    private String name;
    private BigDecimal price;
    private String image;
    private String description;
    private String unit;
    private int quantity;
    private int quantityOrder;
    private int sold;
    private String status;
    private Boolean isDeleted;
    private Timestamp createAt;
    private Timestamp updateAt;

    public Service() {

    }

    public Service(int id, String name, BigDecimal price, String image, String description, String unit, int quantity, int sold, String status, Boolean isDeleted, Timestamp createAt, Timestamp updateAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.unit = unit;
        this.quantity = quantity;
        this.sold = sold;
        this.status = status;
        this.isDeleted = isDeleted;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getUnit() {
        return unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getQuantityOrder() {
        return quantityOrder;
    }

    public int getSold() {
        return sold;
    }

    public String getStatus() {
        return status;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Timestamp getCreatedAt() {
        return createAt;
    }

    public Timestamp getUpdatedAt() {
        return updateAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setQuantityOrder(int quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setCreatedAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public void setUpdatedAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }
}
