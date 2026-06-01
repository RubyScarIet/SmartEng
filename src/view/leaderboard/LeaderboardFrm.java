package view.leaderboard;
import view.admin.RoundedBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
public class LeaderboardFrm extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private String selectedUserName = "";
    private int userId;
    public LeaderboardFrm(int userId) {
        this.userId = userId;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(createOverviewPanel(), "Overview");
        mainPanel.add(createRankDetailPanel(), "RankDetail");
        mainPanel.add(createUserStatsPanel(), "UserStats");
        add(mainPanel, BorderLayout.CENTER);
    }
    private JPanel createOverviewPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
        JButton btnLeague = new JButton("League");
        btnLeague.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnLeague.setForeground(new Color(30, 40, 60));
        btnLeague.setBackground(Color.WHITE);
        btnLeague.setFocusPainted(false);
        btnLeague.setContentAreaFilled(false);
        btnLeague.setOpaque(true);
        btnLeague.setPreferredSize(new Dimension(80, 35));
        btnLeague.setBorder(new RoundedBorder(10, new Color(100, 100, 110), 1));
        btnLeague.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLeague.addActionListener(e -> {
            dao.LeaderboardDAO dao = new dao.LeaderboardDAO();
            java.util.List<String> leagues = dao.getAllLeagues();
            JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Các Bậc Xếp Hạng (Leagues)",
                    true);
            dialog.setSize(300, 400);
            dialog.setLocationRelativeTo(this);
            JPanel listPanel = new JPanel();
            listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
            listPanel.setBackground(Color.WHITE);
            if (leagues == null || leagues.isEmpty()) {
                leagues = java.util.Arrays.asList("Radiant", "Immortal", "Ascendant", "Diamond", "Platinum", "Gold",
                        "Silver", "Bronze", "Iron");
            }
            for (String league : leagues) {
                JLabel lbl = new JLabel(league, SwingConstants.CENTER);
                lbl.setFont(new Font("SansSerif", Font.BOLD, 16));
                lbl.setBorder(new EmptyBorder(15, 10, 15, 10));
                lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
                listPanel.add(lbl);
                JSeparator sep = new JSeparator();
                sep.setMaximumSize(new Dimension(250, 1));
                listPanel.add(sep);
            }
            JScrollPane scroll = new JScrollPane(listPanel);
            dialog.add(scroll);
            dialog.setVisible(true);
        });
        JPanel leftTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        leftTop.setBackground(Color.WHITE);
        leftTop.add(btnLeague);
        topPanel.add(leftTop, BorderLayout.WEST);
        dao.LeaderboardDAO dao = new dao.LeaderboardDAO();
        dao.ProfileDAO profileDao = new dao.ProfileDAO();
        Object[] profileStats = profileDao.getProfileByUserId(this.userId);
        String loggedInUser = (profileStats != null && profileStats[0] != null) ? (String) profileStats[0] : "StarLearner";
        Object[] userStats = dao.getUserStats(loggedInUser);
        String currentLeague = "Gold"; 
        if (userStats != null && userStats[5] != null) {
            currentLeague = (String) userStats[5];
        }
        JPanel centerTop = new JPanel();
        centerTop.setLayout(new BoxLayout(centerTop, BoxLayout.Y_AXIS));
        centerTop.setBackground(Color.WHITE);
        JLabel lblRank = new JLabel(currentLeague, SwingConstants.CENTER);
        lblRank.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblRank.setForeground(new Color(30, 40, 60));
        lblRank.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerTop.add(lblRank);
        JLabel lblDiamond = new JLabel("◇", SwingConstants.CENTER);
        lblDiamond.setFont(new Font("SansSerif", Font.PLAIN, 40));
        lblDiamond.setForeground(new Color(50, 60, 80));
        lblDiamond.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerTop.add(lblDiamond);
        topPanel.add(centerTop, BorderLayout.CENTER);
        JButton btnViewRank = new JButton("Detail");
        btnViewRank.setVisible(false); 
        btnViewRank.addActionListener(e -> cardLayout.show(mainPanel, "RankDetail"));
        topPanel.add(btnViewRank, BorderLayout.EAST);
        p.add(topPanel, BorderLayout.NORTH);
        String[] cols = { "No", "Avt", "Name", "Total EXP" };
        Object[][] data = {
                { 1, "URL1", "Jett", 424 },
                { 2, "URL2", "Raze", 390 },
                { 3, "URL3", "Omen", 310 },
                { 4, "URL4", "Sage", 280 },
                { 5, "URL5", "Reyna", 250 }
        };
        Object[][] dbData = dao.getTopUsersByLeague(currentLeague);
        if (dbData != null && dbData.length > 0) {
            data = dbData;
        }
        DefaultTableModel model = new DefaultTableModel(data, cols) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        table.setRowHeight(50);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setForeground(new Color(50, 50, 60));
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getTableHeader().setBackground(Color.WHITE);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.getTableHeader().setForeground(new Color(100, 100, 110));
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    selectedUserName = (String) table.getValueAt(row, 2);
                    mainPanel.add(createUserStatsPanel(), "UserStats");
                    cardLayout.show(mainPanel, "UserStats");
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        JPanel tableWrapper = new JPanel(new BorderLayout());
        tableWrapper.setBackground(Color.WHITE);
        tableWrapper.setBorder(new RoundedBorder(20, new Color(100, 100, 110), 1));
        tableWrapper.add(scrollPane, BorderLayout.CENTER);
        JPanel paddingPanel = new JPanel(new BorderLayout());
        paddingPanel.setBackground(Color.WHITE);
        paddingPanel.setBorder(new EmptyBorder(0, 40, 40, 40));
        paddingPanel.add(tableWrapper, BorderLayout.CENTER);
        p.add(paddingPanel, BorderLayout.CENTER);
        return p;
    }
    private JPanel createRankDetailPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        JLabel lbl = new JLabel("Rank Detail: Radiant - Top 1% of players", SwingConstants.CENTER);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 24));
        p.add(lbl, BorderLayout.CENTER);
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> cardLayout.show(mainPanel, "Overview"));
        JPanel bot = new JPanel();
        bot.add(btnBack);
        p.add(bot, BorderLayout.SOUTH);
        return p;
    }
    private JPanel createUserStatsPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        String name = selectedUserName.isEmpty() ? "Jett" : selectedUserName;
        dao.LeaderboardDAO dao = new dao.LeaderboardDAO();
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
        p.add(topPanel, BorderLayout.NORTH);
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
        p.add(centerPanel, BorderLayout.CENTER);
        return p;
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
