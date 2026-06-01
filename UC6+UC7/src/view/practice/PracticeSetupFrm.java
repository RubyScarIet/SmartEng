package view.practice;

import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

import dao.TopicDAO;
import dao.LessonDAO;
import dao.QuestionDAO;

class ComboBoxItem {
    int id;
    String label;
    public ComboBoxItem(int id, String label) { this.id = id; this.label = label; }
    @Override public String toString() { return label; }
}

public class PracticeSetupFrm extends JPanel {
    private JComboBox<String> cbxMode;
    private JComboBox<ComboBoxItem> cbxTopic;
    private JComboBox<ComboBoxItem> cbxLesson;
    private JButton btnStart;

    public PracticeSetupFrm() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 248, 250));

        add(createSetupPanel(), BorderLayout.CENTER);
    }

    private JPanel createSetupPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(new Color(245, 248, 250));

        JLabel title = new JLabel("PRACTICE", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(new Color(30, 40, 60));
        title.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        p.add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        center.setBackground(Color.WHITE);
        center.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
        
        cbxMode = new JComboBox<>(new String[]{"Skill", "Multiple Choice", "Speaking"});
        
        cbxTopic = new JComboBox<>();
        cbxTopic.addItem(new ComboBoxItem(0, "Topic"));
        
        cbxLesson = new JComboBox<>();
        cbxLesson.addItem(new ComboBoxItem(0, "Lesson"));

        JComboBox[] cbxs = {cbxMode, cbxTopic, cbxLesson};
        for(JComboBox cbx : cbxs) {
            cbx.setFont(new Font("SansSerif", Font.PLAIN, 14));
            cbx.setForeground(new Color(100, 110, 120));
            cbx.setBackground(Color.WHITE);
            cbx.setPreferredSize(new Dimension(150, 40));
            ((JComponent)cbx.getRenderer()).setBorder(new javax.swing.border.EmptyBorder(0, 10, 0, 10));
        }

        cbxTopic.setEnabled(true);
        cbxLesson.setEnabled(false);
        loadTopics();

        cbxTopic.addActionListener(e -> {
            ComboBoxItem selectedTopic = (ComboBoxItem) cbxTopic.getSelectedItem();
            if (selectedTopic != null && selectedTopic.id > 0) {
                cbxLesson.setEnabled(true);
                loadLessons(selectedTopic.id);
            } else {
                cbxLesson.removeAllItems();
                cbxLesson.addItem(new ComboBoxItem(0, "Lesson"));
                cbxLesson.setEnabled(false);
            }
        });
        
        btnStart = new JButton("Start");
        btnStart.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnStart.setForeground(new Color(100, 110, 120));
        btnStart.setBackground(Color.WHITE);
        btnStart.setFocusPainted(false);
        btnStart.setContentAreaFilled(false);
        btnStart.setOpaque(true);
        btnStart.setPreferredSize(new Dimension(100, 35));
        btnStart.setBorder(new view.admin.RoundedBorder(15, new Color(100, 100, 110), 1));
        btnStart.setEnabled(false);
        
        cbxLesson.addActionListener(e -> {
            ComboBoxItem selectedLesson = (ComboBoxItem) cbxLesson.getSelectedItem();
            boolean enabled = selectedLesson != null && selectedLesson.id > 0;
            btnStart.setEnabled(enabled);
            if(enabled) {
                btnStart.setForeground(new Color(30, 40, 60));
                btnStart.setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else {
                btnStart.setForeground(new Color(100, 110, 120));
                btnStart.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        center.add(cbxMode);
        center.add(cbxTopic);
        center.add(cbxLesson);
        p.add(center, BorderLayout.CENTER);

        btnStart.addActionListener(e -> startPractice());
        JPanel bot = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 20));
        bot.setBackground(Color.WHITE);
        bot.add(btnStart);
        p.add(bot, BorderLayout.SOUTH);
        return p;
    }

    private void loadTopics() {
        cbxTopic.removeAllItems();
        cbxTopic.addItem(new ComboBoxItem(0, "Topic"));
        TopicDAO dao = new TopicDAO();
        List<Object[]> topics = dao.getAllTopics();
        for(Object[] row : topics) {
            cbxTopic.addItem(new ComboBoxItem((Integer)row[0], (String)row[1]));
        }
    }

    private void loadLessons(int topicID) {
        cbxLesson.removeAllItems();
        cbxLesson.addItem(new ComboBoxItem(0, "Lesson"));
        LessonDAO dao = new LessonDAO();
        List<Object[]> lessons = dao.getLessonsByTopic(topicID);
        for(Object[] row : lessons) {
            cbxLesson.addItem(new ComboBoxItem((Integer)row[0], (String)row[1]));
        }
    }

    private void startPractice() {
        ComboBoxItem lesson = (ComboBoxItem) cbxLesson.getSelectedItem();
        if(lesson == null || lesson.id == 0) return;
        
        QuestionDAO dao = new QuestionDAO();
        List<Object[]> allQuestions = dao.getQuestionsByLesson(lesson.id);
        
        String mode = (String) cbxMode.getSelectedItem();
        List<Object[]> questions = new ArrayList<>();
        for (Object[] q : allQuestions) {
            String qType = (String) q[2];
            if ("Skill".equals(mode) && "Skill".equalsIgnoreCase(qType)) {
                questions.add(q);
            } else if ("Multiple Choice".equals(mode) && "MULTIPLE_CHOICE".equalsIgnoreCase(qType)) {
                questions.add(q);
            } else if ("Speaking".equals(mode) && "SPEAKING".equalsIgnoreCase(qType)) {
                questions.add(q);
            }
        }
        
        if(questions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có câu hỏi nào cho bài học này ở chế độ " + mode + "!");
            return;
        }
        
        
        new PracticeExecutionFrm(questions, lesson, mode);
    }
}