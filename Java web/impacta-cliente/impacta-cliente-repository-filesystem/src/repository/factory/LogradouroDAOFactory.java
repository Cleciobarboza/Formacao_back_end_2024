package repository.factory;

import java.io.IOException;

import repository.LogradouroDAO;
import repository.io.FileSystemLogradouroDAO;

public final class LogradouroDAOFactory {

    private LogradouroDAOFactory() {
        super();
    }

    public static LogradouroDAO createInstance() throws IOException {
        return new FileSystemLogradouroDAO();
    }

    public static LogradouroDAO createInstance(String source) throws IOException {
        return new FileSystemLogradouroDAO(source);
    }
}
