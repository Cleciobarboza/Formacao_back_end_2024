package service;

import java.util.Collection;

interface Service<T, E extends Exception> {

    void validar(T domain) throws E;

    void salvar(T domain) throws E;

    void apagar(T domain) throws E;

    Collection<T> listarTodos() throws E;

    Integer contarTodos() throws E;
}
