package org.aerie.forest.core.element.rebar.unsingleton.tool;

import org.aerie.forest.core.element.brick.exception.CustomException;
import org.aerie.forest.core.element.brick.exception.ExceptionGradeEnum;
import org.aerie.forest.core.element.brick.exception.ExceptionPack;

/**
 * 
 * @description 可抛物工具类
 * @author falconTrotk
 * @company aerie
 * @date 2019年2月10日下午10:32:46
 * @version 1.0.1
 */
public enum ThrowableTool {
	INSTANCE;
	/**
	 * 
	 * @description 根据下表获得异常的堆栈信息
	 * @param throwable
	 * @param index
	 * @return
	 * @throws Exception
	 * @author falconTrotk
	 * @throws ExceptionPack
	 * @company aerie
	 * @date 2019年2月10日下午10:05:16
	 */
	public String getStackTraceFormatting(Throwable throwable) throws ExceptionPack {
		return getStackTraceFormatting(throwable, -1);
	}

	/**
	 * 获得异常堆的指定行的堆栈信息
	 * 
	 * @param throwable
	 * @param line
	 * @return
	 * @throws ExceptionPack
	 */
	public String getStackTraceFormatting(Throwable throwable, int line) throws ExceptionPack {
		if (null == throwable)
			return null;
		StringBuffer result = new StringBuffer();
		try {
			int index = 0;
			StackTraceElement[] stackTraceElements = throwable.getStackTrace();
			if (line > stackTraceElements.length || line < -1) {
				throw new CustomException("获取堆栈异常信息超出范围");
			}
			for (StackTraceElement stackTraceElement : stackTraceElements) {
				if (line == -1) {
					result.append("\tat ");
					result.append(stackTraceElement);
					if (index < stackTraceElements.length) {
						result.append("\n");
					}
				} else if (index++ == line) {
					result.append("\tat ");
					result.append(stackTraceElement);
					break;
				}
			}
		} catch (Exception e) {
			throw new ExceptionPack("获取异常堆栈信息失败", e, ExceptionGradeEnum.WARN, false);
		}
		return result.toString();
	}

	/**
	 * 
	 * @description 根据下表获得异常的堆栈信息
	 * @param throwable
	 * @param index
	 * @return
	 * @throws Exception
	 * @author falconTrotk
	 * @throws ExceptionPack
	 * @company aerie
	 * @date 2019年2月10日下午10:05:16
	 */
	public String getStackTraceByIndex(Throwable throwable, int index) throws ExceptionPack {
		if (null == throwable)
			return null;
		String result = null;
		try {
			String.valueOf(throwable.getStackTrace()[index]);
		} catch (Exception e) {
			throw new ExceptionPack("获取异常的第" + index + "条堆栈信息失败", e, ExceptionGradeEnum.WARN, false);
		}
		return result;
	}

	/**
	 * 获取line行后面的堆栈信息【自己new的异常】【共异常包处理器使用】
	 * 
	 * @param throwable
	 * @param line
	 * @return
	 * @throws ExceptionPack
	 */
	public String getStackTraceBeginLine(Throwable throwable, int line) throws ExceptionPack {
		// 为了只打印程序员自己写的类，
		// 处理的ExceptionPack是程序员自己new的，通过堆栈信息获得异常的包名
		// 之后不属于本包的异常只打印一条供参考
		// 要想查看异常的完整堆栈信息，可以从根本异常的堆栈信息去查看
		String packageName = null;
		if (null == throwable)
			return null;
		StringBuffer result = new StringBuffer();
		StackTraceElement[] stackTrace = throwable.getStackTrace();
		try {
			if (line > stackTrace.length || line < 0) {
				throw new CustomException("获取堆栈异常信息超出范围");
			}
			int index = 0;
			for (StackTraceElement stackTraceElement : stackTrace) {
				if (index++ >= line - 1) {
					if (packageName == null) {
						String stackTraceElementOne = String.valueOf(stackTraceElement);
						// 获取项目的包名【需要程序员严格按照包名规范去建包】
						packageName = stackTraceElementOne.substring(0,
								StringTool.INSTANCE.getCharacterPosition(stackTraceElementOne, "\\.", 2) - 1);
					}
					result.append("\tat ");
					result.append(stackTraceElement);
					// 不是本包的异常只打印一条供参考
					if (packageName != null && !String.valueOf(stackTraceElement).startsWith(packageName)) {
						break;
					}
					if (index < stackTrace.length) {
						result.append("\n");
					}
				}
			}
		} catch (Exception e) {
			throw new ExceptionPack("获取异常堆栈信息失败", e, ExceptionGradeEnum.WARN, false);
		}
		return result.toString();
	}
}