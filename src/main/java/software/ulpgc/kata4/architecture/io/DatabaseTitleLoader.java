package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.Title;

import java.util.List;

public class DatabaseTitleLoader implements TitleLoader {
    private final List<Title> titles;

    public DatabaseTitleLoader(List<Title> titles) {
        this.titles = titles;
    }

    @Override
    public Title load(int i) {
        return titles.get(i % titles.size());
    }
}
