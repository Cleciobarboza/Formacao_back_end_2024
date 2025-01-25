package repository.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import domain.exception.MunicipioException;
import domain.model.Municipio;
import domain.model.UFVO;
import repository.MunicipioDAO;

public class MySQLMunicipioDAO extends MySQLDAO implements MunicipioDAO {

    public MySQLMunicipioDAO() throws MySQLException {
        this("localhost");
    }

    public MySQLMunicipioDAO(String source) throws MySQLException {
        super(source);
    }

    /** {@inheritDoc} */
    @Override
    public void inserir(Municipio domain) throws MunicipioException {
        String sql;
        PreparedStatement query;

        // Criar o SQL
        sql = "INSERT INTO municipio (nm_municipio, id_uf) VALUES(?, ?) ";

        try {
            // Criar a consulta
            query = prepareStatement(sql);

            // Preparar a consulta
            query.setString(1, domain.getNome());
            query.setString(2, domain.getUf().toString());

            // Executar a consulta
            int rows = query.executeUpdate();

            // Tratar as exceptions
            if (rows == 0) {
                throw new MunicipioException("NÃO INSERIU!");
            }
        } catch (SQLException cause) {
            throw new MunicipioException("PROBLEMAS AO INSERIR MUNICIPIO NO MYSQL!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void atualizar(Municipio domain) throws MunicipioException {
        String sql;
        PreparedStatement query;

        // Criar o SQL
        sql = "UPDATE municipio SET nm_municipio = ?, id_uf = ? WHERE id_municipio = ?";

        try {
            // Criar a consulta
            query = prepareStatement(sql);

            // Preparar a consulta
            query.setString(1, domain.getNome());
            query.setString(2, domain.getUf().toString());
            query.setLong(3, domain.getId());

            // Executar a consulta
            int rows = query.executeUpdate();

            // Tratar as exceptions
            if (rows == 0) {
                throw new MunicipioException("NÃO ATUALIZOU!");
            }
        } catch (SQLException cause) {
            throw new MunicipioException("PROBLEMAS AO ATUALIZAR MUNICIPIO NO MYSQL!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void apagar(Municipio domain) throws MunicipioException {
        String sql;
        PreparedStatement query;

        // Criar o SQL
        sql = "DELETE FROM municipio WHERE id_municipio = ?";

        try {
            // Criar a consulta
            query = prepareStatement(sql);

            // Preparar a consulta
            query.setLong(1, domain.getId());

            // Executar a consulta
            int rows = query.executeUpdate();

            // Tratar as exceptions
            if (rows == 0) {
                throw new MunicipioException("NÃO APAGOU!");
            }
        } catch (SQLException cause) {
            throw new MunicipioException("PROBLEMAS AO APAGAR MUNICIPIO NO MYSQL!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Set<Municipio> selecionar(UFVO uf) throws MunicipioException {
        String sql;
        PreparedStatement query;
        final Set<Municipio> domains = new TreeSet<Municipio>();

        // Criar o SQL
        sql = "SELECT id_municipio, nm_municipio FROM municipio WHERE id_uf = ? ";

        try {
            // Criar a consulta
            query = prepareStatement(sql);

            // Preparar a consulta
            query.setString(1, uf.toString());

            // Executar a consulta
            ResultSet rs = query.executeQuery();
            Municipio domain;

            while (rs.next()) {
                domain = new Municipio();

                domain.setId(rs.getLong("id_municipio"));
                domain.setNome(rs.getString("nm_municipio"));
                domain.setUf(uf);

                domains.add(domain);
            }

            return domains;
        } catch (SQLException cause) {
            // Tratar as exceptions
            throw new MunicipioException("PROBLEMAS AO SELECIONAR MUNICIPIOS!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Set<Municipio> selecionarTodos() throws MunicipioException {
        String sql;
        PreparedStatement query;
        final Set<Municipio> domains = new TreeSet<Municipio>();

        // Criar o SQL
        sql = "SELECT id_municipio, nm_municipio, id_uf FROM municipio";

        try {
            // Criar a consulta
            query = prepareStatement(sql);

            // Executar a consulta
            ResultSet rs = query.executeQuery();
            Municipio domain;

            while (rs.next()) {
                domain = new Municipio();

                domain.setId(rs.getLong("id_municipio"));
                domain.setNome(rs.getString("nm_municipio"));
                domain.setUf(UFVO.valueOf(rs.getString("id_uf")));

                domains.add(domain);
            }

            return domains;
        } catch (SQLException cause) {
            // Tratar as exceptions
            throw new MunicipioException("PROBLEMAS AO SELECIONAR MUNICIPIOS!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Integer contarTodos() throws MunicipioException {
        String sql;
        PreparedStatement query;

        // Criar o SQL
        sql = "SELECT COUNT(id_municipio) AS total FROM municipio";

        try {
            // Criar a consulta
            query = prepareStatement(sql);

            // Executar a consulta
            ResultSet rs = query.executeQuery();

            if (rs.next()) {
                return rs.getInt("total");
            }

            return 0;
        } catch (SQLException cause) {
            // Tratar as exceptions
            throw new MunicipioException("PROBLEMAS AO CONTAR MUNICIPIOS!", cause);
        }
    }
}
