package org.aerie.forest.framework;

import org.aerie.forest.core.factory.ForestFactory;
import org.aerie.forest.core.init.ForestConfig;

/**
 * 
 * @description forest守护者
 * @author falconTrotk
 * @company aerie
 * @date 2019年10月10日下午4:15:42
 * @version 1.0.1
 */
public final class Forester {
	/**
	 * forest的环境变量
	 */
	public final static ForestConfig FOREST_CONFIG = ForestConfig.INSTANCE;
	/**
	 * 静态初始化log
	 */
	static {
		FOREST_CONFIG.logInit();
	}
	/**
	 * forest架构元素工厂
	 */
	public final static ForestFactory forestFactory = ForestFactory.INSTANCE;
}
