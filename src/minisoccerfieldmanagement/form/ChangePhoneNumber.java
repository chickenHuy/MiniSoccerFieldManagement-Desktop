package minisoccerfieldmanagement.form;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.io.File;
import minisoccerfieldmanagement.login.UserSession;
import minisoccerfieldmanagement.model.User;
import minisoccerfieldmanagement.service.IUserService;
import minisoccerfieldmanagement.service.UserServiceImpl;
import raven.alerts.MessageAlerts;
import raven.drawer.Drawer;
import raven.drawer.component.header.SimpleHeader;
import raven.drawer.component.header.SimpleHeaderData;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;
import raven.swing.AvatarIcon;

public class ChangePhoneNumber extends javax.swing.JFrame {

    IUserService userService;
    private int userId;
    private AccountInformation accountInformationForm;

    public ChangePhoneNumber() {
        initComponents();
        loadData();
    }

    public ChangePhoneNumber(AccountInformation accountInformationForm) {
        this();
        this.accountInformationForm = accountInformationForm;
        userService = new UserServiceImpl();
        loadData();
        setLocationRelativeTo(null);
    }
    
    public void setAccountInfo() {
        User user = UserSession.getInstance().getUser();
        String title = user.getName();
        String desc = user.getPhoneNumber();
        String path = "/minisoccerfieldmanagement/image/profile.jpg";
        if (user.getImage() != null) {
            File file = new File("src/minisoccerfieldmanagement/image/user/" + user.getImage());
            if (file.exists()) {
                path = "/minisoccerfieldmanagement/image/user/" + user.getImage();
            }
        }
        SimpleHeaderData newSimpleHeaderData = new SimpleHeaderData()
                .setIcon(new AvatarIcon(getClass().getResource(path), 60, 60, 999))
                .setTitle(title)
                .setDescription(desc);
        SimpleHeader header = (SimpleHeader) Drawer.getInstance().getDrawerPanel().getDrawerBuilder().getHeader();
        header.setSimpleHeaderData(newSimpleHeaderData);
    }

    private void loadData() {
        User user = UserSession.getInstance().getUser();
        userId = user.getId();
        tfPhoneNumber.setText(user.getPhoneNumber());
        btnSave.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/tick.svg", 0.35f));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        flatLabel1 = new com.formdev.flatlaf.extras.components.FlatLabel();
        tfPhoneNumber = new com.formdev.flatlaf.extras.components.FlatTextField();
        btnSave = new com.formdev.flatlaf.extras.components.FlatButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        flatLabel1.setText("Edit Phone Number");
        flatLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(flatLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(flatLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tfPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        User user = new User();
        String phoneNumber = tfPhoneNumber.getText().trim();
        if (phoneNumber.isBlank()) {
            MessageAlerts.getInstance().showMessage("Wrong format", "Please do not leave phone number blank", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                @Override
                public void action(PopupController pc, int i) {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                }
            });
            return;
        }
        if (!phoneNumber.matches("\\d{10}")) {
            MessageAlerts.getInstance().showMessage("Wrong format", "Phone number must contain exactly 10 digits, cannot contain special characters", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
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
        userNew.setPhoneNumber(phoneNumber);
        userNew.setUserName(userNew.getUserName());
        userNew.setPassword(userNew.getPassword());
        userNew.setRole(userNew.getRole());
        try {
            if (userService.checkPhoneNumberExistExceptCurrent(userNew.getId(), phoneNumber)) {
                MessageAlerts.getInstance().showMessage("Update failed", "This phone number already exists! Please use another phone number.", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, (PopupController pc, int i) -> {
                    if (i == MessageAlerts.CLOSED_OPTION) {

                    }
                });
                return;
            }
            MessageAlerts.getInstance().showMessage("Change phone number", "Are you sure you want to change your phone number?", MessageAlerts.MessageType.WARNING, MessageAlerts.YES_NO_OPTION, (PopupController pc, int i) -> {
                if (i == MessageAlerts.YES_OPTION) {
                    if (userService.update(userNew)) {
                        UserSession.getInstance().loginUser(userNew);
                        setAccountInfo();
                        MessageAlerts.getInstance().showMessage("Edit success", "Phone number has been saved", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                            @Override
                            public void action(PopupController pc, int i) {
                                if (i == MessageAlerts.CLOSED_OPTION) {

                                }
                            }
                        });                       
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

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AccountInformation accountInformationForm = new AccountInformation();
                new ChangePhoneNumber(accountInformationForm).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.formdev.flatlaf.extras.components.FlatButton btnSave;
    private com.formdev.flatlaf.extras.components.FlatLabel flatLabel1;
    private com.formdev.flatlaf.extras.components.FlatTextField tfPhoneNumber;
    // End of variables declaration//GEN-END:variables
}
