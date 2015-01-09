package jp.dodododo.dao.impl;

import java.sql.Connection;

import javax.sql.DataSource;

/**
 * @deprecated
 */
@Deprecated
public class DaoImpl extends RdbDao {

	public DaoImpl() {
		super();
	}

	public DaoImpl(Connection connection) {
		super(connection);
	}

	public DaoImpl(DataSource dataSource) {
		super(dataSource);
	}

	public DaoImpl(String jndiName) {
		super(jndiName);
	}

}
