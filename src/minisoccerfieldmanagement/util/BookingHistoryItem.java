package minisoccerfieldmanagement.util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import minisoccerfieldmanagement.model.Booking;
import minisoccerfieldmanagement.model.Field;
import minisoccerfieldmanagement.service.FieldServiceImpl;
import minisoccerfieldmanagement.service.IFieldService;

public class BookingHistoryItem extends javax.swing.JPanel {

    private boolean selected;
    IFieldService fieldService;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    public BookingHistoryItem() {
        initComponents();
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        fieldService = new FieldServiceImpl();
    }

    private Booking data;

    public Booking getData() {
        return data;
    }

    public void setData(Booking data) {
        this.data = data;
        int fieldId = data.getFieldId();
        Field field = fieldService.findById(fieldId);
        if (field.getImage() != null && !field.getImage().isBlank()) {
            File file = new File("src/minisoccerfieldmanagement/image/field/" + field.getImage());
            if (file.exists()) {
                pic.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/field/" + field.getImage()));
                pic.repaint();
            }
        }
        SimpleDateFormat dateFormatted = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormatted.format(new Date(data.getTimeStart().getTime()));
        lblDate.setText(date);
        String name = field.getName();
        SimpleDateFormat timeFormatted = new SimpleDateFormat("HH:mm");
        String startTime = timeFormatted.format(new Date(data.getTimeStart().getTime()));
        String endTime = timeFormatted.format(new Date(data.getTimeEnd().getTime()));
        String nameTime = name + " - " + startTime.replace(":", "h") + " : " + endTime.replace(":", "h");
        lblNameTime.setText(nameTime);
        DecimalFormat df = new DecimalFormat("#,##0.##");
        lblPrice.setText(df.format(data.getPrice()) + " VND");
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (selected) {
            g2.setColor(new Color(0, 160, 30));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        }
        g2.dispose();
        super.paint(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDate = new javax.swing.JLabel();
        pic = new minisoccerfieldmanagement.util.PictureBox();
        lblNameTime = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(140, 101));
        setMinimumSize(new java.awt.Dimension(140, 101));

        lblDate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDate.setText("Date");
        lblDate.setToolTipText("");

        pic.setImage(new javax.swing.ImageIcon(getClass().getResource("/minisoccerfieldmanagement/image/pitch.png"))); // NOI18N

        lblNameTime.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNameTime.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNameTime.setText("Name + Time");
        lblNameTime.setToolTipText("");

        lblPrice.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPrice.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPrice.setText("Price");
        lblPrice.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNameTime)
                    .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(lblDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNameTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPrice)
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblNameTime;
    private javax.swing.JLabel lblPrice;
    private minisoccerfieldmanagement.util.PictureBox pic;
    // End of variables declaration//GEN-END:variables
}
