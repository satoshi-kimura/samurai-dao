package jp.dodododo.dao.sql.node;

import jp.dodododo.dao.context.CommandContext;
import jp.dodododo.dao.util.StringUtil;

/**
 * 
 * @author Satoshi Kimura
 */
public class BeginNode extends ContainerNode {

	@Override
	public void accept(CommandContext ctx) {
		CommandContext childCtx = new CommandContext(ctx);
		super.accept(childCtx);
		if (childCtx.isEnabled()) {
			String sql = childCtx.getSql();
			if (StringUtil.trimLine(sql).equalsIgnoreCase("where\n") == false && StringUtil.trimLine(sql).equalsIgnoreCase("where") == false) {
				ctx.addSql(sql, childCtx.getBindVariables(), childCtx.getBindVariableTypes());
			}
		}
	}

}
