package repository.factory;

import repository.BairroDAO;
import repository.jdbc.MySQLBairroDAO;
import repository.jdbc.MySQLException;

public final class BairroDAOFactory {

    private BairroDAOFactory() {
        super();
    }

    public static BairroDAO createInstance() throws MySQLException {
        return new MySQLBairroDAO();
    }

    public static BairroDAO createInstance(String source) throws MySQLException {
        return new MySQLBairroDAO(source);
    }
}
