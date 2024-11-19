package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.Title;

import java.io.*;

public class FileTiteReader implements TitleReader{
    private final TitleDeserializer deserializer;
    private final BufferedReader reader;

    public FileTiteReader(File file, TitleDeserializer deserializer) throws IOException {
        this.deserializer = deserializer;
        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        this.reader.readLine();
    }

    @Override
    public Title read() throws IOException {
        return deserializer(reader.readLine());
    }

    private Title deserializer(String line) {
        return line != null ? deserializer.deserialize(line) : null;
    }

    @Override
    public void close() throws Exception {
        this.reader.close();
    }
}
