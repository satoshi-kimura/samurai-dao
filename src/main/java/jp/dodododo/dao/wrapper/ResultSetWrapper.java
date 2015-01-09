package jp.dodododo.dao.wrapper;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

/**
 *
 * @author Satoshi Kimura
 */
public class ResultSetWrapper implements ResultSet {
	protected ResultSet rs;
	private String sql;

	protected ResultSetWrapper(ResultSet rs) {
		this(rs, null);
	}

	public ResultSetWrapper(ResultSet rs, String sql) {
		this.rs = rs;
		this.sql = sql;
	}

	public String getSql() {
		if (sql != null) {
			return sql;
		}
		if (rs instanceof ResultSetWrapper) {
			ResultSetWrapper wrapper = (ResultSetWrapper) rs;
			return wrapper.getSql();
		}
		return null;
	}

	public boolean absolute(int i) throws SQLException {
		return rs.absolute(i);
	}

	public void afterLast() throws SQLException {
		rs.afterLast();
	}

	public void beforeFirst() throws SQLException {
		rs.beforeFirst();
	}

	public void cancelRowUpdates() throws SQLException {
		rs.cancelRowUpdates();
	}

	public void clearWarnings() throws SQLException {
		rs.clearWarnings();
	}

	public void close() throws SQLException {
		rs.close();
	}

	public void deleteRow() throws SQLException {
		rs.deleteRow();
	}

	public int findColumn(String s) throws SQLException {
		return rs.findColumn(s);
	}

	public boolean first() throws SQLException {
		return rs.first();
	}

	public Array getArray(int i) throws SQLException {
		return rs.getArray(i);
	}

	public Array getArray(String s) throws SQLException {
		return rs.getArray(s);
	}

	public InputStream getAsciiStream(int i) throws SQLException {
		return rs.getAsciiStream(i);
	}

	public InputStream getAsciiStream(String s) throws SQLException {
		return rs.getAsciiStream(s);
	}

	@Deprecated
	public BigDecimal getBigDecimal(int i, int j) throws SQLException {
		return rs.getBigDecimal(i, j);
	}

	public BigDecimal getBigDecimal(int i) throws SQLException {
		return rs.getBigDecimal(i);
	}

	@Deprecated
	public BigDecimal getBigDecimal(String s, int i) throws SQLException {
		return rs.getBigDecimal(s, i);
	}

	public BigDecimal getBigDecimal(String s) throws SQLException {
		return rs.getBigDecimal(s);
	}

	public InputStream getBinaryStream(int i) throws SQLException {
		return rs.getBinaryStream(i);
	}

	public InputStream getBinaryStream(String s) throws SQLException {
		return rs.getBinaryStream(s);
	}

	public Blob getBlob(int i) throws SQLException {
		return rs.getBlob(i);
	}

	public Blob getBlob(String s) throws SQLException {
		return rs.getBlob(s);
	}

	public boolean getBoolean(int i) throws SQLException {
		return rs.getBoolean(i);
	}

	public boolean getBoolean(String s) throws SQLException {
		return rs.getBoolean(s);
	}

	public byte getByte(int i) throws SQLException {
		return rs.getByte(i);
	}

	public byte getByte(String s) throws SQLException {
		return rs.getByte(s);
	}

	public byte[] getBytes(int i) throws SQLException {
		return rs.getBytes(i);
	}

	public byte[] getBytes(String s) throws SQLException {
		return rs.getBytes(s);
	}

	public Reader getCharacterStream(int i) throws SQLException {
		return rs.getCharacterStream(i);
	}

	public Reader getCharacterStream(String s) throws SQLException {
		return rs.getCharacterStream(s);
	}

	public Clob getClob(int i) throws SQLException {
		return rs.getClob(i);
	}

	public Clob getClob(String s) throws SQLException {
		return rs.getClob(s);
	}

	public int getConcurrency() throws SQLException {
		return rs.getConcurrency();
	}

	public String getCursorName() throws SQLException {
		return rs.getCursorName();
	}

	public Date getDate(int i, Calendar calendar) throws SQLException {
		return rs.getDate(i, calendar);
	}

	public Date getDate(int i) throws SQLException {
		return rs.getDate(i);
	}

