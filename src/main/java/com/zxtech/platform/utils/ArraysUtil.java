package com.zxtech.platform.utils;

import java.lang.reflect.Array;

/**
 * 数组操作的工具类。
 * <b>DO NOT MODIFY: this source is generated.</b> 
 * 
 * @author 
 */
public class ArraysUtil {



	// ---------------------------------------------------------------- merge

	/**
	 * 合并数组。
	 */
	@SuppressWarnings({"unchecked"})
	public static <T> T[] merge(T[]... arrays) {
		Class<?> componentType =  arrays.getClass().getComponentType().getComponentType();
		int length = 0;
		for (T[] array : arrays) {
			length += array.length;
		}
		T[] result = (T[]) Array.newInstance(componentType, length);

		length = 0;
		for (T[] array : arrays) {
			System.arraycopy(array, 0, result, length, array.length);
			length += array.length;
		}
		return result;
	}


	/**
	 * 合并数组。
	 */
	public static String[] merge(String[]... arrays) {
		int length = 0;
		for (String[] array : arrays) {
			length += array.length;
		}
		String[] result = new String[length];
		length = 0;
		for (String[] array : arrays) {
			System.arraycopy(array, 0, result, length, array.length);
			length += array.length;
		}
		return result;
	}

	/**
	 * 合并数组。
	 */
	public static byte[] merge(byte[]... arrays) {
		int length = 0;
		for (byte[] array : arrays) {
			length += array.length;
		}
		byte[] result = new byte[length];
		length = 0;
		for (byte[] array : arrays) {
			System.arraycopy(array, 0, result, length, array.length);
			length += array.length;
		}
		return result;
	}

	/**
	 * 合并数组。
	 */
	public static char[] merge(char[]... arrays) {
		int length = 0;
		for (char[] array : arrays) {
			length += array.length;
		}
		char[] result = new char[length];
		length = 0;
		for (char[] array : arrays) {
			System.arraycopy(array, 0, result, length, array.length);
			length += array.length;
		}
		return result;
	}

	/**
	 * 合并数组。
	 */
	public static short[] merge(short[]... arrays) {
		int length = 0;
		for (short[] array : arrays) {
			length += array.length;
		}
		short[] result = new short[length];
		length = 0;
		for (short[] array : arrays) {
			System.arraycopy(array, 0, result, length, array.length);
			length += array.length;
		}
		return result;
	}

	/**
	 * 合并数组。
	 */
	public static int[] merge(int[]... arrays) {
		int length = 0;
		for (int[] array : arrays) {
			length += array.length;
		}
		int[] result = new int[length];
		length = 0;
		for (int[] array : arrays) {
			System.arraycopy(array, 0, result, length, array.length);
			length += array.length;
		}
		return result;
	}

	/**
	 * 合并数组。
	 */
	public static long[] merge(long[]... arrays) {
		int length = 0;
		for (long[] array : arrays) {
			length += array.length;
		}
		long[] result = new long[length];
		length = 0;
		for (long[] array : arrays) {
			System.arraycopy(array, 0, result, length, array.length);
			length += array.length;
		}
		return result;
	}

	/**
	 * 合并数组。
	 */
	public static float[] merge(float[]... arrays) {
		int length = 0;
		for (float[] array : arrays) {
			length += array.length;
		}
		float[] result = new float[length];
		length = 0;
		for (float[] array : arrays) {
			System.arraycopy(array, 0, result, length, array.length);
			length += array.length;
		}
		return result;
	}

	/**
	 * 合并数组。
	 */
	public static double[] merge(double[]... arrays) {
		int length = 0;
		for (double[] array : arrays) {
			length += array.length;
		}
		double[] result = new double[length];
		length = 0;
		for (double[] array : arrays) {
			System.arraycopy(array, 0, result, length, array.length);
			length += array.length;
		}
		return result;
	}

	/**
	 * 合并数组。
	 */
	public static boolean[] merge(boolean[]... arrays) {
		int length = 0;
		for (boolean[] array : arrays) {
			length += array.length;
		}
		boolean[] result = new boolean[length];
		length = 0;
		for (boolean[] array : arrays) {
			System.arraycopy(array, 0, result, length, array.length);
			length += array.length;
		}
		return result;
	}


	// ---------------------------------------------------------------- join

	/**
	 * 连接两个数组（等价于merge两个数组）
	 */
	public static <T> T[] join(T[] first, T[] second) {
		return join(first, second, null);
	}

	/**
	 * 连接两个数组（等价于merge两个数组）
	 */
	@SuppressWarnings({"unchecked"})
	public static <T> T[] join(T[] first, T[] second, Class<?> componentType) {
		if (componentType == null) {
			componentType = first.getClass().getComponentType();
		}
		T[] temp = (T[]) Array.newInstance(componentType, first.length + second.length);
		System.arraycopy(first, 0, temp, 0, first.length);
		System.arraycopy(second, 0, temp, first.length, second.length);
		return temp;
	}


	/**
	 * 连接两个数组（等价于merge两个数组）
	 */
	public static String[] join(String[] first, String[] second) {
		String[] temp = new String[first.length + second.length];
		System.arraycopy(first, 0, temp, 0, first.length);
		System.arraycopy(second, 0, temp, first.length, second.length);
		return temp;
	}

	/**
	 * 连接两个数组（等价于merge两个数组）
	 */
	public static byte[] join(byte[] first, byte[] second) {
		byte[] temp = new byte[first.length + second.length];
		System.arraycopy(first, 0, temp, 0, first.length);
		System.arraycopy(second, 0, temp, first.length, second.length);
		return temp;
	}

	/**
	 * 连接两个数组（等价于merge两个数组）
	 */
	public static char[] join(char[] first, char[] second) {
		char[] temp = new char[first.length + second.length];
		System.arraycopy(first, 0, temp, 0, first.length);
		System.arraycopy(second, 0, temp, first.length, second.length);
		return temp;
	}

