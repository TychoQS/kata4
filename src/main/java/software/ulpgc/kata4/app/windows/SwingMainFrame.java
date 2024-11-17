package software.ulpgc.kata4.app.windows;

import software.ulpgc.kata4.architecture.control.Command;
import software.ulpgc.kata4.architecture.view.TitleDisplayer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SwingMainFrame extends JFrame {
    private final Map<String, Command> commands;
    private SwingMovieNameJPanelCreator movieNameJPanelCreator;
    private SwingPlayerAnswerJPanelCreator playerAnswerJPanelCreator;

    public SwingMainFrame() {
        commands = new HashMap<>();
        this.setTitle("Guess the release date game");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.add(titlePane());
        this.add(mainPane());
    }

    public Command put(String key, Command value) {
        return commands.put(key, value);
    }

    public TitleDisplayer getTitleDisplayer() {
        return movieNameJPanelCreator.getSwingTitleDisplayer();
    }

    public JTextField getTextField() {
        return playerAnswerJPanelCreator.getTextField();
    }

    private Component mainPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(movieNamePanel());
        panel.add(playerAnswerPanel());
        return panel;
    }

    private Component playerAnswerPanel() {
        this.playerAnswerJPanelCreator = new SwingPlayerAnswerJPanelCreator(commands);
        return this.playerAnswerJPanelCreator.create();
    }

    private Component movieNamePanel() {
        this.movieNameJPanelCreator = new SwingMovieNameJPanelCreator();
        return this.movieNameJPanelCreator.create();
    }

    private Component titlePane() {
        return new SwingTitleJPanelCreator().create();
    }
}
