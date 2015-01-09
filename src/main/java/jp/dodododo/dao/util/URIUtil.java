package jp.dodododo.dao.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import jp.dodododo.dao.exception.IORuntimeException;
import jp.dodododo.dao.exception.URISyntaxRuntimeException;

public class URIUtil {
	public static URL toURL(URI uri) {
		try {
			return uri.toURL();
		} catch (MalformedURLException e) {
			throw new IORuntimeException(e);
		}
	}

	public static URI newURI(String uri) {
		try {
			return new URI(uri);
		} catch (URISyntaxException e) {
			throw new URISyntaxRuntimeException(e);
		}
	}
}
