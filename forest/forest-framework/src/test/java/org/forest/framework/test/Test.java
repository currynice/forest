package org.forest.framework.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.aerie.forest.core.element.brick.exception.ExceptionPack;
import org.aerie.forest.core.element.rebar.processer.exception.ExceptionProcessor;
import org.aerie.forest.core.init.ForestInit;
import org.forest.framework.ForestFactory;
import org.forest.framework.ForestRebarShelf;

public class Test {
	public static void main(String[] args) {
		ForestInit.INSTANCE.loadLogConfig();
		try {
			s1();
		} catch (Exception e) {
//			ExceptionProcessor forestRebarFactory = ForestFactory.INSTANCE
//					.getForestRebarFactory(ForestRebarShelf.getInstance().);
//			forestRebarFactory.recordException("我是直接原因", e);
		}
	}

	private static void s1() throws Exception {
		System.err.println("s11111111111111111");
		try {
			s2();
		} catch (Exception e) {
			throw new ExceptionPack("二级间接原因", e);
		}

	}

	private static void s2() throws Exception {
		System.err.println("s22222222222222222");
		try {
			s3();
		} catch (Exception e) {
			throw new ExceptionPack("一级间接原因", e);
		}
	}

	private static void s3() throws Exception {
		System.err.println("s33333333333333333");
		File file = new File("");
		InputStream inputStream = new FileInputStream(file);
	}
}
