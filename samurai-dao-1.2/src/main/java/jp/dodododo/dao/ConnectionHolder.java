package jp.dodododo.dao;

import java.sql.Connection;

public interface ConnectionHolder {
	void setConnection(Connection connection);

	Connection getConnection();
}
