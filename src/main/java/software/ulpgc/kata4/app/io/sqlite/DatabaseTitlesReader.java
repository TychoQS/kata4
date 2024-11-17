package software.ulpgc.kata4.app.io.sqlite;

import software.ulpgc.kata4.architecture.model.Title;
import software.ulpgc.kata4.architecture.io.TitlesReader;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class DatabaseTitlesReader implements TitlesReader {

    private static final String selectRandomMoviesStatement = """
            select * FROM movies WHERE titletype == 'MOVIE' \s
            and year IS NOT NULL ORDER BY random();
            """;
    private final Connection connection;

    private final PreparedStatement readMoviesStatement;


    public DatabaseTitlesReader(File file) throws SQLException {
        this(connectionFor(file));
    }

    public DatabaseTitlesReader(String connection) throws SQLException {
        this.connection = DriverManager.getConnection(connection);
        this.readMoviesStatement = getReadMoviesStatement();
    }

    private PreparedStatement getReadMoviesStatement() throws SQLException {
        return this.connection.prepareStatement(selectRandomMoviesStatement);
    }

    @Override
    public List<Title> read() throws IOException {
        try {
            ResultSet resultSet = this.readMoviesStatement.executeQuery();
            List<Title> titles = new MultiplesTitlesDatabaseResponseAdapter(resultSet).adapt();
            this.connection.close();
            return titles;
        } catch (SQLException e) {
            throw new IOException(e.getMessage());
        }
    }

    private static String connectionFor(File file) {
        return "jdbc:sqlite:" + file.getAbsolutePath();
    }
}
