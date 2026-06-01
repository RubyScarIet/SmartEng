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

    public LeaderboardFrm() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(Color.WHITE);

        mainPanel.add(createOverviewPanel(), "Overview");
        mainPanel.add(createRankDetailPanel(), "RankDetail");
        mainPanel.add(new CompetitorProfileFrm(selectedUserName, cardLayout, mainPanel), "UserStats");

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
            LeagueFrm leagueFrm = new LeagueFrm(this);
            leagueFrm.setVisible(true);
        });

        JPanel leftTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        leftTop.setBackground(Color.WHITE);
        leftTop.add(btnLeague);
        topPanel.add(leftTop, BorderLayout.WEST);

        dao.LeagueDAO dao = new dao.LeagueDAO();
        
        String loggedInUser = "StarLearner"; 
        Object[] userStats = dao.getUserStats(loggedInUser);
        String currentLeague = "Obsidian";
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
                    mainPanel.add(new CompetitorProfileFrm(selectedUserName, cardLayout, mainPanel), "UserStats");
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


}