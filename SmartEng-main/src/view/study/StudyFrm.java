package view.study;

import dao.LessonDAO;
import dao.TopicDAO;
import view.admin.RoundedBorder;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.List;

public class StudyFrm extends JPanel {
    private JPanel roadmapPanel;

    public StudyFrm() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.WHITE);
        
        JLabel lblTitle = new JLabel("Lộ trình học tập (Skill Tree)", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setForeground(new Color(30, 40, 60));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setBorder(new EmptyBorder(20, 0, 20, 0));
        topPanel.add(lblTitle);

        add(topPanel, BorderLayout.NORTH);

        roadmapPanel = new JPanel();
        roadmapPanel.setLayout(new BoxLayout(roadmapPanel, BoxLayout.Y_AXIS));
        roadmapPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(roadmapPanel);
        scrollPane.setBorder(new RoundedBorder(20, new Color(200, 200, 200), 1));
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Scroll nhanh hơn

        JPanel outerWrapper = new JPanel(new BorderLayout());
        outerWrapper.setBackground(Color.WHITE);
        outerWrapper.setBorder(new EmptyBorder(0, 40, 20, 40));
        outerWrapper.add(scrollPane, BorderLayout.CENTER);

        add(outerWrapper, BorderLayout.CENTER);

        // Nạp dữ liệu thực tế từ Database
        loadDataFromDatabase();
    }

    public void loadDataFromDatabase() {
        roadmapPanel.removeAll();
        TopicDAO tDao = new TopicDAO();
        LessonDAO lDao = new LessonDAO();

        List<Object[]> topics = tDao.getAllTopics(); // Lấy Chủ đề

        for (Object[] topicRow : topics) {
            int topicId = (Integer) topicRow[0];
            String topicName = (String) topicRow[1];

            // Render Tên Topic
            JLabel lblTopic = new JLabel("  " + topicName.toUpperCase() + "  ", SwingConstants.CENTER);
            lblTopic.setFont(new Font("SansSerif", Font.BOLD, 14));
            lblTopic.setForeground(new Color(50, 50, 60));
            lblTopic.setBorder(new RoundedBorder(15, new Color(100, 100, 110)));
            lblTopic.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            roadmapPanel.add(Box.createVerticalStrut(30));
            roadmapPanel.add(lblTopic);
            roadmapPanel.add(Box.createVerticalStrut(20));

            // Lấy các bài học (Lessons) thuộc Topic này
            List<Object[]> lessons = lDao.getLessonsByTopic(topicId);
            int xOffset = 60; // Tạo hiệu ứng ZigZag giống Duolingo
            
            for (int i = 0; i < lessons.size(); i++) {
                Object[] lessonRow = lessons.get(i);
                int lessonId = (Integer) lessonRow[0];
                String lessonName = (String) lessonRow[1];

                roadmapPanel.add(createNode(lessonId, lessonName, (i % 2 == 0) ? xOffset : -xOffset));
                roadmapPanel.add(Box.createVerticalStrut(20));
            }
        }
        roadmapPanel.revalidate();
        roadmapPanel.repaint();
    }

    private JPanel createNode(int lessonId, String lessonName, int xOffset) {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapper.setBackground(Color.WHITE);
        
        if (xOffset > 0) wrapper.add(Box.createHorizontalStrut(xOffset));

        JButton btn = new JButton("★");
        btn.setToolTipText(lessonName);
        btn.setPreferredSize(new Dimension(65, 65));
        btn.setFont(new Font("SansSerif", Font.BOLD, 20));
        btn.setForeground(new Color(30, 40, 60));
        btn.setBackground(new Color(245, 245, 250)); // Màu bài học chưa học
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorder(new RoundedBorder(35, new Color(100, 100, 110), 2));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Khi bấm vào bài học sẽ mở Cửa sổ Quiz
        btn.addActionListener(e -> {
            Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
            LessonQuizFrm quizFrm = new LessonQuizFrm(parentFrame, lessonId, lessonName);
            quizFrm.setVisible(true); // Luồng sẽ dừng ở đây cho đến khi User đóng popup
            
            // Đổi màu nút thành Đã học (Màu vàng) sau khi popup đóng lại
            btn.setBackground(new Color(255, 215, 0));
            btn.setForeground(Color.WHITE);
            btn.setBorder(new RoundedBorder(35, new Color(218, 165, 32), 2));
        });

        wrapper.add(btn);
        if (xOffset < 0) wrapper.add(Box.createHorizontalStrut(-xOffset));

        return wrapper;
    }
}