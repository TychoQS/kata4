package software.ulpgc.kata4.app.windows;

import software.ulpgc.kata4.architecture.control.Command;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SwingPlayerAnswerJPanelCreator implements JPanelCreator {
    private final Map<String, Command> commands;
    private final JPanel pane;

    public JTextField getTextField() {
        return textField;
    }

    private final JTextField textField;

    public SwingPlayerAnswerJPanelCreator(Map<String, Command> commands) {
        this.commands = commands;
        this.textField = new JTextField(4);
        this.pane = new JPanel(new BorderLayout());
        this.pane.add(BorderLayout.NORTH, centerPane());
        this.pane.add(BorderLayout.CENTER, buttonPane());
    }

    private Component buttonPane() {
        JPanel buttonPane = new JPanel();
        buttonPane.add(validateButton());
        buttonPane.add(anotherTitleButton());
        return buttonPane;
    }

    @Override
    public JPanel create() {
        return pane;
    }

    private Component anotherTitleButton() {
        JButton button = new JButton("Another title");
        button.addActionListener(e -> commands.get("another-title").execute());
        return button;
    }
    private Component validateButton() {
        JButton button = new JButton("Validate");
        button.addActionListener(e -> commands.get("validate").execute());
        return button;
    }

    private Component centerPane() {
        JPanel centerPane = new JPanel();
        centerPane.add(new JLabel("Your guess: "));
        centerPane.add(textField);
        return centerPane;
    }
}
