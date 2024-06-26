package minisoccerfieldmanagement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import minisoccerfieldmanagement.model.Service;

public class ServiceDAOImpl implements IServiceDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ServiceDAOImpl() {

    }

    @Override
    public Boolean add(Service model) {
        try {
            String sql = "INSERT INTO `service`(`name`, `price`, `image`, `description`, `unit`, `quantity`, `sold`, `status`, `createdAt`) VALUES (?,?,?,?,?,?,?,?,NOW()) ;";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, model.getName());
            ps.setBigDecimal(2, model.getPrice());
            ps.setString(3, model.getImage());
            ps.setString(4, model.getDescription());
            ps.setString(5, model.getUnit());
            ps.setInt(6, model.getQuantity());
            ps.setInt(7, model.getSold());
            ps.setString(8, model.getStatus());

            ps.executeUpdate();
            conn.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(Service model) {
        try {
            String sql = "UPDATE `service` SET `name`=?,`price`=?,`image`=?,`description`=?,`unit`=?, `quantity`=?, `sold`=?, `status`=?, `updatedAt`=NOW() WHERE `id`= ? ;";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, model.getName());
            ps.setBigDecimal(2, model.getPrice());
            ps.setString(3, model.getImage());
            ps.setString(4, model.getDescription());
            ps.setString(5, model.getUnit());
            ps.setInt(6, model.getQuantity());
            ps.setInt(7, model.getSold());
            ps.setString(8, model.getStatus());
            ps.setInt(9, model.getId());

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
            String sql = "UPDATE `service` SET `isDeleted` = ? WHERE `id`= ?";

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
    public Service findById(int id) {
        try {
            String sql = "SELECT * FROM `service` WHERE `id` = ? AND isDeleted = 0 ;";

            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                Service pl = new Service();
                pl.setId(rs.getInt("id"));
                pl.setName(rs.getString("name"));
                pl.setPrice(rs.getBigDecimal("price"));
                pl.setImage(rs.getString("image"));
                pl.setDescription(rs.getString("description"));
                pl.setUnit(rs.getString("unit"));
                pl.setQuantity(rs.getInt("quantity"));
                pl.setSold(rs.getInt("sold"));
                pl.setStatus(rs.getString("status"));
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
    public List<Service> findByStatus(String status) {
        List<Service> statusLists = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `service` WHERE `status` = ? AND isDeleted = 0 ;";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setString(1, status);

            rs = ps.executeQuery();
            while (rs.next()) {
                Service pl = new Service();
                pl.setId(rs.getInt("id"));
                pl.setName(rs.getString("name"));
                pl.setPrice(rs.getBigDecimal("price"));
                pl.setImage(rs.getString("image"));
                pl.setDescription(rs.getString("description"));
                pl.setUnit(rs.getString("unit"));
                pl.setQuantity(rs.getInt("quantity"));
                pl.setSold(rs.getInt("sold"));
                pl.setStatus(rs.getString("status"));
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
    public List<Service> findAll() {
        List<Service> statusLists = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `service` WHERE isDeleted = 0 ;";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                Service pl = new Service();
                pl.setId(rs.getInt("id"));
                pl.setName(rs.getString("name"));
                pl.setPrice(rs.getBigDecimal("price"));
                pl.setImage(rs.getString("image"));
                pl.setDescription(rs.getString("description"));
                pl.setUnit(rs.getString("unit"));
                pl.setQuantity(rs.getInt("quantity"));
                pl.setSold(rs.getInt("sold"));
                pl.setStatus(rs.getString("status"));
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
    public Boolean updateStatus(int id, String status) {
        try {
            String sql = "UPDATE `service` SET `status` = ?, `updatedAt` = NOW() WHERE `id` = ?";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, status);
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
    public Boolean updateSoldAndQuantity(int id, int qty) {
        try {
            String sql = "UPDATE `service` SET `sold` = `sold` + ?, `quantity` = quantity - ?, `updatedAt` = NOW() WHERE `id` = ?;";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);

            ps.setInt(1, qty);
            ps.setInt(2, qty);
            ps.setInt(3, id);

            ps.executeUpdate();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Service> loadDataIntoJTable(String keyword, String status, int limit, int offset, String orderBy, String field) {
        List<Service> serviceList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM `service` WHERE `isDeleted` = 0";
            List<Object> params = new ArrayList<>();

            if (keyword != null && !keyword.isEmpty()) {
                sql += " AND (`name` LIKE ?)";
                params.add("%" + keyword + "%");
            }

            if (status != null && !status.isEmpty()) {
                if (status.equals("Active")) {
                    sql += " AND (`status` = 'Active' )";
                } else {
                    sql += " AND (`status` = 'Inactive' )";
                }
            }

            if (orderBy != null && !orderBy.isEmpty() && field != null && !field.isEmpty()) {
                sql += " ORDER BY " + field + " " + orderBy;
            }

            if (limit != -1) {
                if (limit > 0) {
                    sql += " LIMIT ?";
                    params.add(limit);
                }

                if (offset > 0) {
                    sql += " OFFSET ?";
                    params.add(offset);
                }
            }

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof String) {
                    ps.setString(i + 1, (String) param);
                } else if (param instanceof Integer) {
                    ps.setInt(i + 1, (int) param);
                }
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                Service pl = new Service();
                pl.setId(rs.getInt("id"));
                pl.setName(rs.getString("name"));
                pl.setPrice(rs.getBigDecimal("price"));
                pl.setImage(rs.getString("image"));
                pl.setDescription(rs.getString("description"));
                pl.setUnit(rs.getString("unit"));
                pl.setQuantity(rs.getInt("quantity"));
                pl.setSold(rs.getInt("sold"));
                pl.setStatus(rs.getString("status"));
                pl.setDeleted(Boolean.FALSE);
                pl.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    pl.setUpdatedAt(new Timestamp(updatedAtDate.getTime()));
                }
                serviceList.add(pl);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return serviceList;
    }

    @Override
    public List<String> loadServiceNameByKeyword(String keyword, String status) {
        List<String> nameServiceList = new ArrayList<>();

        try {
            String sql = "SELECT name FROM `service` WHERE `isDeleted` = 0";
            List<Object> params = new ArrayList<>();

            if (keyword != null && !keyword.isEmpty()) {
                sql += " AND (`name` LIKE ?)";
                params.add("%" + keyword + "%");
            }

            if (status != null && !status.isEmpty()) {
                if (status.equals("Active")) {
                    sql += " AND (`status` = 'Active' )";
                } else {
                    sql += " AND (`status` = 'Inactive' )";
                }
            }

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof String) {
                    ps.setString(i + 1, (String) param);
                } else if (param instanceof Integer) {
                    ps.setInt(i + 1, (int) param);
                }
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                nameServiceList.add(rs.getString("name"));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nameServiceList;
    }
}
