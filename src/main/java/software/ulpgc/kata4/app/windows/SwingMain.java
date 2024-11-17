package software.ulpgc.kata4.app.windows;

import software.ulpgc.kata4.architecture.control.DisplayNextTitleCommand;
import software.ulpgc.kata4.architecture.io.TitleLoader;
import software.ulpgc.kata4.architecture.control.ValidateCommand;
import software.ulpgc.kata4.architecture.io.DatabaseTitleLoader;
import software.ulpgc.kata4.app.io.sqlite.DatabaseTitlesReader;
import software.ulpgc.kata4.app.io.sqlite.ImportCommand;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class SwingMain {
    public static void main(String[] args) {
        ImportCommand importCommand = new ImportCommand();
        importCommand.execute();
        TitleLoader loader = null;
        try {
            loader = new DatabaseTitleLoader(new DatabaseTitlesReader(new File("titles.db")).read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        SwingMainFrame mainFrame = new SwingMainFrame();
        mainFrame.put("another-title", new DisplayNextTitleCommand(loader, mainFrame.getTitleDisplayer()));
        mainFrame.put("validate", new ValidateCommand(new SwingTextInput(mainFrame.getTextField()), (DatabaseTitleLoader) loader, new SwingValidateDialogDisplayer()));
        mainFrame.getTitleDisplayer().display(loader.load(0));
        mainFrame.setVisible(true);
    }
}
