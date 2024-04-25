package minisoccerfieldmanagement.util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import minisoccerfieldmanagement.model.Service;

public class ServiceItem extends javax.swing.JPanel {

    private Service service;

    public Service getData() {
        return service;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    private boolean selected;

    public ServiceItem() {
        initComponents();
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(236, 236, 236));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        if (selected) {
            g2.setColor(new Color(94, 156, 255));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        }
        g2.dispose();
        super.paint(grphcs);
    }

    public void setData(Service service) {
        this.service = service;
        if (service.getImage() != null) {
            pictureBox1.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/service/" + service.getImage()));
        } else {
            pictureBox1.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/service/service_image_default.png"));
        }
        pictureBox1.repaint();

        lbItemName.setText(service.getName());
        lbBrand.setText(service.getUnit());
        lbPrice.setText(Utils.formatVND(service.getPrice()));

        if (service.getQuantity() == 0) {
            buttonSoutOut.setVisible(true);
        } else {
            buttonSoutOut.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbItemName = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        lbBrand = new javax.swing.JLabel();
        pictureBox1 = new minisoccerfieldmanagement.util.PictureBox();
        buttonSoutOut = new javax.swing.JButton();
        lbBrand1 = new javax.swing.JLabel();

        lbItemName.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbItemName.setForeground(new java.awt.Color(51, 51, 51));
        lbItemName.setText("Item Name");

        lbPrice.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbPrice.setForeground(new java.awt.Color(51, 51, 51));
        lbPrice.setText("$0.00");

        lbBrand.setFont(new java.awt.Font("sansserif", 1, 11)); // NOI18N
        lbBrand.setForeground(new java.awt.Color(76, 76, 76));
        lbBrand.setText("Brand");

        buttonSoutOut.setBackground(new java.awt.Color(51, 51, 51));
        buttonSoutOut.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonSoutOut.setForeground(new java.awt.Color(255, 255, 255));
        buttonSoutOut.setText("Sold out");
        pictureBox1.add(buttonSoutOut);
        buttonSoutOut.setBounds(40, 50, 80, 23);

        lbBrand1.setFont(new java.awt.Font("sansserif", 1, 11)); // NOI18N
        lbBrand1.setForeground(new java.awt.Color(76, 76, 76));
        lbBrand1.setText("Unit:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbBrand1)
                        .addGap(6, 6, 6)
                        .addComponent(lbBrand)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbPrice)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbItemName)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbBrand1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbPrice)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSoutOut;
    private javax.swing.JLabel lbBrand;
    private javax.swing.JLabel lbBrand1;
    private javax.swing.JLabel lbItemName;
    private javax.swing.JLabel lbPrice;
    private minisoccerfieldmanagement.util.PictureBox pictureBox1;
    // End of variables declaration//GEN-END:variables
}
