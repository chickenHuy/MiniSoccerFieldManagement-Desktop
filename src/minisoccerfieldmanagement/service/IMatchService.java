package minisoccerfieldmanagement.service;

import java.sql.Date;
import java.util.List;
import minisoccerfieldmanagement.model.Match;

public interface IMatchService {

    Boolean checkIn(int bookingId);

    void checkOut(int id);

    Boolean softDelete(int id);

    Match findById(int id);

    List<Match> findByBooking(int bookingId);

    List<Match> findByDate(Date date);

    List<Match> findByField(int fieldId);

    List<Match> findByDateAndField(Date date, int fieldId);
}
