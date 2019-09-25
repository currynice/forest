package org.aerie.forest.core.element.brick.log;

import org.aerie.forest.core.init.ForestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * 
 * @description 全局日志配置器
 * @author falconTrotk
 * @company aerie
 * @date 2019年9月12日下午1:13:52
 * @version 1.0.1
 */
public enum GlobalLogger {
	INSTANCE;
	/**
	 * 全局异常信息日志【枚举类无法通过lombok注解注入Logger】
	 */
	private final Logger LOGGER = LoggerFactory.getLogger("Logger");
	/**
	 * FATAL日志标记：==> 字体颜色：30:黑 31:红 32:绿 33:黄 34:蓝色 35:紫色 36:深绿 37:白色 背景颜色：40:黑
	 * 41:深红42:绿43:黄色44:蓝色 45:紫色 46:深绿 47:白色
	 */
	private final Marker MARKERFATAL = MarkerFactory
			.getMarker("  \u001b[45;1;37m!*#*#* FATAL *#*#*!\u001b[47;0;35m  ==>");

	private GlobalLogger() {
		LOGGER.debug("全局日志配置器初始化");
	}

	public Logger getLOGGER() {
		if (!ForestConfig.INSTANCE.isLogInitConfig()) {
			throw new Error("log未完成配置文件加载");
		}
		return LOGGER;
	}

	public Marker getMARKERFATAL() {
		if (!ForestConfig.INSTANCE.isLogInitConfig()) {
			throw new Error("log未完成配置文件加载");
		}
		return MARKERFATAL;
	}
}