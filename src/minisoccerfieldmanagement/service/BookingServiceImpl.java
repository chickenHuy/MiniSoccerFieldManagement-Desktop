/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.service;

import java.sql.Date;
import java.util.List;
import minisoccerfieldmanagement.dao.BookingDAOImpl;
import minisoccerfieldmanagement.dao.IBookingDAO;
import minisoccerfieldmanagement.model.Booking;
import minisoccerfieldmanagement.util.StaticStrings;

/**
 *
 * @author trank
 */
public class BookingServiceImpl implements IBookingService{

    IBookingDAO bookingDao;
    public BookingServiceImpl()
    {
        bookingDao = new BookingDAOImpl();
    }
    @Override
    public Boolean add(Booking booking) {
        return bookingDao.add(booking);
    }

    @Override
    public Boolean updateStatus(int id, String status) {
        return bookingDao.updateStatus(id, status);
    }

    @Override
    public Boolean softDelete(int id) {
        return bookingDao.softDelete(id);
    }

    @Override
    public Booking findById(int id) {
        return bookingDao.findById(id);
    }

    @Override
    public List<Booking> findByCustomer(int customerId) {
        return bookingDao.findByCustomer(customerId);
    }

    @Override
    public List<Booking> findByUser(int userId) {
        return bookingDao.findByUser(userId);
    }

    @Override
    public List<Booking> findByField(int fieldId) {
        return bookingDao.findByField(fieldId);
    }

    @Override
    public List<Booking> findByStatus(String status) {
        return bookingDao.findByStatus(status);
    }

    @Override
    public List<Booking> findByDate(Date date) {
        return bookingDao.findByDate(date);
    }

    @Override
    public List<Booking> findByDateAndField(Date date, int fieldId) {
        return bookingDao.findByDateAndField(date, fieldId);
    }
    
    public List<Booking> findByDateAndFieldType(Date date, String typeField)
    {
        if (typeField.equals(StaticStrings.FIELD_STYLE_5_A_SIZE))
        {
            return bookingDao.findByDateAndTypeField(date, typeField);
        }
        else if (typeField.equals(StaticStrings.FIELD_STYLE_7_A_SIZE))
        {
            return bookingDao.findByDateAndTypeField(date, typeField);
        }
        return bookingDao.findByDate(date);
    }

    @Override
    public List<Booking> findByDateAndPhoneKeyWord(Date date, String keyword) {
        return bookingDao.findByDateAndPhoneKeyWord(date, keyword);
    }

    @Override
    public List<Booking> findByDateAndFieldAndPhoneKeyWord(Date date, int fieldId, String keyword) {
        return bookingDao.findByDateAndFieldAndPhoneKeyWord(date, fieldId, keyword);
    }
}
