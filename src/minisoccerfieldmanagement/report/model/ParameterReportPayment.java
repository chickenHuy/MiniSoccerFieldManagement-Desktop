package minisoccerfieldmanagement.report.model;

import java.io.InputStream;
import java.util.List;

public class ParameterReportPayment {

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public InputStream getQrcode() {
        return qrcode;
    }

    public void setQrcode(InputStream qrcode) {
        this.qrcode = qrcode;
    }

    public List<FieldReportPayment> getFields() {
        return fields;
    }

    public void setFields(List<FieldReportPayment> fields) {
        this.fields = fields;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }


    public ParameterReportPayment(String staff, String customer, String total, InputStream qrcode, String date, String id, String subTotal, String other, String discount, List<FieldReportPayment> fields) {
        this.staff = staff;
        this.customer = customer;
        this.total = total;
        this.qrcode = qrcode;
        this.date = date;
        this.id = id;
        this.subTotal = subTotal;
        this.other = other;
        this.discount = discount;
        this.fields = fields;
    }
    
    public ParameterReportPayment() {
    }

    private String staff;
    private String customer;
    private String total;
    private InputStream qrcode;
    private String date;
    private String id;
    private String subTotal;
    private String other;
    private String discount;
    private List<FieldReportPayment> fields;
}
