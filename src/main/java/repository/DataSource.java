package repository;

import configuration.Config;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static DataSource datasource;
    private final BasicDataSource connectionPool;


    private DataSource(Config config) {
        connectionPool = new BasicDataSource();
        connectionPool.setDriverClassName(config.getDriver());
        connectionPool.setUrl(config.getURL());
        connectionPool.setUsername(config.getUsername());
        connectionPool.setPassword(config.getPassword());
        connectionPool.setMinIdle(5);
        connectionPool.setMaxIdle(10);
        connectionPool.setMaxOpenPreparedStatements(100);
    }

    public static DataSource getInstance(Config config) {
        if (datasource == null) {
            datasource = new DataSource(config);
        }
        return datasource;
    }

    public Connection getConnection() throws SQLException {
        return this.connectionPool.getConnection();
    }

}
