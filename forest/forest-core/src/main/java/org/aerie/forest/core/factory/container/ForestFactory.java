package org.aerie.forest.core.factory.container;

import org.aerie.forest.core.factory.container.ForestContainer;
import org.aerie.forest.core.factory.container.ForestRebarFactoryType.RebarType;

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
	public <T> T getForestRebarFactory(RebarType<T> rebarType) {
		/**
		 * 根据forest架构元素工厂的类别返回对应架构元素
		 */
		return (T) ForestContainer.INSTANCE.getForestRebar(rebarType.getForestRebarCategory());
	}
}
