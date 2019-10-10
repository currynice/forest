package org.aerie.forest.core.init;

import org.aerie.forest.core.element.brick.exception.ExceptionGradeEnum;

/**
 * 
 * @description forest的环境变量
 * @author falconTrotk
 * @company aerie
 * @date 2019年9月25日下午5:01:08
 * @version 1.0.1
 */
public enum ForestConfig {
	INSTANCE;
	/**
	 * log环境初始化标识符
	 */
	private boolean logInitConfig = false;
	/**
	 * 默认异常等级
	 */
	private ExceptionGradeEnum defaultExceptionLevel = ExceptionGradeEnum.WARN;

	private ForestConfig() {
		// Do Nothing
	}

	public boolean isLogInitConfig() {
		return logInitConfig;
	}

	public ExceptionGradeEnum getDefaultExceptionLevel() {
		return defaultExceptionLevel;
	}

	public void setDefaultExceptionLevel(ExceptionGradeEnum defaultExceptionLevel) {
		this.defaultExceptionLevel = defaultExceptionLevel;
	}

	/**
	 * 
	 * @description log初始化
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年10月10日下午3:30:58
	 * @version 1.0.1
	 */
	public void logInit() {
		ForestLogInit.INSTANCE.loadLogConfig();
		logInitConfig = true;
	}
}
