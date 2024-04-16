package minisoccerfieldmanagement.login;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import minisoccerfieldmanagement.form.Dashboard;
import net.miginfocom.swing.MigLayout;
import minisoccerfieldmanagement.main.Main;
import minisoccerfieldmanagement.model.User;
import minisoccerfieldmanagement.service.UserServiceImpl;
import minisoccerfieldmanagement.tabbed.WindowsTabbed;
import minisoccerfieldmanagement.util.PanelRound;
import raven.alerts.MessageAlerts;
import raven.drawer.Drawer;
import raven.drawer.component.header.SimpleHeader;
import raven.drawer.component.header.SimpleHeaderData;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;
import raven.swing.AvatarIcon;

public class Login extends JPanel {

    private Image backgroundImage;

    public Login() {
        try {
            URL url = new URL("https://th.bing.com/th/id/R.d179d1f5e4942bb7f17db25767d398e4?rik=7g0Ug7EG4LNmWw&riu=http%3a%2f%2fwallpapercave.com%2fwp%2fwc1740690.jpg&ehk=nkwj9urnJlcs3p2W%2fxhFtHLVcDSl5vgVsNePPe54aPE%3d&risl=&pid=ImgRaw&r=0");
            backgroundImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        init();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void init() {
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        cmdLogin = new JButton("Login");
        PanelRound panel = new PanelRound(new MigLayout("wrap,fillx,insets 50 45 55 45", "fill,250:200"));
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "[light]background:darken(@background,3%);"
                + "[dark]background:lighten(@background,3%)");
        panel.setRoundBottomLeft(30);
        panel.setRoundBottomRight(30);
        panel.setRoundTopLeft(30);
        panel.setRoundTopRight(30);

        txtPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]background:darken(@background,10%);"
                + "[dark]background:lighten(@background,10%);"
                + "margin:4,6,4,6;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");
        cmdLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        cmdLogin.addActionListener((e) -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            if (username.equals("") || password.equals("")) {
                MessageAlerts.getInstance().showMessage("Login failed", "Username and password are not empty!!!", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                    @Override
                    public void action(PopupController pc, int i) {
                    }
                });
            } else {
                UserServiceImpl userServiceImpl = new UserServiceImpl();
                User user = userServiceImpl.verifyLoginData(username, password);
                if (user == null) {
                    MessageAlerts.getInstance().showMessage("Login failed", "Username or password is incorrect!!!", MessageAlerts.MessageType.ERROR, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                        @Override
                        public void action(PopupController pc, int i) {
                        }
                    });
                } else {
                    UserSession.getInstance().loginUser(user);
                    setAccountInfo();
                    Main.main.showMainForm();
                    WindowsTabbed.getInstance().addTab("Dashboard", new Dashboard());
                }
            }
            
//            User tempUser = new User();
//            tempUser.setName("Thanh Huy");
//            tempUser.setGender("Male");
//            tempUser.setDateOfBirth(Timestamp.valueOf("2003-06-25 00:00:00"));
//            tempUser.setPhoneNumber("0869017464");
//            tempUser.setPassword("admin");
//            tempUser.setUserName("admin");
//            tempUser.setRole("admin");
//            tempUser.setCreatedAt(Timestamp.valueOf("2024-04-06 17:00:00"));
//            UserSession.getInstance().loginUser(tempUser);
//            Main.main.showMainForm();
            
        });
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Username");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");

        JLabel lbTitle = new JLabel("Login");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +13");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(new JLabel(""), "gapy 8");
        panel.add(lbTitle);
        panel.add(new JLabel(""), "gapy 8");
        panel.add(new JLabel(""), "gapy 8");
        panel.add(new JLabel(""), "gapy 8");
        panel.add(txtUsername);
        panel.add(new JLabel(""), "gapy 8");
        panel.add(txtPassword);
        JLabel forgetPasswordLabel = new JLabel("Forget password?");
        forgetPasswordLabel.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:regular -2");
        forgetPasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        forgetPasswordLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgetPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MessageAlerts.getInstance().showMessage("Message", "Please contact management for help!!!", MessageAlerts.MessageType.WARNING, MessageAlerts.CLOSED_OPTION, new PopupCallbackAction() {
                    @Override
                    public void action(PopupController pc, int i) {
                    }
                });
            }
        });
        panel.add(forgetPasswordLabel, "alignx right, wrap");

        panel.add(new JLabel(""), "gapy 8");
        panel.add(cmdLogin, "gapy 10");
        panel.add(new JLabel(""), "gapy 8");
        add(panel);
    }
    private void setAccountInfo(){
        User user = UserSession.getInstance().getUser();
        String title = user.getName();
        String desc = user.getPhoneNumber();
        String path = "/minisoccerfieldmanagement/image/profile.jpg";
        if (user.getImage() != null)
        {
            File file = new File("src/minisoccerfieldmanagement/image/user/" + user.getImage());
            if (file.exists()) {
                path = "/minisoccerfieldmanagement/image/user/" + user.getImage();
            } 
        }
        SimpleHeaderData newSimpleHeaderData = new SimpleHeaderData()
                .setIcon(new AvatarIcon(getClass().getResource(path), 60, 60, 999))
                .setTitle(title)
                .setDescription(desc);
        SimpleHeader header=(SimpleHeader)Drawer.getInstance().getDrawerPanel().getDrawerBuilder().getHeader();
        header.setSimpleHeaderData(newSimpleHeaderData);
    }
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton cmdLogin;
}
