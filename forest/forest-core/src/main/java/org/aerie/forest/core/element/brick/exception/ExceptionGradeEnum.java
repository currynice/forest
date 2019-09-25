package org.aerie.forest.core.element.brick.exception;

import org.aerie.forest.core.element.brick.log.GlobalLogger;

/**
 * 
 * @description 异常的等级【枚举】
 * @author falconTrotk
 * @company aerie
 * @date 2019年9月12日下午1:14:06
 * @version 1.0.1
 */
public enum ExceptionGradeEnum {
	/**
	 * 信息
	 */
	INFO("信息", 1),
	/**
	 * 警告
	 */
	WARN("警告", 2),
	/**
	 * 错误
	 */
	ERROR("错误", 3),
	/**
	 * 崩溃
	 */
	FATAL("崩溃", 4);
	/**
	 * 异常等级
	 */
	private final String MESSAGE;
	/**
	 * 
	 */
	private final int LEVEL;

	private ExceptionGradeEnum(String message, int level) {
		this.MESSAGE = message;
		this.LEVEL = level;
		GlobalLogger.INSTANCE.getLOGGER().trace("【单例】异常等级【" + this.name() + ":" + this.MESSAGE + "】初始化");
	}

	public String getMESSAGE() {
		return MESSAGE;
	}

	public int getLEVEL() {
		return LEVEL;
	}

}
