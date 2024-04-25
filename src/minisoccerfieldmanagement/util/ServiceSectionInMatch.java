package minisoccerfieldmanagement.util;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeSupport;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import minisoccerfieldmanagement.model.Field;
import minisoccerfieldmanagement.model.Service;

public class ServiceSectionInMatch extends javax.swing.JPanel {

    private int selectedServiceId = -1;
    private ISendQuantityOrder listener;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setSelectedServiceId(int selectedServiceId) {
        int oldSelectedServiceId = this.selectedServiceId;
        this.selectedServiceId = selectedServiceId;
        propertyChangeSupport.firePropertyChange("selectedServiceId", oldSelectedServiceId, selectedServiceId);
    }

    private EventItem event;

    public int getSelectedServiceId() {
        return selectedServiceId;
    }

    public void setEvent(EventItem event) {
        this.event = event;
    }

    public void setQuantityListener(ISendQuantityOrder listener) {
        this.listener = listener;
    }

    private void sendDataBack(Service service, int quantityOrder) {
        if (listener != null) {
            listener.sendQuantityOrder(service, quantityOrder);
        }
    }

    public ServiceSectionInMatch() {
        initComponents();
        panelService.setLayout(new GridLayout(0, 2, 10, 10));
        panelService.setBorder(new EmptyBorder(10, 15, 10, 15));
        scrollPane.getVerticalScrollBar().setUnitIncrement(17);
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
                    sendDataBack(service, quantityOrder);
                });
                getQuantityOfService.setVisible(true);
            }
        });
        for (Service i : data) {
            this.addItem(i);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        panelService = new minisoccerfieldmanagement.util.PanelField();

        scrollPane.setBackground(new java.awt.Color(242, 242, 242));
        scrollPane.setInheritsPopupMenu(true);
        scrollPane.setViewportView(panelService);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private minisoccerfieldmanagement.util.PanelField panelService;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
