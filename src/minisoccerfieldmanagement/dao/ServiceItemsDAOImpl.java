package minisoccerfieldmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import minisoccerfieldmanagement.model.ServiceItems;

public class ServiceItemsDAOImpl implements IServiceItemsDAO{
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    @Override
    public Boolean add(ServiceItems model) {
        try {
            String sql = "INSERT INTO `ServiceItems`(`serviceUsageId`, `serviceId`, `quantity`, `createdAt`) VALUES(?,?,?,NOW())";
            conn = new DBConnection().getConnection();
            
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, model.getServiceUsageId());
            ps.setInt(2, model.getServiceId());
            ps.setInt(3, model.getQuantity());
            
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }     
        return true;
    }
    
    @Override
    public Boolean updateQty(int id, int quantity) {
        try {
            String sql = "UPDATE `ServiceItems` SET `quantity` = ?, `updatedAt` = NOW() WHERE `id` = ?;";
            conn = new DBConnection().getConnection();
            
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }     
        return true;
    }
    
    @Override
    public Boolean softDelete(int id) {
        try {
            String sql = "UPDATE `ServiceItems` SET `isDeleted` = ?, `updatedAt` = NOW() WHERE `id` = ?;";
            conn = new DBConnection().getConnection();
            
            ps = conn.prepareStatement(sql);
            
            ps.setBoolean(1, true);
            ps.setInt(2, id);
            
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }  
        return true;
    }
    
    @Override
    public ServiceItems findById(int id) {
        ServiceItems model = new ServiceItems();
        try {
            String sql = "SELECT * FROM `ServiceItems` WHERE `id` = ? AND `isDeleted` = 0;";
            conn = new DBConnection().getConnection();
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
                model.setId(rs.getInt("id"));
                model.setServiceUsageId(rs.getInt("serviceUsageId"));
                model.setServiceId(rs.getInt("serviceId"));
                model.setQuantity(rs.getInt("quantity"));
                model.setIsDeleted(rs.getBoolean("isDeleted"));
                model.setCreateAt(rs.getTimestamp("createdAt"));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    model.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }
            }
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }  
        return model;
    }
    
    @Override
    public List<ServiceItems> findByServiceUsage(int serviceUsageId) {
        List<ServiceItems> models = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `ServiceItems` WHERE `serviceUsageId` = ? AND `isDeleted` = 0;";
            conn = new DBConnection().getConnection();
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, serviceUsageId);
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
                ServiceItems model = new ServiceItems();
                model.setId(rs.getInt("id"));
                model.setServiceUsageId(rs.getInt("serviceUsageId"));
                model.setServiceId(rs.getInt("serviceId"));
                model.setQuantity(rs.getInt("quantity"));
                model.setIsDeleted(rs.getBoolean("isDeleted"));
                model.setCreateAt(rs.getTimestamp("createdAt"));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    model.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }
                
                models.add(model);
            }
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }  
        return models;
    }
    
    @Override
    public List<ServiceItems> findByService(int serviceId) {
        List<ServiceItems> models = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `ServiceItems` WHERE `serviceId` = ? AND `isDeleted` = 0;";
            conn = new DBConnection().getConnection();
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, serviceId);
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
                ServiceItems model = new ServiceItems();
                model.setId(rs.getInt("id"));
                model.setServiceUsageId(rs.getInt("serviceUsageId"));
                model.setServiceId(rs.getInt("serviceId"));
                model.setQuantity(rs.getInt("quantity"));
                model.setIsDeleted(rs.getBoolean("isDeleted"));
                model.setCreateAt(rs.getTimestamp("createdAt"));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    model.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }
                
                models.add(model);
            }
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }  
        return models;
    }
}
