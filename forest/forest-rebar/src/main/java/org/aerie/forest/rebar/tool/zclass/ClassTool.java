package org.aerie.forest.rebar.tool.zclass;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.tool.ForestTool;

public class ClassTool extends ForestTool {
	/**
	 * 单例
	 */
	private static ClassTool classTool;

	private ClassTool() {
		synchronized (ClassTool.class) {
			if (singletonFlag == true) {
				throw new RuntimeException(forestRebarName + "受到反射攻击");
			}
			singletonFlag = true;
			GlobalLogger.INSTANCE.getLOGGER().info("【单例】" + forestRebarName + "初始化");
		}
	}

	/**
	 * 
	 * @description 获得单例
	 * @return
	 * @throws Exception
	 */
	protected final static ClassTool getInstance() {
		/**
		 * 双重锁
		 */
		if (classTool == null) {
			synchronized (ClassTool.class) {
				if (classTool == null) {
					classTool = new ClassTool();
				}
			}
		}
		return classTool;
	}

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
