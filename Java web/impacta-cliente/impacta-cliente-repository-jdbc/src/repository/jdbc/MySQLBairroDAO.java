package repository.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import domain.exception.BairroException;
import domain.model.Bairro;
import domain.model.Municipio;
import repository.BairroDAO;

public class MySQLBairroDAO extends MySQLDAO implements BairroDAO {

    public MySQLBairroDAO() throws MySQLException {
        this("localhost");
    }

    public MySQLBairroDAO(String ip) throws MySQLException {
        super(ip);
    }

    /** {@inheritDoc} */
    @Override
    public void inserir(Bairro domain) throws BairroException {
        String sql;
        PreparedStatement query;

        // Criar o SQL
        sql = "INSERT INTO bairro (nm_bairro, id_municipio) VALUES(?, ?) ";

        try {
            // Criar a consulta
            query = prepareStatement(sql);

            // Preparar a consulta
            query.setString(1, domain.getNome());
            query.setLong(2, domain.getMunicipio().getId());

            // Executar a consulta
            int rows = query.executeUpdate();

            // Tratar as exceptions
            if (rows == 0) {
                throw new BairroException("N�O INSERIU!");
            }
        } catch (SQLException cause) {
            throw new BairroException("PROBLEMAS AO INSERIR BAIRRO NO MYSQL!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void atualizar(Bairro domain) throws BairroException {
        String sql;
        PreparedStatement query;

        // Criar o SQL
        sql = "UPDATE bairro SET nm_bairro = ?, id_municipio = ? WHERE id_bairro = ?";

        try {
            // Criar a consulta
            query = prepareStatement(sql);

            // Preparar a consulta
            query.setString(1, domain.getNome());
            query.setLong(2, domain.getMunicipio().getId());
            query.setLong(3, domain.getId());

            // Executar a consulta
            int rows = query.executeUpdate();

            // Tratar as exceptions
            if (rows == 0) {
                throw new BairroException("N�O ATUALIZOU!");
            }
        } catch (SQLException cause) {
            throw new BairroException("PROBLEMAS AO ATUALIZAR BAIRRO NO MYSQL!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void apagar(Bairro domain) throws BairroException {
        String sql;
        PreparedStatement query;

        // Criar o SQL
        sql = "DELETE FROM bairro WHERE id_bairro = ?";

        try {
            // Criar a consulta
            query = prepareStatement(sql);

            // Preparar a consulta
            query.setLong(1, domain.getId());

            // Executar a consulta
            int rows = query.executeUpdate();

            // Tratar as exceptions
            if (rows == 0) {
                throw new BairroException("N�O APAGOU!");
            }
        } catch (SQLException cause) {
            throw new BairroException("PROBLEMAS AO APAGAR BAIRRO NO MYSQL!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Set<Bairro> selecionar(Municipio municipio) throws BairroException {
        String sql;
        PreparedStatement query;
        final Set<Bairro> domains = new HashSet<Bairro>();

        // Criar o SQL
        sql = "SELECT id_bairro, nm_bairro FROM bairro WHERE id_municipio = ? ";

        try {
            // Criar a consulta
            query = prepareStatement(sql);

            // Preparar a consulta
            query.setLong(1, municipio.getId());

            // Executar a consulta
            ResultSet rs = query.executeQuery();
            Bairro domain;

            while (rs.next()) {
                domain = new Bairro();

                domain.setId(rs.getInt("id_bairro"));
                domain.setNome(rs.getString("nm_bairro"));
                domain.setMunicipio(municipio);

                domains.add(domain);
            }

            return domains;
        } catch (SQLException cause) {
            // Tratar as exceptions
            throw new BairroException("PROBLEMAS AO SELECIONAR BAIRROS!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Set<Bairro> selecionarTodos() throws BairroException {
        String sql;
        PreparedStatement query;
        final Set<Bairro> domains = new HashSet<Bairro>();

        // Criar o SQL
        sql = "SELECT id_bairro, nm_bairro, id_municipio FROM bairro";

        try {
            // Criar a consulta
            query = prepareStatement(sql);

            // Executar a consulta
            ResultSet rs = query.executeQuery();
            Bairro domain;

            while (rs.next()) {
                domain = Bairro.createInstance();

                domain.setId(rs.getInt("id_bairro"));
                domain.setNome(rs.getString("nm_bairro"));
                domain.setMunicipio(Municipio.createInstance(rs.getLong("id_municipio")));

                domains.add(domain);
            }

            return domains;
        } catch (SQLException cause) {
            // Tratar as exceptions
            throw new BairroException("PROBLEMAS AO SELECIONAR BAIRROS!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Integer contarTodos() throws BairroException {
        String sql;
        PreparedStatement query;

        // Criar o SQL
        sql = "SELECT COUNT(id_bairro) AS total FROM bairro";

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
            throw new BairroException("PROBLEMAS AO CONTAR BAIRROS!", cause);
        }
    }
}
