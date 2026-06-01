package view.profile;

import view.admin.RoundedBorder;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PersonalSettingFrm extends JPanel {
    private JComboBox<String> cbxLanguage;
    private JComboBox<String> cbxTimeGoal;
    private int userId;

    public PersonalSettingFrm(int userId) {
        this.userId = userId;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Personal Setting", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(new Color(30, 40, 60));
        lblTitle.setBorder(new EmptyBorder(30, 10, 30, 10));
        add(lblTitle, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(new EmptyBorder(0, 50, 50, 50));

        JPanel pnlLang = createSettingRow("Ngôn ngữ:", new String[]{"Tiếng Thanh Hóa", "Tiếng Anh", "Tiếng Việt"});
        cbxLanguage = (JComboBox<String>) ((JPanel) ((JPanel) pnlLang.getComponent(0)).getComponent(1)).getComponent(0);
        centerPanel.add(pnlLang);
        
        centerPanel.add(Box.createVerticalStrut(20));
        
        JPanel pnlTime = createSettingRow("Thời gian học mỗi ngày:", new String[]{"10 phút", "20 phút", "30 phút", "45 phút", "60 phút"});
        cbxTimeGoal = (JComboBox<String>) ((JPanel) ((JPanel) pnlTime.getComponent(0)).getComponent(1)).getComponent(0);
        centerPanel.add(pnlTime);
        
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(createSettingRow("Dark Mode:", new String[]{"Tắt", "Bật"}));
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(createSettingRow("Thông báo:", new String[]{"Tắt", "Bật"}));

        // Load existing settings from DB
        dao.PersonalSettingDAO dao = new dao.PersonalSettingDAO();
        Object[] existingSettings = dao.getSettingByUserId(userId);
        if (existingSettings != null) {
            String timeGoalStr = (String) existingSettings[0];
            int timeGoal = 10;
            if (timeGoalStr != null) {
                try {
                    timeGoal = Integer.parseInt(timeGoalStr.replace(" minutes", "").trim());
                } catch(Exception e){}
            }
            int langId = (Integer) existingSettings[1];
            // Simple mapping back to UI index
            cbxTimeGoal.setSelectedItem(timeGoal + " phút");
            if (langId == 1) cbxLanguage.setSelectedItem("Tiếng Anh");
            else if (langId == 2) cbxLanguage.setSelectedItem("Tiếng Việt");
            else cbxLanguage.setSelectedItem("Tiếng Thanh Hóa");
        }

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(new EmptyBorder(20, 10, 50, 10));
        
        JButton btnSave = new JButton("Lưu thay đổi");
        btnSave.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnSave.setForeground(new Color(30, 40, 60));
        btnSave.setBackground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.setContentAreaFilled(false);
        btnSave.setOpaque(true);
        btnSave.setPreferredSize(new Dimension(150, 40));
        btnSave.setBorder(new RoundedBorder(20, new Color(100, 100, 110), 1));
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.addActionListener(e -> {
            try {
                String timeStr = cbxTimeGoal.getSelectedItem().toString().replace(" phút", "");
                int timeGoal = Integer.parseInt(timeStr);
                
                String langStr = cbxLanguage.getSelectedItem().toString();
                int langId = langStr.equals("Tiếng Anh") ? 1 : (langStr.equals("Tiếng Việt") ? 2 : 3);
                
                if (timeGoal < 0) {
                    JOptionPane.showMessageDialog(this, "Thời gian không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                dao.PersonalSettingDAO updateDao = new dao.PersonalSettingDAO();
                boolean success = updateDao.updateSettings(userId, timeGoal, langId);
                
                if (success) {
                    JOptionPane.showMessageDialog(this, "Cài đặt đã được lưu thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi cập nhật cài đặt! Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        bottomPanel.add(btnSave);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createSettingRow(String labelText, String[] options) {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapper.setBackground(Color.WHITE);

        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(Color.WHITE);
        row.setPreferredSize(new Dimension(500, 50));
        row.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(25, new Color(100, 100, 110), 1),
            new EmptyBorder(2, 20, 2, 10)
        ));

        JLabel lbl = new JLabel(labelText);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl.setForeground(new Color(30, 40, 60));
        row.add(lbl, BorderLayout.WEST);

        JComboBox<String> cbx = new JComboBox<>(options);
        cbx.setFont(new Font("SansSerif", Font.PLAIN, 14));
        cbx.setForeground(new Color(50, 60, 80));
        cbx.setBackground(Color.WHITE);
        cbx.setPreferredSize(new Dimension(150, 30));
        
        // Remove nested RoundedBorder from combobox to prevent double clipping
        cbx.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 160), 1));
        
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.add(cbx);
        
        row.add(rightPanel, BorderLayout.EAST);

        wrapper.add(row);
        return wrapper;
    }
}