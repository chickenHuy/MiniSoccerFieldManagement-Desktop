package minisoccerfieldmanagement.form;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import minisoccerfieldmanagement.login.UserSession;
import minisoccerfieldmanagement.model.User;
import minisoccerfieldmanagement.service.IUserService;
import minisoccerfieldmanagement.service.UserServiceImpl;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;
import minisoccerfieldmanagement.datetime.component.date.DatePicker;

public class ChangeDateOfBirth extends javax.swing.JFrame {

    IUserService userService;
    private int userId;
    private AccountInformation accountInformationForm;
    DatePicker dateChooser;

    public ChangeDateOfBirth() {
        initComponents();
        loadData();
        
    }

    public ChangeDateOfBirth(AccountInformation accountInformationForm) {
        this();
        this.accountInformationForm = accountInformationForm;
        userService = new UserServiceImpl();
        loadData();
        setDateChooser();
        setLocationRelativeTo(null);
    }

    private void loadData() {
        User user = UserSession.getInstance().getUser();
        userId = user.getId();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDateOfBirth = dateFormat.format(user.getDateOfBirth());
        tfDateOfBirth.setText(strDateOfBirth);
        btnSave.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/tick.svg", 0.35f));
    }
    
    private void setDateChooser() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateChooser = new DatePicker();
        dateChooser.now();
        dateChooser.setEditor(tfDateOfBirth);
        tfDateOfBirth.setText(dateFormat.format(new Timestamp(System.currentTimeMillis())));
        tfDateOfBirth.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        flatLabel1 = new com.formdev.flatlaf.extras.components.FlatLabel();
        btnSave = new com.formdev.flatlaf.extras.components.FlatButton();
        tfDateOfBirth = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        flatLabel1.setText("Edit Date Of Birth");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tfDateOfBirth)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(flatLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(tfDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        User user = new User();
        String strDateOfBirth = tfDateOfBirth.getText().trim();
        user.setId(userId);
        User userNew = userService.findById(userId);
        userNew.setId(userNew.getId());
        userNew.setName(userNew.getName());
        userNew.setGender(userNew.getGender());
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date parsedDate = dateFormat.parse(strDateOfBirth);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            userNew.setDateOfBirth(timestamp);
        } catch (ParseException e) {
            return;
        }
        userNew.setImage(userNew.getImage());
        userNew.setPhoneNumber(userNew.getPhoneNumber());
        userNew.setUserName(userNew.getUserName());
        userNew.setPassword(userNew.getPassword());
        userNew.setRole(userNew.getRole());
        try {
            if (userService.update(userNew)) {
                MessageAlerts.getInstance().showMessage("Edit success", "Date of birth has been saved", MessageAlerts.MessageType.SUCCESS, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
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
                new ChangeDateOfBirth(accountInformationForm).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.formdev.flatlaf.extras.components.FlatButton btnSave;
    private com.formdev.flatlaf.extras.components.FlatLabel flatLabel1;
    private javax.swing.JFormattedTextField tfDateOfBirth;
    // End of variables declaration//GEN-END:variables
}
