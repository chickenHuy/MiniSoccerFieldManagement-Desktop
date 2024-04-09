package minisoccerfieldmanagement.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import minisoccerfieldmanagement.model.MemberShip;

public class MembershipDAOImpl implements IMembershipDAO{
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    @Override
    public Boolean add(MemberShip model) {
        try {
            String sql = "INSERT INTO `MemberShip`(`name`, `discountRate`, `minimumSpendAmount`, `createdAt`) VALUES(?,?,?,NOW());";
            conn = new DBConnection().getConnection();
            
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, model.getName());
            ps.setInt(2, model.getDiscountRate());
            ps.setBigDecimal(3, model.getMinimumSpendAmount());
            
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }     
        return true;
    }
    
    @Override
    public Boolean update(MemberShip model) {
        try {
            String sql = "UPDATE `MemberShip` SET `name` = ?, `discountRate` = ?, `minimumSpendAmount` = ?, `updatedAt` = NOW() WHERE `id` = ?;";
            conn = new DBConnection().getConnection();
            
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, model.getName());
            ps.setInt(2, model.getDiscountRate());
            ps.setBigDecimal(3, model.getMinimumSpendAmount());
            ps.setInt(4, model.getId());
            
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
            String sql = "UPDATE `MemberShip` SET `isDeleted` = ?, `updatedAt` = NOW() WHERE `id` = ?;";
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
    public MemberShip findById(int id) {
        MemberShip model = new MemberShip();
        try {
            String sql = "SELECT * FROM `MemberShip` WHERE `id` = ? AND `isDeleted` = 0;";
            conn = new DBConnection().getConnection();
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
                model.setId(rs.getInt("id"));
                model.setName(rs.getString("name"));
                model.setDiscountRate(rs.getInt("discountRate"));
                model.setMinimumSpendAmount(rs.getBigDecimal("minimumSpendAmount"));
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
    public List<MemberShip> findAll() {
        List<MemberShip> models = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `MemberShip` WHERE `isDeleted` = 0;";
            conn = new DBConnection().getConnection();
            
            ps = conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
                MemberShip model = new MemberShip();
                model.setId(rs.getInt("id"));
                model.setName(rs.getString("name"));
                model.setDiscountRate(rs.getInt("discountRate"));
                model.setMinimumSpendAmount(rs.getBigDecimal("minimumSpendAmount"));
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
    public MemberShip findBySpendAmount(BigDecimal totalSpend) {
        MemberShip model = new MemberShip();
        try {
            String sql = "SELECT * FROM `membership` WHERE ? >= `minimumSpendAmount` AND `isDeleted` = 0 ORDER BY `minimumSpendAmount` DESC LIMIT 1;";
            conn = new DBConnection().getConnection();
            
            ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1, totalSpend);
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
                model.setId(rs.getInt("id"));
                model.setName(rs.getString("name"));
                model.setDiscountRate(rs.getInt("discountRate"));
                model.setMinimumSpendAmount(rs.getBigDecimal("minimumSpendAmount"));
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
    public int findDiscountByCustomer(int customerId) {
        int discount = 0;
        try {
            String sql = "SELECT `discountRate` FROM `MemberShip` JOIN `Customer` ON `MemberShip`.`id` = `Customer`.`memberShipId` WHERE `Customer`.`id` = ?;";
            conn = new DBConnection().getConnection();
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, customerId);
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
                discount = rs.getInt("discountRate");
            }
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }  
        return discount;
    }
    
    @Override
    public int findIdByName(String name) {
        int id = -1;
        try {
            String sql = "SELECT id FROM membership WHERE name = ?";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    
}
