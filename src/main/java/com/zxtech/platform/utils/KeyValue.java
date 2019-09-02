package com.zxtech.platform.utils;

/**
 * 简单的名值对对象
 */
public class KeyValue<K, V> {

	protected K key;
	protected V value;

	public KeyValue() {
	}

	public KeyValue(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Sets a key.
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * Returns a key.
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Returns a value.
	 */
	public V getValue() {
		return value;
	}

	/**
	 * Sets a value.
	 */
	public void setValue(V value) {
		this.value = value;
	}
}
