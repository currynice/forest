package org.aerie.forest.core.element.rebar.singleton.processer.exception;

import java.util.Optional;

import org.aerie.forest.core.element.brick.exception.CustomException;
import org.aerie.forest.core.element.brick.exception.ExceptionGradeEnum;
import org.aerie.forest.core.element.brick.exception.ExceptionPack;
import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.singleton.processer.ForestProcessor;
import org.aerie.forest.core.element.rebar.tool.ThrowableTool;

/**
 * 
 * @description 全局异常包装处理器
 * @author falconTrotk
 * @company aerie
 * @date 2019年8月21日上午10:42:28
 * @version 1.0.1
 */
public final class ExceptionProcessor extends ForestProcessor {

	/**
	 * 单例
	 */
	private static ExceptionProcessor exceptionProcessor;

	/**
	 * 默认异常等级
	 */
	private ExceptionGradeEnum defaultExceptionGrade = null;
//			ConfigProcessor.INSTANCE.getForestConfig().getExceptionConfig()
//			.getDefaultExceptionGrade();

	/**
	 * 
	 * @description 封装私有化构造器【防止反射攻击】
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年9月12日上午10:03:19
	 * @version 1.0.1
	 * @throws Exception
	 */
	private ExceptionProcessor() {
		synchronized (ExceptionProcessor.class) {
			if (singletonFlag == true) {
				throw new RuntimeException(forestRebarName + "受到反射攻击");
			}
			singletonFlag = true;
			GlobalLogger.INSTANCE.getLOGGER().info("【单例】" + forestRebarName + "初始化");
		}
		GlobalLogger.INSTANCE.getLOGGER().debug("全局异常包装处理器初始化");
	}

	/**
	 * 
	 * @description 获得单例
	 * @return
	 * @throws Exception
	 */
	static ExceptionProcessor getInstance() {
		if (exceptionProcessor == null) {
			synchronized (ExceptionProcessor.class) {
				if (exceptionProcessor == null) {
					exceptionProcessor = new ExceptionProcessor();
				}
			}
		}
		return exceptionProcessor;
	}

	/**
	 * 
	 * @description 处理 异常
	 * @author falconTrotk
	 * @param message
	 * @param exception
	 */
	public void recordException(String message, Exception exception) {
		recordExceptionPack(message, exception, null);
	}

	/**
	 * 
	 * @description 处理异常
	 * @author falconTrotk
	 * @param message
	 * @param exception
	 * @param exceptionGradeEnum
	 */
	public void recordException(String message, Exception exception, ExceptionGradeEnum exceptionGradeEnum) {
		// 执行异常处理动作
		disposeFunction(null, recordExceptionPack(message, exception, exceptionGradeEnum));
	}

	/**
	 * 
	 * @description 处理异常
	 * @param message
	 * @param throwable
	 * @param exceptionDisposeFunction
	 */
	public void recordException(String message, Exception exception,
			ExceptionDisposeFunction exceptionDisposeFunction) {
		// 执行异常处理动作
		disposeFunction(exceptionDisposeFunction, recordExceptionPack(message, exception, null));
	}

	/**
	 * 
	 * @description 处理异常信息包【直接异常--间接异常--根本异常】
	 * @param message
	 * @param throwable
	 * @param exceptionDisposeFunction
	 */
	public void recordException(String message, Exception exception, ExceptionGradeEnum exceptionGradeEnum,
			ExceptionDisposeFunction exceptionDisposeFunction) {
		// 执行异常处理动作
		disposeFunction(exceptionDisposeFunction, recordExceptionPack(message, exception, exceptionGradeEnum));
	}

	/**
	 * 递归获得根本异常
	 * 
	 * @param exceptionPack
	 * @return
	 * @throws CustomException
	 * @throws ExceptionPack
	 */
	public Exception getPrimaryException(Exception exception) throws ExceptionPack {
		Optional.ofNullable(exception).orElseThrow(
				() -> new ExceptionPack("无法获得根本异常", new CustomException("异常包装内部信息丢失-->无法处理"), ExceptionGradeEnum.WARN));
		if (!ExceptionPack.class.isInstance(exception)) {
			return exception;
		}
		Exception exceptionPacked = Optional.ofNullable(((ExceptionPack) exception).getException()).orElseThrow(
				() -> new ExceptionPack("无法获得根本异常", new CustomException("异常包装内部信息丢失-->无法处理"), ExceptionGradeEnum.WARN));
		// 递归处理
		return getPrimaryException(exceptionPacked);
	}

