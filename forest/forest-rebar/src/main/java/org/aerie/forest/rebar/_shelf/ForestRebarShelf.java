package org.aerie.forest.rebar._shelf;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.factory.ForestRebarContainerCategory;
import org.aerie.forest.core.factory.ForestShelf;
import org.aerie.forest.rebar.bootable.timecrystal.TimeCrystal;
import org.aerie.forest.rebar.bootable.uuidpool.UuidPool;
import org.aerie.forest.rebar.tool.file.FileTool;
import org.aerie.forest.rebar.tool.zclass.ClassTool;

/**
 * 
 * @description forest架构元素货架
 * @author falconTrotk
 * @company aerie
 * @date 2019年10月10日下午4:40:48
 * @version 1.0.1
 */
public final class ForestRebarShelf extends ForestShelf {
	/**
	 * 单例
	 */
	private static ForestRebarShelf forestRebarShelf;

	/**
	 * 单例标识符
	 */
	private boolean singletonFlag = false;

	private ForestRebarShelf() {
		synchronized (ForestRebarShelf.class) {
			if (singletonFlag == true) {
				throw new RuntimeException("forest架构元素出厂货架受到反射攻击");
			}
			singletonFlag = true;
			GlobalLogger.INSTANCE.getLOGGER().info("【单例】forest架构元素货架初始化");
		}
	}

	/**
	 * 
	 * @description 获得单例
	 * @return
	 * @throws Exception
	 */
	public static ForestRebarShelf getInstance() {
		/**
		 * 双重锁
		 */
		if (forestRebarShelf == null) {
			synchronized (ForestRebarShelf.class) {
				if (forestRebarShelf == null) {
					forestRebarShelf = new ForestRebarShelf();
				}
			}
		}
		return forestRebarShelf;
	}

	// ===>===>===>启动器
	/**
	 * 时间晶振类
	 */
	public final RebarScutcheon<TimeCrystal> TIME_CRYSTAL = new RebarScutcheon<TimeCrystal>("时间晶振",
			ForestRebarContainerCategory.BOOTABLE) {
	};
	/**
	 * UUID池类
	 */
	public final RebarScutcheon<UuidPool> UUID_POOL = new RebarScutcheon<UuidPool>("UUID池",
			ForestRebarContainerCategory.BOOTABLE) {

	};
	// ===>===>===>工具
	/**
	 * File工具
	 */
	public final RebarScutcheon<FileTool> FILE_TOOL = new RebarScutcheon<FileTool>("File工具",
			ForestRebarContainerCategory.TOOL) {
	};
	/**
	 * class工具
	 */
	public final RebarScutcheon<ClassTool> CLASS_TOOL = new RebarScutcheon<ClassTool>("Ficlass工具",
			ForestRebarContainerCategory.TOOL) {
	};
}
