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
"DATE(`Transaction`.createdAt)";
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
   
}
