package bankingapp.Services.Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;
import java.util.TreeSet;

import bankingapp.ApplicationLookUpTable.Definitions;
import bankingapp.JDBC.JDBC;
import bankingapp.Model.PreppedStatements.Statements;
import bankingapp.Protocol.Classes.RequestProtocol;
import bankingapp.Protocol.Classes.ResponseProtocol;
import bankingapp.Services.Interfaces.HandlerService;
import bankingapp.UtilityClasses.AppUtility;

public class GetPendingAccountApplicationsHandler implements HandlerService {

    public Set<String> ReadPendingApplicationResultSetIntoStringSet(ResultSet results) throws Exception {

        Set<String> ret = new TreeSet<String>();

        StringBuilder bldr = new StringBuilder();
        String applicationBuffer = null;

        if (results != null && results.isBeforeFirst()) {

            while (results.next()) {

                if (AppUtility.ColumnExists(results, Definitions.APP_ID))
                    bldr.append(" APPLICATION ID " + results.getInt(Definitions.APP_ID));

                if (AppUtility.ColumnExists(results, Definitions.USERNAME))
                    bldr.append(" USERNAME " + results.getString(Definitions.USERNAME));

                if (AppUtility.ColumnExists(results, Definitions.STARTING_AMOUNT))
                    bldr.append(" STARTING AMOUNT  " + results.getDouble(Definitions.STARTING_AMOUNT));

                applicationBuffer = bldr.toString();
                ret.add(applicationBuffer);

                bldr.setLength(0);
                applicationBuffer = null;

            }
        }
        return ret;
    }

    @Override
    public ResponseProtocol Handler(RequestProtocol requestPayload) throws Exception {
        // TODO Auto-generated method stub
        ResponseProtocol resp = null;
        Set<String> respMessage = null;
        PreparedStatement getPendingAccountApps = JDBC.GetPreparedStatement(Statements.READ_ALL_PENDING_ACCOUNTS);
        ResultSet pendingAccounts = getPendingAccountApps.executeQuery();

        respMessage = ReadPendingApplicationResultSetIntoStringSet(pendingAccounts);

        if (respMessage.size() == 0)
            resp = new ResponseProtocol(true, "No pending account applications have been discovered.");
        else
            resp = new ResponseProtocol(true, respMessage);

        return resp;
    }

}
