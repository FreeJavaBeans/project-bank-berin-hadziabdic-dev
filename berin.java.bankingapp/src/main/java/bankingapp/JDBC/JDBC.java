package bankingapp.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JDBC {

    private static Connection InitConnection() {

        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASS");
        String database = System.getenv("DB");
        String jdbcUrl = "jdbc:postgresql:" + database;
        Connection initConnection = null;

        try {
            initConnection = DriverManager.getConnection(jdbcUrl, username, password);
            initConnection.setSchema("bankapp");
        } catch (Exception e) {
            System.out.println("DB connection exception encountered in class " + JDBC.class.toGenericString());
            System.out.println(e.getMessage());
            System.out.println("Exiting application...");
            System.exit(1);
        }

        return initConnection;

    }

    private static Connection jdbcConnection = InitConnection();

    public static PreparedStatement GetPreparedStatement(String parametrizedSqlStatement) throws Exception {

        return JDBC.jdbcConnection.prepareStatement(parametrizedSqlStatement);

    }

}
