package jp.dodododo.dao;

import jp.dodododo.dao.config.DaoConfig;
import jp.dodododo.dao.log.SqlLogRegistry;

/**
 * {@.en }
 *
 * <br />
 *
 * {@.ja }
 *
 * @author skimura
 */
public interface Dao extends SelectDao, ExecuteUpdateDao {
	SqlLogRegistry getSqlLogRegistry();

	void setSqlLogRegistry(SqlLogRegistry sqlLogRegistry);

	void setQueryTimeout(int seconds);

	DaoConfig getConfig();
}
