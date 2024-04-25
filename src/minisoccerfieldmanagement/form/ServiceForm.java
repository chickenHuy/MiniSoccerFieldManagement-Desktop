package minisoccerfieldmanagement.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import minisoccerfieldmanagement.model.Customer;
import minisoccerfieldmanagement.model.Field;
import minisoccerfieldmanagement.model.MemberShip;
import minisoccerfieldmanagement.model.Service;
import minisoccerfieldmanagement.service.CustomerServiceImpl;
import minisoccerfieldmanagement.service.IMemberShipService;
import minisoccerfieldmanagement.service.MemberShipServiceImpl;
import minisoccerfieldmanagement.service.ServiceServiceImpl;
import minisoccerfieldmanagement.tabbed.TabbedForm;
import minisoccerfieldmanagement.util.EventItem;
import minisoccerfieldmanagement.util.GetQuantityOfService;
import minisoccerfieldmanagement.util.ServiceItem;
import minisoccerfieldmanagement.util.Utils;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;

public class ServiceForm extends TabbedForm {

    private final ServiceServiceImpl serviceService = new ServiceServiceImpl();
    private final CustomerServiceImpl customerService = new CustomerServiceImpl();
    private final MemberShipServiceImpl memberShipService = new MemberShipServiceImpl();

    private List<Service> listServiceInCart = null;
    private List<Service> listService = null;
    private DefaultListModel<String> listResultSearch = null;
    private DefaultListModel<String> listResultSearchCustomer = null;
    private EventItem event;
    private CustomTableModel model = null;
    private int selectedServiceId = -1;
    private int page = 0;
    private Timer timer = null;
    private Customer customer = null;

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ServiceForm() {
        initComponents();

        listServiceInCart = new ArrayList<>();
        setUpWidgit();
    }

    public ServiceForm(Service service) {
        initComponents();

        listServiceInCart = new ArrayList<>();
        listServiceInCart.add(service);

        setUpWidgit();
    }

    private void setUpWidgit() {
        listService = new ArrayList<>();

        buttonPrevous.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/left.svg", 0.35f));
        buttonNext.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/right.svg", 0.35f));
        buttonClean.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/clean.svg", 0.35f));
        buttonSearch.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/search.svg", 0.35f));
        buttonRemoveServiceInCard.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/delete.svg", 0.35f));

        crazyPanel1.setComponentZOrder(jScrollSearch, 0);
        jListSearch.setVisible(false);
        jScrollSearch.setVisible(false);

        crazyPanel3.setComponentZOrder(jScrollSearchCustomer, 0);
        jListSearchCustomer.setVisible(false);
        jScrollSearchCustomer.setVisible(false);

        listResultSearch = new DefaultListModel<>();
        jListSearch.setModel(listResultSearch);

        listResultSearchCustomer = new DefaultListModel<>();
        jListSearchCustomer.setModel(listResultSearchCustomer);

        textSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setListSuggest(textSearch.getText().trim(), "Active");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setListSuggest(textSearch.getText().trim(), "Active");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                setListSuggest(textSearch.getText().trim(), "Active");
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

        editTextPhoneNumber.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (isPhoneNumber(editTextPhoneNumber.getText().trim())) {
                    setListCustomerSuggest(editTextPhoneNumber.getText().trim());
                } else {
                    editTextPhoneNumber.setText("");
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (isPhoneNumber(editTextPhoneNumber.getText().trim())) {
                    setListCustomerSuggest(editTextPhoneNumber.getText().trim());
                } else {
                    editTextPhoneNumber.setText("");
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (isPhoneNumber(editTextPhoneNumber.getText().trim())) {
                    setListCustomerSuggest(editTextPhoneNumber.getText().trim());
                } else {
                    editTextPhoneNumber.setText("");
                }
            }
        });

        jListSearchCustomer.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedValue = (String) jListSearchCustomer.getSelectedValue();

