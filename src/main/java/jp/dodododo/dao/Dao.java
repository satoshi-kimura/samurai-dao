package jp.dodododo.dao;

import jp.dodododo.dao.config.DaoConfig;
import jp.dodododo.dao.log.SqlLogRegistry;

/**
 *
 * @author skimura
 */
public interface Dao extends SelectDao, ExecuteUpdateDao {
	SqlLogRegistry getSqlLogRegistry();

	@Deprecated
	void setSqlLogRegistry(SqlLogRegistry sqlLogRegistry);

	void setQueryTimeout(int seconds);

	DaoConfig getConfig();
}
