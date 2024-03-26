/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package minisoccerfieldmanagement.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import minisoccerfieldmanagement.tabbed.TabbedForm;
import minisoccerfieldmanagement.util.TableGradientCell;

/**
 *
 * @author trank
 */
public class ServiceManagement extends TabbedForm {

    /**
     * Creates new form ServiceManagement
     */
    public ServiceManagement() {
        initComponents();
        FlatLaf.registerCustomDefaultsSource("tableview");
        applyTableStyle(tblService);
        testData(tblService);
    }
    
    private void testData(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(getRandomRow("Beer"));
        model.addRow(getRandomRow("Shirt"));
        model.addRow(getRandomRow("Laptop"));
        model.addRow(getRandomRow("Book"));
        model.addRow(getRandomRow("Coffee"));
        model.addRow(getRandomRow("Phone"));
        model.addRow(getRandomRow("Chair"));
        model.addRow(getRandomRow("Watch"));
        model.addRow(getRandomRow("Sunglasses"));
        model.addRow(getRandomRow("Bag"));
        model.addRow(getRandomRow("Headphones"));
        model.addRow(getRandomRow("Camera"));
        model.addRow(getRandomRow("Necklace"));
        model.addRow(getRandomRow("Perfume"));
        model.addRow(getRandomRow("Wallet"));
        model.addRow(getRandomRow("Jacket"));
        model.addRow(getRandomRow("Bicycle"));
        model.addRow(getRandomRow("Game console"));
        model.addRow(getRandomRow("Tennis racket"));
    }
    private Object[] getRandomRow(String name) {
        DecimalFormat df = new DecimalFormat("#,##0.##");
        return new Object[]{false, name, "$" + df.format(getAmount(9999, 9999999)), "$" + df.format(getAmount(9999, 9999999)), df.format(getAmount(-100, 100))};
    }
    
    private double getAmount(int from, int to) {
        Random ran = new Random();
        return (ran.nextInt(to - from) + from) * ran.nextDouble();
    }

    private void applyTableStyle(JTable table) {

        btnAddNew.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/add.svg", 0.35f));
        btnSave.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/edit.svg", 0.35f));
        btnDelete.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/delete.svg", 0.35f));
        btnPrint.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/excel.svg",0.35f));
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
                        if (column == 4) {
                            if (Double.parseDouble(value.toString()) > 0) {
                                com.setForeground(new Color(17, 182, 60));
                                label.setText("+" + value);
                            } else {
                                com.setForeground(new Color(202, 48, 48));
                            }
                        } else {
                            if (isSelected) {
                                com.setForeground(table.getSelectionForeground());
                            } else {
                                com.setForeground(table.getForeground());
                            }
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
        btnPrint = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblService = new javax.swing.JTable();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        crazyPanel4 = new raven.crazypanel.CrazyPanel();
        btnAddNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        spnQuantity = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();

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
        crazyPanel2.add(txtSearch);

        btnPrint.setText("Print");
        crazyPanel2.add(btnPrint);

        crazyPanel1.add(crazyPanel2);

        tblService.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "ID", "Name", "Description", "Price", "Unit", "Quantity", "Sold", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblService);

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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bottle", "Person", "Piece", "Meter", "Kilogram", "Liter", "Pair" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Out Of Stock", "Temp. out of stock" }));
        jComboBox2.setVerifyInputWhenFocusTarget(false);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Service Details");

        jLabel4.setText("Name");

        jLabel5.setText("Desc");

        jLabel6.setText("Price");

        jLabel7.setText("Unit");

        jLabel8.setText("Status");

        crazyPanel4.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        btnAddNew.setText("Add New");
        crazyPanel4.add(btnAddNew);

        btnSave.setText("Save");
        crazyPanel4.add(btnSave);

        btnDelete.setText("Delete");
        crazyPanel4.add(btnDelete);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("(VND)");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(195, 204, 90));
        jLabel2.setText("#135341");

        jLabel10.setText("Quantity");

        javax.swing.GroupLayout crazyPanel3Layout = new javax.swing.GroupLayout(crazyPanel3);
        crazyPanel3.setLayout(crazyPanel3Layout);
        crazyPanel3Layout.setHorizontalGroup(
            crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel3Layout.createSequentialGroup()
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(crazyPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(crazyPanel3Layout.createSequentialGroup()
                                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField2)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)))
                            .addGroup(crazyPanel3Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(crazyPanel3Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, 0, 177, Short.MAX_VALUE)
                            .addComponent(spnQuantity, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(crazyPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(crazyPanel3Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        crazyPanel3Layout.setVerticalGroup(
            crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(32, 32, 32)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(35, 35, 35)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addGap(30, 30, 30)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(35, 35, 35)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2)
                    .addComponent(jLabel8))
                .addGap(36, 36, 36)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(29, 29, 29)
                .addComponent(crazyPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(crazyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crazyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNew;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSave;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private raven.crazypanel.CrazyPanel crazyPanel4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JSpinner spnQuantity;
    private javax.swing.JTable tblService;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