	/**
	 * 
	 * @description 执行异常处理动作
	 * @author falconTrotk
	 * @param exceptionDisposeFunction
	 */
	private void disposeFunction(ExceptionDisposeFunction exceptionDisposeFunction,
			ExceptionGradeEnum exceptionGradeEnum) {
		// 根据配置fatal异常执行退出程序
		if (exceptionGradeEnum == ExceptionGradeEnum.FATAL
//				&& ConfigProcessor.INSTANCE.getForestConfig().getExceptionConfig().isFatalExit()
		)
			exceptionDisposeFunction = new ExceptionDisposeFunction() {

				@Override
				public void action() {
					GlobalLogger.INSTANCE.getLOGGER().error(GlobalLogger.INSTANCE.getMARKER_FATAL(), "系统崩溃退出");
					// 系统退出
					System.exit(0);
				}
			};
		if (null == exceptionDisposeFunction) {
			GlobalLogger.INSTANCE.getLOGGER().debug("没有需要处理的异常处理动作");
			return;
		}
		try {
			exceptionDisposeFunction.action();
		} catch (Exception e) {
			GlobalLogger.INSTANCE.getLOGGER().error("异常处理动作错误");
		}
	}

	/**
	 * 
	 * @description 处理异常包
	 * @author falconTrotk
	 * @param message
	 * @param exception
	 * @param exceptionGradeEnum
	 */
	private ExceptionGradeEnum recordExceptionPack(String message, Exception exception,
			ExceptionGradeEnum exceptionGradeEnum) {
		System.err.println("\n解析异常：— — — — — — — — — — — — — — — — — — — — — — — — — — — — — —"
				+ " — — — — — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —"
				+ "— — — — — — — — ############");
		// 自处理空异常包direct
		if (null == exception) {
			ExceptionStamper.INSTANCE.printException(ExceptionLogger.EXCEPTION_LOGGER, "禁止处理空异常",
					ExceptionGradeEnum.ERROR);
			ExceptionStamper.INSTANCE.printException(ExceptionLogger.EXC_STACK_TRACE_LOGGER,
					"lead to------>无法判断此处异常的等级,请尽快处理", ExceptionGradeEnum.ERROR);
			try {
				ExceptionStamper.INSTANCE.printException(ExceptionLogger.EXC_STACK_TRACE_LOGGER,
						ThrowableTool.INSTANCE.getStackTraceFormatting(new CustomException("无意义的"), 2),
						ExceptionGradeEnum.ERROR);
			} catch (ExceptionPack e) {
				ExceptionStamper.INSTANCE.printException(ExceptionLogger.EXCEPTION_LOGGER, "处理异常失败", e,
						ExceptionGradeEnum.ERROR);
			}
			return null;
		}
		exceptionGradeEnum = Optional.ofNullable(exceptionGradeEnum).orElse(defaultExceptionGrade);
		// 处理直接异常的异常等级
		ExceptionGradeEnum exceptionGradeDirect = exceptionGradeEnum;
		if (exception instanceof ExceptionPack) {
			ExceptionGradeEnum exceptionGrade = ((ExceptionPack) exception).getExceptionGradeEnum();
			if (exceptionGrade.getLEVEL() > exceptionGradeEnum.getLEVEL()) {
				exceptionGradeDirect = exceptionGrade;
			}
		}
		try {
			// 处理直接异常包
			ExceptionStamper.INSTANCE.printException(ExceptionLogger.EXC_STACK_TRACE_LOGGER,
					"------------------------------------------------------------>[direct   cause]",
					exceptionGradeDirect);
			// 打印直接异常信息
			ExceptionStamper.INSTANCE.printException(ExceptionLogger.EXCEPTION_LOGGER, message, exceptionGradeDirect);
			// 打印直接异常的产生的位置
			ExceptionStamper.INSTANCE.printException(ExceptionLogger.EXC_STACK_TRACE_LOGGER,
					ThrowableTool.INSTANCE.getStackTraceBeginLine(new CustomException("无意义的"), 3),
					exceptionGradeDirect);
			// 处理的是异常包的情况
			Exception PrimaryException = exception;
			// 处理的是异常的情况
			if (exception instanceof ExceptionPack) {
				ExceptionPack exceptionPack = (ExceptionPack) exception;
				// 弹窗处理【现在只对直接异常做弹窗处理】
				disposeCustomException(exceptionPack);
				// 处理间接异常 ==========>
				recordIndirectException(exceptionPack);
				PrimaryException = getPrimaryException(exceptionPack);
			}
			// 打印根本异常信息 ==========>
			ExceptionStamper.INSTANCE.printException(ExceptionLogger.EXC_STACK_TRACE_LOGGER,
					"be caused by:\n\t---------------------------------------------------->[primary  cause]",
					PrimaryException, ExceptionGradeEnum.WARN);
		} catch (ExceptionPack e) {
			this.recordException("处理异常失败", e, ExceptionGradeEnum.ERROR);
		}
		System.err.println(
				"***************************《 *************************** 《《《《《《 * * * * * * 》》》》》》***************************** 》************************* &&&&&&&&&&&&\n");
		return exceptionGradeDirect;
	}

