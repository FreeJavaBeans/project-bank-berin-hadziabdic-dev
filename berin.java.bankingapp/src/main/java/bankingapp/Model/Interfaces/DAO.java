package bankingapp.Model.Interfaces;

//I made this interface as a way of standardizing the basic operations
// most of the data structures would require.
//From what I can see, most ds wil require 
//a readby id and create operations
public interface DAO<DTO, Id> {

    public boolean Create(DTO t) throws Exception;

    public DTO Read(Id t) throws Exception;
}
