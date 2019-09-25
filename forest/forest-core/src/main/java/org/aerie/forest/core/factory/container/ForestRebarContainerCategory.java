package org.aerie.forest.core.factory.container;

import org.aerie.forest.core.element.rebar.ForestRebar;
import org.aerie.forest.core.element.rebar.bootable.ForestBootable;
import org.aerie.forest.core.factory.container.ForestRebarContainer;
import org.aerie.forest.core.element.rebar.processer.ForestProcessor;
import org.aerie.forest.core.element.rebar.tool.ForestTool;

/**
 * 
 * @description forest架构元素的容器分类（亦是forest架构元素容器）
 * @author falconTrotk
 * @company aerie
 * @date 2019年9月23日下午3:23:26
 * @version 1.0.1
 */
public enum ForestRebarContainerCategory {
	// 启动器
	BOOTABLE(new ForestRebarContainer<ForestBootable>() {
	}),
	// 处理器
	PROCESSER(new ForestRebarContainer<ForestProcessor>() {
	}),
	// 工具
	TOOL(new ForestRebarContainer<ForestTool>() {
	});
	/**
	 * forest架构元素容器
	 */
	private ForestRebarContainer<?> forestRebarContainer;

	private ForestRebarContainerCategory(ForestRebarContainer<?> forestRebarContainer) {
		this.forestRebarContainer = forestRebarContainer;
	}

	public Class<? extends ForestRebar> getZclass() {

		return forestRebarContainer.getGenericityZclass();
	}

	ForestRebarContainer<?> getForestRebarContainer() {
		return forestRebarContainer;
	}

}
