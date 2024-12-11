    package software.ulpgc.kata4.app.io.sqlite;

    import software.ulpgc.kata4.architecture.io.TitlesReader;
    import software.ulpgc.kata4.architecture.model.Title;

    import java.io.File;
    import java.io.IOException;
    import java.sql.*;
    import java.util.List;

    import static software.ulpgc.kata4.app.io.sqlite.DatabaseTitleWriter.connectionFor;

    public class DatabaseTitlesReader implements TitlesReader {
        private static final String getMoviesStatement = """
                select *
                FROM movies
                """;
        private final Connection connection;
        private final PreparedStatement readStatement;

        public DatabaseTitlesReader(File file) throws SQLException {
            this(connectionFor(file));
        }

        public DatabaseTitlesReader(String connection) throws SQLException {
            this.connection = DriverManager.getConnection(connection);
            this.readStatement = this.connection.prepareStatement(getMoviesStatement);
        }

        @Override
        public List<Title> read() throws IOException {
            try {
                ResultSet resultSet = this.readStatement.executeQuery();
                List<Title> titleList = ResultSetToTitleListConverter.convert(resultSet);
                this.connection.close();
                return titleList;
            } catch (SQLException e) {
                throw new IOException(e.getMessage());
            }
        }
    }
