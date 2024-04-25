/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
import raven.popup.component.PopupController;

/**
 *
 * @author trank
 */
public class Excel {
    public static void export(JTable table, String filePath, String title) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Data");
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();
            Row headerRow = sheet.createRow(0);
            Cell headerCell = headerRow.createCell(0);
            headerCell.setCellValue(title);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnCount - 1));
            CellStyle headerCellStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setBold(true);
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCell.setCellStyle(headerCellStyle);
            Row columnHeadersRow = sheet.createRow(1);
            for (int col = 0; col < columnCount; col++) {
                Cell cell = columnHeadersRow.createCell(col);
                cell.setCellValue(model.getColumnName(col));
            }
            for (int row = 0; row < rowCount; row++) {
                Row excelRow = sheet.createRow(row + 2);
                for (int col = 0; col < columnCount; col++) {
                    Cell cell = excelRow.createCell(col);
                    if (model.getValueAt(row, col) != null)
                        cell.setCellValue(model.getValueAt(row, col).toString());
                    else
                        cell.setCellValue("");
                    sheet.autoSizeColumn(col);
                    int columnWidth = sheet.getColumnWidth(col);
                    sheet.setColumnWidth(col, columnWidth + 100);
                }
            }
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
                MessageAlerts.getInstance().showMessage("Save successful", "Data exported to Excel", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                });
                Desktop.getDesktop().open(new File(filePath));
            }
            workbook.close();
        } catch (IOException ex) {
            MessageAlerts.getInstance().showMessage("Save failed", "Error exporting data to Excel", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.CLOSED_OPTION) {

                }
            });
        }
    }
}
