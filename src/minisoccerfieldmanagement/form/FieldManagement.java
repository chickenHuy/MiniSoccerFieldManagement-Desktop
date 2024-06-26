package minisoccerfieldmanagement.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import minisoccerfieldmanagement.model.Field;
import minisoccerfieldmanagement.service.FieldServiceImpl;
import minisoccerfieldmanagement.service.IFieldService;
import minisoccerfieldmanagement.tabbed.TabbedForm;
import minisoccerfieldmanagement.util.StaticStrings;
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

public class FieldManagement extends TabbedForm {

    private int index;
    private File tempPicture = null;
    private boolean isSearchDeleted = false;
    private boolean havePicture = false;
    private boolean wantToChangePicture = false;
    IFieldService fieldService;
    DefaultTableModel models;
    
    public FieldManagement() {
        initComponents();
        fieldService = new FieldServiceImpl();
        tableModelField();
        loadFieldType();
        applyTableStyle(tblField);
        index = -1;
    }

    private void loadData(boolean isGetDeleted) {
        List<Field> fields = new ArrayList<>();
        if (isGetDeleted) {
            fields = fieldService.findAllDeleted();
        } else {
            fields = fieldService.findAll();
        }
        for (Field field : fields) {
            if (isGetDeleted) {
                models.addRow(addDeletedFieldRow(field));
            } else {
                models.addRow(addFieldRow(field));
            }
        }
    }

    private void tableModelField() {
        setTableModel(new String[]{"ID", "Name", "Type", "Status", "Created at", "Updated at"});
        btnField.setVisible(false);
        btnDeletedField.setVisible(true);
        models = (DefaultTableModel) tblField.getModel();
        loadData(false);
    }

    private void loadFieldType() {
        cboFieldType.addItem(StaticStrings.FIELD_STYLE_5_A_SIZE);
        cboFieldType.addItem(StaticStrings.FIELD_STYLE_7_A_SIZE);
    }

    private void tableModelDeleted() {
        setTableModel(new String[]{"ID", "Name", "Type", "Updated at"});
        btnField.setVisible(true);
        btnDeletedField.setVisible(false);
        models = (DefaultTableModel) tblField.getModel();
        loadData(true);
    }

    private Object[] addFieldRow(Field field) {
        return new Object[]{field.getId(), field.getName(), field.getType(), field.getStatus(), field.getCreatedAt(), field.getUpdatedAt()};
    }

    private Object[] addDeletedFieldRow(Field field) {
        return new Object[]{field.getId(), field.getName(), field.getType(), field.getUpdatedAt()};
    }

