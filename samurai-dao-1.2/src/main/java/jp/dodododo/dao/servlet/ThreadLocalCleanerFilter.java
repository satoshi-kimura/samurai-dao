package jp.dodododo.dao.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import jp.dodododo.dao.flyweight.FlyweightFactory;
import jp.dodododo.dao.log.SqlLogRegistry;
import jp.dodododo.dao.object.PropertyDesc;

public class ThreadLocalCleanerFilter implements Filter {

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} finally {
			SqlLogRegistry.getInstance().clear();
			FlyweightFactory.dispose();
			PropertyDesc.cacheModeOff();
		}
	}

	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
	}

	/**
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

}
