/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package minisoccerfieldmanagement.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.mysql.cj.util.Util;
import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import minisoccerfieldmanagement.login.UserSession;
import minisoccerfieldmanagement.model.Booking;
import minisoccerfieldmanagement.model.Customer;
import minisoccerfieldmanagement.model.Match;
import minisoccerfieldmanagement.model.Service;
import minisoccerfieldmanagement.model.ServiceItems;
import minisoccerfieldmanagement.model.ServiceUsage;
import minisoccerfieldmanagement.model.Transaction;
import minisoccerfieldmanagement.model.User;
import minisoccerfieldmanagement.report.model.FieldReportPayment;
import minisoccerfieldmanagement.report.model.ParameterReportPayment;
import minisoccerfieldmanagement.report.print.ReportManager;
import minisoccerfieldmanagement.service.BookingServiceImpl;
import minisoccerfieldmanagement.service.CustomerServiceImpl;
import minisoccerfieldmanagement.service.IBookingService;
import minisoccerfieldmanagement.service.ICustomerService;
import minisoccerfieldmanagement.service.IMatchService;
import minisoccerfieldmanagement.service.IServiceItemsService;
import minisoccerfieldmanagement.service.IServiceService;
import minisoccerfieldmanagement.service.IServiceUsageService;
import minisoccerfieldmanagement.service.IUserService;
import minisoccerfieldmanagement.service.MatchServiceImpl;
import minisoccerfieldmanagement.service.ServiceItemsServiceImpl;
import minisoccerfieldmanagement.service.ServiceServiceImpl;
import minisoccerfieldmanagement.service.ServiceUsageServiceImpl;
import minisoccerfieldmanagement.service.UserServiceImpl;
import minisoccerfieldmanagement.util.ModelItemSell;
import minisoccerfieldmanagement.util.Utils;

/**
 *
 * @author trank
 */
public class PanelTransaction extends javax.swing.JPanel {

    /**
     * Creates new form Transaction
     */
    Transaction transaction;
    Customer customer;
    IMatchService matchService;
    IBookingService bookingService;
    DefaultTableModel tableModel;
    IServiceItemsService serviceItemsService;
    IServiceService serviceService;
    IServiceUsageService serviceUsageService;
    public PanelTransaction(Transaction transaction) {
        initComponents();
        this.transaction = transaction;
        applyTableStyle(tblInvoice);
        matchService = new MatchServiceImpl();
        bookingService = new BookingServiceImpl();
        serviceItemsService = new ServiceItemsServiceImpl();
        serviceService = new ServiceServiceImpl();
        serviceUsageService = new ServiceUsageServiceImpl();
        tableModel = (DefaultTableModel) tblInvoice.getModel();
        setData();
        
    }
    
    private void applyTableStyle(JTable table) {

      
        btnPrint.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/excel.svg",0.35f));
       
        JScrollPane scroll = (JScrollPane) table.getParent().getParent();
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Table.background;"
                + "track:$Table.background;"
                + "trackArc:999");

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        table.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

