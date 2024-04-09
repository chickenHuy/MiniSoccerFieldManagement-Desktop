package minisoccerfieldmanagement.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Component;
import java.awt.Image;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import minisoccerfieldmanagement.model.Customer;
import minisoccerfieldmanagement.service.CustomerServiceImpl;
import minisoccerfieldmanagement.service.ICustomerService;
import minisoccerfieldmanagement.tabbed.TabbedForm;
import minisoccerfieldmanagement.util.TableGradientCell;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import minisoccerfieldmanagement.model.MemberShip;
import minisoccerfieldmanagement.service.IMemberShipService;
import minisoccerfieldmanagement.service.MemberShipServiceImpl;

public class CustomerManagement extends TabbedForm {

    private int index;
    private File tempPicture = null;
    ICustomerService customerService;
    IMemberShipService membershipService;
    DefaultTableModel customerModels;

    public CustomerManagement() {
        initComponents();
        FlatLaf.registerCustomDefaultsSource("tableview");
        customerService = new CustomerServiceImpl();
        membershipService = new MemberShipServiceImpl();
        customerModels = (DefaultTableModel) tblCustomer.getModel();
        applyTableStyle(tblCustomer);
        index = -1;
        loadDataCustomerManagement();
        displayListBookingHistory();
    }

    private void displayListBookingHistory() {

    }

