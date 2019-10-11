package org.aerie.forest.core.element.rebar.entity.processer.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @description 异常处理的日志配置器
 * @author falconTrotk
 * @company aerie
 * @date 2019年7月31日上午12:20:56
 * @version 1.0.1
 */
enum ExceptionLogger {
	/**
	 * 全局异常信息日志
	 */
	EXCEPTION_LOGGER(LoggerFactory.getLogger("ExceptionLogger")),
	/**
	 * 全局异常堆栈信息日志
	 */
	EXC_STACK_TRACE_LOGGER(LoggerFactory.getLogger("ExcStackTraceLogger"));
	/**
	 * 
	 */
	final Logger LOGGER;

	private ExceptionLogger(Logger lOGGER) {
		LOGGER = lOGGER;
	}

	public Logger getLOGGER() {
		return LOGGER;
	}

}
