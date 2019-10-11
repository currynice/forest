package org.aerie.forest.rebar.tool.file;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.ForestRebarStorage;

/**
 * 
 * @description File工具入库组件
 * @author falconTrotk
 * @company aerie
 * @date 2019年9月27日下午2:30:27
 * @version 1.0.1
 */
public class FileToolStorage extends ForestRebarStorage {

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
			GlobalLogger.INSTANCE.getLOGGER().info("【单例】File工具入库初始化");
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
			synchronized (FileToolStorage.class) {
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