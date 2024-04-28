/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package minisoccerfieldmanagement.panel.statistics;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JLabel;
import minisoccerfieldmanagement.dao.ChartDAOImpl;
import minisoccerfieldmanagement.dao.IChartDAO;
import minisoccerfieldmanagement.model.IncomeChart;
import minisoccerfieldmanagement.util.DateCalculator;
import minisoccerfieldmanagement.util.StaticStrings;
import net.miginfocom.swing.MigLayout;
import raven.chart.ChartLegendRenderer;
import raven.chart.data.category.DefaultCategoryDataset;
import raven.chart.line.LineChart;
import raven.crazypanel.CrazyPanel;

/**
 *
 * @author trank
 */
public class IncomeStatistics extends CrazyPanel {

    IChartDAO chartDAO;
    public IncomeStatistics() {
        initComponents();
        chartDAO = new ChartDAOImpl();
        lineChart.startAnimation();
        createLineChart();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lineChart = new raven.chart.line.LineChart();

        setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        setPreferredSize(new java.awt.Dimension(980, 220));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lineChart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1089, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.chart.line.LineChart lineChart;
    // End of variables declaration//GEN-END:variables

    private void createLineChart() {
        lineChart.setChartType(LineChart.ChartType.CURVE);
        lineChart.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");
        lineChart.setValuesFormat(new DecimalFormat("#,##0.##"));
        createLineChartData();
    }
    
    private void createLineChartData() {
        
        
        List<IncomeChart> finalIncome = chartDAO.getIncomeChart(StaticStrings.FINAL_AMOUNT);
        List<IncomeChart> fieldIncome = chartDAO.getIncomeChart(StaticStrings.FIELD_AMOUNT);
        List<IncomeChart> serviceIncome = chartDAO.getIncomeChart(StaticStrings.SERVICE_AMOUNT);
        
        List<java.sql.Date> dates = new ArrayList<java.sql.Date>();
        for (IncomeChart incomeChart : fieldIncome) {
            if (!dates.contains(incomeChart.getDate()))
            {
                dates.add(incomeChart.getDate());
            }
        }
        for (IncomeChart incomeChart : finalIncome) {
            if (!dates.contains(incomeChart.getDate()))
            {
                dates.add(incomeChart.getDate());
            }
        }
        for (IncomeChart incomeChart : serviceIncome) {
            if (!dates.contains(incomeChart.getDate()))
            {
                dates.add(incomeChart.getDate());
            }
        }
        Set<java.sql.Date> uniqueDates = new TreeSet<>(dates);
        dates = new ArrayList<>(uniqueDates);
                
        
        
        double[][] incomes = new double[3][dates.size()];
        for (IncomeChart incomeChart : fieldIncome) {
            for (int i = 0; i < dates.size(); i++)
            {
                if (dates.get(i).equals(incomeChart.getDate()))
                {
                   incomes[1][i] = incomeChart.getIncome().doubleValue();
                }
            }
        }
        for (IncomeChart incomeChart : finalIncome) {
            for (int i = 0; i < dates.size(); i++)
            {
                if (dates.get(i).equals(incomeChart.getDate()))
                {
                   incomes[0][i] = incomeChart.getIncome().doubleValue();
                }
            }
        }
        for (IncomeChart incomeChart : serviceIncome) {
           for (int i = 0; i < dates.size(); i++)
            {
                if (dates.get(i).equals(incomeChart.getDate()))
                {
                   incomes[2][i] = incomeChart.getIncome().doubleValue();
                }
            }
        }
        
        
        
        
        
        
        
        DefaultCategoryDataset<String, String> categoryDataset = new DefaultCategoryDataset<>();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");
        for (int i = 0; i < dates.size(); i++) {
            String date = df.format(dates.get(i));
            categoryDataset.addValue(incomes[0][i], "Final Income", date);
            categoryDataset.addValue(incomes[1][i], "Field Income", date);
            categoryDataset.addValue(incomes[2][i], "Service Income", date);
        }

        /**
         * Control the legend we do not show all legend
         */
        try {
            Date date = df.parse(categoryDataset.getColumnKey(0));
            Date dateEnd = df.parse(categoryDataset.getColumnKey(categoryDataset.getColumnCount() - 1));

            DateCalculator dcal = new DateCalculator(date, dateEnd);
            long diff = dcal.getDifferenceDays();

            double d = Math.ceil((diff / 10f));
            lineChart.setLegendRenderer(new ChartLegendRenderer() {
                @Override
                public Component getLegendComponent(Object legend, int index) {
                    if (index % d == 0) {
                        return super.getLegendComponent(legend, index);
                    } else {
                        return null;
                    }
                }
            });
        } catch (ParseException e) {
            System.err.println(e);
        }

        lineChart.setCategoryDataset(categoryDataset);
        lineChart.getChartColor().addColor(Color.decode("#38bdf8"), Color.decode("#fb7185"), Color.decode("#34d399"));
        JLabel header = new JLabel("Income Data (VND)");
        header.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1;"
                + "border:0,0,5,0");
        lineChart.setHeader(header);
    }
    
}
