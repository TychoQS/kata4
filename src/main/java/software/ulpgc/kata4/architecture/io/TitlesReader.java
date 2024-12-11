package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.Title;

import java.io.IOException;
import java.util.List;

public interface TitlesReader {
    List<Title> read() throws IOException;
}
