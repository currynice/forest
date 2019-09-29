package org.forest.framework.test;

import java.io.IOException;
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
import org.aerie.forest.core.element.rebar.bootable.timecrystal.TimeCrystal;
import org.aerie.forest.core.element.rebar.bootable.uuidpool.UuidPool;
import org.aerie.forest.core.element.rebar.processer.exception.ExceptionProcessor;
import org.aerie.forest.core.element.rebar.tool.file.FileTool;
import org.aerie.forest.core.init.ForestInit;
import org.apache.logging.log4j.core.Logger;
import org.forest.framework.ForestFactory;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.FieldInfo;

/**
 * Unit test for simple App.
 */
public class AppTest {

	public static void main(String[] args) {
		ClassPool pool = ClassPool.getDefault();
		CtClass clazz = pool.makeClass("org.forest.framework.test.Tcxfz");
		ClassFile ccFile = clazz.getClassFile();
		ConstPool constpool = ccFile.getConstPool();
		CtClass executor = null;
		try {
			executor = pool.get("javassist.bytecode.analysis.Executor");
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fieldName = "sss";
		// 增加字段
		CtField field = null;
		try {
			field = new CtField(executor, fieldName, clazz);
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		field.setModifiers(Modifier.PUBLIC);
//		FieldInfo fieldInfo = field.getFieldInfo();
		try {
			clazz.addField(field);
		} catch (CannotCompileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			clazz.writeFile();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		ForestInit.INSTANCE.loadLogConfig();
////		ForestInit.INSTANCE.loadLogConfig();
//		TimeCrystal forestRebarFactory = ForestFactory.INSTANCE
//				.getForestRebarFactory(ForestRebarFactoryType.TIME_CRYSTAL);
//		try {
//			forestRebarFactory.run();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("");
//		CCC c = new CCC();
//		c.sSS();
//		List<String> s = c.getS();
//		for (String ss : s) {
//			System.out.println(ss);
//		}
//		FileTool f = new FileTool();

//		ForestRebarFactoryType d = new ForestRebarFactoryType();
//		try {

//			forestRebarFactory.run();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public static <T> T SS() {
		return null;

	}
}