	/**
	 * 连接两个数组（等价于merge两个数组）
	 */
	public static short[] join(short[] first, short[] second) {
		short[] temp = new short[first.length + second.length];
		System.arraycopy(first, 0, temp, 0, first.length);
		System.arraycopy(second, 0, temp, first.length, second.length);
		return temp;
	}

	/**
	 * 连接两个数组（等价于merge两个数组）
	 */
	public static int[] join(int[] first, int[] second) {
		int[] temp = new int[first.length + second.length];
		System.arraycopy(first, 0, temp, 0, first.length);
		System.arraycopy(second, 0, temp, first.length, second.length);
		return temp;
	}

	/**
	 * 连接两个数组（等价于merge两个数组）
	 */
	public static long[] join(long[] first, long[] second) {
		long[] temp = new long[first.length + second.length];
		System.arraycopy(first, 0, temp, 0, first.length);
		System.arraycopy(second, 0, temp, first.length, second.length);
		return temp;
	}

	/**
	 * 连接两个数组（等价于merge两个数组）
	 */
	public static float[] join(float[] first, float[] second) {
		float[] temp = new float[first.length + second.length];
		System.arraycopy(first, 0, temp, 0, first.length);
		System.arraycopy(second, 0, temp, first.length, second.length);
		return temp;
	}

	/**
	 * 连接两个数组（等价于merge两个数组）
	 */
	public static double[] join(double[] first, double[] second) {
		double[] temp = new double[first.length + second.length];
		System.arraycopy(first, 0, temp, 0, first.length);
		System.arraycopy(second, 0, temp, first.length, second.length);
		return temp;
	}

	/**
	 * 连接两个数组（等价于merge两个数组）
	 */
	public static boolean[] join(boolean[] first, boolean[] second) {
		boolean[] temp = new boolean[first.length + second.length];
		System.arraycopy(first, 0, temp, 0, first.length);
		System.arraycopy(second, 0, temp, first.length, second.length);
		return temp;
	}


	// ---------------------------------------------------------------- resize

	/**
	 * 改变数组的大小
	 */
	public static <T> T[] resize(T[] buffer, int newSize) {
		return resize(buffer, newSize, null);
	}
		
	/**
	 * 改变数组的大小
	 */
	@SuppressWarnings({"unchecked"})
	public static <T> T[] resize(T[] buffer, int newSize, Class<?> componentType) {
		if (componentType == null) {
			componentType =  buffer.getClass().getComponentType();
		}
		T[] temp = (T[]) Array.newInstance(componentType, newSize);
		System.arraycopy(buffer, 0, temp, 0, buffer.length >= newSize ? newSize : buffer.length);
		return temp;
	}

	/**
	 * 改变数组的大小
	 */
	public static String[] resize(String buffer[], int newSize) {
		String temp[] = new String[newSize];
		System.arraycopy(buffer, 0, temp, 0, buffer.length >= newSize ? newSize : buffer.length);
		return temp;
	}

	/**
	 * 改变数组的大小
	 */
	public static byte[] resize(byte buffer[], int newSize) {
		byte temp[] = new byte[newSize];
		System.arraycopy(buffer, 0, temp, 0, buffer.length >= newSize ? newSize : buffer.length);
		return temp;
	}

	/**
	 * 改变数组的大小
	 */
	public static char[] resize(char buffer[], int newSize) {
		char temp[] = new char[newSize];
		System.arraycopy(buffer, 0, temp, 0, buffer.length >= newSize ? newSize : buffer.length);
		return temp;
	}

	/**
	 * 改变数组的大小
	 */
	public static short[] resize(short buffer[], int newSize) {
		short temp[] = new short[newSize];
		System.arraycopy(buffer, 0, temp, 0, buffer.length >= newSize ? newSize : buffer.length);
		return temp;
	}

	/**
	 * 改变数组的大小
	 */
	public static int[] resize(int buffer[], int newSize) {
		int temp[] = new int[newSize];
		System.arraycopy(buffer, 0, temp, 0, buffer.length >= newSize ? newSize : buffer.length);
		return temp;
	}

	/**
	 * 改变数组的大小
	 */
	public static long[] resize(long buffer[], int newSize) {
		long temp[] = new long[newSize];
		System.arraycopy(buffer, 0, temp, 0, buffer.length >= newSize ? newSize : buffer.length);
		return temp;
	}

	/**
	 * 改变数组的大小
	 */
	public static float[] resize(float buffer[], int newSize) {
		float temp[] = new float[newSize];
		System.arraycopy(buffer, 0, temp, 0, buffer.length >= newSize ? newSize : buffer.length);
		return temp;
	}

	/**
	 * 改变数组的大小
	 */
	public static double[] resize(double buffer[], int newSize) {
		double temp[] = new double[newSize];
		System.arraycopy(buffer, 0, temp, 0, buffer.length >= newSize ? newSize : buffer.length);
		return temp;
	}

	/**
	 * 改变数组的大小
	 */
	public static boolean[] resize(boolean buffer[], int newSize) {
		boolean temp[] = new boolean[newSize];
		System.arraycopy(buffer, 0, temp, 0, buffer.length >= newSize ? newSize : buffer.length);
		return temp;
	}


	// ---------------------------------------------------------------- append

	/**
	 * 追加元素到数组末尾
	 */
	public static <T> T[] append(T[] buffer, T newElement) {
		T[] t = resize(buffer, buffer.length + 1, newElement.getClass());
		t[buffer.length] = newElement;
		return t;
	}

	/**
	 * 追加元素到数组末尾
	 */
	public static String[] append(String buffer[], String newElement) {
		String[] t = resize(buffer, buffer.length + 1);
		t[buffer.length] = newElement;
		return t;
	}

	/**
	 * 追加元素到数组末尾
	 */
	public static byte[] append(byte buffer[], byte newElement) {
		byte[] t = resize(buffer, buffer.length + 1);
		t[buffer.length] = newElement;
		return t;
	}

