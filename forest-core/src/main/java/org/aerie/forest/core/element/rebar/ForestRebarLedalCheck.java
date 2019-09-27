package org.aerie.forest.core.element.rebar;

/**
 * 
 * @description forest架构元素合法性校验器
 * @author falconTrotk
 * @company aerie
 * @date 2019年9月26日下午6:28:45
 * @version 1.0.1
 */
@FunctionalInterface
public interface ForestRebarLedalCheck {

	/**
	 * 
	 * @description 合法性校验
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年9月26日下午6:32:55
	 * @version 1.0.1
	 * @param forestRebar
	 */
	public void isLegal(ForestRebar forestRebar);
}
