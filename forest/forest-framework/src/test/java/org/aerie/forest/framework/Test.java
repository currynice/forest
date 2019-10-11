package org.aerie.forest.framework;

import java.lang.reflect.Method;

import org.aerie.forest.core.factory.StorageBuilder;
import org.aerie.forest.rebar._shelf.ForestRebarShelf;
import org.aerie.forest.rebar.bootable.timecrystal.TimeCrystal;

public class Test {
	public static void main(String[] args) {
		try {
			TimeCrystal forestRebarFactory = Forester.forestFactory.getForestRebarFactory(ForestRebarShelf.getInstance().TIME_CRYSTAL);
			forestRebarFactory.run();
//			StorageBuilder.INSTANCE.buildStorage(TimeCrystal.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
