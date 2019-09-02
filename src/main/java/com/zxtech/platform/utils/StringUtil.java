package com.zxtech.platform.utils;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
	
	public static String toSafeString(Object obj) {
		return obj != null ? obj.toString() : "";
	}

	/**
     * <p>Checks if a String is whitespace, empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     * 
     */
	public static boolean isBlank(String string) {
		return StringUtils.isBlank(string);
	}

	/**
     * <p>Checks if a String is not empty (""), not null and not whitespace only.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     */
	public static boolean isNotBlank(String string) {
		return StringUtils.isNotBlank(string);
	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String stringConvertor(Object obj) {
		return (obj != null && !"null".equals(String.valueOf(obj))) ? obj.toString() : "";
	}
	
	public static boolean isObjectBlank(Object obj) {
		return isBlank(toSafeString(obj));
	}
}
