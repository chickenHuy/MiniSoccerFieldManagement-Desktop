/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package minisoccerfieldmanagement.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.mysql.cj.util.Util;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import minisoccerfieldmanagement.login.UserSession;
import minisoccerfieldmanagement.model.Booking;
import minisoccerfieldmanagement.model.Customer;
import minisoccerfieldmanagement.model.Field;
import minisoccerfieldmanagement.model.Match;
import minisoccerfieldmanagement.model.MemberShip;
import minisoccerfieldmanagement.model.Service;
import minisoccerfieldmanagement.model.ServiceItems;
import minisoccerfieldmanagement.model.ServiceUsage;
import minisoccerfieldmanagement.model.Transaction;
import minisoccerfieldmanagement.model.User;
import minisoccerfieldmanagement.service.BookingServiceImpl;
import minisoccerfieldmanagement.service.CustomerServiceImpl;
import minisoccerfieldmanagement.service.IBookingService;
import minisoccerfieldmanagement.service.ICustomerService;
import minisoccerfieldmanagement.service.IMatchService;
import minisoccerfieldmanagement.service.IMemberShipService;
import minisoccerfieldmanagement.service.IServiceItemsService;
import minisoccerfieldmanagement.service.IServiceService;
import minisoccerfieldmanagement.service.IServiceUsageService;
import minisoccerfieldmanagement.service.ITransactionService;
import minisoccerfieldmanagement.service.MatchServiceImpl;
import minisoccerfieldmanagement.service.MemberShipServiceImpl;
import minisoccerfieldmanagement.service.ServiceItemsServiceImpl;
import minisoccerfieldmanagement.service.ServiceServiceImpl;
import minisoccerfieldmanagement.service.ServiceUsageServiceImpl;
import minisoccerfieldmanagement.service.TransactionServiceImpl;
import minisoccerfieldmanagement.tabbed.TabbedForm;
import minisoccerfieldmanagement.tabbed.TabbedItem;
import minisoccerfieldmanagement.tabbed.WindowsTabbed;
import minisoccerfieldmanagement.util.TableGradientCell;
import minisoccerfieldmanagement.util.Utils;
import raven.alerts.MessageAlerts;
import raven.datetime.component.time.TimePicker;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;
import raven.toast.Notifications;

/**
 *
 * @author trank
 */
public class MatchRecord extends TabbedForm {

    TimePicker timePicker1;
    TimePicker timePicker2;
    private BigDecimal serviceFee;

    private Field field;
    private Match match;
    private Customer customer;
    private Booking booking;
    IServiceUsageService serviceUsageService;
    BigDecimal total;
    BigDecimal firstTotal;
    BigDecimal discount;
    BigDecimal rateBig;
    private ServiceUsage serviceUsage;
    ICustomerService customerService;
    IServiceService serviceService;
    List<Service> service;
    DefaultTableModel serviceModel;
    List<ServiceItems> serviceItemses;
    IServiceItemsService serviceItemsService;
    private void setTimePicker() {
        timePicker1 = new TimePicker();
        timePicker1.set24HourView(true);
        timePicker1.setOrientation(SwingConstants.HORIZONTAL);

        timePicker2 = new TimePicker();
        timePicker2.set24HourView(true);
        timePicker2.setOrientation(SwingConstants.HORIZONTAL);

        timePicker1.setEditor(tfStartTime);
        timePicker2.setEditor(tfEndTime);
    }

