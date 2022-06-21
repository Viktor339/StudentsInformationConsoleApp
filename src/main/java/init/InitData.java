package init;

import configuration.Config;
import service.exception.UnableToExecuteQueryException;
import service.exception.UnableToGetConnectionException;
import lombok.AllArgsConstructor;
import repository.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@AllArgsConstructor
public class InitData {

    private static final String CREATE_TABLE =
            "create table if not exists students (id serial not null constraint students_pkey primary key,name varchar not null,first_name varchar,surname varchar not null,patronymic varchar not null,birth_date varchar not null,study_group varchar not null);";
    private final Config config;

    protected Connection getConnection() {
        try {
            return DataSource.getInstance(config).getConnection();
        } catch (SQLException e) {
            throw new UnableToGetConnectionException(e.getMessage());
        }
    }

    public void createTable() {

        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE);
            preparedStatement.execute();

        } catch (Exception e) {
            throw new UnableToExecuteQueryException(e.getMessage());
        }
    }


}
