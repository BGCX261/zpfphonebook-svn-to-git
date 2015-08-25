package org.feifei.phonebook.other;

import java.net.URL;
import java.net.URLConnection;

public class Test {
	public static void main(final String[] args) throws Exception {
		String ntype = null;
		int nlength = 0;
		URL url = new URL(
				"http://10.13.45.94:8080/PMV4/images/download/UPDATE.zip");
		URLConnection urlconnection = url.openConnection();
		nlength = urlconnection.getContentLength();
		ntype = urlconnection.getContentType();
		System.out.println(nlength + "  " + ntype);
	}
}
