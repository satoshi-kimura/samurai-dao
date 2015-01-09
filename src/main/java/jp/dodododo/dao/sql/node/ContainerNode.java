package jp.dodododo.dao.sql.node;

import jp.dodododo.dao.context.CommandContext;

public class ContainerNode extends AbstractNode {
	public void accept(CommandContext ctx) {
		for (int i = 0; i < getChildSize(); ++i) {
			getChild(i).accept(ctx);
		}
	}
}
