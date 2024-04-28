package minisoccerfieldmanagement.panel.statistics;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JLabel;
import minisoccerfieldmanagement.model.Service;
import minisoccerfieldmanagement.service.ServiceServiceImpl;
import raven.chart.data.pie.DefaultPieDataset;
import raven.crazypanel.CrazyPanel;

public class ServiceStatistics extends CrazyPanel {

    private final ServiceServiceImpl serviceService = new ServiceServiceImpl();
    private List<Service> listService = new ArrayList<>();
    private int numberService = 3;
    private int numberSold = 0;

    public ServiceStatistics() {
        initComponents();

        setWidgit();
        getListService();

        createPieChart();
    }

    private void setWidgit() {
        setComponentZOrder(comboBoxNumberService, 0);

        comboBoxNumberService.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBoxNumberService.getSelectedIndex();
                if (selectedIndex == 0) {
                    numberService = 3;
                } else {
                    if (selectedIndex == 1) {
                        numberService = 5;

                    } else {
                        numberService = 9;
                    }
                }
                createPieChart();
            }
        });
    }

    private void getListService() {
        listService = serviceService.loadDataIntoJTable("", "", -1, 0, "DESC", "sold");
        getNumberSold();
    }

    private void getNumberSold() {
        for (Service service : listService) {
            numberSold += service.getSold();
        }
        System.out.println(numberSold);
    }

    private void createPieChart() {
        JLabel header1 = new JLabel("Sold Services");
        header1.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+1");
        pieChart1.setHeader(header1);

        pieChart1.getChartColor().addColor(Color.decode("#f87171"), Color.decode("#fb923c"), Color.decode("#fbbf24"), Color.decode("#a3e635"), Color.decode("#34d399"), Color.decode("#22d3ee"), Color.decode("#818cf8"), Color.decode("#c084fc"), Color.decode("#d53f8c"), Color.decode("#e53e3e"));
        pieChart1.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20");
        pieChart1.setDataset(createPieData());
    }

    private DefaultPieDataset createPieData() {
        int otherServiceSold = numberSold;

        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

        for (int i = 0; i < numberService; i++) {
            dataset.addValue("[" + i + "] " + listService.get(i).getName(), listService.get(i).getSold());
            otherServiceSold -= listService.get(i).getSold();
        }
        Random random = new Random();
        dataset.addValue("Other Services", otherServiceSold);

        return dataset;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pieChart1 = new raven.chart.pie.PieChart();
        comboBoxNumberService = new javax.swing.JComboBox<>();

        setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        setPreferredSize(new java.awt.Dimension(450, 218));

        comboBoxNumberService.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3", "5", "10" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(comboBoxNumberService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(comboBoxNumberService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxNumberService;
    private raven.chart.pie.PieChart pieChart1;
    // End of variables declaration//GEN-END:variables
}
