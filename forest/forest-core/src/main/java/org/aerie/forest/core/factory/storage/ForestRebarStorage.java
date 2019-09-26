package org.aerie.forest.core.factory.storage;

import org.aerie.forest.core.element.rebar.ForestRebar;
import org.aerie.forest.core.factory.container.ForestContainer;

/**
 * 
 * @description forest架构元素入库组件
 * @author trotkFaclon
 * @company aerie
 * @version 1.0.1
 * @date 2019年9月15日下午3:51:54
 *
 */
public abstract class ForestRebarStorage<T extends ForestRebar> {

	/**
	 * 单例标识符
	 */
	protected boolean singletonFlag = false;

	/**
	 * 
	 * @description 获得forest架构元素
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年9月23日下午1:37:58
	 * @version 1.0.1
	 */
	public final void putInStorage() throws Exception {
		T forestRebar = getForestRebar();
		isLegal(forestRebar);
		ForestContainer.INSTANCE.addForestRebar(forestRebar);
	}

	/**
	 * 
	 * @description 获得对应的forest架构元素
	 * @return
	 */
	protected abstract T getForestRebar();

	/**
	 * 
	 * @description 架构元素的规范是否合法
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年9月26日上午10:43:15
	 * @version 1.0.1
	 */
	protected abstract void isLegal(T forestRebar);

}