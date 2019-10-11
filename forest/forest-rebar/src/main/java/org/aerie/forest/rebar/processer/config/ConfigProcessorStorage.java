package org.aerie.forest.rebar.processer.config;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.ForestRebarStorage;

/**
 * 
 * @description 环境处理器入库组件
 * @author trotkFaclon
 * @company aerie
 * @version 1.0.1
 * @date 2019年9月15日下午7:02:31
 *
 */
public final class ConfigProcessorStorage extends ForestRebarStorage {

	/**
	 * 单例
	 */
	private static ConfigProcessorStorage configProcessorStorage;

	private ConfigProcessorStorage() {
		synchronized (ConfigProcessorStorage.class) {
			if (singletonFlag == true) {
				throw new RuntimeException("受到反射攻击");
			}
			singletonFlag = true;
			GlobalLogger.INSTANCE.getLOGGER().info("【单例】环境处理器入库组件初始化");
		}
	}

	/**
	 * 
	 * @description 获得单例
	 * @return
	 * @throws Exception
	 */
	public final static ConfigProcessorStorage getInstance() {
		/**
		 * 双重锁
		 */
		if (configProcessorStorage == null) {
			synchronized (ConfigProcessorStorage.class) {
				if (configProcessorStorage == null) {
					configProcessorStorage = new ConfigProcessorStorage();
				}
			}
		}
		return configProcessorStorage;
	}

	@Override
	protected ConfigProcessor getForestRebar() {

		return ConfigProcessor.getInstance();
	}
}
