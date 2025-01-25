package repository.factory;

import repository.MunicipioDAO;
import repository.jdbc.MySQLException;
import repository.jdbc.MySQLMunicipioDAO;

public final class MunicipioDAOFactory {

    private MunicipioDAOFactory() {
        super();
    }

    public static MunicipioDAO createInstance() throws MySQLException {
        return new MySQLMunicipioDAO();
    }

    public static MunicipioDAO createInstance(String source) throws MySQLException {
        return new MySQLMunicipioDAO(source);
    }
}
