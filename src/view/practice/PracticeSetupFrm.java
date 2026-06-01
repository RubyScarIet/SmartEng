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
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JComboBox<String> cbxMode;
    private JComboBox<ComboBoxItem> cbxTopic;
    private JComboBox<ComboBoxItem> cbxLesson;
    private JButton btnStart;

    private List<Object[]> questions;
    private int currentQuestionIndex = 0;
    
    // Quiz UI components
    private JLabel lblQuestion;
    private JPanel quizOptionsPanel;
    private JButton btnCheck;

    public PracticeSetupFrm() {
        setLayout(new BorderLayout());
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel setupCard = createSetupPanel();
        JPanel quizCard = createQuizPanel();

        mainPanel.add(setupCard, "Setup");
        mainPanel.add(quizCard, "Quiz");

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createSetupPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        JLabel lblTitle = new JLabel("Practice Setup", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(new Color(30, 40, 60));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        p.add(lblTitle, BorderLayout.NORTH);

        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        center.setBackground(Color.WHITE);
        center.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
        
        cbxMode = new JComboBox<>(new String[]{"Skill", "Vocabulary", "Grammar"});
        
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

        cbxTopic.setEnabled(false);
        cbxLesson.setEnabled(false);

        cbxMode.addActionListener(e -> {
            if (cbxMode.getSelectedIndex() > 0) {
                cbxTopic.setEnabled(true);
                loadTopics();
            } else {
                cbxTopic.removeAllItems();
                cbxTopic.addItem(new ComboBoxItem(0, "Topic"));
                cbxTopic.setEnabled(false);
                cbxLesson.removeAllItems();
                cbxLesson.addItem(new ComboBoxItem(0, "Lesson"));
                cbxLesson.setEnabled(false);
            }
        });

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
        questions = dao.getQuestionsByLesson(lesson.id);
        
        if(questions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có câu hỏi nào cho bài học này!");
            return;
        }
        
        currentQuestionIndex = 0;
        loadQuestionData();
        cardLayout.show(mainPanel, "Quiz");
    }

    private JPanel createQuizPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        lblQuestion = new JLabel("", SwingConstants.CENTER);
        lblQuestion.setFont(new Font("SansSerif", Font.BOLD, 20));
        p.add(lblQuestion, BorderLayout.NORTH);

        quizOptionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        quizOptionsPanel.setBackground(Color.WHITE);
        p.add(quizOptionsPanel, BorderLayout.CENTER);

        btnCheck = new JButton("Next Question");
        btnCheck.addActionListener(e -> {
            // Note: In a real app we check if answer is correct before moving next.
            // For simplicity here, we just move to the next question.
            currentQuestionIndex++;
            if(currentQuestionIndex >= questions.size()) {
                JOptionPane.showMessageDialog(this, "Practice Completed Successfully!");
                cbxMode.setSelectedIndex(0);
                cardLayout.show(mainPanel, "Setup");
            } else {
                loadQuestionData();
            }
        });
        JPanel bot = new JPanel(); 
        bot.setBackground(Color.WHITE);
        bot.add(btnCheck);
        p.add(bot, BorderLayout.SOUTH);
        
        return p;
    }

    private void loadQuestionData() {
        if(questions == null || currentQuestionIndex >= questions.size()) return;
        Object[] q = questions.get(currentQuestionIndex);
        lblQuestion.setText("Q" + (currentQuestionIndex+1) + ": " + (String)q[1]);
        String qType = (String)q[2];
        String optionsStr = (String)q[3];
        
        quizOptionsPanel.removeAll();
        
        if("MULTIPLE_CHOICE".equals(qType) || "WORD_ORDER".equals(qType)) {
            quizOptionsPanel.setLayout(new GridLayout(2, 2, 10, 10));
            if(optionsStr != null && optionsStr.length() > 4) {
                // simple json array parser: ["A","B","C"]
                String clean = optionsStr.substring(2, optionsStr.length()-2); // remove [" and "]
                String[] opts = clean.split("\",\"");
                for(String opt : opts) {
                    JButton btn = new JButton(opt);
                    btn.setBackground(new Color(245, 248, 250));
                    btn.setBorder(new view.admin.RoundedBorder(10, new Color(200, 200, 200), 1));
                    quizOptionsPanel.add(btn);
                }
            }
        } else {
            quizOptionsPanel.setLayout(new FlowLayout());
            JTextArea txt = new JTextArea(3, 20);
            txt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            txt.setFont(new Font("SansSerif", Font.PLAIN, 16));
            quizOptionsPanel.add(txt);
        }
        
        quizOptionsPanel.revalidate();
        quizOptionsPanel.repaint();
    }
}