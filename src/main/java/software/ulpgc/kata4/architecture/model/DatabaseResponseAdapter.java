package software.ulpgc.kata4.architecture.model;

import java.io.IOException;
import java.util.List;

public interface DatabaseResponseAdapter {
    List<Title> adapt() throws IOException;
}
