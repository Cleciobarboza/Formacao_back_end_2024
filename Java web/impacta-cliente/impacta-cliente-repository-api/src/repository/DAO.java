package repository;

import java.util.Set;

interface DAO<T, E extends Exception> {

    void inserir(T domain) throws E;

    void atualizar(T domain) throws E;

    void apagar(T domain) throws E;

    Set<T> selecionarTodos() throws E;

    Integer contarTodos() throws E;
}
