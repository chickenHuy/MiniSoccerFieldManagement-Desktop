/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.form;

/**
 *
 * @author trank
 */
import java.awt.Color;
import java.awt.Component;
import java.math.BigDecimal;
import javax.swing.table.DefaultTableModel;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.swing.table.DefaultTableCellRenderer;
import minisoccerfieldmanagement.model.Booking;
import minisoccerfieldmanagement.model.Field;

public class Scheduler {
    int col;
    int fieldId;
    Timestamp date;
    JTable tableScheduler;
    List<Booking> listBookings;
    public Scheduler(int col, int fieldId, Timestamp date , JTable tableScheduler)
    {
        this.col = col;
        this.fieldId = fieldId;
        this.date = date;
        this.tableScheduler = tableScheduler;
        
        listBookings = new ArrayList<Booking>();
        for (int i = 5; i < 15; i+=2) {
            Booking booking = new Booking();
            booking.setId(i + 1);
            booking.setCustomerId(i + 1);
            booking.setFieldId(1); // All bookings are for field id 1
            booking.setUserId(i + 1);
            booking.setStatus("Booked");
            booking.setNote("Sample booking " + (i + 1));
            booking.setTimeStart(Timestamp.valueOf(LocalDateTime.now().plusHours(i)));
            booking.setTimeEnd(Timestamp.valueOf(LocalDateTime.now().plusHours(i + 1)));
            booking.setPrice(new BigDecimal("100.00"));
            booking.setIsDeleted(false);
            listBookings.add(booking);
    }
        for (int i = 0; i < 10; i++) {
        Booking booking = new Booking();
        booking.setId(i + 1);
        booking.setCustomerId(i + 1);
        booking.setFieldId(1); // All bookings are for field id 1
        booking.setUserId(i + 1);
        booking.setStatus("Booked");
        booking.setNote("Sample booking " + (i + 1));
        booking.setTimeStart(Timestamp.valueOf(LocalDateTime.now().plusHours(i)));
        booking.setTimeEnd(Timestamp.valueOf(LocalDateTime.now().plusHours(i + 1)));
        booking.setPrice(new BigDecimal("100.00"));
        booking.setIsDeleted(false);
        booking.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        booking.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        listBookings.add(booking);
   
        }
        displayBookings(tableScheduler, listBookings);
    }
    public void displayBookings(JTable table, List<Booking> bookings) {
     
         String[] columnNames = {"Time Slot", "Field 1"};

        Object[][] data = new Object[37][2]; // Assuming the table has 37 time slots
        for (int i = 0; i < 37; i++) {
            data[i][0] = LocalTime.of(5, 0).plus(i * 30, ChronoUnit.MINUTES).toString();
            data[i][1] = ""; // Initialize the rest of the fields with empty strings
        }
     for (Booking booking : bookings) {
        int startSlot = (int) ChronoUnit.MINUTES.between(LocalTime.of(5, 0), booking.getTimeStart().toLocalDateTime().toLocalTime()) / 30;
        int endSlot = (int) ChronoUnit.MINUTES.between(LocalTime.of(5, 0), booking.getTimeEnd().toLocalDateTime().toLocalTime()) / 30;
        for (int i = startSlot; i < endSlot; i++) {
            data[i][1] = "Booked";
        }
    }

    // Create table model and set it to the table
    DefaultTableModel model = new DefaultTableModel(data, columnNames);
    table.setModel(model);
    // Set custom renderer for the second column
    table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if ("Booked".equals(value)) {
                setBackground(Color.RED); // Set the background color to red if the cell is booked
            } else {
                setBackground(Color.WHITE); // Set the background color to white otherwise
            }
            return this;
        }
    });
    
}
}