	/**
	 * 追加元素到数组末尾
	 */
	public static char[] append(char buffer[], char newElement) {
		char[] t = resize(buffer, buffer.length + 1);
		t[buffer.length] = newElement;
		return t;
	}

	/**
	 * 追加元素到数组末尾
	 */
	public static short[] append(short buffer[], short newElement) {
		short[] t = resize(buffer, buffer.length + 1);
		t[buffer.length] = newElement;
		return t;
	}

	/**
	 * 追加元素到数组末尾
	 */
	public static int[] append(int buffer[], int newElement) {
		int[] t = resize(buffer, buffer.length + 1);
		t[buffer.length] = newElement;
		return t;
	}

	/**
	 * 追加元素到数组末尾
	 */
	public static long[] append(long buffer[], long newElement) {
		long[] t = resize(buffer, buffer.length + 1);
		t[buffer.length] = newElement;
		return t;
	}

	/**
	 * 追加元素到数组末尾
	 */
	public static float[] append(float buffer[], float newElement) {
		float[] t = resize(buffer, buffer.length + 1);
		t[buffer.length] = newElement;
		return t;
	}

	/**
	 * 追加元素到数组末尾
	 */
	public static double[] append(double buffer[], double newElement) {
		double[] t = resize(buffer, buffer.length + 1);
		t[buffer.length] = newElement;
		return t;
	}

	/**
	 * 追加元素到数组末尾
	 */
	public static boolean[] append(boolean buffer[], boolean newElement) {
		boolean[] t = resize(buffer, buffer.length + 1);
		t[buffer.length] = newElement;
		return t;
	}


	// ---------------------------------------------------------------- remove

	/**
	 * 删除子数组
	 */
	public static <T> T[] remove(T[] buffer, int offset, int length) {
		return remove(buffer, offset, length, null);
	}

	/**
	 * 删除子数组
	 */
	@SuppressWarnings({"unchecked"})
	public static <T> T[] remove(T[] buffer, int offset, int length, Class<?> componentType) {
		if (componentType == null) {
			componentType = buffer.getClass().getComponentType();
		}
		int len2 = buffer.length - length;
		T[] temp = (T[]) Array.newInstance(componentType, len2);
		System.arraycopy(buffer, 0, temp, 0, offset);
		System.arraycopy(buffer, offset + length, temp, offset, len2 - offset);
		return temp;
	}

	/**
	 * 删除子数组
	 */
	public static String[] remove(String[] buffer, int offset, int length) {
		int len2 = buffer.length - length;
		String temp[] = new String[len2];
		System.arraycopy(buffer, 0, temp, 0, offset);
		System.arraycopy(buffer, offset + length, temp, offset, len2 - offset);
		return temp;
	}

	/**
	 * 删除子数组
	 */
	public static byte[] remove(byte[] buffer, int offset, int length) {
		int len2 = buffer.length - length;
		byte temp[] = new byte[len2];
		System.arraycopy(buffer, 0, temp, 0, offset);
		System.arraycopy(buffer, offset + length, temp, offset, len2 - offset);
		return temp;
	}

	/**
	 * 删除子数组
	 */
	public static char[] remove(char[] buffer, int offset, int length) {
		int len2 = buffer.length - length;
		char temp[] = new char[len2];
		System.arraycopy(buffer, 0, temp, 0, offset);
		System.arraycopy(buffer, offset + length, temp, offset, len2 - offset);
		return temp;
	}

	/**
	 * 删除子数组
	 */
	public static short[] remove(short[] buffer, int offset, int length) {
		int len2 = buffer.length - length;
		short temp[] = new short[len2];
		System.arraycopy(buffer, 0, temp, 0, offset);
		System.arraycopy(buffer, offset + length, temp, offset, len2 - offset);
		return temp;
	}

	/**
	 * 删除子数组
	 */
	public static int[] remove(int[] buffer, int offset, int length) {
		int len2 = buffer.length - length;
		int temp[] = new int[len2];
		System.arraycopy(buffer, 0, temp, 0, offset);
		System.arraycopy(buffer, offset + length, temp, offset, len2 - offset);
		return temp;
	}

	/**
	 * 删除子数组
	 */
	public static long[] remove(long[] buffer, int offset, int length) {
		int len2 = buffer.length - length;
		long temp[] = new long[len2];
		System.arraycopy(buffer, 0, temp, 0, offset);
		System.arraycopy(buffer, offset + length, temp, offset, len2 - offset);
		return temp;
	}

	/**
	 * 删除子数组
	 */
	public static float[] remove(float[] buffer, int offset, int length) {
		int len2 = buffer.length - length;
		float temp[] = new float[len2];
		System.arraycopy(buffer, 0, temp, 0, offset);
		System.arraycopy(buffer, offset + length, temp, offset, len2 - offset);
		return temp;
	}

	/**
	 * 删除子数组
	 */
	public static double[] remove(double[] buffer, int offset, int length) {
		int len2 = buffer.length - length;
		double temp[] = new double[len2];
		System.arraycopy(buffer, 0, temp, 0, offset);
		System.arraycopy(buffer, offset + length, temp, offset, len2 - offset);
		return temp;
	}

	/**
	 * 删除子数组
	 */
	public static boolean[] remove(boolean[] buffer, int offset, int length) {
		int len2 = buffer.length - length;
		boolean temp[] = new boolean[len2];
		System.arraycopy(buffer, 0, temp, 0, offset);
		System.arraycopy(buffer, offset + length, temp, offset, len2 - offset);
		return temp;
	}


	// ---------------------------------------------------------------- subarray

	/**
	 * 获取子数组
	 */
	public static <T> T[] subarray(T[] buffer, int offset, int length) {
		return subarray(buffer, offset, length, null);
	}

