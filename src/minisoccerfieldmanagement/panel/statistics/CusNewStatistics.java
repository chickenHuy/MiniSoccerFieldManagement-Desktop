package minisoccerfieldmanagement.panel.statistics;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import minisoccerfieldmanagement.dao.ChartDAOImpl;
import minisoccerfieldmanagement.dao.IChartDAO;
import minisoccerfieldmanagement.model.CustomerChart;
import raven.chart.bar.HorizontalBarChart;
import raven.chart.data.pie.DefaultPieDataset;
import raven.crazypanel.CrazyPanel;

public class CusNewStatistics extends CrazyPanel {

    public CusNewStatistics() {
        initComponents();
        horizontalBarChart1.startAnimation();
        createBarChart();
    }

    private void createBarChart() {
        // BarChart 2
        JLabel header2 = new JLabel("Number of new Customer");
        header2.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1;"
                + "border:0,0,5,0");
        horizontalBarChart1.setHeader(header2);
        horizontalBarChart1.setBarColor(Color.decode("#10b981"));
        horizontalBarChart1.setDataset(createData());
        horizontalBarChart1.setValuesFormat(new DecimalFormat("#,#0.00"));
    }
    
    private DefaultPieDataset createData() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        IChartDAO chartDAO = new ChartDAOImpl();
        List<CustomerChart> list = chartDAO.getCustomerChart();
        for (CustomerChart cc : list) {
            dataset.addValue(cc.getDate().toString(), cc.getNumberOfCustomer());
        }
        return dataset;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        horizontalBarChart1 = new raven.chart.bar.HorizontalBarChart();

        setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        setPreferredSize(new java.awt.Dimension(590, 222));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(horizontalBarChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(horizontalBarChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.chart.bar.HorizontalBarChart horizontalBarChart1;
    // End of variables declaration//GEN-END:variables
}
