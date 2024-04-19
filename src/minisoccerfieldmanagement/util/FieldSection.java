package minisoccerfieldmanagement.util;

import minisoccerfieldmanagement.util.FieldItem;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeSupport;
import java.util.List;
import javax.swing.SwingUtilities;
import minisoccerfieldmanagement.model.Field;
import minisoccerfieldmanagement.model.Service;
import minisoccerfieldmanagement.util.EventItem;
import minisoccerfieldmanagement.util.ScrollBar;

public class FieldSection extends javax.swing.JPanel {

    private int selectedFieldId = -1;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setSelectedFieldId(int selectedFieldId) {
        int oldSelectedFieldId = this.selectedFieldId;
        this.selectedFieldId = selectedFieldId;  
        propertyChangeSupport.firePropertyChange("selectedFieldId", oldSelectedFieldId, selectedFieldId);
    }
    private EventItem event;

    public int getSelectedFieldId() {
        return selectedFieldId;
    }

    
    public void setEvent(EventItem event) {
        this.event = event;
    }
    
    public FieldSection() {
        initComponents();
        scrollPane.setHorizontalScrollBar(new ScrollBar());
    }

    public void addItem(Field data) {
        FieldItem item = new FieldItem();
        item.setData(data);
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    setSelectedFieldId(data.getId());
                    setSelected(item);
                    if (event != null)
                        event.fieldClick(item, data);
                }
            }
        });
        
        panelField.add(item);
        panelField.revalidate();
        panelField.repaint();
    }
    
    public void setSelected(Component item) {
        for (Component com:panelField.getComponents()) {
            FieldItem i = (FieldItem)com;
            if (i.isSelected()) {
                i.setSelected(false);
            }
        }
        if (item != null)
            ((FieldItem)item).setSelected(true);
    }
    
    public void addData(List<Field> data) {
        this.setEvent(new EventItem() {
            @Override
            public void fieldClick(Component com, Field item) {
                setSelectedFieldId(item.getId());
                setSelected(com);
            }

            @Override
            public void serviceItemClick(Component com, Service service) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
        for(Field i : data) {
            this.addItem(i);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        panelField = new minisoccerfieldmanagement.util.PanelField();

        scrollPane.setBackground(new java.awt.Color(242, 242, 242));
        scrollPane.setInheritsPopupMenu(true);
        scrollPane.setViewportView(panelField);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private minisoccerfieldmanagement.util.PanelField panelField;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
