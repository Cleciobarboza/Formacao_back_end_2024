package repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class MySQLDataSource {

    private static final String DRIVER;
    private static final String URL;
    private static final String USER;
    private static final String PASSWD;

    private static Connection connection;

    static {
        DRIVER = "com.mysql.jdbc.Driver";
        URL = "jdbc:mysql://%s:3306/impacta-cliente-db";
        USER = "impacta";
        PASSWD = "impacta";
        connection = null;
    }

    public static Connection getConnection(String ip) throws MySQLException {
        try {
            // Carregar o DRIVER.
            Class.forName(DRIVER); // Reflection Framework

            if (connection == null) {
                connection = DriverManager.getConnection(String.format(URL, ip), USER, PASSWD);
            }

            // Recuperar a conexão.
            return connection;
        } catch (ClassNotFoundException cause) {
            throw new MySQLException("PROBLEMAS AO CARREGAR O DRIVER!", cause);
        } catch (SQLException cause) {
            throw new MySQLException("PROBLEMAS AO CONECTAR AO MYSQL!", cause);
        }
    }

    public static void close(Connection connection) throws MySQLException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException cause) {
            throw new MySQLException("PROBLEMAS AO FECHAR CONEXÃO!", cause);
        }
    }
}
