package org.aerie.forest.framework;

import org.aerie.forest.core.element.rebar.ForestRebarCategory;
import org.aerie.forest.core.element.rebar.bootable.timecrystal.TimeCrystal;
import org.aerie.forest.core.element.rebar.bootable.uuidpool.UuidPool;

/**
 * 
 * @description forest架构元素工厂的类别
 * @author trotkFaclon
 * @company aerie
 * @version 1.0.1
 * @date 2019年9月14日下午8:43:43
 *
 */
final class ForestRebarFactoryType {
	/**
	 * 
	 * @description 类别【没有校验该泛型是不是存在于ForestRebarContainerCategory对应的容器里面】T的限定也没有处理
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年9月24日下午4:01:48
	 * @version 1.0.1
	 */
	public final static class RebarType<T> {
		/**
		 * 架构元素所在容器分类
		 */
		private ForestRebarCategory forestRebarCategory;

		public RebarType(ForestRebarCategory forestRebarCategory) {
			super();
			this.forestRebarCategory = forestRebarCategory;
		}

		public ForestRebarCategory getForestRebarCategory() {
			return forestRebarCategory;
		}

	}

	/*
	 * 下面的枚举需要人为校验泛型和参数的类型是不是一致
	 */
	/////////////////////////////////////////////////// =========>>>>
	/**
	 * 时间晶振类
	 */
	public final static RebarType<TimeCrystal> TIME_CRYSTAL = new RebarType<>(ForestRebarCategory.TIME_CRYSTAL);
	/**
	 * UUID池类
	 */
	public final static RebarType<UuidPool> UUID_POOL = new RebarType<>(ForestRebarCategory.UUID_POOL);

}