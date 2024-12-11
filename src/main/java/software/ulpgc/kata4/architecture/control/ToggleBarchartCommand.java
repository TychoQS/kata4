package software.ulpgc.kata4.architecture.control;

import software.ulpgc.kata4.architecture.view.BarchartDisplay;
import software.ulpgc.kata4.architecture.io.BarchartLoader;

public class ToggleBarchartCommand implements Command{
    private final BarchartLoader loader;
    private final BarchartDisplay display;
    private int i = 1;

    public ToggleBarchartCommand(BarchartLoader loader, BarchartDisplay display) {
        this.loader = loader;
        this.display = display;
    }

    @Override
    public void execute() {
        display.show(loader.load(i++ %2));
    }
}
