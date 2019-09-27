package org.aerie.forest.core.element.rebar.tool.file;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.ForestRebarStorage;
import org.aerie.forest.core.element.rebar.bootable.timecrystal.TimeCrystalStorage;

public class FileToolStorage extends ForestRebarStorage<FileTool> {

	/**
	 * 单例
	 */
	private static FileToolStorage fileToolStorage;

	private FileToolStorage() {
		synchronized (FileToolStorage.class) {
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
	public final static FileToolStorage getInstance() {
		/**
		 * 双重锁
		 */
		if (fileToolStorage == null) {
			synchronized (TimeCrystalStorage.class) {
				if (fileToolStorage == null) {
					fileToolStorage = new FileToolStorage();
				}
			}
		}
		return fileToolStorage;
	}

	@Override
	public FileTool getForestRebar() {

		return FileTool.getInstance();
	}

}
