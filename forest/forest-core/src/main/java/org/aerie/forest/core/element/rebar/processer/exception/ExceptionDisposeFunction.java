package org.aerie.forest.core.element.rebar.processer.exception;

/**
 * 
 * @description 异常处理动作
 * @author falconTrotk
 * @company aerie
 * @date 2019年9月12日下午2:17:36
 * @version 1.0.1
 */
@FunctionalInterface
interface ExceptionDisposeFunction {
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
