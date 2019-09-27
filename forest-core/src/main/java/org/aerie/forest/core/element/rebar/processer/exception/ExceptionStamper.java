package org.aerie.forest.core.element.rebar.processer.exception;

import java.util.Optional;

import org.aerie.forest.core.element.brick.exception.ExceptionGradeEnum;
import org.aerie.forest.core.element.brick.log.GlobalLogger;

/**
 * 
 * @description 异常打印器
 * @author trotkfalcon
 * @company aerie
 * @date 2019年7月31日上午9:36:18
 * @version 1.0.1
 */
enum ExceptionStamper {
	INSTANCE;

	void printException(ExceptionLogger exceptionLogger, String message) {
		printException(exceptionLogger, message, null, null);
	}

	void printException(ExceptionLogger exceptionLogger, String message, Throwable throwable) {
		printException(exceptionLogger, message, throwable, null);
	}

	void printException(ExceptionLogger exceptionLogger, String message, ExceptionGradeEnum enumExceptionGradeEnum) {
		printException(exceptionLogger, message, null, enumExceptionGradeEnum);
	}

	/**
	 * 
	 * @description 打印异常
	 * @param message
	 * @param throwable
	 * @param enumExceptionGradeEnum
	 */
	void printException(ExceptionLogger exceptionLogger, String message, Throwable throwable,
			ExceptionGradeEnum enumExceptionGradeEnum) {
		switch (Optional.ofNullable(enumExceptionGradeEnum).orElse(ExceptionGradeEnum.WARN)) {
		case INFO:
			exceptionLogger.getLOGGER().info(message, throwable);
			break;
		case WARN:
			exceptionLogger.getLOGGER().warn(message, throwable);
			break;
		case ERROR:
			exceptionLogger.getLOGGER().error(message, throwable);
			break;
		case FATAL:
			// FATAL标记
			exceptionLogger.getLOGGER().error(GlobalLogger.INSTANCE.getMARKER_FATAL(), message, throwable);
			break;
		}
	}
}
