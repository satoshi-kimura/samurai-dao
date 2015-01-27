package jp.dodododo.dao.id;

import java.sql.Connection;
import java.util.UUID;

import jp.dodododo.dao.dialect.Dialect;

public enum RandomUUID implements IdGenerator {
	RANDOM_UUID;

    @Override
	public Object generate(Connection connection, String sequenceName) {
		return UUID.randomUUID();
	}

    @Override
	public Object generate(Connection connection, Dialect dialect, String sequenceName) {
		return UUID.randomUUID();
	}

    @Override
	public boolean isPrepare(Connection connection) {
		return true;
	}

    @Override
	public boolean isPrepare(Dialect dialect) {
		return true;
	}

}