	public Date getDate(String s, Calendar calendar) throws SQLException {
		return rs.getDate(s, calendar);
	}

	public Date getDate(String s) throws SQLException {
		return rs.getDate(s);
	}

	public double getDouble(int i) throws SQLException {
		return rs.getDouble(i);
	}

	public double getDouble(String s) throws SQLException {
		return rs.getDouble(s);
	}

	public int getFetchDirection() throws SQLException {
		return rs.getFetchDirection();
	}

	public int getFetchSize() throws SQLException {
		return rs.getFetchSize();
	}

	public float getFloat(int i) throws SQLException {
		return rs.getFloat(i);
	}

	public float getFloat(String s) throws SQLException {
		return rs.getFloat(s);
	}

	public int getHoldability() throws SQLException {
		return rs.getHoldability();
	}

	public int getInt(int i) throws SQLException {
		return rs.getInt(i);
	}

	public int getInt(String s) throws SQLException {
		return rs.getInt(s);
	}

	public long getLong(int i) throws SQLException {
		return rs.getLong(i);
	}

	public long getLong(String s) throws SQLException {
		return rs.getLong(s);
	}

	public ResultSetMetaData getMetaData() throws SQLException {
		return rs.getMetaData();
	}

	public Reader getNCharacterStream(int i) throws SQLException {
		return rs.getNCharacterStream(i);
	}

	public Reader getNCharacterStream(String s) throws SQLException {
		return rs.getNCharacterStream(s);
	}

	public NClob getNClob(int i) throws SQLException {
		return rs.getNClob(i);
	}

	public NClob getNClob(String s) throws SQLException {
		return rs.getNClob(s);
	}

	public String getNString(int i) throws SQLException {
		return rs.getNString(i);
	}

	public String getNString(String s) throws SQLException {
		return rs.getNString(s);
	}

	public Object getObject(int arg0, Map<String, Class<?>> arg1) throws SQLException {
		return rs.getObject(arg0, arg1);
	}

	public Object getObject(int i) throws SQLException {
		return rs.getObject(i);
	}

	public Object getObject(String arg0, Map<String, Class<?>> arg1) throws SQLException {
		return rs.getObject(arg0, arg1);
	}

	public Object getObject(String s) throws SQLException {
		return rs.getObject(s);
	}

	public Ref getRef(int i) throws SQLException {
		return rs.getRef(i);
	}

	public Ref getRef(String s) throws SQLException {
		return rs.getRef(s);
	}

	public int getRow() throws SQLException {
		return rs.getRow();
	}

	public RowId getRowId(int i) throws SQLException {
		return rs.getRowId(i);
	}

	public RowId getRowId(String s) throws SQLException {
		return rs.getRowId(s);
	}

	public short getShort(int i) throws SQLException {
		return rs.getShort(i);
	}

	public short getShort(String s) throws SQLException {
		return rs.getShort(s);
	}

	public SQLXML getSQLXML(int i) throws SQLException {
		return rs.getSQLXML(i);
	}

	public SQLXML getSQLXML(String s) throws SQLException {
		return rs.getSQLXML(s);
	}

	public Statement getStatement() throws SQLException {
		return rs.getStatement();
	}

	public String getString(int i) throws SQLException {
		return rs.getString(i);
	}

	public String getString(String s) throws SQLException {
		return rs.getString(s);
	}

	public Time getTime(int i, Calendar calendar) throws SQLException {
		return rs.getTime(i, calendar);
	}

	public Time getTime(int i) throws SQLException {
		return rs.getTime(i);
	}

	public Time getTime(String s, Calendar calendar) throws SQLException {
		return rs.getTime(s, calendar);
	}

	public Time getTime(String s) throws SQLException {
		return rs.getTime(s);
	}

	public Timestamp getTimestamp(int i, Calendar calendar) throws SQLException {
		return rs.getTimestamp(i, calendar);
	}

	public Timestamp getTimestamp(int i) throws SQLException {
		return rs.getTimestamp(i);
	}

	public Timestamp getTimestamp(String s, Calendar calendar) throws SQLException {
		return rs.getTimestamp(s, calendar);
	}

