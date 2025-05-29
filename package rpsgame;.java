package rpsgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RockPaperScissorsGUI extends JFrame implements ActionListener {

    private JLabel computerChoiceLabel;
    private JLabel resultLabel;

    public RockPaperScissorsGUI() {
        setTitle("🎮 Rock Paper Scissors");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(new Color(34, 34, 34));
        setLayout(new BorderLayout(10, 10));

        // Title Label
        JLabel titleLabel = new JLabel("Choose Rock, Paper or Scissors", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.ORANGE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(34, 34, 34));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        String[] choices = {"Rock", "Paper", "Scissors"};
        for (String choice : choices) {
            JButton btn = new JButton(choice);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
            btn.setBackground(new Color(70, 70, 70));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.addActionListener(this);
            buttonPanel.add(btn);
        }
        add(buttonPanel, BorderLayout.CENTER);

        // Result Panel
        JPanel resultPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        resultPanel.setBackground(new Color(34, 34, 34));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        computerChoiceLabel = new JLabel("Computer chose: ", SwingConstants.CENTER);
        computerChoiceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        computerChoiceLabel.setForeground(Color.WHITE);

        resultLabel = new JLabel("Make your move!", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        resultLabel.setForeground(Color.CYAN);

        resultPanel.add(computerChoiceLabel);
        resultPanel.add(resultLabel);

        add(resultPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userChoice = e.getActionCommand();
        String[] options = {"Rock", "Paper", "Scissors"};

        // Randomly choose computer move
        int compIndex = (int) (Math.random() * 3);
        String computerChoice = options[compIndex];

        computerChoiceLabel.setText("Computer chose: " + computerChoice);

        // Decide winner
        String result = getResult(userChoice, computerChoice);
        resultLabel.setText(result);
    }

    private String getResult(String user, String computer) {
        if (user.equals(computer)) {
            return "It's a Tie!";
        }
        switch (user) {
            case "Rock":
                return (computer.equals("Scissors")) ? "You Win! Rock crushes Scissors." : "You Lose! Paper covers Rock.";
            case "Paper":
                return (computer.equals("Rock")) ? "You Win! Paper covers Rock." : "You Lose! Scissors cuts Paper.";
            case "Scissors":
                return (computer.equals("Paper")) ? "You Win! Scissors cuts Paper." : "You Lose! Rock crushes Scissors.";
            default:
                return "Error!";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RockPaperScissorsGUI());
    }
}