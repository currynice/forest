package org.aerie.forest.rebar.tool.string;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.ForestRebarStorage;

/**
 * 
 * @description File工具入库组件
 * @author falconTrotk
 * @company aerie
 * @date 2019年9月27日下午2:30:27
 * @version 1.0.1
 */
public class StringToolStorage extends ForestRebarStorage {

	/**
	 * 单例
	 */
	private static StringToolStorage fileToolStorage;

	private StringToolStorage() {
		synchronized (StringToolStorage.class) {
			if (singletonFlag == true) {
				throw new RuntimeException("受到反射攻击");
			}
			singletonFlag = true;
			GlobalLogger.INSTANCE.getLOGGER().info("【单例】File工具入库初始化");
		}
	}

	/**
	 * 
	 * @description 获得单例
	 * @return
	 * @throws Exception
	 */
	public final static StringToolStorage getInstance() {
		/**
		 * 双重锁
		 */
		if (fileToolStorage == null) {
			synchronized (StringToolStorage.class) {
				if (fileToolStorage == null) {
					fileToolStorage = new StringToolStorage();
				}
			}
		}
		return fileToolStorage;
	}

	@Override
	public StringTool getForestRebar() {

		return StringTool.getInstance();
	}

}