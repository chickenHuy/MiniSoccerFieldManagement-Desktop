/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import minisoccerfieldmanagement.model.Transaction;

/**
 *
 * @author trank
 */
public class TransactionDAOImpl implements ITransactionDAO{

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    @Override
    public Boolean add(Transaction transaction) {
        try {
            String sql = "INSERT INTO `Transaction`(`serviceUsageId`, `userId`, `type`, `totalAmount`, `additionalFee`, `discountAmount`, `finalAmount`,`createdAt`) VALUES(?,?,?,?,?,?,?,NOW())";
            conn = new DBConnection().getConnection();
            
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, transaction.getServiceUsageId());
            ps.setInt(2, transaction.getUserID());
            ps.setString(3, transaction.getType());
            ps.setBigDecimal(4, transaction.getTotalAmount());
            ps.setBigDecimal(5, transaction.getAdditionalFee());
            ps.setBigDecimal(6, transaction.getDiscountAmount());
            ps.setBigDecimal(7, transaction.getFinalAmount());
            
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }     
        return true;
    }

    @Override
    public Boolean update(Transaction transaction) {
        try {
            String sql = "UPDATE `Transaction` SET `type` = ?, `totalAmount` = ?, `additionalFee` = ?, `discountAmount` = ?, `finalAmount` = ?, `updatedAt` = NOW(), `serviceUsageId` = ? AND `userId` = ? WHERE `id` = ?";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);

            ps.setString(1, transaction.getType());
            ps.setBigDecimal(2, transaction.getTotalAmount());
            ps.setBigDecimal(3, transaction.getAdditionalFee());
            ps.setBigDecimal(4, transaction.getDiscountAmount());
            ps.setBigDecimal(5, transaction.getFinalAmount());
            ps.setInt(6, transaction.getServiceUsageId());
            ps.setInt(7, transaction.getUserID());
            ps.setInt(8, transaction.getId());

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
            String sql = "UPDATE `Transaction` SET `isDeleted` = 1 WHERE `id` = ?";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }     
        return true;
    }

    @Override
    public Transaction findById(int id) {
        Transaction transaction = null;
        try {
            String sql = "SELECT * FROM `Transaction` WHERE `id` = ? AND `isDeleted` = 0";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if(rs.next()) {
                transaction = new Transaction();
                transaction.setId(rs.getInt("id"));
                transaction.setServiceUsageId(rs.getInt("serviceUsageId"));
                transaction.setUserID(rs.getInt("userId"));
                transaction.setType(rs.getString("type"));
                transaction.setTotalAmount(rs.getBigDecimal("totalAmount"));
                transaction.setAdditionalFee(rs.getBigDecimal("additionalFee"));
                transaction.setDiscountAmount(rs.getBigDecimal("discountAmount"));
                transaction.setFinalAmount(rs.getBigDecimal("finalAmount"));
                transaction.setCreateAt(rs.getTimestamp("createdAt"));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    transaction.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transaction;
}


    @Override
    public List<Transaction> findByUser(int userId) {
    List<Transaction> transactions = new ArrayList<>();
    try {
        String sql = "SELECT * FROM `Transaction` WHERE `userId` = ? AND `isDeleted` = 0";
        conn = new DBConnection().getConnection();

        ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);

        rs = ps.executeQuery();

        while(rs.next()) {
            Transaction transaction = new Transaction();
            transaction.setId(rs.getInt("id"));
            transaction.setServiceUsageId(rs.getInt("serviceUsageId"));
            transaction.setUserID(rs.getInt("userId"));
            transaction.setType(rs.getString("type"));
            transaction.setTotalAmount(rs.getBigDecimal("totalAmount"));
            transaction.setAdditionalFee(rs.getBigDecimal("additionalFee"));
            transaction.setDiscountAmount(rs.getBigDecimal("discountAmount"));
            transaction.setFinalAmount(rs.getBigDecimal("finalAmount"));
            transaction.setCreateAt(rs.getTimestamp("createdAt"));
            Date updatedAtDate = rs.getDate("updatedAt");
            if (updatedAtDate != null) {
                transaction.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
            }
            transactions.add(transaction);
        }

        conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    return transactions;
    }

    @Override
    public Transaction findByServiceUsage(int serviceUsageId) {
        Transaction transaction = null;
        try {
            String sql = "SELECT * FROM `Transaction` WHERE `serviceUsageId` = ? AND `isDeleted` = 0";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, serviceUsageId);

            rs = ps.executeQuery();

            if(rs.next()) {
                transaction = new Transaction();
                transaction.setId(rs.getInt("id"));
                transaction.setServiceUsageId(rs.getInt("serviceUsageId"));
                transaction.setUserID(rs.getInt("userId"));
                transaction.setType(rs.getString("type"));
                transaction.setTotalAmount(rs.getBigDecimal("totalAmount"));
                transaction.setAdditionalFee(rs.getBigDecimal("additionalFee"));
                transaction.setDiscountAmount(rs.getBigDecimal("discountAmount"));
                transaction.setFinalAmount(rs.getBigDecimal("finalAmount"));
                transaction.setCreateAt(rs.getTimestamp("createdAt"));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    transaction.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transaction;
    }

   @Override
    public List<Transaction> findByDate(Timestamp date) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `Transaction` WHERE DATE(createdAt) = DATE(?) AND `isDeleted` = 0";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, date);

            rs = ps.executeQuery();

            while(rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(rs.getInt("id"));
                transaction.setServiceUsageId(rs.getInt("serviceUsageId"));
                transaction.setUserID(rs.getInt("userId"));
                transaction.setType(rs.getString("type"));
                transaction.setTotalAmount(rs.getBigDecimal("totalAmount"));
                transaction.setAdditionalFee(rs.getBigDecimal("additionalFee"));
                transaction.setDiscountAmount(rs.getBigDecimal("discountAmount"));
                transaction.setFinalAmount(rs.getBigDecimal("finalAmount"));
                transaction.setCreateAt(rs.getTimestamp("createdAt"));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    transaction.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }
                transactions.add(transaction);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }


    @Override
    public List<Transaction> findByCustomer(int customerId) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            String sql = "SELECT t.* FROM `Transaction` t INNER JOIN `ServiceUsage` su ON t.serviceUsageId = su.id WHERE su.customerId = ? AND t.`isDeleted` = 0";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, customerId);

            rs = ps.executeQuery();

            while(rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(rs.getInt("id"));
                transaction.setServiceUsageId(rs.getInt("serviceUsageId"));
                transaction.setUserID(rs.getInt("userId"));
                transaction.setType(rs.getString("type"));
                transaction.setTotalAmount(rs.getBigDecimal("totalAmount"));
                transaction.setAdditionalFee(rs.getBigDecimal("additionalFee"));
                transaction.setDiscountAmount(rs.getBigDecimal("discountAmount"));
                transaction.setFinalAmount(rs.getBigDecimal("finalAmount"));
                transaction.setCreateAt(rs.getTimestamp("createdAt"));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    transaction.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }
                transactions.add(transaction);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }


    @Override
    public List<Transaction> findByFieldId(int fieldId) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            String sql = "SELECT t.* FROM `Transaction` t INNER JOIN `ServiceUsage` su ON t.serviceUsageId = su.id WHERE su.fieldId = ? AND t.`isDeleted` = 0";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, fieldId);

            rs = ps.executeQuery();

            while(rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(rs.getInt("id"));
                transaction.setServiceUsageId(rs.getInt("serviceUsageId"));
                transaction.setUserID(rs.getInt("userId"));
                transaction.setType(rs.getString("type"));
                transaction.setTotalAmount(rs.getBigDecimal("totalAmount"));
                transaction.setAdditionalFee(rs.getBigDecimal("additionalFee"));
                transaction.setDiscountAmount(rs.getBigDecimal("discountAmount"));
                transaction.setFinalAmount(rs.getBigDecimal("finalAmount"));
                transaction.setCreateAt(rs.getTimestamp("createdAt"));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    transaction.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }
                transactions.add(transaction);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `Transaction` WHERE `isDeleted` = 0";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(rs.getInt("id"));
                transaction.setServiceUsageId(rs.getInt("serviceUsageId"));
                transaction.setUserID(rs.getInt("userId"));
                transaction.setType(rs.getString("type"));
                transaction.setTotalAmount(rs.getBigDecimal("totalAmount"));
                transaction.setAdditionalFee(rs.getBigDecimal("additionalFee"));
                transaction.setDiscountAmount(rs.getBigDecimal("discountAmount"));
                transaction.setFinalAmount(rs.getBigDecimal("finalAmount"));
                transaction.setCreateAt(rs.getTimestamp("createdAt"));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    transaction.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }
                transactions.add(transaction);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
    
}
