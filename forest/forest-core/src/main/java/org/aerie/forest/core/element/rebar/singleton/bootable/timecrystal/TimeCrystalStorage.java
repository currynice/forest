package org.aerie.forest.core.element.rebar.singleton.bootable.timecrystal;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.ForestRebar;
import org.aerie.forest.core.element.rebar.singleton.bootable.ForestBootableStorage;

/**
 * 
 * @description 时间晶振入库组件
 * @author trotkFaclon
 * @company aerie
 * @version 1.0.1
 * @date 2019年9月15日下午4:12:47s
 *
 */
public final class TimeCrystalStorage extends ForestBootableStorage {

	/**
	 * 单例
	 */
	private static TimeCrystalStorage timeCrystalStorage;

	@Override
	public ForestRebar getForestRebar() {
		return TimeCrystal.getInstance();
	}

	private TimeCrystalStorage() {
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
	public final static TimeCrystalStorage getInstance() {
		/**
		 * 双重锁
		 */
		if (timeCrystalStorage == null) {
			synchronized (TimeCrystalStorage.class) {
				if (timeCrystalStorage == null) {
					timeCrystalStorage = new TimeCrystalStorage();
				}
			}
		}
		return timeCrystalStorage;
	}

}