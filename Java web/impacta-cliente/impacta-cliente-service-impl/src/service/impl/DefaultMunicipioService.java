package service.impl;

import java.util.Arrays;
import java.util.Collection;

import domain.Closeable;
import domain.exception.MunicipioException;
import domain.model.Municipio;
import domain.model.UFVO;
import repository.MunicipioDAO;
import repository.factory.MunicipioDAOFactory;
import service.MunicipioService;

public class DefaultMunicipioService implements Closeable, MunicipioService {

    private MunicipioDAO dao;

    public DefaultMunicipioService(String source) throws MunicipioException {
        super();

        try {
            this.dao = MunicipioDAOFactory.createInstance(source);
        } catch (Exception cause) {
            throw new MunicipioException(cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void validar(Municipio domain) throws MunicipioException {
        domain.validarUf();
        domain.validarNome();
    }

    /** {@inheritDoc} */
    @Override
    public void salvar(Municipio domain) throws MunicipioException {
        if (domain.isNullId()) {
            dao.inserir(domain);
        } else {
            dao.atualizar(domain);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void apagar(Municipio domain) throws MunicipioException {
        dao.apagar(domain);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Municipio> listar(UFVO uf) throws MunicipioException {
        return dao.selecionar(uf);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Municipio> listarTodos() throws MunicipioException {
        return dao.selecionarTodos();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<UFVO> listarUFs() {
        return Arrays.asList(UFVO.values());
    }

    /** {@inheritDoc} */
    @Override
    public Integer contarTodos() throws MunicipioException {
        return dao.contarTodos();
    }

    /** {@inheritDoc} */
    @Override
    public void close() throws Exception {
        ((Closeable) dao).close();
    }
}
