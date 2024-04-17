package minisoccerfieldmanagement.report.print;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import minisoccerfieldmanagement.report.model.ParameterReportPayment;

public class ReportManager {

    private static ReportManager instance;

    private JasperReport reportPay;
    private JasperReport reportInvoice;

    public static ReportManager getInstance() {
        if (instance == null) {
            instance = new ReportManager();
        }
        return instance;
    }

    private ReportManager() {
    }

    public void compileReport() throws JRException {
        reportPay = JasperCompileManager.compileReport(getClass().getResourceAsStream("/minisoccerfieldmanagement/report/print/report_pay.jrxml"));
        //reportInvoice = JasperCompileManager.compileReport(getClass().getResourceAsStream("/print/report_invoice.jrxml"));
    }

    public void printReportPayment(ParameterReportPayment data) throws JRException {
        Map para = new HashMap();
        para.put("staff", data.getStaff());
        para.put("customer", data.getCustomer());
        para.put("total", data.getTotal());
        para.put("qrcode", data.getQrcode());
        para.put("subtotal", data.getSubTotal());
        para.put("date", data.getDate());
        para.put("discount", data.getDiscount());
        para.put("other", data.getOther());
        para.put("id", data.getId());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
        JasperPrint print = JasperFillManager.fillReport(reportPay, para, dataSource);
        view(print);
    }


    private void view(JasperPrint print) throws JRException {
        JasperViewer.viewReport(print, false);
    }
}
