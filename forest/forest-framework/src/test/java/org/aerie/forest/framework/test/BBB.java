package org.aerie.forest.framework.test;

import java.util.ArrayList;
import java.util.List;

public class BBB {

	public static List<String> s = new ArrayList<>();

	static {
		System.out.println("22222");
		s.add("111");
	}
}
