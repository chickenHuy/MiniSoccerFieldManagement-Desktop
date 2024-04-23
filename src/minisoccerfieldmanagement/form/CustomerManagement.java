package minisoccerfieldmanagement.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import minisoccerfieldmanagement.model.Booking;
import minisoccerfieldmanagement.model.Customer;
import minisoccerfieldmanagement.model.MemberShip;
import minisoccerfieldmanagement.service.BookingServiceImpl;
import minisoccerfieldmanagement.service.CustomerServiceImpl;
import minisoccerfieldmanagement.service.IBookingService;
import minisoccerfieldmanagement.service.ICustomerService;
import minisoccerfieldmanagement.service.IMemberShipService;
import minisoccerfieldmanagement.service.MemberShipServiceImpl;
import minisoccerfieldmanagement.tabbed.TabbedForm;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;

public class CustomerManagement extends TabbedForm {

    private int index;
    private File tempPicture = null;
    ICustomerService customerService;
    IMemberShipService membershipService;
    IBookingService bookingService;
    DefaultTableModel customerModels;
    List<Customer> customers;
    private String search = "";
    private int type = -1;
    private int order = 0;

    public CustomerManagement() {
        initComponents();
        FlatLaf.registerCustomDefaultsSource("tableview");
        customerService = new CustomerServiceImpl();
        membershipService = new MemberShipServiceImpl();
        bookingService = new BookingServiceImpl();
        customerModels = (DefaultTableModel) tblCustomer.getModel();
        applyTableStyle(tblCustomer);
        index = -1;
        btnDeleteImage.setEnabled(false);
        loadDataCustomerManagement();
    }

    private void loadListBookingHistory(int customerId) {
        List<Booking> bookings = bookingService.findByCustomer(customerId);
        clearBookingHistory();
        if (bookings.isEmpty()) {
            bookingHistorySection1.showNoBookingMessage();
        } else {
            bookingHistorySection1.addData(bookings);
        }
    }

    private void clearBookingHistory() {
        bookingHistorySection1.clearData();
    }

