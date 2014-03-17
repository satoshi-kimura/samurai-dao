package jp.dodododo.dao.sql.node;

import jp.dodododo.dao.context.CommandContext;

public class SqlNode extends AbstractNode {

	private String sql;

	public SqlNode(String sql) {
		this.sql = sql;
	}

	public void accept(CommandContext ctx) {
		ctx.addSql(sql);
	}

}
