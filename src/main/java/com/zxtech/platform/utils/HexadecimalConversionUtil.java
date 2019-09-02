package com.zxtech.platform.utils;

public class HexadecimalConversionUtil {

	private final static char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z' };

	public static String numericToNHexString(long i, int system) {
		long num = 0;
		if (i < 0) {
			num = ((long) 2 * 0x7fffffff) + i + 2;
		} else {
			num = i;
		}
		char[] buf = new char[32];
		int charPos = 32;
		while ((num / system) > 0) {
			buf[--charPos] = DIGITS[(int) (num % system)];
			num /= system;
		}
		buf[--charPos] = DIGITS[(int) (num % system)];
		return new String(buf, charPos, (32 - charPos));
	}
}
