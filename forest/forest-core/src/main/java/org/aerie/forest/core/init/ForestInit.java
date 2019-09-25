package org.aerie.forest.core.init;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.xml.XmlConfiguration;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.util.StatusPrinter;

/**
 * 
 * @description 森林初始化环境
 * @author trotkFalcon
 * @company aerie
 * @date 2019年8月27日上午11:28:54
 * @version 1.0.1
 */
public enum ForestInit {
	INSTANCE;
	/**
	 * 
	 * @description 日志绑定实现的类型
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月28日下午1:58:45
	 * @version 1.0.1
	 */
	private enum LogBinderType {
		LOG4J2, LOGBACK;
	}

	/**
	 * 资源路径
	 */
	private final static String RESOURCES_PATH = "/src/main/resources/";
	/**
	 * log配置文件的包路径
	 */
	private final static String PACK_PATH = "/logConfig/";
	/**
	 * log4j2的配置文件名
	 */
	private final static String LOG4J2_FILE_NAME = "log4j2-forest.xml";
	/**
	 * logback的配置文件名
	 */
	private final static String LOGBACK_FILE_NAME = "logback-forest.xml";
	/**
	 * 静态日志绑定器路径
	 */
	private final static String STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";

	private ForestInit() {
		// Do Nothing
	}

	/**
	 * 加载log配置环境
	 */
	public synchronized void loadLogConfig() {
		if (ForestConfig.INSTANCE.isLogInitConfig()) {
			System.err.println("log配置环境已经加载，无法重复加载");
			return;
		}
		System.err.println("==>\t开始刷新log配置环境");
		try {
			if (getLogBinderType() == LogBinderType.LOG4J2) {
				// 分两种情况一种是LoggerContext已经加载了，一种是还没有加载【这里因为loggerContext初始化，无法加载，所有强制重新加载】
				// 已经加载需要重新加载
				afreshProcessLog4j2ConfigFile();
			} else {
				// 加载log4j2配置文件
				processLogbackConfigFile();
			}
			// 更改初始化标识符
			ForestConfig.INSTANCE.setLogInitConfig(true);
			System.err.println("<==\t刷新log配置环境成功");
		} catch (Exception e) {
			System.err.println("加载log配置文件失败:\nCaused By:");
			// 加载log之前只能这么打印
			e.printStackTrace();
			System.err.println("非正常强制退出");
			System.exit(-1);
		}
	}

	/**
	 * 
	 * @description 判断loggerContext是否已经加载
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月30日下午12:50:55
	 * @version 1.0.1
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean isLoggerContextLoaded() {
		// 没有想到好办法
		// TODO
		return true;
	}

	/**
	 * 
	 * @description 获得日志绑定实现的类型
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月28日下午2:10:32
	 * @version 1.0.1
	 * @throws CustomException
	 */
	public LogBinderType getLogBinderType() throws Exception {
		// 获得可能的静态日志绑定路径
		List<URL> findPossibleStaticLoggerBinderPath = findPossibleStaticLoggerBinderPathSet();
		int size = findPossibleStaticLoggerBinderPath.size();
		// 静态日志绑定路径有且只能有一个
		if (size != 1) {
			throw new Exception("slf4j检测到" + size + "个log依赖，有且只能有一个");
		}
		String staticLoggerBinderPath = findPossibleStaticLoggerBinderPath.get(0).getPath();
		// 判断依赖的log的类型【暂时只支持log4j2和logback】
		if (staticLoggerBinderPath.indexOf("log4j-slf4j-impl-2") != -1) {
			return LogBinderType.LOG4J2;
		} else if (staticLoggerBinderPath.indexOf("ch/qos/logback/logback-classic/") != -1) {
			return LogBinderType.LOGBACK;
		} else {
			throw new Exception("现在只支持log4j2和logback;而查询到的日志绑定路径是：" + staticLoggerBinderPath);
		}
	}

