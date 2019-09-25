package org.aerie.forest.core.element.rebar;

import org.aerie.forest.core.element.rebar.bootable.timecrystal.TimeCrystal;
import org.aerie.forest.core.element.rebar.bootable.uuidpool.UuidPool;
import org.aerie.forest.core.factory.container.ForestRebarContainerCategory;
import org.aerie.forest.core.factory.storage.ForestRebarStorage;
import org.aerie.forest.core.element.rebar.bootable.timecrystal.TimeCrystalStorage;
import org.aerie.forest.core.element.rebar.bootable.uuidpool.UuidPoolStorage;

/**
 * 
 * @description forest架构元素的明细分类
 * @author trotkFaclon
 * @company aerie
 * @version 1.0.1
 * @date 2019年9月16日下午3:00:34
 *
 */
public enum ForestRebarCategory {
	TIME_CRYSTAL("时间晶振", TimeCrystal.class, TimeCrystalStorage.getInstance(), ForestRebarContainerCategory.BOOTABLE),
	UUID_POOL("UUID池", UuidPool.class, UuidPoolStorage.getInstance(), ForestRebarContainerCategory.BOOTABLE);
	/**
	 * 类型的名称
	 */
	private String typeName;
	/**
	 * 架构元素类
	 */
	private Class<? extends ForestRebar> forestRebarClass;
	/**
	 * 架构元素入库组件
	 */
	private ForestRebarStorage forestRebarStorage;
	/**
	 * forest架构元素属于的容器
	 */
	private ForestRebarContainerCategory forestRebarContainerCategory;

	private ForestRebarCategory(String typeName, Class<? extends ForestRebar> forestRebarClass,
			ForestRebarStorage forestRebarStorage, ForestRebarContainerCategory forestRebarContainerCategory) {
		this.typeName = typeName;
		this.forestRebarClass = forestRebarClass;
		this.forestRebarStorage = forestRebarStorage;
		this.forestRebarContainerCategory = forestRebarContainerCategory;
	}

	public String getTypeName() {
		return typeName;
	}

	public Class<? extends ForestRebar> getForestRebarClass() {
		return forestRebarClass;
	}

	public ForestRebarStorage getForestRebarStorage() {
		return forestRebarStorage;
	}

	public ForestRebarContainerCategory getForestRebarContainerCategory() {
		return forestRebarContainerCategory;
	}

	/**
	 * 
	 * @description 执行入库组件
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年9月25日下午4:19:10
	 * @version 1.0.1
	 */
	public void executeStorage() {
		try {
			forestRebarStorage.putInStorage();
		} catch (Exception e) {
			throw new Error(typeName + "执行入库组件失败\ncaused by:" + e.getMessage());
		}
	}
}
