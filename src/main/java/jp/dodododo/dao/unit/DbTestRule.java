package jp.dodododo.dao.unit;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

import javax.sql.DataSource;

import jp.dodododo.dao.empty_impl.DataSourceImpl;
import jp.dodododo.dao.error.SQLError;
import jp.dodododo.dao.util.ClassUtil;
import jp.dodododo.dao.wrapper.ConnectionWrapper;

import org.junit.rules.ExternalResource;

public class DbTestRule extends ExternalResource {

	protected DBConfig config;

	protected Connection connection;

	protected DataSource dataSource;

	protected Driver driver;

	public DbTestRule() {
		Properties properties = new Properties();
		try (InputStream in = DbTestRule.class.getResourceAsStream("/db.properties")) {
			properties.load(in);
			DBConfig config = ClassUtil.newInstance(properties.getProperty("config"));
			this.config = config;
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	public DbTestRule(DBConfig config) {
		this.config = config;
	}

	@Override
	protected void before() throws Throwable {
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		for (; drivers.hasMoreElements();) {
			DriverManager.deregisterDriver(drivers.nextElement());
		}
		driver = ClassUtil.newInstance(config.driverClassName());
		DriverManager.registerDriver(driver);
		this.connection = DriverManager.getConnection(config.URL(), config.properties());
		this.connection.setAutoCommit(false);
		final Connection connection = this.connection;
		this.dataSource = new DataSourceImpl() {
			@Override
			public Connection getConnection() throws SQLException {
				return new ConnectionWrapper(connection) {
					@Override
					public void close() throws SQLException {
					}
				};
			}
		};
	}

	@Override
	protected void after() {
		try {
			getConnection().rollback();
			this.connection.close();
		} catch (SQLException e) {
			throw new SQLError(e);
		} finally {
			try {
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
				throw new SQLError(e);
			} finally {
				driver = null;
			}
		}
	}

	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new SQLError(e);
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}

}
