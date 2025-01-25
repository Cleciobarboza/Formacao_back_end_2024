package repository;

import java.util.Set;

import domain.exception.ClienteException;
import domain.model.Cliente;
import domain.model.EstadoCivilVO;
import domain.model.SexoVO;

public interface ClienteDAO extends DAO<Cliente, ClienteException> {

	Set<Cliente> selecionar(EstadoCivilVO estadoCivil) throws ClienteException;

	Set<Cliente> selecionar(SexoVO sexo) throws ClienteException;

	Set<Cliente> selecionarPorNome(String nome) throws ClienteException;

	Set<Cliente> selecionarPorSobrenome(String sobrenome) throws ClienteException;
}
