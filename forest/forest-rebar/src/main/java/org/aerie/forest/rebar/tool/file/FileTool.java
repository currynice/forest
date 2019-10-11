package org.aerie.forest.rebar.tool.file;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;

import org.aerie.forest.core.element.brick.exception.CustomException;
import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.entity.tool.ForestTool;
import org.yaml.snakeyaml.Yaml;

/**
 * 
 * @description 文件的工具类
 * @author falconTrotk
 * @company aerie
 * @date 2019年7月31日下午5:21:02
 * @version 1.0.1
 */
public final class FileTool extends ForestTool {

	/**
	 * 单例
	 */
	private static FileTool fileTool;

	private FileTool() {
		synchronized (FileTool.class) {
			if (singletonFlag == true) {
				throw new RuntimeException(forestRebarName + "受到反射攻击");
			}
			singletonFlag = true;
			GlobalLogger.INSTANCE.getLOGGER().info("【单例】" + forestRebarName + "初始化");
		}
	}

	/**
	 * 
	 * @description 获得单例
	 * @return
	 * @throws Exception
	 */
	protected final static FileTool getInstance() {
		/**
		 * 双重锁
		 */
		if (fileTool == null) {
			synchronized (FileTool.class) {
				if (fileTool == null) {
					fileTool = new FileTool();
				}
			}
		}
		return fileTool;
	}

	/**
	 * 
	 * @description yaml文件的解析
	 * @author falconTrotk
	 * @param filePath
	 * @throws ExceptionPack
	 */
	public Object analyzeYamlByClass(InputStream inputStream, Class<?> zClass) throws CustomException {
		if (null == inputStream)
			throw new CustomException("yaml文件流为空");
		Yaml yaml = new Yaml();
		return yaml.loadAs(inputStream, zClass);
	}

	/**
	 * 
	 * @description yaml文件的解析成map
	 * @author falconTrotk
	 * @param inputStream
	 * @return
	 * @throws ExceptionPack
	 */
	public Map<?, ?> analyzeYamlToMap(InputStream inputStream) throws CustomException {

		return (Map<?, ?>) analyzeYamlByClass(inputStream, Map.class);
	}

	/**
	 * 
	 * @description yaml文件的解析成Hashtable
	 * @author falconTrotk
	 * @param inputStream
	 * @return
	 * @throws ExceptionPack
	 */
	public Hashtable<?, ?> analyzeYamlToHashtable(InputStream inputStream) throws CustomException {

		return (Hashtable<?, ?>) analyzeYamlByClass(inputStream, Hashtable.class);
	}

}
