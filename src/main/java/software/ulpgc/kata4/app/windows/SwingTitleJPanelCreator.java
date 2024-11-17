package software.ulpgc.kata4.app.windows;

import javax.swing.*;
import java.awt.*;

public class SwingTitleJPanelCreator implements JPanelCreator {

    private final JPanel titlePane;

    public SwingTitleJPanelCreator() {
        this.titlePane = new JPanel();
        this.titlePane.add(titlePaneLabel());
    }

    private Component titlePaneLabel() {
        JLabel label = new JLabel("CAN YOU GUESS THE RELEASE DATE OF THIS GAME?");
        label.setFont(getFont());
        return label;
    }

    @Override
    public JPanel create() {
        return titlePane;
    }

    private Font getFont() {
        return new Font("Arial", Font.BOLD | Font.ITALIC, getAdaptedFontSize());
    }

    private int getAdaptedFontSize() {
        return Toolkit.getDefaultToolkit().getScreenSize().width / 50;
    }
}
