package jp.co.eintecs.filter;

public class Security {
	/**
	 * XSS対策用エスケープメソッド
	 * @author sugie
	 * @param str
	 * @return エスケープ文字
	 */
	public static String escape(String str) {
		if (str == null) {
			return "";
		} else {
			str = str.replaceAll("&", "&amp;");
			str = str.replaceAll("<", "&lt;");
			str = str.replaceAll(">", "&gt;");
			str = str.replaceAll("\"", "&quot;");
			str = str.replaceAll("'", "&apos;");
		}
		return str;
	}
}
