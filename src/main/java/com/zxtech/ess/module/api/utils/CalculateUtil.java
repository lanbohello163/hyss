package com.zxtech.ess.module.api.utils;


import java.math.BigDecimal;

public class CalculateUtil {
	
	/**
	 * 折合层数自动计算，
	 * “贯通门”为“否”时：
	 * 当门<层时，折合层数=（层+门）
	 * 当门≥层时，折合层数=门；
	 * “贯通门”为“是”时：
	 * “双开门层数”值假设为f：
	 * 当门-f<层时，折合层数 =（层+门）+1；
	 * 当门-f≥层时，折合层数 = 门-0.5f+1
	 * 
	 * @param through_door 贯通门
	 * @param ele_door 门
	 * @param ele_floor 层
	 * @param double_door_layer 双开门层数
	 * @return
	 */
	public static BigDecimal calculateConversionLayer(String through_door, Integer ele_door, Integer ele_floor, BigDecimal double_door_layer){
		if (through_door == null || "".equals(through_door)) {
			through_door = "0";
		}
		if (ele_door == null) {
			ele_door = 0;
		}
		if (ele_floor == null) {
			ele_floor = 0;
		}
		if (double_door_layer == null) {
			double_door_layer = new BigDecimal(0);
		}
		BigDecimal conversion_layer = null;
		if ("0".equals(through_door)) {
			if (ele_door.intValue() < ele_floor.intValue()) {
				conversion_layer = new BigDecimal(ele_door.intValue() + ele_floor.intValue());
			} else {
				conversion_layer = new BigDecimal(ele_door.intValue());
			}
		} else {
			if (ele_door.intValue() - double_door_layer.doubleValue() < ele_floor.intValue()) {
				conversion_layer = new BigDecimal(ele_door.intValue() + ele_floor.intValue() + 1);
			} else {
				conversion_layer = new BigDecimal(ele_door.intValue() - double_door_layer.doubleValue() * 0.5 + 1);
			}
		}
		return conversion_layer;
	}
}
