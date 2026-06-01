package view.study;
import view.admin.RoundedBorder;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class StudyFrm extends JPanel {
    private int currentUnlockedLevel = 1;
    private JPanel roadmapPanel;
    public StudyFrm() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.WHITE);
        JLabel lblTitle = new JLabel("Study", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(new Color(30, 40, 60));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setBorder(new EmptyBorder(20, 0, 10, 0));
        topPanel.add(lblTitle);
        JLabel lblTopic = new JLabel("  FOOD AND DRINK  ", SwingConstants.CENTER);
        lblTopic.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblTopic.setForeground(new Color(50, 50, 60));
        lblTopic.setBorder(new RoundedBorder(15, new Color(100, 100, 110)));
        lblTopic.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(lblTopic);
        topPanel.add(Box.createVerticalStrut(20));
        add(topPanel, BorderLayout.NORTH);
        roadmapPanel = new JPanel();
        roadmapPanel.setLayout(new BoxLayout(roadmapPanel, BoxLayout.Y_AXIS));
        roadmapPanel.setBackground(Color.WHITE);
        JPanel outerRoadmap = new JPanel(new BorderLayout());
        outerRoadmap.setBackground(Color.WHITE);
        outerRoadmap.setBorder(new RoundedBorder(20, new Color(100, 100, 110), 1));
        JScrollPane scrollPane = new JScrollPane(roadmapPanel);
        scrollPane.setBorder(null);
        outerRoadmap.add(scrollPane, BorderLayout.CENTER);
        JPanel outerWrapper = new JPanel(new BorderLayout());
        outerWrapper.setBackground(Color.WHITE);
        outerWrapper.setBorder(new EmptyBorder(0, 40, 20, 40));
        outerWrapper.add(outerRoadmap, BorderLayout.CENTER);
        add(outerWrapper, BorderLayout.CENTER);
        renderRoadmap();
    }
    private void renderRoadmap() {
        roadmapPanel.removeAll();
        roadmapPanel.add(Box.createVerticalStrut(30));
        roadmapPanel.add(createNode("L1", 1, 50));
        roadmapPanel.add(Box.createVerticalStrut(30));
        roadmapPanel.add(createNode("L2", 2, -50));
        JLabel lblSection = new JLabel("  TRAVEL  ");
        lblSection.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblSection.setForeground(new Color(50, 50, 60));
        lblSection.setBorder(new RoundedBorder(15, new Color(100, 100, 110)));
        lblSection.setAlignmentX(Component.CENTER_ALIGNMENT);
        roadmapPanel.add(Box.createVerticalStrut(30));
        roadmapPanel.add(lblSection);
        roadmapPanel.add(Box.createVerticalStrut(30));
        roadmapPanel.add(createNode("L3", 3, 50));
        roadmapPanel.add(Box.createVerticalStrut(30));
        roadmapPanel.add(createNode("L4", 4, -50));
        roadmapPanel.add(Box.createVerticalStrut(30));
        roadmapPanel.revalidate();
        roadmapPanel.repaint();
    }
    private JPanel createNode(String name, int levelIndex, int xOffset) {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapper.setBackground(Color.WHITE);
        if (xOffset > 0) wrapper.add(Box.createHorizontalStrut(xOffset));
        JButton btn = new JButton(name);
        btn.setPreferredSize(new Dimension(60, 60));
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn.setForeground(new Color(30, 40, 60));
        boolean unlocked = levelIndex <= currentUnlockedLevel;
        btn.setBackground(unlocked ? Color.WHITE : new Color(245, 245, 245));
        btn.setEnabled(unlocked);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorder(new RoundedBorder(30, new Color(100, 100, 110), 2));
        if(unlocked) btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Completed " + name + "!");
            if (levelIndex == currentUnlockedLevel) {
                currentUnlockedLevel++;
                renderRoadmap();
            }
        });
        wrapper.add(btn);
        if (xOffset < 0) wrapper.add(Box.createHorizontalStrut(-xOffset));
        return wrapper;
    }
}
