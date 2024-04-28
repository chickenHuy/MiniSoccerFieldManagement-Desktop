/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.dao;

import java.util.List;
import minisoccerfieldmanagement.model.BookingChart;
import minisoccerfieldmanagement.model.CustomerChart;
import minisoccerfieldmanagement.model.IncomeChart;
import minisoccerfieldmanagement.model.UserChart;

/**
 *
 * @author trank
 */
public interface IChartDAO {
    public List<UserChart> getUserCharById(int id);
    public List<CustomerChart> getCustomerChart();
    public List<BookingChart> getBookingChart();
    List<IncomeChart> getIncomeChart(String type);

}
