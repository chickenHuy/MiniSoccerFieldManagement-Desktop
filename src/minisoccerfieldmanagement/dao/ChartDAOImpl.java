/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import minisoccerfieldmanagement.model.BookingChart;
import minisoccerfieldmanagement.model.CustomerChart;
import minisoccerfieldmanagement.model.User;
import minisoccerfieldmanagement.model.UserChart;

/**
 *
 * @author trank
 */
public class ChartDAOImpl implements IChartDAO{
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ChartDAOImpl() {

    }
    @Override
    public List<UserChart> getUserCharById(int id) {
        List<UserChart> models = new ArrayList<>();
        try {
            String sql = "SELECT SUM(finalAmount) as sumTotal, `User`.name, `User`.id, DATE(`Transaction`.createdAt) as `date` from `Transaction` join `User` on `Transaction`.userId = `User`.id where `User`.isDeleted = 0 and `User`.Id = ? and `Transaction`.isDeleted  = 0 Group by `User`.name, `User`.id,\n" +
"DATE(`Transaction`.createdAt)  limit 4";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                UserChart model = new UserChart();
                model.setId(rs.getInt("id"));
                model.setName(rs.getString("name"));
                model.setDate(rs.getDate("date"));
                model.setSumTotal(rs.getBigDecimal("sumTotal"));
                models.add(model);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return models;
    }

    @Override
    public List<CustomerChart> getCustomerChart() {
        List<CustomerChart> models = new ArrayList<>();
        try {
            String sql="SELECT DATE(t.date) AS date, COALESCE(COUNT(c.id), 0) AS new_customers_count FROM (SELECT DATE_SUB(CURDATE(), INTERVAL 0 DAY) AS date UNION ALL SELECT DATE_SUB(CURDATE(), INTERVAL 1 DAY) AS date UNION ALL SELECT DATE_SUB(CURDATE(), INTERVAL 2 DAY) AS date UNION ALL SELECT DATE_SUB(CURDATE(), INTERVAL 3 DAY) AS date) AS t LEFT JOIN Customer c ON DATE(c.createdAt) = t.date GROUP BY DATE(t.date);";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                CustomerChart model = new CustomerChart();
                model.setNumberOfCustomer(rs.getInt("new_customers_count"));
                model.setDate(rs.getDate("date"));
                models.add(model);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return models;
    }
    
    @Override
    public List<BookingChart> getBookingChart() {
        List<BookingChart> models = new ArrayList<>();
        try {
            String sql="SELECT DATE(t.date) AS date, COALESCE(COUNT(c.id), 0) AS new_bookings_count FROM (SELECT DATE_SUB(CURDATE(), INTERVAL 0 DAY) AS date UNION ALL SELECT DATE_SUB(CURDATE(), INTERVAL 1 DAY) AS date UNION ALL SELECT DATE_SUB(CURDATE(), INTERVAL 2 DAY) AS date UNION ALL SELECT DATE_SUB(CURDATE(), INTERVAL 3 DAY) AS date) AS t LEFT JOIN Booking c ON DATE(c.createdAt) = t.date GROUP BY DATE(t.date);";
            conn = new DBConnection().getConnection();

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                BookingChart model = new BookingChart();
                model.setNumberOfBooking(rs.getInt("new_bookings_count"));
                model.setDate(rs.getDate("date"));
                models.add(model);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return models;
    }
   
}