    private void applyTableStyle(JTable table) {
        btnAddNew.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/add.svg", 0.35f));
        btnSave.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/edit.svg", 0.35f));
        btnDelete.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/delete.svg", 0.35f));
        btnPrint.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/excel.svg", 0.35f));
        btnUpload.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/upload.svg", 0.35f));
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/search.svg", 0.35f));
        tblCustomer.setDefaultRenderer(Object.class, new TableGradientCell());
        tblCustomer.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:1,1,1,1,$TableHeader.bottomSeparatorColor,,10");
        tblCustomer.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background");
        jScrollPane1.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:3,0,3,0,$Table.background,10,10");
        jScrollPane1.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "hoverTrackColor:null");
    }

    private void loadDataCustomerManagement() {
        customerModels.setRowCount(0);
        List<Customer> customers = new ArrayList<>();
        customers = customerService.findAll();
        for (Customer customer : customers) {
            customerModels.addRow(getRow(customer));
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tblCustomer.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblCustomer.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        TableCellRenderer backgroundRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    setBackground(table.getSelectionBackground());
                } else {
                    setBackground(table.getBackground());
                }
                return this;
            }
        };
        tblCustomer.getColumnModel().getColumn(1).setCellRenderer(backgroundRenderer);
        tblCustomer.getColumnModel().getColumn(3).setCellRenderer(backgroundRenderer);
        tblCustomer.getColumnModel().getColumn(4).setCellRenderer(backgroundRenderer);
        tblCustomer.setModel(customerModels);
        tblCustomer.getTableHeader().setReorderingAllowed(false);
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
        cboMembership.setSelectedItem("Standard");
        tfName.setText("");
        tfPhoneNumber.setText("");
        tfTotalSpend.setText("");
        tfName.requestFocus();
        tempPicture = null;
        ptbCustomerImage.setImage(null);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        cboMembership = new javax.swing.JComboBox<>();
        lblCustomer = new javax.swing.JLabel();
        lblMembershipId = new javax.swing.JLabel();
        btnUpload = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        lblPhoneNumber = new javax.swing.JLabel();
        tfPhoneNumber = new javax.swing.JTextField();
        lblTotalSpend = new javax.swing.JLabel();
        tfTotalSpend = new javax.swing.JTextField();
        btnAddNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblBookingHistory = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        panelListBookingHistory = new raven.crazypanel.CrazyPanel();
        panelOneBookingHistory = new raven.crazypanel.CrazyPanel();
        lblCustomerName = new com.formdev.flatlaf.extras.components.FlatLabel();
        lblSan = new com.formdev.flatlaf.extras.components.FlatLabel();
        flatLabel3 = new com.formdev.flatlaf.extras.components.FlatLabel();
        ptbBookingHistoryImage = new minisoccerfieldmanagement.util.PictureBox();
        ptbCustomerImage = new minisoccerfieldmanagement.util.PictureBox();

        setMaximumSize(new java.awt.Dimension(1188, 696));
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
        crazyPanel2.add(txtSearch);

        btnPrint.setText("Print");
        crazyPanel2.add(btnPrint);

        crazyPanel1.add(crazyPanel2);

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Membership", "PhoneNumber", "TotalSpend"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
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

        crazyPanel1.add(jScrollPane1);

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

        cboMembership.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Standard", "Silver", "Gold", "Platinum", "Diamond" }));

        lblCustomer.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCustomer.setText("Customer");

        lblMembershipId.setText("Membership");

        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        lblName.setText("Name");

        lblPhoneNumber.setText("PhoneNumber");

        lblTotalSpend.setText("TotalSpend");

        btnAddNew.setText("Add New");
        btnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewActionPerformed(evt);
            }
        });

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

        lblBookingHistory.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblBookingHistory.setText("Booking History");

        lblId.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblId.setForeground(new java.awt.Color(195, 204, 90));
        lblId.setText("#Id");

        lblCustomerName.setText("Nguyễn Minh Khánh");

        lblSan.setText("Sân A - 2h30:4h30");

        flatLabel3.setText("100.000 VND");

        ptbBookingHistoryImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/minisoccerfieldmanagement/image/empty_service.png"))); // NOI18N
        ptbBookingHistoryImage.setMinimumSize(new java.awt.Dimension(70, 70));

        javax.swing.GroupLayout panelOneBookingHistoryLayout = new javax.swing.GroupLayout(panelOneBookingHistory);
        panelOneBookingHistory.setLayout(panelOneBookingHistoryLayout);
        panelOneBookingHistoryLayout.setHorizontalGroup(
            panelOneBookingHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOneBookingHistoryLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(ptbBookingHistoryImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelOneBookingHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(flatLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        panelOneBookingHistoryLayout.setVerticalGroup(
            panelOneBookingHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOneBookingHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOneBookingHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ptbBookingHistoryImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelOneBookingHistoryLayout.createSequentialGroup()
                        .addComponent(lblCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(flatLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelListBookingHistoryLayout = new javax.swing.GroupLayout(panelListBookingHistory);
        panelListBookingHistory.setLayout(panelListBookingHistoryLayout);
        panelListBookingHistoryLayout.setHorizontalGroup(
            panelListBookingHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListBookingHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelOneBookingHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelListBookingHistoryLayout.setVerticalGroup(
            panelListBookingHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListBookingHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelOneBookingHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(192, Short.MAX_VALUE))
        );

        ptbCustomerImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/minisoccerfieldmanagement/image/empty_service.png"))); // NOI18N

        javax.swing.GroupLayout crazyPanel3Layout = new javax.swing.GroupLayout(crazyPanel3);
        crazyPanel3.setLayout(crazyPanel3Layout);
        crazyPanel3Layout.setHorizontalGroup(
            crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, crazyPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBookingHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(crazyPanel3Layout.createSequentialGroup()
                                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(crazyPanel3Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(ptbCustomerImage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(crazyPanel3Layout.createSequentialGroup()
                        .addContainerGap(31, Short.MAX_VALUE)
                        .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(crazyPanel3Layout.createSequentialGroup()
                                .addComponent(btnAddNew)
                                .addGap(18, 18, 18)
                                .addComponent(btnSave)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete))
                            .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(crazyPanel3Layout.createSequentialGroup()
                                    .addComponent(lblTotalSpend)
                                    .addGap(37, 37, 37)
                                    .addComponent(tfTotalSpend, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                                        .addComponent(lblName)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(crazyPanel3Layout.createSequentialGroup()
                                        .addComponent(lblMembershipId)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cboMembership, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                                        .addComponent(lblPhoneNumber)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelListBookingHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpload)
                .addGap(84, 84, 84))
        );
        crazyPanel3Layout.setVerticalGroup(
            crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crazyPanel3Layout.createSequentialGroup()
                        .addComponent(lblCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ptbCustomerImage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpload)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName))
                .addGap(18, 18, 18)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboMembership, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMembershipId))
                .addGap(18, 18, 18)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhoneNumber))
                .addGap(18, 18, 18)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTotalSpend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalSpend))
                .addGap(18, 18, 18)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNew)
                    .addComponent(btnSave)
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblBookingHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelListBookingHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(crazyPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked
        index = tblCustomer.getSelectedRow();
        if (index != -1) {
            lblId.setText("#" + customerModels.getValueAt(index, 0).toString());
            tfName.setText(customerModels.getValueAt(index, 1).toString());
            cboMembership.setSelectedItem(customerModels.getValueAt(index, 2).toString());
            tfPhoneNumber.setText(customerModels.getValueAt(index, 3).toString());
            tfTotalSpend.setText(customerModels.getValueAt(index, 4).toString().replace(" VND", "").replace(",", ""));
            Customer customer = customerService.findById(Integer.parseInt(customerModels.getValueAt(index, 0).toString()));
            if (customer.getImage() == null) {
                ptbCustomerImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/empty_service.png"));
                ptbCustomerImage.repaint();
            } else {
                File file = new File("src/minisoccerfieldmanagement/image/customer/" + customer.getImage());
                if (!file.exists()) {
                    ptbCustomerImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/empty_service.png"));
                    ptbCustomerImage.repaint();
                } else {
                    ptbCustomerImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/customer/" + customer.getImage()));
                    ptbCustomerImage.repaint();
                }
            }
        }
    }//GEN-LAST:event_tblCustomerMouseClicked

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        clearText();
        index = -1;
    }//GEN-LAST:event_btnAddNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        Customer customer = new Customer();
        String name = tfName.getText().trim();
        String membershipName = cboMembership.getSelectedItem().toString().trim();
        String phoneNumber = tfPhoneNumber.getText().trim();
        String totalSpendText = tfTotalSpend.getText().trim();
        if (name.isBlank() || phoneNumber.isBlank() || totalSpendText.isBlank()) {
            MessageAlerts.getInstance().showMessage("Wrong format", "Please do not leave fields blank", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });
        } else {
            if (!phoneNumber.matches("\\d{10}")) {
                MessageAlerts.getInstance().showMessage("Wrong format", "PhoneNumber must contain exactly 10 digits, cannot contain special characters", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                    @Override
                    public void action(PopupController pc, int i) {
                        if (i == MessageAlerts.CLOSED_OPTION) {

                        }
                    }
                });
                return;
            }
            if (!totalSpendText.matches("\\d+")) {
                MessageAlerts.getInstance().showMessage("Wrong format", "TotalSpend cannot contain special characters, cannot be negative, must be a positive integer >= 0", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                    @Override
                    public void action(PopupController pc, int i) {
                        if (i == MessageAlerts.CLOSED_OPTION) {

                        }
                    }
                });
                return;
            }
            int membershipId = membershipService.findIdByName(membershipName);
            customer.setMemberShipId(membershipId);
            customer.setName(name);
            customer.setPhoneNumber(phoneNumber);
            BigDecimal totalSpend = new BigDecimal(totalSpendText);
            if (tempPicture != null) {
                String picturePath = tempPicture.getAbsolutePath();
                String newName = customer.getName().replaceAll(" ", "") + picturePath.substring(picturePath.lastIndexOf('.'));
                customer.setImage(newName);
                if (!saveFile(tempPicture, newName)) {
                    return;
                }
            }
            try {
                customer.setTotalSpend(totalSpend);
                if (index == -1) {
                    if (customerService.checkPhoneNumberExist(phoneNumber)) {
                        MessageAlerts.getInstance().showMessage("Add failed", "This phone number already exists. Please use another phone number.", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                            @Override
                            public void action(PopupController pc, int i) {
                                if (i == MessageAlerts.CLOSED_OPTION) {

                                }
                            }
                        });
                        return;
                    }
                    if (customerService.add(customer)) {
                        MessageAlerts.getInstance().showMessage("Add Success", "Your data has been saved", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                            @Override
                            public void action(PopupController pc, int i) {
                                if (i == MessageAlerts.CLOSED_OPTION) {

                                }
                            }
                        });
                        loadDataCustomerManagement();
                    } else {
                        MessageAlerts.getInstance().showMessage("Add failed", "Please check and try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                            @Override
                            public void action(PopupController pc, int i) {
                                if (i == MessageAlerts.CLOSED_OPTION) {

                                }
                            }
                        });
                    }

                } else {
                    customer.setId(Integer.parseInt(customerModels.getValueAt(index, 0).toString()));
                    if (customerService.checkPhoneNumberExistExceptCurrent(customer.getId(), phoneNumber)) {
                        MessageAlerts.getInstance().showMessage("Update failed", "This phone number already exists! Please use another phone number.", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                            @Override
                            public void action(PopupController pc, int i) {
                                if (i == MessageAlerts.CLOSED_OPTION) {

                                }
                            }
                        });
                        return;
                    }
                    if (customerService.update(customer)) {
                        MessageAlerts.getInstance().showMessage("Update Success", "Your data has been saved", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                            @Override
                            public void action(PopupController pc, int i) {
                                if (i == MessageAlerts.CLOSED_OPTION) {

                                }
                            }
                        });
                        loadDataCustomerManagement();
                    } else {
                        MessageAlerts.getInstance().showMessage("Updated failed", "Please check and try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                            @Override
                            public void action(PopupController pc, int i) {
                                if (i == MessageAlerts.CLOSED_OPTION) {

                                }
                            }
                        });
                    }
                }
            } catch (Exception e) {
                MessageAlerts.getInstance().showMessage("Add/Update failed", "Please check the format again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                    @Override
                    public void action(PopupController pc, int i) {
                        if (i == MessageAlerts.CLOSED_OPTION) {

                        }
                    }
                });
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        DefaultTableModel tblModel = (DefaultTableModel) tblCustomer.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(tblModel);
        tblCustomer.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(txtSearch.getText()));
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (index != -1) {
            MessageAlerts.getInstance().showMessage("Delete Customer", "You definitely want to delete the customer with id: " + customerModels.getValueAt(index, 0).toString(), MessageAlerts.MessageType.WARNING, MessageAlerts.YES_NO_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.YES_OPTION) {
                        int id = Integer.parseInt(customerModels.getValueAt(index, 0).toString());
                        if (customerService.softDelete(id)) {
                            MessageAlerts.getInstance().showMessage("Deleted", "Successfully deleted data", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                                @Override
                                public void action(PopupController pc, int i) {
                                    if (i == MessageAlerts.CLOSED_OPTION) {

                                    }
                                }
                            });
                            customerModels.removeRow(index);
                            clearText();
                        } else {
                            MessageAlerts.getInstance().showMessage("Delete failed", "There was an erro during deletion, please try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                                @Override
                                public void action(PopupController pc, int i) {

                                }
                            });
                        }
                    }
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
                    System.out.print(tempPicture);
                } else {
                    MessageAlerts.getInstance().showMessage("Select failed", "Select image file, please try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                        @Override
                        public void action(PopupController pc, int i) {

                        }
                    });
                }
            } else {
                MessageAlerts.getInstance().showMessage("Select failed", "Select image file, please try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                    @Override
                    public void action(PopupController pc, int i) {

                    }
                });
            }
        }
    }//GEN-LAST:event_btnUploadActionPerformed

    private boolean saveFile(File file, String fileName) {
        File destinationFolder = new File("src/minisoccerfieldmanagement/image/customer");
        System.out.println(destinationFolder.getAbsolutePath());
        try {
            if (!destinationFolder.exists()) {
                destinationFolder.mkdirs();
            }
            File destinationFile = new File(destinationFolder, fileName);
            System.out.println(destinationFile.getAbsolutePath());
            Files.copy(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            MessageAlerts.getInstance().showMessage("Upload Image failed", "Please check and try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNew;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpload;
    private javax.swing.JComboBox<String> cboMembership;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private com.formdev.flatlaf.extras.components.FlatLabel flatLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBookingHistory;
    private javax.swing.JLabel lblCustomer;
    private com.formdev.flatlaf.extras.components.FlatLabel lblCustomerName;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblMembershipId;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPhoneNumber;
    private com.formdev.flatlaf.extras.components.FlatLabel lblSan;
    private javax.swing.JLabel lblTotalSpend;
    private raven.crazypanel.CrazyPanel panelListBookingHistory;
    private raven.crazypanel.CrazyPanel panelOneBookingHistory;
    private minisoccerfieldmanagement.util.PictureBox ptbBookingHistoryImage;
    private minisoccerfieldmanagement.util.PictureBox ptbCustomerImage;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPhoneNumber;
    private javax.swing.JTextField tfTotalSpend;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
