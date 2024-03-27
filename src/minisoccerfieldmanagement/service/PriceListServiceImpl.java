/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import minisoccerfieldmanagement.dao.IPriceListDAO;
import minisoccerfieldmanagement.dao.PriceListDAOImpl;
import minisoccerfieldmanagement.model.PriceList;

/**
 *
 * @author trank
 */
public class PriceListServiceImpl implements IPriceListService{

    IPriceListDAO priceListDAO = new PriceListDAOImpl();
    @Override
    public Boolean add(PriceList model) {
        return priceListDAO.add(model);
    }

    @Override
    public Boolean update(PriceList model) {
        return priceListDAO.update(model);
    }

    @Override
    public Boolean softDelete(int id) {
        return priceListDAO.softDelete(id);
    }

    @Override
    public List<PriceList> findByDateOfWeek(String date) {
        return priceListDAO.findByDateOfWeek(date);
    }

    @Override
    public List<PriceList> findAll() {
        return priceListDAO.findAll();
    }

    @Override
    public PriceList findById(int id) {
        return priceListDAO.findById(id);
    }

    @Override
    public BigDecimal findPriceByTime(Time dateTimeIn, Time dateTimeOut, String date) {
        List<PriceList> priceLists = priceListDAO.findByDateOfWeek(date);

        // Lấy giờ và phút của timeIn và timeOut
        int hourIn = dateTimeIn.toLocalTime().getHour();
        int minuteIn = dateTimeIn.toLocalTime().getMinute();
        int hourOut = dateTimeOut.toLocalTime().getHour();
        int minuteOut = dateTimeOut.toLocalTime().getMinute();

        // Tính tổng số phút giữa timeIn và timeOut
        long durationInMinutes = (hourOut * 60 + minuteOut) - (hourIn * 60 + minuteIn);

        // Tính số đoạn thời gian mỗi 30 phút
        int numberOfSegments = (int) Math.ceil((double) durationInMinutes / 30);

        // Tính tổng giá tiền
        BigDecimal totalPrice = BigDecimal.ZERO;

        // Duyệt qua từng đoạn thời gian mỗi 30 phút
        for (int i = 0; i < numberOfSegments; i++) {
            // Tính thời gian bắt đầu và kết thúc của đoạn thời gian
            int segmentStartHour = hourIn + (minuteIn + i * 30) / 60;
            int segmentStartMinute = (minuteIn + i * 30) % 60;
            int segmentEndHour = hourIn + (minuteIn + (i + 1) * 30) / 60;
            int segmentEndMinute = (minuteIn + (i + 1) * 30) % 60;

            // Duyệt qua danh sách các mục giá
            for (PriceList priceList : priceLists) {
                // Lấy giờ và phút bắt đầu và kết thúc của mục giá
                int startHour = priceList.getStartTime().toLocalTime().getHour();
                int startMinute = priceList.getStartTime().toLocalTime().getMinute();
                int endHour = priceList.getEndTime().toLocalTime().getHour();
                int endMinute = priceList.getEndTime().toLocalTime().getMinute();

                // Kiểm tra xem đoạn thời gian mỗi 30 phút có nằm trong khoảng thời gian của mục giá không
                if ((segmentStartHour > startHour || (segmentStartHour == startHour && segmentStartMinute >= startMinute)) &&
                    (segmentEndHour < endHour || (segmentEndHour == endHour && segmentEndMinute <= endMinute))) {
                    // Nếu có, cộng dồn giá tiền của mục giá vào tổng giá tiền
                    totalPrice = totalPrice.add(priceList.getUnitPricePer30Minutes());
                    break; // Chuyển sang đoạn thời gian tiếp theo
                }
            }
        }
        return totalPrice;
        
    }

    @Override
    public List<PriceList> findByDateOfWeek(String date, String typeField) {
        List<PriceList> pl = findByDateOfWeek(date);
        List<PriceList> selected = new ArrayList<>();
        for (PriceList priceList : pl) {
            if (priceList.getTypeField().equals(typeField))
            {
               selected.add(priceList);
            }
            
        }
        return  selected;
    }
    
}
