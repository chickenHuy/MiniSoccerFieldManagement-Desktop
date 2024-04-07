package minisoccerfieldmanagement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import minisoccerfieldmanagement.model.Booking;
import minisoccerfieldmanagement.model.Match;

public class MatchDAOImpl implements IMatchDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public Boolean checkIn(int bookingId) {
        try {
            String sql = "INSERT INTO `Match` (bookingId, checkIn, createdAt) VALUES (?, NOW(), NOW())";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bookingId);

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
    public void checkOut(int id) {
        try {
            String sql = "UPDATE `Match` SET checkOut = NOW() WHERE id = ?";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean softDelete(int id) {
        try {
            String sql = "UPDATE `Match` SET isDeleted = 1 WHERE id = ?";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

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
    public Match findById(int id) {
        Match match = null;
        try {
            String sql = "SELECT * FROM `Match` WHERE id = ? AND isDeleted = 0";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                match = new Match();

                match.setId(rs.getInt("id"));
                match.setBookingId(rs.getInt("bookingId"));
                match.setCheckIn(new Timestamp(rs.getDate("checkIn").getTime()));
                java.util.Date checkOutDate = rs.getDate("checkOut");
                if (checkOutDate != null) {
                    match.setCheckOut(new Timestamp(checkOutDate.getTime()));
                }
                match.setIsDeleted(rs.getBoolean("isDeleted"));
                match.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                java.util.Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    match.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return match;
    }

    @Override
    public Match findByBooking(int bookingId) {
        Match match;
        try {
            String sql = "SELECT * FROM `Match` WHERE bookingId = ? AND isDeleted = 0";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bookingId);
            rs = ps.executeQuery();

            if (rs.next()) {
                match = new Match();
                match.setId(rs.getInt("id"));
                match.setBookingId(rs.getInt("bookingId"));
                match.setCheckIn(new Timestamp(rs.getTimestamp("checkIn").getTime()));
                java.util.Date checkOutDate = rs.getDate("checkOut");
                if (checkOutDate != null) {
                    match.setCheckOut(new Timestamp(checkOutDate.getTime()));
                }
                match.setIsDeleted(rs.getBoolean("isDeleted"));
                match.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                java.util.Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    match.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }
                return match;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Match> findByDate(Date date) {
        List<Match> matches = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `Match` WHERE DATE(checkIn) = DATE(?) AND isDeleted = 0";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, date.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                Match match = new Match();

                match.setId(rs.getInt("id"));
                match.setBookingId(rs.getInt("bookingId"));
                match.setCheckIn(new Timestamp(rs.getDate("checkIn").getTime()));
                java.util.Date checkOutDate = rs.getDate("checkOut");
                if (checkOutDate != null) {
                    match.setCheckOut(new Timestamp(checkOutDate.getTime()));
                }
                match.setIsDeleted(rs.getBoolean("isDeleted"));
                match.setCreatedAt(new Timestamp(rs.getDate("createddAt").getTime()));
                java.util.Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    match.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }
                matches.add(match);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }

    @Override
    public List<Match> findByField(int fieldId) {
        List<Match> matches = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `Match` WHERE fieldId = ? AND isDeleted = 0";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, fieldId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Match match = new Match();

                match.setId(rs.getInt("id"));
                match.setBookingId(rs.getInt("bookingId"));
                match.setCheckIn(new Timestamp(rs.getDate("checkIn").getTime()));
                java.util.Date checkOutDate = rs.getDate("checkOut");
                if (checkOutDate != null) {
                    match.setCheckOut(new Timestamp(checkOutDate.getTime()));
                }
                match.setIsDeleted(rs.getBoolean("isDeleted"));
                match.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                java.util.Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    match.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }
                matches.add(match);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }

    @Override
    public List<Match> findByDateAndField(Date date, int fieldId) {
        List<Match> matches = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `Match` WHERE DATE(checkIn) = DATE(?) AND fieldId = ? AND isDeleted = 0";

            conn = new DBConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, date.toString());
            ps.setInt(2, fieldId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Match match = new Match();

                match.setId(rs.getInt("id"));
                match.setBookingId(rs.getInt("bookingId"));
                match.setCheckIn(new Timestamp(rs.getDate("checkIn").getTime()));
                java.util.Date checkOutDate = rs.getDate("checkOut");
                if (checkOutDate != null) {
                    match.setCheckOut(new Timestamp(checkOutDate.getTime()));
                }
                match.setIsDeleted(rs.getBoolean("isDeleted"));
                match.setCreatedAt(new Timestamp(rs.getDate("createdAt").getTime()));
                java.util.Date updatedAtDate = rs.getDate("updatedAt");
                if (updatedAtDate != null) {
                    match.setUpdateAt(new Timestamp(updatedAtDate.getTime()));
                }
                matches.add(match);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }

}
