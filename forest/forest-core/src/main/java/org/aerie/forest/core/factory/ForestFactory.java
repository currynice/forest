package org.aerie.forest.core.factory;

import org.aerie.forest.core.element.rebar.ForestRebar;
import org.aerie.forest.core.factory.ForestRebarShelf.RebarScutcheon;

/**
 * 
 * @description forest架构元素工厂
 * @author trotkFaclon
 * @company aerie
 * @version 1.0.1
 * @return
 * @date 2019年9月14日下午8:29:35
 *
 */
public enum ForestFactory {
	INSTANCE;

	private ForestFactory() {
		
	}

	/**
	 * 
	 * @description 获得架构元素【参数从ForestRebarFactoryType（枚举类）中获取】
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年9月24日下午4:03:54
	 * @version 1.0.1
	 * @param <T>
	 * @param rebarType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends ForestRebar> T getForestRebarFactory(RebarScutcheon<T> rebarScutcheon) {
		/**
		 * 根据forest架构元素工厂的类别返回对应架构元素
		 */
		return (T) ForestContainer.INSTANCE.getForestRebar(rebarScutcheon);
	}

}
