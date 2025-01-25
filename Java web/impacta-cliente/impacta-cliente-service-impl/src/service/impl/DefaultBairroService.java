package service.impl;

import java.util.Collection;

import domain.exception.BairroException;
import domain.model.Bairro;
import domain.model.Municipio;
import repository.BairroDAO;
import repository.factory.BairroDAOFactory;
import service.BairroService;

public class DefaultBairroService implements BairroService {

    private BairroDAO dao;

    public DefaultBairroService(String source) throws BairroException {
        super();

        try {
            this.dao = BairroDAOFactory.createInstance(source);
        } catch (Exception cause) {
            throw new BairroException(cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void validar(Bairro domain) throws BairroException {
        domain.validarUf();
        domain.validarMunicipio();
        domain.validarNome();
    }

    /** {@inheritDoc} */
    @Override
    public void salvar(Bairro domain) throws BairroException {
        if (domain.isNullId()) {
            dao.inserir(domain);
        } else {
            dao.atualizar(domain);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void apagar(Bairro domain) throws BairroException {
        dao.apagar(domain);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Bairro> listar(Municipio municipio) throws BairroException {
        return dao.selecionar(municipio);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Bairro> listarTodos() throws BairroException {
        return dao.selecionarTodos();
    }

    /** {@inheritDoc} */
    @Override
    public Integer contarTodos() throws BairroException {
        return dao.contarTodos();
    }
}
