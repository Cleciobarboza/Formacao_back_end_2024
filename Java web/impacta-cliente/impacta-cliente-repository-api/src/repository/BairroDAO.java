package repository;

import java.util.Set;

import domain.exception.BairroException;
import domain.model.Bairro;
import domain.model.Municipio;

public interface BairroDAO extends DAO<Bairro, BairroException> {

	Set<Bairro> selecionar(Municipio municipio) throws BairroException;
}
