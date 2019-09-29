package org.forest.framework;

import org.aerie.forest.core.element.rebar.ForestRebar;
import org.aerie.forest.core.element.rebar.ForestRebarCategory;
import org.aerie.forest.core.element.rebar.bootable.timecrystal.TimeCrystal;
import org.aerie.forest.core.element.rebar.bootable.uuidpool.UuidPool;
import org.aerie.forest.core.element.rebar.processer.exception.ExceptionProcessor;
import org.aerie.forest.core.element.rebar.tool.file.FileTool;

/**
 * 
 * @description forest架构元素出厂货架
 * @author trotkFaclon
 * @company aerie
 * @version 1.0.1
 * @date 2019年9月14日下午8:43:43
 *
 */
public class ForestRebarShelf {

	/**
	 * 
	 * @description 类别【没有校验该泛型是不是存在于ForestRebarContainerCategory对应的容器里面】T的限定也没有处理
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年9月29日下午3:11:07
	 * @version 1.0.1
	 */
	protected static class RebarType<T extends ForestRebar> {
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

	private ForestRebarShelf() {

	}

	/*
	 * 下面的枚举需要人为校验泛型和参数的类型是不是一致
	 */
	/////////////////////////////////////////////////// =========>>>>
	// ===>===>===>启动器
	/**
	 * 时间晶振类
	 */
	public final static RebarType<TimeCrystal> TIME_CRYSTAL = new RebarType<>(ForestRebarCategory.TIME_CRYSTAL);
	/**
	 * UUID池类
	 */
	public final static RebarType<UuidPool> UUID_POOL = new RebarType<>(ForestRebarCategory.UUID_POOL);
	// ===>===>===>处理器
	/**
	 * 分层异常处理器
	 */
	public final static RebarType<ExceptionProcessor> EXCRPTION_PROCESSOR = new RebarType<>(
			ForestRebarCategory.EXCRPTION_PROCESSOR);
	// ===>===>===>工具
	/**
	 * File工具
	 */
	public final static RebarType<FileTool> FILE_TOOL = new RebarType<>(ForestRebarCategory.FILE_TOOL);

//	/**
//	 * 单例
//	 */
//	private static ForestRebarShelf forestRebarShelf;
//	/**
//	 * 单例标识符
//	 */
//	private boolean singletonFlag = false;
//
//	private ForestRebarShelf() {
//		synchronized (ForestRebarShelf.class) {
//			if (singletonFlag == true) {
//				throw new RuntimeException("forest架构元素出厂货架受到反射攻击");
//			}
//			singletonFlag = true;
//			GlobalLogger.INSTANCE.getLOGGER().info("【单例】forest架构元素出厂货架初始化");
//		}
//	}
//
//	public final static ForestRebarShelf getInstance() {
//		/**
//		 * 双重锁
//		 */
//		if (forestRebarShelf == null) {
//			synchronized (ForestRebarShelf.class) {
//				if (forestRebarShelf == null) {
//					forestRebarShelf = new ForestRebarShelf();
//				}
//			}
//		}
//		return forestRebarShelf;
//	}

}