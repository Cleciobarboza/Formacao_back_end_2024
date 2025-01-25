package facade;

import java.util.Collection;

import domain.exception.BairroException;
import service.BairroService;
import service.impl.DefaultBairroService;

public class BairroFacade {

    public static final String NAME = "BAIRRO_FACADE";
    public static final String SOURCE = "impacta_source";

    private BairroService service;

    public BairroFacade(String source) throws BairroException {
        super();

        this.service = new DefaultBairroService(source);
    }

    public Collection<?> listarTodos() throws BairroException {
        return service.listarTodos();
    }

    public Integer contarTodos() throws BairroException {
        return service.contarTodos();
    }

    public void close() {

    }
}
