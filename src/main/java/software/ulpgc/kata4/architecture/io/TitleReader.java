package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.Title;

import java.io.IOException;

public interface TitleReader extends AutoCloseable {
    Title read() throws IOException;
}
