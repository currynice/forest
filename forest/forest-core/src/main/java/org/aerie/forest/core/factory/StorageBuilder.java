package org.aerie.forest.core.factory;

import java.lang.reflect.Modifier;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.ForestRebarStorage;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;

/**
 * 
 * @description 入库组件生成器
 * @author falconTrotk
 * @company aerie
 * @date 2019年10月11日上午10:55:38
 * @version 1.0.1
 */
public enum StorageBuilder {
	INSTANCE;

	private final String STORAGE = "Storage";

	/**
	 * 
	 * @description 生成入库组件【字节码生成】
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年10月11日上午11:01:16
	 * @version 1.0.1
	 * @return
	 * @return
	 */
	public <T> Class<?> buildStorage(Class<T> zclass) throws Exception {
		if (Modifier.isAbstract(zclass.getModifiers())) {
			throw new RuntimeException("抽象forest架构元素无法生成入库组件");
		}
		// 判断入库组件是否已经存在【存在就直接返回】
		Class<?> result = null;
		try {
			result = Class.forName(zclass.getName() + STORAGE);
			return result;
		} catch (ClassNotFoundException e) {
			GlobalLogger.INSTANCE.getLOGGER().info("字节码创建架构元素的入库组建");
		}
		// 获得入库组件的类名
		String className = zclass.getSimpleName() + STORAGE;
		String absoluteClassName = zclass.getName() + STORAGE;
		// 创建入库组件的类s
		ClassPool classPool = ClassPool.getDefault();
		CtClass ctClass = classPool.makeClass(absoluteClassName, classPool.get(ForestRebarStorage.class.getName()));
		// 添加静态单例属性
		CtField enameField = new CtField(classPool.getCtClass(absoluteClassName), capitalizeFirest(className), ctClass);
		enameField.setModifiers(Modifier.PRIVATE + Modifier.STATIC);
		ctClass.addField(enameField);
		// 添加私有的构造方法【懒双重锁单例模式，防止反射攻击优化】
		CtConstructor ctConstructor = new CtConstructor(new CtClass[] {}, ctClass);
		StringBuffer buffer = new StringBuffer();
		StringBuffer append = buffer.append("{\nsynchronized  (" + absoluteClassName + ".class) {\n")
				.append("if (singletonFlag == true)\n").append("throw new RuntimeException(\"受到反射攻击\");\n")
				.append("singletonFlag = true;\n").append("System.err.println(\"【单例】时间晶振入库组件初始化\");\n}\n}");
		ctConstructor.setBody(append.toString());
		ctConstructor.setModifiers(Modifier.PRIVATE);
		ctClass.addConstructor(ctConstructor);
		// 添加获取单例的静态方法
		CtMethod methodGetInstance = new CtMethod(classPool.get(absoluteClassName), "getInstance", new CtClass[] {},
				ctClass);
		methodGetInstance.setModifiers(Modifier.PUBLIC + Modifier.STATIC + Modifier.FINAL);
		StringBuffer methodBodyGetInstance = new StringBuffer();
		methodBodyGetInstance.append("{\nif (" + capitalizeFirest(className) + " == null) {")
				.append("synchronized (" + absoluteClassName + ".class) {")
				.append("if (" + capitalizeFirest(className) + " == null) {")
				.append(capitalizeFirest(className) + " = new " + absoluteClassName + "();").append("}\n}\n}\n")
				.append("return " + capitalizeFirest(className) + ";\n}");
		methodGetInstance.setBody(methodBodyGetInstance.toString());
		ctClass.addMethod(methodGetInstance);
		// 添加获取架构元素的方法
		CtMethod methodGetForestRebar = new CtMethod(classPool.get("org.aerie.forest.core.element.rebar.ForestRebar"),
				"getForestRebar", new CtClass[] {}, ctClass);
		methodGetForestRebar.setModifiers(Modifier.PROTECTED);
		String methodBodyGetForestRebar = "{\nreturn (org.aerie.forest.core.element.rebar.ForestRebar)"
				+ zclass.getName() + ".getInstance();\n}";
		methodGetForestRebar.setBody(methodBodyGetForestRebar);
		ctClass.addMethod(methodGetForestRebar);
		return ctClass.toClass();
	}

	/**
	 * 
	 * @description 字符串首字母小写
	 * @param str
	 * @return
	 */
	private String capitalizeFirest(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}
}
