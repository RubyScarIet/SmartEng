package view.user;



import view.practice.*;
import view.leaderboard.*;
import view.admin.RoundedBorder; 

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class LearnerHomeFrm extends JFrame {
    private JPanel centerPanel;
    private JPanel sidebar;
    private Map<String, JButton> tabButtons;
    private String currentTab = "";

    public LearnerHomeFrm() {
        super("SmartEng");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 248, 250));

        
        JPanel mainWrapper = new JPanel(new BorderLayout());
        mainWrapper.setBackground(new Color(245, 248, 250));
        mainWrapper.setBorder(new EmptyBorder(30, 30, 30, 30));
        
        JPanel innerPanel = new JPanel(new BorderLayout());
        innerPanel.setBackground(Color.WHITE);
        innerPanel.setBorder(new LineBorder(new Color(50, 50, 60), 2, true));

        
        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(Color.WHITE);
        sidebar.setPreferredSize(new Dimension(250, 700));
        sidebar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(50, 50, 60)),
            new EmptyBorder(30, 20, 30, 20)
        ));

        JLabel titleLbl = new JLabel("SmartEng");
        titleLbl.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLbl.setForeground(new Color(30, 40, 60));
        titleLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(titleLbl);
        sidebar.add(Box.createVerticalStrut(40));

        tabButtons = new HashMap<>();
        String[] tabs = {"Study", "Alphabet", "Practice", "Leaderboard", "Daily Mission", "Profile", "Personal Setting"};
        
        for (String t : tabs) {
            JButton btn = createSidebarButton(t);
            tabButtons.put(t, btn);
            sidebar.add(btn);
            sidebar.add(Box.createVerticalStrut(15));
        }

        innerPanel.add(sidebar, BorderLayout.WEST);

        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        innerPanel.add(centerPanel, BorderLayout.CENTER);
        
        mainWrapper.add(innerPanel, BorderLayout.CENTER);
        add(mainWrapper, BorderLayout.CENTER);

        
        switchTab("Study");
    }

    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setForeground(new Color(50, 50, 60));
        btn.setBackground(Color.WHITE);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMaximumSize(new Dimension(200, 40));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorder(new RoundedBorder(20, new Color(100, 100, 110)));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (!currentTab.equals(text)) btn.setBackground(new Color(245, 245, 250));
            }
            public void mouseExited(MouseEvent e) {
                if (!currentTab.equals(text)) btn.setBackground(Color.WHITE);
            }
        });

        btn.addActionListener(e -> switchTab(text));
        return btn;
    }

    private void switchTab(String tabName) {
        currentTab = tabName;
        for (Map.Entry<String, JButton> entry : tabButtons.entrySet()) {
            if (entry.getKey().equals(tabName)) {
                entry.getValue().setBackground(new Color(225, 230, 235));
                entry.getValue().setBorder(new RoundedBorder(20, new Color(50, 50, 60), 2));
            } else {
                entry.getValue().setBackground(Color.WHITE);
                entry.getValue().setBorder(new RoundedBorder(20, new Color(100, 100, 110), 1));
            }
        }

        centerPanel.removeAll();
        JPanel viewPanel = null;
        switch (tabName) {
            case "Study": viewPanel = new JPanel(); break;
            case "Alphabet": viewPanel = new JPanel(); break;
            case "Practice": viewPanel = new PracticeSetupFrm(); break;
            case "Daily Mission": viewPanel = new JPanel(); break;
            case "Leaderboard": viewPanel = new LeaderboardFrm(); break;
            case "Profile": viewPanel = new JPanel(); break;
            case "Personal Setting": viewPanel = new JPanel(); break;
            default: viewPanel = new JPanel(); break;
        }
        centerPanel.add(viewPanel, BorderLayout.CENTER);
        centerPanel.revalidate();
        centerPanel.repaint();
    }
}
