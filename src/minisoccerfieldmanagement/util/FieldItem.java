package minisoccerfieldmanagement.util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import javax.swing.ImageIcon;
import minisoccerfieldmanagement.model.Field;

public class FieldItem extends javax.swing.JPanel {

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    public FieldItem() {
        initComponents();
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    private Field data;

    public Field getData() {
        return data;
    }

    public void setData(Field data) {
        this.data = data;
        if (data.getImage() != null && !data.getImage().isBlank()) {
            File file = new File("src/minisoccerfieldmanagement/image/field/" + data.getImage());
            if (file.exists()) {
                pic.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/field/" + data.getImage()));
                pic.repaint();
            }
        }
        lblFieldName.setText(data.getName());
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (selected) {
            g2.setColor(new Color(0,160,30));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        }
        g2.dispose();
        super.paint(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFieldName = new javax.swing.JLabel();
        pic = new minisoccerfieldmanagement.util.PictureBox();

        setMaximumSize(new java.awt.Dimension(140, 101));
        setMinimumSize(new java.awt.Dimension(140, 101));

        lblFieldName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFieldName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFieldName.setText("Field Name");
        lblFieldName.setToolTipText("");

        pic.setImage(new javax.swing.ImageIcon(getClass().getResource("/minisoccerfieldmanagement/image/pitch.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFieldName, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFieldName)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblFieldName;
    private minisoccerfieldmanagement.util.PictureBox pic;
    // End of variables declaration//GEN-END:variables
}
