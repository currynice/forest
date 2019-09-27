package org.aerie.forest.core.element.brick.exception;

import java.util.Optional;

/**
 * 
 * @description 异常包装【将异常包装起来，用包装异常处理器处理】******会忽略抑制异常<以及>包装可抛物中的异常包装所包装的可抛物
 * @author falconTrotk
 * @company aerie
 * @date 2019年9月12日下午1:11:38
 * @version 1.0.1
 */
public class ExceptionPack extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 需要被包装处理的异常
	 */
	private Exception exception;
	/**
	 * 异常的等级
	 */
	private ExceptionGradeEnum exceptionGradeEnum = ExceptionGradeEnum.WARN;
	/**
	 * 是否弹窗
	 */
	private boolean whetherPopUp = false;

	/**
	 * 
	 * @param message
	 * @param throwable
	 * @param exceptionGradeEnum
	 */
	public ExceptionPack(String message, Exception exception) {
		super(Optional.ofNullable(message).filter(p1 -> !p1.isEmpty()).orElse(">--异常包装丢失异常信息--<"));
		this.exception = Optional.ofNullable(exception).orElse(new CustomException(">--异常包装丢失包装异常--<"));
		adjustExceptionGrade(exception);
	}

	/**
	 * 
	 * @param message
	 * @param throwable
	 * @param exceptionGradeEnum
	 */
	public ExceptionPack(String message, Exception exception, ExceptionGradeEnum exceptionGradeEnum) {
		super(Optional.ofNullable(message).filter(p1 -> !p1.isEmpty()).orElse(">--异常包装丢失异常信息--<"));
		this.exception = Optional.ofNullable(exception).orElse(new CustomException(">--异常包装丢失包装异常--<"));
		this.exceptionGradeEnum = Optional.ofNullable(exceptionGradeEnum).orElse(ExceptionGradeEnum.WARN);
		adjustExceptionGrade(exception);
	}

	/**
	 * 
	 * @param message
	 * @param throwable
	 * @param exceptionGradeEnum
	 * @param whetherPopUp
	 */
	public ExceptionPack(String message, Exception exception, ExceptionGradeEnum exceptionGradeEnum,
			boolean whetherPopUp) {
		super(Optional.ofNullable(message).filter(p1 -> !p1.isEmpty()).orElse(">--异常包装丢失异常信息--<"));
		this.exception = Optional.ofNullable(exception).orElse(new CustomException(">--异常包装丢失包装异常--<"));
		this.exceptionGradeEnum = Optional.ofNullable(exceptionGradeEnum).orElse(ExceptionGradeEnum.WARN);
		this.whetherPopUp = Optional.ofNullable(whetherPopUp).orElse(false);
		adjustExceptionGrade(exception);
	}

	/**
	 * 
	 * @description 调整异常等级
	 * @author falconTrotk
	 * @param exception
	 */
	private void adjustExceptionGrade(Exception exception) {
		// 当包装的是异常包的时候，异常包的异常等级不能小于内部异常包的异常等级
		if (ExceptionPack.class.isInstance(exception)) {
			ExceptionGradeEnum exceptionGradeEnum = ((ExceptionPack) exception).getExceptionGradeEnum();
			if (exceptionGradeEnum.getLEVEL() > this.exceptionGradeEnum.getLEVEL()) {
				this.exceptionGradeEnum = exceptionGradeEnum;
			}
		}
	}

	public Exception getException() {
		return exception;
	}

	public ExceptionGradeEnum getExceptionGradeEnum() {
		return exceptionGradeEnum;
	}

	public boolean isWhetherPopUp() {
		return whetherPopUp;
	}
}
