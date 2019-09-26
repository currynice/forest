package org.aerie.forest.core.element.rebar.tool.file;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;

import org.aerie.forest.core.element.brick.exception.CustomException;
import org.aerie.forest.core.element.rebar.tool.ForestTool;
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

	protected FileTool() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @description yaml文件的解析
	 * @author falconTrotk
	 * @param filePath
	 * @throws ExceptionPack
	 */
	public static Object analyzeYamlByClass(InputStream inputStream, Class<?> zClass) throws CustomException {
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
	public static Map<?, ?> analyzeYamlToMap(InputStream inputStream) throws CustomException {

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
	public static Hashtable<?, ?> analyzeYamlToHashtable(InputStream inputStream) throws CustomException {

		return (Hashtable<?, ?>) analyzeYamlByClass(inputStream, Hashtable.class);
	}

}