        //  To Create table alignment
        table.getTableHeader().setDefaultRenderer(getAlignmentCellRender(table.getTableHeader().getDefaultRenderer(), true));
        table.setDefaultRenderer(Object.class, getAlignmentCellRender(table.getDefaultRenderer(Object.class), false));
    }

    private TableCellRenderer getAlignmentCellRender(TableCellRenderer oldRender, boolean header) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = oldRender.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (com instanceof JLabel) {
                    JLabel label = (JLabel) com;
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                   
                   
                }
                return com;
            }
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel10 = new raven.crazypanel.CrazyPanel();
        lblCustomer1 = new javax.swing.JLabel();
        lblCustomer2 = new javax.swing.JLabel();
        lblCustomer3 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInvoice = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblCashier = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblCustomer = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblTotal = new javax.swing.JLabel();
        lblFinalAmount = new javax.swing.JLabel();
        lblAddtionFees = new javax.swing.JLabel();
        lblDiscount = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1188, 696));

        crazyPanel10.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        lblCustomer1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCustomer1.setForeground(new java.awt.Color(196, 204, 90));
        lblCustomer1.setText("MSFM Group");

        lblCustomer2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCustomer2.setText("INVOICE");

        lblCustomer3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCustomer3.setText("TP.HCM");

        lblId.setText("#");

        jLabel1.setText("Date");

        jLabel2.setText("Staff");

        jLabel3.setText("Type");

        tblInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Price", "Total", "Total"
            }
        ));
        jScrollPane1.setViewportView(tblInvoice);

        jLabel4.setText("Subtotal");

        jLabel5.setText("Other");

        jLabel6.setText("Total");

        jLabel7.setText("Discount");

        lblDate.setText("dd/MM/yyyy");

        lblCashier.setText("admin");

        lblType.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblType.setText("retail");

        jLabel8.setText("Customer");

        lblCustomer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCustomer.setText("customer");

        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("0,000 VNĐ");
        lblTotal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTotal.setVerifyInputWhenFocusTarget(false);

        lblFinalAmount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFinalAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFinalAmount.setText("0,000 VNĐ");
        lblFinalAmount.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFinalAmount.setVerifyInputWhenFocusTarget(false);

        lblAddtionFees.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAddtionFees.setText("0,000 VNĐ");
        lblAddtionFees.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblAddtionFees.setVerifyInputWhenFocusTarget(false);

        lblDiscount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDiscount.setText("0,000 VNĐ");
        lblDiscount.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblDiscount.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout crazyPanel10Layout = new javax.swing.GroupLayout(crazyPanel10);
        crazyPanel10.setLayout(crazyPanel10Layout);
        crazyPanel10Layout.setHorizontalGroup(
            crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel10Layout.createSequentialGroup()
                .addGroup(crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crazyPanel10Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(crazyPanel10Layout.createSequentialGroup()
                                .addGroup(crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCashier, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblType, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, crazyPanel10Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblFinalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, crazyPanel10Layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, crazyPanel10Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblAddtionFees, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, crazyPanel10Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(crazyPanel10Layout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addGroup(crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(crazyPanel10Layout.createSequentialGroup()
                                .addComponent(lblCustomer2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblId))
                            .addGroup(crazyPanel10Layout.createSequentialGroup()
                                .addComponent(lblCustomer1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCustomer3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        crazyPanel10Layout.setVerticalGroup(
            crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustomer1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCustomer3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustomer2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblId))
                .addGap(23, 23, 23)
                .addGroup(crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblDate)
                    .addComponent(jLabel3)
                    .addComponent(lblType))
                .addGap(18, 18, 18)
                .addGroup(crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblCashier)
                    .addComponent(jLabel8)
                    .addComponent(lblCustomer))
                .addGap(21, 21, 21)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblTotal))
                .addGap(18, 18, 18)
                .addGroup(crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblAddtionFees))
                .addGap(18, 18, 18)
                .addGroup(crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblDiscount))
                .addGap(18, 18, 18)
                .addGroup(crazyPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblFinalAmount))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(crazyPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnPrint)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPrint)
                    .addComponent(crazyPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        print();
    }//GEN-LAST:event_btnPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private raven.crazypanel.CrazyPanel crazyPanel10;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAddtionFees;
    private javax.swing.JLabel lblCashier;
    private javax.swing.JLabel lblCustomer;
    private javax.swing.JLabel lblCustomer1;
    private javax.swing.JLabel lblCustomer2;
    private javax.swing.JLabel lblCustomer3;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDiscount;
    private javax.swing.JLabel lblFinalAmount;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblType;
    private javax.swing.JTable tblInvoice;
    // End of variables declaration//GEN-END:variables

    private void setData() {
        try {
        lblAddtionFees.setText(Utils.toVND(transaction.getAdditionalFee()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        lblDate.setText(dateFormat.format(transaction.getCreateAt()));
        if (transaction.getDiscountAmount().equals(BigDecimal.ZERO))
        {
            lblDiscount.setText(Utils.toVND(transaction.getDiscountAmount()));
        }
        else
        {
            lblDiscount.setText("-"+ Utils.toVND(transaction.getDiscountAmount()));
        }
        lblTotal.setText(Utils.toVND(transaction.getTotalAmount()));
        lblFinalAmount.setText(Utils.toVND(transaction.getFinalAmount()));
        lblId.setText(String.valueOf(transaction.getId()));
        User cashier = UserSession.getInstance().getUser();
        lblCashier.setText(cashier.getName());
        ICustomerService customerService = new CustomerServiceImpl();
        
        ServiceUsage svServiceUsage = serviceUsageService.findById(transaction.getServiceUsageId());
        customer = customerService.findById(svServiceUsage.getCustomerId());
        if (customer == null)
        {
            lblCustomer.setText("Guest");
        }
        else {
            lblCustomer.setText(customer.getName());
        }
        LoadService(transaction.getServiceUsageId());
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    
    private Object[] getRowItem(ServiceItems serviceItems)
    {   
        Service service = serviceService.findById(serviceItems.getServiceId());
        if (service == null)
        {
            return new Object[]{"Not found", "Not found",serviceItems.getQuantity(),  "Not found"};
        }
        return new Object[]{service.getName(), Utils.toVND(service.getPrice()),serviceItems.getQuantity(),  Utils.toVND(service.getPrice().multiply(new BigDecimal(serviceItems.getQuantity())))};
    }
    
    private void LoadService(int serviceUsageId)
    {
        ServiceUsage serviceUsage = serviceUsageService.findById(serviceUsageId);
        tableModel.setNumRows(0);
        Match match = matchService.findById(serviceUsage.getMatchId());
        if (match != null){
            Booking booking  = bookingService.findById(match.getBookingId());
            if (booking != null)
            {
                Object[] fieldItem = new Object[]{"Field Service",Utils.toVND(booking.getPrice()), 1, Utils.toVND(booking.getPrice())};

                tableModel.addRow(fieldItem);
            }
        }
        List<ServiceItems> serviceItems = serviceItemsService.findByServiceUsage(serviceUsageId);
        
        
        if (!serviceItems.isEmpty())
        {
            for (ServiceItems serviceItem : serviceItems) {
                tableModel.addRow(getRowItem(serviceItem));
            }
        }
    }
    
    private InputStream generateQrcode(BigDecimal total) throws WriterException, IOException {

        String invoice = String.valueOf(total);
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(invoice, BarcodeFormat.QR_CODE, 100, 100, hints);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private void print() { 
        try {
            ReportManager.getInstance().compileReport();
            List<FieldReportPayment> fields = new ArrayList<>();
            String name;
            int qty;
            String price;
            String totalPrice;
            for (int i = 0; i < tblInvoice.getRowCount(); i++) {
                name = String.valueOf(tblInvoice.getValueAt(i, 0));
                price = String.valueOf(tblInvoice.getValueAt(i, 1)).replace(" VND", "").replace(",", "");
                qty = Integer.parseInt(String.valueOf(tblInvoice.getValueAt(i, 2)));
                totalPrice = String.valueOf(tblInvoice.getValueAt(i, 3)).replace(" VND", "").replace(",", "");
                fields.add(new FieldReportPayment(name , qty, Double.parseDouble(price), Double.parseDouble(totalPrice)));
            }
            String dis = Utils.formatVND(BigDecimal.ZERO);
            String other = Utils.formatVND(BigDecimal.ZERO);
            if (transaction.getDiscountAmount() != null && (!transaction.getDiscountAmount().equals(BigDecimal.ZERO)))
                dis = "-" + Utils.formatVND(transaction.getDiscountAmount());
            if (transaction.getAdditionalFee() != null && (!transaction.getFinalAmount().equals(BigDecimal.ZERO)))
                other = Utils.formatVND(transaction.getAdditionalFee());
            ParameterReportPayment dataprint = new ParameterReportPayment(lblCashier.getText(), lblCustomer.getText(),  Utils.formatVND(transaction.getFinalAmount()), generateQrcode(transaction.getFinalAmount()), lblDate.getText(), "#" + String.valueOf(transaction.getId()), Utils.formatVND(transaction.getTotalAmount()), other, dis, fields);
            ReportManager.getInstance().printReportPayment(dataprint);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
