package org.aerie.forest.core.element.rebar;

import org.aerie.forest.core.factory.ForestContainer;

/**
 * 
 * @description forest架构元素入库组件
 * @author trotkFaclon
 * @company aerie
 * @version 1.0.1
 * @date 2019年9月15日下午3:51:54
 *
 */
public abstract class ForestRebarStorage {

	/**
	 * 单例标识符
	 */
	protected boolean singletonFlag = false;

	/**
	 * 
	 * @description forest架构元素入库
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年9月26日下午2:54:51
	 * @version 1.0.1
	 * @throws Exception
	 */
	public void putInStorage() throws Exception {
		ForestRebar forestRebar = getForestRebar();
		forestRebar.addLedalCheck();
		forestRebar.isLegal(forestRebar);
		ForestContainer.INSTANCE.addForestRebar(forestRebar);
	}

	/**
	 * 
	 * @description 获得对应的forest架构元素
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年9月26日下午2:54:58
	 * @version 1.0.1
	 * @return
	 */
	protected abstract ForestRebar getForestRebar();
}