package org.aerie.forest.core.element.rebar.singleton;

import org.aerie.forest.core.element.rebar.ForestRebar;
import org.aerie.forest.core.factory.storage.ForestRebarStorage;

/**
 * 
 * @description forest单例架构元素入库组件
 * @author falconTrotk
 * @company aerie
 * @date 2019年9月26日上午7:29:29
 * @version 1.0.1
 */
public abstract class ForestSingletonRebarStorage<T extends ForestSingletonRebar> extends ForestRebarStorage<T> {
	
	@Override
	protected void isLegal(ForestRebar forestRebar) {
		
	}

}
