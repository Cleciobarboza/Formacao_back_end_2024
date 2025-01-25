package repository.factory;

import java.io.IOException;

import domain.exception.PathException;
import repository.MunicipioDAO;
import repository.io.FileSystemMunicipioDAO;

public final class MunicipioDAOFactory {

    private static final String PATH_PROBLEM_MESSAGE = "\n\n\nPROBLEMAS COM A LOCALIZAÇÃO DA PASTA FONTE DE DADOS!\n"
			+ "Favor, verifique se foi criado em \"C:\\sources\" (Windows) ou"
			+ " \"/sources\" (Linux/Mac).\nLEMBRE-SE: O nome precisa ser TODO EM MINÚSCULO"
			+ " e ter PERMISSÃO DE ACESSO PARA LEITURA E ESCRITA.\n\n\n";

	private MunicipioDAOFactory() {
        super();
    }

    public static MunicipioDAO createInstance() throws PathException {
    	try {
    		return new FileSystemMunicipioDAO();
    	} catch (IOException cause) {
			throw new PathException(PATH_PROBLEM_MESSAGE, cause);
		}
    }

    public static MunicipioDAO createInstance(String source) throws PathException {
        try {
        	return new FileSystemMunicipioDAO(source);
    	} catch (IOException cause) {
			throw new PathException(PATH_PROBLEM_MESSAGE, cause);
		}
    }
}
