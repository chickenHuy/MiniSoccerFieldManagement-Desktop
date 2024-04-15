package minisoccerfieldmanagement.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import minisoccerfieldmanagement.model.Service;
import minisoccerfieldmanagement.service.ServiceServiceImpl;
import minisoccerfieldmanagement.tabbed.TabbedForm;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;

public class ServiceManagement extends TabbedForm {
    
    private File tempPicture = null;
    private CustomTableModel model = null;
    private List<Service> listService = null;
    private ServiceServiceImpl serviceService = null;
    private DefaultListModel<String> listResultSearch = null;
    
    private int limit = 20;
    private String status = "Active";
    private String orderBy = "id";
    private String direction = "DESC";
    private int page = 0;
    private Timer timer = null;
    
    private int selectedServiceIndex = -1;
    
    public ServiceManagement() {
        initComponents();
        FlatLaf.registerCustomDefaultsSource("tableview");
        setWidgit();
        cleanField();
        applyTableStyle(tblService);
        setUpJTable(tblService);
        loadDataIntoJTable("", status, limit, 0, direction, orderBy);
    }
    
    private void setWidgit() {
        crazyPanel4.setAlignmentX(0.5f);
        
        crazyPanel1.setComponentZOrder(jScrollSearch, 0);
        jListSearch.setVisible(false);
        jScrollSearch.setVisible(false);
        
        listResultSearch = new DefaultListModel<>();
        jListSearch.setModel(listResultSearch);
        
        buttonPrevous.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/left.svg", 0.35f));
        buttonNext.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/right.svg", 0.35f));
        buttonClean.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/clean.svg", 0.35f));
        buttonSearch.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/search.svg", 0.35f));
        
        serviceService = new ServiceServiceImpl();
        
        textServicePrice.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkInput(textServicePrice);
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                checkInput(textServicePrice);
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkInput(textServicePrice);
            }
        });
        
        textQuantity.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkInput(textQuantity);
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                checkInput(textQuantity);
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkInput(textQuantity);
            }
        });
        
        comboBoxStatusFielter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBoxStatusFielter.getSelectedIndex();
                if (selectedIndex == 0) {
                    status = "Active";
                } else {
                    if (selectedIndex == 1) {
                        status = "Inactive";
                    } else {
                        status = "";
                    }
                }
                model.setRowCount(0);
                loadDataIntoJTable("", status, limit, 0, direction, orderBy);
            }
        });
        
        comboBoxDirection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBoxDirection.getSelectedIndex();
                if (selectedIndex == 0) {
                    direction = "DESC";
                } else {
                    direction = "ASC";
                }
                model.setRowCount(0);
                loadDataIntoJTable("", status, limit, 0, direction, orderBy);
            }
        });
        
        comboBoxLimit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBoxLimit.getSelectedIndex();
                if (selectedIndex == 0) {
                    limit = 20;
                } else {
                    if (selectedIndex == 1) {
                        limit = 30;
                    } else {
                        if (selectedIndex == 2) {
                            limit = 50;
                        } else {
                            limit = -1;
                        }
                    }
                }
                model.setRowCount(0);
                loadDataIntoJTable("", status, limit, 0, direction, orderBy);
            }
        });
        
        comboBoxOrderBy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBoxOrderBy.getSelectedIndex();
                if (selectedIndex == 0) {
                    orderBy = "id";
                } else {
                    if (selectedIndex == 1) {
                        orderBy = "price";
                    } else {
                        if (selectedIndex == 2) {
                            orderBy = "quantity";
                        } else {
                            orderBy = "sold";
                        }
                    }
                }
                model.setRowCount(0);
                loadDataIntoJTable("", status, limit, 0, direction, orderBy);
            }
        });
        
        textSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setListSuggest(textSearch.getText().trim(), status);
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                setListSuggest(textSearch.getText().trim(), status);
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                setListSuggest(textSearch.getText().trim(), status);
            }
        });
        
        jListSearch.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedValue = (String) jListSearch.getSelectedValue();
                    
                    if (selectedValue != null) {
                        textSearch.setText(selectedValue);
                        jListSearch.setVisible(false);
                        jScrollSearch.setVisible(false);
                    }
                }
            }
        });
        
    }
    
    private void setListSuggest(String keyWord, String status) {
        listResultSearch.clear();
        
        if (keyWord.equals("") || keyWord.isBlank()) {
            jListSearch.setVisible(false);
            jScrollSearch.setVisible(false);
            return;
        }
        
        if (timer != null) {
            timer.stop();
        }
        
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jListSearch.setVisible(true);
                jScrollSearch.setVisible(true);
                List<String> listSuggest = serviceService.loadServiceNameByKeyword(keyWord, status);
                if (listSuggest.size() != 0) {
                    for (String name : listSuggest) {
                        listResultSearch.addElement(name);
                    }
                }
                timer = null;
            }
        });
        
        timer.setRepeats(false);
        timer.start();
        
    }
    
    private void checkInput(JTextField textField) {
        String text = textField.getText();
        if (text.equals("")) {
            textField.setBackground(null);
            return;
        }
        if (text.matches(".*[^0-9].*")) {
            textField.setToolTipText("Invalid input! Please enter a valid number");
            textField.setBackground(new Color(250, 17, 60));
            return;
        }
        try {
            BigDecimal value = new BigDecimal(text);
            textField.setBackground(null);
        } catch (NumberFormatException ex) {
            textField.setToolTipText("Invalid input! Please enter a valid number");
            textField.setBackground(new Color(250, 17, 60));
        }
    }
    
    private void loadDataIntoJTable(String keyword, String status, int limit, int offset, String orderBy, String field) {
        listService = serviceService.loadDataIntoJTable(keyword, status, limit, offset, orderBy, field);
        
        for (Service service : listService) {
            model.addRow(getRowData(service));
        }
    }
    
    private void setUpJTable(JTable table) {
        model = new CustomTableModel(new Object[][]{}, new String[]{"Image", "Name", "Price", "Description", "Unit", "Quantity", "Sold", "Status"});
        table.setModel(model);
        table.setRowHeight(70);
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(130);
        table.getColumnModel().getColumn(2).setPreferredWidth(130);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(60);
        table.getColumnModel().getColumn(6).setPreferredWidth(60);
        
    }
    
    private Object[] getRowData(Service service) {
        DecimalFormat df = new DecimalFormat("#,##0 VNĐ");
        return new Object[]{
            getServiceImage(service.getImage()),
            service.getName(),
            df.format(service.getPrice()),
            service.getDescription(),
            service.getUnit(),
            service.getQuantity(),
            service.getSold(),
            service.getStatus(),};
    }
    
    private void applyTableStyle(JTable table) {
        
        btnAddNew.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/add.svg", 0.35f));
        buttonUpdate.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/edit.svg", 0.35f));
        btnDelete.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/delete.svg", 0.35f));
        buttonPrint.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/excel.svg", 0.35f));
        btnUpload.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/upload.svg", 0.35f));
        //  Change scroll style
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
    
    private TableCellRenderer getAlignmentCellRender(TableCellRenderer oldRender, boolean header) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = oldRender.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (com instanceof JLabel) {
                    JLabel label = (JLabel) com;
                    if (column == 0 || column == 2 || column == 4 || column == 5 || column == 6 || column == 7) {
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                    } else if (column == 1 || column == 3) {
                        label.setHorizontalAlignment(SwingConstants.LEADING);
                    }
                    if (!header) {
                        if (column == 2) {
                            com.setForeground(new Color(17, 182, 60));
                        } else {
                            if (column == 7) {
                                if (value.toString().equals("Active")) {
                                    com.setForeground(new Color(17, 182, 60));
                                } else {
                                    com.setForeground(new Color(225, 17, 60));
                                }
                            } else {
                                com.setForeground(tblService.getForeground());
                            }
                        }
                    }
                }
                
                return com;
            }
        };
    }
    
    public ImageIcon getServiceImage(String path) {
        ImageIcon originalIcon = null;
        if (path == null) {
            originalIcon = new ImageIcon("src/minisoccerfieldmanagement/image/service/service_image_default.png");
        } else {
            originalIcon = new ImageIcon("src/minisoccerfieldmanagement/image/service/" + path);
        }
        Image originalImage = originalIcon.getImage();
        
        Image scaledImage = originalImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }
    
    public class CustomTableModel extends DefaultTableModel {
        
        public CustomTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }
        
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0) {
                return ImageIcon.class;
            } else {
                return super.getColumnClass(columnIndex);
            }
        }
        
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblService = new javax.swing.JTable();
        jScrollSearch = new javax.swing.JScrollPane();
        jListSearch = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        textSearch = new javax.swing.JTextField();
        buttonSearch = new javax.swing.JButton();
        buttonPrint = new javax.swing.JButton();
        buttonNext = new javax.swing.JButton();
        comboBoxLimit = new javax.swing.JComboBox<>();
        buttonPrevous = new javax.swing.JButton();
        comboBoxDirection = new javax.swing.JComboBox<>();
        comboBoxOrderBy = new javax.swing.JComboBox<>();
        comboBoxStatusFielter = new javax.swing.JComboBox<>();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        textServiceName = new javax.swing.JTextField();
        textServicePrice = new javax.swing.JTextField();
        comboBoxStatus = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        textServiceDescription = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        crazyPanel4 = new raven.crazypanel.CrazyPanel();
        btnAddNew = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnUpload = new javax.swing.JButton();
        textServiceUnit = new javax.swing.JTextField();
        pictureBoxServiceImage = new minisoccerfieldmanagement.util.PictureBox();
        textQuantity = new javax.swing.JTextField();
        buttonClean = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1188, 696));
        setPreferredSize(new java.awt.Dimension(1188, 696));
        setLayout(null);

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
        crazyPanel1.setLayout(null);

        tblService.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblService.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblServiceMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblService);

        crazyPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(15, 60, 778, 590);

        jListSearch.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollSearch.setViewportView(jListSearch);

        crazyPanel1.add(jScrollSearch);
        jScrollSearch.setBounds(20, 50, 170, 146);

        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        buttonPrint.setText("Print");

        buttonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNextActionPerformed(evt);
            }
        });

        comboBoxLimit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "20", "30", "50", "All" }));
        comboBoxLimit.setVerifyInputWhenFocusTarget(false);

        buttonPrevous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrevousActionPerformed(evt);
            }
        });

        comboBoxDirection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Decrease", "Increase" }));
        comboBoxDirection.setVerifyInputWhenFocusTarget(false);

        comboBoxOrderBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Price", "Quantity", "Sold" }));
        comboBoxOrderBy.setVerifyInputWhenFocusTarget(false);

        comboBoxStatusFielter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive", "All" }));
        comboBoxStatusFielter.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(comboBoxStatusFielter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxOrderBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxDirection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonPrevous, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonPrint)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(buttonPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonPrevous, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboBoxLimit)
                        .addComponent(textSearch, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(buttonSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboBoxDirection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBoxOrderBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBoxStatusFielter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8))
        );

        crazyPanel1.add(jPanel1);
        jPanel1.setBounds(10, 10, 777, 40);

        add(crazyPanel1);
        crazyPanel1.setBounds(6, 25, 793, 654);

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

        comboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));
        comboBoxStatus.setVerifyInputWhenFocusTarget(false);

        textServiceDescription.setColumns(20);
        textServiceDescription.setRows(5);
        jScrollPane2.setViewportView(textServiceDescription);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
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
        btnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewActionPerformed(evt);
            }
        });
        crazyPanel4.add(btnAddNew);

        buttonUpdate.setText("Update");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });
        crazyPanel4.add(buttonUpdate);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        crazyPanel4.add(btnDelete);

        jLabel10.setText("Quantity");

        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        buttonClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCleanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout crazyPanel3Layout = new javax.swing.GroupLayout(crazyPanel3);
        crazyPanel3.setLayout(crazyPanel3Layout);
        crazyPanel3Layout.setHorizontalGroup(
            crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(crazyPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpload)
                        .addGap(47, 47, 47)
                        .addComponent(buttonClean, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(crazyPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(crazyPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                                    .addComponent(textServiceName, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textServicePrice, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(comboBoxStatus, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textQuantity)
                                    .addComponent(textServiceUnit, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(crazyPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                        .addComponent(pictureBoxServiceImage, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(91, 91, 91))))
        );
        crazyPanel3Layout.setVerticalGroup(
            crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(pictureBoxServiceImage, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonClean, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textServiceName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crazyPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textServicePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textServiceUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(textQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(crazyPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(crazyPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        add(crazyPanel3);
        crazyPanel3.setBounds(811, 25, 379, 671);
    }// </editor-fold>//GEN-END:initComponents

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
                    Image image = myImage.getImage().getScaledInstance(pictureBoxServiceImage.getWidth(), pictureBoxServiceImage.getHeight(), Image.SCALE_SMOOTH);
                    pictureBoxServiceImage.setImage(new ImageIcon(image));
                    pictureBoxServiceImage.repaint();
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
    
    private Service getDataFromForm() {
        Service newService = new Service();
        
        String name = textServiceName.getText().trim();
        String description = textServiceDescription.getText().trim();
        String unit = textServiceUnit.getText().trim();
        String price = textServicePrice.getText().trim();
        String quantity = textQuantity.getText().trim();
        String status = comboBoxStatus.getSelectedItem().toString();
        
        if (name.isBlank() || unit.isBlank() || price.isBlank() || quantity.isBlank()) {
            MessageAlerts.getInstance().showMessage("Wrong format", "Please do not leave fields blank", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {
                    }
                }
            });
            return null;
        }
        if (price.matches(".*[^0-9].*")) {
            MessageAlerts.getInstance().showMessage("Wrong format", "Price contain only numeric characters, cannot contain another characters", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {
                        
                    }
                }
            });
            return null;
        }
        
        if (quantity.matches(".*[^0-9].*")) {
            MessageAlerts.getInstance().showMessage("Wrong format", "Quantity contain only numeric characters, cannot contain another characters", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {
                        
                    }
                }
            });
            return null;
        }
        
        newService.setName(name);
        newService.setDescription(description);
        newService.setPrice(new BigDecimal(price));
        newService.setUnit(unit);
        newService.setStatus(status);
        
        if (tempPicture != null) {
            String picturePath = tempPicture.getAbsolutePath();
            String newName = System.currentTimeMillis() + "_" + newService.getName().replaceAll(" ", "") + picturePath.substring(picturePath.lastIndexOf('.'));
            newService.setImage(newName);
            if (!saveFile(tempPicture, newName)) {
                return null;
            }
        }
        return newService;
    }
    

    private void tblServiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblServiceMouseClicked
        selectedServiceIndex = tblService.getSelectedRow();
        Service serviceSeleted = listService.get(selectedServiceIndex);
        displayService(serviceSeleted);
    }//GEN-LAST:event_tblServiceMouseClicked

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        if (selectedServiceIndex != -1) {
            cleanField();
            return;
        }
        Service newService = getDataFromForm();
        if (serviceService.add(newService)) {
            MessageAlerts.getInstance().showMessage("Success", "", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {
                        
                    }
                }
            });
        } else {
            MessageAlerts.getInstance().showMessage("Failed", "Please check and try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {
                        
                    }
                }
            });
        }
        tempPicture = null;
        
        if (newService != null) {
            model.insertRow(0, getRowData(newService));
        }
    }//GEN-LAST:event_btnAddNewActionPerformed

    private void buttonCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCleanActionPerformed
        cleanField();
    }//GEN-LAST:event_buttonCleanActionPerformed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        if (selectedServiceIndex == -1) {
            return;
        }
        
        Service updateService = getDataFromForm();
        updateService.setId(listService.get(selectedServiceIndex).getId());
        
        if (tempPicture == null) {
            updateService.setImage(listService.get(selectedServiceIndex).getImage());
        }
        if (serviceService.update(updateService)) {
            MessageAlerts.getInstance().showMessage("Success", "", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {
                        
                    }
                }
            });
            
            model.removeRow(selectedServiceIndex);
            listService.remove(selectedServiceIndex);
            
            if (status.equals(updateService.getStatus()) || status.equals("")) {
                model.insertRow(selectedServiceIndex, getRowData(updateService));
                listService.add(selectedServiceIndex, updateService);
                
            }
            
            selectedServiceIndex = -1;
            
            cleanField();
        } else {
            MessageAlerts.getInstance().showMessage("Failed", "Please check and try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {
                        
                    }
                }
            });
        }

    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedServiceIndex != -1) {
            MessageAlerts.getInstance().showMessage("Delete Service", "You definitely want to delete the service with name: " + model.getValueAt(selectedServiceIndex, 1).toString(), MessageAlerts.MessageType.WARNING, MessageAlerts.YES_NO_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.YES_OPTION) {
                        if (serviceService.softDelete(listService.get(selectedServiceIndex).getId())) {
                            MessageAlerts.getInstance().showMessage("Deleted", "Successfully deleted data", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                                @Override
                                public void action(PopupController pc, int i) {
                                    if (i == MessageAlerts.CLOSED_OPTION) {
                                        
                                    }
                                }
                            });
                            model.removeRow(selectedServiceIndex);
                            listService.remove(selectedServiceIndex);
                            cleanField();
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

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        jListSearch.setVisible(false);
        jScrollSearch.setVisible(false);
        
        model.setRowCount(0);
        loadDataIntoJTable(textSearch.getText(), status, -1, 0, direction, orderBy);
        
        textSearch.setText("");
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void buttonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNextActionPerformed
        if (limit == -1) {
            return;
        }
        if (!listService.isEmpty()) {
            page++;
            model.setRowCount(0);
            loadDataIntoJTable("", status, limit, limit * page, direction, orderBy);
        }
        System.out.println(page);
    }//GEN-LAST:event_buttonNextActionPerformed

    private void buttonPrevousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrevousActionPerformed
        if (limit == -1) {
            return;
        }
        if (page > 0) {
            page--;
            model.setRowCount(0);
            loadDataIntoJTable("", status, limit, limit * page - limit, direction, orderBy);
        }
        System.out.println(page);
    }//GEN-LAST:event_buttonPrevousActionPerformed
    
    private void cleanField() {
        pictureBoxServiceImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/service/service_image_default.png"));
        pictureBoxServiceImage.repaint();
        
        textServiceName.setText("");
        textServiceDescription.setText("");
        textServicePrice.setText("");
        textServiceUnit.setText("");
        textQuantity.setText("");
        comboBoxStatus.setSelectedItem(0);
    }
    
    private void displayService(Service service) {
        if (service.getImage() != null) {
            pictureBoxServiceImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/service/" + service.getImage()));
        } else {
            pictureBoxServiceImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/service/service_image_default.png"));
        }
        pictureBoxServiceImage.repaint();
        
        textServiceName.setText(service.getName());
        textServiceDescription.setText(service.getDescription());
        textServicePrice.setText(service.getPrice().stripTrailingZeros().toPlainString());
        textServiceUnit.setText(service.getUnit());
        textQuantity.setText(String.valueOf(service.getQuantity()));
        if (service.getStatus().equals("Active")) {
            comboBoxStatus.setSelectedIndex(0);
        } else {
            comboBoxStatus.setSelectedIndex(1);
        }
    }
    
    private boolean saveFile(File file, String fileName) {
        File destinationFolder = new File("src/minisoccerfieldmanagement/image/service");
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
    private javax.swing.JButton btnUpload;
    private javax.swing.JButton buttonClean;
    private javax.swing.JButton buttonNext;
    private javax.swing.JButton buttonPrevous;
    private javax.swing.JButton buttonPrint;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JComboBox<String> comboBoxDirection;
    private javax.swing.JComboBox<String> comboBoxLimit;
    private javax.swing.JComboBox<String> comboBoxOrderBy;
    private javax.swing.JComboBox<String> comboBoxStatus;
    private javax.swing.JComboBox<String> comboBoxStatusFielter;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private raven.crazypanel.CrazyPanel crazyPanel4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jListSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollSearch;
    private minisoccerfieldmanagement.util.PictureBox pictureBoxServiceImage;
    private javax.swing.JTable tblService;
    private javax.swing.JTextField textQuantity;
    private javax.swing.JTextField textSearch;
    private javax.swing.JTextArea textServiceDescription;
    private javax.swing.JTextField textServiceName;
    private javax.swing.JTextField textServicePrice;
    private javax.swing.JTextField textServiceUnit;
    // End of variables declaration//GEN-END:variables
}
