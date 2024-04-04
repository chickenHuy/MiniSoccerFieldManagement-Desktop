package minisoccerfieldmanagement.dao;

import java.util.List;
import java.sql.Date;
import minisoccerfieldmanagement.model.Match;


public interface IMatchDAO {

    Boolean checkIn(int bookingId);

    void checkOut(int id);

    Boolean softDelete(int id);

    Match findById(int id);

    List<Match> findByBooking(int bookingId);

    List<Match> findByDate(Date date);

    List<Match> findByField(int fieldId);

    List<Match> findByDateAndField(Date date, int fieldId);
}
