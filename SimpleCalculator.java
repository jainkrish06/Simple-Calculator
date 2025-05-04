import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator {
    JFrame frame;
    JTextField textField;
    String operator = "";
    double num1 = 0, num2 = 0, result = 0;

    public SimpleCalculator() {
        frame = new JFrame("Calculator");
        textField = new JTextField();
        frame.setLayout(new BorderLayout());
        frame.add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        frame.add(panel, BorderLayout.CENTER);

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();
                    if (command.charAt(0) == 'C') {
                        textField.setText("");
                        num1 = num2 = result = 0;
                    } else if (command.charAt(0) == '=') {
                        num2 = Double.parseDouble(textField.getText());
                        switch (operator) {
                            case "+":
                                result = num1 + num2;
                                break;
                            case "-":
                                result = num1 - num2;
                                break;
                            case "*":
                                result = num1 * num2;
                                break;
                            case "/":
                                result = num1 / num2;
                                break;
                        }
                        textField.setText(String.valueOf(result));
                        num1 = result;
                        operator = "";
                    } else if ("+-*/".indexOf(command) >= 0) {
                        if (!operator.isEmpty()) {
                            return;
                        }
                        operator = command;
                        num1 = Double.parseDouble(textField.getText());
                        textField.setText("");
                    } else {
                        textField.setText(textField.getText() + command);
                    }
                }
            });
            panel.add(button);
        }

        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
