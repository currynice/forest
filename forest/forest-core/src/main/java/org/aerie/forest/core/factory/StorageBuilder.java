package org.aerie.forest.core.factory;

import java.lang.reflect.Modifier;

import org.aerie.forest.core.element.rebar.ForestRebarStorage;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;

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

	private final String STORAGE = "Storage1";

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
		System.out.println(zclass.getName());
		System.out.println(zclass.getResource("").getPath());
		if (Modifier.isAbstract(zclass.getModifiers())) {
			throw new RuntimeException("抽象forest架构元素无法生成入库组件");
		}
		// 创建入库组件的类
		ClassPool classPool = ClassPool.getDefault();
		// 获得入库组件的类名
		String className = zclass.getSimpleName() + STORAGE;
		CtClass ctClass = classPool.makeClass(className, classPool.get(ForestRebarStorage.class.getName()));
		// 添加静态单例属性
		CtField enameField = new CtField(classPool.getCtClass(className), className, ctClass);
		enameField.setModifiers(Modifier.PRIVATE + Modifier.STATIC);
		ctClass.addField(enameField);
		// 添加私有的构造方法【懒双重锁单例模式，防止反射攻击优化】
		CtConstructor ctConstructor = new CtConstructor(new CtClass[] {}, ctClass);
		StringBuffer buffer = new StringBuffer();
		StringBuffer append = buffer.append("{\nsynchronized  (" + className + ".class) {\n")
				.append("if (singletonFlag == true)\n").append("throw new RuntimeException(\"受到反射攻击\");\n")
				.append("singletonFlag = true;\n").append("System.err.println(\"【单例】时间晶振入库组件初始化\");\n}\n}");
		ctConstructor.setBody(append.toString());
		ctConstructor.setModifiers(Modifier.PRIVATE);
		ctClass.addConstructor(ctConstructor);
		// 
		ctClass.writeFile("C:\\Users\\trotk\\Desktop\\cs");
		Class<?> forName = ctClass.toClass();
//		Method method = forName.getMethod("setTcxfz", String.class);
//		Object newInstance = forName.newInstance();
//		method.invoke(newInstance, "268172s");
//		Method method1 = forName.getMethod("getTcxfz");
//		Object invoke = method1.invoke(newInstance);
//		System.out.println(invoke);
//		CtConstructor parentConstructor = new CtConstructor(new CtClass[] {}, ForestRebarStorage.class);  
//        parentConstructor.setBody("{}");  
//        parentClass.addConstructor(parentConstructor);
		return forName;
	}
}
