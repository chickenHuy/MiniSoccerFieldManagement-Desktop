package minisoccerfieldmanagement.dao;

import java.util.List;
import java.sql.Date;
import minisoccerfieldmanagement.model.Booking;

public interface IBookingDAO {

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
}
