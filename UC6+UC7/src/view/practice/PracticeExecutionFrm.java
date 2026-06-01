package view.practice;

import java.awt.*;
import javax.swing.*;
import java.util.List;

public class PracticeExecutionFrm extends JFrame {
    private List<Object[]> questions;
    private int currentQuestionIndex = 0;
    private int correctCount = 0;
    private boolean answered = false;

    private JLabel lblQuestion;
    private JPanel quizOptionsPanel;
    private JButton btnCheck;
    private JProgressBar progressBar;

    private ComboBoxItem lesson;
    private String mode;

    public PracticeExecutionFrm(List<Object[]> questions, ComboBoxItem lesson, String mode) {
        super("Practice - " + mode);
        this.questions = questions;
        this.lesson = lesson;
        this.mode = mode;
        
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setContentPane(createQuizPanel());

        if (progressBar != null) {
            progressBar.setMaximum(questions.size());
            progressBar.setValue(0);
            progressBar.setString("1 / " + questions.size());
        }
        
        loadQuestionData();
        setVisible(true);
    }

    private JPanel createQuizPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel topPanel = new JPanel(new BorderLayout(0, 10));
        topPanel.setBackground(Color.WHITE);
        
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(67, 200, 130));
        topPanel.add(progressBar, BorderLayout.NORTH);

        lblQuestion = new JLabel("", SwingConstants.CENTER);
        lblQuestion.setFont(new Font("SansSerif", Font.BOLD, 20));
        topPanel.add(lblQuestion, BorderLayout.CENTER);
        
        p.add(topPanel, BorderLayout.NORTH);

        quizOptionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        quizOptionsPanel.setBackground(Color.WHITE);
        p.add(quizOptionsPanel, BorderLayout.CENTER);

        btnCheck = new JButton("Next");
        btnCheck.setEnabled(false);
        btnCheck.addActionListener(e -> {
            if(!answered && quizOptionsPanel.getComponentCount() > 0 && quizOptionsPanel.getComponent(0) instanceof JTextArea) {
                JTextArea txt = (JTextArea) quizOptionsPanel.getComponent(0);
                Object[] q = questions.get(currentQuestionIndex);
                String correctAnswer = (String) q[4];
                
                dao.QuestionDAO qDao = new dao.QuestionDAO();
                if (qDao.checkAnswer(txt.getText(), correctAnswer)) {
                    correctCount++;
                }
                answered = true;
            }
            
            currentQuestionIndex++;
            if (progressBar != null) {
                progressBar.setValue(currentQuestionIndex);
                if (currentQuestionIndex < questions.size()) {
                    progressBar.setString((currentQuestionIndex + 1) + " / " + questions.size());
                } else {
                    progressBar.setString(questions.size() + " / " + questions.size());
                }
            }
            if(currentQuestionIndex >= questions.size()) {
                double pct = questions.size() > 0 ? (correctCount * 100.0 / questions.size()) : 0;
                boolean pass = pct >= 60.0;
                
                if (lesson != null && lesson.id > 0) {
                    dao.StudyProgressDAO spDao = new dao.StudyProgressDAO();
                    model.StudyProgress sp = new model.StudyProgress();
                    model.Profile profile = new model.Profile();
                    profile.setId(1); 
                    sp.setProfile(profile);
                    model.Lesson l = new model.Lesson();
                    l.setId(lesson.id);
                    sp.setLesson(l);
                    sp.setCompletionPercentage(String.format("%.1f", pct).replace(",", "."));
                    sp.setStatus(pass);
                    if (pass) {
                        sp.setCompletedAt(new java.util.Date());
                    }
                    spDao.upsertProgress(sp);

                    if (pass && correctCount > 0) {
                        dao.ProfileDAO pDao = new dao.ProfileDAO();
                        pDao.addEXP(1, correctCount * 10);
                        if (pct >= 100.0) {
                            pDao.updateHeart(1, 1);
                        }
                    }
                }

                String msg = "Practice Completed!\nScore: " + correctCount + "/" + questions.size() + " (" + String.format("%.1f", pct) + "%)";
                String[] options = {"Continue"};
                JOptionPane.showOptionDialog(this, msg, "Message", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                
                this.dispose(); 
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
        answered = false;
        btnCheck.setEnabled(false);
        Object[] q = questions.get(currentQuestionIndex);
        lblQuestion.setText("Q" + (currentQuestionIndex+1) + ": " + (String)q[1]);
        String qType = (String)q[2];
        String optionsStr = (String)q[3];
        String correctAnswer = (String)q[4];
        
        quizOptionsPanel.removeAll();
        
        if("MULTIPLE_CHOICE".equals(qType) || "WORD_ORDER".equals(qType)) {
            quizOptionsPanel.setLayout(new GridLayout(2, 2, 10, 10));
            if(optionsStr != null && optionsStr.length() > 4) {
                String clean = optionsStr.substring(2, optionsStr.length()-2); 
                String[] opts = clean.split("\",\"");
                for(String opt : opts) {
                    JButton btn = new JButton(opt);
                    btn.setBackground(new Color(245, 248, 250));
                    btn.setBorder(new view.admin.RoundedBorder(10, new Color(200, 200, 200), 1));
                    btn.addActionListener(e -> {
                        if(answered) return;
                        answered = true;
                        btnCheck.setEnabled(true);
                        boolean correct = false;
                        dao.QuestionDAO qDao = new dao.QuestionDAO();
                        if(qDao.checkAnswer(opt, correctAnswer)) {
                            correct = true;
                            correctCount++;
                        }
                        for(Component c : quizOptionsPanel.getComponents()) {
                            if(c instanceof JButton) {
                                JButton b = (JButton) c;
                                String text = b.getText().trim();
                                if(qDao.checkAnswer(text, correctAnswer)) {
                                    b.setBackground(new Color(200, 245, 215)); 
                                } else if(b == btn && !correct) {
                                    b.setBackground(new Color(255, 205, 200)); 
                                }
                            }
                        }
                    });
                    quizOptionsPanel.add(btn);
                }
            }
        } else if ("SPEAKING".equals(qType)) {
            quizOptionsPanel.setLayout(new FlowLayout());
            JButton btnMic = new JButton("🎤");
            btnMic.setFont(new Font("SansSerif", Font.PLAIN, 40));
            btnMic.setPreferredSize(new Dimension(80, 80));
            btnMic.setBackground(new Color(245, 248, 250));
            btnMic.setBorder(new view.admin.RoundedBorder(40, new Color(200, 200, 200), 1));
            btnMic.setFocusPainted(false);
            btnMic.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnMic.addActionListener(e -> {
                if(answered) return;
                answered = true;
                correctCount++;
                btnMic.setBackground(new Color(200, 245, 215));
                btnCheck.setEnabled(true);
            });
            quizOptionsPanel.add(btnMic);
            btnCheck.setEnabled(false); 
        } else {
            quizOptionsPanel.setLayout(new FlowLayout());
            JTextArea txt = new JTextArea(3, 20);
            txt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            txt.setFont(new Font("SansSerif", Font.PLAIN, 16));
            quizOptionsPanel.add(txt);
            btnCheck.setEnabled(true);
        }
        
        quizOptionsPanel.revalidate();
        quizOptionsPanel.repaint();
    }
}