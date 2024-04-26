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

public class ChangeName extends javax.swing.JFrame {

    IUserService userService;
    private int userId;
    private AccountInformation accountInformationForm;

    public ChangeName() {
        initComponents();
        loadData();
    }

    public ChangeName(AccountInformation accountInformationForm) {
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
        tfName.setText(user.getName());       
        btnSave.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/tick.svg", 0.35f));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        flatLabel1 = new com.formdev.flatlaf.extras.components.FlatLabel();
        tfName = new com.formdev.flatlaf.extras.components.FlatTextField();
        btnSave = new com.formdev.flatlaf.extras.components.FlatButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        flatLabel1.setText("Edit Name");
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
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        User user = new User();
        String name = tfName.getText().trim();
        if (name.isBlank()) {
            MessageAlerts.getInstance().showMessage("Wrong format", "Please do not leave name blank", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
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
        userNew.setName(name);
        userNew.setGender(userNew.getGender());
        userNew.setDateOfBirth(userNew.getDateOfBirth());
        userNew.setImage(userNew.getImage());
        userNew.setPhoneNumber(userNew.getPhoneNumber());
        userNew.setUserName(userNew.getUserName());
        userNew.setPassword(userNew.getPassword());
        userNew.setRole(userNew.getRole());
        try {
            if (userService.update(userNew)) {
                UserSession.getInstance().loginUser(userNew);
                setAccountInfo();
                MessageAlerts.getInstance().showMessage("Edit success", "Name has been saved", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
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
                new ChangeName(accountInformationForm).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.formdev.flatlaf.extras.components.FlatButton btnSave;
    private com.formdev.flatlaf.extras.components.FlatLabel flatLabel1;
    private com.formdev.flatlaf.extras.components.FlatTextField tfName;
    // End of variables declaration//GEN-END:variables
}
