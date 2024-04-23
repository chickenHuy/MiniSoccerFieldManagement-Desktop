package minisoccerfieldmanagement.form;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import minisoccerfieldmanagement.model.Field;
import minisoccerfieldmanagement.model.Service;
import minisoccerfieldmanagement.service.ServiceServiceImpl;
import minisoccerfieldmanagement.tabbed.TabbedForm;
import minisoccerfieldmanagement.util.EventItem;
import minisoccerfieldmanagement.util.GetQuantityOfService;
import minisoccerfieldmanagement.util.ServiceItem;

public class ServiceForm extends TabbedForm {
    
    private final ServiceServiceImpl serviceService = new ServiceServiceImpl();
    
    private List<Service> listServiceInCart = null;
    private List<Service> listService = null;
    private DefaultListModel<String> listResultSearch = null;
    private EventItem event;
    private int selectedServiceId = -1;
    private int page = 0;
    private Timer timer = null;
    
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
        
        crazyPanel1.setComponentZOrder(jScrollSearch, 0);
        
        listResultSearch = new DefaultListModel<>();
        jListSearch.setModel(listResultSearch);
        
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
        
        setUpListService();
        setUpListServiceInCart();
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
        crazyPanel5 = new raven.crazypanel.CrazyPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

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

        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        buttonReload.setText("Reload");
        buttonReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReloadActionPerformed(evt);
            }
        });

        buttonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNextActionPerformed(evt);
            }
        });

        buttonPrevous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrevousActionPerformed(evt);
            }
        });

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

        jScrollSearch.setPreferredSize(new java.awt.Dimension(55, 146));

        jListSearch.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollSearch.setViewportView(jListSearch);

        javax.swing.GroupLayout crazyPanel1Layout = new javax.swing.GroupLayout(crazyPanel1);
        crazyPanel1.setLayout(crazyPanel1Layout);
        crazyPanel1Layout.setHorizontalGroup(
            crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(buttonReload)
                .addGap(389, 389, 389)
                .addComponent(buttonPrevous, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(buttonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(crazyPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPanelService, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        crazyPanel1Layout.setVerticalGroup(
            crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonReload)
                    .addComponent(buttonPrevous, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPanelService, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

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
                .addContainerGap(153, Short.MAX_VALUE)
                .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(buttonClean, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        crazyPanel3Layout.setVerticalGroup(
            crazyPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout crazyPanel5Layout = new javax.swing.GroupLayout(crazyPanel5);
        crazyPanel5.setLayout(crazyPanel5Layout);
        crazyPanel5Layout.setHorizontalGroup(
            crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        crazyPanel5Layout.setVerticalGroup(
            crazyPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonReloadActionPerformed

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        jListSearch.setVisible(false);
        jScrollSearch.setVisible(false);
        
        loadDataIntoJTable(textSearch.getText(), "Active", -1, 0, "", "");
        
        textSearch.setText("");
    }//GEN-LAST:event_buttonSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpload;
    private javax.swing.JButton buttonClean;
    private javax.swing.JButton buttonNext;
    private javax.swing.JButton buttonPrevous;
    private javax.swing.JButton buttonReload;
    private javax.swing.JButton buttonSearch;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private raven.crazypanel.CrazyPanel crazyPanel5;
    private javax.swing.JList<String> jListSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollSearch;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panelService;
    private javax.swing.JScrollPane scrollPanelService;
    private javax.swing.JTextField textSearch;
    // End of variables declaration//GEN-END:variables
}