    private void setTableModel(String[] columnNames) {
        DefaultTableModel model = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Return the editable status of the column
                return false;
            }
        };

        // Add columns to the model
        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }
        tblField.setModel(model);
    }

    private void applyTableStyle(JTable table) {
        btnAddNew.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/add.svg", 0.35f));
        btnSave.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/edit.svg", 0.35f));
        btnDelete.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/delete.svg", 0.35f));
        btnPrint.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/excel.svg", 0.35f));
        btnUpload.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/upload.svg", 0.35f));
        btnDeleteIcon.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/delete.svg", 0.35f));
        buttonClean.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/clean.svg", 0.35f));

        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/search.svg", 0.35f));
        //  Change scroll style
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
                    if (column == 0 || column == 4) {
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                    } else if (column == 2 || column == 3) {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        txtSearch = new javax.swing.JTextField();
        btnField = new javax.swing.JButton();
        btnDeletedField = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnDeleteIcon = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblField = new javax.swing.JTable();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        btnUpload = new javax.swing.JButton();
        ptbFieldImage = new minisoccerfieldmanagement.util.PictureBox();
        buttonClean = new javax.swing.JButton();
        crazyPanel5 = new raven.crazypanel.CrazyPanel();
        cboFieldType = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblCombineField2 = new javax.swing.JLabel();
        lblCombineField1 = new javax.swing.JLabel();
        lblCombineField3 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        cboStatus = new javax.swing.JComboBox<>();
        cboSubField1 = new javax.swing.JComboBox<>();
        cboSubField2 = new javax.swing.JComboBox<>();
        cboSubField3 = new javax.swing.JComboBox<>();
        crazyPanel6 = new raven.crazypanel.CrazyPanel();
        btnAddNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        crazyPanel7 = new raven.crazypanel.CrazyPanel();
        btnDelete = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

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

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        crazyPanel2.add(txtSearch);

        btnField.setText("Field");
        btnField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFieldActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnField);

        btnDeletedField.setText("Deleted Field");
        btnDeletedField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletedFieldActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnDeletedField);

        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnPrint);

        btnDeleteIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteIconActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnDeleteIcon);

        crazyPanel1.add(crazyPanel2);

        tblField.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "ID", "Name", "Type", "Status", "Created at", "Deleted at"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFieldMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblField);

        crazyPanel1.add(jScrollPane1);

        crazyPanel3.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel3.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            null
        ));

        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        ptbFieldImage.setBackground(new java.awt.Color(153, 153, 255));

        buttonClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCleanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout crazyPanel3Layout = new javax.swing.GroupLayout(crazyPanel3);
        crazyPanel3.setLayout(crazyPanel3Layout);
        crazyPanel3Layout.setHorizontalGroup(
            crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel3Layout.createSequentialGroup()
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crazyPanel3Layout.createSequentialGroup()
                        .addContainerGap(108, Short.MAX_VALUE)
                        .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addComponent(buttonClean, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ptbFieldImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        crazyPanel3Layout.setVerticalGroup(
            crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ptbFieldImage, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonClean, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        crazyPanel5.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel5.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            null
        ));

        cboFieldType.setMaximumSize(new java.awt.Dimension(200, 200));
        cboFieldType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFieldTypeActionPerformed(evt);
            }
        });

        jLabel1.setText("Name");

        jLabel2.setText("Status");

        lblCombineField2.setText("Combine Field 2");

        lblCombineField1.setText("Combine Field 1");

        lblCombineField3.setText("Combine Field 3");

        cboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "active", "inactive" }));

        crazyPanel6.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        btnAddNew.setText("Add New");
        btnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewActionPerformed(evt);
            }
        });
        crazyPanel6.add(btnAddNew);

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        crazyPanel6.add(btnSave);

        crazyPanel7.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        crazyPanel7.add(btnDelete);

        jLabel3.setPreferredSize(new java.awt.Dimension(10, 10));

        javax.swing.GroupLayout crazyPanel5Layout = new javax.swing.GroupLayout(crazyPanel5);
        crazyPanel5.setLayout(crazyPanel5Layout);
        crazyPanel5Layout.setHorizontalGroup(
            crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crazyPanel5Layout.createSequentialGroup()
                        .addComponent(cboFieldType, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(crazyPanel5Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(cboStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCombineField1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCombineField3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCombineField2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboSubField1, 0, 158, Short.MAX_VALUE)
                            .addComponent(cboSubField2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboSubField3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(crazyPanel5Layout.createSequentialGroup()
                        .addComponent(crazyPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(450, 450, 450)
                        .addComponent(crazyPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))))
        );
        crazyPanel5Layout.setVerticalGroup(
            crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel5Layout.createSequentialGroup()
                .addGap(15, 16, Short.MAX_VALUE)
                .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(crazyPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(crazyPanel5Layout.createSequentialGroup()
                        .addComponent(cboFieldType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(crazyPanel5Layout.createSequentialGroup()
                                .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(crazyPanel5Layout.createSequentialGroup()
                                .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblCombineField1)
                                    .addComponent(cboSubField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblCombineField2)
                                    .addComponent(cboSubField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(crazyPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblCombineField3)
                                    .addComponent(cboSubField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(crazyPanel5Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(crazyPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(826, Short.MAX_VALUE)
                .addComponent(crazyPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(17, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(crazyPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(398, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(crazyPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(433, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(284, Short.MAX_VALUE)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(crazyPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(449, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeletedFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletedFieldActionPerformed
        // TODO add your handling code here:
        tableModelDeleted();
        isSearchDeleted = true;
        btnSave.setEnabled(false);
        btnDeleteIcon.setEnabled(false);
        btnDelete.setText("Restore");
        clearText();
    }//GEN-LAST:event_btnDeletedFieldActionPerformed

    private void btnFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFieldActionPerformed
        // TODO add your handling code here:
        tableModelField();
        isSearchDeleted = false;
        btnSave.setEnabled(true);
        btnDeleteIcon.setEnabled(true);
        btnDelete.setText("Delete");
        clearText();
    }//GEN-LAST:event_btnFieldActionPerformed

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        // TODO add your handling code here:
        clearText();
        index = -1;
    }//GEN-LAST:event_btnAddNewActionPerformed

    private void clearText() {
        havePicture = false;
        tfName.setText("");
        tfName.requestFocus();
        tempPicture = null;
        ptbFieldImage.setImage(null);
        ptbFieldImage.revalidate();
        ptbFieldImage.repaint();
        index = -1;
    }

    private void hide7Type() {
        lblCombineField1.setVisible(false);
        lblCombineField2.setVisible(false);
        lblCombineField3.setVisible(false);
        cboSubField1.setVisible(false);
        cboSubField2.setVisible(false);
        cboSubField3.setVisible(false);
    }

    private void show7Type() {
        lblCombineField1.setVisible(true);
        lblCombineField2.setVisible(true);
        lblCombineField3.setVisible(true);
        cboSubField1.setVisible(true);
        cboSubField2.setVisible(true);
        cboSubField3.setVisible(true);

    }

    private void tblFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFieldMouseClicked
        // TODO add your handling code here:
        index = tblField.getSelectedRow();
        if (index != -1) {
            tempPicture = null;
            tfName.setText(models.getValueAt(index, 1).toString());
            String type = models.getValueAt(index, 2).toString();
            cboStatus.setSelectedItem(models.getValueAt(index, 3).toString());
            cboFieldType.setSelectedItem(type);
            Field field = fieldService.findById(Integer.parseInt(models.getValueAt(index, 0).toString()));
            if (field == null || field.getImage() == null) {
                havePicture = false;
                ptbFieldImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/pitch.png"));
                ptbFieldImage.repaint();
            } else {
                havePicture = true;
                File file = new File("src/minisoccerfieldmanagement/image/field/" + field.getImage());
                if (!file.exists()) {
                    ptbFieldImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/pitch.png"));
                    ptbFieldImage.repaint();
                } else {
                    ptbFieldImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/field/" + field.getImage()));
                    ptbFieldImage.repaint();
                }
            }
            if (type.equals(StaticStrings.FIELD_STYLE_5_A_SIZE)) {
                hide7Type();
            } else {
                show7Type();
                Field field7 = fieldService.findById(Integer.parseInt(models.getValueAt(index, 0).toString()));
                loadComboboxField();
                loadComboboxWithDeletedField(cboSubField1,field7);
                loadComboboxWithDeletedField(cboSubField2,field7);
                loadComboboxWithDeletedField(cboSubField3,field7);
                selectCombineField(cboSubField1, field7.getCombineField1());
                selectCombineField(cboSubField2, field7.getCombineField2());
                selectCombineField(cboSubField3, field7.getCombineField3());

            }
            System.out.println("pictureBox " + ptbFieldImage.getImage().toString());
            File file = new File(ptbFieldImage.getImage().toString());
            System.out.println("pictureBox " + file.getName());
        }
    }//GEN-LAST:event_tblFieldMouseClicked

    private void loadComboboxWithDeletedField(JComboBox cboField, Field field7) {
        List<Field> deletedField = fieldService.findAllDeleted();
        for (Field field: deletedField) {
            if (field.getId() == field7.getCombineField1() || field.getId() == field7.getCombineField2() || field.getId() == field.getCombineField3())
                cboField.addItem(field.getId());

        }
    }
    
    private void selectCombineField(JComboBox combobox, int fieldId) {
        for (int i = 0; i < combobox.getItemCount(); i++) {
            if (combobox.getItemAt(i).toString().equals(String.valueOf(fieldId))) {
                combobox.setSelectedItem(combobox.getItemAt(i));
                return;
            }
        }
    }

    private void loadComboboxField() {
        loadField5Combobox(cboSubField1);
        loadField5Combobox(cboSubField2);
        loadField5Combobox(cboSubField3);
    }

    private void loadField5Combobox(JComboBox cboField) {
        List<Field> fields = fieldService.findAllNormalFiled();
        cboField.removeAllItems();
        for (Field field : fields) {
            cboField.addItem(field.getId());
        }
    }

    private void cboFieldTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFieldTypeActionPerformed
        // TODO add your handling code here:
        if (cboFieldType.getSelectedItem().toString().equals(StaticStrings.FIELD_STYLE_5_A_SIZE)) {
            hide7Type();
        } else {
            show7Type();
            loadComboboxField();
        }
    }//GEN-LAST:event_cboFieldTypeActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        Field field = new Field();
        String name = tfName.getText();
        String type = cboFieldType.getSelectedItem().toString();
        String status = cboStatus.getSelectedItem().toString();
        if (name.isBlank()) {
            MessageAlerts.getInstance().showMessage("Wrong format", "Please do not leave fields blank", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });

        } else {
            field.setName(name);
            field.setType(type);
            field.setStatus(status);
            if (tempPicture != null) {
                String picturePath = tempPicture.getAbsolutePath();
                String uuid = UUID.randomUUID().toString();
                String newName = uuid + picturePath.substring(picturePath.lastIndexOf('.'));
//                String newName = field.getName().replaceAll(" ", "") + picturePath.substring(picturePath.lastIndexOf('.'));
                field.setImage(newName);
                // Ghép tên + extension để tạo tên file mới
                if (!saveFile(tempPicture, newName)) {
                    return;
                }
            }
            if (type.equals(StaticStrings.FIELD_STYLE_7_A_SIZE)) {
                String idField1 = cboSubField1.getSelectedItem().toString();
                String idField2 = cboSubField2.getSelectedItem().toString();
                String idField3 = cboSubField3.getSelectedItem().toString();
                if (idField1.equals(idField2) || idField1.equals(idField3) || idField2.equals(idField3)) {
                    MessageAlerts.getInstance().showMessage("Combine Field Duplicate", "Please choose 3 different field id for combined field", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                        @Override
                        public void action(PopupController pc, int i) {
                            if (i == MessageAlerts.CLOSED_OPTION) {

                            }
                        }
                    });
                    return;
                } else {
                    field.setCombineField1(Integer.parseInt(idField1));
                    field.setCombineField2(Integer.parseInt(idField2));
                    field.setCombineField3(Integer.parseInt(idField3));
                }
            }
            if (index == -1) {
                boolean success;
                if (type.equals(StaticStrings.FIELD_STYLE_7_A_SIZE)) {
                    success = fieldService.add7Field(field);
                } else {
                    success = fieldService.add5Field(field);
                }
                if (success) {
                    MessageAlerts.getInstance().showMessage("Add Success", "Your data has been saved", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                        @Override
                        public void action(PopupController pc, int i) {
                            if (i == MessageAlerts.CLOSED_OPTION) {

                            }
                        }
                    });
                    List<Field> fields = fieldService.findAll();
                    models.setNumRows(0);
                    for (Field fld : fields) {

                        models.addRow(addFieldRow(fld));
                    }
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
                field.setId(Integer.parseInt(models.getValueAt(index, 0).toString()));
                Field oldField = fieldService.findById(field.getId());
                if (tempPicture == null) {
                    if (havePicture && wantToChangePicture) {
                        field.setImage(null);
                        wantToChangePicture = false;
                        havePicture = false;
                    }
                    else
                        field.setImage(oldField.getImage());
                } else {
                    File destinationFolder = new File("src/minisoccerfieldmanagement/image/field");
                    if (oldField.getImage() != null)
                        deleteFile(destinationFolder, oldField.getImage());
                }
                boolean isSuccess;
                if (field.getType().equals(StaticStrings.FIELD_STYLE_7_A_SIZE))
                    isSuccess = fieldService.update7Field(field);
                else 
                    isSuccess = fieldService.update5Field(field);
                if (isSuccess) {
                    MessageAlerts.getInstance().showMessage("Updated Success", "Your data has been saved", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                        @Override
                        public void action(PopupController pc, int i) {
                            if (i == MessageAlerts.CLOSED_OPTION) {

                            }
                        }
                    });
                    List<Field> fields = fieldService.findAll();
                    models.setNumRows(0);
                    for (Field fld : fields) {

                        models.addRow(addFieldRow(fld));
                    }
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
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (index != -1) {
            if (isSearchDeleted == false) {
                MessageAlerts.getInstance().showMessage("Delete Field", "You definitely want to delete the field with id: " + models.getValueAt(index, 0).toString(), MessageAlerts.MessageType.WARNING, MessageAlerts.YES_NO_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.YES_OPTION) {
                        int id = Integer.parseInt(models.getValueAt(index, 0).toString());
                        if (fieldService.softDelete(id)) {
                            MessageAlerts.getInstance().showMessage("Deleted", "Successfully deleted data", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                                @Override
                                public void action(PopupController pc, int i) {
                                    if (i == MessageAlerts.CLOSED_OPTION) {

                                    }
                                }
                            });
                            models.removeRow(index);
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
            else {
                MessageAlerts.getInstance().showMessage("Restore Field", "You definitely want to restore the field with id: " + models.getValueAt(index, 0).toString(), MessageAlerts.MessageType.WARNING, MessageAlerts.YES_NO_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.YES_OPTION) {
                        int id = Integer.parseInt(models.getValueAt(index, 0).toString());
                        if (fieldService.restore(id)) {
                            MessageAlerts.getInstance().showMessage("Restored", "Successfully restored data", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                                @Override
                                public void action(PopupController pc, int i) {
                                    if (i == MessageAlerts.CLOSED_OPTION) {

                                    }
                                }
                            });
                            models.removeRow(index);
                            clearText();

                        } else {
                            MessageAlerts.getInstance().showMessage("Restore failed", "There was an erro during restoration, please try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                                @Override
                                public void action(PopupController pc, int i) {

                                }
                            });
                        }
                    }
                }
            });
            }
            }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnDeleteIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteIconActionPerformed
        if (index != -1) {
            if (isSearchDeleted == false) {
                MessageAlerts.getInstance().showMessage("Delete Field", "You definitely want to delete the field with id: " + models.getValueAt(index, 0).toString(), MessageAlerts.MessageType.WARNING, MessageAlerts.YES_NO_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.YES_OPTION) {
                        int id = Integer.parseInt(models.getValueAt(index, 0).toString());
                        if (fieldService.softDelete(id)) {
                            MessageAlerts.getInstance().showMessage("Deleted", "Successfully deleted data", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                                @Override
                                public void action(PopupController pc, int i) {
                                    if (i == MessageAlerts.CLOSED_OPTION) {

                                    }
                                }
                            });
                            models.removeRow(index);
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
            else {
                MessageAlerts.getInstance().showMessage("Restore Field", "You definitely want to restore the field with id: " + models.getValueAt(index, 0).toString(), MessageAlerts.MessageType.WARNING, MessageAlerts.YES_NO_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.YES_OPTION) {
                        int id = Integer.parseInt(models.getValueAt(index, 0).toString());
                        if (fieldService.restore(id)) {
                            MessageAlerts.getInstance().showMessage("Restored", "Successfully restored data", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                                @Override
                                public void action(PopupController pc, int i) {
                                    if (i == MessageAlerts.CLOSED_OPTION) {

                                    }
                                }
                            });
                            models.removeRow(index);
                            clearText();

                        } else {
                            MessageAlerts.getInstance().showMessage("Restore failed", "There was an erro during restoration, please try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                                @Override
                                public void action(PopupController pc, int i) {

                                }
                            });
                        }
                    }
                }
            });
            }
            

        }
    }//GEN-LAST:event_btnDeleteIconActionPerformed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("* .Image", "jpg", "png"));
        chooser.setFileFilter(chooser.getChoosableFileFilters()[1]);
        int r = chooser.showDialog(this, null);
        if (r != JFileChooser.APPROVE_OPTION) return;
        ptbFieldImage.setImage(new ImageIcon(chooser.getSelectedFile().getAbsolutePath()));
        ptbFieldImage.repaint();
        tempPicture = chooser.getSelectedFile();
        System.out.print("Chon file " + tempPicture);
    }//GEN-LAST:event_btnUploadActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
        List<Field> searchField = fieldService.findByNameALike(txtSearch.getText(), isSearchDeleted);
        models.setRowCount(0);
        for (Field field : searchField) {
            if (isSearchDeleted) {
                models.addRow(addDeletedFieldRow(field));
            } else {
                models.addRow(addFieldRow(field));
            }
        }
    }//GEN-LAST:event_txtSearchActionPerformed

    private void buttonCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCleanActionPerformed
        cleanPicture();
    }//GEN-LAST:event_buttonCleanActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
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
            exportToExcel(tblField, filePath);
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
            if (isSearchDeleted == true) {
                headerCell.setCellValue("Deleted Field List");
            } else {
                headerCell.setCellValue("Field List");
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
                    if (model.getValueAt(row, col) != null)
                        cell.setCellValue(model.getValueAt(row, col).toString());
                    else
                        cell.setCellValue("");
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
    
    private void cleanPicture() {
        wantToChangePicture = true;
        tempPicture = null;
        ptbFieldImage.setImage(null);
        ptbFieldImage.repaint();
    }
    
    private void deleteFile(File folderPath, String fileName) {
        File destinationFile = new File(folderPath, fileName);
        if (destinationFile.exists()) {
            try {
                System.out.println("delete "+ destinationFile.toURI().toString());
                Files.delete(Paths.get(destinationFile.toURI()));
            } catch (IOException ex) {
                Logger.getLogger(FieldManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private boolean saveFile(File file, String fileName) {
        File destinationFolder = new File("src/minisoccerfieldmanagement/image/field");
        try {
            // Create the destination folder if it doesn't exist
            if (!destinationFolder.exists()) {
                destinationFolder.mkdirs();
            }
            // Create the destination file
            File destinationFile = new File(destinationFolder, fileName);

            // Copy the selected file to the destination folder
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
    private javax.swing.JButton btnDeleteIcon;
    private javax.swing.JButton btnDeletedField;
    private javax.swing.JButton btnField;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpload;
    private javax.swing.JButton buttonClean;
    private javax.swing.JComboBox<String> cboFieldType;
    private javax.swing.JComboBox<String> cboStatus;
    private javax.swing.JComboBox<String> cboSubField1;
    private javax.swing.JComboBox<String> cboSubField2;
    private javax.swing.JComboBox<String> cboSubField3;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private raven.crazypanel.CrazyPanel crazyPanel5;
    private raven.crazypanel.CrazyPanel crazyPanel6;
    private raven.crazypanel.CrazyPanel crazyPanel7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCombineField1;
    private javax.swing.JLabel lblCombineField2;
    private javax.swing.JLabel lblCombineField3;
    private minisoccerfieldmanagement.util.PictureBox ptbFieldImage;
    private javax.swing.JTable tblField;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
