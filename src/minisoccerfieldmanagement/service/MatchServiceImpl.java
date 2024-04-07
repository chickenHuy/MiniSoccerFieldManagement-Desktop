package minisoccerfieldmanagement.service;

import java.sql.Date;
import java.util.List;
import minisoccerfieldmanagement.dao.MatchDAOImpl;
import minisoccerfieldmanagement.model.Match;

public class MatchServiceImpl implements IMatchService {

    MatchDAOImpl matchDaoImpl = new MatchDAOImpl();

    @Override
    public Boolean checkIn(int bookingId) {
        return matchDaoImpl.checkIn(bookingId);
    }

    @Override
    public void checkOut(int id) {
        matchDaoImpl.checkOut(id);
    }

    @Override
    public Boolean softDelete(int id) {
        return matchDaoImpl.softDelete(id);
    }

    @Override
    public Match findById(int id) {
        return matchDaoImpl.findById(id);
    }

    @Override
    public List<Match> findByBooking(int bookingId) {
        return matchDaoImpl.findByBooking(bookingId);
    }

    @Override
    public List<Match> findByDate(Date date) {
        return matchDaoImpl.findByDate(date);
    }

    @Override
    public List<Match> findByField(int fieldId) {
        return matchDaoImpl.findByField(fieldId);
    }

    @Override
    public List<Match> findByDateAndField(Date date, int fieldId) {
        return matchDaoImpl.findByDateAndField(date, fieldId);
    }

}
