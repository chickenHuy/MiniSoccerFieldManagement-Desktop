package minisoccerfieldmanagement.dao;

import minisoccerfieldmanagement.util.StaticStrings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import minisoccerfieldmanagement.model.Field;

public class FieldDAOImpl implements IFieldDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public FieldDAOImpl() {

    }

    @Override
    public Boolean add7Field(Field model) {
        try {
            String sql = "INSERT INTO `field`(`name`, `status`, `type`, `image`, `combineField1`, `combineField2`, `combineField3`, `createdAt`) VALUES (?,?,?,?,?,?,?,NOW()) ;";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, model.getName());
            ps.setString(2, model.getStatus());
            ps.setString(3, model.getType());
            ps.setString(4, model.getImage());
            ps.setInt(5, model.getCombineField1());
            ps.setInt(6, model.getCombineField2());
            ps.setInt(7, model.getCombineField3());

            ps.executeUpdate();
            conn.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public Boolean add5Field(Field model) {
        try {
            String sql = "INSERT INTO `field`(`name`, `status`, `type`, `image`, `createdAt`) VALUES (?,?,?,?,NOW()) ;";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, model.getName());
            ps.setString(2, model.getStatus());
            ps.setString(3, model.getType());
            ps.setString(4, model.getImage());
            ps.executeUpdate();
            conn.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(Field model) {
        try {
            String sql = "UPDATE `field` SET `name`=?,`status`=?,`type`=?,`image`=?,`combineField1`=?, `combineField2`=?, `combineField3`=?, `updatedAt`=NOW() WHERE `id`= ? ;";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, model.getName());
            ps.setString(2, model.getStatus());
            ps.setString(3, model.getType());
            ps.setString(4, model.getImage());
            ps.setInt(5, model.getCombineField1());
            ps.setInt(6, model.getCombineField2());
            ps.setInt(7, model.getCombineField3());
            ps.setInt(8, model.getId());

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
            String sql = "UPDATE `field` SET `isDeleted` = ? WHERE `id`= ?";

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
    public Field findById(int id) {
        try {
            String sql = "SELECT * FROM `field` WHERE `id` = ? AND isDeleted = 0 ;";

            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                Field pl = new Field();
                pl.setId(rs.getInt("id"));
                pl.setName(rs.getString("name"));
                pl.setStatus(rs.getString("status"));
                pl.setType(rs.getString("type"));
                pl.setImage(rs.getString("image"));
                pl.setCombineField1(rs.getInt("combineField1"));
                pl.setCombineField2(rs.getInt("combineField2"));
                pl.setCombineField3(rs.getInt("combineField3"));
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
    public List<Field> findByStatus(String status) {
        List<Field> statusLists = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `field` WHERE `status` = ? AND isDeleted = 0 ;";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setString(1, status);

            rs = ps.executeQuery();
            while (rs.next()) {
                Field pl = new Field();
                pl.setId(rs.getInt("id"));
                pl.setName(rs.getString("name"));
                pl.setStatus(rs.getString("status"));
                pl.setType(rs.getString("type"));
                pl.setImage(rs.getString("image"));
                pl.setCombineField1(rs.getInt("combineField1"));
                pl.setCombineField2(rs.getInt("combineField2"));
                pl.setCombineField3(rs.getInt("combineField3"));
                pl.setDeleted(Boolean.FALSE);
                pl.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    pl.setUpdatedAt(new Timestamp(updatedAtDate.getTime()));
                }

                statusLists.add(pl);
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusLists;
    }

    @Override
    public List<Field> findAll() {
        List<Field> allLists = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `field` WHERE isDeleted = 0 ;";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                Field pl = new Field();
                pl.setId(rs.getInt("id"));
                pl.setName(rs.getString("name"));
                pl.setStatus(rs.getString("status"));
                pl.setType(rs.getString("type"));
                pl.setImage(rs.getString("image"));
                pl.setCombineField1(rs.getInt("combineField1"));
                pl.setCombineField2(rs.getInt("combineField2"));
                pl.setCombineField3(rs.getInt("combineField3"));
                pl.setDeleted(Boolean.FALSE);
                pl.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    pl.setUpdatedAt(new Timestamp(updatedAtDate.getTime()));
                }

                allLists.add(pl);
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allLists;
    }

    @Override
    public List<Field> findAllCombinedField() {
        List<Field> fieldLists = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `field` WHERE type = ? AND isDeleted = 0 ;";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setString(1, StaticStrings.FIELD_STYLE_7_A_SIZE);

            rs = ps.executeQuery();
            while (rs.next()) {
                Field pl = new Field();
                pl.setId(rs.getInt("id"));
                pl.setName(rs.getString("name"));
                pl.setStatus(rs.getString("status"));
                pl.setType(rs.getString("type"));
                pl.setImage(rs.getString("image"));
                pl.setCombineField1(rs.getInt("combineField1"));
                pl.setCombineField2(rs.getInt("combineField2"));
                pl.setCombineField3(rs.getInt("combineField3"));
                pl.setDeleted(Boolean.FALSE);
                pl.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    pl.setUpdatedAt(new Timestamp(updatedAtDate.getTime()));
                }

                fieldLists.add(pl);
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fieldLists;
    }

    @Override
    public List<Field> findAllNormalFiled() {
        List<Field> fieldLists = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `field` WHERE type = ? AND isDeleted = 0 ;";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setString(1, StaticStrings.FIELD_STYLE_5_A_SIZE);

            rs = ps.executeQuery();
            while (rs.next()) {
                Field pl = new Field();
                pl.setId(rs.getInt("id"));
                pl.setName(rs.getString("name"));
                pl.setStatus(rs.getString("status"));
                pl.setType(rs.getString("type"));
                pl.setImage(rs.getString("image"));
                pl.setDeleted(Boolean.FALSE);
                pl.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    pl.setUpdatedAt(new Timestamp(updatedAtDate.getTime()));
                }

                fieldLists.add(pl);
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fieldLists;
    }

    @Override
    public List<Field> findAllDeleted() {
        List<Field> allLists = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `field` WHERE isDeleted = 1 ;";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                Field pl = new Field();
                pl.setId(rs.getInt("id"));
                pl.setName(rs.getString("name"));
                pl.setStatus(rs.getString("status"));
                pl.setType(rs.getString("type"));
                pl.setImage(rs.getString("image"));
                pl.setCombineField1(rs.getInt("combineField1"));
                pl.setCombineField2(rs.getInt("combineField2"));
                pl.setCombineField3(rs.getInt("combineField3"));
                pl.setDeleted(Boolean.FALSE);
                pl.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    pl.setUpdatedAt(new Timestamp(updatedAtDate.getTime()));
                }

                allLists.add(pl);
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allLists;
    }

    @Override
    public List<Field> findParent(int id) {
        List<Field> allLists = new ArrayList<>();
        try {
            String sql = "SELECT parent.* FROM `field` as child JOIN `field` as parent on child.id = parent.combineField1 or child.id = parent.combineField2 or  child.id = parent.combineField3 WHERE  child.id = ?;";

            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                Field pl = new Field();
                pl.setId(rs.getInt("id"));
                pl.setName(rs.getString("name"));
                pl.setStatus(rs.getString("status"));
                pl.setType(rs.getString("type"));
                pl.setImage(rs.getString("image"));
                pl.setCombineField1(rs.getInt("combineField1"));
                pl.setCombineField2(rs.getInt("combineField2"));
                pl.setCombineField3(rs.getInt("combineField3"));
                pl.setDeleted(Boolean.FALSE);
                pl.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    pl.setUpdatedAt(new Timestamp(updatedAtDate.getTime()));
                }

                allLists.add(pl);
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allLists;
    }

}
