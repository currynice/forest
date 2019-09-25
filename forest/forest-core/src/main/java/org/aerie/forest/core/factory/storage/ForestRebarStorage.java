package org.aerie.forest.core.factory.storage;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.ForestRebar;
import org.aerie.forest.core.factory.container.ForestContainer;

/**
 * 
 * @description forest架构元素入库组件
 * @author trotkFaclon
 * @company aerie
 * @version 1.0.1
 * @date 2019年9月15日下午3:51:54
 *
 */
public abstract class ForestRebarStorage {

	/**
	 * 单例标识符
	 */
	protected boolean singletonFlag = false;

	/**
	 * 
	 * @description 获得forest架构元素
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年9月23日下午1:37:58
	 * @version 1.0.1
	 */
	public final void putInStorage() throws Exception {
		ForestRebar forestRebar = getForestRebar();
		isLegal(forestRebar);
		ForestContainer.INSTANCE.addForestRebar(forestRebar);
	}

	/**
	 * 
	 * @description 获得对应的forest架构元素
	 * @return
	 */
	protected abstract ForestRebar getForestRebar();

	/**
	 * 
	 * @description 判断是否是合法单例【只从私有构造和包调用获得单例的方法出发】
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年9月25日下午1:09:25
	 * @version 1.0.1
	 * @return
	 */
	private void isLegal(ForestRebar forestRebar) {
		// 获得对象的类
		Class<? extends ForestRebar> forestRebarClass = forestRebar.getClass();
		if (forestRebarClass.getModifiers() != 17) {
			GlobalLogger.INSTANCE.getLOGGER().warn(forestRebarClass.getName() + "类的修饰符应该是public + final");
		}
		// 获得所有public的构造函数
		if (forestRebarClass.getConstructors().length != 0) {
			throw new Error("forest架构元素必须是单例，私有化所有的构造方法");
		}
		// 获得获得本身对象以及修饰符是(默认修饰符或者protected)+(static或者final+static)的方法
		List<Method> resultMethods = Arrays.asList(forestRebarClass.getDeclaredMethods()).stream()
				.filter(p1 -> p1.getGenericReturnType().getTypeName().equals(forestRebarClass.getName()))
				.filter(p2 -> p2.getModifiers() == 8 || p2.getModifiers() == 12 || p2.getModifiers() == 24
						|| p2.getModifiers() == 28)
				.collect(Collectors.toList());
		if (resultMethods != null && resultMethods.size() != 1) {
			throw new Error("forest架构元素必须是单例，获取单例的静态方法有且只有一个");
		}
	}
}