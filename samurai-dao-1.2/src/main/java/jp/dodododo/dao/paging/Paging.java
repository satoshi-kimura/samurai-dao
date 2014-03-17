package jp.dodododo.dao.paging;

/**
 * {@.en }
 * 
 * <br />
 * 
 * {@.ja }
 * 
 * @author Satoshi Kimura
 */
public class Paging extends LimitOffset {

	private static final long serialVersionUID = 1291432066111220692L;

	private long pageNo;

	/**
	 * 
	 * @param limit
	 *            {@.en limit} {@.ja 件数}
	 * @param pageNo
	 *            {@.en pageNo(ranges from 0) } {@.ja ページ番号(0スタート) }
	 */
	public Paging(long limit, long pageNo) {
		super(limit, pageNo * limit);
		this.pageNo = pageNo;
	}

	public long getPageNo() {
		return pageNo;
	}
}
