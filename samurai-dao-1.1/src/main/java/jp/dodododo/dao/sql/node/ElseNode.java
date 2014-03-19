package jp.dodododo.dao.sql.node;

import jp.dodododo.dao.context.CommandContext;

public class ElseNode extends ContainerNode {
	@Override
	public void accept(CommandContext ctx) {
		super.accept(ctx);
		ctx.setEnabled(true);
	}
}