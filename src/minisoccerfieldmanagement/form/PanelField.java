package minisoccerfieldmanagement.form;

import java.awt.Color;
import javax.swing.JPanel;
import minisoccerfieldmanagement.util.WrapLayout;

public class PanelField extends JPanel{
        
    public PanelField() {
        setBackground(Color.WHITE);
        setLayout(new WrapLayout(WrapLayout.LEFT, 10, 10));
    }
    
}
