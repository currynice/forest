package org.aerie.forest.core.element.rebar.bootable;

import java.time.LocalDateTime;

/**
 * 
 * @description 启动器
 * @author falconTrotk
 * @company aerie
 * @date 2019年8月21日下午4:59:59
 * @version 1.0.1
 */
public interface BootProcessor {
	/**
	 * 
	 * @description
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年9月12日下午2:08:01
	 * @version 1.0.1
	 */
	@FunctionalInterface
	interface RunnableFunction {
		/**
		 * 
		 * @description 执行
		 * @author falconTrotk
		 * @company aerie
		 * @date 2019年9月12日下午1:31:31
		 * @version 1.0.1
		 * @throws Exception
		 */
		public void action() throws Exception;
	}

	/**
	 * 
	 * @description 允许启动判断
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月23日下午5:24:19
	 * @version 1.0.1
	 * @return
	 */
	boolean allowStart();

	/**
	 * 
	 * @description 开始运行
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月23日下午5:24:04
	 * @version 1.0.1
	 * @throws Exception
	 */
	void run() throws Exception;

	/**
	 * 
	 * @description 停止运行
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月23日下午5:23:56
	 * @version 1.0.1
	 */
	void close() throws Exception;

	/**
	 * 
	 * @description
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月25日下午9:45:40
	 * @version 1.0.1
	 * @return
	 */
	boolean hasStarted();

	/**
	 * 
	 * @description 获得启动的时间
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月25日下午10:06:49
	 * @version 1.0.1
	 * @return
	 */
	LocalDateTime getStartTime();

	/**
	 * 
	 * @description 是否运行时存在异常
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月25日下午10:07:46
	 * @version 1.0.1
	 * @return
	 */
	boolean hasException();

	/**
	 * 
	 * @description 获得最后一个根本异常
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月25日下午10:08:35
	 * @version 1.0.1
	 * @return
	 */
	Exception getLastPrimaryException();

	/**
	 * 
	 * @description 获得异常产生的时间
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月25日下午10:09:33
	 * @version 1.0.1
	 * @return
	 */
	LocalDateTime getLastExceptionTime();

	/**
	 * 
	 * @description 分析异常集合
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月26日上午6:56:54
	 * @version 1.0.1
	 */
	void analyzeExceptions();

	/**
	 * 
	 * @description 启动的线程是否活跃
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月26日下午12:39:49
	 * @version 1.0.1
	 * @return
	 */
	boolean isAlive();
}