    public MatchRecord(Match match, Customer customer, Booking booking, Field field) {
        this.match = match;
        this.customer = customer;
        this.booking = booking;
        this.field = field;
        serviceUsage = new ServiceUsage();
        serviceItemsService = new ServiceItemsServiceImpl();
        discount = BigDecimal.ZERO;
        total = BigDecimal.ZERO;
        firstTotal = BigDecimal.ZERO;
        serviceFee = BigDecimal.ZERO;
        initComponents();
        serviceModel = (DefaultTableModel)tblService.getModel();
        applyTableStyle(tblService);
        setTimePicker();
        serviceItemses = new ArrayList<>();
        setData();
        completedBooking();
        createServiceUsage();
        getService();
        setEvent();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel5 = new raven.crazypanel.CrazyPanel();
        lblId1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        crazyPanel4 = new raven.crazypanel.CrazyPanel();
        jLabel4 = new javax.swing.JLabel();
        tfDate = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        tfStartTime = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        tfEndTime = new javax.swing.JFormattedTextField();
        duration = new javax.swing.JLabel();
        crazyPanel9 = new raven.crazypanel.CrazyPanel();
        jLabel6 = new javax.swing.JLabel();
        lblCustomer = new javax.swing.JLabel();
        crazyPanel10 = new raven.crazypanel.CrazyPanel();
        jLabel5 = new javax.swing.JLabel();
        lblCheckin = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblRemaining = new javax.swing.JLabel();
        crazyPanel11 = new raven.crazypanel.CrazyPanel();
        jLabel10 = new javax.swing.JLabel();
        lblField = new javax.swing.JLabel();
        crazyPanel6 = new raven.crazypanel.CrazyPanel();
        lblId2 = new javax.swing.JLabel();
        serviceSectionInMatch1 = new minisoccerfieldmanagement.util.ServiceSectionInMatch();
        crazyPanel7 = new raven.crazypanel.CrazyPanel();
        lblId3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblService = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        crazyPanel8 = new raven.crazypanel.CrazyPanel();
        crazyPanel13 = new raven.crazypanel.CrazyPanel();
        jLabel13 = new javax.swing.JLabel();
        lblFieldPrice = new javax.swing.JLabel();
        crazyPanel14 = new raven.crazypanel.CrazyPanel();
        jLabel14 = new javax.swing.JLabel();
        lblServiceFees = new javax.swing.JLabel();
        crazyPanel15 = new raven.crazypanel.CrazyPanel();
        jLabel15 = new javax.swing.JLabel();
        lblDiscount = new javax.swing.JLabel();
        crazyPanel16 = new raven.crazypanel.CrazyPanel();
        jLabel16 = new javax.swing.JLabel();
        tfPenaltyFee = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblDuration6 = new javax.swing.JLabel();
        crazyPanel12 = new raven.crazypanel.CrazyPanel();
        btnSave = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        taNote = new javax.swing.JTextArea();
        btnSaveNote = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1188, 696));

        crazyPanel5.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        lblId1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblId1.setForeground(new java.awt.Color(195, 204, 90));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Match");

        crazyPanel4.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        jLabel4.setText("Date");
        crazyPanel4.add(jLabel4);

        tfDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        tfDate.setEnabled(false);
        crazyPanel4.add(tfDate);

        jLabel1.setText("From");
        crazyPanel4.add(jLabel1);

        tfStartTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        tfStartTime.setEnabled(false);
        crazyPanel4.add(tfStartTime);

        jLabel2.setText("To");
        crazyPanel4.add(jLabel2);

        tfEndTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        tfEndTime.setEnabled(false);
        crazyPanel4.add(tfEndTime);
        crazyPanel4.add(duration);

        crazyPanel9.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Customer");

        lblCustomer.setText("Trần Khang - 21110497");

        javax.swing.GroupLayout crazyPanel9Layout = new javax.swing.GroupLayout(crazyPanel9);
        crazyPanel9.setLayout(crazyPanel9Layout);
        crazyPanel9Layout.setHorizontalGroup(
            crazyPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCustomer)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        crazyPanel9Layout.setVerticalGroup(
            crazyPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel9Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(crazyPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblCustomer))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        crazyPanel10.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        jLabel5.setText("Check-in");
        crazyPanel10.add(jLabel5);

        lblCheckin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCheckin.setForeground(new java.awt.Color(195, 204, 90));
        lblCheckin.setText("12:30:30");
        crazyPanel10.add(lblCheckin);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel9.setText("Time remaining");
        crazyPanel10.add(jLabel9);

        lblRemaining.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRemaining.setForeground(new java.awt.Color(195, 204, 90));
        lblRemaining.setText("00:00:00");
        crazyPanel10.add(lblRemaining);

        crazyPanel11.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Field");

        lblField.setText("Field 5 -2");

        javax.swing.GroupLayout crazyPanel11Layout = new javax.swing.GroupLayout(crazyPanel11);
        crazyPanel11.setLayout(crazyPanel11Layout);
        crazyPanel11Layout.setHorizontalGroup(
            crazyPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblField)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        crazyPanel11Layout.setVerticalGroup(
            crazyPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel11Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(crazyPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblField))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout crazyPanel5Layout = new javax.swing.GroupLayout(crazyPanel5);
        crazyPanel5.setLayout(crazyPanel5Layout);
        crazyPanel5Layout.setHorizontalGroup(
            crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crazyPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblId1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(crazyPanel5Layout.createSequentialGroup()
                        .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(crazyPanel5Layout.createSequentialGroup()
                                .addComponent(crazyPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(crazyPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(crazyPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                            .addComponent(crazyPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))))
        );
        crazyPanel5Layout.setVerticalGroup(
            crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel5Layout.createSequentialGroup()
                .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crazyPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblId1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(crazyPanel5Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crazyPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(crazyPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(crazyPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        crazyPanel6.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblId2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblId2.setForeground(new java.awt.Color(195, 204, 90));
        crazyPanel6.add(lblId2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 98, 30));
        crazyPanel6.add(serviceSectionInMatch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 400, -1));

        crazyPanel7.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        lblId3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblId3.setForeground(new java.awt.Color(195, 204, 90));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Service");

        tblService.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Service", "Price / Product", "Quantity", "Total"
            }
        ));
        jScrollPane1.setViewportView(tblService);

        btnDelete.setText("Delete Service");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout crazyPanel7Layout = new javax.swing.GroupLayout(crazyPanel7);
        crazyPanel7.setLayout(crazyPanel7Layout);
        crazyPanel7Layout.setHorizontalGroup(
            crazyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel7Layout.createSequentialGroup()
                .addGroup(crazyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(crazyPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, crazyPanel7Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(crazyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblId3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        crazyPanel7Layout.setVerticalGroup(
            crazyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel7Layout.createSequentialGroup()
                .addGroup(crazyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crazyPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(crazyPanel7Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblId3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        crazyPanel8.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        crazyPanel13.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        jLabel13.setText("Field Price");
        crazyPanel13.add(jLabel13);

        lblFieldPrice.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFieldPrice.setForeground(new java.awt.Color(195, 204, 90));
        lblFieldPrice.setText("12:30:30");
        crazyPanel13.add(lblFieldPrice);

        crazyPanel14.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        jLabel14.setText("Service Fees");
        crazyPanel14.add(jLabel14);

        lblServiceFees.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblServiceFees.setForeground(new java.awt.Color(195, 204, 90));
        lblServiceFees.setText("12:30:30");
        crazyPanel14.add(lblServiceFees);

        crazyPanel15.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        jLabel15.setText("Discount");
        crazyPanel15.add(jLabel15);

        lblDiscount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDiscount.setForeground(new java.awt.Color(195, 204, 90));
        lblDiscount.setText("12:30:30");
        crazyPanel15.add(lblDiscount);

        crazyPanel16.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        jLabel16.setText("Penalty Fees");
        crazyPanel16.add(jLabel16);

        tfPenaltyFee.setPreferredSize(new java.awt.Dimension(120, 22));
        tfPenaltyFee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPenaltyFeeKeyReleased(evt);
            }
        });
        crazyPanel16.add(tfPenaltyFee);

        jLabel18.setText("VND");
        crazyPanel16.add(jLabel18);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Payment Details");

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("2,000,000 VNĐ");

        lblDuration6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDuration6.setForeground(new java.awt.Color(195, 204, 90));
        lblDuration6.setText("Total");

        javax.swing.GroupLayout crazyPanel8Layout = new javax.swing.GroupLayout(crazyPanel8);
        crazyPanel8.setLayout(crazyPanel8Layout);
        crazyPanel8Layout.setHorizontalGroup(
            crazyPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(crazyPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crazyPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(crazyPanel8Layout.createSequentialGroup()
                            .addComponent(crazyPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblDuration6))
                        .addComponent(jLabel17)
                        .addComponent(crazyPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(crazyPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                        .addComponent(crazyPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        crazyPanel8Layout.setVerticalGroup(
            crazyPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(crazyPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(crazyPanel8Layout.createSequentialGroup()
                        .addComponent(crazyPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(crazyPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(crazyPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(crazyPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblDuration6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        crazyPanel12.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(196, 204, 90));
        btnSave.setText("CHECKOUT");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        taNote.setColumns(20);
        taNote.setRows(5);
        jScrollPane2.setViewportView(taNote);

        btnSaveNote.setText("Save Note");
        btnSaveNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveNoteActionPerformed(evt);
            }
        });

        jLabel7.setText("Note");

        javax.swing.GroupLayout crazyPanel12Layout = new javax.swing.GroupLayout(crazyPanel12);
        crazyPanel12.setLayout(crazyPanel12Layout);
        crazyPanel12Layout.setHorizontalGroup(
            crazyPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel12Layout.createSequentialGroup()
                .addGroup(crazyPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crazyPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(crazyPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnSave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(crazyPanel12Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(btnSaveNote, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        crazyPanel12Layout.setVerticalGroup(
            crazyPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel12Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSaveNote)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(crazyPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(crazyPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(crazyPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(crazyPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crazyPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 435, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(crazyPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(crazyPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(crazyPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(crazyPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(crazyPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        checkOut();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveNoteActionPerformed
        setSaveNote();
    }//GEN-LAST:event_btnSaveNoteActionPerformed

    private void tfPenaltyFeeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPenaltyFeeKeyReleased
        setTotal();
    }//GEN-LAST:event_tfPenaltyFeeKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveNote;
    private raven.crazypanel.CrazyPanel crazyPanel10;
    private raven.crazypanel.CrazyPanel crazyPanel11;
    private raven.crazypanel.CrazyPanel crazyPanel12;
    private raven.crazypanel.CrazyPanel crazyPanel13;
    private raven.crazypanel.CrazyPanel crazyPanel14;
    private raven.crazypanel.CrazyPanel crazyPanel15;
    private raven.crazypanel.CrazyPanel crazyPanel16;
    private raven.crazypanel.CrazyPanel crazyPanel4;
    private raven.crazypanel.CrazyPanel crazyPanel5;
    private raven.crazypanel.CrazyPanel crazyPanel6;
    private raven.crazypanel.CrazyPanel crazyPanel7;
    private raven.crazypanel.CrazyPanel crazyPanel8;
    private raven.crazypanel.CrazyPanel crazyPanel9;
    private javax.swing.JLabel duration;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCheckin;
    private javax.swing.JLabel lblCustomer;
    private javax.swing.JLabel lblDiscount;
    private javax.swing.JLabel lblDuration6;
    private javax.swing.JLabel lblField;
    private javax.swing.JLabel lblFieldPrice;
    private javax.swing.JLabel lblId1;
    private javax.swing.JLabel lblId2;
    private javax.swing.JLabel lblId3;
    private javax.swing.JLabel lblRemaining;
    private javax.swing.JLabel lblServiceFees;
    private javax.swing.JLabel lblTotal;
    private minisoccerfieldmanagement.util.ServiceSectionInMatch serviceSectionInMatch1;
    private javax.swing.JTextArea taNote;
    private javax.swing.JTable tblService;
    private javax.swing.JFormattedTextField tfDate;
    private javax.swing.JFormattedTextField tfEndTime;
    private javax.swing.JTextField tfPenaltyFee;
    private javax.swing.JFormattedTextField tfStartTime;
    // End of variables declaration//GEN-END:variables

    private void setData() {
        serviceService = new ServiceServiceImpl();
        service = serviceService.findByStatus("active");
        btnSaveNote.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/edit.svg", 0.35f));
        lblCustomer.setText(customer.getName() + " - " + customer.getPhoneNumber());
        lblCheckin.setText(match.getCheckIn().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")));
        lblField.setText(field.getName());
        lblFieldPrice.setText(Utils.toVND(booking.getPrice()));
        tfDate.setValue(Date.from(booking.getTimeStart().toLocalDateTime().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        tfStartTime.setText(formatter.format(booking.getTimeStart().toLocalDateTime().toLocalTime()));
        tfEndTime.setText(formatter.format(booking.getTimeEnd().toLocalDateTime().toLocalTime()));
        customerService = new CustomerServiceImpl();
        IMemberShipService memberShipService = new MemberShipServiceImpl();
        int rate = memberShipService.findDiscountByCustomer(customer.getId());
        rateBig = (new BigDecimal(rate).divide(new BigDecimal(100)));
        updateFee(booking.getPrice());

        lblTotal.setText(Utils.toVND(total));
        lblDiscount.setText(Utils.toVND(discount));
        lblServiceFees.setText(Utils.toVND(serviceFee));

        javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRemainingTime(booking.getTimeEnd().getTime());
            }
        });
        timer.start();
        
        
       
    }

    private void updateRemainingTime(long endTime) {
        long currentTime = System.currentTimeMillis();
        long remainingTime = endTime - currentTime;
        if (remainingTime < 0) {
            remainingTime = 0;
            lblRemaining.setForeground(Color.red);
        }
        long hours = remainingTime / 3600000;
        long minutes = (remainingTime % 3600000) / 60000;
        long seconds = ((remainingTime % 3600000) % 60000) / 1000;

        String remainingTimeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        lblRemaining.setText(remainingTimeString);
    }

    private Object[] getRow(MemberShip membership) {
        DecimalFormat df = new DecimalFormat("#,##0.##");
        return new Object[]{membership.getId(), membership.getName(), df.format(membership.getMinimumSpendAmount()) + " VND", String.valueOf(membership.getDiscountRate())};
    }

    private Object[] getRowService(Service s, int qty) {
        Object[] obj;
        try {
            obj = new Object[]{s.getName(), Utils.formatVND(s.getPrice()), qty, Utils.formatVND(s.getPrice().multiply(new BigDecimal(qty)))};

        } catch (Exception e) {
            return null;
        }
        return obj;
    }

    private void applyTableStyle(JTable table) {

        btnSave.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/edit.svg", 0.35f));
        btnDelete.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/delete.svg", 0.35f));

        table.setDefaultRenderer(Object.class, new TableGradientCell());
        table.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:1,1,1,1,$TableHeader.bottomSeparatorColor,,10");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background");
        jScrollPane1.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:3,0,3,0,$Table.background,10,10");
        jScrollPane1.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "hoverTrackColor:null");

    }

    private void createServiceUsage() {
        serviceUsageService = new ServiceUsageServiceImpl();
        ServiceUsage su = serviceUsageService.findByMatch(match.getId());
        if (su == null) {
            su = new ServiceUsage();
            su.setCustomerId(customer.getId());
            su.setMatchId(match.getId());
            su.setNote("");
            if (serviceUsageService.add(su)) {
                su = serviceUsageService.findByMatch(match.getId());
            }

        }
        taNote.setText(su.getNote());
        serviceUsage = su;

    }

    private void setSaveNote() {
        try {
            String note = taNote.getText();
            if (note.isEmpty()) {
                throw new Exception("Note is empty");
            }
            serviceUsage.setNote(note);
            if (serviceUsageService.update(serviceUsage)) {
                MessageAlerts.getInstance().showMessage("Success", "The note has been saved", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                    @Override
                    public void action(PopupController pc, int i) {
                        if (i == MessageAlerts.CLOSED_OPTION) {

                        }
                    }
                });
            } else {
                throw new Exception("Add Note failed");
            }
        } catch (Exception e) {
            MessageAlerts.getInstance().showMessage("ERROR", e.getMessage(), MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });
        }
    }

    private void checkOut() {

        try {

            ITransactionService transactionService = new TransactionServiceImpl();
            Transaction transaction = new Transaction();
            User user = UserSession.getInstance().getUser();
            transaction.setUserID(user.getId());
            transaction.setServiceUsageId(serviceUsage.getId());

            String fee = tfPenaltyFee.getText();
            if (fee == null || fee.isBlank()) {
                fee = "0";
            }
            BigDecimal feesBigDecimal = new BigDecimal(fee);
            if (feesBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
                throw new Exception("Please Input fee >= 0");
            }
            transaction.setAdditionalFee(feesBigDecimal);
            transaction.setCreateAt(new java.sql.Timestamp(System.currentTimeMillis()));
            transaction.setDiscountAmount(discount);
            transaction.setFinalAmount(total.add(feesBigDecimal));
            transaction.setTotalAmount(firstTotal);
            transaction.setType("Booking Service");
            if (transactionService.add(transaction)) {
                Transaction transaction1 = transactionService.findByServiceUsage(serviceUsage.getId());
                MessageAlerts.getInstance().showMessage("CHECKOUT", "You won be able to go back", MessageAlerts.MessageType.WARNING, MessageAlerts.OK_CANCEL_OPTION, new PopupCallbackAction() {
                    @Override
                    public void action(PopupController pc, int i) {
                        if (i == MessageAlerts.OK_OPTION) {
                            IMatchService matchService = new MatchServiceImpl();
                            matchService.checkOut(match.getId());
                            customerService.updateTotalSpend(customer.getId(), total.subtract(feesBigDecimal));
                            removeAll();
                            PanelTransaction panelTransaction = new PanelTransaction(transaction1);
                            panelTransaction.setSize(1188, 696);
                            add(panelTransaction);
                            validate();
                            repaint();
                        }
                    }
                });

            } else {
                throw new Exception("Make error when save transaction");
            }
        } catch (Exception e) {
            MessageAlerts.getInstance().showMessage("CHECKOUT ERROR", e.getMessage(), MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });
            e.printStackTrace();
        }

    }

    private void updateFee(BigDecimal price) {
        firstTotal = firstTotal.add(price);
        discount = firstTotal.multiply(rateBig);
        total = firstTotal.subtract(discount);
        lblTotal.setText(Utils.toVND(total));
        lblDiscount.setText(Utils.toVND(discount));

    }

    private void completedBooking() {
        IBookingService bookingService = new BookingServiceImpl();
        bookingService.updateStatus(booking.getId(), "completed");
    }

    private void setTotal() {
        try {
            BigDecimal addionalFee = new BigDecimal(tfPenaltyFee.getText());
            if (addionalFee.compareTo(BigDecimal.ZERO) < 0) {
                throw new Exception("Plase add fee >= 0");
            }
            BigDecimal result = total.add(addionalFee);
            lblTotal.setText(Utils.toVND(result));
        } catch (Exception e) {
            tfPenaltyFee.setText("");
            lblTotal.setText(String.valueOf(total));
            MessageAlerts.getInstance().showMessage("Add Fee Failed", e.getMessage(), MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });
        }
    }

    private void getService() {

        serviceSectionInMatch1.addData(service);
        serviceItemses = serviceItemsService.findByServiceUsage(serviceUsage.getId());
        for (ServiceItems si : serviceItemses)
        {
            for (Service se: service)
            {
                if (se.getId() == si.getServiceId())
                {
                    serviceModel.addRow(getRowService(se, si.getQuantity()));
                    updateFee(se.getPrice().multiply(new BigDecimal(si.getQuantity())));
                    setServicePrice(se.getPrice().multiply(new BigDecimal(si.getQuantity())));
                
                }
                
            }
        }
    }

    private void setEvent() {
        serviceSectionInMatch1.setQuantityListener((Service serviceSelected, int quantityOrder) -> {
            try {
                int flag = 0;
            int indexRow = 0;
            Service ser = serviceService.findById(serviceSelected.getId());
            if (ser == null)
            {
                throw new Exception("Service not found");
            }
            else
            if (ser.getQuantity()< quantityOrder)
            {
                throw  new Exception("Please select the appropriate quantity.");
            }
            else
            {
                for (ServiceItems serviceItems : serviceItemses)
            {
                if (serviceItems.getServiceId() == serviceSelected.getId())
                {
                    serviceItems.setQuantity(quantityOrder + serviceItems.getQuantity());
                    serviceModel.setValueAt(serviceItems.getQuantity(), indexRow, 2);
                    serviceModel.setValueAt(Utils.formatVND(serviceSelected.getPrice().multiply(new BigDecimal(serviceItems.getQuantity()))), indexRow, 3);
                    if(serviceItemsService.updateQty(serviceItems.getId(), serviceItems.getQuantity()));
                    {
                        int qty = serviceSelected.getQuantity() - quantityOrder;
                        int sold = serviceSelected.getSold() + quantityOrder;
                        serviceSelected.setQuantity(qty);
                        serviceSelected.setSold(sold);
                        updateFee(serviceSelected.getPrice().multiply(new BigDecimal(quantityOrder)));
                        setServicePrice(serviceSelected.getPrice().multiply(new BigDecimal(quantityOrder)));
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Update Success");
                    }
                    flag = 1;
                    break;
                }
                indexRow ++;
            }
            if (flag == 0){
                ServiceItems si = new ServiceItems();
                si.setQuantity(quantityOrder);
                si.setServiceId(serviceSelected.getId());
                si.setServiceUsageId(serviceUsage.getId());
                serviceItemses.add(si);
                if(serviceItemsService.add(si))
                {
                    int qty = serviceSelected.getQuantity() - quantityOrder;
                    int sold = serviceSelected.getSold() + quantityOrder;
                    serviceSelected.setQuantity(qty);
                    serviceSelected.setSold(sold);
                    serviceService.update(serviceSelected);
                    updateFee(serviceSelected.getPrice().multiply(new BigDecimal(quantityOrder)));
                    setServicePrice(serviceSelected.getPrice().multiply(new BigDecimal(quantityOrder)));
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Success");
                }
                serviceModel.addRow(getRowService(serviceSelected, quantityOrder));
            }
            }
       
            } catch (Exception e) {
                Notifications.getInstance().show(Notifications.Type.ERROR, e.getMessage());
            }
        });
    }
    private void setServicePrice(BigDecimal pr)
    {
        serviceFee = serviceFee.add(pr);
        lblServiceFees.setText(Utils.toVND(serviceFee));
    }

}