	public Timestamp getTimestamp(String s) throws SQLException {
		return rs.getTimestamp(s);
	}

	public int getType() throws SQLException {
		return rs.getType();
	}

	@Deprecated
	public InputStream getUnicodeStream(int i) throws SQLException {
		return rs.getUnicodeStream(i);
	}

	@Deprecated
	public InputStream getUnicodeStream(String s) throws SQLException {
		return rs.getUnicodeStream(s);
	}

	public URL getURL(int i) throws SQLException {
		return rs.getURL(i);
	}

	public URL getURL(String s) throws SQLException {
		return rs.getURL(s);
	}

	public SQLWarning getWarnings() throws SQLException {
		return rs.getWarnings();
	}

	public void insertRow() throws SQLException {
		rs.insertRow();
	}

	public boolean isAfterLast() throws SQLException {
		return rs.isAfterLast();
	}

	public boolean isBeforeFirst() throws SQLException {
		return rs.isBeforeFirst();
	}

	public boolean isClosed() throws SQLException {
		return rs.isClosed();
	}

	public boolean isFirst() throws SQLException {
		return rs.isFirst();
	}

	public boolean isLast() throws SQLException {
		return rs.isLast();
	}

	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		return rs.isWrapperFor(arg0);
	}

	public boolean last() throws SQLException {
		return rs.last();
	}

	public void moveToCurrentRow() throws SQLException {
		rs.moveToCurrentRow();
	}

	public void moveToInsertRow() throws SQLException {
		rs.moveToInsertRow();
	}

	public boolean next() throws SQLException {
		return rs.next();
	}

	public boolean previous() throws SQLException {
		return rs.previous();
	}

	public void refreshRow() throws SQLException {
		rs.refreshRow();
	}

	public boolean relative(int i) throws SQLException {
		return rs.relative(i);
	}

	public boolean rowDeleted() throws SQLException {
		return rs.rowDeleted();
	}

	public boolean rowInserted() throws SQLException {
		return rs.rowInserted();
	}

	public boolean rowUpdated() throws SQLException {
		return rs.rowUpdated();
	}

	public void setFetchDirection(int i) throws SQLException {
		rs.setFetchDirection(i);
	}

	public void setFetchSize(int i) throws SQLException {
		rs.setFetchSize(i);
	}

	public <T> T unwrap(Class<T> arg0) throws SQLException {
		return rs.unwrap(arg0);
	}

	public void updateArray(int i, Array array) throws SQLException {
		rs.updateArray(i, array);
	}

	public void updateArray(String s, Array array) throws SQLException {
		rs.updateArray(s, array);
	}

	public void updateAsciiStream(int i, InputStream inputstream, int j) throws SQLException {
		rs.updateAsciiStream(i, inputstream, j);
	}

	public void updateAsciiStream(int i, InputStream inputstream, long l) throws SQLException {
		rs.updateAsciiStream(i, inputstream, l);
	}

	public void updateAsciiStream(int i, InputStream inputstream) throws SQLException {
		rs.updateAsciiStream(i, inputstream);
	}

	public void updateAsciiStream(String s, InputStream inputstream, int i) throws SQLException {
		rs.updateAsciiStream(s, inputstream, i);
	}

	public void updateAsciiStream(String s, InputStream inputstream, long l) throws SQLException {
		rs.updateAsciiStream(s, inputstream, l);
	}

	public void updateAsciiStream(String s, InputStream inputstream) throws SQLException {
		rs.updateAsciiStream(s, inputstream);
	}

	public void updateBigDecimal(int i, BigDecimal bigdecimal) throws SQLException {
		rs.updateBigDecimal(i, bigdecimal);
	}

	public void updateBigDecimal(String s, BigDecimal bigdecimal) throws SQLException {
		rs.updateBigDecimal(s, bigdecimal);
	}

	public void updateBinaryStream(int i, InputStream inputstream, int j) throws SQLException {
		rs.updateBinaryStream(i, inputstream, j);
	}

	public void updateBinaryStream(int i, InputStream inputstream, long l) throws SQLException {
		rs.updateBinaryStream(i, inputstream, l);
	}

	public void updateBinaryStream(int i, InputStream inputstream) throws SQLException {
		rs.updateBinaryStream(i, inputstream);
	}

	public void updateBinaryStream(String s, InputStream inputstream, int i) throws SQLException {
		rs.updateBinaryStream(s, inputstream, i);
	}

	public void updateBinaryStream(String s, InputStream inputstream, long l) throws SQLException {
		rs.updateBinaryStream(s, inputstream, l);
	}

	public void updateBinaryStream(String s, InputStream inputstream) throws SQLException {
		rs.updateBinaryStream(s, inputstream);
	}

	public void updateBlob(int i, Blob blob) throws SQLException {
		rs.updateBlob(i, blob);
	}

	public void updateBlob(int i, InputStream inputstream, long l) throws SQLException {
		rs.updateBlob(i, inputstream, l);
	}

	public void updateBlob(int i, InputStream inputstream) throws SQLException {
		rs.updateBlob(i, inputstream);
	}

	public void updateBlob(String s, Blob blob) throws SQLException {
		rs.updateBlob(s, blob);
	}

	public void updateBlob(String s, InputStream inputstream, long l) throws SQLException {
		rs.updateBlob(s, inputstream, l);
	}

	public void updateBlob(String s, InputStream inputstream) throws SQLException {
		rs.updateBlob(s, inputstream);
	}

	public void updateBoolean(int i, boolean flag) throws SQLException {
		rs.updateBoolean(i, flag);
	}

	public void updateBoolean(String s, boolean flag) throws SQLException {
		rs.updateBoolean(s, flag);
	}

	public void updateByte(int i, byte byte0) throws SQLException {
		rs.updateByte(i, byte0);
	}

	public void updateByte(String s, byte byte0) throws SQLException {
		rs.updateByte(s, byte0);
	}

	public void updateBytes(int i, byte[] bytes) throws SQLException {
		rs.updateBytes(i, bytes);
	}

	public void updateBytes(String s, byte[] bytes) throws SQLException {
		rs.updateBytes(s, bytes);
	}

	public void updateCharacterStream(int i, Reader reader, int j) throws SQLException {
		rs.updateCharacterStream(i, reader, j);
	}

	public void updateCharacterStream(int i, Reader reader, long l) throws SQLException {
		rs.updateCharacterStream(i, reader, l);
	}

	public void updateCharacterStream(int i, Reader reader) throws SQLException {
		rs.updateCharacterStream(i, reader);
	}

	public void updateCharacterStream(String s, Reader reader, int i) throws SQLException {
		rs.updateCharacterStream(s, reader, i);
	}

	public void updateCharacterStream(String s, Reader reader, long l) throws SQLException {
		rs.updateCharacterStream(s, reader, l);
	}

	public void updateCharacterStream(String s, Reader reader) throws SQLException {
		rs.updateCharacterStream(s, reader);
	}

	public void updateClob(int i, Clob clob) throws SQLException {
		rs.updateClob(i, clob);
	}

	public void updateClob(int i, Reader reader, long l) throws SQLException {
		rs.updateClob(i, reader, l);
	}

	public void updateClob(int i, Reader reader) throws SQLException {
		rs.updateClob(i, reader);
	}

	public void updateClob(String s, Clob clob) throws SQLException {
		rs.updateClob(s, clob);
	}

	public void updateClob(String s, Reader reader, long l) throws SQLException {
		rs.updateClob(s, reader, l);
	}

	public void updateClob(String s, Reader reader) throws SQLException {
		rs.updateClob(s, reader);
	}

	public void updateDate(int i, Date date) throws SQLException {
		rs.updateDate(i, date);
	}

	public void updateDate(String s, Date date) throws SQLException {
		rs.updateDate(s, date);
	}

	public void updateDouble(int i, double d) throws SQLException {
		rs.updateDouble(i, d);
	}

	public void updateDouble(String s, double d) throws SQLException {
		rs.updateDouble(s, d);
	}

	public void updateFloat(int i, float f) throws SQLException {
		rs.updateFloat(i, f);
	}

	public void updateFloat(String s, float f) throws SQLException {
		rs.updateFloat(s, f);
	}

	public void updateInt(int i, int j) throws SQLException {
		rs.updateInt(i, j);
	}

	public void updateInt(String s, int i) throws SQLException {
		rs.updateInt(s, i);
	}

	public void updateLong(int i, long l) throws SQLException {
		rs.updateLong(i, l);
	}

	public void updateLong(String s, long l) throws SQLException {
		rs.updateLong(s, l);
	}

	public void updateNCharacterStream(int i, Reader reader, long l) throws SQLException {
		rs.updateNCharacterStream(i, reader, l);
	}

	public void updateNCharacterStream(int i, Reader reader) throws SQLException {
		rs.updateNCharacterStream(i, reader);
	}

	public void updateNCharacterStream(String s, Reader reader, long l) throws SQLException {
		rs.updateNCharacterStream(s, reader, l);
	}

	public void updateNCharacterStream(String s, Reader reader) throws SQLException {
		rs.updateNCharacterStream(s, reader);
	}

	public void updateNClob(int i, NClob nclob) throws SQLException {
		rs.updateNClob(i, nclob);
	}

	public void updateNClob(int i, Reader reader, long l) throws SQLException {
		rs.updateNClob(i, reader, l);
	}

	public void updateNClob(int i, Reader reader) throws SQLException {
		rs.updateNClob(i, reader);
	}

	public void updateNClob(String s, NClob nclob) throws SQLException {
		rs.updateNClob(s, nclob);
	}

	public void updateNClob(String s, Reader reader, long l) throws SQLException {
		rs.updateNClob(s, reader, l);
	}

	public void updateNClob(String s, Reader reader) throws SQLException {
		rs.updateNClob(s, reader);
	}

	public void updateNString(int i, String s) throws SQLException {
		rs.updateNString(i, s);
	}

	public void updateNString(String s, String s1) throws SQLException {
		rs.updateNString(s, s1);
	}

	public void updateNull(int i) throws SQLException {
		rs.updateNull(i);
	}

	public void updateNull(String s) throws SQLException {
		rs.updateNull(s);
	}

	public void updateObject(int i, Object obj, int j) throws SQLException {
		rs.updateObject(i, obj, j);
	}

	public void updateObject(int i, Object obj) throws SQLException {
		rs.updateObject(i, obj);
	}

	public void updateObject(String s, Object obj, int i) throws SQLException {
		rs.updateObject(s, obj, i);
	}

	public void updateObject(String s, Object obj) throws SQLException {
		rs.updateObject(s, obj);
	}

	public void updateRef(int i, Ref ref) throws SQLException {
		rs.updateRef(i, ref);
	}

	public void updateRef(String s, Ref ref) throws SQLException {
		rs.updateRef(s, ref);
	}

	public void updateRow() throws SQLException {
		rs.updateRow();
	}

	public void updateRowId(int i, RowId rowid) throws SQLException {
		rs.updateRowId(i, rowid);
	}

	public void updateRowId(String s, RowId rowid) throws SQLException {
		rs.updateRowId(s, rowid);
	}

	public void updateShort(int i, short word0) throws SQLException {
		rs.updateShort(i, word0);
	}

	public void updateShort(String s, short word0) throws SQLException {
		rs.updateShort(s, word0);
	}

	public void updateSQLXML(int i, SQLXML sqlxml) throws SQLException {
		rs.updateSQLXML(i, sqlxml);
	}

	public void updateSQLXML(String s, SQLXML sqlxml) throws SQLException {
		rs.updateSQLXML(s, sqlxml);
	}

	public void updateString(int i, String s) throws SQLException {
		rs.updateString(i, s);
	}

	public void updateString(String s, String s1) throws SQLException {
		rs.updateString(s, s1);
	}

	public void updateTime(int i, Time time) throws SQLException {
		rs.updateTime(i, time);
	}

	public void updateTime(String s, Time time) throws SQLException {
		rs.updateTime(s, time);
	}

	public void updateTimestamp(int i, Timestamp timestamp) throws SQLException {
		rs.updateTimestamp(i, timestamp);
	}

	public void updateTimestamp(String s, Timestamp timestamp) throws SQLException {
		rs.updateTimestamp(s, timestamp);
	}

	public boolean wasNull() throws SQLException {
		return rs.wasNull();
	}

	public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
		throw new UnsupportedOperationException();
	}

}
