/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.util;

import java.awt.Point;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import minisoccerfieldmanagement.model.Booking;
import minisoccerfieldmanagement.model.Field;
import minisoccerfieldmanagement.service.FieldServiceImpl;
import minisoccerfieldmanagement.service.IFieldService;

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
    public static String toVND(BigDecimal price)
    {
        DecimalFormat df = new DecimalFormat("#,##0.## VND");
        return df.format(price);
    }
    public  static BigDecimal toBigDecimal(String price)
    {
        return new BigDecimal(price.replace(" VND", price).replace(",", ""));
    }
    
    public static LocalTime convertTimestampToLocalTime(Timestamp t)
    {
        return t.toLocalDateTime().toLocalTime();
    }
    
    public static java.sql.Date convertUtilDateToSqlDate(Date date)
    {
        return new java.sql.Date(date.getTime());
    }
    
    public static List<Field> blockTime(List<Field> listField7, List<Field> listField5)
    {
        List<Field> result = new ArrayList<>();
        IFieldService fieldService = new FieldServiceImpl();
        for (Field f7 : listField7)
        {
            Field f1 = fieldService.findById(f7.getCombineField1());
            Field f2 = fieldService.findById(f7.getCombineField2());
            Field f3 = fieldService.findById(f7.getCombineField3());
            
            if (f1 != null)
                result.add(f1);
            if (f2 != null)
                result.add(f2);
            if (f3 != null)
                result.add(f3); 
        }
        return result;
    }
    
    public static String formatVND(BigDecimal price) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(price);
    }
}
