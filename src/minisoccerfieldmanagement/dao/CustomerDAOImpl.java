package minisoccerfieldmanagement.dao;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import minisoccerfieldmanagement.model.Customer;

public class CustomerDAOImpl implements ICustomerDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public Boolean add(Customer model) {
        try {
            String sql = "INSERT INTO `Customer`(`memberShipId`, `name`, `phoneNumber`, `totalSpend`, `image`, `createdAt`) VALUES(?,?,?,0,?,NOW());";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);

            ps.setInt(1, model.getMemberShipId());
            ps.setString(2, model.getName());
            ps.setString(3, model.getPhoneNumber());
            ps.setString(4, model.getImage());

            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean update(Customer model) {
        try {
            String sql = "UPDATE `Customer` SET `name` = ?, `phoneNumber` = ?, `image` = ?, `memberShipId` = ?, `totalSpend`= ?, `updatedAt` = NOW() WHERE `id` = ?;";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);

            ps.setString(1, model.getName());
            ps.setString(2, model.getPhoneNumber());
            ps.setString(3, model.getImage());
            ps.setInt(4, model.getMemberShipId());
            ps.setBigDecimal(5, model.getTotalSpend());
            ps.setInt(6, model.getId());

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
            String sql = "UPDATE `Customer` SET `isDeleted` = ?, `updatedAt` = NOW() WHERE `id` = ?;";
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
    public Customer findById(int id) {
        Customer model = new Customer();
        try {
            String sql = "SELECT * FROM `Customer` WHERE `id` = ? AND `isDeleted` = 0;";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                model.setId(rs.getInt("id"));
                model.setMemberShipId(rs.getInt("memberShipId"));
                model.setName(rs.getString("name"));
                model.setPhoneNumber(rs.getString("phoneNumber"));
                model.setTotalSpend(rs.getBigDecimal("totalSpend"));
                model.setImage(rs.getString("image"));
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
    public Customer findByPhoneNumber(String phoneNumber) {
        Customer model = new Customer();
        try {
            String sql = "SELECT * FROM `Customer` WHERE `phoneNumber` = ? AND `isDeleted` = 0;";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setString(1, phoneNumber);

            rs = ps.executeQuery();

            while (rs.next()) {
                model.setId(rs.getInt("id"));
                model.setMemberShipId(rs.getInt("memberShipId"));
                model.setName(rs.getString("name"));
                model.setPhoneNumber(rs.getString("phoneNumber"));
                model.setTotalSpend(rs.getBigDecimal("totalSpend"));
                model.setImage(rs.getString("image"));
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
    public List<Customer> findByMemberShip(int memberShipId) {
        List<Customer> models = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `Customer` WHERE `memberShipId` = ? AND `isDeleted` = 0;";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, memberShipId);

            rs = ps.executeQuery();

            while (rs.next()) {
                Customer model = new Customer();
                model.setId(rs.getInt("id"));
                model.setMemberShipId(rs.getInt("memberShipId"));
                model.setName(rs.getString("name"));
                model.setPhoneNumber(rs.getString("phoneNumber"));
                model.setTotalSpend(rs.getBigDecimal("totalSpend"));
                model.setImage(rs.getString("image"));
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

    public List<Customer> findAll() {
        List<Customer> models = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `Customer` WHERE `isDeleted` = 0";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                Customer model = new Customer();
                model.setId(rs.getInt("id"));
                model.setMemberShipId(rs.getInt("memberShipId"));
                model.setName(rs.getString("name"));
                model.setPhoneNumber(rs.getString("phoneNumber"));
                model.setTotalSpend(rs.getBigDecimal("totalSpend"));
                model.setImage(rs.getString("image"));
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
    public Boolean checkPhoneNumberExist(String phoneNumber) {
        try {
            String sql = "SELECT COUNT(*) FROM `Customer` WHERE `phoneNumber` = ? AND `isDeleted` = 0;";
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, phoneNumber);
            rs = ps.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean checkPhoneNumberExistExceptCurrent(int id, String phoneNumber) {
        boolean exists = false;
        try {
            String sql = "SELECT COUNT(*) AS count FROM customer WHERE phoneNumber = ? AND id != ? AND `isDeleted` = 0;";
            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, phoneNumber);
            ps.setInt(2, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    exists = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

}