    private void applyTableStyle(JTable table) {
        btnAddNew.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/add.svg", 0.35f));
        btnSave.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/edit.svg", 0.35f));
        btnDelete.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/delete.svg", 0.35f));
        btnPrint.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/excel.svg", 0.35f));
        btnUpload.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/upload.svg", 0.35f));
        btnDeleteImage.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/clean.svg", 0.35f));
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/search.svg", 0.35f));
        JScrollPane scroll = (JScrollPane) table.getParent().getParent();
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Table.background;"
                + "track:$Table.background;"
                + "trackArc:999");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        table.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        table.getTableHeader().setDefaultRenderer(getAlignmentCellRender(table.getTableHeader().getDefaultRenderer(), true));
        table.setDefaultRenderer(Object.class, getAlignmentCellRender(table.getDefaultRenderer(Object.class), false));
        tfTotalSpend.setText("0");
        tfTotalSpend.setEnabled(false);
        tfTotalSpend.setDisabledTextColor(new java.awt.Color(196, 204, 90));
    }

    private void loadDataCustomerManagement() {
        customerModels.setRowCount(0);
        customers = customerService.findAllAndFilter(search, type, order);
        for (Customer customer : customers) {
            customerModels.addRow(getRow(customer));
        }
    }

    private TableCellRenderer getAlignmentCellRender(TableCellRenderer oldRender, boolean header) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = oldRender.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (com instanceof JLabel) {
                    JLabel label = (JLabel) com;
                    if (column == 0 || column == 3) {
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                    } else if (column == 4) {
                        label.setHorizontalAlignment(SwingConstants.TRAILING);
                    } else {
                        label.setHorizontalAlignment(SwingConstants.LEADING);
                    }
                    if (header == false) {
                        if (isSelected) {
                            com.setForeground(table.getSelectionForeground());
                        } else {
                            com.setForeground(table.getForeground());
                        }

                    }
                }
                return com;
            }
        };
    }

    private Object[] getRow(Customer customer) {
        DecimalFormat df = new DecimalFormat("#,##0.##");
        String membershipName = "";
        MemberShip membership = membershipService.findById(customer.getMemberShipId());
        if (membership != null) {
            membershipName = membership.getName();
        }
        return new Object[]{customer.getId(), customer.getName(), membershipName, customer.getPhoneNumber(), df.format(customer.getTotalSpend()) + " VND"};
    }

    private void clearText() {
        lblId.setText("");
        tfName.setText("");
        tfPhoneNumber.setText("");
        tfTotalSpend.setText("");
        tfName.requestFocus();
        tempPicture = null;
        ptbCustomerImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/profile.jpg"));
        ptbCustomerImage.repaint();
        index = -1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        txtSearch = new javax.swing.JTextField();
        btnPrint = new javax.swing.JButton();
        cbxMembership = new com.formdev.flatlaf.extras.components.FlatComboBox();
        cbxTotalSpend = new com.formdev.flatlaf.extras.components.FlatComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        tfName = new javax.swing.JTextField();
        tfPhoneNumber = new javax.swing.JTextField();
        lblCustomer = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblPhoneNumber = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        ptbCustomerImage = new minisoccerfieldmanagement.util.PictureBox();
        btnUpload = new com.formdev.flatlaf.extras.components.FlatButton();
        btnDeleteImage = new com.formdev.flatlaf.extras.components.FlatButton();
        btnAddNew = new com.formdev.flatlaf.extras.components.FlatButton();
        lblTotalSpend = new com.formdev.flatlaf.extras.components.FlatLabel();
        tfTotalSpend = new com.formdev.flatlaf.extras.components.FlatTextField();
        btnSave = new com.formdev.flatlaf.extras.components.FlatButton();
        btnDelete = new com.formdev.flatlaf.extras.components.FlatButton();
        crazyPanel5 = new raven.crazypanel.CrazyPanel();
        lblBookingHistory = new javax.swing.JLabel();
        bookingHistorySection1 = new minisoccerfieldmanagement.util.BookingHistorySection();

        setPreferredSize(new java.awt.Dimension(1188, 696));

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            null
        ));

        crazyPanel2.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "JTextField.placeholderText=Search;background:@background",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][]",
            "",
            new String[]{
                "width 200"
            }
        ));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        cbxMembership.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Standard", "Silver", "Gold", "Platinum", "Diamond" }));
        cbxMembership.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMembershipActionPerformed(evt);
            }
        });

        cbxTotalSpend.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Increase", "Decrease" }));
        cbxTotalSpend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTotalSpendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout crazyPanel2Layout = new javax.swing.GroupLayout(crazyPanel2);
        crazyPanel2.setLayout(crazyPanel2Layout);
        crazyPanel2Layout.setHorizontalGroup(
            crazyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPrint)
                .addGap(30, 30, 30)
                .addComponent(cbxTotalSpend, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(cbxMembership, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        crazyPanel2Layout.setVerticalGroup(
            crazyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(crazyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint)
                    .addComponent(cbxMembership, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTotalSpend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Membership", "PhoneNumber", "TotalSpend"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCustomer);

        javax.swing.GroupLayout crazyPanel1Layout = new javax.swing.GroupLayout(crazyPanel1);
        crazyPanel1.setLayout(crazyPanel1Layout);
        crazyPanel1Layout.setHorizontalGroup(
            crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
                    .addComponent(crazyPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        crazyPanel1Layout.setVerticalGroup(
            crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(crazyPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        crazyPanel3.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel3.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "",
            "",
            null
        ));
        crazyPanel3.setName(""); // NOI18N

        lblCustomer.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCustomer.setForeground(new java.awt.Color(195, 204, 90));
        lblCustomer.setText("Customer");

        lblName.setText("Name");

        lblPhoneNumber.setText("PhoneNumber");

        lblId.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblId.setForeground(new java.awt.Color(195, 204, 90));

        ptbCustomerImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/minisoccerfieldmanagement/image/profile.jpg"))); // NOI18N

        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        btnDeleteImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteImageActionPerformed(evt);
            }
        });

        btnAddNew.setText("Add New");
        btnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewActionPerformed(evt);
            }
        });

        lblTotalSpend.setText("TotalSpend");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout crazyPanel3Layout = new javax.swing.GroupLayout(crazyPanel3);
        crazyPanel3.setLayout(crazyPanel3Layout);
        crazyPanel3Layout.setHorizontalGroup(
            crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                            .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(crazyPanel3Layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addComponent(lblId))
                                .addComponent(lblCustomer))
                            .addGap(73, 73, 73)
                            .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(crazyPanel3Layout.createSequentialGroup()
                                    .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnDeleteImage, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(17, 17, 17))
                                .addComponent(ptbCustomerImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                            .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblPhoneNumber)
                                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTotalSpend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tfPhoneNumber)
                                .addComponent(tfTotalSpend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(crazyPanel3Layout.createSequentialGroup()
                        .addComponent(btnAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
        );
        crazyPanel3Layout.setVerticalGroup(
            crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crazyPanel3Layout.createSequentialGroup()
                        .addComponent(lblCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblId))
                    .addComponent(ptbCustomerImage, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteImage, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhoneNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTotalSpend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalSpend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        crazyPanel5.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel5.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "",
            "",
            null
        ));
        crazyPanel5.setName(""); // NOI18N

        lblBookingHistory.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblBookingHistory.setForeground(new java.awt.Color(195, 204, 90));
        lblBookingHistory.setText("Booking History");

        javax.swing.GroupLayout crazyPanel5Layout = new javax.swing.GroupLayout(crazyPanel5);
        crazyPanel5.setLayout(crazyPanel5Layout);
        crazyPanel5Layout.setHorizontalGroup(
            crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel5Layout.createSequentialGroup()
                .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crazyPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lblBookingHistory))
                    .addGroup(crazyPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bookingHistorySection1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        crazyPanel5Layout.setVerticalGroup(
            crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBookingHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookingHistorySection1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(crazyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crazyPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(crazyPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(crazyPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked
        index = tblCustomer.getSelectedRow();
        btnDeleteImage.setEnabled(true);
        if (index != -1) {
            tempPicture = null;
            clearBookingHistory();
            lblId.setText("#" + customerModels.getValueAt(index, 0).toString());
            tfName.setText(customerModels.getValueAt(index, 1).toString());
            tfPhoneNumber.setText(customerModels.getValueAt(index, 3).toString());
            tfTotalSpend.setText(customerModels.getValueAt(index, 4).toString().replace(" VND", "").replace(",", ""));
            Customer customer = customerService.findById(Integer.parseInt(customerModels.getValueAt(index, 0).toString()));
            int customerId = Integer.parseInt(customerModels.getValueAt(index, 0).toString());
            loadListBookingHistory(customerId);
            if (customer.getImage() == null || customer.getImage().isEmpty()) {
                ptbCustomerImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/profile.jpg"));
                ptbCustomerImage.repaint();
            } else {
                File file = new File("src/minisoccerfieldmanagement/image/customer/" + customer.getImage());
                if (!file.exists()) {
                    ptbCustomerImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/profile.jpg"));
                    ptbCustomerImage.repaint();
                } else {
                    ptbCustomerImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/customer/" + customer.getImage()));
                    ptbCustomerImage.repaint();
                }
            }
        }
        tfTotalSpend.setEnabled(false);
        tfTotalSpend.setDisabledTextColor(new java.awt.Color(196, 204, 90));
    }//GEN-LAST:event_tblCustomerMouseClicked

    private void cbxMembershipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMembershipActionPerformed
        String membershipNameCbx = cbxMembership.getSelectedItem().toString().trim();
        if (!membershipNameCbx.equals("All")) {
            type = membershipService.findIdByName(membershipNameCbx);
        } else {
            type = -1;
        }
        loadDataCustomerManagement();
    }//GEN-LAST:event_cbxMembershipActionPerformed

    private void cbxTotalSpendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTotalSpendActionPerformed
        String totalSpendString = cbxTotalSpend.getSelectedItem().toString().trim();
        if (totalSpendString.equals("Decrease")) {
            order = -1;
        } else if (totalSpendString.equals("Increase")) {
            order = 1;
        } else {
            order = 0;
        }
        loadDataCustomerManagement();
    }//GEN-LAST:event_cbxTotalSpendActionPerformed

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        clearText();
        index = -1;
        tfTotalSpend.setText("0");
        tfTotalSpend.setEnabled(false);
        tfTotalSpend.setDisabledTextColor(new java.awt.Color(196, 204, 90));
        btnDeleteImage.setEnabled(false);
        clearBookingHistory();
    }//GEN-LAST:event_btnAddNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        clearBookingHistory();
        btnDeleteImage.setEnabled(false);
        Customer customer = new Customer();
        String name = tfName.getText().trim();
        String phoneNumber = tfPhoneNumber.getText().trim();
        String totalSpendText = tfTotalSpend.getText().trim();
        if (name.isBlank() || phoneNumber.isBlank() || totalSpendText.isBlank()) {
            MessageAlerts.getInstance().showMessage("Wrong format", "Please do not leave fields blank", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.CLOSED_OPTION) {

                }
            });
        } else {
            if (!phoneNumber.matches("\\d{10}")) {
                MessageAlerts.getInstance().showMessage("Wrong format", "Phone number must contain exactly 10 digits, cannot contain special characters", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                });
                return;
            }
            if (!totalSpendText.matches("\\d+")) {
                MessageAlerts.getInstance().showMessage("Wrong format", "Total spend cannot contain special characters, cannot be negative, must be a positive integer >= 0", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                });
                return;
            }
            customer.setName(name);
            customer.setPhoneNumber(phoneNumber);
            BigDecimal totalSpend = new BigDecimal(totalSpendText);
            customer.setTotalSpend(totalSpend);
            if (tempPicture != null) {
                String picturePath = tempPicture.getAbsolutePath();
                String uuid = UUID.randomUUID().toString();
                String newName = uuid + picturePath.substring(picturePath.lastIndexOf('.'));
                customer.setImage(newName);
                if (!saveFile(tempPicture, newName)) {
                    return;
                }
            } else {
                customer.setImage(customer.getImage());
            }
            if (index == -1) {
                String membershipName = "Standard";
                int membershipId = membershipService.findIdByName(membershipName);
                customer.setMemberShipId(membershipId);
                if (customerService.checkPhoneNumberExist(phoneNumber)) {
                    MessageAlerts.getInstance().showMessage("Add failed", "This phone number already exists. Please use another phone number.", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                        if (i == MessageAlerts.CLOSED_OPTION) {

                        }
                    });
                    return;
                }
                if (customerService.add(customer)) {
                    MessageAlerts.getInstance().showMessage("Add successful", "Your data has been saved", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                        if (i == MessageAlerts.CLOSED_OPTION) {

                        }
                    });
                    clearText();
                    tfTotalSpend.setText("0");
                    type = -1;
                    loadDataCustomerManagement();
                } else {
                    MessageAlerts.getInstance().showMessage("Add failed", "Please check and try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                        if (i == MessageAlerts.CLOSED_OPTION) {

                        }
                    });
                }

            } else {
                int customerId = Integer.parseInt(customerModels.getValueAt(index, 0).toString());
                customer.setId(customerId);
                Customer cus = customerService.findById(customerId);
                customer.setMemberShipId(cus.getMemberShipId());
                if (customerService.checkPhoneNumberExistExceptCurrent(customer.getId(), phoneNumber)) {
                    MessageAlerts.getInstance().showMessage("Update failed", "This phone number already exists! Please use another phone number.", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                        if (i == MessageAlerts.CLOSED_OPTION) {

                        }
                    });
                    return;
                }
                if (tblCustomer.getSelectedRowCount() == 1) {
                    if (customerService.update(customer)) {
                        MessageAlerts.getInstance().showMessage("Update successful", "Your data has been saved", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                            if (i == MessageAlerts.CLOSED_OPTION) {

                            }
                        });
                        clearText();
                        tfTotalSpend.setText("0");
                        type = -1;
                        loadDataCustomerManagement();
                    } else {
                        MessageAlerts.getInstance().showMessage("Updated failed", "Please check and try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                            if (i == MessageAlerts.CLOSED_OPTION) {

                            }
                        });
                    }
                } else {
                    if (tblCustomer.getSelectedRowCount() == 0) {
                        MessageAlerts.getInstance().showMessage("Update failed", "Please select a customer to update", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                            if (i == MessageAlerts.CLOSED_OPTION) {

                            }
                        });
                    }
                }
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        search = txtSearch.getText().trim();
        loadDataCustomerManagement();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (index != -1) {
            MessageAlerts.getInstance().showMessage("Delete customer", "You definitely want to delete customer with id : " + customerModels.getValueAt(index, 0).toString(), MessageAlerts.MessageType.WARNING, MessageAlerts.YES_NO_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.YES_OPTION) {
                    int id = Integer.parseInt(customerModels.getValueAt(index, 0).toString());
                    if (customerService.softDelete(id)) {
                        MessageAlerts.getInstance().showMessage("Delete successful", "Customer has been deleted", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, (PopupController pc1, int i1) -> {
                            if (i1 == MessageAlerts.CLOSED_OPTION) {
                            }
                        });
                        customerModels.removeRow(index);
                        clearText();
                        tfTotalSpend.setText("0");
                        tfTotalSpend.setEnabled(false);
                        tfTotalSpend.setDisabledTextColor(new java.awt.Color(196, 204, 90));
                        clearBookingHistory();
                        btnDeleteImage.setEnabled(false);
                        type = -1;
                    } else {
                        MessageAlerts.getInstance().showMessage("Delete failed", "An error occurred while deleting, please try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc1, int i1) -> {
                        });
                    }
                }
            });
        } else {
            MessageAlerts.getInstance().showMessage("Delete failed", "Please select a customer to delete", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.CLOSED_OPTION) {

                }
            });
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("* .Image", "jpg", "png");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                String fileName = selectedFile.getName();
                if (fileName.endsWith(".jpg") || fileName.endsWith(".JPG") || fileName.endsWith(".png") || fileName.endsWith(".PNG")) {
                    String filePath = selectedFile.getAbsolutePath();
                    ImageIcon myImage = new ImageIcon(filePath);
                    Image image = myImage.getImage().getScaledInstance(ptbCustomerImage.getWidth(), ptbCustomerImage.getHeight(), Image.SCALE_SMOOTH);
                    ptbCustomerImage.setImage(new ImageIcon(image));
                    ptbCustomerImage.repaint();
                    tempPicture = fileChooser.getSelectedFile();
                    btnDeleteImage.setEnabled(false);
                } else {
                    MessageAlerts.getInstance().showMessage("Select failed", "Select image file is not suitable, please select .jpg or .png file", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                    });
                }
            } else {
                MessageAlerts.getInstance().showMessage("Select failed", "Select image file is not suitable, please select .jpg or .png file", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                });
            }
        }
    }//GEN-LAST:event_btnUploadActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx";
            }
            exportToExcel(tblCustomer, filePath);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void exportToExcel(JTable table, String filePath) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Data");
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();
            Row headerRow = sheet.createRow(0);
            Cell headerCell = headerRow.createCell(0);
            String membershipName = cbxMembership.getSelectedItem().toString();
            if ("All".equals(membershipName)) {
                headerCell.setCellValue("Customer list");
            } else {
                headerCell.setCellValue("Customer list with membership is " + membershipName);
            }
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnCount - 1));
            CellStyle headerCellStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setBold(true);
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCell.setCellStyle(headerCellStyle);
            Row columnHeadersRow = sheet.createRow(1);
            for (int col = 0; col < columnCount; col++) {
                Cell cell = columnHeadersRow.createCell(col);
                cell.setCellValue(model.getColumnName(col));
            }
            for (int row = 0; row < rowCount; row++) {
                Row excelRow = sheet.createRow(row + 2);
                for (int col = 0; col < columnCount; col++) {
                    Cell cell = excelRow.createCell(col);
                    cell.setCellValue(model.getValueAt(row, col).toString());
                    sheet.autoSizeColumn(col);
                    int columnWidth = sheet.getColumnWidth(col);
                    sheet.setColumnWidth(col, columnWidth + 100);
                }
            }
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
                MessageAlerts.getInstance().showMessage("Save successful", "Data exported to Excel", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                });
                Desktop.getDesktop().open(new File(filePath));
            }
            workbook.close();
        } catch (IOException ex) {
            MessageAlerts.getInstance().showMessage("Save failed", "Error exporting data to Excel", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.CLOSED_OPTION) {

                }
            });
        }
    }

    private boolean saveFile(File file, String fileName) {
        File destinationFolder = new File("src/minisoccerfieldmanagement/image/customer");
        try {
            if (!destinationFolder.exists()) {
                destinationFolder.mkdirs();
            }
            File destinationFile = new File(destinationFolder, fileName);
            Files.copy(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            MessageAlerts.getInstance().showMessage("Upload image failed", "Please check and try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.CLOSED_OPTION) {

                }
            });
            return false;
        }
    }

    private void btnDeleteImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteImageActionPerformed
        int customerId = Integer.parseInt(customerModels.getValueAt(index, 0).toString());
        Customer customerNew = customerService.findById(customerId);
        if (customerNew.getImage() == null || customerNew.getImage().isEmpty() || customerNew.getImage().equals("profile.jpg")) {
            MessageAlerts.getInstance().showMessage("Delete failed", "No image to delete", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.CLOSED_OPTION) {

                }
            });
        } else {
            File file = new File("src/minisoccerfieldmanagement/image/customer/" + customerNew.getImage());
            if (file.exists()) {
                if (file.delete()) {
                    customerNew.setId(customerId);
                    customerNew.setMemberShipId(customerNew.getMemberShipId());
                    customerNew.setName(customerNew.getName());
                    customerNew.setPhoneNumber(customerNew.getPhoneNumber());
                    customerNew.setTotalSpend(customerNew.getTotalSpend());
                    customerNew.setImage(null);
                    ptbCustomerImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/profile.jpg"));
                    ptbCustomerImage.repaint();
                    try {
                        if (customerService.update(customerNew)) {
                            MessageAlerts.getInstance().showMessage("Delete successful", "Image has been deleted", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                                @Override
                                public void action(PopupController pc, int i) {
                                    if (i == MessageAlerts.CLOSED_OPTION) {

                                    }
                                }
                            });
                            type = -1;
                            btnDeleteImage.setEnabled(false);
                            loadDataCustomerManagement();
                        } else {
                            MessageAlerts.getInstance().showMessage("Delete failed", "An error occurred while deleting image", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                                @Override
                                public void action(PopupController pc, int i) {
                                    if (i == MessageAlerts.CLOSED_OPTION) {

                                    }
                                }
                            });
                        }
                    } catch (Exception e) {
                        MessageAlerts.getInstance().showMessage("Delete failed", "An error occurred while deleting image", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                            @Override
                            public void action(PopupController pc, int i) {
                                if (i == MessageAlerts.CLOSED_OPTION) {

                                }
                            }
                        });
                    }
                }
            }
        }
    }//GEN-LAST:event_btnDeleteImageActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private minisoccerfieldmanagement.util.BookingHistorySection bookingHistorySection1;
    private com.formdev.flatlaf.extras.components.FlatButton btnAddNew;
    private com.formdev.flatlaf.extras.components.FlatButton btnDelete;
    private com.formdev.flatlaf.extras.components.FlatButton btnDeleteImage;
    private javax.swing.JButton btnPrint;
    private com.formdev.flatlaf.extras.components.FlatButton btnSave;
    private com.formdev.flatlaf.extras.components.FlatButton btnUpload;
    private com.formdev.flatlaf.extras.components.FlatComboBox cbxMembership;
    private com.formdev.flatlaf.extras.components.FlatComboBox cbxTotalSpend;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private raven.crazypanel.CrazyPanel crazyPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBookingHistory;
    private javax.swing.JLabel lblCustomer;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPhoneNumber;
    private com.formdev.flatlaf.extras.components.FlatLabel lblTotalSpend;
    private minisoccerfieldmanagement.util.PictureBox ptbCustomerImage;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPhoneNumber;
    private com.formdev.flatlaf.extras.components.FlatTextField tfTotalSpend;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

}
