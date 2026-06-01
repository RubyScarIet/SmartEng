package view.user;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public class LoginFrm extends JFrame {
    public LoginFrm() {
        super("SmartEng - Log In");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(220, 500));
        sidebar.setBackground(Color.WHITE);
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.DARK_GRAY));
        JLabel lblLogo = new JLabel("SmartEng", SwingConstants.LEFT);
        lblLogo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblLogo.setBorder(new EmptyBorder(30, 20, 0, 0));
        sidebar.add(lblLogo, BorderLayout.NORTH);
        JPanel mainArea = new JPanel(new GridBagLayout());
        mainArea.setBackground(Color.WHITE);
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        JLabel lblTitle = new JLabel("Log In", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField txtUser = new JTextField();
        txtUser.setMaximumSize(new Dimension(280, 40));
        txtUser.setPreferredSize(new Dimension(280, 40));
        txtUser.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtUser.setHorizontalAlignment(JTextField.CENTER);
        txtUser.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
            new EmptyBorder(5, 5, 5, 5)
        ));
        JPasswordField txtPass = new JPasswordField();
        txtPass.setMaximumSize(new Dimension(280, 40));
        txtPass.setPreferredSize(new Dimension(280, 40));
        txtPass.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtPass.setHorizontalAlignment(JTextField.CENTER);
        txtPass.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true),
            new EmptyBorder(5, 5, 5, 5)
        ));
        JButton btnLogin = new JButton("Log In");
        btnLogin.setMaximumSize(new Dimension(150, 40));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setBackground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnLogin.addActionListener(e -> {
            String uname = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword());
            model.User user = new model.User();
            user.setUsername(uname);
            user.setPassword(pass);
            dao.UserDAO userDAO = new dao.UserDAO();
            if (userDAO.checkLogin(user)) {
                if ("Admin".equalsIgnoreCase(user.getPosition()) || "admin".equalsIgnoreCase(uname)) {
                    new AdminHomeFrm().setVisible(true);
                } else {
                    new LearnerHomeFrm(user).setVisible(true);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu!");
            }
        });
        formPanel.add(lblTitle);
        formPanel.add(Box.createVerticalStrut(40));
        JLabel lblU = new JLabel("Username");
        lblU.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblU.setForeground(Color.GRAY);
        formPanel.add(lblU);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(txtUser);
        formPanel.add(Box.createVerticalStrut(20));
        JLabel lblP = new JLabel("Password");
        lblP.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblP.setForeground(Color.GRAY);
        formPanel.add(lblP);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(txtPass);
        formPanel.add(Box.createVerticalStrut(40));
        formPanel.add(btnLogin);
        mainArea.add(formPanel);
        add(sidebar, BorderLayout.WEST);
        add(mainArea, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrm().setVisible(true));
    }
}
