package org.aerie.forest.core.factory.container;

import java.util.ArrayList;
import java.util.List;

import org.aerie.forest.core.container.ForestElementContainer;
import org.aerie.forest.core.element.rebar.ForestRebar;

/**
 * 
 * @description forest架构元素容器
 * @author falconTrotk
 * @company aerie
 * @date 2019年9月23日下午3:04:52
 * @version 1.0.1
 */
class ForestRebarContainer<T extends ForestRebar> extends ForestElementContainer<T> {
	/**
	 * 容器
	 */
	private List<T> container = new ArrayList<>();

	List<T> getContainer() {
		return container;
	}

}
