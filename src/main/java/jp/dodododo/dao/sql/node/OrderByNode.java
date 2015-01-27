package jp.dodododo.dao.sql.node;

import jp.dodododo.dao.context.CommandContext;
import jp.dodododo.dao.util.EmptyUtil;
import jp.dodododo.dao.util.OgnlUtil;


/**
 * @author Satoshi Kimura
 */
public class OrderByNode extends AbstractNode {
	private String expression;

	public OrderByNode(String expression) {
		this.expression = expression;
	}

    @Override
	public void accept(CommandContext ctx) {
		Object object = OgnlUtil.parseExpression(expression);
		Object value = OgnlUtil.getValue(object, ctx);
		if (value != null) {
			String sql = value.toString().trim();
			if (EmptyUtil.isEmpty(sql) == false) {
				ctx.addSql("ORDER BY " + sql);
			}
		}
	}
}
