package org.aerie.forest.framework.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.aerie.forest.core.element.brick.exception.ExceptionGradeEnum;
import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.ForestRebar;
import org.aerie.forest.core.element.rebar.singleton.bootable.timecrystal.TimeCrystal;
import org.aerie.forest.core.element.rebar.singleton.bootable.timecrystal.TimeCrystalStorage;
import org.aerie.forest.core.init.ForestInit;
import org.aerie.forest.framework.ForestFactory;
import org.aerie.forest.framework.ForestRebarFactoryType;

/**
 * Unit test for simple App.
 */
public class AppTest {

	public static void main(String[] args) {
		ForestInit.INSTANCE.loadLogConfig();
		ExceptionGradeEnum error = ExceptionGradeEnum.ERROR;
//		ForestInit.INSTANCE.loadLogConfig();
//		TimeCrystal forestRebarFactory = ForestFactory.INSTANCE
//				.getForestRebarFactory(ForestRebarFactoryType.TIME_CRYSTAL);
//		ForestRebarFactoryType d = new ForestRebarFactoryType();
//		try {
//			forestRebarFactory.run();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	protected static void isLegal(ForestRebar forestRebar) {
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
