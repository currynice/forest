package org.aerie.forest.framework;

import org.aerie.forest.core.element.rebar.bootable.timecrystal.TimeCrystal;
import org.aerie.forest.core.factory.ForestFactory;
import org.aerie.forest.core.factory.ForestRebarInitShelf;

/**
 * Unit test for simple App.
 */
public class AppTest {
	public static void main(String[] args) {

		ForestFactory forestFactory = ForestFactory.INSTANCE;
		TimeCrystal forestRebarFactory = forestFactory
				.getForestRebarFactory(ForestRebarInitShelf.getInstance().TIME_CRYSTAL);
		try {
			forestRebarFactory.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
