package jp.dodododo.dao.sql;

import java.sql.Connection;

public interface SConnection extends Connection {

    @Override
	void close();

	Connection unwrap();
}