	/**
	 * 
	 * @description 重新加载log4j2的配置文件
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月28日下午2:07:58
	 * @version 1.0.1
	 * @throws Exception
	 */
	private void afreshProcessLog4j2ConfigFile() throws Exception {
		try {
			// 防止loggerContext初始化了但是loggerFactory没有初始化的情况【会报没有找到Log4j2配置文件】
			@SuppressWarnings("unused")
			GlobalLogger globalLogger = GlobalLogger.INSTANCE;
			System.err.println("==> ↑↑↑无视上方无法找到Log4j2配置文件的错误");
			// 获取log4j2配置文件流
			InputStream inputStream = ForestInit.class.getResourceAsStream(PACK_PATH + LOG4J2_FILE_NAME);
			// 获得配置源
			ConfigurationSource configurationSource = new ConfigurationSource(inputStream);
			// 初始化获得log的上下文
			LoggerContext loggerContext = Configurator.initialize(null, configurationSource);
			// 得到配置文件的环境
			XmlConfiguration xmlConfiguration = new XmlConfiguration(loggerContext, configurationSource);
			// 讲log的上下文按照指定的配置文件启动
			loggerContext.start(xmlConfiguration);
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 
	 * @description 加载log4j2配置文件
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月30日下午12:48:45
	 * @version 1.0.1
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void processLog4j2ConfigFile() throws Exception {
		System.err.println("系统初始化加载log4j2配置文件");
		File logConfigFile = new File(RESOURCES_PATH + PACK_PATH + LOG4J2_FILE_NAME);
		if (!logConfigFile.exists()) {
			try {
				File logConfigFilepack = new File(RESOURCES_PATH + PACK_PATH);
				if (!logConfigFilepack.exists()) {
					// 包路径不存在，创建
					logConfigFilepack.mkdirs();
				}
				// 创建配置文件
				logConfigFile.createNewFile();
				// 写入jar包中的配置文件
				try (FileOutputStream fileOutputStream = new FileOutputStream(logConfigFile);
						InputStream resourceAsStream = ForestInit.class.getClassLoader()
								.getResourceAsStream(PACK_PATH + LOG4J2_FILE_NAME);) {
					byte buffer[] = new byte[1024];
					int num;
					while ((num = resourceAsStream.read(buffer)) != -1) {
						for (int i = 0; i < num; i++)
							fileOutputStream.write(buffer[i]);
					}
				} catch (Exception e) {
					throw e;
				}
			} catch (Exception e) {
				throw new Exception("处理log4j2的配置文件", e);
			}
		}
		System.setProperty("log4j2ConfigurationFile", RESOURCES_PATH + PACK_PATH + LOG4J2_FILE_NAME);
	}

	/**
	 * 
	 * @description 处理logback的配置文件
	 * @author trotkFalcon
	 * @company aeried
	 * @date 2019年8月28日下午2:32:26
	 * @version 1.0.1
	 * @throws Exception
	 */
	private void processLogbackConfigFile() throws Exception {
		ILoggerFactory iLoggerFactory = org.slf4j.LoggerFactory.getILoggerFactory();
		File file = new File(System.getProperty("user.dir") + RESOURCES_PATH + PACK_PATH + LOGBACK_FILE_NAME);
		Class<?> forName;
		try {
			forName = Class.forName("ch.qos.logback.classic.joran.JoranConfigurator");
			ContextAwareBase newInstance = (ContextAwareBase) forName.newInstance();
			newInstance.setContext((Context) iLoggerFactory);
			Method method = forName.getMethod("doConfigure", File.class);
			method.invoke(newInstance, file);
		} catch (Exception e) {
			throw e;
		}
		StatusPrinter.printInCaseOfErrorsOrWarnings((Context) iLoggerFactory);
	}

	/**
	 * 
	 * @description 找到可能的静态日志绑定路径
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月28日下午1:18:54
	 * @version 1.0.1
	 * @return
	 */
	private List<URL> findPossibleStaticLoggerBinderPathSet() throws Exception {
		List<URL> staticLoggerBinderPathSet = new ArrayList<>();
		try {
			// 加载LoggerFactory
			ClassLoader loggerFactoryClassLoader = LoggerFactory.class.getClassLoader();
			Enumeration<URL> paths;
			// 搜索路径中查找静态日志绑定器路径的所有资源
			if (loggerFactoryClassLoader == null) {
				paths = ClassLoader.getSystemResources(STATIC_LOGGER_BINDER_PATH);
			} else {
				paths = loggerFactoryClassLoader.getResources(STATIC_LOGGER_BINDER_PATH);
			}
			// 遍历并添加到返回集合中
			while (paths.hasMoreElements()) {
				URL path = paths.nextElement();
				staticLoggerBinderPathSet.add(path);
			}
		} catch (IOException e) {
			throw new Exception("找到可能的静态日志绑定路径出错", e);
		}
		return staticLoggerBinderPathSet;
	}

}
