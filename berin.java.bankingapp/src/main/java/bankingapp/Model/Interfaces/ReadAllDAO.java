package bankingapp.Model.Interfaces;

import java.sql.ResultSet;

//This dao is used to read all entries in a table with no where crietria (select col0,..,coln from tableName)
//I separated it out from DAO because ReadAll is not an operation required by most features
public interface ReadAllDAO {

    public ResultSet ReadAll() throws Exception;

}
