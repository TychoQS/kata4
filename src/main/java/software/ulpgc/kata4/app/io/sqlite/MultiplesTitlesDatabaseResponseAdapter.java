package software.ulpgc.kata4.app.io.sqlite;

import software.ulpgc.kata4.architecture.io.DatabaseResponseAdapter;
import software.ulpgc.kata4.architecture.model.Title;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MultiplesTitlesDatabaseResponseAdapter implements DatabaseResponseAdapter {

    private final ResultSet resultSet;
    private static final String ID = "id";
    private static final String TITLETYPE = "titletype";
    private static final String TITLE = "title";
    private static final String YEAR = "year";
    private static final String DURATION = "DURATION";

    public MultiplesTitlesDatabaseResponseAdapter(ResultSet resultSet) {
        this.resultSet =resultSet;
    }

    @Override
    public List<Title> adapt() throws IOException {
        List<Title> titles = new ArrayList<>();
        try {
            while (resultSet.next()) {
                titles.add(new Title(getString(ID), Title.TitleType.valueOf(getString(TITLETYPE).toUpperCase()), getString(TITLE), getInt(YEAR), getInt(DURATION)));
            }
        } catch (SQLException e) {
            throw new IOException(e.getMessage());
        }
        return titles;
    }

    public String getString(String columnLabel) throws SQLException {
        return resultSet.getString(columnLabel);
    }

    public int getInt(String columnLabel) throws SQLException {
        return resultSet.getInt(columnLabel);
    }
}
