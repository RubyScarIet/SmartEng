package view.profile;
import view.admin.RoundedBorder;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicProgressBarUI;
public class ProfileFrm extends JPanel {
    private JLabel lblName;
    private int userId;
    public ProfileFrm(int userId) {
        this.userId = userId;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        JLabel lblTitle = new JLabel("Profile", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(new Color(100, 110, 120));
        lblTitle.setBorder(new EmptyBorder(30, 10, 30, 10));
        add(lblTitle, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        String dName = "Trường Đức Tùng";
        int dLevel = 18;
        int dTotalEXP = 1836;
        int dStreak = 36;
        dao.ProfileDAO dao = new dao.ProfileDAO();
        Object[] dbProfile = dao.getProfileByUserId(userId); 
        if (dbProfile != null) {
            dName = (String) dbProfile[0];
            dLevel = (Integer) dbProfile[2];
            dTotalEXP = (Integer) dbProfile[3];
            dStreak = (Integer) dbProfile[4];
        }
        JPanel topInfoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
        topInfoPanel.setBackground(Color.WHITE);
        JLabel lblAvatar = new JLabel("👤", SwingConstants.CENTER);
        lblAvatar.setFont(new Font("SansSerif", Font.PLAIN, 40));
        lblAvatar.setForeground(new Color(150, 160, 170));
        lblAvatar.setPreferredSize(new Dimension(100, 100));
        JPanel avatarWrapper = new JPanel(new BorderLayout());
        avatarWrapper.setBackground(new Color(230, 235, 240));
        avatarWrapper.setBorder(new RoundedBorder(50, new Color(150, 160, 170), 1));
        avatarWrapper.setPreferredSize(new Dimension(100, 100));
        avatarWrapper.add(lblAvatar, BorderLayout.CENTER);
        JPanel nameStreakPanel = new JPanel();
        nameStreakPanel.setLayout(new BoxLayout(nameStreakPanel, BoxLayout.Y_AXIS));
        nameStreakPanel.setBackground(Color.WHITE);
        lblName = new JLabel(dName != null ? dName : "User");
        lblName.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblName.setForeground(new Color(30, 40, 60));
        lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel lblStreakNum = new JLabel(String.valueOf(dStreak));
        lblStreakNum.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblStreakNum.setForeground(new Color(30, 40, 60));
        lblStreakNum.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel lblStreakTxt = new JLabel("streak");
        lblStreakTxt.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblStreakTxt.setForeground(new Color(100, 110, 120));
        lblStreakTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameStreakPanel.add(lblName);
        nameStreakPanel.add(Box.createVerticalStrut(10));
        nameStreakPanel.add(lblStreakNum);
        nameStreakPanel.add(lblStreakTxt);
        topInfoPanel.add(avatarWrapper);
        topInfoPanel.add(nameStreakPanel);
        int maxExp = ((dTotalEXP / 2000) + 1) * 2000;
        JProgressBar progressBar = new JProgressBar(0, maxExp);
        progressBar.setValue(dTotalEXP);
        progressBar.setStringPainted(true);
        progressBar.setString("Level " + dLevel);
        progressBar.setFont(new Font("SansSerif", Font.BOLD, 14));
        progressBar.setForeground(new Color(30, 40, 60));
        progressBar.setBackground(Color.WHITE);
        progressBar.setBorder(new RoundedBorder(20, new Color(100, 100, 110), 1));
        progressBar.setPreferredSize(new Dimension(400, 30));
        progressBar.setMaximumSize(new Dimension(400, 30));
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        progressBar.setUI(new BasicProgressBarUI() {
            protected Color getSelectionBackground() { return new Color(30, 40, 60); }
            protected Color getSelectionForeground() { return new Color(30, 40, 60); }
            public void paintDeterminate(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Insets b = progressBar.getInsets();
                int w = progressBar.getWidth() - b.right - b.left;
                int h = progressBar.getHeight() - b.top - b.bottom;
                int amountFull = getAmountFull(b, w, h);
                g2.setColor(new Color(120, 240, 160)); 
                g2.fillRoundRect(b.left, b.top, amountFull, h, 20, 20);
                super.paintString(g, b.left, b.top, w, h, amountFull, b);
            }
        });
        JLabel lblProgress = new JLabel(dTotalEXP + "/" + maxExp);
        lblProgress.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblProgress.setForeground(new Color(100, 110, 120));
        lblProgress.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnEdit = new JButton("Edit");
        btnEdit.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnEdit.setForeground(new Color(30, 40, 60));
        btnEdit.setBackground(Color.WHITE);
        btnEdit.setFocusPainted(false);
        btnEdit.setContentAreaFilled(false);
        btnEdit.setOpaque(true);
        btnEdit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEdit.setMaximumSize(new Dimension(100, 35));
        btnEdit.setBorder(new RoundedBorder(15, new Color(100, 100, 110), 1));
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEdit.addActionListener(e -> {
            JPanel editPanel = new JPanel(new GridLayout(3, 1, 10, 10));
            JTextField txtName = new JTextField(lblName.getText());
            JButton btnUpload = new JButton("Upload Avatar...");
            String[] selectedAvatar = new String[]{"ava.jpg"}; 
            btnUpload.addActionListener(ev -> {
                JFileChooser fileChooser = new JFileChooser();
                int res = fileChooser.showOpenDialog(this);
                if (res == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fileChooser.getSelectedFile();
                    String fileName = file.getName().toLowerCase();
                    if (!fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg") && !fileName.endsWith(".png")) {
                        JOptionPane.showMessageDialog(this, "Chỉ chấp nhận định dạng ảnh .jpg, .jpeg, .png", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    long sizeInMB = file.length() / (1024 * 1024);
                    if (sizeInMB > 5) {
                        JOptionPane.showMessageDialog(this, "Dung lượng ảnh không được vượt quá 5MB", "Lỗi dung lượng", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    selectedAvatar[0] = file.getName();
                    JOptionPane.showMessageDialog(this, "Đã tải lên ảnh hợp lệ: " + file.getName());
                }
            });
            editPanel.add(new JLabel("Edit Name:"));
            editPanel.add(txtName);
            editPanel.add(btnUpload);
            int result = JOptionPane.showConfirmDialog(this, editPanel, "Edit Profile", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String newName = txtName.getText().trim();
                if (newName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Tên hiển thị không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else if (newName.length() > 50) {
                    JOptionPane.showMessageDialog(this, "Tên hiển thị không được vượt quá 50 ký tự", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    dao.ProfileDAO updateDao = new dao.ProfileDAO();
                    boolean success = updateDao.updateProfile(userId, newName, selectedAvatar[0]);
                    if (success) {
                        lblName.setText(newName);
                        JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Tên hiển thị này đã tồn tại hoặc có lỗi xảy ra", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(topInfoPanel);
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(progressBar);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(lblProgress);
        centerPanel.add(Box.createVerticalStrut(50));
        centerPanel.add(btnEdit);
        add(centerPanel, BorderLayout.CENTER);
    }
}
