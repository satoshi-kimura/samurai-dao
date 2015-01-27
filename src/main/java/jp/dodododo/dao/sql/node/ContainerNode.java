package jp.dodododo.dao.sql.node;

import jp.dodododo.dao.context.CommandContext;

public class ContainerNode extends AbstractNode {
    @Override
	public void accept(CommandContext ctx) {
		getChildren().forEach(child -> child.accept(ctx));
	}
}
