/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import minisoccerfieldmanagement.model.PriceList;
import minisoccerfieldmanagement.model.ServiceUsage;

/**
 *
 * @author trank
 */
public class ServiceUsageDAOImpl implements IServiceUsageDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ServiceUsageDAOImpl() {
    }

    @Override
    public Boolean add(ServiceUsage serviceUsage) {
        try {
            String sql = "INSERT INTO `serviceusage`( `matchId`, `customerId`, `note`, `createdAt`) VALUES (?,?,?, NOW()) ;";

            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);

            ps.setInt(1, serviceUsage.getMatchId());
            ps.setInt(2, serviceUsage.getCustomerId());
            ps.setString(3, serviceUsage.getNote());

            ps.executeUpdate();
            conn.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(ServiceUsage serviceUsage) {
        try {
            String sql = "UPDATE `serviceusage` SET `matchId`= ?,`customerId`= ?,`note`= ?,`updatedAt`= NOW() WHERE `id`= ? ;";

            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, serviceUsage.getMatchId());
            ps.setInt(2, serviceUsage.getCustomerId());
            ps.setString(3, serviceUsage.getNote());
            ps.setInt(4, serviceUsage.getId());

            ps.executeUpdate();
            conn.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean softDelete(int id) {
        try {
            String sql = "UPDATE `serviceusage` SET `isDeleted`= 1 ,`updatedAt`= NOW() WHERE `id`= ? ;";

            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            conn.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ServiceUsage findById(int id) {
        try {
            String sql = "SELECT `id`, `matchId`, `customerId`, `note`, `isDeleted`, `createdAt`, `updatedAt` FROM `serviceusage` WHERE `id`= ? and `isDeleted` = 0 ";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                ServiceUsage su = new ServiceUsage();
                su.setId(rs.getInt("id"));
                su.setMatchId(rs.getInt("matchId"));
                su.setCustomerId(rs.getInt("customerId"));
                su.setNote(rs.getString("note"));
                su.setDeleted(Boolean.FALSE);
                su.setCreateAt(new Timestamp(rs.getDate("createdAt").getTime()));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    su.setUpdateAt(new Timestamp(updatedAtDate.getTime()));

                }
                return su;
            }

            conn.close();

        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public ServiceUsage findByMatch(int matchId) {
        try {
            String sql = "SELECT `id`, `matchId`, `customerId`, `note`, `isDeleted`, `createdAt`, `updatedAt` FROM `serviceusage` WHERE `isDeleted` = 0 and `matchId` = ? ";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, matchId);

            rs = ps.executeQuery();
            if (rs.next()) {
                ServiceUsage su = new ServiceUsage();
                su.setId(rs.getInt("id"));
                su.setMatchId(rs.getInt("matchId"));
                su.setCustomerId(rs.getInt("customerId"));
                su.setNote(rs.getString("note"));
                su.setDeleted(Boolean.FALSE);
                su.setCreateAt(new Timestamp(rs.getDate("createdAt").getTime()));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    su.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }
                return su;
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ServiceUsage> findByCustomer(int customerId) {
        List<ServiceUsage> sus = new ArrayList<>();
        ServiceUsage su;
        try {
            String sql = "SELECT `id`, `matchId`, `customerId`, `note`, `isDeleted`, `createdAt`, `updatedAt` FROM `serviceusage` WHERE `customerId`= ? and `isDeleted` = 0 ";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, customerId);

            rs = ps.executeQuery();
            while (rs.next()) {
                su = new ServiceUsage();
                su.setId(rs.getInt("id"));
                su.setMatchId(rs.getInt("matchId"));
                su.setCustomerId(rs.getInt("customerId"));
                su.setNote(rs.getString("note"));
                su.setDeleted(Boolean.FALSE);
                su.setCreateAt(new Timestamp(rs.getDate("createdAt").getTime()));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    su.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }
                sus.add(su);
            }

            conn.close();

        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public int addServiceUsageWithReturnId(ServiceUsage serviceUsage) {
        int generatedId = -1;
        try {
            String insertSql;
            String retrieveIdSql = "SELECT LAST_INSERT_ID();";

            conn = new DBConnection().getConnection();
            conn.setAutoCommit(false);

            if (serviceUsage.getCustomerId() == -1) {
                insertSql = "INSERT INTO `serviceusage`(`note`, `createdAt`) VALUES (?, NOW());";
                ps = conn.prepareStatement(insertSql);
                ps.setString(1, serviceUsage.getNote());
            } else {
                insertSql = "INSERT INTO `serviceusage`(`customerId`, `note`, `createdAt`) VALUES (?,?, NOW());";
                ps = conn.prepareStatement(insertSql);
                ps.setInt(1, serviceUsage.getCustomerId());
                ps.setString(2, serviceUsage.getNote());
            }
            ps.executeUpdate();

            ps = conn.prepareStatement(retrieveIdSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
            conn.commit();
            conn.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return -1;
        }
        return generatedId;
    }

}
