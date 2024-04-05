package minisoccerfieldmanagement.form;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.SwingUtilities;
import minisoccerfieldmanagement.model.Field;
import minisoccerfieldmanagement.util.EventItem;
import minisoccerfieldmanagement.util.ScrollBar;

public class FieldSection extends javax.swing.JPanel {

    private EventItem event;

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
        ((FieldItem)item).setSelected(true);
    }
    
    public void addData(List<Field> data) {
        this.setEvent(new EventItem() {
            @Override
            public void fieldClick(Component com, Field item) {
                System.out.println(item.getId());
                setSelected(com);
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
        panelField = new minisoccerfieldmanagement.form.PanelField();

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
    private minisoccerfieldmanagement.form.PanelField panelField;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
