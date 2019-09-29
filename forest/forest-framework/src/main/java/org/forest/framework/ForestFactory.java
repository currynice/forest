package org.forest.framework;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.ForestRebar;
import org.aerie.forest.core.element.rebar.bootable.timecrystal.TimeCrystal;
import org.aerie.forest.core.factory.container.ForestContainer;
import org.forest.framework.ForestRebarShelf.RebarType;

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
public final class ForestFactory<E extends ForestRebarShelf> {
	/**
	 * 单例
	 */
	private ForestFactory<?> forestFactory;
	/**
	 * forest架构元素出厂货架
	 */
	private E e;
	/**
	 * 
	 */
	private boolean singletonFlag;

	private ForestFactory() {
		synchronized (TimeCrystal.class) {
			if (singletonFlag == true) {
				throw new RuntimeException("forest架构元素工厂受到反射攻击");
			}
			singletonFlag = true;
			GlobalLogger.INSTANCE.getLOGGER().info("【单例】forest架构元素工厂初始化");
		}
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
	public <T extends ForestRebar> T getForestRebarFactory(RebarType<T> rebarType) {
		/**
		 * 根据forest架构元素工厂的类别返回对应架构元素
		 */
		return (T) ForestContainer.INSTANCE.getForestRebar(rebarType.getForestRebarCategory());
	}

	@SuppressWarnings("static-access")
	public void ss() {
		getForestRebarFactory(e.EXCRPTION_PROCESSOR);
	}
}
