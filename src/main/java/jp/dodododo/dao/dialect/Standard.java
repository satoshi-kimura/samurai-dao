package jp.dodododo.dao.dialect;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.dodododo.dao.metadata.ColumnMetaData;
import jp.dodododo.dao.metadata.TableMetaData;
import jp.dodododo.dao.object.PropertyDesc;
import jp.dodododo.dao.paging.LimitOffset;
import jp.dodododo.dao.util.ConnectionUtil;
import jp.dodododo.dao.util.InputStreamUtil;

public class Standard implements Dialect {

    @Override
	public String limitOffsetSql(String originalSQL, LimitOffset limitOffset) {
		long offset = limitOffset.getOffset();
		long limit = limitOffset.getLimit();
		StringBuilder sqlBuf = new StringBuilder(originalSQL);
		sqlBuf.append(" LIMIT ");
		sqlBuf.append(limit);
		sqlBuf.append(" OFFSET ");
		sqlBuf.append(offset);
		return sqlBuf.toString();
	}

    @Override
	public String sequenceNextValSql(String sequenceName) {
		throw new UnsupportedOperationException("sequenceName : " + sequenceName);
	}

    @Override
	public String identitySelectSql() {
		throw new UnsupportedOperationException();
	}

    @Override
	public String getSuffix() {
		throw new UnsupportedOperationException();
	}

    @Override
	public ResultSet resultSet(ResultSet rs) {
		return rs;
	}

    @Override
	public String toDateString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return "'" + sdf.format(date) + "'";
	}

    @Override
	public String toTimestampString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		return "'" + sdf.format(date) + "'";
	}

    @Override
	public boolean isPrepareIdentitySelectSql() {
		return true;
	}

    @Override
	public PreparedStatement preparedStatement(PreparedStatement ps) {
		return ps;
	}

    @Override
	public BigDecimal getBigDecimal(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getBigDecimal(columnIndex);
	}

    @Override
	public void setBinaryStream(PreparedStatement ps, int index, InputStream inputStream) throws SQLException {
		ps.setBinaryStream(index, inputStream, InputStreamUtil.available(inputStream));
	}

    @Override
	public String getVersion() {
		return null;
	}

    @Override
	public void setId(Object entity, PropertyDesc propertyDesc, Object idValue) {
		if (idValue == null) {
			return;
		}
		propertyDesc.setValue(entity, idValue);
	}

    @Override
	public void bugfix(ColumnMetaData columnMetaData) {
	}

	@Override
	public boolean isSupportMultiRowInsert() {
		return true;
	}

	@Override
	public String getReplaceCommand() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getReplaceOption() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getSchema(Connection connection) {
		return ConnectionUtil.getSchema(connection);
	}

	@Override
	public String getSchema(TableMetaData metaData) {
		return metaData.getSchema();
	}
}
