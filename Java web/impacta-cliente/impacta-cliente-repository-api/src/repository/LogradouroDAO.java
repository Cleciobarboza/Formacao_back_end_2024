package repository;

import java.util.Set;

import domain.exception.LogradouroException;
import domain.model.Bairro;
import domain.model.Logradouro;

public interface LogradouroDAO extends DAO<Logradouro, LogradouroException> {

	Set<Logradouro> selecionar(Bairro bairro) throws LogradouroException;
}
