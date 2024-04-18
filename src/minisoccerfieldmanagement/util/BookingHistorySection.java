package minisoccerfieldmanagement.util;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeSupport;
import java.util.List;
import javax.swing.SwingUtilities;
import minisoccerfieldmanagement.model.Booking;

public class BookingHistorySection extends javax.swing.JPanel {

    private int selectedFieldId = -1;

    public void setSelectedBookingId(int selectedFieldId) {
        int oldSelectedFieldId = this.selectedFieldId;
        this.selectedFieldId = selectedFieldId;
        PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
        propertyChangeSupport.firePropertyChange("selectedFieldId", oldSelectedFieldId, selectedFieldId);
    }

    private BookingItem event;

    public void setEvent(BookingItem event) {
        this.event = event;
    }

    public int getSelectedBookingId() {
        return selectedFieldId;
    }

    public BookingHistorySection() {
        initComponents();
        panelBookingHistory.setLayout(new GridBagLayout());
    }

    public void addItem(Booking data) {
        BookingHistoryItem item = new BookingHistoryItem();
        item.setData(data);
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    setSelectedBookingId(data.getId());
                    setSelected(item);
                    if (event != null) {
                        event.bookingClick(item, data);
                    }
                }
            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelBookingHistory.add(item, gbc);
        panelBookingHistory.revalidate();
        panelBookingHistory.repaint();
    }

    public void setSelected(Component item) {
        for (Component com : panelBookingHistory.getComponents()) {
            BookingHistoryItem i = (BookingHistoryItem) com;
            if (i.isSelected()) {
                i.setSelected(false);
            }
        }
        if (item != null) {
            ((BookingHistoryItem) item).setSelected(true);
        }
    }

    public void clearData() {
        panelBookingHistory.removeAll();
        panelBookingHistory.revalidate();
        panelBookingHistory.repaint();
    }

    public void addData(List<Booking> data) {
        this.setEvent(new BookingItem() {
            @Override
            public void bookingClick(Component com, Booking item) {
                setSelectedBookingId(item.getId());
                setSelected(com);
            }
        });
        for (Booking i : data) {
            this.addItem(i);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        panelBookingHistory = new minisoccerfieldmanagement.util.PanelField();

        scrollPane.setBackground(new java.awt.Color(242, 242, 242));
        scrollPane.setInheritsPopupMenu(true);
        scrollPane.setViewportView(panelBookingHistory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private minisoccerfieldmanagement.util.PanelField panelBookingHistory;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
