package org.aerie.forest.core.factory;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.processer.exception.ExceptionProcessor;
import org.aerie.forest.core.element.rebar.tool.file.FileTool;

/**
 * 
 * @description forest架构元素初始化货架【封装好的货架】
 * @author falconTrotk
 * @company aerie
 * @date 2019年10月9日上午11:38:55
 * @version 1.0.1
 */
public final class ForestRebarInitShelf extends ForestShelf {
	/**
	 * 单例
	 */
	private static ForestRebarInitShelf forestRebarInitShelf;

	/**
	 * 单例标识符
	 */
	private boolean singletonFlag = false;

	private ForestRebarInitShelf() {
		synchronized (ForestRebarInitShelf.class) {
			if (singletonFlag == true) {
				throw new RuntimeException("forest架构元素出厂货架受到反射攻击");
			}
			singletonFlag = true;
			GlobalLogger.INSTANCE.getLOGGER().info("【单例】forest架构元素出厂货架初始化");
		}
	}

	/**
	 * 
	 * @description 获得单例
	 * @return
	 * @throws Exception
	 */
	public static ForestRebarInitShelf getInstance() {
		/**
		 * 双重锁
		 */
		if (forestRebarInitShelf == null) {
			synchronized (ForestRebarInitShelf.class) {
				if (forestRebarInitShelf == null) {
					forestRebarInitShelf = new ForestRebarInitShelf();
				}
			}
		}
		return forestRebarInitShelf;
	}

	// ===>===>===>启动器
	
	
	// ===>===>===>处理器
	/**
	 * 分层异常处理器
	 */
	public final RebarScutcheon<ExceptionProcessor> EXCRPTION_PROCESSOR = new RebarScutcheon<ExceptionProcessor>(
			"异常处理器", ForestRebarContainerCategory.PROCESSER) {

	};
	// ===>===>===>工具
	/**
	 * File工具
	 */
	public final RebarScutcheon<FileTool> FILE_TOOL = new RebarScutcheon<FileTool>("File工具",
			ForestRebarContainerCategory.TOOL) {
	};
}
