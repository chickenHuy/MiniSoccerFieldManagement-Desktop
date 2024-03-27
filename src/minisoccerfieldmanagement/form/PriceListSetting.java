/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package minisoccerfieldmanagement.form;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import minisoccerfieldmanagement.model.PriceList;
import minisoccerfieldmanagement.service.IPriceListService;
import minisoccerfieldmanagement.service.PriceListServiceImpl;
import minisoccerfieldmanagement.tabbed.TabbedForm;
import minisoccerfieldmanagement.util.StaticStrings;
import raven.alerts.MessageAlerts;
import raven.datetime.component.time.TimeEvent;
import raven.datetime.component.time.TimePicker;
import raven.datetime.component.time.TimeSelectionListener;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;

/**
 *
 * @author trank
 */
public class PriceListSetting extends TabbedForm {

    /**
     * Creates new form PriceListSetting2
     */
    IPriceListService priceListService;
    DefaultTableModel[] priceListModels;
    String typeField;
    

    public PriceListSetting() {
        initComponents();
        getData();
        typeField = StaticStrings.FIELD_STYLE_5_A_SIZE;
        setTimePicker();
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
        btnTypeField = new javax.swing.JButton();
        flatTabbedPane1 = new com.formdev.flatlaf.extras.components.FlatTabbedPane();
        pnlMon = new raven.crazypanel.CrazyPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMon = new javax.swing.JTable();
        pnlTue = new raven.crazypanel.CrazyPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTue = new javax.swing.JTable();
        pnlWed = new raven.crazypanel.CrazyPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblWed = new javax.swing.JTable();
        pnlThu = new raven.crazypanel.CrazyPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblThu = new javax.swing.JTable();
        pnlFri = new raven.crazypanel.CrazyPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblFri = new javax.swing.JTable();
        pnlSat = new raven.crazypanel.CrazyPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSat = new javax.swing.JTable();
        pnlSun = new raven.crazypanel.CrazyPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSun = new javax.swing.JTable();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ftfTo = new javax.swing.JFormattedTextField();
        ftfFrom = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        crazyPanel4 = new raven.crazypanel.CrazyPanel();
        btnSave = new javax.swing.JButton();
        lblId = new javax.swing.JLabel();
        tfUnitPrice = new javax.swing.JTextField();

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

        btnTypeField.setText("5 A SIDE");
        btnTypeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTypeFieldActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnTypeField);

        crazyPanel1.add(crazyPanel2);

        pnlMon.setLayout(new java.awt.BorderLayout());

        tblMon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "From", "To", "Unit Price Per 30 Minutes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMon);

        pnlMon.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        flatTabbedPane1.addTab("Mon", pnlMon);

        pnlTue.setLayout(new java.awt.BorderLayout());

        tblTue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "From", "To", "Unit Price Per 30 Minutes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTueMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTue);

        pnlTue.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        flatTabbedPane1.addTab("Tue", pnlTue);

        pnlWed.setLayout(new java.awt.BorderLayout());

        tblWed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "From", "To", "Unit Price Per 30 Minutes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblWed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblWedMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblWed);

        pnlWed.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        flatTabbedPane1.addTab("Wed", pnlWed);

        pnlThu.setLayout(new java.awt.BorderLayout());

        tblThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "From", "To", "Unit Price Per 30 Minutes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblThu);

        pnlThu.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        flatTabbedPane1.addTab("Thu", pnlThu);

        pnlFri.setLayout(new java.awt.BorderLayout());

        tblFri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "From", "To", "Unit Price Per 30 Minutes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFriMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblFri);

        pnlFri.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        flatTabbedPane1.addTab("Fri", pnlFri);

        pnlSat.setLayout(new java.awt.BorderLayout());

        tblSat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "From", "To", "Unit Price Per 30 Minutes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSatMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblSat);

        pnlSat.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        flatTabbedPane1.addTab("Sat", pnlSat);

        pnlSun.setLayout(new java.awt.BorderLayout());

        tblSun.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "From", "To", "Unit Price Per 30 Minutes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSunMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblSun);

        pnlSun.add(jScrollPane7, java.awt.BorderLayout.CENTER);

        flatTabbedPane1.addTab("Sun", pnlSun);

        crazyPanel1.add(flatTabbedPane1);

        crazyPanel3.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        jLabel1.setText("Price (VND)");

        jLabel2.setText("From");

        ftfTo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        ftfTo.setEnabled(false);

        ftfFrom.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        ftfFrom.setEnabled(false);

        jLabel3.setText("To");

