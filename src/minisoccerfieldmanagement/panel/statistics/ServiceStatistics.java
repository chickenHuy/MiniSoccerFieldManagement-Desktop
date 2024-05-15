package minisoccerfieldmanagement.panel.statistics;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import minisoccerfieldmanagement.model.Service;
import minisoccerfieldmanagement.service.ServiceServiceImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import raven.alerts.MessageAlerts;
import raven.chart.data.category.DefaultCategoryDataset;
import raven.chart.data.pie.DefaultPieDataset;
import raven.chart.data.pie.PieDataset;
import raven.crazypanel.CrazyPanel;
import raven.popup.component.PopupController;

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
        setComponentZOrder(buttonPrint, 0);

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
                        numberService = 6;
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
        buttonPrint = new javax.swing.JButton();

        setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        setPreferredSize(new java.awt.Dimension(450, 218));

        comboBoxNumberService.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3", "5", "7" }));

        buttonPrint.setText("Print");
        buttonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(289, 289, 289)
                .addComponent(buttonPrint)
                .addGap(9, 9, 9)
                .addComponent(comboBoxNumberService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(buttonPrint))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(comboBoxNumberService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(193, Short.MAX_VALUE))
            .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx";
            }
            exportChartDataToExcel(filePath, pieChart1.getDataset());
        }
    }//GEN-LAST:event_buttonPrintActionPerformed

    public void exportChartDataToExcel(String filePath, PieDataset dataset) {
    try {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        // Create header row
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Income Statistics");

        CellStyle headerCellStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 11);
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCell.setCellStyle(headerCellStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));

        // Create column headers
        Row columnHeadersRow = sheet.createRow(1);
        String[] headers = {"Service name", "Solded"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = columnHeadersRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        CellStyle leftAlignCellStyle = workbook.createCellStyle();
        leftAlignCellStyle.setAlignment(HorizontalAlignment.LEFT);

        // Populate data rows
        int rowNum = 2;
        for (int i = 0; i < dataset.getItemCount(); i++) {
            Row row = sheet.createRow(rowNum++);
            String category = dataset.getKey(i).toString();

            Cell categoryCell = row.createCell(0);
            categoryCell.setCellValue(category);
            categoryCell.setCellStyle(leftAlignCellStyle);

            Cell valueCell = row.createCell(1);
            valueCell.setCellValue(dataset.getValue(i).doubleValue());
            valueCell.setCellStyle(leftAlignCellStyle);
        }

        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the workbook to the file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            workbook.close();

            // Show success message
            MessageAlerts.getInstance().showMessage("Save successful", "Data exported to Excel", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.CLOSED_OPTION) {
                    // Do nothing
                }
            });

            // Open the file if Desktop is supported
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(filePath));
            }
        }
    } catch (IOException ex) {
        // Show error message
        MessageAlerts.getInstance().showMessage("Save failed", "Error exporting data to Excel", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
            if (i == MessageAlerts.CLOSED_OPTION) {
                // Do nothing
            }
        });
    }
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonPrint;
    private javax.swing.JComboBox<String> comboBoxNumberService;
    private raven.chart.pie.PieChart pieChart1;
    // End of variables declaration//GEN-END:variables
}
