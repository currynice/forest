package org.aerie.forest.core.element.rebar.singleton.processer.config;

import java.io.File;
import java.util.List;

import org.aerie.forest.core.element.brick.exception.CustomException;
import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.singleton.bootable.timecrystal.TimeCrystal;
import org.aerie.forest.core.element.rebar.singleton.processer.ForestProcessor;
import org.aerie.forest.core.element.rebar.singleton.processer.config.container.ForestConfig;
import org.aerie.forest.core.element.rebar.unsingleton.tool.FileTool;

/**
 * 
 * @description forest配置处理器【1.默认先加载自定义配置文件;2.默认先加载properties配置文件】
 * @author falconTrotk
 * @company aerie
 * @date 2019年7月31日上午10:24:56
 * @version 1.0.1
 */
public final class ConfigProcessor extends ForestProcessor {
	/**
	 * 配置路径【打成jar的路径】
	 */
	private final String CONFIG_PATH = "forestConfig/";
	/**
	 * 配置路径【自定义配置的路径】
	 */
	private final String CUSTOM_CONFIG_PATH = "src/main/resources/forestConfig/";
	/**
	 * 配置文件【yaml】
	 */
	private final String DEFAULT_CONFIG_PATH_YAML = "forest.yml";
	/**
	 * 配置容器
	 */
	private ForestConfig forestConfig = new ForestConfig();
	/**
	 * 单例
	 */
	private static ConfigProcessor configProcessor;

	private ConfigProcessor() {
		synchronized (ConfigProcessor.class) {
			if (singletonFlag == true) {
				throw new RuntimeException(forestRebarName + "受到反射攻击");
			}
			singletonFlag = true;
			GlobalLogger.INSTANCE.getLOGGER().info("【单例】" + forestRebarName + "初始化");
		}
		GlobalLogger.INSTANCE.getLOGGER().debug("forest配置处理器初始化");
	}

	/**
	 * 
	 * @description 获得单例
	 * @return
	 * @throws Exception
	 */
	static ConfigProcessor getInstance() {
		/**
		 * 双重锁
		 */
		if (configProcessor == null) {
			synchronized (TimeCrystal.class) {
				if (configProcessor == null) {
					configProcessor = new ConfigProcessor();
				}
			}
		}
		return configProcessor;
	}

	public ForestConfig getForestConfig() {
		return forestConfig;
	}

	/**
	 * 
	 * @description 加载配置文件【将forest配置处理器和异常处理器解耦，同时解决初始化互相调用的问题，这里的下载的异常需要自处理】
	 * @author falconTrotk
	 * @throws ExceptionPack
	 */
	public void loadConfig() {
		// 加载配置文件【流加载】
		try {
			ForestConfig forestConfig = (ForestConfig) FileTool.INSTANCE.analyzeYamlByClass(
					this.getClass().getClassLoader().getResourceAsStream(CONFIG_PATH + DEFAULT_CONFIG_PATH_YAML),
					ForestConfig.class);
			List<String> fieldsNameIsNull = forestConfig.getFieldsNameIsNull();
			if (fieldsNameIsNull != null && !fieldsNameIsNull.isEmpty()) {
				throw new CustomException(fieldsNameIsNull.toString() + "这些配置文件的属性为空");
			}
			// 校验成功后再加载
			this.forestConfig = forestConfig;
			GlobalLogger.INSTANCE.getLOGGER().info("加载配置文件成功");
		} catch (Exception e) {
			System.err.println("\n解析异常：— — — — — — — — — — — — — — — — — — — — — — — — — — — — — —"
					+ " — — — — — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —"
					+ "— — — — — — — — ############");
			GlobalLogger.INSTANCE.getLOGGER().error("forest配置处理器初始化失败");
			System.err.println("-->cause by:");
			GlobalLogger.INSTANCE.getLOGGER().error(e.getMessage());
			// yaml配置加载出错
			File file = new File(CUSTOM_CONFIG_PATH + DEFAULT_CONFIG_PATH_YAML);
			// 存在自定义配置文件的话
			if (file.exists()) {
				GlobalLogger.INSTANCE.getLOGGER().debug(DEFAULT_CONFIG_PATH_YAML + "配置文件不准确");
				// 将自定义配置文件重命名
				file.renameTo(new File(CUSTOM_CONFIG_PATH + "forestErr.yml"));
				GlobalLogger.INSTANCE.getLOGGER().info("加载依赖默认配置文件");
				// 重新加载配置文件
				loadConfig();
			} else {
				System.err.println("异常解析完毕：— — — — — — — — — — — — — — — — — — — — — — — — — — — — — —"
						+ " — — — — — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —"
						+ "— — — — — — — — ############\n");
				GlobalLogger.INSTANCE.getLOGGER().error("forest依赖崩溃");
				// 影响整个系统的运行程序退出
				System.exit(0);
			}
			System.err.println("异常解析完毕：— — — — — — — — — — — — — — — — — — — — — — — — — — — — — —"
					+ " — — — — — — — — — — — — — — — — — — — — — — — — — — — — — — — — — —"
					+ "— — — — — — — — ############\n");
		}
	}
}
