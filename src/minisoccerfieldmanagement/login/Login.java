package minisoccerfieldmanagement.login;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import minisoccerfieldmanagement.main.Main;

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
        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 50 45 55 45", "fill,220:150"));
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "[light]background:darken(@background,3%);"
                + "[dark]background:lighten(@background,3%)");

        txtPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]background:darken(@background,10%);"
                + "[dark]background:lighten(@background,10%);"
                + "margin:4,6,4,6;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");

        cmdLogin.addActionListener((e) -> {
            Main.main.showMainForm();
        });
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Username");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");

        JLabel lbTitle = new JLabel("Login");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +13");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);

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

                JOptionPane.showMessageDialog(Login.this, "Đừng thử, chưa làm chức năng này");
            }
        });
        panel.add(forgetPasswordLabel, "alignx right, wrap");

        panel.add(new JLabel(""), "gapy 8");
        panel.add(cmdLogin, "gapy 10");
        add(panel);
    }

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton cmdLogin;
}
