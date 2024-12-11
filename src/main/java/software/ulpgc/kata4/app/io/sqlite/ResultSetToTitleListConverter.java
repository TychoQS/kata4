package software.ulpgc.kata4.app.io.sqlite;

import software.ulpgc.kata4.architecture.model.Title;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetToTitleListConverter {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String TITLETYPE = "titletype";
    private static final String YEAR = "year";
    private static final String DURATION = "duration";

    public static List<Title> convert(ResultSet resultSet) throws SQLException {
        List<Title> titleList = new ArrayList<>();
        while (resultSet.next()) {
            titleList.add(new Title(
                    getString(resultSet, ID),
                    getString(resultSet, TITLE),
                    getInt(resultSet, YEAR),
                    getInt(resultSet, DURATION),
                    Title.TitleType.valueOf(getString(resultSet, TITLETYPE))
            ));
        }
        return titleList;
    }

    private static int getInt(ResultSet resultSet, String field) throws SQLException {
        return resultSet.getInt(field);
    }

    private static String getString(ResultSet resultSet, String field) throws SQLException {
        return resultSet.getString(field);
    }
}
