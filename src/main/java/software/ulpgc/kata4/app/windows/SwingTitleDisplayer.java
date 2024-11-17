package software.ulpgc.kata4.app.windows;

import software.ulpgc.kata4.architecture.model.Title;
import software.ulpgc.kata4.architecture.view.TitleDisplayer;

import javax.swing.*;
import java.awt.*;

public class SwingTitleDisplayer extends JLabel implements TitleDisplayer {

    public SwingTitleDisplayer() {
        this.setFont(new Font("Arial", Font.PLAIN, 40));
    }

    @Override
    public void display(Title title) {
        this.setText(title.title());
    }
}
