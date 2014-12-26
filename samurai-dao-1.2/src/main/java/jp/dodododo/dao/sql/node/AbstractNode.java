package jp.dodododo.dao.sql.node;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import jp.dodododo.dao.annotation.Dialects;
import jp.dodododo.dao.context.CommandContext;
import jp.dodododo.dao.dialect.Default;
import jp.dodododo.dao.dialect.Dialect;
import jp.dodododo.dao.message.Message;
import jp.dodododo.dao.object.ObjectDesc;
import jp.dodododo.dao.object.ObjectDescFactory;
import jp.dodododo.dao.object.PropertyDesc;
import jp.dodododo.dao.types.SQLType;
import jp.dodododo.dao.util.OgnlUtil;
import jp.dodododo.dao.util.TypesUtil;

/**
 *
 * @author Satoshi Kimura
 */
public abstract class AbstractNode implements Node {

	private List<Node> children = new ArrayList<Node>();

	protected Node parent;

	protected String sql;

	public AbstractNode() {
	}

	public AbstractNode(Node parent) {
		this.parent = parent;
	}

	public List<Node> getChildren() {
		return children;
	}

	public int getChildSize() {
		return children.size();
	}

	public boolean isEmpty() {
		return children.isEmpty();
	}

	public Node getChild(int index) {
		return children.get(index);
	}

	public void addChild(Node node) {
		children.add(node);
		node.setParent(this);
	}

	protected int getType(Object value) {
		SQLType type = TypesUtil.getSQLType(value);
		return type.getType();
	}

	protected int getType(Class<?> clazz) {
		SQLType type = TypesUtil.getSQLType(clazz);
		return type.getType();
	}

	/**
	 * "'val'"や"'foo val bar'"ならtrue<br>
	 * "'' val"や、"val 'val'"はfalse
	 */
	protected boolean isEnclosedBySingleQuot(String sql, String val) {
		int valLength = val.length();
		boolean enclosed = false;
		for (int i = 0; i < sql.length(); i++) {
			char c = sql.charAt(i);
			if (c == '\'') {
				enclosed = !enclosed;
			}
			if (enclosed == true) {
				continue;
			}
			if (val.equals(sql.substring(i, i + valLength)) == true) {
				return false;
			}
		}
		if (enclosed == true) {
			throw new IllegalArgumentException(Message.getMessage("00011"));
		}
		return true;
	}

	protected Object getValue(String expression, String[] names, Object root, Dialect dialect) {
		Object ret = OgnlUtil.getValue(expression, root);
		if (ret != null) {
			return ret;
		}

		StringBuilder exp = new StringBuilder(names[0]);
		for (int i = 1; i < names.length - 1; i++) {
			exp.append(".");
			exp.append(names[i]);
		}
		Object parent = OgnlUtil.getValue(exp.toString(), root);
		if (parent == null) {
			return null;
		}
		ObjectDesc<?> parentDesc = ObjectDescFactory.getObjectDesc(parent);
		String propertyName = names[names.length - 1];
		PropertyDesc propertyDesc = parentDesc.getPropertyDesc(propertyName);
		Dialects annotation = propertyDesc.getAnnotation(Dialects.class);
		if (annotation != null) {
			ret = getValue(annotation, dialect);
			if (ret != null) {
				return ret;
			}
		}
		ret = propertyDesc.getValue(parent);
		if (ret != null) {
			return ret;
		}
		return null;
	}

	private Object getValue(Dialects expression, Dialect dialect) {
		Class<? extends Dialect>[] dialects = expression.dialect();
		Object defaultValue = null;
		for (int i = 0; i < dialects.length; i++) {
			Class<? extends Dialect> dialectClass = dialects[i];
			if (dialectClass.equals(dialect.getClass())) {
				return expression.value()[i];
			}
			if (dialectClass.equals(Default.class)) {
				defaultValue = expression.value()[i];
			}
		}
		return defaultValue;
	}

	protected void bindArray(CommandContext ctx, Object array) {
		int length = Array.getLength(array);
		if (length == 0) {
			return;
		}
		Class<?> clazz = null;
		for (int i = 0; i < length; ++i) {
			Object o = Array.get(array, i);
			if (o != null) {
				clazz = o.getClass();
			}
		}
		ctx.addSql("(");
		int type = getType(clazz);
		ctx.addSql("?", Array.get(array, 0), type);
		for (int i = 1; i < length; ++i) {
			ctx.addSql(", ?", Array.get(array, i), type);
		}
		ctx.addSql(")");
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		if (sql == null) {
			return parent.getSql();
		}
		return sql;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

}
