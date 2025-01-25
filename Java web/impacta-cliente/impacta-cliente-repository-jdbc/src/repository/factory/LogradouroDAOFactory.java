package repository.factory;

import repository.LogradouroDAO;
import repository.jdbc.MySQLException;
import repository.jdbc.MySQLLogradouroDAO;

public final class LogradouroDAOFactory {

    private LogradouroDAOFactory() {
        super();
    }

    public static LogradouroDAO createInstance() throws MySQLException {
        return new MySQLLogradouroDAO();
    }

    public static LogradouroDAO createInstance(String source) throws MySQLException {
        return new MySQLLogradouroDAO(source);
    }
}
