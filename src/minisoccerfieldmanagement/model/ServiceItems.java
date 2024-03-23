package minisoccerfieldmanagement.model;

import java.sql.Timestamp;

public class ServiceItems {
    private int id;
    private int serviceUsageId;
    private int serviceId;
    private int quantity;
    private Boolean isDeleted;
    private Timestamp createAt;
    private Timestamp updateAt;

    public ServiceItems() {
    }

    public ServiceItems(int id, int serviceUsageId, int serviceId, int quantity, Boolean isDeleted, Timestamp createAt, Timestamp updateAt) {
        this.id = id;
        this.serviceUsageId = serviceUsageId;
        this.serviceId = serviceId;
        this.quantity = quantity;
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

    public int getServiceUsageId() {
        return serviceUsageId;
    }

    public void setServiceUsageId(int serviceUsageId) {
        this.serviceUsageId = serviceUsageId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
