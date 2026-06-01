package view.study;

import dao.ProfileDAO;
import dao.QuestionDAO;
import dao.StudyProgressDAO;
import view.admin.RoundedBorder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class LessonQuizFrm extends JDialog {
    private List<Object[]> questions;
    private int currentIndex = 0;
    private int correctCount = 0;
    private int heartsLost = 0;
    private int lessonId;
    
    // Giao diện
    private JLabel lblQuestion;
    private JPanel optionsPanel;
    private ButtonGroup btnGroup;
    private JButton btnCheck;

    public LessonQuizFrm(Frame parent, int lessonId, String lessonName) {
        super(parent, "Đang học: " + lessonName, true); // Modal Dialog
        this.lessonId = lessonId;
        setSize(500, 450);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Fetch Data from DB
        QuestionDAO qDao = new QuestionDAO();
        questions = qDao.getQuestionsByLesson(lessonId);

        if (questions == null || questions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bài học này hiện chưa có câu hỏi trong ngân hàng dữ liệu!");
            dispose();
            return;
        }

        buildUI();
        loadQuestionData();
    }

    private void buildUI() {
        JPanel pnlCenter = new JPanel(new BorderLayout(0, 20));
        pnlCenter.setBackground(Color.WHITE);
        pnlCenter.setBorder(new EmptyBorder(30, 30, 30, 30));

        lblQuestion = new JLabel("Câu hỏi", SwingConstants.CENTER);
        lblQuestion.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblQuestion.setForeground(new Color(30, 40, 60));
        pnlCenter.add(lblQuestion, BorderLayout.NORTH);

        optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        optionsPanel.setBackground(Color.WHITE);
        pnlCenter.add(optionsPanel, BorderLayout.CENTER);

        add(pnlCenter, BorderLayout.CENTER);

        JPanel pnlBot = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 20));
        pnlBot.setBackground(Color.WHITE);
        btnCheck = new JButton("Kiểm tra (Check)");
        btnCheck.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnCheck.setPreferredSize(new Dimension(150, 40));
        btnCheck.setBackground(new Color(120, 240, 160));
        btnCheck.setBorder(new RoundedBorder(20, new Color(100, 200, 140), 1));
        btnCheck.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnCheck.addActionListener(e -> checkAnswer());
        pnlBot.add(btnCheck);
        add(pnlBot, BorderLayout.SOUTH);
    }

    private void loadQuestionData() {
        if (currentIndex >= questions.size()) {
            finishLesson();
            return;
        }

        Object[] q = questions.get(currentIndex);
        lblQuestion.setText("Q" + (currentIndex + 1) + ": " + (String) q[1]);
        String optionsStr = (String) q[3];

        optionsPanel.removeAll();
        btnGroup = new ButtonGroup();

        // Parse JSON String ["A", "B", "C", "D"] from database
        if (optionsStr != null && optionsStr.length() > 4) {
            String clean = optionsStr.substring(2, optionsStr.length() - 2);
            String[] opts = clean.split("\",\"");
            for (String opt : opts) {
                JRadioButton radio = new JRadioButton(opt);
                radio.setFont(new Font("SansSerif", Font.PLAIN, 16));
                radio.setBackground(Color.WHITE);
                btnGroup.add(radio);
                optionsPanel.add(radio);
            }
        }
        optionsPanel.revalidate();
        optionsPanel.repaint();
    }

    private void checkAnswer() {
        String selectedAnswer = null;
        for (Component c : optionsPanel.getComponents()) {
            if (c instanceof JRadioButton) {
                JRadioButton rb = (JRadioButton) c;
                if (rb.isSelected()) selectedAnswer = rb.getText();
            }
        }

        if (selectedAnswer == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một đáp án!");
            return;
        }

        Object[] q = questions.get(currentIndex);
        String correctAnswer = (String) q[4];

        if (selectedAnswer.equals(correctAnswer)) {
            JOptionPane.showMessageDialog(this, "Chính xác! (+EXP)");
            correctCount++;
        } else {
            JOptionPane.showMessageDialog(this, "Sai rồi! Đáp án đúng là: " + correctAnswer);
            heartsLost++;
            if (heartsLost >= 5) {
                JOptionPane.showMessageDialog(this, "Bạn đã hết Tim! Hãy quay lại sau.");
                dispose();
                return;
            }
        }

        currentIndex++;
        loadQuestionData();
    }

    private void finishLesson() {
        int earnedExp = correctCount * 10;
        if (correctCount == questions.size()) earnedExp += 5; // Thưởng Combo hoàn hảo

        // Cập nhật CSDL (giả sử userId đang đăng nhập là 1 theo code gốc)
        int userId = 1; 
        
        ProfileDAO pDao = new ProfileDAO();
        pDao.updateLearningStats(userId, earnedExp, heartsLost);
        
        StudyProgressDAO spDao = new StudyProgressDAO();
        spDao.saveProgress(1, lessonId); // profileId = 1, lessonId

        JOptionPane.showMessageDialog(this, 
            "BÀI HỌC HOÀN TẤT!\nKinh nghiệm: +" + earnedExp + " EXP\nTim mất: " + heartsLost, 
            "Hoàn thành", JOptionPane.INFORMATION_MESSAGE);
        
        dispose(); // Đóng popup học, quay về màn Cây kỹ năng
    }
}