package jp.dodododo.dao.sql;

import java.sql.Connection;

public interface SConnection extends Connection {

	void close();

	Connection unwrap();
}
