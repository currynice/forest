package org.aerie.forest.framework;

import org.aerie.forest.bootable.timecrystal.TimeCrystal;
import org.aerie.forest.core.factory.ForestFactory;

public class Test {
	public static void main(String[] args) {

		ForestFactory forestFactory = Forester.forestFactory;
		TimeCrystal forestRebarFactory = forestFactory
				.getForestRebarFactory(ForestRebarShelf.getInstance().TIME_CRYSTAL);
		try {
			forestRebarFactory.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
