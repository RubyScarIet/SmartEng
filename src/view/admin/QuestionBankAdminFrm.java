package view.admin;
import javax.swing.*;
import java.awt.*;
public class QuestionBankAdminFrm extends JPanel {
    public QuestionBankAdminFrm() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        JLabel lbl = new JLabel("Welcome Admin");
        lbl.setFont(new Font("SansSerif", Font.BOLD, 36));
        lbl.setForeground(new Color(30, 40, 60));
        add(lbl);
    }
}
