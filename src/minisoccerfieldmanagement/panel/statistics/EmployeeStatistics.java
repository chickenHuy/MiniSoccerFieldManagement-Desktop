/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package minisoccerfieldmanagement.panel.statistics;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import minisoccerfieldmanagement.custom.chart.ModelChart;
import minisoccerfieldmanagement.dao.ChartDAOImpl;
import minisoccerfieldmanagement.dao.IChartDAO;
import minisoccerfieldmanagement.model.User;
import minisoccerfieldmanagement.model.UserChart;
import minisoccerfieldmanagement.service.IUserService;
import minisoccerfieldmanagement.service.UserServiceImpl;
import raven.crazypanel.CrazyPanel;

/**
 *
 * @author trank
 */
public class EmployeeStatistics extends CrazyPanel {
    
    private List<User> listUser;
    private IUserService userService;
    private IChartDAO chartDAO;
    private void generateData(List<java.sql.Date> date , double[][] finalAmount, int size) {
        chart.clear();
        for (int i = 0; i < date.size(); i++){
            double[] doubleList = new double[size];
            for (int j = 0; j < size; j++)
            {
                doubleList[j] = finalAmount[j][i];
            }
            chart.addData(new ModelChart(String.valueOf(date.get(i)), doubleList));
        }
        chart.start();
    }   
    public EmployeeStatistics() {
        initComponents();
        setChart();
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chart = new minisoccerfieldmanagement.custom.chart.CurveLineChart();

        setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        setPreferredSize(new java.awt.Dimension(699, 218));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 699, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private minisoccerfieldmanagement.custom.chart.CurveLineChart chart;
    // End of variables declaration//GEN-END:variables

    private void setChart() {
        userService = new UserServiceImpl();
        listUser = userService.getTopKpi(3);
        chartDAO = new ChartDAOImpl();
        chart.setTitle("KPI data");
        String[] color1 = new String[]{"#7b4397", "#e65c00", "#0099F7"};
        String[] color2 = new String[]{"#dc2430","#F9D423", "#F11712"};
        int vt  = 0;
        List<java.sql.Date> date = new ArrayList<java.sql.Date>();
        double[][] finalAmount = new double[3][100];
        int dateCount = 0;
        if (!listUser.isEmpty()){
        {
            for (User user : listUser) {
                dateCount = 0;
                chart.addLegend(listUser.get(vt).getName(), Color.decode(color1[vt%3]),  Color.decode(color2[vt%3]));

                List<UserChart> userCharts = chartDAO.getUserCharById(user.getId());
                for (UserChart uc: userCharts)
                {
                    if (date.isEmpty() || (!date.contains(uc.getDate())))
                    {
                        date.add(uc.getDate());

                    }
                    finalAmount[vt][dateCount] = uc.getSumTotal().doubleValue();
                    dateCount++;
                }
                vt++;
            }

            generateData(date, finalAmount, listUser.size());
            }
        }
        
        
       
    }
}
