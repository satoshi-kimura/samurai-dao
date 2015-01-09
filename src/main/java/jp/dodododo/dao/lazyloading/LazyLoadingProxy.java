package jp.dodododo.dao.lazyloading;

import jp.dodododo.dao.annotation.Proxy;

@Proxy
public interface LazyLoadingProxy<T> {

	T lazyLoad();

	T real();

	void setReal(T real);
}
