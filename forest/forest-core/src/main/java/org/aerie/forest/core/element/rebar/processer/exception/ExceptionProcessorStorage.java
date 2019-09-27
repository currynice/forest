package org.aerie.forest.core.element.rebar.processer.exception;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.bootable.timecrystal.TimeCrystalStorage;
import org.aerie.forest.core.element.rebar.processer.ForestProcessorStorage;

public final class ExceptionProcessorStorage extends ForestProcessorStorage<ExceptionProcessor> {

	/**
	 * 单例
	 */
	private static ExceptionProcessorStorage exceptionProcessorStorage;

	private ExceptionProcessorStorage() {
		synchronized (TimeCrystalStorage.class) {
			if (singletonFlag == true) {
				throw new RuntimeException("受到反射攻击");
			}
			singletonFlag = true;
			GlobalLogger.INSTANCE.getLOGGER().info("【单例】时间晶振入库组件初始化");
		}
	}

	/**
	 * 
	 * @description 获得单例
	 * @return
	 * @throws Exception
	 */
	public final static ExceptionProcessorStorage getInstance() {
		/**
		 * 双重锁
		 */
		if (exceptionProcessorStorage == null) {
			synchronized (TimeCrystalStorage.class) {
				if (exceptionProcessorStorage == null) {
					exceptionProcessorStorage = new ExceptionProcessorStorage();
				}
			}
		}
		return exceptionProcessorStorage;
	}

	@Override
	protected ExceptionProcessor getForestRebar() {

		return ExceptionProcessor.getInstance();
	}

}