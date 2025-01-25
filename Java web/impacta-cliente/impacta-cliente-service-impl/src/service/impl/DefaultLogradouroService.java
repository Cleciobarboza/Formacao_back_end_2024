package service.impl;

import java.util.Collection;

import domain.Closeable;
import domain.exception.LogradouroException;
import domain.model.Bairro;
import domain.model.Logradouro;
import repository.LogradouroDAO;
import repository.factory.LogradouroDAOFactory;
import service.LogradouroService;

public class DefaultLogradouroService implements Closeable, LogradouroService {

    private LogradouroDAO dao;

    public DefaultLogradouroService(String source) throws LogradouroException {
        super();

        try {
            this.dao = LogradouroDAOFactory.createInstance(source);
        } catch (Exception cause) {
            throw new LogradouroException(cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void validar(Logradouro domain) throws LogradouroException {
        domain.validarUf();
        domain.validarMunicipio();
        domain.validarBairro();
        domain.validarTipo();
        domain.validarNome();
    }

    /** {@inheritDoc} */
    @Override
    public void salvar(Logradouro domain) throws LogradouroException {
        if (domain.isNullId()) {
            dao.inserir(domain);
        } else {
            dao.atualizar(domain);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void apagar(Logradouro domain) throws LogradouroException {
        dao.apagar(domain);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Logradouro> listar(Bairro bairro) throws LogradouroException {
        return dao.selecionar(bairro);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Logradouro> listarTodos() throws LogradouroException {
        return dao.selecionarTodos();
    }

    /** {@inheritDoc} */
    @Override
    public Integer contarTodos() throws LogradouroException {
        return dao.contarTodos();
    }

    @Override
    public void close() throws Exception {
        ((Closeable) dao).close();
    }
}
