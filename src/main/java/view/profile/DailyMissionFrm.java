package view.profile;

import view.admin.RoundedBorder;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DailyMissionFrm extends JPanel {
    public DailyMissionFrm() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("DAILY MISSIONS", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(new Color(30, 40, 60));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        add(lblTitle, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.WHITE);

        dao.DailyMissionDAO dao = new dao.DailyMissionDAO();
        java.util.List<Object[]> dbMissions = dao.getMissions();
        if (dbMissions != null && !dbMissions.isEmpty()) {
            java.util.Random rand = new java.util.Random();
            for (int i = 0; i < dbMissions.size(); i++) {
                Object[] row = dbMissions.get(i);
                int target = (Integer) row[3];
                int current = rand.nextInt(target + 1);
                boolean isClaimable = (current == target);
                
                center.add(createMissionCard((i+1) + ". " + row[0], (String)row[2], row[1] + " EXP", current, target, isClaimable));
                center.add(Box.createVerticalStrut(20));
            }
        } else {
            // Fallback mock
            center.add(createMissionCard("1. Hoàn thành 2 bài học", "Học ít nhất 2 bài", "50 EXP", 1, 2, false));
            center.add(Box.createVerticalStrut(20));
            center.add(createMissionCard("2. Đạt 100% bài kiểm tra", "Đạt điểm tối đa", "20 Coins", 1, 1, true));
            center.add(Box.createVerticalStrut(20));
            center.add(createMissionCard("3. Luyện tập 10 phút", "Thời gian học > 10m", "1 Heart", 1, 1, true));
        }

        JScrollPane scroll = new JScrollPane(center);
        scroll.setBorder(null);
        scroll.setBackground(Color.WHITE);
        scroll.getViewport().setBackground(Color.WHITE);
        
        JPanel paddingPanel = new JPanel(new BorderLayout());
        paddingPanel.setBackground(Color.WHITE);
        paddingPanel.setBorder(new EmptyBorder(0, 40, 40, 40));
        paddingPanel.add(scroll, BorderLayout.CENTER);
        
        add(paddingPanel, BorderLayout.CENTER);
    }

    private JPanel createMissionCard(String title, String desc, String exp, int current, int target, boolean isClaimable) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(20, new Color(100, 100, 110), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panel.setMaximumSize(new Dimension(800, 140));

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(Color.WHITE);
        
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblTitle.setForeground(new Color(30, 40, 60));
        
        JLabel lblDesc = new JLabel(desc);
        lblDesc.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblDesc.setForeground(new Color(80, 90, 100));
        
        JPanel detailRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        detailRow.setBackground(Color.WHITE);
        
        JLabel lblProgress = new JLabel("Progress: " + current + "/" + target + "   ");
        lblProgress.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblProgress.setForeground(new Color(100, 110, 120));
        
        JLabel lblExp = new JLabel("Reward: " + exp);
        lblExp.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblExp.setForeground(new Color(100, 110, 120));
        
        detailRow.add(lblProgress);
        detailRow.add(Box.createHorizontalStrut(20));
        detailRow.add(lblExp);
        
        info.add(lblTitle);
        info.add(Box.createVerticalStrut(5));
        info.add(lblDesc);
        info.add(Box.createVerticalStrut(15));
        info.add(detailRow);
        
        panel.add(info, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new BorderLayout());
        actionPanel.setBackground(Color.WHITE);
        actionPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        
        if (isClaimable) {
            JButton btn = new JButton("[ Claim Reward ]");
            btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
            btn.setForeground(new Color(50, 60, 80));
            btn.setBackground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setContentAreaFilled(false);
            btn.setOpaque(true);
            btn.setBorder(new RoundedBorder(15, new Color(100, 100, 110), 1));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setPreferredSize(new Dimension(150, 35));
            btn.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this, "Confirm claim reward?", "Claim Reward", JOptionPane.OK_CANCEL_OPTION);
                if (confirm == JOptionPane.OK_OPTION) {
                    JOptionPane.showMessageDialog(this, "Congratulation!\nReward Received", "Success", JOptionPane.INFORMATION_MESSAGE);
                    btn.setText("[ Claimed ]");
                    btn.setEnabled(false);
                }
            });
            actionPanel.add(btn, BorderLayout.EAST);
        } else {
            JLabel lblStatus = new JLabel("Status: In Progress");
            lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 14));
            lblStatus.setForeground(new Color(100, 110, 120));
            actionPanel.add(lblStatus, BorderLayout.EAST);
        }
        
        panel.add(actionPanel, BorderLayout.EAST);
        return panel;
    }
}
