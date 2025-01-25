package service;

import java.util.Collection;

import domain.exception.BairroException;
import domain.model.Bairro;
import domain.model.Municipio;

public interface BairroService extends Service<Bairro, BairroException> {

	Collection<Bairro> listar(Municipio municipio) throws BairroException;
}
