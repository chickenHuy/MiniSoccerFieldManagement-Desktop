package minisoccerfieldmanagement.dao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import minisoccerfieldmanagement.model.User;

public class UserDAOImpl implements IUserDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public UserDAOImpl() {

    }

    @Override
    public Boolean add(User model) {
        try {
            String sql = "INSERT INTO `user`(`name`, `gender`, `dateOfBirth`, `image`, `phoneNumber`, `userName`, `password`, `role`, `type`, `createdAt`) VALUES (?,?,NOW(),?,?,?,?,?,?,NOW()) ;";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, model.getName());
            ps.setString(2, model.getGender());
            ps.setString(3, model.getImage());
            ps.setString(4, model.getPhoneNumber());
            ps.setString(5, model.getUserName());
            ps.setString(6, model.getPassword());
            ps.setString(7, model.getRole());
            ps.setString(8, model.getType());

            ps.executeUpdate();
            conn.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(User model) {
        try {
            String sql = "UPDATE `user` SET `name`=?,`gender`=?,`dateOfBirth`=?,`image`=?,`phoneNumber`=?,`userName`=?, `password`=?, `role`=?, `type`=?, `updatedAt`=NOW() WHERE `id`= ? ;";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, model.getName());
            ps.setString(2, model.getGender());
            ps.setTimestamp(3, model.getDateOfBirth());
            ps.setString(4, model.getImage());
            ps.setString(5, model.getPhoneNumber());
            ps.setString(6, model.getUserName());
            ps.setString(7, model.getPassword());
            ps.setString(8, model.getRole());
            ps.setString(9, model.getType());
            ps.setInt(10, model.getId());

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
            String sql = "UPDATE `user` SET `isDeleted` = ? WHERE `id`= ?";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setBoolean(1, true);
            ps.setInt(2, id);

            ps.executeUpdate();
            conn.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findById(int id) {
        try {
            String sql = "SELECT * FROM `user` WHERE `id` = ? AND isDeleted = 0 ;";

            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                User pl = new User();
                pl.setId(rs.getInt("id"));
                pl.setName(rs.getString("name"));
                pl.setGender(rs.getString("gender"));
                pl.setDateOfBirth(new Timestamp(rs.getDate("dateOfBirth").getTime()));
                pl.setImage(rs.getString("image"));
                pl.setPhoneNumber(rs.getString("phoneNumber"));
                pl.setUserName(rs.getString("userName"));
                pl.setPassword(rs.getString("password"));
                pl.setRole(rs.getString("role"));
                pl.setType(rs.getString("type"));
                pl.setDeleted(Boolean.FALSE);
                pl.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    pl.setUpdatedAt(new Timestamp(updatedAtDate.getTime()));
                }
                return pl;
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User verifyLoginData(String userName, String password) {
        try {
            String sql = "SELECT * FROM `user` WHERE `userName`=? AND `password`=? AND `isDeleted`=0";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, userName);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {
                User pl = new User();
                pl.setId(rs.getInt("id"));
                pl.setName(rs.getString("name"));
                pl.setGender(rs.getString("gender"));
                pl.setDateOfBirth(new Timestamp(rs.getDate("dateOfBirth").getTime()));
                pl.setImage(rs.getString("image"));
                pl.setPhoneNumber(rs.getString("phoneNumber"));
                pl.setUserName(rs.getString("userName"));
                pl.setPassword(rs.getString("password"));
                pl.setRole(rs.getString("role"));
                pl.setType(rs.getString("type"));
                pl.setDeleted(Boolean.FALSE);
                pl.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    pl.setUpdatedAt(new Timestamp(updatedAtDate.getTime()));
                }
                return pl;
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean changeRole(int id, String role) {
        try {
            String sql = "UPDATE `user` SET `role` = ?, `updatedAt` = NOW() WHERE `id` = ?";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, role);
            ps.setInt(2, id);

            ps.executeUpdate();

            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean changePassword(int id, String oldPass, String newPass) {
        try {
            String sql = "SELECT * FROM `user` WHERE id = ? AND password = ?";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, oldPass);

            rs = ps.executeQuery();

            if (rs.next()) {
                sql = "UPDATE `user` SET `password` = ?, `updatedAt` = NOW() WHERE id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, newPass);
                ps.setInt(2, id);
                ps.executeUpdate();
                conn.close();
                return true;
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
            String sql = "SELECT COUNT(*) AS count FROM user WHERE phoneNumber = ? AND id != ? AND `isDeleted` = 0;";
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
