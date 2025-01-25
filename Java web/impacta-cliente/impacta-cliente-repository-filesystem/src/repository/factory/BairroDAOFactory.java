package repository.factory;

import java.io.IOException;

import repository.BairroDAO;
import repository.io.FileSystemBairroDAO;

public final class BairroDAOFactory {

    private BairroDAOFactory() {
        super();
    }

    public static BairroDAO createInstance() throws IOException {
        return new FileSystemBairroDAO();
    }

    public static BairroDAO createInstance(String source) throws IOException {
        return new FileSystemBairroDAO(source);
    }
}
