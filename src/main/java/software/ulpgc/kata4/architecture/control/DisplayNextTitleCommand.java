package software.ulpgc.kata4.architecture.control;

import software.ulpgc.kata4.architecture.view.TitleDisplayer;
import software.ulpgc.kata4.architecture.io.TitleLoader;

public class DisplayNextTitleCommand implements Command {
    private static int i = 0;
    private final TitleLoader loader;
    private final TitleDisplayer displayer;

    public DisplayNextTitleCommand(TitleLoader loader, TitleDisplayer displayer) {
        this.loader = loader;
        this.displayer = displayer;
    }

    @Override
    public void execute() {
        displayer.display(loader.load(++DisplayNextTitleCommand.i));
    }

    public static int getI() {
        return i;
    }
}
