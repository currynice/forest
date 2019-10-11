package org.aerie.forest.framework;

import org.aerie.forest.core.factory.ForestFactory;
import org.aerie.forest.rebar._shelf.ForestRebarShelf;
import org.aerie.forest.rebar.bootable.uuidpool.UuidPool;

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
