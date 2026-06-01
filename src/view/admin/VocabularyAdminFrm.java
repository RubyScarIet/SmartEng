package view.admin;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public class VocabularyAdminFrm extends JPanel {
    public VocabularyAdminFrm() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        JLabel lbl = new JLabel("Welcome Admin");
        lbl.setFont(new Font("SansSerif", Font.BOLD, 36));
        lbl.setForeground(new Color(30, 40, 60));
        add(lbl);
    }
}
