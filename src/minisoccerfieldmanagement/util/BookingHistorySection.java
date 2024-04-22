package minisoccerfieldmanagement.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeSupport;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import minisoccerfieldmanagement.model.Booking;

public class BookingHistorySection extends javax.swing.JPanel {

    private int selectedBookingId = -1;

    public void setSelectedBookingId(int selectedBookingId) {
        int oldSelectedBookingId = this.selectedBookingId;
        this.selectedBookingId = selectedBookingId;
        PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
        propertyChangeSupport.firePropertyChange("selectedFieldId", oldSelectedBookingId, selectedBookingId);
    }

    private BookingItem event;

    public void setEvent(BookingItem event) {
        this.event = event;
    }

    public int getSelectedBookingId() {
        return selectedBookingId;
    }

    public BookingHistorySection() {
        initComponents();
        panelBookingHistory.setLayout(new GridBagLayout());
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
    }

    public void addItem(Booking data) {
        BookingHistoryItem item = new BookingHistoryItem();
        item.setData(data);
        item.setMaximumSize(new Dimension(230, Integer.MAX_VALUE));
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
        gbc.insets = new Insets(5, 10, 5, 10);
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

    public void showNoBookingMessage() {
        JLabel noBookingLabel = new JLabel("No booking history", SwingConstants.CENTER);
        noBookingLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        noBookingLabel.setForeground(new Color(255, 0, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panelBookingHistory.add(noBookingLabel, gbc);
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
