package jp.dodododo.dao.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jp.dodododo.dao.annotation.Internal;
import jp.dodododo.dao.dialect.Dialect;
import jp.dodododo.dao.exception.ArgNotFoundException;
import jp.dodododo.dao.message.Message;
import jp.dodododo.dao.types.SQLTypes;
import jp.dodododo.dao.util.CaseInsensitiveMap;
import jp.dodododo.dao.util.DaoUtil;
import jp.dodododo.dao.value.ParameterValue;
import ognl.OgnlRuntime;

/**
 *
 * @author Satoshi Kimura
 */
@Internal
public class CommandContext {
	private CaseInsensitiveMap<Object> args = new CaseInsensitiveMap<Object>();

	private CaseInsensitiveMap<Integer> argTypes = new CaseInsensitiveMap<Integer>();

	private StringBuilder sqlBuf = new StringBuilder(100);

	private List<Object> bindVariables = new ArrayList<Object>();

	private List<Integer> bindVariableTypes = new ArrayList<Integer>();

	private boolean enabled = true;

	private CommandContext parent;

	private Dialect dialect;

	static {
		OgnlRuntime.setPropertyAccessor(CommandContext.class, new CommandContextPropertyAccessor());
	}

	public CommandContext(Dialect dialect) {
		this.dialect = dialect;
	}

	public CommandContext(CommandContext parent) {
		this.parent = parent;
		enabled = false;
	}

	public Object getArg(String name) {
		if (args.containsKey(name)) {
			return args.get(name);
		} else if (parent != null) {
			return parent.getArg(name);
		} else {
			throw new ArgNotFoundException(name, "");
		}
	}

	public Integer getArgType(String name) {
		if (argTypes.containsKey(name)) {
			return argTypes.get(name);
		} else if (parent != null) {
			return parent.getArgType(name);
		} else {
			throw new RuntimeException(Message.getMessage("00013", name));
		}
	}

	public String getSql() {
		return sqlBuf.toString();
	}

	public List<Object> getBindVariables() {
		return bindVariables;
	}

	public List<Integer> getBindVariableTypes() {
		return bindVariableTypes;
	}

	public CommandContext addSql(String sql) {
		sqlBuf.append(sql);
		return this;
	}

	public CommandContext addSql(String sql, Object bindVariable, int bindVariableType) {

		sqlBuf.append(sql);
		bindVariables.add(bindVariable);
		bindVariableTypes.add(bindVariableType);
		return this;
	}

	public CommandContext addSql(String sql, List<Object> bindVariables, List<Integer> bindVariableTypes) {
		sqlBuf.append(sql);
		for (int i = 0; i < bindVariables.size(); ++i) {
			this.bindVariables.add(bindVariables.get(i));
			this.bindVariableTypes.add(bindVariableTypes.get(i));
		}
		return this;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void addArgs(Map<String, ParameterValue> values) {
		Set<Entry<String, ParameterValue>> entrySet = values.entrySet();
		entrySet.forEach(set -> {
			ParameterValue value = set.getValue();
			String name = value.getName();
			args.put(name, value.getValue());
			argTypes.put(name, value.getDataType());
		});
	}

	public Dialect getDialect() {
		if (dialect == null) {
			return parent.getDialect();
		}
		return dialect;
	}

	public Map<String, Object> getArgs() {
		return args;
	}

	public void addValues(List<?> values) {
		Map<String, ParameterValue> arg = new HashMap<String, ParameterValue>();
		arg.put(DaoUtil.VALUES, new ParameterValue(DaoUtil.VALUES, SQLTypes.OBJECT.getType(), values, false));
		addArgs(arg);
	}
}
