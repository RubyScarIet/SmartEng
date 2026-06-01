package view.profile;

import view.admin.RoundedBorder;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PersonalSettingFrm extends JPanel {
    public PersonalSettingFrm() {
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

        centerPanel.add(createSettingRow("Ngôn ngữ:", new String[]{"Tiếng Thanh Hóa", "Tiếng Anh", "Tiếng Việt"}));
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(createSettingRow("Thời gian học mỗi ngày:", new String[]{"10 phút", "20 phút", "30 phút", "45 phút", "60 phút"}));
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(createSettingRow("Dark Mode:", new String[]{"Tắt", "Bật"}));
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(createSettingRow("Thông báo:", new String[]{"Tắt", "Bật"}));

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
            JOptionPane.showMessageDialog(this, "Cài đặt đã được lưu thành công!");
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