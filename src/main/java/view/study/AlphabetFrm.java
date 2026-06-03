package view.study;

import view.admin.RoundedBorder;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AlphabetFrm extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public AlphabetFrm() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(Color.WHITE);

        JPanel gridCard = new JPanel(new BorderLayout());
        gridCard.setBackground(Color.WHITE);
        
        JLabel lblTitle = new JLabel("Alphabet Learning", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(new Color(30, 40, 60));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        gridCard.add(lblTitle, BorderLayout.NORTH);

        JPanel centerGrid = new JPanel(new GridLayout(0, 7, 15, 15));
        centerGrid.setBackground(Color.WHITE);
        centerGrid.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        dao.AlphabetDAO dao = new dao.AlphabetDAO();
        java.util.List<Object[]> dbLetters = dao.getAllLetters();
        
        if (dbLetters != null && !dbLetters.isEmpty()) {
            for (Object[] row : dbLetters) {
                String cStr = (String) row[0];
                if (cStr == null || cStr.isEmpty()) continue;
                char c = cStr.charAt(0);
                JButton btnLetter = createAlphabetBtn(c);
                btnLetter.addActionListener(e -> showLetterDetails(c, (String)row[1], (String)row[2]));
                centerGrid.add(btnLetter);
            }
        } else {
            // Fallback
            char[] alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
            for (char c : alphabets) {
                JButton btnLetter = createAlphabetBtn(c);
                btnLetter.addActionListener(e -> showLetterDetails(c, "/a/", "Apple"));
                centerGrid.add(btnLetter);
            }
        }
        
        JPanel outerGridWrapper = new JPanel(new BorderLayout());
        outerGridWrapper.setBackground(Color.WHITE);
        outerGridWrapper.setBorder(new RoundedBorder(20, new Color(100, 100, 110), 1));
        outerGridWrapper.add(centerGrid, BorderLayout.CENTER);

        JPanel gridWrapperAlignTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gridWrapperAlignTop.setBackground(Color.WHITE);
        gridWrapperAlignTop.add(outerGridWrapper);

        JPanel paddingPanel = new JPanel(new BorderLayout());
        paddingPanel.setBackground(Color.WHITE);
        paddingPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
        paddingPanel.add(gridWrapperAlignTop, BorderLayout.NORTH);

        gridCard.add(paddingPanel, BorderLayout.CENTER);

        JPanel detailsCard = new JPanel(new BorderLayout());
        detailsCard.setBackground(Color.WHITE);

        mainPanel.add(gridCard, "Grid");
        mainPanel.add(detailsCard, "Details");

        add(mainPanel, BorderLayout.CENTER);
    }

    private JButton createAlphabetBtn(char c) {
        JButton btn = new JButton(String.valueOf(c));
        btn.setFont(new Font("SansSerif", Font.BOLD, 22));
        btn.setForeground(new Color(30, 40, 60));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setPreferredSize(new Dimension(60, 60));
        btn.setBorder(new RoundedBorder(15, new Color(100, 100, 110), 1));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void showLetterDetails(char c, String pron, String ex) {
        JPanel detailsCard = new JPanel(new BorderLayout());
        detailsCard.setBackground(Color.WHITE);
        detailsCard.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel lblLetter = new JLabel("Letter: " + c);
        lblLetter.setFont(new Font("SansSerif", Font.BOLD, 30));
        lblLetter.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblType = new JLabel("Type: " + (pron != null ? pron : "N/A"));
        lblType.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lblType.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblExample = new JLabel("Example: " + (ex != null ? ex : "N/A"));
        lblExample.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lblExample.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnPronounce = new JButton("Pronounce");
        btnPronounce.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPronounce.addActionListener(e -> {
            btnPronounce.setText("Playing...");
            btnPronounce.setEnabled(false);
            Timer timer = new Timer(1000, evt -> {
                btnPronounce.setText("Pronounce");
                btnPronounce.setEnabled(true);
            });
            timer.setRepeats(false);
            timer.start();
        });

        infoPanel.add(lblLetter);
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(lblType);
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(lblExample);
        infoPanel.add(Box.createVerticalStrut(40));
        infoPanel.add(btnPronounce);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> cardLayout.show(mainPanel, "Grid"));
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(btnClose);

        detailsCard.add(infoPanel, BorderLayout.CENTER);
        detailsCard.add(bottomPanel, BorderLayout.SOUTH);

        mainPanel.add(detailsCard, "Details");
        cardLayout.show(mainPanel, "Details");
    }
}
