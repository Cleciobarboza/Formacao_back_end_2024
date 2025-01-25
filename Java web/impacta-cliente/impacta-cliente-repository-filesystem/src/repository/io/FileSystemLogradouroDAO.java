package repository.io;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;

import domain.exception.LogradouroException;
import domain.model.Bairro;
import domain.model.Logradouro;
import repository.LogradouroDAO;

public class FileSystemLogradouroDAO extends FileSystemDAO implements LogradouroDAO {

    public FileSystemLogradouroDAO() throws IOException {
        this("/sources");
    }

    public FileSystemLogradouroDAO(String source) throws IOException {
        super(Paths.get(URI.create(String.format("file://%s/logradouros.txt", source))));
    }

    /** {@inheritDoc} */
    @Override
    public void inserir(Logradouro domain) throws LogradouroException {
        System.out.println("Inserindo em arquivo: " + domain);
    }

    /** {@inheritDoc} */
    @Override
    public void atualizar(Logradouro domain) throws LogradouroException {
        System.out.println("Atualizando em arquivo: " + domain);
    }

    /** {@inheritDoc} */
    @Override
    public void apagar(Logradouro domain) throws LogradouroException {
        System.out.println("Apagando em arquivo: " + domain);
    }

    /** {@inheritDoc} */
    @Override
    public Set<Logradouro> selecionar(Bairro bairro) throws LogradouroException {
        System.out.println("Selecionando em arquivo por bairro: " + bairro);
        return Collections.<Logradouro> emptySet();
    }

    /** {@inheritDoc} */
    @Override
    public Set<Logradouro> selecionarTodos() throws LogradouroException {
        System.out.println("Selecionando em arquivo");
        return Collections.<Logradouro> emptySet();
    }

    /** {@inheritDoc} */
    @Override
    public Integer contarTodos() throws LogradouroException {
        Integer total = 0;

        try {
            source.seek(0L);
            String line = "";

            while (line != null) {
                line = source.readLine();
                if (line != null && !line.isEmpty())
                    total++;
            }

            return total;
        } catch (IOException cause) {
            throw new LogradouroException("PROBLEMAS AO CONTAR TODOS OS LOGRADOUROS DO ARQUIVO!", cause);
        }
    }
}