        crazyPanel4.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        btnSave.setText("Save");
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
        });
        crazyPanel4.add(btnSave);

        lblId.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblId.setForeground(new java.awt.Color(195, 204, 90));

        javax.swing.GroupLayout crazyPanel3Layout = new javax.swing.GroupLayout(crazyPanel3);
        crazyPanel3.setLayout(crazyPanel3Layout);
        crazyPanel3Layout.setHorizontalGroup(
            crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(crazyPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(crazyPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, crazyPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfUnitPrice, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(crazyPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ftfTo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                                .addGap(0, 2, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(42, 42, 42)
                                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ftfFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(crazyPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(39, 39, 39))
        );
        crazyPanel3Layout.setVerticalGroup(
            crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ftfFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftfTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(tfUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(crazyPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(crazyPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addComponent(crazyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTypeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTypeFieldActionPerformed
        String type5 = "5 A SIDE";
        String type7 = "7 A SIDE";
        if (btnTypeField.getText().equals("5 A SIDE"))
        {
            btnTypeField.setText(type7);
            typeField = StaticStrings.FIELD_STYLE_7_A_SIZE;
            GetPriceList(StaticStrings.FIELD_STYLE_7_A_SIZE);
        }
        else
        {
            btnTypeField.setText(type5);
            typeField = StaticStrings.FIELD_STYLE_5_A_SIZE;
            GetPriceList(StaticStrings.FIELD_STYLE_5_A_SIZE);
        }
    }//GEN-LAST:event_btnTypeFieldActionPerformed

    private void tblMonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMonMouseClicked
        int indexRow = tblMon.getSelectedRow();
        if (indexRow != -1 ){
            String id = priceListModels[0].getValueAt(indexRow, 0).toString();
            String from = priceListModels[0].getValueAt(indexRow, 1).toString();
            String to = priceListModels[0].getValueAt(indexRow, 2).toString();
            String price = priceListModels[0].getValueAt(indexRow, 3).toString();
            
            clickTableEvent(id, from, to, price, indexRow);
        }
        
    }//GEN-LAST:event_tblMonMouseClicked

    private void tblTueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTueMouseClicked
        int indexRow = tblTue.getSelectedRow();
        if (indexRow != -1 ){
            String id = priceListModels[1].getValueAt(indexRow, 0).toString();
            String from = priceListModels[1].getValueAt(indexRow, 1).toString();
            String to = priceListModels[1].getValueAt(indexRow, 2).toString();
            String price = priceListModels[1].getValueAt(indexRow, 3).toString();
            
            clickTableEvent(id, from, to, price, indexRow);
        }
    }//GEN-LAST:event_tblTueMouseClicked

    private void tblWedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblWedMouseClicked
        int indexRow = tblWed.getSelectedRow();
        if (indexRow != -1 ){
            String id = priceListModels[2].getValueAt(indexRow, 0).toString();
            String from = priceListModels[2].getValueAt(indexRow, 1).toString();
            String to = priceListModels[2].getValueAt(indexRow, 2).toString();
            String price = priceListModels[2].getValueAt(indexRow, 3).toString();
            
            clickTableEvent(id, from, to, price, indexRow);
        }
    }//GEN-LAST:event_tblWedMouseClicked

    private void tblThuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuMouseClicked
        int indexRow = tblThu.getSelectedRow();
        if (indexRow != -1 ){
            String id = priceListModels[3].getValueAt(indexRow, 0).toString();
            String from = priceListModels[3].getValueAt(indexRow, 1).toString();
            String to = priceListModels[3].getValueAt(indexRow, 2).toString();
            String price = priceListModels[3].getValueAt(indexRow, 3).toString();
            
            clickTableEvent(id, from, to, price, indexRow);
        }
    }//GEN-LAST:event_tblThuMouseClicked

    private void tblFriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFriMouseClicked
        int indexRow = tblFri.getSelectedRow();
        if (indexRow != -1 ){
            String id = priceListModels[4].getValueAt(indexRow, 0).toString();
            String from = priceListModels[4].getValueAt(indexRow, 1).toString();
            String to = priceListModels[4].getValueAt(indexRow, 2).toString();
            String price = priceListModels[4].getValueAt(indexRow, 3).toString();
            
            clickTableEvent(id, from, to, price, indexRow);
        }
    }//GEN-LAST:event_tblFriMouseClicked

    private void tblSatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSatMouseClicked
        int indexRow = tblSat.getSelectedRow();
        if (indexRow != -1 ){
            String id = priceListModels[5].getValueAt(indexRow, 0).toString();
            String from = priceListModels[5].getValueAt(indexRow, 1).toString();
            String to = priceListModels[5].getValueAt(indexRow, 2).toString();
            String price = priceListModels[5].getValueAt(indexRow, 3).toString();
            
            clickTableEvent(id, from, to, price, indexRow);
        }
    }//GEN-LAST:event_tblSatMouseClicked

    private void tblSunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSunMouseClicked
        int indexRow = tblSun.getSelectedRow();
        if (indexRow != -1 ){
            String id = priceListModels[6].getValueAt(indexRow, 0).toString();
            String from = priceListModels[6].getValueAt(indexRow, 1).toString();
            String to = priceListModels[6].getValueAt(indexRow, 2).toString();
            String price = priceListModels[6].getValueAt(indexRow, 3).toString();
            
            clickTableEvent(id, from, to, price, indexRow);
        }
    }//GEN-LAST:event_tblSunMouseClicked

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        try {
            int id = Integer.parseInt(lblId.getText().toString().replace("#", ""));
            BigDecimal unitPrice = new BigDecimal(tfUnitPrice.getText());
            if (unitPrice.compareTo(BigDecimal.ZERO) < 0)
            {
                MessageAlerts.getInstance().showMessage("Wrong format", "Please Enter Only Numbers", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                    @Override
                    public void action(PopupController pc, int i) {
                        if (i == MessageAlerts.CLOSED_OPTION )
                        {
                            tfUnitPrice.setText("");
                        }
                    }
                });
             
            }
            PriceList model = priceListService.findById(id);
            if (model != null)
            {
                model.setUnitPricePer30Minutes(unitPrice);
                priceListService.update(model);
                MessageAlerts.getInstance().showMessage("Success", ("Price has been changed to: " + tfUnitPrice.getText()), MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.OK_OPTION )
                    {
                        GetPriceList(typeField);    
                    }
                }
            });
            }
        
        } catch (Exception e) {
            MessageAlerts.getInstance().showMessage("Wrong format", "Please Enter Only Numbers", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION )
                    {
                        tfUnitPrice.setText("");
                    }
                }
            });
        }
    }//GEN-LAST:event_btnSaveMouseClicked
    
    private void clickTableEvent(String id, String from, String to, String price, int index)
    {
        lblId.setText("#" +id);
        ftfFrom.setText(from);
        ftfTo.setText(to);
        tfUnitPrice.setText(price);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnTypeField;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private raven.crazypanel.CrazyPanel crazyPanel4;
    private com.formdev.flatlaf.extras.components.FlatTabbedPane flatTabbedPane1;
    private javax.swing.JFormattedTextField ftfFrom;
    private javax.swing.JFormattedTextField ftfTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblId;
    private raven.crazypanel.CrazyPanel pnlFri;
    private raven.crazypanel.CrazyPanel pnlMon;
    private raven.crazypanel.CrazyPanel pnlSat;
    private raven.crazypanel.CrazyPanel pnlSun;
    private raven.crazypanel.CrazyPanel pnlThu;
    private raven.crazypanel.CrazyPanel pnlTue;
    private raven.crazypanel.CrazyPanel pnlWed;
    private javax.swing.JTable tblFri;
    private javax.swing.JTable tblMon;
    private javax.swing.JTable tblSat;
    private javax.swing.JTable tblSun;
    private javax.swing.JTable tblThu;
    private javax.swing.JTable tblTue;
    private javax.swing.JTable tblWed;
    private javax.swing.JTextField tfUnitPrice;
    // End of variables declaration//GEN-END:variables
    private raven.datetime.component.time.TimePicker timePicker1, timePicker2;
    private void getData() {
        
        typeField = StaticStrings.FIELD_STYLE_5_A_SIZE;
        priceListService = new PriceListServiceImpl();
        priceListModels = new DefaultTableModel[7];
        priceListModels[0] = (DefaultTableModel) tblMon.getModel();
        priceListModels[1] = (DefaultTableModel) tblTue.getModel();
        priceListModels[2] = (DefaultTableModel) tblWed.getModel();
        priceListModels[3] = (DefaultTableModel) tblThu.getModel();
        priceListModels[4] = (DefaultTableModel) tblFri.getModel();
        priceListModels[5] = (DefaultTableModel) tblSat.getModel();
        priceListModels[6] = (DefaultTableModel) tblSun.getModel();
        
        GetPriceList(typeField);
       
       
        
    }
    private void GetPriceList(String typeField)
    {
        try{
        
        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int vt = 0;
        Object[] row = new Object[4];
        
        for (String dateInWeek : week)
        {
            priceListModels[vt].setRowCount(0);
            List<PriceList> pl = priceListService.findByDateOfWeek(dateInWeek, typeField);
            if (pl != null){
                for (PriceList p : pl)
                {
                    row[0] = p.getId();
                    row[1] = p.getStartTime();
                    row[2] = p.getEndTime();
                    row[3] = p.getUnitPricePer30Minutes();
                    priceListModels[vt].addRow(row);
                }
            }
            vt++;
        }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setTimePicker() {
        timePicker1 = new  TimePicker();
        timePicker1.set24HourView(true);
        // timePicker1.setSelectedTime(LocalTime.of(1, 45));
        timePicker1.setOrientation(SwingConstants.HORIZONTAL);
        timePicker1.addTimeSelectionListener(new TimeSelectionListener() {
            @Override
            public void timeSelected(TimeEvent te) {
                if (timePicker1.isTimeSelected()) {
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm a");
                    System.out.println(timePicker1.getSelectedTime());
                }
            }
        });
        
        timePicker2 = new  TimePicker();
        timePicker2.set24HourView(true);
        // timePicker1.setSelectedTime(LocalTime.of(1, 45));
        timePicker2.setOrientation(SwingConstants.HORIZONTAL);
        timePicker2.addTimeSelectionListener(new TimeSelectionListener() {
            @Override
            public void timeSelected(TimeEvent te) {
                if (timePicker2.isTimeSelected()) {
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm a");
                    System.out.println(timePicker2.getSelectedTime());
                }
            }
        });
        
        timePicker1.setEditor(ftfTo);
        timePicker2.setEditor(ftfFrom);
    }
}