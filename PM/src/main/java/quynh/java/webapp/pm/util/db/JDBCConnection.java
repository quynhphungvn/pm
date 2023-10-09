/**
 * .
 */
package quynh.java.webapp.pm.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class JDBCConnection {
    private JDBCConnection() { }

    /**
     * .
     * @return A Connection instance.
     * @throws SQL and Class not found exception.
     */
    public static Connection getMySQLConnection()
            throws SQLException, ClassNotFoundException {
        final String hostName = "localhost";
        final String dbName = "sm";
        final String userName = "qin";
        final String password = "mysql123";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    /**
     * @param hostName the host name of the connection.
     * @param dbName the database name of the connection.
     * @param userName the username to login.
     * @param password the password to login.
     * @return A Connection instance.
     * @throws SQL and Class not found exception.
     */
    public static Connection getMySQLConnection(final String hostName,
                                                final String dbName,
                                                final String userName,
                                                final String password)
                          throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        Connection conn = DriverManager.getConnection(connectionURL,
                                                        userName, password);
        return conn;
    }
}
