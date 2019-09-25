package org.aerie.forest.core.element.rebar.singleton.bootable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aerie.forest.core.element.brick.exception.CustomException;
import org.aerie.forest.core.element.brick.exception.ExceptionGradeEnum;
import org.aerie.forest.core.element.brick.exception.ExceptionPack;
import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.singleton.ForestSingletonRebar;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @description forest启动器
 * @author falconTrotk
 * @company aerie
 * @date 2019年8月21日上午9:35:36
 * @version 1.0.1
 */
public abstract class ForestBootable extends ForestSingletonRebar implements BootProcessor {

	/**
	 * 
	 * @description 启停组件
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年8月21日上午9:37:14
	 * @version 1.0.1
	 */
	protected class BootModule implements Runnable {
		/**
		 * 
		 * @description 启动组件的异常信息
		 * @author falconTrotk
		 * @company aerie
		 * @date 2019年8月22日下午1:44:30
		 * @version 1.0.1
		 */
		@Data
		@AllArgsConstructor
		protected class ExceptionInfo {
			/**
			 * 根本异常
			 */
			private Exception primaryException;
			/**
			 * 异常发生的时间
			 */
			private LocalDateTime exceptionTime;
		}

		/**
		 * 允许启动标识符
		 */
		private boolean allowBootFlag = true;
		/**
		 * 启动标识符
		 */
		private boolean bootFlag = false;
		/**
		 * 启动的时间
		 */
		private LocalDateTime bootTime;
		/**
		 * 异常标识符
		 */
		private boolean exceptionFlag = false;
		/**
		 * 异常信息集合
		 */
		private List<ExceptionInfo> exceptionInfos = new ArrayList<>();
		/**
		 * 启动器的核心线程
		 */
		private RunnableFunction runableFunction;
		/**
		 * 关闭触发器
		 */
		private boolean closeFlag = false;

		protected BootModule(boolean allowBootFlag, RunnableFunction runableFunction) {
			super();
			this.allowBootFlag = Optional.ofNullable(allowBootFlag).orElse(false);
			this.runableFunction = Optional.ofNullable(runableFunction).orElse(new RunnableFunction() {

				@Override
				public void action() throws Exception {
					GlobalLogger.INSTANCE.getLOGGER().error("启动器的启动线程不合法");
				}
			});
		}

		/**
		 * 
		 * @description 记录异常
		 * @author falconTrotk
		 * @param exception
		 */
		private void recordException(Exception exception) {
//			try {
//				// 写入根本异常和异常时间
//				bootModule.exceptionInfos.add(new ExceptionInfo(
//						ExceptionProcessor.INSTANCE.getPrimaryException(exception), LocalDateTime.now()));
//				// 修改异常标识符
//				bootModule.exceptionFlag = true;
//			} catch (ExceptionPack e) {
//				ExceptionProcessor.INSTANCE.recordException("无法获得异常信息的根本异常", e, ExceptionGradeEnum.WARN);
//			}
		}

		@Override
		public void run() {
			try {
				while (!closeFlag) {
					runableFunction.action();
				}
			} catch (Exception e) {
				disposeException(e);
			}

			// 修改启动标识符
			bootModule.bootFlag = false;
			// 重置关闭触发器
			closeFlag = false;
			GlobalLogger.INSTANCE.getLOGGER().debug("时间晶振关闭");
		}
	}

	/**
	 * 启动组件
	 */
	protected BootModule bootModule;
	/**
	 * 启动器的线程
	 */
	protected Thread thread;
	/**
	 * forest架构元素的名称
	 */
	protected String forestRebarName = "未知元素";

	public ForestBootable(boolean allowBootFlag, String forestRebarName, RunnableFunction runnableFunction) {
		super();
		this.bootModule = new BootModule(allowBootFlag, runnableFunction);
		this.forestRebarName = forestRebarName;
	}

	@Override
	public String getForestRebarName() {
		return forestRebarName;
	}

	/**
	 * 
	 * @description 允许启动判断
	 * @author falconTrotk
	 * @return
	 */
	@Override
	public final boolean allowStart() {
		if ((null != bootModule && bootModule.allowBootFlag && !bootModule.bootFlag)) {
			GlobalLogger.INSTANCE.getLOGGER().debug(this.getForestRebarName() + "允许启动");
			return true;
		}
		GlobalLogger.INSTANCE.getLOGGER().debug(this.getForestRebarName() + "不允许启动");
		return false;
	}

	@Override
	public final synchronized void close() throws Exception {
		// 启动器启动了才可关闭
		if (bootModule.bootFlag == true) {
			// 关闭
			shutDown();
			return;
		}
		GlobalLogger.INSTANCE.getLOGGER().info("时间晶振没有启动无法关闭");
	}

	/**
	 * 
	 * @description 启动器启动前的处理
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月23日下午5:37:48
	 * @version 1.0.1
	 */
	protected abstract void beforeRun();

	/**
	 * 
	 * @description 启动器启动后的处理
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月23日下午5:38:00
	 * @version 1.0.1
	 */
	protected abstract void afterRun();

	/**
	 * 
	 * @description 运行
	 * @author falconTrotk
	 * @throws Exception
	 */
	@Override
	public final synchronized void run() throws Exception {
		try {
			// 校验是否允许启动【允许启动和自定义启动条件同时达到】
			if (!allowStart() || !customAllowStart())
				throw new CustomException("不符合启动条件，无法启动");
			// 执行启动前的自定义方法
			beforeRun();
			// 启动
			thread.start();
			// 修改启动标识
			bootModule.bootFlag = true;
			// 写入启动时间
			bootModule.bootTime = LocalDateTime.now();
			// 执行运行之后的自定义方法
			afterRun();
		} catch (Exception e) {
			// 记录异常
			bootModule.recordException(e);
			throw new ExceptionPack(this.getForestRebarName() + "无法运行", e, ExceptionGradeEnum.ERROR);
		}
	}

	/**
	 * 
	 * @description 启动器未设置自定义启动拦截
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月23日下午5:38:08
	 * @version 1.0.1
	 * @return
	 */
	protected abstract boolean customAllowStart();

	/**
	 * 
	 * @description 启动器的关闭逻辑
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月23日下午5:39:04
	 * @version 1.0.1
	 * @throws Exception
	 */
	protected void shutDown() throws Exception {
		bootModule.closeFlag = true;
	}

	@Override
	public final boolean hasStarted() {

		return bootModule.bootFlag;
	}

	@Override
	public final LocalDateTime getStartTime() {

		return bootModule.bootTime;
	}

	@Override
	public final boolean hasException() {

		return bootModule.exceptionFlag;
	}

	@Override
	public final Exception getLastPrimaryException() {

		return bootModule.exceptionInfos.get(bootModule.exceptionInfos.size() - 1).getPrimaryException();
	}

	@Override
	public final LocalDateTime getLastExceptionTime() {

		return bootModule.exceptionInfos.get(bootModule.exceptionInfos.size() - 1).getExceptionTime();
	}

	/**
	 * 
	 * @description 线程是否活跃
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月26日下午12:38:49
	 * @version 1.0.1
	 * @return
	 */
	@Override
	public final boolean isAlive() {
		return thread.isAlive();
	}

	/**
	 * 解析异常
	 */
	@Override
	public final void analyzeExceptions() {
		// TODO Auto-generated method stub
		// 解析异常没想好怎么处理
		GlobalLogger.INSTANCE.getLOGGER().info("待开发。。。。。。");
	}

	/**
	 * 处理异常
	 */
	protected abstract void disposeException(Exception exception);
}
