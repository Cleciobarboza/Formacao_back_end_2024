package facade;

import java.util.Collection;

import domain.Closeable;
import domain.exception.LogradouroException;
import service.LogradouroService;
import service.impl.DefaultLogradouroService;

public class LogradouroFacade {

    public static final String NAME = "LOGRADOURO_FACADE";
    public static final String SOURCE = "impacta_source";

    private LogradouroService service;

    public LogradouroFacade(String source) throws LogradouroException {
        super();

        this.service = new DefaultLogradouroService(source);
    }

    public Collection<?> listarTodos() throws LogradouroException {
        return service.listarTodos();
    }

    public Integer contarTodos() throws LogradouroException {
        return service.contarTodos();
    }

    public void close() throws Exception {
        ((Closeable) service).close();
    }
}
