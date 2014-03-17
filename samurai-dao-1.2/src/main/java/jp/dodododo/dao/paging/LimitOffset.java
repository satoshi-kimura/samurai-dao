package jp.dodododo.dao.paging;

import java.io.Serializable;

import jp.dodododo.dao.message.Message;

/**
 * {@.en }
 * 
 * <br />
 * 
 * {@.ja }
 * 
 * @author Satoshi Kimura
 */
public class LimitOffset implements Serializable {

	private static final long serialVersionUID = -8263540726191171148L;

	public static final String KEYWORD = "limit_offset";

	private long limit;

	private long offset;

	/**
	 * 
	 * @param limit
	 *            {@.en limit} {@.ja 件数}
	 * @param offset
	 *            {@.en offset(ranges from 0) } {@.ja 開始位置(0スタート) }
	 */
	public LimitOffset(long limit, long offset) {
		this.limit = limit;
		this.offset = offset;
		if (offset < 0) {
			throw new IllegalArgumentException(Message.getMessage("00015", offset));
		}
	}

	public long getLimit() {
		return limit;
	}

	public long getOffset() {
		return offset;
	}

}
