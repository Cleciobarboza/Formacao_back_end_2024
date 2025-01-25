package repository.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import domain.exception.LogradouroException;
import domain.model.Bairro;
import domain.model.Logradouro;
import domain.model.LogradouroVO;
import repository.LogradouroDAO;

public class MySQLLogradouroDAO extends MySQLDAO implements LogradouroDAO {

    public MySQLLogradouroDAO() throws MySQLException {
        this("localhost");
    }

    public MySQLLogradouroDAO(String ip) throws MySQLException {
        super(ip);
    }

    /** {@inheritDoc} */
    @Override
    public void inserir(Logradouro domain) throws LogradouroException {
        String sql;
        PreparedStatement query;

        // Criar o SQL
        sql = "INSERT INTO logradouro (tp_logradouro, nm_logradouro, id_bairro) VALUES(?, ?, ?) ";

        try {
            // Criar a consulta
            query = prepareStatement(sql);

            // Preparar a consulta
            query.setInt(1, domain.getTipo().ordinal());
            query.setString(2, domain.getNome());
            query.setInt(3, domain.getBairro().getId());

            // Executar a consulta
            int rows = query.executeUpdate();

            // Tratar as exceptions
            if (rows == 0) {
                throw new LogradouroException("N�O INSERIU!");
            }
        } catch (SQLException cause) {
            throw new LogradouroException("PROBLEMAS AO INSERIR LOGRADOURO NO MYSQL!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void atualizar(Logradouro domain) throws LogradouroException {
        String sql;
        PreparedStatement query;

        // Criar o SQL
        sql = "UPDATE logradouro SET tp_logradouro = ?, nm_logradouro = ?, id_bairro = ? WHERE id_logradouro = ?";

        try {
            // Criar a consulta
            query = prepareStatement(sql);

            // Preparar a consulta
            query.setInt(1, domain.getTipo().ordinal());
            query.setString(2, domain.getNome());
            query.setInt(3, domain.getBairro().getId());
            query.setInt(4, domain.getId());

            // Executar a consulta
            int rows = query.executeUpdate();

            // Tratar as exceptions
            if (rows == 0) {
                throw new LogradouroException("N�O ATUALIZOU!");
            }
        } catch (SQLException cause) {
            throw new LogradouroException("PROBLEMAS AO ATUALIZAR LOGRADOURO NO MYSQL!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void apagar(Logradouro domain) throws LogradouroException {
        String sql;
        PreparedStatement query;

        // Criar o SQL
        sql = "DELETE FROM logradouro WHERE id_logradouro = ?";

        try {
            // Criar a consulta
            query = prepareStatement(sql);

            // Preparar a consulta
            query.setInt(1, domain.getId());

            // Executar a consulta
            int rows = query.executeUpdate();

            // Tratar as exceptions
            if (rows == 0) {
                throw new LogradouroException("N�O APAGOU!");
            }
        } catch (SQLException cause) {
            throw new LogradouroException("PROBLEMAS AO APAGAR LOGRADOURO NO MYSQL!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Set<Logradouro> selecionar(Bairro bairro) throws LogradouroException {
        String sql;
        PreparedStatement query;
        final Set<Logradouro> domains = new HashSet<Logradouro>();

        // Criar o SQL
        sql = "SELECT id_logradouro, tp_logradouro, nm_logradouro FROM logradouro WHERE id_bairro = ?";

        try {
            // Criar a consulta
            query = prepareStatement(sql);

            // Preparar a consulta
            query.setInt(1, bairro.getId());

            // Executar a consulta
            ResultSet rs = query.executeQuery();
            Logradouro domain;

            while (rs.next()) {
                domain = Logradouro.createInstance();

                domain.setId(rs.getInt("id_logradouro"));
                domain.setTipo(LogradouroVO.valueOf(rs.getInt("tp_logradouro")));
                domain.setNome(rs.getString("nm_logradouro"));
                domain.setBairro(bairro);

                domains.add(domain);
            }

            return domains;
        } catch (SQLException cause) {
            // Tratar as exceptions
            throw new LogradouroException("PROBLEMAS AO SELECIONAR LOGRADOUROS!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Set<Logradouro> selecionarTodos() throws LogradouroException {
        String sql;
        PreparedStatement query;
        final Set<Logradouro> domains = new HashSet<Logradouro>();

        // Criar o SQL
        sql = "SELECT id_logradouro, tp_logradouro, nm_logradouro, id_bairro FROM logradouro";

        try {
            // Criar a consulta
            query = prepareStatement(sql);

            // Executar a consulta
            ResultSet rs = query.executeQuery();
            Logradouro domain;

            while (rs.next()) {
                domain = Logradouro.createInstance();

                domain.setId(rs.getInt("id_logradouro"));
                domain.setTipo(LogradouroVO.valueOf(rs.getInt("tp_logradouro")));
                domain.setNome(rs.getString("nm_logradouro"));
                domain.setBairro(Bairro.createInstance(rs.getInt("id_bairro")));

                domains.add(domain);
            }

            return domains;
        } catch (SQLException cause) {
            // Tratar as exceptions
            throw new LogradouroException("PROBLEMAS AO SELECIONAR LOGRADOUROS!", cause);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Integer contarTodos() throws LogradouroException {
        String sql;
        PreparedStatement query;

        // Criar o SQL
        sql = "SELECT COUNT(id_logradouro) AS total FROM logradouro";

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
            throw new LogradouroException("PROBLEMAS AO CONTAR LOGRADOUROS!", cause);
        }
    }
}
