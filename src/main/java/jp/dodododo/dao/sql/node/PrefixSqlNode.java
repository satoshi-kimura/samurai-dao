package jp.dodododo.dao.sql.node;

import jp.dodododo.dao.context.CommandContext;

public class PrefixSqlNode extends AbstractNode {

	private String prefix;

	private String sql;

	public PrefixSqlNode(String prefix, String sql) {
		this.prefix = prefix;
		this.sql = sql;
	}

	public String getPrefix() {
		return prefix;
	}

	@Override
	public String getSql() {
		return sql;
	}

	public void accept(CommandContext ctx) {
		if (ctx.isEnabled()) {
			ctx.addSql(prefix);
		}
		ctx.addSql(sql);
	}

}
