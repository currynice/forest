package org.aerie.forest.core;

import org.aerie.forest.core.element.rebar.bootable.timecrystal.TimeCrystal;
import org.aerie.forest.core.element.rebar.bootable.uuidpool.UuidPool;
import org.aerie.forest.core.factory.ForestFactory;
import org.aerie.forest.core.factory.ForestRebarInitShelf;
import org.aerie.forest.core.init.ForestInit;

public class Test {
	public static void main(String[] args) {
		ForestInit.INSTANCE.loadLogConfig();
		UuidPool forestRebarFactory = ForestFactory.INSTANCE
				.getForestRebarFactory(ForestRebarInitShelf.getInstance().UUID_POOL);
		try {
			forestRebarFactory.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
