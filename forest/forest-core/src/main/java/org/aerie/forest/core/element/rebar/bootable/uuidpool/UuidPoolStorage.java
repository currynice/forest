package org.aerie.forest.core.element.rebar.bootable.uuidpool;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.ForestRebar;
import org.aerie.forest.core.element.rebar.bootable.ForestBootableStorage;

/**
 * 
 * @description UUID池入库组件
 * @author trotkFaclon
 * @company aerie
 * @version 1.0.1
 * @date 2019年9月15日下午6:39:15
 *
 */
public final class UuidPoolStorage extends ForestBootableStorage {

	/**
	 * 单例
	 */
	private static UuidPoolStorage uuidPoolStorage;

	@Override
	protected ForestRebar getForestRebar() {
		// TODO Auto-generated method stub
		return null;
	}

	private UuidPoolStorage() {
		synchronized (UuidPoolStorage.class) {
			if (singletonFlag == true) {
				throw new RuntimeException("受到反射攻击");
			}
			singletonFlag = true;
			GlobalLogger.INSTANCE.getLOGGER().info("【单例】UUID池入库组件初始化");
		}
	}

	/**
	 * 
	 * @description 获得单例
	 * @return
	 * @throws Exception
	 */
	public final static UuidPoolStorage getInstance() {
		/**
		 * 双重锁
		 */
		if (uuidPoolStorage == null) {
			synchronized (UuidPoolStorage.class) {
				if (uuidPoolStorage == null) {
					uuidPoolStorage = new UuidPoolStorage();
				}
			}
		}
		return uuidPoolStorage;
	}
}