	/**
	 * 处理间接异常
	 * 
	 * @param exceptionPack
	 * @param index
	 * @return
	 * @throws CustomException
	 * @throws ExceptionPack
	 */
	private void recordIndirectException(ExceptionPack exceptionPack) throws ExceptionPack {
		Optional.ofNullable(exceptionPack).orElseThrow(
				() -> new ExceptionPack("处理间接异常", new CustomException("异常包装内部信息丢失-->无法处理"), ExceptionGradeEnum.WARN));
		ExceptionStamper.INSTANCE.printException(ExceptionLogger.EXC_STACK_TRACE_LOGGER,
				"be caused by:\n\t---------------------------------------------------->[indirect cause]",
				exceptionPack.getExceptionGradeEnum());
		// 打印间接异常信息
		ExceptionStamper.INSTANCE.printException(ExceptionLogger.EXCEPTION_LOGGER, exceptionPack.getMessage(),
				exceptionPack.getExceptionGradeEnum());
		// 打印间接异常的产生的位置【存在没有调用出处的异常处理，则打印的位置就是异常生成处】
		ExceptionStamper.INSTANCE.printException(ExceptionLogger.EXC_STACK_TRACE_LOGGER,
				Optional.ofNullable(ThrowableTool.INSTANCE.getStackTraceFormatting(exceptionPack, 0))
						.filter(p1 -> !p1.isEmpty())
						.orElse(ThrowableTool.INSTANCE.getStackTraceFormatting(exceptionPack, 1)),
				exceptionPack.getExceptionGradeEnum());
		if (exceptionPack.getException() instanceof ExceptionPack) {
			recordIndirectException((ExceptionPack) exceptionPack.getException());
		}
	}

	/**
	 * 
	 * @description 异常包装的弹窗提示处理
	 * @param exceptionPack
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年2月10日下午10:03:32
	 */
	private void disposeCustomException(ExceptionPack exceptionPack) {
		// 如果异常包为空或者异常包的是否弹窗标识符是false不处理直接返回
		if (exceptionPack == null || exceptionPack.isWhetherPopUp() == false) {
			return;
		}
		// TODO 这里的弹窗对现在的服务器没实际的作用，考虑是否去掉
//		ExceptionGradeEnum exceptionGradeEnum = Optional.ofNullable(exceptionPack.getExceptionGradeEnum())
//				.orElse(defaultExceptionGrade);
//		switch (exceptionGradeEnum) {
//		case INFO:
//			PopUpWindows.info(exceptionPack.getMessage());
//			break;
//		case WARN:
//			PopUpWindows.warn(exceptionPack.getMessage());
//			break;
//		case ERROR:
//			PopUpWindows.error();
//			break;
//		}
	}
}
