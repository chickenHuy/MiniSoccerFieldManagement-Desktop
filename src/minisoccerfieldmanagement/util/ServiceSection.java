package minisoccerfieldmanagement.util;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeSupport;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import minisoccerfieldmanagement.model.Field;
import minisoccerfieldmanagement.model.Service;

public class ServiceSection extends javax.swing.JPanel {

    private int selectedServiceId = -1;
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

    public ServiceSection() {
        initComponents();
        panelService.setLayout(new GridLayout(0, 2, 10, 10));
        panelService.setBorder(new EmptyBorder(10, 10, 10, 10));
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
                    setSelected(item);
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

    public void setSelected(Component item) {
        for (Component com : panelService.getComponents()) {
            FieldItem i = (FieldItem) com;
            if (i.isSelected()) {
                i.setSelected(false);
            }
        }
        if (item != null) {
            ((FieldItem) item).setSelected(true);
        }
    }

    public void addData(List<Service> data) {
        this.setEvent(new EventItem() {
            @Override
            public void fieldClick(Component com, Field item) {
                setSelectedServiceId(item.getId());
                setSelected(com);
            }

            @Override
            public void serviceItemClick(Component com, Service service) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from
                // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private minisoccerfieldmanagement.util.PanelField panelService;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
