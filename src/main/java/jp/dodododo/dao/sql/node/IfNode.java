package jp.dodododo.dao.sql.node;

import jp.dodododo.dao.context.CommandContext;
import jp.dodododo.dao.exception.IllegalBoolExpressionRuntimeException;
import jp.dodododo.dao.util.OgnlUtil;

public class IfNode extends ContainerNode {

	private String expression;

	private Object parsedExpression;

	private ElseNode elseNode;

	public IfNode(String expression) {
		this.expression = expression;
		this.parsedExpression = OgnlUtil.parseExpression(expression);
	}

	public void setElseNode(ElseNode elseNode) {
		this.elseNode = elseNode;
	}

	@Override
	public void accept(CommandContext ctx) {
		Object result = OgnlUtil.getValue(parsedExpression, ctx);
		if (result instanceof Boolean) {
			if (((Boolean) result).booleanValue()) {
				super.accept(ctx);
				ctx.setEnabled(true);
			} else if (elseNode != null) {
				elseNode.accept(ctx);
				ctx.setEnabled(true);
			}
		} else {
			throw new IllegalBoolExpressionRuntimeException(expression);
		}
	}

}
