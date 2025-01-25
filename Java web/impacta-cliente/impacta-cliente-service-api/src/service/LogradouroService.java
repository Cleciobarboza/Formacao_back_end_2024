package service;

import java.util.Collection;

import domain.exception.LogradouroException;
import domain.model.Bairro;
import domain.model.Logradouro;

public interface LogradouroService extends Service<Logradouro, LogradouroException> {

	Collection<Logradouro> listar(Bairro bairro) throws LogradouroException;
}
