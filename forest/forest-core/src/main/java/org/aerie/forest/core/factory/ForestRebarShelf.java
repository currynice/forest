package org.aerie.forest.core.factory;

import org.aerie.forest.core.element.rebar.ForestRebar;
import org.aerie.forest.core.element.rebar.ForestRebarStorage;
import org.aerie.forest.core.genericity.ContainsGenericity;

/**
 * 
 * @description forest架构元素货架
 * @author falconTrotk
 * @company aerie
 * @date 2019年10月9日上午11:21:07
 * @version 1.0.1
 */
public abstract class ForestRebarShelf {
	/**
	 * 
	 * @description 类别【没有校验该泛型是不是存在于ForestRebarContainerCategory对应的容器里面】T的限定也没有处理
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年9月29日下午3:11:07
	 * @version 1.0.1
	 */
	protected abstract static class RebarScutcheon<T extends ForestRebar> extends ContainsGenericity<T> {
		/**
		 * 标牌名称
		 */
		private String scutcheonName;
		/**
		 * 架构元素类
		 */
		private Class<T> forestRebarClass;
		/**
		 * 架构元素入库组件
		 */
		private ForestRebarStorage<?> forestRebarStorage;
		/**
		 * forest架构元素属于的容器
		 */
		private ForestRebarContainerCategory forestRebarContainerCategory;

		public RebarScutcheon(String scutcheonName,
				org.aerie.forest.core.factory.ForestRebarContainerCategory forestRebarContainerCategory) {
			super();
			this.scutcheonName = scutcheonName;
			this.forestRebarContainerCategory = forestRebarContainerCategory;
		}

		public Class<T> getForestRebarClass() {
			forestRebarClass = (forestRebarClass == null) ? this.getGenericityZclass() : forestRebarClass;
			return forestRebarClass;
		}

		public ForestRebarStorage<?> getForestRebarStorage() {
			String StorageName = getForestRebarClass().getName() + "Storage";
			// TODO
			return forestRebarStorage;
		}

		public ForestRebarContainerCategory getForestRebarContainerCategory() {
			return forestRebarContainerCategory;
		}

		public RebarScutcheon<T> setForestRebarClass(Class<T> forestRebarClass) {
			this.forestRebarClass = forestRebarClass;
			return this;
		}

		public String getScutcheonName() {
			return scutcheonName;
		}

	}

	/**
	 * 
	 * @description forest架构元素货架合法性判断
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年10月9日下午5:05:05
	 * @version 1.0.1
	 * @throws Exception
	 */
	public final boolean isLegal() {
		return false;
	}
}
