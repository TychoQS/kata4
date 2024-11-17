package software.ulpgc.kata4.architecture.control;

import software.ulpgc.kata4.architecture.model.AnswerValidator;
import software.ulpgc.kata4.architecture.view.DialogDisplayer;
import software.ulpgc.kata4.architecture.io.TextInput;
import software.ulpgc.kata4.architecture.io.DatabaseTitleLoader;

public class ValidateCommand implements Command {
    private final TextInput input;
    private final DatabaseTitleLoader loader;
    private final DialogDisplayer displayer;

    public ValidateCommand(TextInput input, DatabaseTitleLoader loader, DialogDisplayer displayer) {
        this.input = input;
        this.loader = loader;
        this.displayer = displayer;
    }


    @Override
    public void execute() {
        System.out.println("Titulo que le llega a validate command: " + loader.load(DisplayNextTitleCommand.getI()));
        if (AnswerValidator.validate(input.getText(), loader.load(DisplayNextTitleCommand.getI()))) {
            displayer.display("You are right!", 1);
        } else {
            displayer.display("You are wrong", 0);
        }
    }
}
