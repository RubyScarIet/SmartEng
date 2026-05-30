package view.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginFrm extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrm() {
        super("SmartEng - Log In");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // --- Left Panel (Sidebar) ---
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(200, 500));
        leftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.DARK_GRAY));
        leftPanel.setLayout(null);

        JLabel lblLogo = new JLabel("SmartEng");
        lblLogo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblLogo.setBounds(20, 30, 150, 30);
        lblLogo.setForeground(Color.DARK_GRAY);
        leftPanel.add(lblLogo);

        add(leftPanel, BorderLayout.WEST);

        // --- Right Panel (Main Content) ---
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Log In", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblTitle.setForeground(Color.DARK_GRAY);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 40, 10);
        rightPanel.add(lblTitle, gbc);

        // Username field
        txtUsername = new JTextField(20);
        txtUsername.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtUsername.setPreferredSize(new Dimension(250, 40));
        txtUsername.setBorder(BorderFactory.createTitledBorder("Username"));
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 10, 10);
        rightPanel.add(txtUsername, gbc);

        // Password field
        txtPassword = new JPasswordField(20);
        txtPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtPassword.setPreferredSize(new Dimension(250, 40));
        txtPassword.setBorder(BorderFactory.createTitledBorder("Password"));
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 10, 30, 10);
        rightPanel.add(txtPassword, gbc);

        // Login Button
        btnLogin = new JButton("Log In");
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnLogin.setBackground(Color.DARK_GRAY);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setPreferredSize(new Dimension(250, 45));
        
        // Login Action
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText().trim();
                
                if (username.equalsIgnoreCase("admin")) {
                    JOptionPane.showMessageDialog(LoginFrm.this, "Đăng nhập thành công với quyền Admin!");
                    // new AdminHomeFrm().setVisible(true);
                    // dispose();
                } else if (!username.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrm.this, "Đăng nhập thành công với quyền Learner!");
                    // new LearnerHomeFrm().setVisible(true);
                    // dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFrm.this, "Vui lòng nhập Username!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        rightPanel.add(btnLogin, gbc);

        add(rightPanel, BorderLayout.CENTER);
    }
    
    // Hàm main để bạn có thể Run trực tiếp file này và xem thử giao diện
    public static void main(String[] args) {
        new LoginFrm().setVisible(true);
    }
}