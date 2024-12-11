package software.ulpgc.kata4.app.io.sqlite;

import software.ulpgc.kata4.architecture.io.TitleWriter;
import software.ulpgc.kata4.architecture.model.Title;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class DatabaseTitleWriter implements TitleWriter {
    private static final String createTableStatement = """
            CREATE TABLE IF NOT EXISTS movies (
                            id TEXT PRIMARY KEY,
                            titletype TEXT,
                            title TEXT NOT NULL,
                            year INTEGER,
                            duration INTEGER
                        )
            """;
    private static final String insertRecordStatement = """
            INSERT INTO movies (id,titletype,title,year,duration)
                        VALUES (?,?,?,?,?)
            """;
    private final Connection connection;
    private final PreparedStatement insertStatement;

    public DatabaseTitleWriter(File file) throws SQLException {
        this(connectionFor(file));
    }

    public DatabaseTitleWriter(String connection) throws SQLException {
        this.connection = DriverManager.getConnection(connection);
        this.connection.setAutoCommit(false);
        this.insertStatement = initDatabase();
    }

    private PreparedStatement initDatabase() throws SQLException {
        Statement statement = this.connection.createStatement();
        statement.execute(createTableStatement);
        return this.connection.prepareStatement(insertRecordStatement);
    }

    static String connectionFor(File file) {
        return "jdbc:sqlite:" + file.getAbsolutePath();
    }

    @Override
    public void write(Title title) throws IOException {
        try {
            updateInsertStatementWith(title);
            this.insertStatement.execute();
        } catch (SQLException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void updateInsertStatementWith(Title title) throws SQLException {
        for (Parameter parameter : toParameters(title)) {
            if (isNull(parameter.value)) {
                this.insertStatement.setNull(parameter.id, parameter.type);
            } else {
                this.insertStatement.setObject(parameter.id, parameter.value);
            }
        }
    }

    private boolean isNull(Object value) {
        return value instanceof Integer && ((Integer) value) == -1;
    }

    private List<Parameter> toParameters(Title title) {
        return List.of(
                new Parameter(1, title.getId(), Types.LONGNVARCHAR),
                new Parameter(2, title.getTitleType(), Types.LONGNVARCHAR),
                new Parameter(3, title.getTitle(), Types.LONGNVARCHAR),
                new Parameter(4, title.getYear(), Types.INTEGER),
                new Parameter(5, title.getDuration(), Types.INTEGER)
        );
    }

    private record Parameter(int id, Object value, int type) {}
    @Override
    public void close() throws Exception {
        this.connection.commit();
    }
}
