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
import minisoccerfieldmanagement.login.UserSession;
import minisoccerfieldmanagement.model.Customer;
import minisoccerfieldmanagement.model.Field;
import minisoccerfieldmanagement.model.MemberShip;
import minisoccerfieldmanagement.model.Service;
import minisoccerfieldmanagement.model.ServiceItems;
import minisoccerfieldmanagement.model.ServiceUsage;
import minisoccerfieldmanagement.model.Transaction;
import minisoccerfieldmanagement.service.CustomerServiceImpl;
import minisoccerfieldmanagement.service.MemberShipServiceImpl;
import minisoccerfieldmanagement.service.ServiceItemsServiceImpl;
import minisoccerfieldmanagement.service.ServiceServiceImpl;
import minisoccerfieldmanagement.service.ServiceUsageServiceImpl;
import minisoccerfieldmanagement.service.TransactionServiceImpl;
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
    private final ServiceItemsServiceImpl serviceItemsService = new ServiceItemsServiceImpl();
    private final MemberShipServiceImpl memberShipService = new MemberShipServiceImpl();
    private final TransactionServiceImpl transactionService = new TransactionServiceImpl();
    private final ServiceUsageServiceImpl serviceUsageService = new ServiceUsageServiceImpl();

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
    private Transaction transaction = null;

    private BigDecimal totalPrice = BigDecimal.ZERO;
    private BigDecimal totalDiscount = BigDecimal.ZERO;
    private BigDecimal totalAddition = BigDecimal.ZERO;
    private BigDecimal finalAmount = BigDecimal.ZERO;

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
                if (service.getQuantity() == 0) {
                    return;
                }

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
                        if (serviceSelected.getQuantity() >= quantityOrder) {
                            serviceSelected.setQuantityOrder(quantityOrder);
                            listServiceInCart.add(serviceSelected);
                            model.addRow(getRowData(serviceSelected));
                        }
                    }
                    updatePaymentInformation();
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
        model = new CustomTableModel(new Object[][]{}, new String[]{"Image", "Name", "Price", "Description", "Unit", "Quantity", "Total Price"});
        table.setModel(model);
        table.setRowHeight(70);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(130);
        table.getColumnModel().getColumn(3).setPreferredWidth(130);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(60);
        table.getColumnModel().getColumn(6).setPreferredWidth(150);

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
            service.getQuantityOrder(),
            Utils.formatVND(service.getPrice().multiply(BigDecimal.valueOf((double) service.getQuantityOrder())))
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        textViewCustomerName = new javax.swing.JLabel();
        textViewCustomerPhone = new javax.swing.JLabel();
        textViewCustomerMembership = new javax.swing.JLabel();
        textViewTotalPrice = new javax.swing.JLabel();
        textViewFinalAmount = new javax.swing.JLabel();
        textViewTotalDiscount = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaNote = new javax.swing.JTextArea();
        crazyPanel8 = new raven.crazypanel.CrazyPanel();
        buttonClean = new javax.swing.JButton();
        buttonPayment = new javax.swing.JButton();
        buttonExport = new javax.swing.JButton();
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
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(crazyPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel6Layout.createSequentialGroup()
                        .addGroup(crazyPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editTextPhoneNumber))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editTextCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel6Layout.createSequentialGroup()
                        .addComponent(buttonRemoveServiceInCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(102, 102, 102))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel6Layout.createSequentialGroup()
                        .addComponent(buttonSelectUser)
                        .addGap(144, 144, 144))))
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

        crazyPanel3.add(crazyPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, 360, 160));

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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("PAYMENT INFORMATION");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Customer name:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Phone number:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Membership type:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Total price:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Total discount:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Final amount:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Note:");

        textAreaNote.setColumns(20);
        textAreaNote.setRows(5);
        jScrollPane2.setViewportView(textAreaNote);

        javax.swing.GroupLayout crazyPanel7Layout = new javax.swing.GroupLayout(crazyPanel7);
        crazyPanel7.setLayout(crazyPanel7Layout);
        crazyPanel7Layout.setHorizontalGroup(
            crazyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(105, 105, 105))
            .addGroup(crazyPanel7Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(crazyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(crazyPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textViewFinalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(crazyPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(textViewTotalDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(crazyPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(textViewTotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(crazyPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textViewCustomerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(crazyPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textViewCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(crazyPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textViewCustomerMembership, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        crazyPanel7Layout.setVerticalGroup(
            crazyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addGap(24, 24, 24)
                .addGroup(crazyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textViewCustomerName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crazyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textViewCustomerPhone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crazyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textViewCustomerMembership))
                .addGap(32, 32, 32)
                .addGroup(crazyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(crazyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(textViewTotalPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crazyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(textViewTotalDiscount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crazyPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(textViewFinalAmount))
                .addGap(46, 46, 46))
        );

        crazyPanel3.add(crazyPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 360, 390));

        crazyPanel8.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel8.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            null
        ));

        buttonClean.setText("Clear");
        buttonClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCleanActionPerformed(evt);
            }
        });

        buttonPayment.setText("Payment");
        buttonPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPaymentActionPerformed(evt);
            }
        });

        buttonExport.setText("Export");
        buttonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout crazyPanel8Layout = new javax.swing.GroupLayout(crazyPanel8);
        crazyPanel8.setLayout(crazyPanel8Layout);
        crazyPanel8Layout.setHorizontalGroup(
            crazyPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonClean, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        crazyPanel8Layout.setVerticalGroup(
            crazyPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crazyPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonExport, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(buttonPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonClean, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        crazyPanel3.add(crazyPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 613, 380, -1));

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
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE))
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

    private void clear() {
        listServiceInCart.clear();
        model.setRowCount(0);

        customer = null;
        editTextPhoneNumber.setText("");
        editTextCustomerName.setText("");

        textViewCustomerName.setText("");
        textViewCustomerPhone.setText("");
        textViewCustomerMembership.setText("");
        textViewTotalPrice.setText("");
        textViewTotalDiscount.setText("");
        textViewFinalAmount.setText("");

        totalPrice = BigDecimal.ZERO;
        totalDiscount = BigDecimal.ZERO;
        totalAddition = BigDecimal.ZERO;
        finalAmount = BigDecimal.ZERO;

        transaction = null;
        buttonPayment.setEnabled(true);
    }

    private void updatePaymentInformation() {
        totalPrice = BigDecimal.ZERO;

        for (Service serviceItem : listServiceInCart) {
            totalPrice = totalPrice.add(serviceItem.getPrice().multiply(BigDecimal.valueOf(serviceItem.getQuantityOrder())));
        }

        MemberShip memberShip = null;
        if (customer != null) {
            textViewCustomerName.setText(customer.getName());
            textViewCustomerPhone.setText(customer.getPhoneNumber());

            memberShip = memberShipService.findById(customer.getMemberShipId());
            if (memberShip != null) {
                textViewCustomerMembership.setText(memberShip.getName());
            } else {
                textViewCustomerMembership.setText("No membership");
            }
        }

        textViewTotalPrice.setText(Utils.formatVND(totalPrice));

        if (memberShip != null) {
            BigDecimal discount = totalPrice.multiply(BigDecimal.valueOf(0.01)).multiply(BigDecimal.valueOf((double) memberShip.getDiscountRate()));
            BigDecimal maxDiscount = memberShip.getMinimumSpendAmount().multiply(BigDecimal.valueOf(0.01));

            int compare = discount.compareTo(maxDiscount);
            if (compare > 0) {
                totalDiscount = maxDiscount;
                textViewTotalDiscount.setText("-" + Utils.formatVND(totalDiscount) + " (MAX)");
            } else {
                totalDiscount = discount;
                textViewTotalDiscount.setText("-" + Utils.formatVND(totalDiscount));
            }
        } else {
            textViewTotalDiscount.setText(Utils.formatVND(totalDiscount));
        }

        finalAmount = totalPrice.subtract(totalDiscount).add(totalAddition);
        textViewFinalAmount.setText(Utils.formatVND(finalAmount));
    }

    private void buttonPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPaymentActionPerformed
        updatePaymentInformation();

        ServiceUsage serviceUsage = new ServiceUsage();
        if (customer != null) {
            serviceUsage.setCustomerId(customer.getId());
        } else {
            serviceUsage.setCustomerId(-1);
        }
        serviceUsage.setNote(textAreaNote.getText());

        int serviceUsageId = serviceUsageService.addServiceUsageWithReturnId(serviceUsage);

        for (Service service : listServiceInCart) {
            ServiceItems serviceItem = new ServiceItems();
            serviceItem.setServiceUsageId(serviceUsageId);
            serviceItem.setServiceId(service.getId());
            serviceItem.setQuantity(service.getQuantityOrder());

            try {
                if (!serviceItemsService.add(serviceItem)) {
                    MessageAlerts.getInstance().showMessage("Error", "Please check and try again!!!", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                        @Override
                        public void action(PopupController pc, int i) {
                            if (i == MessageAlerts.CLOSED_OPTION) {

                            }
                        }
                    });
                    return;
                }

                if (service.getQuantity() >= service.getQuantityOrder()) {
                    if (!serviceService.updateSoldAndQuantity(service.getId(), service.getQuantityOrder())) {
                        MessageAlerts.getInstance().showMessage("Error", "Please check and try again!!!", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                            @Override
                            public void action(PopupController pc, int i) {
                                if (i == MessageAlerts.CLOSED_OPTION) {

                                }
                            }
                        });
                        return;
                    }
                } else {
                    MessageAlerts.getInstance().showMessage("Error", "Please check and try again!!!", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                        @Override
                        public void action(PopupController pc, int i) {
                            if (i == MessageAlerts.CLOSED_OPTION) {

                            }
                        }
                    });
                    return;
                }

            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        transaction = new Transaction();
        transaction.setServiceUsageId(serviceUsageId);
        transaction.setUserID(UserSession.getInstance().getUser().getId());
        transaction.setType("Retail");
        transaction.setTotalAmount(totalPrice);
        transaction.setDiscountAmount(totalDiscount);
        transaction.setAdditionalFee(totalAddition);
        transaction.setFinalAmount(finalAmount);

        try {
            int transactionId = transactionService.addTransactionWithReturnId(transaction);
            if (transactionId == -1) {
                MessageAlerts.getInstance().showMessage("Error", "Please check and try again!!!", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                    @Override
                    public void action(PopupController pc, int i) {
                        if (i == MessageAlerts.CLOSED_OPTION) {

                        }
                    }
                });
                return;
            } else {
                transaction = transactionService.findById(transactionId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (customer != null) {
            customerService.updateTotalSpend(customer.getId(), finalAmount);
        }

        MessageAlerts.getInstance().showMessage("Success", "Payment successfully!!!", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
            @Override
            public void action(PopupController pc, int i) {
                if (i == MessageAlerts.CLOSED_OPTION) {

                }
            }
        });

        buttonPayment.setEnabled(false);
    }//GEN-LAST:event_buttonPaymentActionPerformed

    private void buttonCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCleanActionPerformed
        clear();
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
        updatePaymentInformation();
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
            customer = customerService.findById(currentCustomerId);
        }
        updatePaymentInformation();
    }//GEN-LAST:event_buttonSelectUserActionPerformed

    private void buttonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExportActionPerformed
        if (transaction != null) {
            removeAll();
            PanelTransaction panelTransaction = new PanelTransaction(transaction);
            panelTransaction.setSize(1188, 696);
            add(panelTransaction);
            validate();
            repaint();
        }
    }//GEN-LAST:event_buttonExportActionPerformed

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
                        if (column == 2 || column == 6) {
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
    private javax.swing.JButton buttonClean;
    private javax.swing.JButton buttonExport;
    private javax.swing.JButton buttonNext;
    private javax.swing.JButton buttonPayment;
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
    private raven.crazypanel.CrazyPanel crazyPanel8;
    private javax.swing.JTextField editTextCustomerName;
    private javax.swing.JTextField editTextPhoneNumber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jListSearch;
    private javax.swing.JList<String> jListSearchCustomer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollSearch;
    private javax.swing.JScrollPane jScrollSearchCustomer;
    private javax.swing.JTable jTableServiceCart;
    private javax.swing.JPanel panelService;
    private javax.swing.JScrollPane scrollPanelService;
    private javax.swing.JTextArea textAreaNote;
    private javax.swing.JTextField textSearch;
    private javax.swing.JLabel textViewCustomerMembership;
    private javax.swing.JLabel textViewCustomerName;
    private javax.swing.JLabel textViewCustomerPhone;
    private javax.swing.JLabel textViewFinalAmount;
    private javax.swing.JLabel textViewTotalDiscount;
    private javax.swing.JLabel textViewTotalPrice;
    // End of variables declaration//GEN-END:variables
}