                    if (selectedValue != null) {
                        String[] parts = selectedValue.split("-");
                        editTextCustomerName.setText(parts[1]);
                        editTextPhoneNumber.setText(parts[0]);

                        jListSearchCustomer.setVisible(false);
                        jScrollSearchCustomer.setVisible(false);
                    }
                }
            }
        });

        setUpListService();
        setUpListServiceInCart();
    }

    private boolean isPhoneNumber(String number) {
        if (number.matches(".*[^0-9].*")) {
            MessageAlerts.getInstance().showMessage("Not number", "Quantity contain only numeric characters, cannot contain another characters", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });
            return false;
        }
        if (number.length() > 10) {
            MessageAlerts.getInstance().showMessage("Over limit", "Ten-digit phone number!!!", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });
            return false;
        }
        return true;
    }

    private void setListCustomerSuggest(String keyWord) {
        listResultSearchCustomer.clear();

        if (keyWord.equals("") || keyWord.isBlank()) {
            jListSearchCustomer.setVisible(false);
            jScrollSearchCustomer.setVisible(false);
            return;
        }

        if (keyWord.length() == 10 && !editTextCustomerName.getText().isEmpty()) {
            jListSearchCustomer.setVisible(false);
            jScrollSearchCustomer.setVisible(false);
            return;
        }

        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jListSearchCustomer.setVisible(true);
                jScrollSearchCustomer.setVisible(true);
                List<Customer> listSuggestCustomer = customerService.findAllAndFilter(keyWord, -1, 0);
                if (!listSuggestCustomer.isEmpty()) {
                    for (Customer customer : listSuggestCustomer) {
                        listResultSearchCustomer.addElement(customer.getPhoneNumber() + "-" + customer.getName());
                    }
                } else {
                    jListSearchCustomer.setVisible(false);
                    jScrollSearchCustomer.setVisible(false);
                }
                timer = null;
            }
        });

        timer.setRepeats(false);
        timer.start();

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
                } else {
                    jListSearch.setVisible(false);
                    jScrollSearch.setVisible(false);
                }
                timer = null;
            }
        });

        timer.setRepeats(false);
        timer.start();

    }

    private void loadDataIntoJTable(String keyword, String status, int limit, int offset, String orderBy, String field) {
        listService = serviceService.loadDataIntoJTable(keyword, status, limit, offset, orderBy, field);

        panelService.removeAll();
        panelService.revalidate();
        panelService.repaint();

        addData(listService);
    }

    public void addData(List<Service> data) {
        this.setEvent(new EventItem() {
            @Override
            public void fieldClick(Component com, Field item) {
                setSelectedServiceId(item.getId());
            }

            @Override
            public void serviceItemClick(Component com, Service service) {
                setSelectedServiceId(service.getId());
                GetQuantityOfService getQuantityOfService = new GetQuantityOfService(service);
                getQuantityOfService.setLocationRelativeTo(null);
                getQuantityOfService.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                getQuantityOfService.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        getQuantityOfService.dispose();
                    }
                });
                getQuantityOfService.setQuantityListener((Service serviceSelected, int quantityOrder) -> {
                    int index = 0;
                    boolean inCart = false;

                    for (Service serviceItem : listServiceInCart) {
                        if (serviceSelected.getId() == serviceItem.getId()) {
                            inCart = true;
                            int totalQuantityOrder = serviceItem.getQuantityOrder() + quantityOrder;
                            if (totalQuantityOrder <= serviceItem.getQuantity()) {
                                serviceItem.setQuantityOrder(totalQuantityOrder);

                                model.removeRow(index);
                                model.insertRow(index, getRowData(serviceItem));
                            } else {
                                MessageAlerts.getInstance().showMessage("Insufficient", "The quantity of products in stock is " + serviceItem.getQuantity() + "!!!", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                                    @Override
                                    public void action(PopupController pc, int i) {
                                        if (i == MessageAlerts.CLOSED_OPTION) {

                                        }
                                    }
                                });
                            }
                            break;
                        }
                        index++;
                    }
                    if (!inCart) {
                        serviceSelected.setQuantityOrder(quantityOrder);
                        listServiceInCart.add(serviceSelected);
                        model.addRow(getRowData(serviceSelected));
                    }
                });
                getQuantityOfService.setVisible(true);
            }
        });
        for (Service i : data) {
            this.addItem(i);
        }
    }

    private void setUpListService() {
        panelService.setLayout(new GridLayout(0, 4, 10, 10));
        panelService.setBorder(new EmptyBorder(10, 15, 10, 15));
        scrollPanelService.getVerticalScrollBar().setUnitIncrement(17);

        loadDataIntoJTable("", "Active", 20, 0, "", "");
    }

    public void setSelectedServiceId(int selectedServiceId) {
        int oldSelectedServiceId = this.selectedServiceId;
        this.selectedServiceId = selectedServiceId;
        propertyChangeSupport.firePropertyChange("selectedServiceId", oldSelectedServiceId, selectedServiceId);
    }

    public int getSelectedServiceId() {
        return selectedServiceId;
    }

    public void setEvent(EventItem event) {
        this.event = event;
    }

    public void addItem(Service data) {
        ServiceItem item = new ServiceItem();
        item.setData(data);
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    setSelectedServiceId(data.getId());
                    if (event != null) {
                        event.serviceItemClick(item, data);
                    }
                }
            }
        });

        panelService.add(item);
        panelService.revalidate();
        panelService.repaint();
    }

    private void setUpListServiceInCart() {
        setUpJTable(jTableServiceCart);
    }

    private void setUpJTable(JTable table) {
        model = new CustomTableModel(new Object[][]{}, new String[]{"Image", "Name", "Price", "Description", "Unit", "Quantity"});
        table.setModel(model);
        table.setRowHeight(70);
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(130);
        table.getColumnModel().getColumn(3).setPreferredWidth(130);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(60);

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

    private Object[] getRowData(Service service) {
        return new Object[]{
            getServiceImage(service.getImage()),
            service.getName(),
            Utils.formatVND(service.getPrice()),
            service.getDescription(),
            service.getUnit(),
            service.getQuantityOrder()};
    }

    public ImageIcon getServiceImage(String path) {
        ImageIcon originalIcon = null;
        if (path == null) {
            originalIcon = new ImageIcon("src/minisoccerfieldmanagement/image/service/service_image_default.png");
        } else {
            originalIcon = new ImageIcon("src/minisoccerfieldmanagement/image/service/" + path);
        }
        Image originalImage = originalIcon.getImage();

        Image scaledImage = originalImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        textSearch = new javax.swing.JTextField();
        buttonSearch = new javax.swing.JButton();
        buttonReload = new javax.swing.JButton();
        buttonNext = new javax.swing.JButton();
        buttonPrevous = new javax.swing.JButton();
        scrollPanelService = new javax.swing.JScrollPane();
        panelService = new javax.swing.JPanel();
        jScrollSearch = new javax.swing.JScrollPane();
        jListSearch = new javax.swing.JList<>();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        btnUpload = new javax.swing.JButton();
        buttonClean = new javax.swing.JButton();
        crazyPanel6 = new raven.crazypanel.CrazyPanel();
        buttonRemoveServiceInCard1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        editTextPhoneNumber = new javax.swing.JTextField();
        editTextCustomerName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        buttonSelectUser = new javax.swing.JButton();
        jScrollSearchCustomer = new javax.swing.JScrollPane();
        jListSearchCustomer = new javax.swing.JList<>();
        crazyPanel7 = new raven.crazypanel.CrazyPanel();
        crazyPanel5 = new raven.crazypanel.CrazyPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableServiceCart = new javax.swing.JTable();
        buttonRemoveServiceInCard = new javax.swing.JButton();

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
        crazyPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        crazyPanel1.add(textSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 6, 150, 23));

        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });
        crazyPanel1.add(buttonSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 6, 31, 23));

        buttonReload.setText("Reload");
        buttonReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReloadActionPerformed(evt);
            }
        });
        crazyPanel1.add(buttonReload, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 6, -1, -1));

        buttonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNextActionPerformed(evt);
            }
        });
        crazyPanel1.add(buttonNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(724, 6, 40, 23));

        buttonPrevous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrevousActionPerformed(evt);
            }
        });
        crazyPanel1.add(buttonPrevous, new org.netbeans.lib.awtextra.AbsoluteConstraints(678, 6, 40, 23));

        javax.swing.GroupLayout panelServiceLayout = new javax.swing.GroupLayout(panelService);
        panelService.setLayout(panelServiceLayout);
        panelServiceLayout.setHorizontalGroup(
            panelServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 792, Short.MAX_VALUE)
        );
        panelServiceLayout.setVerticalGroup(
            panelServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 384, Short.MAX_VALUE)
        );

        scrollPanelService.setViewportView(panelService);

        crazyPanel1.add(scrollPanelService, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 35, 760, 380));

        jScrollSearch.setPreferredSize(new java.awt.Dimension(55, 146));

        jListSearch.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollSearch.setViewportView(jListSearch);

        crazyPanel1.add(jScrollSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 35, 150, -1));

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
        crazyPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });
        crazyPanel3.add(btnUpload, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 659, 107, -1));

        buttonClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCleanActionPerformed(evt);
            }
        });
        crazyPanel3.add(buttonClean, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 659, 28, 23));

        crazyPanel6.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel6.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            null
        ));

        buttonRemoveServiceInCard1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveServiceInCard1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("CUSTOMER INFORMATION");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Phone number:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Customer name:");

        buttonSelectUser.setText("Select");
        buttonSelectUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelectUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout crazyPanel6Layout = new javax.swing.GroupLayout(crazyPanel6);
        crazyPanel6.setLayout(crazyPanel6Layout);
        crazyPanel6Layout.setHorizontalGroup(
            crazyPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel6Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(crazyPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel6Layout.createSequentialGroup()
                        .addGroup(crazyPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editTextPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(crazyPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(editTextCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel6Layout.createSequentialGroup()
                                        .addComponent(buttonSelectUser)
                                        .addGap(108, 108, 108)))))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel6Layout.createSequentialGroup()
                        .addComponent(buttonRemoveServiceInCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(92, 92, 92))))
        );
        crazyPanel6Layout.setVerticalGroup(
            crazyPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(crazyPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(editTextPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crazyPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editTextCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(buttonSelectUser)
                .addGap(85, 85, 85)
                .addComponent(buttonRemoveServiceInCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        crazyPanel3.add(crazyPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 14, -1, 160));

        jScrollSearchCustomer.setPreferredSize(new java.awt.Dimension(50, 146));

        jListSearchCustomer.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollSearchCustomer.setViewportView(jListSearchCustomer);

        crazyPanel3.add(jScrollSearchCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 190, 100));

        crazyPanel7.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel7.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            null
        ));

        javax.swing.GroupLayout crazyPanel7Layout = new javax.swing.GroupLayout(crazyPanel7);
        crazyPanel7.setLayout(crazyPanel7Layout);
        crazyPanel7Layout.setHorizontalGroup(
            crazyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        crazyPanel7Layout.setVerticalGroup(
            crazyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        crazyPanel3.add(crazyPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 360, 220));

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

        jTableServiceCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableServiceCart);

        buttonRemoveServiceInCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveServiceInCardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout crazyPanel5Layout = new javax.swing.GroupLayout(crazyPanel5);
        crazyPanel5.setLayout(crazyPanel5Layout);
        crazyPanel5Layout.setHorizontalGroup(
            crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRemoveServiceInCard, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        crazyPanel5Layout.setVerticalGroup(
            crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRemoveServiceInCard, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(crazyPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed

    }//GEN-LAST:event_btnUploadActionPerformed

    private void buttonCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCleanActionPerformed
    }//GEN-LAST:event_buttonCleanActionPerformed

    private void buttonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNextActionPerformed
        if (!listService.isEmpty()) {
            page++;
            loadDataIntoJTable("", "Active", 20, 20 * page, "", "");
        }
    }//GEN-LAST:event_buttonNextActionPerformed

    private void buttonPrevousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrevousActionPerformed
        if (page > 0) {
            loadDataIntoJTable("", "Active", 20, 20 * page - 20, "", "");
            page--;
        }
    }//GEN-LAST:event_buttonPrevousActionPerformed

    private void buttonReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReloadActionPerformed
        page = 0;
        loadDataIntoJTable("", "Active", 20, 20 * page, "", "");
    }//GEN-LAST:event_buttonReloadActionPerformed

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        jListSearch.setVisible(false);
        jScrollSearch.setVisible(false);

        loadDataIntoJTable(textSearch.getText(), "Active", -1, 0, "", "");

        textSearch.setText("");
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void buttonRemoveServiceInCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveServiceInCardActionPerformed
        int row = jTableServiceCart.getSelectedRow();
        if (row != -1 && row <= listServiceInCart.size()) {
            model.removeRow(row);
            listServiceInCart.remove(row);
        }
    }//GEN-LAST:event_buttonRemoveServiceInCardActionPerformed

    private void buttonRemoveServiceInCard1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveServiceInCard1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRemoveServiceInCard1ActionPerformed

    private void buttonSelectUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelectUserActionPerformed
        if (editTextCustomerName.getText().trim().isEmpty() || editTextPhoneNumber.getText().trim().isEmpty()) {
            MessageAlerts.getInstance().showMessage("Wrong format", "Please do not leave fields blank", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {
                    }
                }
            });
            return;
        }
        if (editTextPhoneNumber.getText().trim().length() < 10) {
            MessageAlerts.getInstance().showMessage("Not a phone number", "Ten-digit phone number!!!", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });
            return;
        }
        if (!isPhoneNumber(editTextPhoneNumber.getText())) {
            MessageAlerts.getInstance().showMessage("Not number", "Quantity contain only numeric characters, cannot contain another characters", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });
            return;
        }

        customer = customerService.findByPhoneNumber(editTextPhoneNumber.getText().trim());
        if (customer == null) {
            customer = new Customer();
            MemberShip membership = memberShipService.findBySpendAmount(BigDecimal.ZERO);
            customer.setName(editTextCustomerName.getText());
            customer.setPhoneNumber(editTextPhoneNumber.getText());
            customer.setMemberShipId(membership.getId());

            int currentCustomerId = customerService.addWithReturnId(customer);
            System.out.println(currentCustomerId);
        } else {
            System.out.println(customer.getId());
        }

    }//GEN-LAST:event_buttonSelectUserActionPerformed

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

    private TableCellRenderer getAlignmentCellRender(TableCellRenderer oldRender, boolean header) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = oldRender.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (com instanceof JLabel) {
                    JLabel label = (JLabel) com;
                    if (column == 0 || column == 2 || column == 4 || column == 5) {
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                    } else if (column == 1 || column == 3) {
                        if (!header) {
                            label.setHorizontalAlignment(SwingConstants.LEADING);
                        }
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
                                com.setForeground(jTableServiceCart.getForeground());
                            }
                        }
                    }
                }

                return com;
            }
        };
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpload;
    private javax.swing.JButton buttonClean;
    private javax.swing.JButton buttonNext;
    private javax.swing.JButton buttonPrevous;
    private javax.swing.JButton buttonReload;
    private javax.swing.JButton buttonRemoveServiceInCard;
    private javax.swing.JButton buttonRemoveServiceInCard1;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JButton buttonSelectUser;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private raven.crazypanel.CrazyPanel crazyPanel5;
    private raven.crazypanel.CrazyPanel crazyPanel6;
    private raven.crazypanel.CrazyPanel crazyPanel7;
    private javax.swing.JTextField editTextCustomerName;
    private javax.swing.JTextField editTextPhoneNumber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jListSearch;
    private javax.swing.JList<String> jListSearchCustomer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollSearch;
    private javax.swing.JScrollPane jScrollSearchCustomer;
    private javax.swing.JTable jTableServiceCart;
    private javax.swing.JPanel panelService;
    private javax.swing.JScrollPane scrollPanelService;
    private javax.swing.JTextField textSearch;
    // End of variables declaration//GEN-END:variables
}
