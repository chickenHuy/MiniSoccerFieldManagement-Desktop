/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.util;

import java.awt.Point;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import minisoccerfieldmanagement.model.Booking;

/**
 *
 * @author trank
 */
public class Utils {
    public static List<Integer> getFrom(List<Booking> bookings, List<LocalTime> timeSlots) {
    List<Integer> coordinates = new ArrayList<>();
    for (Booking booking : bookings) {
        int startRow = timeSlots.indexOf(booking.getTimeStart().toLocalDateTime().toLocalTime());
        int endRow = timeSlots.indexOf(booking.getTimeEnd().toLocalDateTime().toLocalTime());
        for (int row = startRow; row <= endRow; row++) {
            coordinates.add(row);
        }
    }
    return coordinates;
    }
    
    public static LocalTime convertTimestampToLocalTime(Timestamp t)
    {
        return t.toLocalDateTime().toLocalTime();
    }
    
    public static java.sql.Date convertUtilDateToSqlDate(Date date)
    {
        return new java.sql.Date(date.getTime());
    }
}
