package repository.io;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;

import domain.exception.BairroException;
import domain.model.Bairro;
import domain.model.Municipio;
import repository.BairroDAO;

public class FileSystemBairroDAO extends FileSystemDAO implements BairroDAO {

    public FileSystemBairroDAO() throws IOException {
        this("/sources");
    }

    public FileSystemBairroDAO(String source) throws IOException {
        super(Paths.get(String.format("%s/bairros.txt", source)));
    }

    @Override
    public void inserir(Bairro domain) throws BairroException {
        System.out.println("Inserindo em arquivo: " + domain);
    }

    @Override
    public void atualizar(Bairro domain) throws BairroException {
        System.out.println("Atualizando em arquivo: " + domain);
    }

    @Override
    public void apagar(Bairro domain) throws BairroException {
        System.out.println("Apagando em arquivo: " + domain);
    }

    @Override
    public Set<Bairro> selecionar(Municipio municipio) throws BairroException {
        System.out.println("Selecionando em arquivo por munic�pio: " + municipio);
        return Collections.<Bairro> emptySet();
    }

    @Override
    public Set<Bairro> selecionarTodos() throws BairroException {
        System.out.println("Selecionando em arquivo");
        return Collections.<Bairro> emptySet();
    }

    /** {@inheritDoc} */
    @Override
    public Integer contarTodos() throws BairroException {
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
            throw new BairroException("PROBLEMAS AO CONTAR TODOS OS BAIRROS DO ARQUIVO!", cause);
        }
    }
}
