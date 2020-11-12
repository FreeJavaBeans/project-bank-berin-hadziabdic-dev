package bankingapp.Model.Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.JDBC.JDBC;
import bankingapp.Model.Interfaces.DAO;
import bankingapp.Model.Interfaces.ReadAllDAO;
import bankingapp.Model.PreppedStatements.Statements;
import bankingapp.UtilityClasses.AppUtility;

public class AccountApplicationDAO implements DAO<AccountApplication, Integer>, ReadAllDAO {

    private static AccountApplicationDAO singleton;

    private AccountApplicationDAO() {

    }

    @Override
    public boolean Create(AccountApplication t) throws Exception {
        // TODO Auto-generated method stub
        boolean success = false;
        PreparedStatement createAcoountApp = JDBC.GetPreparedStatement(Statements.CREATE_BANK_ACCOUNT_APPLICATION);
        createAcoountApp.setString(1, t.getUsername());
        createAcoountApp.setDouble(2, (Double) t.getStartingAmount());
        createAcoountApp.execute();
        success = true;

        return success;
    }

    @Override
    public ResultSet ReadAll() throws Exception {
        // TODO Auto-generated method stub

        ResultSet results;
        PreparedStatement readAllPendingApps = JDBC.GetPreparedStatement(Statements.READ_ALL_PENDING_ACCOUNTS);
        results = readAllPendingApps.executeQuery();

        return results;
    }

    public boolean DeleteById(Integer appId) throws Exception {

        if (!AppUtility.CheckValidNumber(appId))
            throw new Exception(
                    "Invalid application Id passed to the DeleteById Method in " + this.getClass().toString());

        boolean success = false;
        PreparedStatement deleteAccountApplication = JDBC.GetPreparedStatement(Statements.DELETE_PENDING_ACCOUNT_BY_ID);
        deleteAccountApplication.setInt(1, appId);
        deleteAccountApplication.execute();
        success = true;

        return success;
    }

    @Override
    public AccountApplication Read(Integer t) throws Exception {
        // TODO Auto-generated method stub
        PreparedStatement readAccountApplicationStatement = null;
        ResultSet results = null;
        AccountApplication readAccountApplication = null;
        String errorMsg = "Invalid application id passed detected. Please try again with a valid application id.";

        if (!AppUtility.CheckValidNumber(t))
            throw new Exception(errorMsg);
        else {
            readAccountApplicationStatement = JDBC.GetPreparedStatement(Statements.READ_ACCOUNT_APPLICATION_BY_ID);
            readAccountApplicationStatement.setInt(1, t);
            results = readAccountApplicationStatement.executeQuery();

            if (results.next()) {
                readAccountApplication = new AccountApplication();
                readAccountApplication.setApplicationId(results.getInt(Definitions.APP_ID));
                readAccountApplication.setStartingAmount(results.getDouble(Definitions.STARTING_AMOUNT));
                readAccountApplication.setUsername(results.getString(Definitions.USERNAME));

                if (!t.equals(readAccountApplication.getApplicationId()))
                    throw new Exception(errorMsg);
            }
        }
        return readAccountApplication;
    }

    public static AccountApplicationDAO GetSingleton() {
        // TODO Auto-generated method stub
        if (singleton == null)
            singleton = new AccountApplicationDAO();

        return singleton;
    }
}
