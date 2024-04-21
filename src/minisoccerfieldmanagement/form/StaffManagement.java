package minisoccerfieldmanagement.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Component;
import java.awt.Desktop;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import minisoccerfieldmanagement.tabbed.TabbedForm;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import minisoccerfieldmanagement.model.User;
import minisoccerfieldmanagement.service.IUserService;
import minisoccerfieldmanagement.service.UserServiceImpl;
import java.sql.Timestamp;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import minisoccerfieldmanagement.datetime.component.date.DatePicker;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StaffManagement extends TabbedForm {

    private int index;
    IUserService userService;
    DefaultTableModel userModels;
    private ButtonGroup genderButtonGroup;
    DatePicker dateChooser;
    List<User> users;
    private String search = "";

    public StaffManagement() {
        initComponents();
        pickGender();
        FlatLaf.registerCustomDefaultsSource("tableview");
        userService = new UserServiceImpl();
        userModels = (DefaultTableModel) tblStaff.getModel();
        applyTableStyle(tblStaff);
        index = -1;
        btnSave.setEnabled(false);
        setDateChooser();
        loadDataStaffManagement();
    }

    private void pickGender() {
        genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(rbtnMale);
        genderButtonGroup.add(rbtnFemale);
        genderButtonGroup.add(rbtnOther);
    }
    
    private void setDateChooser() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateChooser = new DatePicker();
        dateChooser.now();
        dateChooser.setEditor(tfDateOfBirth);
        tfDateOfBirth.setText(dateFormat.format(new Timestamp(System.currentTimeMillis())));
        tfDateOfBirth.setEditable(false);
    }

    private void applyTableStyle(JTable table) {
        btnAddNew.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/add.svg", 0.35f));
        btnSave.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/edit.svg", 0.35f));
        btnDelete.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/delete.svg", 0.35f));
        btnPrint.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/excel.svg", 0.35f));
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
    }

    private void loadDataStaffManagement() {
        userModels.setRowCount(0);
        users = userService.findAllAndFilter(search);
        for (User user : users) {
            userModels.addRow(getRow(user));
        }
    }

    private TableCellRenderer getAlignmentCellRender(TableCellRenderer oldRender, boolean header) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = oldRender.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (com instanceof JLabel) {
                    JLabel label = (JLabel) com;
                    if (column == 0 || column == 4 || column == 7) {
                        label.setHorizontalAlignment(SwingConstants.CENTER);
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

    private Object[] getRow(User user) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDateOfBirth = dateFormat.format(user.getDateOfBirth());
        String passwordHidden = "*".repeat(user.getPassword().length());
        return new Object[]{user.getId(), user.getName(), user.getGender(), formattedDateOfBirth, user.getPhoneNumber(), user.getUserName(), passwordHidden, user.getRole()};
    }

    private void clearText() {
        tfName.setText("");
        tfPhoneNumber.setText("");
        tfDateOfBirth.setText("");
        tfUsername.setText("");
        tfName.requestFocus();
        index = -1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStaff = new javax.swing.JTable();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        txtSearch = new javax.swing.JTextField();
        btnPrint = new javax.swing.JButton();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        lblName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        btnAddNew = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblName1 = new javax.swing.JLabel();
        lblName2 = new javax.swing.JLabel();
        lblName3 = new javax.swing.JLabel();
        tfPhoneNumber = new javax.swing.JTextField();
        tfUsername = new javax.swing.JTextField();
        lblName4 = new javax.swing.JLabel();
        rbtnMale = new com.formdev.flatlaf.extras.components.FlatRadioButton();
        rbtnFemale = new com.formdev.flatlaf.extras.components.FlatRadioButton();
        rbtnOther = new com.formdev.flatlaf.extras.components.FlatRadioButton();
        btnSave = new javax.swing.JButton();
        lblEmployeeDetails = new javax.swing.JLabel();
        tfDateOfBirth = new javax.swing.JFormattedTextField();

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

        tblStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Gender", "Date Of Birth", "Phone Number", "Username", "Password", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStaffMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStaff);

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

        javax.swing.GroupLayout crazyPanel2Layout = new javax.swing.GroupLayout(crazyPanel2);
        crazyPanel2.setLayout(crazyPanel2Layout);
        crazyPanel2Layout.setHorizontalGroup(
            crazyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 453, Short.MAX_VALUE)
                .addComponent(btnPrint)
                .addContainerGap())
        );
        crazyPanel2Layout.setVerticalGroup(
            crazyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(crazyPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout crazyPanel1Layout = new javax.swing.GroupLayout(crazyPanel1);
        crazyPanel1.setLayout(crazyPanel1Layout);
        crazyPanel1Layout.setHorizontalGroup(
            crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(crazyPanel1Layout.createSequentialGroup()
                        .addComponent(crazyPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        crazyPanel1Layout.setVerticalGroup(
            crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
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

        lblName.setText("Name");

        btnAddNew.setText("Add New");
        btnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lblName1.setText("Gender");

        lblName2.setText("Date Of Birth");

        lblName3.setText("Phone Number");

        lblName4.setText("Username");

        rbtnMale.setText("Male");

        rbtnFemale.setText("Female");

        rbtnOther.setText("Other");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblEmployeeDetails.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblEmployeeDetails.setForeground(new java.awt.Color(196, 204, 90));
        lblEmployeeDetails.setText("Employee Details");

        javax.swing.GroupLayout crazyPanel3Layout = new javax.swing.GroupLayout(crazyPanel3);
        crazyPanel3.setLayout(crazyPanel3Layout);
        crazyPanel3Layout.setHorizontalGroup(
            crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(crazyPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblName1)
                            .addComponent(lblName)
                            .addComponent(lblName2)
                            .addComponent(lblName4)
                            .addComponent(lblName3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(crazyPanel3Layout.createSequentialGroup()
                                .addComponent(lblEmployeeDetails)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(tfDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(crazyPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tfUsername)
                                .addComponent(tfName, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(crazyPanel3Layout.createSequentialGroup()
                                    .addComponent(rbtnMale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(rbtnFemale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(rbtnOther, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(tfPhoneNumber, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(crazyPanel3Layout.createSequentialGroup()
                                .addComponent(btnAddNew)
                                .addGap(18, 18, 18)
                                .addComponent(btnSave)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete)))))
                .addGap(38, 38, 38))
        );
        crazyPanel3Layout.setVerticalGroup(
            crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblEmployeeDetails)
                .addGap(65, 65, 65)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(crazyPanel3Layout.createSequentialGroup()
                        .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblName)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblName1)
                            .addComponent(rbtnMale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbtnFemale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbtnOther, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblName2)
                            .addComponent(tfDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addComponent(tfPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblName3))
                .addGap(49, 49, 49)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName4)
                    .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNew)
                    .addComponent(btnSave)
                    .addComponent(btnDelete))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(crazyPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(crazyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        clearText();
        index = -1;
        btnSave.setEnabled(true);
    }//GEN-LAST:event_btnAddNewActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (index != -1) {
            MessageAlerts.getInstance().showMessage("Delete customer", "You definitely want to delete the customer with id : " + userModels.getValueAt(index, 0).toString(), MessageAlerts.MessageType.WARNING, MessageAlerts.YES_NO_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.YES_OPTION) {
                    int id = Integer.parseInt(userModels.getValueAt(index, 0).toString());
                    if (userService.softDelete(id)) {
                        MessageAlerts.getInstance().showMessage("Deleted", "Successfully deleted customer", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, (PopupController pc1, int i1) -> {
                            if (i1 == MessageAlerts.CLOSED_OPTION) {
                            }
                        });
                        userModels.removeRow(index);
                        clearText();
                    } else {
                        MessageAlerts.getInstance().showMessage("Delete failed", "There was an erro during deletion, please try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc1, int i1) -> {
                        });
                    }
                }
            });
        } else {
            MessageAlerts.getInstance().showMessage("Delete failed", "Please select a staff to delete", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.CLOSED_OPTION) {

                }
            });
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void savePasswordToFile(String username, String password) {
        String fileName = username + ".txt";
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            writer.println("Username : " + username);
            writer.println("Password : " + password);
        } catch (IOException e) {
            MessageAlerts.getInstance().showMessage("Error", "Save the file failed, please try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.CLOSED_OPTION) {

                }
            });
            return;
        }
        openFile(fileName);
    }

    private void openFile(String fileName) {
        try {
            java.awt.Desktop.getDesktop().open(new java.io.File(fileName));
        } catch (IOException ex) {
            MessageAlerts.getInstance().showMessage("Error", "Open the file failed, please try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.CLOSED_OPTION) {

                }
            });
        }
    }

    private void tblStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStaffMouseClicked
        index = tblStaff.getSelectedRow();
        if (index != -1) {
            tfName.setText(userModels.getValueAt(index, 1).toString());
            String gender = userModels.getValueAt(index, 2).toString();
            if (gender.equals("Male")) {
                rbtnMale.setSelected(true);
            } else if (gender.equals("Female")) {
                rbtnFemale.setSelected(true);
            } else {
                rbtnOther.setSelected(true);
            }
            tfDateOfBirth.setText(userModels.getValueAt(index, 3).toString());
            tfPhoneNumber.setText(userModels.getValueAt(index, 4).toString());
            tfUsername.setText(userModels.getValueAt(index, 5).toString());
            btnSave.setEnabled(false);
        }
    }//GEN-LAST:event_tblStaffMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        User user = new User();
        String name = tfName.getText().trim();
        String gender = "";
        if (rbtnMale.isSelected()) {
            gender = "Male";
        } else if (rbtnFemale.isSelected()) {
            gender = "Female";
        } else if (rbtnOther.isSelected()) {
            gender = "Other";
        }
        String strDateOfBirth = tfDateOfBirth.getText().trim();
        String phoneNumber = tfPhoneNumber.getText().trim();
        String username = tfUsername.getText().trim();
        if (name.isBlank() || phoneNumber.isBlank() || strDateOfBirth.isBlank() || username.isBlank() || phoneNumber.isBlank()) {
            MessageAlerts.getInstance().showMessage("Wrong format", "Please do not leave fields blank", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.CLOSED_OPTION) {

                }
            });
            return;
        }
        if (!phoneNumber.matches("\\d{10}")) {
            MessageAlerts.getInstance().showMessage("Wrong format", "PhoneNumber must contain exactly 10 digits, cannot contain special characters", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.CLOSED_OPTION) {

                }
            });
            return;
        }
        if (username.contains(" ")) {
            MessageAlerts.getInstance().showMessage("Wrong format", "Username cannot contain spaces", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.CLOSED_OPTION) {

                }
            });
            return;
        }
        user.setName(name);
        user.setGender(gender);
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date parsedDate = dateFormat.parse(strDateOfBirth);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            user.setDateOfBirth(timestamp);
        } catch (ParseException e) {
            return;
        }
        user.setPhoneNumber(phoneNumber);
        user.setUserName(username);
        String password = generateRandomPassword();
        user.setPassword(password);
        user.setRole("staff");
        if (index == -1) {
            if (userService.checkPhoneNumberExist(phoneNumber)) {
                MessageAlerts.getInstance().showMessage("Add failed", "This phone number already exists. Please use another phone number.", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                });
                return;
            }
            if (userService.checkUsernameExist(username)) {
                MessageAlerts.getInstance().showMessage("Add failed", "Username already exists. Please use another username.", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                });
                return;
            }
            try {
                if (userService.add(user)) {
                    MessageAlerts.getInstance().showMessage("Add success", "Your data has been saved", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                        if (i == MessageAlerts.CLOSED_OPTION) {

                        }
                    });
                    savePasswordToFile(username, password);
                    loadDataStaffManagement();
                } else {
                    MessageAlerts.getInstance().showMessage("Add failed", "Please check and try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                        if (i == MessageAlerts.CLOSED_OPTION) {

                        }
                    });
                }
            } catch (Exception e) {
                MessageAlerts.getInstance().showMessage("Add failed", "Please check and try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                });
            }
        } else {
            MessageAlerts.getInstance().showMessage("Add failed", "Please check and try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.CLOSED_OPTION) {

                }
            });
        }
        index = -1;
        btnSave.setEnabled(false);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        search = txtSearch.getText().trim();
        loadDataStaffManagement();
    }//GEN-LAST:event_txtSearchKeyReleased

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
            exportToExcel(tblStaff, filePath);
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
            headerCell.setCellValue("Employee list");
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
    
    private String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        int length = 10;
        StringBuilder password = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNew;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSave;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmployeeDetails;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblName1;
    private javax.swing.JLabel lblName2;
    private javax.swing.JLabel lblName3;
    private javax.swing.JLabel lblName4;
    private com.formdev.flatlaf.extras.components.FlatRadioButton rbtnFemale;
    private com.formdev.flatlaf.extras.components.FlatRadioButton rbtnMale;
    private com.formdev.flatlaf.extras.components.FlatRadioButton rbtnOther;
    private javax.swing.JTable tblStaff;
    private javax.swing.JFormattedTextField tfDateOfBirth;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPhoneNumber;
    private javax.swing.JTextField tfUsername;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
