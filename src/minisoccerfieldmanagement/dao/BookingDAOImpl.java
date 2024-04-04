package minisoccerfieldmanagement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import minisoccerfieldmanagement.model.Booking;

public class BookingDAOImpl implements IBookingDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public Boolean add(Booking booking) {
        try {
            String sql = "INSERT INTO Booking (customerId, fieldId, userId, status, note, timeStart, timeEnd, price, createdAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, booking.getCustomerId());
            ps.setInt(2, booking.getFieldId());
            ps.setInt(3, booking.getUserId());
            ps.setString(4, booking.getStatus());
            ps.setString(5, booking.getNote());
            ps.setTimestamp(6, booking.getTimeStart());
            ps.setTimestamp(7, booking.getTimeEnd());
            ps.setBigDecimal(8, booking.getPrice());

            int rowsAffected = ps.executeUpdate();

            conn.close();

            if (rowsAffected > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean updateStatus(int id, String status) {
        try {
            String sql = "UPDATE Booking SET status = ? WHERE id = ?";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, id);

            int rowsAffected = ps.executeUpdate();

            conn.close();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean softDelete(int id) {
        try {
            String sql = "UPDATE Booking SET isDeleted = ? WHERE id = ?";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setBoolean(1, true);
            ps.setInt(2, id);

            int rowsAffected = ps.executeUpdate();

            conn.close();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Booking findById(int id) {
        Booking booking = null;
        try {
            String sql = "SELECT * FROM Booking WHERE id = ? AND isDeleted = 0";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setCustomerId(rs.getInt("customerId"));
                booking.setFieldId(rs.getInt("fieldId"));
                booking.setUserId(rs.getInt("userId"));
                booking.setStatus(rs.getString("status"));
                booking.setNote(rs.getString("note"));
                booking.setTimeStart(rs.getTimestamp("timeStart"));
                booking.setTimeEnd(rs.getTimestamp("timeEnd"));
                booking.setPrice(rs.getBigDecimal("price"));
                booking.setIsDeleted(rs.getBoolean("isDeleted"));
                booking.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                java.util.Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    booking.setUpdatedAt(new Timestamp(updatedAtDate.getTime()));
                }
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booking;
    }

    @Override
    public List<Booking> findByCustomer(int customerId) {
        List<Booking> bookings = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Booking WHERE customerId = ? AND isDeleted = 0";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setCustomerId(rs.getInt("customerId"));
                booking.setFieldId(rs.getInt("fieldId"));
                booking.setUserId(rs.getInt("userId"));
                booking.setStatus(rs.getString("status"));
                booking.setNote(rs.getString("note"));
                booking.setTimeStart(rs.getTimestamp("timeStart"));
                booking.setTimeEnd(rs.getTimestamp("timeEnd"));
                booking.setPrice(rs.getBigDecimal("price"));
                booking.setIsDeleted(rs.getBoolean("isDeleted"));
                booking.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                java.util.Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    booking.setUpdatedAt(new Timestamp(updatedAtDate.getTime()));
                }

                bookings.add(booking);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public List<Booking> findByUser(int userId) {
        List<Booking> bookings = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Booking WHERE userId = ? AND isDeleted = 0";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setCustomerId(rs.getInt("customerId"));
                booking.setFieldId(rs.getInt("fieldId"));
                booking.setUserId(rs.getInt("userId"));
                booking.setStatus(rs.getString("status"));
                booking.setNote(rs.getString("note"));
                booking.setTimeStart(rs.getTimestamp("timeStart"));
                booking.setTimeEnd(rs.getTimestamp("timeEnd"));
                booking.setPrice(rs.getBigDecimal("price"));
                booking.setIsDeleted(rs.getBoolean("isDeleted"));
                booking.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                java.util.Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    booking.setUpdatedAt(new Timestamp(updatedAtDate.getTime()));
                }

                bookings.add(booking);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public List<Booking> findByField(int fieldId) {
        List<Booking> bookings = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Booking WHERE fieldId = ? AND isDeleted = 0";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, fieldId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setCustomerId(rs.getInt("customerId"));
                booking.setFieldId(rs.getInt("fieldId"));
                booking.setUserId(rs.getInt("userId"));
                booking.setStatus(rs.getString("status"));
                booking.setNote(rs.getString("note"));
                booking.setTimeStart(rs.getTimestamp("timeStart"));
                booking.setTimeEnd(rs.getTimestamp("timeEnd"));
                booking.setPrice(rs.getBigDecimal("price"));
                booking.setIsDeleted(rs.getBoolean("isDeleted"));
                booking.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                java.util.Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    booking.setUpdatedAt(new Timestamp(updatedAtDate.getTime()));
                }

                bookings.add(booking);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public List<Booking> findByStatus(String status) {
        List<Booking> bookings = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Booking WHERE status = ? AND isDeleted = 0";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, status);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setCustomerId(rs.getInt("customerId"));
                booking.setFieldId(rs.getInt("fieldId"));
                booking.setUserId(rs.getInt("userId"));
                booking.setStatus(rs.getString("status"));
                booking.setNote(rs.getString("note"));
                booking.setTimeStart(rs.getTimestamp("timeStart"));
                booking.setTimeEnd(rs.getTimestamp("timeEnd"));
                booking.setPrice(rs.getBigDecimal("price"));
                booking.setIsDeleted(rs.getBoolean("isDeleted"));
                booking.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                java.util.Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    booking.setUpdatedAt(new Timestamp(updatedAtDate.getTime()));
                }

                bookings.add(booking);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public List<Booking> findByDate(Date date) {
        List<Booking> bookings = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Booking WHERE DATE(timeStart) = DATE(?) and isDeleted = 0";

            conn = new DBConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date.toString());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setCustomerId(rs.getInt("customerId"));
                booking.setFieldId(rs.getInt("fieldId"));
                booking.setUserId(rs.getInt("userId"));
                booking.setStatus(rs.getString("status"));
                booking.setNote(rs.getString("note"));
                booking.setTimeStart(rs.getTimestamp("timeStart"));
                booking.setTimeEnd(rs.getTimestamp("timeEnd"));
                booking.setPrice(rs.getBigDecimal("price"));
                booking.setIsDeleted(rs.getBoolean("isDeleted"));
                booking.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                java.util.Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    booking.setUpdatedAt(new Timestamp(updatedAtDate.getTime()));
                }

                bookings.add(booking);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public List<Booking> findByDateAndField(Date date, int fieldId) {
        List<Booking> bookings = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Booking WHERE DATE(timeStart) = DATE(?) AND fieldId = ? and isDeleted = 0";

            conn = new DBConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date.toString());
            ps.setInt(2, fieldId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setCustomerId(rs.getInt("customerId"));
                booking.setFieldId(rs.getInt("fieldId"));
                booking.setUserId(rs.getInt("userId"));
                booking.setStatus(rs.getString("status"));
                booking.setNote(rs.getString("note"));
                booking.setTimeStart(rs.getTimestamp("timeStart"));
                booking.setTimeEnd(rs.getTimestamp("timeEnd"));
                booking.setPrice(rs.getBigDecimal("price"));
                booking.setIsDeleted(rs.getBoolean("isDeleted"));
                booking.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                java.util.Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    booking.setUpdatedAt(new Timestamp(updatedAtDate.getTime()));
                }

                bookings.add(booking);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public List<Booking> findByDateAndTypeField(Date date, String typeField) {
        List<Booking> bookings = new ArrayList<>();
        try {
            String sql = "SELECT Booking.id, customerId, fieldId, userId, Booking.status, Booking.note, Booking.timeStart, Booking.timeEnd, Booking.price, Booking.isDeleted, Booking.createdAt, Booking.updatedAt FROM Booking JOIN Field ON Booking.fieldId = Field.id\n WHERE DATE(timeStart) = DATE(?) AND Field.isDeleted = 0 AND Field.type = ?";

            conn = new DBConnection().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date.toString());
            ps.setString(2, typeField);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setCustomerId(rs.getInt("customerId"));
                booking.setFieldId(rs.getInt("fieldId"));
                booking.setUserId(rs.getInt("userId"));
                booking.setStatus(rs.getString("status"));
                booking.setNote(rs.getString("note"));
                booking.setTimeStart(rs.getTimestamp("timeStart"));
                booking.setTimeEnd(rs.getTimestamp("timeEnd"));
                booking.setPrice(rs.getBigDecimal("price"));
                booking.setIsDeleted(rs.getBoolean("isDeleted"));
                booking.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                java.util.Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    booking.setUpdatedAt(new Timestamp(updatedAtDate.getTime()));
                }

                bookings.add(booking);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

}
