package view.leaderboard;

import java.awt.*;
import javax.swing.*;
import view.admin.RoundedBorder;

public class CompetitorProfileFrm extends JPanel {
    
    public CompetitorProfileFrm(String selectedUserName, CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        String name = selectedUserName.isEmpty() ? "Jett" : selectedUserName;

        dao.LeagueDAO dao = new dao.LeagueDAO();
        Object[] stats = dao.getUserStats(name);

        String avatar = "URL 1";
        int exp = 0;
        int streak = 0;
        String prizes = "";
        String league = "Obsidian";

        if (stats != null) {
            avatar = stats[0] != null ? (String) stats[0] : "URL 1";
            exp = (Integer) stats[2];
            streak = (Integer) stats[3];
            prizes = stats[4] != null ? (String) stats[4] : "";
            league = stats[5] != null ? (String) stats[5] : "Unknown";
        }

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        topPanel.setBackground(Color.WHITE);
        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnBack.setForeground(new Color(30, 40, 60));
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setOpaque(true);
        btnBack.setBackground(Color.WHITE);
        btnBack.setBorder(new RoundedBorder(15, new Color(100, 100, 110), 1));
        btnBack.setPreferredSize(new Dimension(80, 40));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(e -> cardLayout.show(mainPanel, "Overview"));
        topPanel.add(btnBack);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);

        JPanel profileTop = new JPanel();
        profileTop.setLayout(new BoxLayout(profileTop, BoxLayout.Y_AXIS));
        profileTop.setBackground(Color.WHITE);

        JLabel lblAvatar = new JLabel(avatar, SwingConstants.CENTER);
        lblAvatar.setPreferredSize(new Dimension(100, 100));
        lblAvatar.setMaximumSize(new Dimension(100, 100));
        lblAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblAvatar.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblAvatar.setForeground(new Color(30, 40, 60));
        lblAvatar.setBorder(new RoundedBorder(50, new Color(100, 100, 110), 1));

        JLabel lblName = new JLabel(name, SwingConstants.CENTER);
        lblName.setFont(new Font("SansSerif", Font.PLAIN, 24));
        lblName.setForeground(new Color(30, 40, 60));
        lblName.setAlignmentX(Component.CENTER_ALIGNMENT);

        profileTop.add(lblAvatar);
        profileTop.add(Box.createVerticalStrut(20));
        profileTop.add(lblName);

        JPanel statsGrid = new JPanel(new GridLayout(2, 2, 20, 20));
        statsGrid.setBackground(Color.WHITE);
        statsGrid.setMaximumSize(new Dimension(600, 200));

        statsGrid.add(createStatBox("Current Streak", streak + " Days"));
        statsGrid.add(createStatBox("Total EXP", exp + " EXP"));
        statsGrid.add(createStatBox("League Rank", league));
        statsGrid.add(createStatBox("Special Prizes", prizes.isEmpty() ? "None" : prizes));

        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(profileTop);
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(statsGrid);

        add(centerPanel, BorderLayout.CENTER);
    }

    private JPanel createStatBox(String title, String value) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(20, new Color(100, 100, 110), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblTitle.setForeground(new Color(100, 110, 120));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblValue.setForeground(new Color(30, 40, 60));
        lblValue.setAlignmentX(Component.LEFT_ALIGNMENT);

        p.add(lblTitle);
        p.add(Box.createVerticalStrut(10));
        p.add(lblValue);

        return p;
    }
}