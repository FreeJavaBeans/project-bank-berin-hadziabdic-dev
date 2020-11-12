package bankingapp.Model.Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.JDBC.JDBC;
import bankingapp.Model.Interfaces.DAO;
import bankingapp.Model.PreppedStatements.Statements;

public class UserDAO implements DAO<User, String> {

    private static UserDAO singleton;

    @Override
    public boolean Create(User t) throws Exception {
        // TODO Auto-generated method stub

        String username = t.getUsername();
        String password = t.getPassword();
        PreparedStatement createUser = null;
        boolean success = false;

        createUser = JDBC.GetPreparedStatement(Statements.CREATE_USER);
        createUser.setString(1, username);
        createUser.setString(2, password);
        createUser.execute();
        success = true;

        return success;
    }

    @Override
    public User Read(String username) throws Exception {
        // TODO Auto-generated method stub
        ResultSet results = null;
        User usrDTO = null;

        PreparedStatement readUser = JDBC.GetPreparedStatement(Statements.READ_USER_BY_USERNAME);
        readUser.setString(1, username);
        results = readUser.executeQuery();

        if (results.next()) {
            usrDTO = new User();
            usrDTO.setUsername(results.getString(Definitions.USERNAME));
            usrDTO.setPassword(results.getString(Definitions.PASSWORD));
            usrDTO.setAccountType(results.getString(Definitions.ACCOUNT_TYPE));
        }

        return usrDTO;
    }

    public static UserDAO GetSingleton() {
        // TODO Auto-generated method stub
        if (singleton == null)
            singleton = new UserDAO();

        return singleton;
    }

}
