package org.aerie.forest.core.element.rebar.unsingleton.tool;

/**
 * 
 * @description 类的工具类
 * @author falconTrotk
 * @company aerie
 * @date 2019年8月19日下午9:52:21
 * @version 1.0.1
 */
public enum ClassTool {
	INSTANCE;
	/**
	 * 
	 * @description 判断对象是不是基本数据类型
	 * @param object
	 * @return
	 */
	public boolean isBaseType(Object object) {
		if (object instanceof Byte) {
			return true;
		}
		if (object instanceof Short) {
			return true;
		}
		if (object instanceof Byte) {
			return true;
		}
		if (object instanceof Long) {
			return true;
		}
		if (object instanceof Float) {
			return true;
		}
		if (object instanceof Double) {
			return true;
		}
		if (object instanceof Boolean) {
			return true;
		}
		if (object instanceof Character) {
			return true;
		}
		if (object instanceof Integer) {
			return true;
		}
		return false;
	}
}