	/**
	 * 获取子数组
	 */
	@SuppressWarnings({"unchecked"})
	public static <T> T[] subarray(T[] buffer, int offset, int length, Class<?> componentType) {
		if (componentType == null) {
			componentType = buffer.getClass().getComponentType();
		}
		T[] temp = (T[]) Array.newInstance(componentType, length);
		System.arraycopy(buffer, offset, temp, 0, length);
		return temp;
	}

	/**
	 * 获取子数组
	 */
	public static String[] subarray(String[] buffer, int offset, int length) {
		String temp[] = new String[length];
		System.arraycopy(buffer, offset, temp, 0, length);
		return temp;
	}

	/**
	 * 获取子数组
	 */
	public static byte[] subarray(byte[] buffer, int offset, int length) {
		byte temp[] = new byte[length];
		System.arraycopy(buffer, offset, temp, 0, length);
		return temp;
	}

	/**
	 * 获取子数组
	 */
	public static char[] subarray(char[] buffer, int offset, int length) {
		char temp[] = new char[length];
		System.arraycopy(buffer, offset, temp, 0, length);
		return temp;
	}

	/**
	 * 获取子数组
	 */
	public static short[] subarray(short[] buffer, int offset, int length) {
		short temp[] = new short[length];
		System.arraycopy(buffer, offset, temp, 0, length);
		return temp;
	}

	/**
	 * 获取子数组
	 */
	public static int[] subarray(int[] buffer, int offset, int length) {
		int temp[] = new int[length];
		System.arraycopy(buffer, offset, temp, 0, length);
		return temp;
	}

	/**
	 * 获取子数组
	 */
	public static long[] subarray(long[] buffer, int offset, int length) {
		long temp[] = new long[length];
		System.arraycopy(buffer, offset, temp, 0, length);
		return temp;
	}

	/**
	 * 获取子数组
	 */
	public static float[] subarray(float[] buffer, int offset, int length) {
		float temp[] = new float[length];
		System.arraycopy(buffer, offset, temp, 0, length);
		return temp;
	}

	/**
	 * 获取子数组
	 */
	public static double[] subarray(double[] buffer, int offset, int length) {
		double temp[] = new double[length];
		System.arraycopy(buffer, offset, temp, 0, length);
		return temp;
	}

	/**
	 * 获取子数组
	 */
	public static boolean[] subarray(boolean[] buffer, int offset, int length) {
		boolean temp[] = new boolean[length];
		System.arraycopy(buffer, offset, temp, 0, length);
		return temp;
	}


	// ---------------------------------------------------------------- insert

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static <T> T[] insert(T[] dest, T[] src, int offset) {
		return insert(dest, src, offset, null);
	}

	public static <T> T[] insert(T[] dest, T src, int offset) {
		return insert(dest, src, offset, null);
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	@SuppressWarnings({"unchecked"})
	public static <T> T[] insert(T[] dest, T[] src, int offset, Class<?> componentType) {
		if (componentType == null) {
			componentType = dest.getClass().getComponentType();
		}
		T[] temp = (T[]) Array.newInstance(componentType, dest.length + src.length);
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset, temp, src.length + offset, dest.length - offset);
		return temp;
	}

