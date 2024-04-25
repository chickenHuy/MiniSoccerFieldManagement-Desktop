package minisoccerfieldmanagement.util;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import minisoccerfieldmanagement.model.Service;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;

public final class GetQuantityOfService extends javax.swing.JFrame {

    private Service service = null;
    private int quantity = 1;
    private boolean isAdjustWithButton = false;
    private ISendQuantityOrder listener;

    public GetQuantityOfService(Service service) {
        initComponents();

        this.service = service;
        setWidgit();
    }

    private void setWidgit() {
        buttonIncrease.setSize(30, 22);
        buttonDecrease.setSize(30, 22);
        buttonIncrease.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/increase.svg", 0.35f));
        buttonDecrease.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/decrease.svg", 0.35f));

        editTextServiceQuantity.setHorizontalAlignment(JTextField.CENTER);

        if (service != null) {
            if (service.getImage() != null) {
                pictureBoxServiceImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/service/" + service.getImage()));
            } else {
                pictureBoxServiceImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/service/service_image_default.png"));
            }
            pictureBoxServiceImage.repaint();

            labelServiceName.setText(service.getName());
            labelServiceInStock.setText(String.valueOf(service.getQuantity()));
            labelServiceUnit.setText(service.getUnit());
            labelServicePrice.setText(Utils.formatVND(service.getPrice()));
        }

        editTextServiceQuantity.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!isAdjustWithButton) {
                    checkInput(editTextServiceQuantity);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!isAdjustWithButton) {
                    checkInput(editTextServiceQuantity);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!isAdjustWithButton) {
                    checkInput(editTextServiceQuantity);
                }
            }
        });
    }

    public void setQuantityListener(ISendQuantityOrder listener) {
        this.listener = listener;
    }

    private void sendDataBack(Service service, int quantityOrder) {
        if (listener != null) {
            listener.sendQuantityOrder(service, quantityOrder);
        }
    }

    private void checkInput(JTextField textField) {
        if (textField != null && !textField.getText().trim().equals("")) {
            if (!isNumber(textField.getText())) {
                this.dispose();
            } else {
                try {
                    int quantity = Integer.parseInt(editTextServiceQuantity.getText());
                    if (quantity > service.getQuantity()) {
                        MessageAlerts.getInstance().showMessage("Insufficient", "The quantity of products in stock is " + service.getQuantity() + "!!!", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                            @Override
                            public void action(PopupController pc, int i) {
                                if (i == MessageAlerts.CLOSED_OPTION) {

                                }
                            }
                        });
                    } else {
                        this.quantity = quantity;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    MessageAlerts.getInstance().showMessage("Over limit", "Quantity limit is 9999!!!", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                        @Override
                        public void action(PopupController pc, int i) {
                            if (i == MessageAlerts.CLOSED_OPTION) {

                            }
                        }
                    });
                }
            }
        }
    }

    private boolean isNumber(String text) {
        if (text.matches(".*[^0-9].*")) {
            MessageAlerts.getInstance().showMessage("Not number", "Quantity contain only numeric characters, cannot contain another characters", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });
            return false;
        }
        if (text.length() >= 5) {
            MessageAlerts.getInstance().showMessage("Over limit", "Quantity limit is 9999!!!", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pictureBoxServiceImage = new minisoccerfieldmanagement.util.PictureBox();
        labelServiceName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelServicePrice = new javax.swing.JLabel();
        editTextServiceQuantity = new javax.swing.JTextField();
        buttonIncrease = new javax.swing.JButton();
        buttonDecrease = new javax.swing.JButton();
        labelServiceUnit = new javax.swing.JLabel();
        labelServiceInStock = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        buttonAdd = new javax.swing.JButton();
        buttonClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelServiceName.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelServiceName.setText("Name");

        jLabel2.setText("Unit: ");

        labelServicePrice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelServicePrice.setText(" 100,000 VND");

        editTextServiceQuantity.setText("1");

        buttonIncrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIncreaseActionPerformed(evt);
            }
        });

        buttonDecrease.setName(""); // NOI18N
        buttonDecrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDecreaseActionPerformed(evt);
            }
        });

        labelServiceUnit.setText("Chai");

        labelServiceInStock.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelServiceInStock.setText("10");

        jLabel4.setText("In stock: ");

        buttonAdd.setBackground(new java.awt.Color(51, 51, 255));
        buttonAdd.setText("Add");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        buttonClose.setText("Close");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureBoxServiceImage, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelServiceName)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelServiceUnit)
                                .addGap(0, 167, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelServiceInStock, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(labelServicePrice, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(buttonDecrease, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(editTextServiceQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonIncrease, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(buttonClose)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(buttonAdd)))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pictureBoxServiceImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelServiceName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(labelServiceUnit)
                            .addComponent(jLabel4)
                            .addComponent(labelServiceInStock))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelServicePrice)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(editTextServiceQuantity, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buttonIncrease, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonDecrease, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonAdd)
                            .addComponent(buttonClose))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void buttonIncreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIncreaseActionPerformed
        isAdjustWithButton = true;
        if (editTextServiceQuantity.getText().trim().equals("")) {
            this.quantity = 1;
            editTextServiceQuantity.setText("1");
        } else {
            if (isNumber(editTextServiceQuantity.getText().trim())) {
                try {
                    int quantity = Integer.parseInt(editTextServiceQuantity.getText());
                    if (quantity >= service.getQuantity()) {
                        MessageAlerts.getInstance().showMessage("Insufficient", "The quantity of products in stock is " + service.getQuantity() + "!!!", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                            @Override
                            public void action(PopupController pc, int i) {
                                if (i == MessageAlerts.CLOSED_OPTION) {

                                }
                            }
                        });
                    } else {
                        this.quantity += 1;
                        editTextServiceQuantity.setText(String.valueOf(this.quantity));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    MessageAlerts.getInstance().showMessage("Over limit", "Quantity limit is 9999!!!", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                        @Override
                        public void action(PopupController pc, int i) {
                            if (i == MessageAlerts.CLOSED_OPTION) {

                            }
                        }
                    });
                }
            }
        }
        isAdjustWithButton = false;
    }//GEN-LAST:event_buttonIncreaseActionPerformed

    private void buttonDecreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDecreaseActionPerformed
        isAdjustWithButton = true;
        if (editTextServiceQuantity.getText().trim().equals("")) {
            this.quantity = 1;
            editTextServiceQuantity.setText("1");
        } else {
            if (isNumber(editTextServiceQuantity.getText().trim())) {
                try {
                    int quantity = Integer.parseInt(editTextServiceQuantity.getText());
                    if (quantity > 1) {
                        this.quantity -= 1;
                        editTextServiceQuantity.setText(String.valueOf(this.quantity));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    MessageAlerts.getInstance().showMessage("Over limit", "Quantity limit is 9999!!!", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                        @Override
                        public void action(PopupController pc, int i) {
                            if (i == MessageAlerts.CLOSED_OPTION) {

                            }
                        }
                    });
                }
            }
        }
        isAdjustWithButton = false;
    }//GEN-LAST:event_buttonDecreaseActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        if (service.getQuantity() >= quantity) {
            sendDataBack(service, quantity);
            this.dispose();
        } else {
            MessageAlerts.getInstance().showMessage("Insufficient", "The quantity of products in stock is " + service.getQuantity() + "!!!", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });
            this.dispose();
        }
    }//GEN-LAST:event_buttonAddActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GetQuantityOfService.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GetQuantityOfService.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GetQuantityOfService.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GetQuantityOfService.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GetQuantityOfService(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonDecrease;
    private javax.swing.JButton buttonIncrease;
    private javax.swing.JTextField editTextServiceQuantity;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel labelServiceInStock;
    private javax.swing.JLabel labelServiceName;
    private javax.swing.JLabel labelServicePrice;
    private javax.swing.JLabel labelServiceUnit;
    private minisoccerfieldmanagement.util.PictureBox pictureBoxServiceImage;
    // End of variables declaration//GEN-END:variables
}
