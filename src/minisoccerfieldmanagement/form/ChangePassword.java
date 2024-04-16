package minisoccerfieldmanagement.form;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import minisoccerfieldmanagement.login.UserSession;
import minisoccerfieldmanagement.model.User;
import minisoccerfieldmanagement.service.IUserService;
import minisoccerfieldmanagement.service.UserServiceImpl;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;

public class ChangePassword extends javax.swing.JFrame {

    IUserService userService;
    private int userId;
    private AccountInformation accountInformationForm;
    private boolean passwordVisible = false;

    public ChangePassword() {
        initComponents();
        loadData();
    }

    public ChangePassword(AccountInformation accountInformationForm) {
        this();
        this.accountInformationForm = accountInformationForm;
        userService = new UserServiceImpl();
        loadData();
        setLocationRelativeTo(null);
    }

    private void loadData() {
        User user = UserSession.getInstance().getUser();
        userId = user.getId();
        String password = user.getPassword();
        tfOldPass.setText(password);
        tfOldPass.setEchoChar('*');
        tfNewPass.setEchoChar('*');
        tfConfirmPass.setEchoChar('*');
        tfOldPass.setEnabled(false);
        lblEyeOld.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/eyeoff.svg", 0.35f));
        lblEyeNew.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/eyeoff.svg", 0.35f));
        lblEyeConfirm.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/eyeoff.svg", 0.35f));
        btnSave.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/tick.svg", 0.35f));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        flatLabel1 = new com.formdev.flatlaf.extras.components.FlatLabel();
        btnSave = new com.formdev.flatlaf.extras.components.FlatButton();
        lblOldPass = new com.formdev.flatlaf.extras.components.FlatLabel();
        lblOldPass1 = new com.formdev.flatlaf.extras.components.FlatLabel();
        lblOldPass2 = new com.formdev.flatlaf.extras.components.FlatLabel();
        tfOldPass = new com.formdev.flatlaf.extras.components.FlatPasswordField();
        tfNewPass = new com.formdev.flatlaf.extras.components.FlatPasswordField();
        tfConfirmPass = new com.formdev.flatlaf.extras.components.FlatPasswordField();
        lblEyeOld = new com.formdev.flatlaf.extras.components.FlatLabel();
        lblEyeNew = new com.formdev.flatlaf.extras.components.FlatLabel();
        lblEyeConfirm = new com.formdev.flatlaf.extras.components.FlatLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        flatLabel1.setText("Edit Password");
        flatLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblOldPass.setText("Old Pass");

        lblOldPass1.setText("New Pass");

        lblOldPass2.setText("Confirm Pass");

        lblEyeOld.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblEyeOldMousePressed(evt);
            }
        });

        lblEyeNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblEyeNewMousePressed(evt);
            }
        });

        lblEyeConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblEyeConfirmMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(flatLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOldPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOldPass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEyeNew, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEyeOld, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEyeConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(flatLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEyeOld, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblOldPass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblEyeNew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblOldPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblEyeConfirm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        User user = new User();
        String oldPass = new String(tfOldPass.getPassword());
        String newPass = new String(tfNewPass.getPassword());
        String confirmPass = new String(tfConfirmPass.getPassword());
        if (oldPass.isBlank() || newPass.isBlank() || confirmPass.isBlank()) {
            MessageAlerts.getInstance().showMessage("Wrong format", "Please do not leave fields blank", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });
            return;
        }
        if (!newPass.equals(confirmPass)) {
            MessageAlerts.getInstance().showMessage("Password mismatch", "New password and Confirm password must match", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });
            return;
        }
        if (newPass.equals(oldPass)) {
            MessageAlerts.getInstance().showMessage("Same password", "New password must be different from old password", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });
            return;
        }
        user.setId(userId);
        User userNew = userService.findById(userId);
        userNew.setId(userNew.getId());
        userNew.setName(userNew.getName());
        userNew.setGender(userNew.getGender());
        userNew.setDateOfBirth(userNew.getDateOfBirth());
        userNew.setImage(userNew.getImage());
        userNew.setPhoneNumber(userNew.getPhoneNumber());
        userNew.setUserName(userNew.getUserName());
        userNew.setPassword(newPass);
        userNew.setRole(userNew.getRole());
        try {
            MessageAlerts.getInstance().showMessage("Change password", "Are you sure you want to change your password?", MessageAlerts.MessageType.WARNING, MessageAlerts.YES_NO_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.YES_OPTION) {
                    if (userService.update(userNew)) {
                        MessageAlerts.getInstance().showMessage("Edit success", "New password has been saved", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                            @Override
                            public void action(PopupController pc, int i) {
                                if (i == MessageAlerts.CLOSED_OPTION) {

                                }
                            }
                        });
                        UserSession.getInstance().loginUser(userNew);
                        accountInformationForm.loadDataAccountInformation();
                        this.dispose();
                    } else {
                        MessageAlerts.getInstance().showMessage("Edit failed", "Please check and try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                            @Override
                            public void action(PopupController pc, int i) {
                                if (i == MessageAlerts.CLOSED_OPTION) {

                                }
                            }
                        });
                    }
                }
            });           
        } catch (Exception e) {
            MessageAlerts.getInstance().showMessage("Edit failed", "Please check and try again", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void lblEyeOldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEyeOldMousePressed
        passwordVisible = !passwordVisible;
        User user = UserSession.getInstance().getUser();
        if (passwordVisible) {
            lblEyeOld.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/eyeopen.svg", 0.35f));
            tfOldPass.setText(user.getPassword());
            tfOldPass.setEchoChar((char) 0);
        } else {
            lblEyeOld.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/eyeoff.svg", 0.35f));
            tfOldPass.setText(user.getPassword());
            tfOldPass.setEchoChar('*');
        }
    }//GEN-LAST:event_lblEyeOldMousePressed

    private void lblEyeNewMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEyeNewMousePressed
        passwordVisible = !passwordVisible;
        if (passwordVisible) {
            lblEyeNew.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/eyeopen.svg", 0.35f));
            tfNewPass.setEchoChar((char) 0);
        } else {
            lblEyeNew.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/eyeoff.svg", 0.35f));
            tfNewPass.setEchoChar('*');
        }
    }//GEN-LAST:event_lblEyeNewMousePressed

    private void lblEyeConfirmMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEyeConfirmMousePressed
        passwordVisible = !passwordVisible;
        if (passwordVisible) {
            lblEyeConfirm.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/eyeopen.svg", 0.35f));
            tfConfirmPass.setEchoChar((char) 0);
        } else {
            lblEyeConfirm.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/eyeoff.svg", 0.35f));
            tfConfirmPass.setEchoChar('*');
        }
    }//GEN-LAST:event_lblEyeConfirmMousePressed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AccountInformation accountInformationForm = new AccountInformation();
                new ChangePassword(accountInformationForm).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.formdev.flatlaf.extras.components.FlatButton btnSave;
    private com.formdev.flatlaf.extras.components.FlatLabel flatLabel1;
    private com.formdev.flatlaf.extras.components.FlatLabel lblEyeConfirm;
    private com.formdev.flatlaf.extras.components.FlatLabel lblEyeNew;
    private com.formdev.flatlaf.extras.components.FlatLabel lblEyeOld;
    private com.formdev.flatlaf.extras.components.FlatLabel lblOldPass;
    private com.formdev.flatlaf.extras.components.FlatLabel lblOldPass1;
    private com.formdev.flatlaf.extras.components.FlatLabel lblOldPass2;
    private com.formdev.flatlaf.extras.components.FlatPasswordField tfConfirmPass;
    private com.formdev.flatlaf.extras.components.FlatPasswordField tfNewPass;
    private com.formdev.flatlaf.extras.components.FlatPasswordField tfOldPass;
    // End of variables declaration//GEN-END:variables
}
