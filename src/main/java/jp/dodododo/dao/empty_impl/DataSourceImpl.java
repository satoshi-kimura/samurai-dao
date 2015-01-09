package jp.dodododo.dao.empty_impl;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

public abstract class DataSourceImpl implements DataSource {

	public Connection getConnection() throws SQLException {
		throw new UnsupportedOperationException();
	}

	public Connection getConnection(String arg0, String arg1) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public PrintWriter getLogWriter() throws SQLException {
		throw new UnsupportedOperationException();
	}

	public int getLoginTimeout() throws SQLException {
		throw new UnsupportedOperationException();
	}

	public void setLogWriter(PrintWriter arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public void setLoginTimeout(int arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public <T> T unwrap(Class<T> arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new UnsupportedOperationException();
	}

}
