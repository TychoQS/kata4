package software.ulpgc.kata4.architecture.control;

import software.ulpgc.kata4.architecture.io.TitleReader;
import software.ulpgc.kata4.architecture.io.TitleWriter;
import software.ulpgc.kata4.architecture.model.Title;

import java.io.IOException;

public class ImportCommand implements Command{
    private final TitleReader titleReader;
    private final TitleWriter titleWriter;

    public ImportCommand(TitleReader titleReader, TitleWriter titleWriter) {
        this.titleReader = titleReader;
        this.titleWriter = titleWriter;
    }

    @Override
    public void execute() {
        try {
            while (true) {
                Title title = titleReader.read();
                if (title == null) break;
                titleWriter.write(title);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
