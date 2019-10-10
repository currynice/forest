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
		 * forest架构元素属于的容器
		 */
		private ForestRebarContainerCategory forestRebarContainerCategory;

		public RebarScutcheon(String scutcheonName, ForestRebarContainerCategory forestRebarContainerCategory) {
			super();
			this.scutcheonName = scutcheonName;
			this.forestRebarContainerCategory = forestRebarContainerCategory;
		}

		public Class<T> getForestRebarClass() {
			forestRebarClass = (forestRebarClass == null) ? this.getGenericityZclass() : forestRebarClass;
			return forestRebarClass;
		}

		@SuppressWarnings("unchecked")
		public ForestRebarStorage<?> getForestRebarStorage() {
			String storageName = getForestRebarClass().getName() + "Storage";
			try {
				// 反射调用静态方法获取入库组件单例对象
				return (ForestRebarStorage<T>) Class.forName(storageName).getDeclaredMethod("getInstance").invoke(null);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("无法找到：【" + storageName + "】入库组件", e);
			} catch (NoSuchMethodException e) {
				throw new RuntimeException("无法找到：【" + storageName + "】入库组件的获取单例对象的方法", e);
			} catch (Exception e) {
				throw new RuntimeException("入库组件： 【" + storageName + "】的获取单例对象的方法不合法");
			}
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
