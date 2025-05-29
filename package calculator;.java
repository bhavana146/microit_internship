package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorGUI extends JFrame implements ActionListener {

    private JTextField display;
    private JPanel buttonPanel;

    private String[] buttonLabels = {
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        "0", ".", "C", "+"
    };

    private JButton[] buttons = new JButton[buttonLabels.length];

    private double result = 0;
    private String operator = "=";
    private boolean calculating = true;

    public CalculatorGUI() {
        setTitle("✨ Pretty Calculator ✨");
        setSize(380, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(30, 30, 30)); // dark background

        // Display Field
        display = new JTextField("0");
        display.setFont(new Font("Segoe UI", Font.BOLD, 36));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.setBackground(new Color(40, 40, 40));
        display.setForeground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(display, BorderLayout.NORTH);

        // Buttons Panel
        buttonPanel = new JPanel(new GridLayout(4, 4, 15, 15));
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFont(new Font("Segoe UI", Font.BOLD, 28));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);

            // Style operators differently
            if ("/*-+=C".contains(buttonLabels[i])) {
                buttons[i].setBackground(new Color(255, 140, 0)); // orange
                buttons[i].setForeground(Color.WHITE);
            } else {
                buttons[i].setBackground(new Color(60, 60, 60)); // dark gray for numbers
                buttons[i].setForeground(Color.WHITE);
            }

            // Round buttons a bit
            buttons[i].setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 2));
            buttonPanel.add(buttons[i]);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("0123456789.".contains(command)) {
            if (calculating) {
                if (command.equals("."))
                    display.setText("0.");
                else
                    display.setText(command);
            } else {
                // Prevent multiple decimals
                if (command.equals(".") && display.getText().contains("."))
                    return;
                display.setText(display.getText() + command);
            }
            calculating = false;
        } else if (command.equals("C")) {
            result = 0;
            operator = "=";
            display.setText("0");
            calculating = true;
        } else {
            if (calculating) {
                if (command.equals("-")) {
                    display.setText(command);
                    calculating = false;
                } else {
                    operator = command;
                }
            } else {
                double x = Double.parseDouble(display.getText());
                calculate(x);
                operator = command;
                calculating = true;
            }
        }
    }

    private void calculate(double n) {
        switch (operator) {
            case "+":
                result += n;
                break;
            case "-":
                result -= n;
                break;
            case "*":
                result *= n;
                break;
            case "/":
                if (n != 0) {
                    result /= n;
                } else {
                    JOptionPane.showMessageDialog(this, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                    result = 0;
                }
                break;
            case "=":
                result = n;
                break;
        }
        // Display result with no trailing .0 if integer
        if (result == (long) result)
            display.setText(String.format("%d", (long) result));
        else
            display.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorGUI());
    }
}