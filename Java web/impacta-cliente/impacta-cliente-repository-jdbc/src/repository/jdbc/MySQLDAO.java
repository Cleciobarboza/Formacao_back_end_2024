package repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.Closeable;

abstract class MySQLDAO implements Closeable {

	private Connection c;

	MySQLDAO(String source) throws MySQLException {
		super();

		this.c = MySQLDataSource.getConnection(source);
	}

	PreparedStatement prepareStatement(String sql) throws SQLException {
		return c.prepareStatement(sql);
	}

	@Override
	public void close() throws Exception {
		MySQLDataSource.close(c);
	}
}
