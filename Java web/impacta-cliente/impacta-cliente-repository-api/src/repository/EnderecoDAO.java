package repository;

import java.util.Set;

import domain.exception.EnderecoException;
import domain.model.Cliente;
import domain.model.Endereco;
import domain.model.Logradouro;

public interface EnderecoDAO extends DAO<Endereco, EnderecoException> {

	Set<Endereco> selecionar(Cliente cliente) throws EnderecoException;

	Set<Endereco> selecionar(Logradouro logradouro) throws EnderecoException;
}
