package view.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import dao.AccessHistoryDAO;

public class StatisticAdminFrm extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    // Access History Components
    private JTextField txtTimeSearch;
    private JButton btnSearch;
    private JTable tblHistory;
    private DefaultTableModel tableModel;

    public StatisticAdminFrm() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel menuCard = createMenuCard();
        JPanel accessCard = createAccessHistoryCard();

        mainPanel.add(menuCard, "Menu");
        mainPanel.add(accessCard, "AccessHistory");

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createMenuCard() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnUsing = createStatisticBtn("Using & Accessing");
        btnUsing.addActionListener(e -> {
            txtTimeSearch.setText("Find by time");
            txtTimeSearch.setForeground(Color.GRAY);
            tableModel.setRowCount(0); // clear
            cardLayout.show(mainPanel, "AccessHistory");
        });

        JButton btnStreak = createStatisticBtn("Activity Streak");
        JButton btnHistory = createStatisticBtn("History");

        JButton[] btns = {btnUsing, btnStreak, btnHistory};
        for (JButton btn : btns) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonPanel.add(btn);
            buttonPanel.add(Box.createVerticalStrut(15));
        }

        p.add(buttonPanel);
        return p;
    }

    private JPanel createAccessHistoryCard() {
        JPanel p = new JPanel(new BorderLayout(0, 20));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        topPanel.setBackground(Color.WHITE);

        JButton btnBack = createActionBtn("<- Back");
        btnBack.setPreferredSize(new Dimension(80, 40));
        btnBack.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        txtTimeSearch = new JTextField();
        txtTimeSearch.setPreferredSize(new Dimension(300, 40));
        txtTimeSearch.setBorder(BorderFactory.createCompoundBorder(
            new view.admin.RoundedBorder(20, new Color(150, 150, 160), 1),
            BorderFactory.createEmptyBorder(0, 15, 0, 15)
        ));
        txtTimeSearch.setText("Find by time");
        txtTimeSearch.setForeground(Color.GRAY);
        txtTimeSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtTimeSearch.getText().equals("Find by time")) {
                    txtTimeSearch.setText("");
                    txtTimeSearch.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtTimeSearch.getText().isEmpty()) {
                    txtTimeSearch.setText("Find by time");
                    txtTimeSearch.setForeground(Color.GRAY);
                }
            }
        });

        btnSearch = createActionBtn("Search");
        btnSearch.addActionListener(e -> {
            String timeStr = txtTimeSearch.getText().trim();
            if(timeStr.equals("Find by time") || timeStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập giờ (vd: 08:30)!");
                tableModel.setRowCount(0);
                return;
            }
            // format validation
            if(!timeStr.matches("\\d{2}:\\d{2}")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng gg:pp (vd: 08:30)!");
                tableModel.setRowCount(0);
                return;
            }
            
            AccessHistoryDAO dao = new AccessHistoryDAO();
            List<Object[]> data = dao.searchByTime(timeStr);
            tableModel.setRowCount(0);
            for(Object[] row : data) {
                tableModel.addRow(row);
            }
            if(data.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu cho thời gian: " + timeStr);
            }
        });

        topPanel.add(btnBack);
        topPanel.add(txtTimeSearch);
        topPanel.add(btnSearch);

        p.add(topPanel, BorderLayout.NORTH);

        // Center Table Panel
        String[] columns = {"ID", "user_ID", "access Time", "ipAddress", "deviceInfo", "actionType", "timeStamp", "duration"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tblHistory = new JTable(tableModel);
        tblHistory.setRowHeight(35);
        tblHistory.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tblHistory.getTableHeader().setBackground(new Color(245, 248, 250));
        tblHistory.getTableHeader().setReorderingAllowed(false);
        tblHistory.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tblHistory.setGridColor(new Color(220, 220, 220));
        
        JScrollPane scrollPane = new JScrollPane(tblHistory);
        scrollPane.setBorder(new view.admin.RoundedBorder(10, new Color(150, 150, 160), 1));
        scrollPane.getViewport().setBackground(Color.WHITE);

        p.add(scrollPane, BorderLayout.CENTER);

        return p;
    }

    private JButton createStatisticBtn(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btn.setForeground(new Color(30, 40, 60));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setMaximumSize(new Dimension(250, 40));
        btn.setPreferredSize(new Dimension(250, 40));
        btn.setBorder(new view.admin.RoundedBorder(20, new Color(100, 100, 110), 1));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JButton createActionBtn(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btn.setForeground(new Color(30, 40, 60));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(100, 40));
        btn.setBorder(new view.admin.RoundedBorder(20, new Color(100, 100, 110), 1));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
