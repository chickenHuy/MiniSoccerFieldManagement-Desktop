/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.service;

import java.sql.Date;
import java.util.List;
import minisoccerfieldmanagement.model.Booking;

/**
 *
 * @author trank
 */
public interface IBookingService {

    Boolean add(Booking booking);

    Boolean updateStatus(int id, String status);

    Boolean softDelete(int id);

    Booking findById(int id);

    List<Booking> findByCustomer(int customerId);

    List<Booking> findByUser(int userId);

    List<Booking> findByField(int fieldId);

    List<Booking> findByStatus(String status);

    List<Booking> findByDate(Date date);

    List<Booking> findByDateAndField(Date date, int fieldId);
    List<Booking> findByDateAndFieldType(Date date, String typeField);
}
