package bankingapp.Model.Interfaces;

import java.util.Set;

//This dao interface reads all entries in the db 
// based upon a specfic id. E.g, read all customer accounts by username.
public interface ReadAllByIdDAO<IdType, DTO> {

    public Set<DTO> ReadAllById(IdType id) throws Exception;

}
