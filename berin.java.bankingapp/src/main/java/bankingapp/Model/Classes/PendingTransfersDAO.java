package bankingapp.Model.Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.JDBC.JDBC;
import bankingapp.Model.Interfaces.DAO;
import bankingapp.Model.PreppedStatements.Statements;
import bankingapp.UtilityClasses.AppUtility;

public class PendingTransfersDAO implements DAO<PendingTransfers, Integer> {

    private static PendingTransfersDAO singleton;

    public PendingTransfersDAO() {

    }

    @Override
    public boolean Create(PendingTransfers t) throws Exception {
        // TODO Auto-generated method stub
        if (!Objects.isNull(t) && !t.SelfValidate())
            throw new Exception(
                    "Null or invalid state object passed to Create DAO method in " + this.getClass().toString());
        boolean success = false;
        PreparedStatement ps = JDBC.GetPreparedStatement(Statements.CREATE_MONEY_TRANSFER);
        ps.setInt(1, t.getFromAccount());
        ps.setInt(2, t.getToAccount());
        ps.setDouble(3, t.getAmount());
        ps.execute(); // this will throw exception out of this method on failure.
        success = true;

        return success;
    }

    public boolean Delete(Integer pendingTransferId) throws Exception {

        boolean success = false;
        PreparedStatement deleteStatement = JDBC.GetPreparedStatement(Statements.DELETE_PENDING_TRANSFER_BY_ID);

        deleteStatement.setInt(1, pendingTransferId);
        success = deleteStatement.execute();

        return success;
    }

    @Override
    public PendingTransfers Read(Integer t) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public static PendingTransfersDAO GetSingleton() {
        // TODO Auto-generated method stub
        if (singleton == null)
            singleton = new PendingTransfersDAO();
        return singleton;
    }

    public Set<String> ReadAllPendingTransfersForUser(String username, String password) throws Exception {

        Set<String> pendingTransfersSet = null;
        ResultSet pendingResultsSet = null;
        PreparedStatement usersPendingTransferStatement = JDBC
                .GetPreparedStatement(Statements.READ_ALL_PENDING_TRANSFERS);

        usersPendingTransferStatement.setString(1, username);

        pendingResultsSet = usersPendingTransferStatement.executeQuery();
        pendingTransfersSet = ReadResultSetIntoStringSet(pendingResultsSet);

        return pendingTransfersSet;
    }

    private Set<String> ReadResultSetIntoStringSet(ResultSet results) throws Exception {
        Set<String> forest = new TreeSet<String>();
        StringBuilder builder = new StringBuilder();
        String iterator = null;

        if (results != null)
            while (results.next()) {

                if (AppUtility.ColumnExists(results, Definitions.TRANSFER_ID))
                    builder.append("transfer_id " + results.getInt(Definitions.TRANSFER_ID) + " ");

                if (AppUtility.ColumnExists(results, Definitions.FROM_ACCOUNT))
                    builder.append("from account " + results.getInt(Definitions.FROM_ACCOUNT) + " ");

                if (AppUtility.ColumnExists(results, Definitions.TO_ACCOUNT))
                    builder.append("to account " + results.getInt(Definitions.TO_ACCOUNT) + " ");

                if (AppUtility.ColumnExists(results, Definitions.AMOUNT))
                    builder.append("transfer amount " + results.getDouble(Definitions.AMOUNT) + " ");

                iterator = builder.toString();
                builder.setLength(0); // clear builder
                forest.add(iterator);

            }

        return forest;
    }

    public PendingTransfers ReadTransferIdOwnedByUser(Integer transferId, String username, String password)
            throws Exception {

        PendingTransfers pendingTransfer = null;
        // id username password
        PreparedStatement getTransferByUser = JDBC
                .GetPreparedStatement(Statements.READ_TRANSFER_BY_ID_USERNAME_AND_PASSWORD);

        getTransferByUser.setInt(1, transferId);
        getTransferByUser.setString(2, username);
        getTransferByUser.setString(3, password);

        ResultSet results = getTransferByUser.executeQuery();

        if (results.next()) {
            pendingTransfer = new PendingTransfers();
            pendingTransfer.setAmount(results.getDouble(Definitions.AMOUNT));
            pendingTransfer.setFromAccount(results.getInt(Definitions.FROM_ACCOUNT));
            pendingTransfer.setToAccount(results.getInt(Definitions.TO_ACCOUNT));
            pendingTransfer.setTransferId(transferId);

        }

        return pendingTransfer;

    }

}
