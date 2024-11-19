package software.ulpgc.kata4.app.io.sqlite;

import software.ulpgc.kata4.architecture.control.Command;
import software.ulpgc.kata4.architecture.io.FileTiteReader;
import software.ulpgc.kata4.architecture.model.Title;
import software.ulpgc.kata4.architecture.io.TsvTitleDeserializer;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ImportCommand implements Command {
    @Override
    public void execute() {
        File input = getInputFile();
        File output = getOutputFile();
        try {
            doExecute(input, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doExecute(File input, File output) throws Exception {
        try (FileTiteReader reader = createMovieReader(input); DatabaseTitleWriter writer = createMovieWriter(output)){
            while (true) {
                Title title = reader.read();
                if (title == null) break;
                writer.write(title);
            }
        }
    }

    private DatabaseTitleWriter createMovieWriter(File output) throws SQLException {
        return new DatabaseTitleWriter(deleteIfExists(output));
    }

    private File deleteIfExists(File file) {
        if (file.exists()) file.delete();
        return file;
    }

    private FileTiteReader createMovieReader(File input) throws IOException {
        return new FileTiteReader(input, new TsvTitleDeserializer());
    }

    private File getOutputFile() {
        return new File("titles.db");
    }

    private File getInputFile() {
        return new File("C:\\Users\\Tycho\\Documents\\title.basics.tsv");
    }
}