	@SuppressWarnings({"unchecked"})
	public static <T> T[] insert(T[] dest, T src, int offset, Class<?> componentType) {
		if (componentType == null) {
			componentType = dest.getClass().getComponentType();
		}
		T[] temp = (T[]) Array.newInstance(componentType, dest.length + 1);
		System.arraycopy(dest, 0, temp, 0, offset);
		temp[offset] = src;
		System.arraycopy(dest, offset, temp, offset + 1, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static String[] insert(String[] dest, String[] src, int offset) {
		String[] temp = new String[dest.length + src.length];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset, temp, src.length + offset, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static String[] insert(String[] dest, String src, int offset) {
		String[] temp = new String[dest.length + 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		temp[offset] = src;
		System.arraycopy(dest, offset, temp, offset + 1, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static byte[] insert(byte[] dest, byte[] src, int offset) {
		byte[] temp = new byte[dest.length + src.length];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset, temp, src.length + offset, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static byte[] insert(byte[] dest, byte src, int offset) {
		byte[] temp = new byte[dest.length + 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		temp[offset] = src;
		System.arraycopy(dest, offset, temp, offset + 1, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static char[] insert(char[] dest, char[] src, int offset) {
		char[] temp = new char[dest.length + src.length];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset, temp, src.length + offset, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static char[] insert(char[] dest, char src, int offset) {
		char[] temp = new char[dest.length + 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		temp[offset] = src;
		System.arraycopy(dest, offset, temp, offset + 1, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static short[] insert(short[] dest, short[] src, int offset) {
		short[] temp = new short[dest.length + src.length];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset, temp, src.length + offset, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static short[] insert(short[] dest, short src, int offset) {
		short[] temp = new short[dest.length + 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		temp[offset] = src;
		System.arraycopy(dest, offset, temp, offset + 1, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static int[] insert(int[] dest, int[] src, int offset) {
		int[] temp = new int[dest.length + src.length];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset, temp, src.length + offset, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static int[] insert(int[] dest, int src, int offset) {
		int[] temp = new int[dest.length + 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		temp[offset] = src;
		System.arraycopy(dest, offset, temp, offset + 1, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static long[] insert(long[] dest, long[] src, int offset) {
		long[] temp = new long[dest.length + src.length];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset, temp, src.length + offset, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static long[] insert(long[] dest, long src, int offset) {
		long[] temp = new long[dest.length + 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		temp[offset] = src;
		System.arraycopy(dest, offset, temp, offset + 1, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static float[] insert(float[] dest, float[] src, int offset) {
		float[] temp = new float[dest.length + src.length];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset, temp, src.length + offset, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static float[] insert(float[] dest, float src, int offset) {
		float[] temp = new float[dest.length + 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		temp[offset] = src;
		System.arraycopy(dest, offset, temp, offset + 1, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static double[] insert(double[] dest, double[] src, int offset) {
		double[] temp = new double[dest.length + src.length];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset, temp, src.length + offset, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static double[] insert(double[] dest, double src, int offset) {
		double[] temp = new double[dest.length + 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		temp[offset] = src;
		System.arraycopy(dest, offset, temp, offset + 1, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static boolean[] insert(boolean[] dest, boolean[] src, int offset) {
		boolean[] temp = new boolean[dest.length + src.length];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset, temp, src.length + offset, dest.length - offset);
		return temp;
	}

	/**
	 * 将一个数组插入到另外一个数组中
	 */
	public static boolean[] insert(boolean[] dest, boolean src, int offset) {
		boolean[] temp = new boolean[dest.length + 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		temp[offset] = src;
		System.arraycopy(dest, offset, temp, offset + 1, dest.length - offset);
		return temp;
	}


	// ---------------------------------------------------------------- insertAt

	/**
	 * 插入一个数组（src）到另外一个数组（dest）的指定位置（offset）。并且offset位置元素会被替换掉
	 */
	public static <T> T[] insertAt(T[] dest, T[] src, int offset) {
		return insertAt(dest, src, offset, null);
	}

	/**
	 * 插入一个数组（src）到另外一个数组（dest）的指定位置（offset）。并且offset位置元素会被替换掉
	 */
	@SuppressWarnings({"unchecked"})
	public static <T> T[] insertAt(T[] dest, T[] src, int offset, Class<?> componentType) {
		if (componentType == null) {
			componentType = dest.getClass().getComponentType();
		}
		T[] temp = (T[]) Array.newInstance(componentType, dest.length + src.length - 1);
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset + 1, temp, src.length + offset, dest.length - offset - 1);
		return temp;
	}

	/**
	 * 插入一个数组（src）到另外一个数组（dest）的指定位置（offset）。并且offset位置元素会被替换掉
	 */
	public static String[] insertAt(String[] dest, String[] src, int offset) {
		String[] temp = new String[dest.length + src.length - 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset + 1, temp, src.length + offset, dest.length - offset - 1);
		return temp;
	}

	/**
	 * 插入一个数组（src）到另外一个数组（dest）的指定位置（offset）。并且offset位置元素会被替换掉
	 */
	public static byte[] insertAt(byte[] dest, byte[] src, int offset) {
		byte[] temp = new byte[dest.length + src.length - 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset + 1, temp, src.length + offset, dest.length - offset - 1);
		return temp;
	}

	/**
	 * 插入一个数组（src）到另外一个数组（dest）的指定位置（offset）。并且offset位置元素会被替换掉
	 */
	public static char[] insertAt(char[] dest, char[] src, int offset) {
		char[] temp = new char[dest.length + src.length - 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset + 1, temp, src.length + offset, dest.length - offset - 1);
		return temp;
	}

	/**
	 * 插入一个数组（src）到另外一个数组（dest）的指定位置（offset）。并且offset位置元素会被替换掉
	 */
	public static short[] insertAt(short[] dest, short[] src, int offset) {
		short[] temp = new short[dest.length + src.length - 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset + 1, temp, src.length + offset, dest.length - offset - 1);
		return temp;
	}

	/**
	 * 插入一个数组（src）到另外一个数组（dest）的指定位置（offset）。并且offset位置元素会被替换掉
	 */
	public static int[] insertAt(int[] dest, int[] src, int offset) {
		int[] temp = new int[dest.length + src.length - 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset + 1, temp, src.length + offset, dest.length - offset - 1);
		return temp;
	}

	/**
	 * 插入一个数组（src）到另外一个数组（dest）的指定位置（offset）。并且offset位置元素会被替换掉
	 */
	public static long[] insertAt(long[] dest, long[] src, int offset) {
		long[] temp = new long[dest.length + src.length - 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset + 1, temp, src.length + offset, dest.length - offset - 1);
		return temp;
	}

	/**
	 * 插入一个数组（src）到另外一个数组（dest）的指定位置（offset）。并且offset位置元素会被替换掉
	 */
	public static float[] insertAt(float[] dest, float[] src, int offset) {
		float[] temp = new float[dest.length + src.length - 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset + 1, temp, src.length + offset, dest.length - offset - 1);
		return temp;
	}

	/**
	 * 插入一个数组（src）到另外一个数组（dest）的指定位置（offset）。并且offset位置元素会被替换掉
	 */
	public static double[] insertAt(double[] dest, double[] src, int offset) {
		double[] temp = new double[dest.length + src.length - 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset + 1, temp, src.length + offset, dest.length - offset - 1);
		return temp;
	}

	/**
	 * 插入一个数组（src）到另外一个数组（dest）的指定位置（offset）。并且offset位置元素会被替换掉
	 */
	public static boolean[] insertAt(boolean[] dest, boolean[] src, int offset) {
		boolean[] temp = new boolean[dest.length + src.length - 1];
		System.arraycopy(dest, 0, temp, 0, offset);
		System.arraycopy(src, 0, temp, offset, src.length);
		System.arraycopy(dest, offset + 1, temp, src.length + offset, dest.length - offset - 1);
		return temp;
	}


	// ---------------------------------------------------------------- convert


	/**
	 * 包装类型的数组转换成基本类型的数组
	 */
	public static byte[] values(Byte[] array) {
		byte[] dest = new byte[array.length];
		for (int i = 0; i < array.length; i++) {
			Byte v = array[i];
			if (v != null) {
				dest[i] = v.byteValue();
			}
		}
		return dest;
	}
	/**
	 * 基本类型的数组转换成包装类型数组
	 */
	public static Byte[] valuesOf(byte[] array) {
		Byte[] dest = new Byte[array.length];
		for (int i = 0; i < array.length; i++) {
			dest[i] = Byte.valueOf(array[i]);
		}
		return dest;
	}


	/**
	 * 包装类型的数组转换成基本类型的数组
	 */
	public static char[] values(Character[] array) {
		char[] dest = new char[array.length];
		for (int i = 0; i < array.length; i++) {
			Character v = array[i];
			if (v != null) {
				dest[i] = v.charValue();
			}
		}
		return dest;
	}
	/**
	 * 基本类型的数组转换成包装类型数组
	 */
	public static Character[] valuesOf(char[] array) {
		Character[] dest = new Character[array.length];
		for (int i = 0; i < array.length; i++) {
			dest[i] = Character.valueOf(array[i]);
		}
		return dest;
	}


	/**
	 * 包装类型的数组转换成基本类型的数组
	 */
	public static short[] values(Short[] array) {
		short[] dest = new short[array.length];
		for (int i = 0; i < array.length; i++) {
			Short v = array[i];
			if (v != null) {
				dest[i] = v.shortValue();
			}
		}
		return dest;
	}
	/**
	 * 基本类型的数组转换成包装类型数组
	 */
	public static Short[] valuesOf(short[] array) {
		Short[] dest = new Short[array.length];
		for (int i = 0; i < array.length; i++) {
			dest[i] = Short.valueOf(array[i]);
		}
		return dest;
	}


	/**
	 * 包装类型的数组转换成基本类型的数组
	 */
	public static int[] values(Integer[] array) {
		int[] dest = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			Integer v = array[i];
			if (v != null) {
				dest[i] = v.intValue();
			}
		}
		return dest;
	}
	/**
	 * 基本类型的数组转换成包装类型数组
	 */
	public static Integer[] valuesOf(int[] array) {
		Integer[] dest = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			dest[i] = Integer.valueOf(array[i]);
		}
		return dest;
	}


	/**
	 * 包装类型的数组转换成基本类型的数组
	 */
	public static long[] values(Long[] array) {
		long[] dest = new long[array.length];
		for (int i = 0; i < array.length; i++) {
			Long v = array[i];
			if (v != null) {
				dest[i] = v.longValue();
			}
		}
		return dest;
	}
	/**
	 * 基本类型的数组转换成包装类型数组
	 */
	public static Long[] valuesOf(long[] array) {
		Long[] dest = new Long[array.length];
		for (int i = 0; i < array.length; i++) {
			dest[i] = Long.valueOf(array[i]);
		}
		return dest;
	}


	/**
	 * 包装类型的数组转换成基本类型的数组
	 */
	public static float[] values(Float[] array) {
		float[] dest = new float[array.length];
		for (int i = 0; i < array.length; i++) {
			Float v = array[i];
			if (v != null) {
				dest[i] = v.floatValue();
			}
		}
		return dest;
	}
	/**
	 * 基本类型的数组转换成包装类型数组
	 */
	public static Float[] valuesOf(float[] array) {
		Float[] dest = new Float[array.length];
		for (int i = 0; i < array.length; i++) {
			dest[i] = Float.valueOf(array[i]);
		}
		return dest;
	}


	/**
	 * 包装类型的数组转换成基本类型的数组
	 */
	public static double[] values(Double[] array) {
		double[] dest = new double[array.length];
		for (int i = 0; i < array.length; i++) {
			Double v = array[i];
			if (v != null) {
				dest[i] = v.doubleValue();
			}
		}
		return dest;
	}
	/**
	 * 基本类型的数组转换成包装类型数组
	 */
	public static Double[] valuesOf(double[] array) {
		Double[] dest = new Double[array.length];
		for (int i = 0; i < array.length; i++) {
			dest[i] = Double.valueOf(array[i]);
		}
		return dest;
	}


	/**
	 * 包装类型的数组转换成基本类型的数组
	 */
	public static boolean[] values(Boolean[] array) {
		boolean[] dest = new boolean[array.length];
		for (int i = 0; i < array.length; i++) {
			Boolean v = array[i];
			if (v != null) {
				dest[i] = v.booleanValue();
			}
		}
		return dest;
	}
	/**
	 * 基本类型的数组转换成包装类型数组
	 */
	public static Boolean[] valuesOf(boolean[] array) {
		Boolean[] dest = new Boolean[array.length];
		for (int i = 0; i < array.length; i++) {
			dest[i] = Boolean.valueOf(array[i]);
		}
		return dest;
	}



	// ---------------------------------------------------------------- indexof


	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(byte[] array, byte value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
	public static boolean contains(byte[] array, byte value) {
		return indexOf(array, value) != -1;
	}
	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(byte[] array, byte value, int startIndex) {
		for (int i = startIndex; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(byte[] array, byte value, int startIndex, int endIndex) {
		for (int i = startIndex; i < endIndex; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(char[] array, char value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
	public static boolean contains(char[] array, char value) {
		return indexOf(array, value) != -1;
	}
	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(char[] array, char value, int startIndex) {
		for (int i = startIndex; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(char[] array, char value, int startIndex, int endIndex) {
		for (int i = startIndex; i < endIndex; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(short[] array, short value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
	public static boolean contains(short[] array, short value) {
		return indexOf(array, value) != -1;
	}
	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(short[] array, short value, int startIndex) {
		for (int i = startIndex; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(short[] array, short value, int startIndex, int endIndex) {
		for (int i = startIndex; i < endIndex; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(int[] array, int value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
	public static boolean contains(int[] array, int value) {
		return indexOf(array, value) != -1;
	}
	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(int[] array, int value, int startIndex) {
		for (int i = startIndex; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(int[] array, int value, int startIndex, int endIndex) {
		for (int i = startIndex; i < endIndex; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(long[] array, long value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
	public static boolean contains(long[] array, long value) {
		return indexOf(array, value) != -1;
	}
	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(long[] array, long value, int startIndex) {
		for (int i = startIndex; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(long[] array, long value, int startIndex, int endIndex) {
		for (int i = startIndex; i < endIndex; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(boolean[] array, boolean value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
	public static boolean contains(boolean[] array, boolean value) {
		return indexOf(array, value) != -1;
	}
	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(boolean[] array, boolean value, int startIndex) {
		for (int i = startIndex; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * 找到元素在数组中第一个出现的下标,根据给定的范围查找
	 */
	public static int indexOf(boolean[] array, boolean value, int startIndex, int endIndex) {
		for (int i = startIndex; i < endIndex; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(float[] array, float value) {
		for (int i = 0; i < array.length; i++) {
			if (Float.compare(array[i], value) == 0) {
				return i;
			}
		}
		return -1;
	}
	public static boolean contains(float[] array, float value) {
		return indexOf(array, value) != -1;
	}
	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(float[] array, float value, int startIndex) {
		for (int i = startIndex; i < array.length; i++) {
			if (Float.compare(array[i], value) == 0) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * 找到元素在数组中第一个出现的下标，在给定范围内查找
	 */
	public static int indexOf(float[] array, float value, int startIndex, int endIndex) {
		for (int i = startIndex; i < endIndex; i++) {
			if (Float.compare(array[i], value) == 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(double[] array, double value) {
		for (int i = 0; i < array.length; i++) {
			if (Double.compare(array[i], value) == 0) {
				return i;
			}
		}
		return -1;
	}
	public static boolean contains(double[] array, double value) {
		return indexOf(array, value) != -1;
	}
	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(double[] array, double value, int startIndex) {
		for (int i = startIndex; i < array.length; i++) {
			if (Double.compare(array[i], value) == 0) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(double[] array, double value, int startIndex, int endIndex) {
		for (int i = startIndex; i < endIndex; i++) {
			if (Double.compare(array[i], value) == 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(Object[] array, Object value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}
	public static boolean contains(Object[] array, Object value) {
		return indexOf(array, value) != -1;
	}

	/**
	 * 找到元素在数组中第一个出现的下标
	 */
	public static int indexOf(Object[] array, Object value, int startIndex) {
		for (int i = startIndex; i < array.length; i++) {
			if (array[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}
	public static boolean contains(Object[] array, Object value, int startIndex) {
		return indexOf(array, value, startIndex) != -1;
	}




	// ---------------------------------------------------------------- indexof 2


	/**
	 * 查找子数组（sub）在数组中第一次出现的下标
	 */
	public static int indexOf(byte[] array, byte[] sub) {
		return indexOf(array, sub, 0, array.length);
	}
	public static boolean contains(byte[] array, byte[] sub) {
		return indexOf(array, sub) != -1;
	}


	/**
	 * 查找子数组（sub）在数组中第一次出现的下标，在给定范围内查找
	 */
	public static int indexOf(byte[] array, byte[] sub, int startIndex) {
		return indexOf(array, sub, startIndex, array.length);
	}

	/**
	 * 查找子数组（sub）在数组中第一次出现的下标，在给定范围内查找
	 */
	public static int indexOf(byte[] array, byte[] sub, int startIndex, int endIndex) {
		int sublen = sub.length;
		if (sublen == 0) {
			return startIndex;
		}
		int total = endIndex - sublen + 1;
		byte c = sub[0];
	mainloop:
		for (int i = startIndex; i < total; i++) {
			if (array[i] != c) {
				continue;
			}
			int j = 1;
			int k = i + 1;
			while (j < sublen) {
				if (sub[j] != array[k]) {
					continue mainloop;
				}
				j++; k++;
			}
			return i;
		}
		return -1;
	}

	/**
	 * 查找子数组（sub）在数组中第一次出现的下标
	 */
	public static int indexOf(char[] array, char[] sub) {
		return indexOf(array, sub, 0, array.length);
	}
	public static boolean contains(char[] array, char[] sub) {
		return indexOf(array, sub) != -1;
	}


	/**
	 * 查找子数组（sub）在数组中第一次出现的下标，在给定范围内查找
	 */
	public static int indexOf(char[] array, char[] sub, int startIndex) {
		return indexOf(array, sub, startIndex, array.length);
	}

	/**
	 * 查找子数组（sub）在数组中第一次出现的下标，在给定范围内查找
	 */
	public static int indexOf(char[] array, char[] sub, int startIndex, int endIndex) {
		int sublen = sub.length;
		if (sublen == 0) {
			return startIndex;
		}
		int total = endIndex - sublen + 1;
		char c = sub[0];
	mainloop:
		for (int i = startIndex; i < total; i++) {
			if (array[i] != c) {
				continue;
			}
			int j = 1;
			int k = i + 1;
			while (j < sublen) {
				if (sub[j] != array[k]) {
					continue mainloop;
				}
				j++; k++;
			}
			return i;
		}
		return -1;
	}

	/**
	 * 查找子数组（sub）在数组中第一次出现的下标
	 */
	public static int indexOf(short[] array, short[] sub) {
		return indexOf(array, sub, 0, array.length);
	}
	public static boolean contains(short[] array, short[] sub) {
		return indexOf(array, sub) != -1;
	}


	/**
	 * 查找子数组（sub）在数组中第一次出现的下标，在给定范围内查找
	 */
	public static int indexOf(short[] array, short[] sub, int startIndex) {
		return indexOf(array, sub, startIndex, array.length);
	}

	/**
	 * 查找子数组（sub）在数组中第一次出现的下标，在给定范围内查找
	 */
	public static int indexOf(short[] array, short[] sub, int startIndex, int endIndex) {
		int sublen = sub.length;
		if (sublen == 0) {
			return startIndex;
		}
		int total = endIndex - sublen + 1;
		short c = sub[0];
	mainloop:
		for (int i = startIndex; i < total; i++) {
			if (array[i] != c) {
				continue;
			}
			int j = 1;
			int k = i + 1;
			while (j < sublen) {
				if (sub[j] != array[k]) {
					continue mainloop;
				}
				j++; k++;
			}
			return i;
		}
		return -1;
	}

	/**
	 * 查找子数组（sub）在数组中第一次出现的下标
	 */
	public static int indexOf(int[] array, int[] sub) {
		return indexOf(array, sub, 0, array.length);
	}
	public static boolean contains(int[] array, int[] sub) {
		return indexOf(array, sub) != -1;
	}


	/**
	 * 查找子数组（sub）在数组中第一次出现的下标，在给定范围内查找
	 */
	public static int indexOf(int[] array, int[] sub, int startIndex) {
		return indexOf(array, sub, startIndex, array.length);
	}

	/**
	 * 查找子数组（sub）在数组中第一次出现的下标，在给定范围内查找
	 */
	public static int indexOf(int[] array, int[] sub, int startIndex, int endIndex) {
		int sublen = sub.length;
		if (sublen == 0) {
			return startIndex;
		}
		int total = endIndex - sublen + 1;
		int c = sub[0];
	mainloop:
		for (int i = startIndex; i < total; i++) {
			if (array[i] != c) {
				continue;
			}
			int j = 1;
			int k = i + 1;
			while (j < sublen) {
				if (sub[j] != array[k]) {
					continue mainloop;
				}
				j++; k++;
			}
			return i;
		}
		return -1;
	}

	/**
	 * 查找子数组（sub）在数组中第一次出现的下标
	 */
	public static int indexOf(long[] array, long[] sub) {
		return indexOf(array, sub, 0, array.length);
	}
	public static boolean contains(long[] array, long[] sub) {
		return indexOf(array, sub) != -1;
	}


	/**
	 * 查找子数组（sub）在数组中第一次出现的下标，在给定范围内查找
	 */
	public static int indexOf(long[] array, long[] sub, int startIndex) {
		return indexOf(array, sub, startIndex, array.length);
	}

	/**
	 * 查找子数组（sub）在数组中第一次出现的下标，在给定范围内查找
	 */
	public static int indexOf(long[] array, long[] sub, int startIndex, int endIndex) {
		int sublen = sub.length;
		if (sublen == 0) {
			return startIndex;
		}
		int total = endIndex - sublen + 1;
		long c = sub[0];
	mainloop:
		for (int i = startIndex; i < total; i++) {
			if (array[i] != c) {
				continue;
			}
			int j = 1;
			int k = i + 1;
			while (j < sublen) {
				if (sub[j] != array[k]) {
					continue mainloop;
				}
				j++; k++;
			}
			return i;
		}
		return -1;
	}

	/**
	 * 查找子数组（sub）在数组中第一次出现的下标
	 */
	public static int indexOf(boolean[] array, boolean[] sub) {
		return indexOf(array, sub, 0, array.length);
	}
	public static boolean contains(boolean[] array, boolean[] sub) {
		return indexOf(array, sub) != -1;
	}


	/**
	 * 查找子数组（sub）在数组中第一次出现的下标，在给定范围内查找
	 */
	public static int indexOf(boolean[] array, boolean[] sub, int startIndex) {
		return indexOf(array, sub, startIndex, array.length);
	}

	/**
	 * 查找子数组（sub）在数组中第一次出现的下标，在给定范围内查找
	 */
	public static int indexOf(boolean[] array, boolean[] sub, int startIndex, int endIndex) {
		int sublen = sub.length;
		if (sublen == 0) {
			return startIndex;
		}
		int total = endIndex - sublen + 1;
		boolean c = sub[0];
	mainloop:
		for (int i = startIndex; i < total; i++) {
			if (array[i] != c) {
				continue;
			}
			int j = 1;
			int k = i + 1;
			while (j < sublen) {
				if (sub[j] != array[k]) {
					continue mainloop;
				}
				j++; k++;
			}
			return i;
		}
		return -1;
	}

	/**
	 * 查找子数组（sub）在数组中第一次出现的下标
	 */
	public static int indexOf(float[] array, float[] sub) {
		return indexOf(array, sub, 0, array.length);
	}
	public static boolean contains(float[] array, float[] sub) {
		return indexOf(array, sub) != -1;
	}


	/**
	 * 查找子数组（sub）在数组中第一次出现的下标，在给定范围内查找
	 */
	public static int indexOf(float[] array, float[] sub, int startIndex) {
		return indexOf(array, sub, startIndex, array.length);
	}

	/**
	 * 查找子数组（sub）在数组中第一次出现的下标，在给定范围内查找
	 */
	public static int indexOf(float[] array, float[] sub, int startIndex, int endIndex) {
		int sublen = sub.length;
		if (sublen == 0) {
			return startIndex;
		}
		int total = endIndex - sublen + 1;
		float c = sub[0];
	mainloop:
		for (int i = startIndex; i < total; i++) {
			if (Float.compare(array[i], c) != 0) {
				continue;
			}
			int j = 1;
			int k = i + 1;
			while (j < sublen) {
				if (Float.compare(sub[j], array[k]) != 0) {
					continue mainloop;
				}
				j++; k++;
			}
			return i;
		}
		return -1;
	}

	/**
	 * 查找子数组（sub）在数组中第一次出现的下标
	 */
	public static int indexOf(double[] array, double[] sub) {
		return indexOf(array, sub, 0, array.length);
	}
	public static boolean contains(double[] array, double[] sub) {
		return indexOf(array, sub) != -1;
	}


	/**
	 * 查找子数组（sub）在数组中第一次出现的下标，在给定范围内查找
	 */
	public static int indexOf(double[] array, double[] sub, int startIndex) {
		return indexOf(array, sub, startIndex, array.length);
	}

	/**
	 * 查找子数组（sub）在数组中第一次出现的下标，在给定范围内查找
	 */
	public static int indexOf(double[] array, double[] sub, int startIndex, int endIndex) {
		int sublen = sub.length;
		if (sublen == 0) {
			return startIndex;
		}
		int total = endIndex - sublen + 1;
		double c = sub[0];
	mainloop:
		for (int i = startIndex; i < total; i++) {
			if (Double.compare(array[i], c) != 0) {
				continue;
			}
			int j = 1;
			int k = i + 1;
			while (j < sublen) {
				if (Double.compare(sub[j], array[k]) != 0) {
					continue mainloop;
				}
				j++; k++;
			}
			return i;
		}
		return -1;
	}
}