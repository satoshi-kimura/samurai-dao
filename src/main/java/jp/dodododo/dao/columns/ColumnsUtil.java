package jp.dodododo.dao.columns;

/**
 * 
 * @author Satoshi Kimura
 */
public abstract class ColumnsUtil {
	public static NoPersistentColumns npc(String... columns) {
		return new NoPersistentColumns(columns);
	}

	public static PersistentColumns pc(String... columns) {
		return new PersistentColumns(columns);
	}
}
