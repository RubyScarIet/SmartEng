package view.leaderboard;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.List;
import java.util.Arrays;

public class LeagueFrm extends JDialog {

    public LeagueFrm(Component parent) {
        super((Frame) SwingUtilities.getWindowAncestor(parent), "Các Bậc Xếp Hạng (Leagues)", true);
        setSize(300, 400);
        setLocationRelativeTo(parent);

        dao.LeagueDAO dao = new dao.LeagueDAO();
        List<String> leagues = dao.getAllLeagues();

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);

        if (leagues == null || leagues.isEmpty()) {
            leagues = Arrays.asList("Radiant", "Immortal", "Ascendant", "Diamond", "Platinum", "Gold",
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
        add(scroll);
    }
}
