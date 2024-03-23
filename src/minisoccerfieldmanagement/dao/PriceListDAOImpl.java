/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Time;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import minisoccerfieldmanagement.model.PriceList;
/**
 *
 * @author trank
 */
public class PriceListDAOImpl implements IPriceListDAO{

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public PriceListDAOImpl(){};
    
    @Override
    public Boolean add(PriceList model) {
        try {
            String sql = "INSERT INTO `pricelist`(`startTime`, `endTime`, `typeField`, `dateOfWeek`, `unitPricePer30Minutes`, `createdAt`) VALUES (?,?,?,?,?,NOW()) ;";

            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);

            ps.setTime(1, model.getStartTime());
            ps.setTime(2, model.getEndTime());
            ps.setString(3, model.getTypeField());
            ps.setString(4, model.getDateOfWeek());
            ps.setBigDecimal(5, model.getUnitPricePer30Minutes());

            ps.executeUpdate();
            conn.close();
            return true;

        } catch (Exception e) {
               e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(PriceList model) {
        try {
            String sql = "UPDATE `pricelist` SET `startTime`=?,`endTime`=?,`typeField`=?,`dateOfWeek`=?,`unitPricePer30Minutes`=?,`updatedAt`=NOW() WHERE `id`= ? ;";

            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            
            ps.setTime(1, model.getStartTime());
            ps.setTime(2, model.getEndTime());
            ps.setString(3, model.getTypeField());
            ps.setString(4, model.getDateOfWeek());
            ps.setBigDecimal(5, model.getUnitPricePer30Minutes());
            ps.setInt(6, model.getId());

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
            String sql = "UPDATE `pricelist` SET `isDeleted` = ? WHERE `id`= ?";

            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);

            ps.setBoolean(1, true);
            ps.setInt(2, id);

            ps.executeUpdate();
            conn.close();
            return true;

        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public List<PriceList> findByDateOfWeek(String date) {
        List<PriceList> priceLists = new ArrayList<>();
        try {
            String sql = "SELECT `id`, `startTime`, `endTime`, `typeField`, `dateOfWeek`, `unitPricePer30Minutes`, `updatedAt`, `createdAt` FROM `pricelist` WHERE `dateOfWeek` = ? AND isDeleted = 0 ;";
            conn = new DBConnection().getConnection();
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, date);
           
            rs = ps.executeQuery();
            while (rs.next()) {
                PriceList pl = new PriceList();
                pl.setId(rs.getInt("id"));
                pl.setStartTime(rs.getTime("startTime"));
                pl.setEndTime(rs.getTime("endTime"));
                pl.setTypeField(rs.getString("typeField"));
                pl.setDateOfWeek(rs.getString("dateOfWeek"));
                pl.setUnitPricePer30Minutes(rs.getBigDecimal("unitPricePer30Minutes"));
                pl.setDeleted(Boolean.FALSE);
                pl.setCreateAt(new Timestamp(rs.getDate("createdAt").getTime()));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    pl.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }
                
                priceLists.add(pl);
            }
			
			conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return priceLists;
    }

    @Override
    public List<PriceList> findAll() {
        List<PriceList> priceLists = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `pricelist` WHERE isDeleted = 0;";
            conn = new DBConnection().getConnection();
            
            ps = conn.prepareStatement(sql);
           
            rs = ps.executeQuery();
            while (rs.next()) {
                PriceList pl = new PriceList();
                pl.setId(rs.getInt("id"));
                pl.setStartTime(rs.getTime("startTime"));
                pl.setEndTime(rs.getTime("endTime"));
                pl.setTypeField(rs.getString("typeField"));
                pl.setDateOfWeek(rs.getString("dateOfWeek"));
                pl.setUnitPricePer30Minutes(rs.getBigDecimal("unitPricePer30Minutes"));
                pl.setDeleted(Boolean.FALSE);
                pl.setCreateAt(new Timestamp(rs.getDate("createdAt").getTime()));
                Date updatedAtDate = rs.getDate("updatedAt");

                if (updatedAtDate != null) {
                    pl.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }

                
                priceLists.add(pl);
            }
			
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return priceLists;
    }

    @Override
    public BigDecimal findPriceByTime(Timestamp dateTimeIn, Timestamp dateTimeOut, String date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PriceList findById(int id) {
        try {
            String sql = "SELECT `id`, `startTime`, `endTime`, `typeField`, `dateOfWeek`, `unitPricePer30Minutes`, `updatedAt`, `createdAt` FROM `pricelist` WHERE `id` = ? AND isDeleted = 0 ;";
            conn = new DBConnection().getConnection();
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
           
            rs = ps.executeQuery();
            if (rs.next()) {
                PriceList pl = new PriceList();
                pl.setId(rs.getInt("id"));
                pl.setStartTime(rs.getTime("startTime"));
                pl.setEndTime(rs.getTime("endTime"));
                pl.setTypeField(rs.getString("typeField"));
                pl.setDateOfWeek(rs.getString("dateOfWeek"));
                pl.setUnitPricePer30Minutes(rs.getBigDecimal("unitPricePer30Minutes"));
                pl.setDeleted(Boolean.FALSE);
                pl.setCreateAt(new Timestamp(rs.getDate("createdAt").getTime()));
                pl.setUpdateAt(new Timestamp(rs.getDate("updatedAt").getTime()));
                return pl;
            }
			
            conn.close();
            
        } catch (Exception e) {
        }
        return null;
    }
    
}
