package repository.io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import domain.exception.MunicipioException;
import domain.model.Municipio;
import domain.model.UFVO;
import repository.MunicipioDAO;

public class FileSystemMunicipioDAO extends FileSystemDAO implements MunicipioDAO {

    public FileSystemMunicipioDAO() throws IOException {
        this("/sources");
    }

    public FileSystemMunicipioDAO(String source) throws IOException {
        super(FileSystems.getDefault().getPath(String.format("%s/municipios.txt", source)));
    }

    /** {@inheritDoc} */
    @Override
    public void inserir(Municipio domain) throws MunicipioException {
        final List<CharSequence> lines = new ArrayList<CharSequence>();

        try {
            final long length = source.length();
            long pos = 0L;
            source.seek(pos);

            if (length > 0) {
                do {
                    lines.add(source.readLine());
                    pos = source.getFilePointer();
                } while (pos < length - 1);

                final int lastLine = lines.size() - 1;
                final String[] fields = lines.get(lastLine).toString().split(";");

                domain.setId(Long.valueOf(fields[0]) + 1);
            } else {
                domain.setId(1L);
            }

            write(domain);
        } catch (Exception cause) {
            throw new MunicipioException(cause);
        }
    }

    private void write(Municipio domain) throws IOException {
        source.write(domain.export().getBytes());
        source.write("\n".getBytes());
    }

    private void write(List<String> lines) throws IOException {
        source.seek(0);
        source.setLength(0);

        for (String line : lines) {
            source.write(line.getBytes());
            source.write("\n".getBytes());
        }
    }

    /** {@inheritDoc} */
    @Override
    public void atualizar(Municipio domain) throws MunicipioException {
        final List<String> lines = new ArrayList<>();

        try {
            String line;
            String[] fields;
            Long id;
            final long length = source.length();
            long pos = 0L;
            source.seek(pos);

            do {
                line = source.readLine();
                fields = line.split(";");
                id = Long.valueOf(fields[0]);
                lines.add(id.equals(domain.getId()) ? domain.export() : line);
                pos = source.getFilePointer();
            } while (pos < length - 1);

            write(lines);
        } catch (Exception cause) {
            throw new MunicipioException("PROBLEMAS AO INSERIR MUNICÍPIO NO ARQUIVO!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void apagar(Municipio domain) throws MunicipioException {
        final List<String> lines = new ArrayList<>();

        try {
            String line;
            String[] fields;
            Long id;
            final long length = source.length();
            long pos = 0L;
            source.seek(pos);

            do {
                line = source.readLine();
                fields = line.split(";");
                id = Long.valueOf(fields[0]);

                if (!id.equals(domain.getId())) {
                    lines.add(line);
                }

                pos = source.getFilePointer();
            } while (pos < length - 1);

            write(lines);
        } catch (Exception cause) {
            throw new MunicipioException("PROBLEMAS AO ATUALIZAR MUNICÍPIO NO ARQUIVO!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Set<Municipio> selecionar(UFVO uf) throws MunicipioException {
        final Set<Municipio> domains = new TreeSet<Municipio>();
        final DecimalFormat nf = new DecimalFormat("##");

        try {
            source.seek(0L);
            String line;
            String[] fields;

            while ((line = source.readLine()) != null && line.length() > 0) {
                fields = line.split(";");

                if (uf.equals(UFVO.valueOf(fields[2]))) {
                    domains.add(new Municipio((Long) nf.parse(fields[0]), fields[1], uf));
                }
            }

            return domains;
        } catch (ParseException | IOException cause) {
            throw new MunicipioException("PROBLEMAS AO SELECIONAR MUNICÍPIOS POR UF DO ARQUIVO!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Set<Municipio> selecionarTodos() throws MunicipioException {
        final Set<Municipio> domains = new TreeSet<Municipio>();
        final DecimalFormat nf = new DecimalFormat("##");

        try {
            source.seek(0L);
            String line;
            String[] fields;

            while ((line = source.readLine()) != null) {
                fields = line.split(";");

                domains.add(new Municipio((Long) nf.parse(fields[0]), fields[1], UFVO.valueOf(fields[2])));
            }

            return domains;
        } catch (ParseException | IOException cause) {
            throw new MunicipioException("PROBLEMAS AO SELECIONAR TODOS OS MUNICÍPIOS DO ARQUIVO!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Integer contarTodos() throws MunicipioException {
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
            throw new MunicipioException("PROBLEMAS AO CONTAR TODOS OS MUNICÍPIOS DO ARQUIVO!", cause);
        }
    }
}
