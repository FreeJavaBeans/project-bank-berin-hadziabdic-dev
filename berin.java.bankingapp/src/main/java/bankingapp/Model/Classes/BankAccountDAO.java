package bankingapp.Model.Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;
import java.util.TreeSet;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.JDBC.JDBC;
import bankingapp.Model.Interfaces.DAO;
import bankingapp.Model.Interfaces.ReadAllByIdDAO;
import bankingapp.Model.PreppedStatements.Statements;
import bankingapp.UtilityClasses.AppUtility;

public class BankAccountDAO implements DAO<BankAccount, Integer>, ReadAllByIdDAO<String, String> {

    private static BankAccountDAO singleton;

    private BankAccountDAO() {

    }

    @Override
    public boolean Create(BankAccount t) throws Exception {
        // TODO Auto-generated method stub
        boolean success = false;
        try {
            PreparedStatement createAccount = JDBC.GetPreparedStatement(Statements.CREATE_BANK_ACCOUNT);
            createAccount.setString(1, t.getUsername());
            createAccount.setDouble(2, t.getBalance());
            createAccount.execute();
            success = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    @Override
    public BankAccount Read(Integer accountID) throws Exception {
        // TODO Auto-generated method stub
        ResultSet results = null;
        PreparedStatement readAccount = JDBC.GetPreparedStatement(Statements.READ_ACCOUNT_BALANCE);
        readAccount.setInt(1, accountID);
        results = readAccount.executeQuery();

        BankAccount returnDTO = null;

        if (results.next()) {

            returnDTO = new BankAccount();
            returnDTO.setBalance(results.getDouble(1));
            returnDTO.setAccountId(accountID);
            returnDTO.setUsername(results.getString(2));

        }

        return returnDTO;
    }

    public boolean Update(BankAccount t) throws Exception {
        // TODO Auto-generated method stub

        boolean success = false;

        PreparedStatement updateAccount = JDBC.GetPreparedStatement(Statements.UPDATE_ACCOUNTBALANCE_BY_ID);
        updateAccount.setDouble(1, t.getBalance());
        updateAccount.setInt(2, t.getAccountId());
        updateAccount.execute();
        success = true;

        return success;
    }

    public static BankAccountDAO GetSingleton() {
        // TODO Auto-generated method stub
        if (singleton == null)
            singleton = new BankAccountDAO();

        return singleton;
    }

    private Set<String> ReadResultSetIntoStringSet(ResultSet accountDataSet) throws Exception {
        Set<String> userSet = new TreeSet<String>();
        String bankAccountString = null;
        StringBuilder builder = new StringBuilder();

        while (accountDataSet.next()) {

            if (AppUtility.ColumnExists(accountDataSet, Definitions.USERNAME))
                builder.append(" Username  ").append(accountDataSet.getString(Definitions.USERNAME)).toString();

            if (AppUtility.ColumnExists(accountDataSet, Definitions.ACCOUNT_NUMBER))
                builder.append(" ACCT # ").append(accountDataSet.getInt(Definitions.ACCOUNT_NUMBER)).toString();

            if (AppUtility.ColumnExists(accountDataSet, Definitions.ACCOUNT_BALANCE))
                builder.append(" BALANCE ").append(accountDataSet.getInt(Definitions.ACCOUNT_BALANCE)).toString();

            bankAccountString = builder.toString();
            builder.setLength(0);

            userSet.add(bankAccountString);
        }

        return userSet;

    }

    @Override
    public Set<String> ReadAllById(String id) throws Exception {
        PreparedStatement readAllStatement = JDBC.GetPreparedStatement(Statements.READ_ACCOUNTS_BY_USERNAME);
        Set<String> returnSet = null;
        ResultSet resultOfReadAll = null;

        if (AppUtility.CheckNotNullOrEmptyString(id)) {
            readAllStatement.setString(1, id);
            resultOfReadAll = readAllStatement.executeQuery();
            returnSet = ReadResultSetIntoStringSet(resultOfReadAll);
        }

        return returnSet;
    }

    public Set<String> ReadAccountIdBalanceByUsername(String id) throws Exception {
        PreparedStatement readAllStatement = JDBC
                .GetPreparedStatement(Statements.READ_ACCOUNTS_AND_BALANCE_BY_USERNAME);
        Set<String> returnSet = null;
        ResultSet resultOfReadAll = null;

        if (AppUtility.CheckNotNullOrEmptyString(id)) {
            readAllStatement.setString(1, id);
            resultOfReadAll = readAllStatement.executeQuery();
            returnSet = ReadResultSetIntoStringSet(resultOfReadAll);
        }

        return returnSet;
    }

}
