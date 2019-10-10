package org.aerie.forest.framework;

import org.aerie.forest.bootable.uuidpool.UuidPool;
import org.aerie.forest.core.factory.ForestFactory;

public class Test {
	public static void main(String[] args) {
		ForestFactory forestFactory = Forester.forestFactory;
		UuidPool forestRebarFactory = forestFactory.getForestRebarFactory(ForestRebarShelf.getInstance().UUID_POOL);
		try {
			forestRebarFactory.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
