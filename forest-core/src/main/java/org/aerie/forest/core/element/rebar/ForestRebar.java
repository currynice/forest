package org.aerie.forest.core.element.rebar;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.init.ForestConfig;

/**
 * 
 * @description forest架构元素
 * @author trotkFalcon
 * @company aerie
 * @date 2019年8月29日下午4:12:16
 * @version 1.0.1
 */
public abstract class ForestRebar {
	/**
	 * 单例标识符
	 */
	protected boolean singletonFlag = false;

	/**
	 * forest架构元素的名称
	 */
	protected String forestRebarName = "未知元素";
	/**
	 * 合法性校验器集合
	 */
	protected List<ForestRebarLedalCheck> forestRebarLedalChecks = new ArrayList<ForestRebarLedalCheck>() {

		/**
		 * @description 序列化
		 * @author falconTrotk
		 * @company aerie
		 * @date 2019年9月27日上午11:15:41
		 * @version 1.0.1
		 */
		private static final long serialVersionUID = 1L;

		{
			/**
			 * forest架构元素单例模式校验
			 */
			add(new ForestRebarLedalCheck() {
				public void isLegal(ForestRebar forestRebar) {
					GlobalLogger.INSTANCE.getLOGGER()
							.debug("开始对" + forestRebar.getForestRebarName() + "forest架构元素单例模式校验");
					// 获得对象的类
					Class<? extends ForestRebar> forestRebarClass = forestRebar.getClass();
					if (forestRebarClass.getModifiers() != 17) {
						GlobalLogger.INSTANCE.getLOGGER().warn(forestRebarClass.getName() + "类的修饰符应该是public + final");
					}
					// 获得所有public的构造函数
					if (forestRebarClass.getConstructors().length != 0) {
						throw new Error("forest单例架构元素必须是单例，私有化所有的构造方法");
					}
					// 获得获得本身对象以及修饰符是(默认修饰符或者protected)+(static或者final+static)的方法
					List<Method> resultMethods = Arrays.asList(forestRebarClass.getDeclaredMethods()).stream()
							.filter(p1 -> p1.getGenericReturnType().getTypeName().equals(forestRebarClass.getName()))
							.collect(Collectors.toList());
					if (resultMethods != null && resultMethods.size() != 1) {
						throw new Error("forest单例架构元素必须是单例，获取单例的静态方法有且只有一个");
					}
					// 该获取单例的方法的修饰符编号
					int modifierCode = resultMethods.get(0).getModifiers();
					if (modifierCode != 8 && modifierCode != 12 && modifierCode != 24 && modifierCode != 28) {
						throw new Error("forest单例架构元素必须是单例，获取单例的静态方法有且只有一个");
					}
				}
			});
		}
	};

	/**
	 * 
	 * @description 构造【forestInit初始化完成才能构造】
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月29日下午4:25:17
	 * @version 1.0.1
	 * @throws Exception
	 */
	protected ForestRebar() {
		if (!ForestConfig.INSTANCE.isLogInitConfig()) {
			throw new Error("forest未完成初始化");
		}
	}

	public String getForestRebarName() {
		return forestRebarName;
	}

	protected List<ForestRebarLedalCheck> getForestRebarLedalChecks() {
		return forestRebarLedalChecks;
	}

	/**
	 * 
	 * @description 校验
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年9月27日下午1:03:35
	 * @version 1.0.1
	 * @param forestRebar
	 */
	protected final void isLegal(ForestRebar forestRebar) {
		// 校验所有的校验法则
		forestRebar.getForestRebarLedalChecks().stream().filter(p1 -> p1 != null)
				.forEach(p2 -> p2.isLegal(forestRebar));
	}

	/**
	 * 
	 * @description 添加校验法则
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年9月27日下午1:46:42
	 * @version 1.0.1
	 */
	protected abstract void addLedalCheck();
}
