package software.ulpgc.kata4.app.windows;

import software.ulpgc.kata4.app.io.sqlite.DatabaseTitleWriter;
import software.ulpgc.kata4.app.io.sqlite.DatabaseTitlesReader;
import software.ulpgc.kata4.architecture.control.ImportCommand;
import software.ulpgc.kata4.architecture.control.ToggleBarchartCommand;
import software.ulpgc.kata4.architecture.io.FileTitleReader;
import software.ulpgc.kata4.architecture.io.MoviesBarchartLoader;
import software.ulpgc.kata4.architecture.io.TitleReader;
import software.ulpgc.kata4.architecture.io.TitleWriter;
import software.ulpgc.kata4.architecture.model.FromMapBarchartElementBuilder;
import software.ulpgc.kata4.architecture.model.Title;
import software.ulpgc.kata4.architecture.model.TsvTitleDeserializer;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static final String TITLES_DB = "titles.db";

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\Tycho\\Documents\\title.basics.tsv");
        importFrom(file);
        List<Title> titles = new DatabaseTitlesReader(new File(TITLES_DB)).read();
        Map<Integer, Integer> titlesPerYearStats = new HashMap<>();
        Map<Title.TitleType, Integer> titlesTypeCountStats = new HashMap<>();
        calculateStats(titles, titlesPerYearStats, titlesTypeCountStats);
        MainFrame mainFrame = new MainFrame();
        MoviesBarchartLoader loader = new MoviesBarchartLoader(new FromMapBarchartElementBuilder<>(titlesPerYearStats).build(), new FromMapBarchartElementBuilder<>(titlesTypeCountStats).build());
        mainFrame.put("toggle", new ToggleBarchartCommand(loader, mainFrame.getDisplay()));
        mainFrame.getDisplay().show(loader.load(0));
        mainFrame.setVisible(true);
    }

    private static void calculateStats(List<Title> titles, Map<Integer, Integer> titlesPerYearStats, Map<Title.TitleType, Integer> titlesTypeCountStats) {
        for (Title title : titles) {
            titlesPerYearStats.put(title.getYear(), titlesPerYearStats.getOrDefault(title.getYear(), 0) + 1);
            titlesTypeCountStats.put(title.getTitleType(), titlesTypeCountStats.getOrDefault(title.getTitleType(), 0) + 1);
        }
    }

    private static void importFrom(File file) throws Exception {
        try (TitleReader reader = createTitleReader(file); TitleWriter writer = createTitleWriter(new File(TITLES_DB))) {
            new ImportCommand(reader, writer).execute();
        }
    }

    private static TitleWriter createTitleWriter(File file) throws SQLException {
        return new DatabaseTitleWriter(deleteIfExists(file));
    }

    private static File deleteIfExists(File file) {
        if (file.exists()) file.delete();
        return file;
    }

    private static TitleReader createTitleReader(File file) throws IOException {
        return new FileTitleReader(file, new TsvTitleDeserializer());
    }
}
