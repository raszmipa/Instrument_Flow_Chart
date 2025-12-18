package com.sample;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    private DroolsLogic logic;
    private JLabel questionLabel;
    private JPanel answersPanel;

    public Gui() {
        
        super("System Ekspercki: Wybór Instrumentu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());

        
        logic = new DroolsLogic();
        logic.fireRules();

        
        questionLabel = new JLabel("Ładowanie...", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(questionLabel, BorderLayout.NORTH);

        answersPanel = new JPanel();
        answersPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Przyciski obok siebie
        add(answersPanel, BorderLayout.CENTER);
        updateView();
    }

    private void updateView() {
        answersPanel.removeAll();

        InstrumentCandidate result = logic.getRecommendedInstrument();
        if (result != null) {
            showResult(result);
            return;
        }

        Question currentQuestion = logic.getCurrentQuestion();
        if (currentQuestion != null) {
            questionLabel.setText("<html><center>" + currentQuestion.getQuestionId() + "</center></html>");

            for (String answerText : currentQuestion.getPossibleAnswers()) {
                JButton button = new JButton(answerText);
                button.setFont(new Font("Arial", Font.PLAIN, 14));
                button.setPreferredSize(new Dimension(120, 50));

                button.addActionListener(e -> {
                    logic.submitAnswer(currentQuestion, answerText);
                    updateView();
                });

                answersPanel.add(button);
            }
        } else {
            questionLabel.setText("Nie znaleziono instrumentu.");
        }

        answersPanel.revalidate();
        answersPanel.repaint();
    }

    private void showResult(InstrumentCandidate instrument) {
        questionLabel.setText("Polecany instrument:");
        
        JLabel resultLabel = new JLabel(instrument.getName());
        resultLabel.setFont(new Font("Arial", Font.BOLD, 32));
        resultLabel.setForeground(new Color(0, 100, 0));
        
        answersPanel.setLayout(new GridBagLayout());
        answersPanel.add(resultLabel);
        
        answersPanel.revalidate();
        answersPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Gui().setVisible(true);
        });
    }
}