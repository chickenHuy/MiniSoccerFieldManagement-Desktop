package minisoccerfieldmanagement.form;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import minisoccerfieldmanagement.login.UserSession;
import minisoccerfieldmanagement.model.User;
import minisoccerfieldmanagement.service.IUserService;
import minisoccerfieldmanagement.service.UserServiceImpl;
import minisoccerfieldmanagement.tabbed.TabbedForm;
import raven.alerts.MessageAlerts;
import raven.drawer.Drawer;
import raven.drawer.component.header.SimpleHeader;
import raven.drawer.component.header.SimpleHeaderData;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;
import raven.swing.AvatarIcon;

public final class AccountInformation extends TabbedForm {

    IUserService userService;
    private int userId;
    private boolean passwordVisible = false;

    public AccountInformation() {
        initComponents();
        applyStyle();
        userService = new UserServiceImpl();
        loadDataAccountInformation();
        setAccountInfo();
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

    public void loadDataAccountInformation() {
        User user = UserSession.getInstance().getUser();
        userId = user.getId();
        User userNew = userService.findById(userId);
        tfName.setText(userNew.getName());
        tfGender.setText(userNew.getGender());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDateOfBirth = dateFormat.format(userNew.getDateOfBirth());
        tfDateOfBirth.setText(strDateOfBirth);
        tfPhoneNumber.setText(userNew.getPhoneNumber());
        tfRole.setText(userNew.getRole());
        tfUsername.setText(userNew.getUserName());
        String password = userNew.getPassword();
        tfPassword.setText("*".repeat(password.length()));
        tfPassword.setEchoChar('*');
        if (userNew.getImage() == null || userNew.getImage().isEmpty()) {
            ptbAccountImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/profile.jpg"));
            ptbAccountImage.repaint();
        } else {
            File file = new File("src/minisoccerfieldmanagement/image/user/" + userNew.getImage());
            if (!file.exists()) {
                ptbAccountImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/profile.jpg"));
                ptbAccountImage.repaint();
            } else {
                ptbAccountImage.setImage(new ImageIcon("src/minisoccerfieldmanagement/image/user/" + userNew.getImage()));
                ptbAccountImage.repaint();
            }
        }
        tfRole.setEnabled(false);
        tfRole.setDisabledTextColor(new Color(196, 204, 90));
        tfName.setEnabled(false);
        tfName.setDisabledTextColor(new Color(196, 204, 90));
        tfGender.setEnabled(false);
        tfGender.setDisabledTextColor(new Color(196, 204, 90));
        tfDateOfBirth.setEnabled(false);
        tfDateOfBirth.setDisabledTextColor(new Color(196, 204, 90));
        tfPhoneNumber.setEnabled(false);
        tfPhoneNumber.setDisabledTextColor(new Color(196, 204, 90));
        tfUsername.setEnabled(false);
        tfUsername.setDisabledTextColor(new Color(196, 204, 90));
        tfPassword.setEnabled(false);
        tfPassword.setDisabledTextColor(new Color(196, 204, 90));
        lblEye.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/eyeoff.svg", 0.35f));
    }

    private void applyStyle() {
        btnEditImage.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/edit.svg", 0.35f));
        btnEditUsername.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/edit.svg", 0.35f));
        btnEditName.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/edit.svg", 0.35f));
        btnEditGender.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/edit.svg", 0.35f));
        btnEditDateOfBirth.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/edit.svg", 0.35f));
        btnEditPhoneNumber.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/edit.svg", 0.35f));
        btnEditPassword.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/edit.svg", 0.35f));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPersonalInformation = new raven.crazypanel.CrazyPanel();
        lblPersonalInformation = new com.formdev.flatlaf.extras.components.FlatLabel();
        lblName = new com.formdev.flatlaf.extras.components.FlatLabel();
        tfName = new com.formdev.flatlaf.extras.components.FlatTextField();
        lblGender = new com.formdev.flatlaf.extras.components.FlatLabel();
        lblDateOfBirth = new com.formdev.flatlaf.extras.components.FlatLabel();
        lblPhoneNumber = new com.formdev.flatlaf.extras.components.FlatLabel();
        tfPhoneNumber = new com.formdev.flatlaf.extras.components.FlatTextField();
        lblRole = new com.formdev.flatlaf.extras.components.FlatLabel();
        tfRole = new com.formdev.flatlaf.extras.components.FlatTextField();
        btnEditName = new com.formdev.flatlaf.extras.components.FlatButton();
        btnEditDateOfBirth = new com.formdev.flatlaf.extras.components.FlatButton();
        btnEditPhoneNumber = new com.formdev.flatlaf.extras.components.FlatButton();
        btnEditGender = new com.formdev.flatlaf.extras.components.FlatButton();
        tfGender = new com.formdev.flatlaf.extras.components.FlatTextField();
        tfDateOfBirth = new com.formdev.flatlaf.extras.components.FlatTextField();
        panelLoginInformation = new raven.crazypanel.CrazyPanel();
        lblLoginInformation = new com.formdev.flatlaf.extras.components.FlatLabel();
        lblUsername = new com.formdev.flatlaf.extras.components.FlatLabel();
        tfUsername = new com.formdev.flatlaf.extras.components.FlatTextField();
        lblPassword = new com.formdev.flatlaf.extras.components.FlatLabel();
        tfPassword = new com.formdev.flatlaf.extras.components.FlatPasswordField();
        lblEye = new com.formdev.flatlaf.extras.components.FlatLabel();
        btnEditUsername = new com.formdev.flatlaf.extras.components.FlatButton();
        btnEditPassword = new com.formdev.flatlaf.extras.components.FlatButton();
        panelAccountImage = new raven.crazypanel.CrazyPanel();
        ptbAccountImage = new minisoccerfieldmanagement.util.PictureBox();
        btnEditImage = new com.formdev.flatlaf.extras.components.FlatButton();
        lblAccountInformation = new com.formdev.flatlaf.extras.components.FlatLabel();

        setMaximumSize(new java.awt.Dimension(1188, 696));
        setPreferredSize(new java.awt.Dimension(1188, 696));

        panelPersonalInformation.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        lblPersonalInformation.setText("Personal Information");
        lblPersonalInformation.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        lblName.setText("Name");

        lblGender.setText("Gender");

        lblDateOfBirth.setText("Date of Birth");

        lblPhoneNumber.setText("PhoneNumber");

        lblRole.setText("Role");

        btnEditName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditNameActionPerformed(evt);
            }
        });

        btnEditDateOfBirth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditDateOfBirthActionPerformed(evt);
            }
        });

        btnEditPhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPhoneNumberActionPerformed(evt);
            }
        });

        btnEditGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditGenderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPersonalInformationLayout = new javax.swing.GroupLayout(panelPersonalInformation);
        panelPersonalInformation.setLayout(panelPersonalInformationLayout);
        panelPersonalInformationLayout.setHorizontalGroup(
            panelPersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPersonalInformationLayout.createSequentialGroup()
                .addGroup(panelPersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPersonalInformationLayout.createSequentialGroup()
                        .addGroup(panelPersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPersonalInformationLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(panelPersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPersonalInformationLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(panelPersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(tfRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfDateOfBirth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(panelPersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditGender, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelPersonalInformationLayout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(lblPersonalInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPersonalInformationLayout.setVerticalGroup(
            panelPersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPersonalInformationLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblPersonalInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelPersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelPersonalInformationLayout.createSequentialGroup()
                        .addGroup(panelPersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelPersonalInformationLayout.createSequentialGroup()
                                .addGroup(panelPersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelPersonalInformationLayout.createSequentialGroup()
                                        .addGroup(panelPersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(panelPersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btnEditName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)
                                        .addGroup(panelPersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(tfGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnEditGender, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(panelPersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnEditDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(panelPersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnEditPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelPersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        panelLoginInformation.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        lblLoginInformation.setText("Login Information");
        lblLoginInformation.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        lblUsername.setText("Username");

        lblPassword.setText("Password");

        lblEye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblEyeMousePressed(evt);
            }
        });

        btnEditUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditUsernameActionPerformed(evt);
            }
        });

        btnEditPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLoginInformationLayout = new javax.swing.GroupLayout(panelLoginInformation);
        panelLoginInformation.setLayout(panelLoginInformationLayout);
        panelLoginInformationLayout.setHorizontalGroup(
            panelLoginInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginInformationLayout.createSequentialGroup()
                .addGroup(panelLoginInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLoginInformationLayout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(lblLoginInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLoginInformationLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(panelLoginInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(panelLoginInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginInformationLayout.createSequentialGroup()
                                .addComponent(tfPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEye, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(panelLoginInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEditUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        panelLoginInformationLayout.setVerticalGroup(
            panelLoginInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginInformationLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelLoginInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEditPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLoginInformationLayout.createSequentialGroup()
                        .addComponent(lblLoginInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(panelLoginInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelLoginInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnEditUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(panelLoginInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblEye, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLoginInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        panelAccountImage.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        ptbAccountImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/minisoccerfieldmanagement/image/profile.jpg"))); // NOI18N

        btnEditImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAccountImageLayout = new javax.swing.GroupLayout(panelAccountImage);
        panelAccountImage.setLayout(panelAccountImageLayout);
        panelAccountImageLayout.setHorizontalGroup(
            panelAccountImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccountImageLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(ptbAccountImage, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAccountImageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEditImage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
        );
        panelAccountImageLayout.setVerticalGroup(
            panelAccountImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccountImageLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(ptbAccountImage, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditImage, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        lblAccountInformation.setForeground(new java.awt.Color(196, 204, 90));
        lblAccountInformation.setText("Account Information");
        lblAccountInformation.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelPersonalInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelLoginInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(panelAccountImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAccountInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(420, 420, 420))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblAccountInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(panelPersonalInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(panelLoginInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(panelAccountImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditImageActionPerformed
        ChangeImage changeImageForm = new ChangeImage(this);
        changeImageForm.setVisible(true);
        changeImageForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnEditImageActionPerformed

    private void lblEyeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEyeMousePressed
        passwordVisible = !passwordVisible;
        User user = UserSession.getInstance().getUser();
        if (passwordVisible) {
            lblEye.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/eyeopen.svg", 0.35f));
            tfPassword.setEchoChar((char) 0);
            tfPassword.setText(user.getPassword());
        } else {
            lblEye.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/eyeoff.svg", 0.35f));
            tfPassword.setEchoChar('*');
            tfPassword.setText("*".repeat(user.getPassword().length()));
        }
    }//GEN-LAST:event_lblEyeMousePressed

    private void btnEditUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditUsernameActionPerformed
        ChangeUsername changeUsernameForm = new ChangeUsername(this);
        changeUsernameForm.setVisible(true);
        changeUsernameForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnEditUsernameActionPerformed

    private void btnEditNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditNameActionPerformed
        ChangeName changeNameForm = new ChangeName(this);
        changeNameForm.setVisible(true);
        changeNameForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnEditNameActionPerformed

    private void btnEditDateOfBirthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditDateOfBirthActionPerformed
        ChangeDateOfBirth changeDateOfBirthForm = new ChangeDateOfBirth(this);
        changeDateOfBirthForm.setVisible(true);
        changeDateOfBirthForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnEditDateOfBirthActionPerformed

    private void btnEditPhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPhoneNumberActionPerformed
        ChangePhoneNumber changePhoneNumberForm = new ChangePhoneNumber(this);
        changePhoneNumberForm.setVisible(true);
        changePhoneNumberForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnEditPhoneNumberActionPerformed

    private void btnEditGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditGenderActionPerformed
        ChangeGender changeGenderForm = new ChangeGender(this);
        changeGenderForm.setVisible(true);
        changeGenderForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnEditGenderActionPerformed

    private void btnEditPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPasswordActionPerformed
        ChangePassword changePasswordForm = new ChangePassword(this);
        changePasswordForm.setVisible(true);
        changePasswordForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnEditPasswordActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.formdev.flatlaf.extras.components.FlatButton btnEditDateOfBirth;
    private com.formdev.flatlaf.extras.components.FlatButton btnEditGender;
    private com.formdev.flatlaf.extras.components.FlatButton btnEditImage;
    private com.formdev.flatlaf.extras.components.FlatButton btnEditName;
    private com.formdev.flatlaf.extras.components.FlatButton btnEditPassword;
    private com.formdev.flatlaf.extras.components.FlatButton btnEditPhoneNumber;
    private com.formdev.flatlaf.extras.components.FlatButton btnEditUsername;
    private com.formdev.flatlaf.extras.components.FlatLabel lblAccountInformation;
    private com.formdev.flatlaf.extras.components.FlatLabel lblDateOfBirth;
    private com.formdev.flatlaf.extras.components.FlatLabel lblEye;
    private com.formdev.flatlaf.extras.components.FlatLabel lblGender;
    private com.formdev.flatlaf.extras.components.FlatLabel lblLoginInformation;
    private com.formdev.flatlaf.extras.components.FlatLabel lblName;
    private com.formdev.flatlaf.extras.components.FlatLabel lblPassword;
    private com.formdev.flatlaf.extras.components.FlatLabel lblPersonalInformation;
    private com.formdev.flatlaf.extras.components.FlatLabel lblPhoneNumber;
    private com.formdev.flatlaf.extras.components.FlatLabel lblRole;
    private com.formdev.flatlaf.extras.components.FlatLabel lblUsername;
    private raven.crazypanel.CrazyPanel panelAccountImage;
    private raven.crazypanel.CrazyPanel panelLoginInformation;
    private raven.crazypanel.CrazyPanel panelPersonalInformation;
    private minisoccerfieldmanagement.util.PictureBox ptbAccountImage;
    private com.formdev.flatlaf.extras.components.FlatTextField tfDateOfBirth;
    private com.formdev.flatlaf.extras.components.FlatTextField tfGender;
    private com.formdev.flatlaf.extras.components.FlatTextField tfName;
    private com.formdev.flatlaf.extras.components.FlatPasswordField tfPassword;
    private com.formdev.flatlaf.extras.components.FlatTextField tfPhoneNumber;
    private com.formdev.flatlaf.extras.components.FlatTextField tfRole;
    private com.formdev.flatlaf.extras.components.FlatTextField tfUsername;
    // End of variables declaration//GEN-END:variables
}
