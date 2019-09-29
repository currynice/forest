package org.aerie.forest.core.element.rebar;

import org.aerie.forest.core.factory.container.ForestRebarContainerCategory;
import org.aerie.forest.core.element.rebar.bootable.timecrystal.TimeCrystal;
import org.aerie.forest.core.element.rebar.bootable.timecrystal.TimeCrystalStorage;
import org.aerie.forest.core.element.rebar.bootable.uuidpool.UuidPool;
import org.aerie.forest.core.element.rebar.bootable.uuidpool.UuidPoolStorage;
import org.aerie.forest.core.element.rebar.tool.file.FileToolStorage;
import org.aerie.forest.core.element.rebar.tool.file.FileTool;
import org.aerie.forest.core.element.rebar.processer.exception.ExceptionProcessor;
import org.aerie.forest.core.element.rebar.processer.exception.ExceptionProcessorStorage;

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
	TIME_CRYSTAL("时间晶振", "org.aerie.forest.core.element.rebar.bootable.timecrystal.TimeCrystal",
			TimeCrystalStorage.getInstance(), ForestRebarContainerCategory.BOOTABLE),
	UUID_POOL("UUID池", "org.aerie.forest.core.element.rebar.bootable.uuidpool.UuidPool", UuidPoolStorage.getInstance(),
			ForestRebarContainerCategory.BOOTABLE),
	EXCRPTION_PROCESSOR("异常处理器", "org.aerie.forest.core.element.rebar.processer.exception.ExceptionProcessor",
			ExceptionProcessorStorage.getInstance(), ForestRebarContainerCategory.PROCESSER),
	FILE_TOOL("File工具", "org.aerie.forest.core.element.rebar.tool.file.FileTool", FileToolStorage.getInstance(),
			ForestRebarContainerCategory.TOOL);
	/**
	 * 类型的名称
	 */
	private String typeName;
	/**
	 * 架构元素类
	 */
	private String forestRebarClass;
	/**
	 * 架构元素入库组件
	 */
	private ForestRebarStorage<?> forestRebarStorage;
	/**
	 * 
	 * forest架构元素属于的容器
	 */
	private ForestRebarContainerCategory forestRebarContainerCategory;

	private ForestRebarCategory(String typeName, String forestRebarClass, ForestRebarStorage<?> forestRebarStorage,
			ForestRebarContainerCategory forestRebarContainerCategory) {
		this.typeName = typeName;
		this.forestRebarClass = forestRebarClass;
		this.forestRebarStorage = forestRebarStorage;
		this.forestRebarContainerCategory = forestRebarContainerCategory;
	}

	public String getTypeName() {
		return typeName;
	}

	@SuppressWarnings("unchecked")
	public Class<? extends ForestRebar> getForestRebarClass() {
		try {
			return (Class<? extends ForestRebar>) Class.forName(forestRebarClass);
		} catch (ClassNotFoundException e) {
			throw new Error("无法找到" + typeName + "的类");
		}
	}

	public ForestRebarStorage<?> getForestRebarStorage() {
		return forestRebarStorage;
	}

	public ForestRebarContainerCategory getForestRebarContainerCategory() {
		return forestRebarContainerCategory;
	}
}
