package org.aerie.forest.core.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.ForestRebar;
import org.aerie.forest.core.factory.ForestShelf.RebarScutcheon;

/**
 * 
 * @description forest容器
 * @author falconTrotk
 * @company aerie
 * @date 2019年10月10日下午5:42:52
 * @version 1.0.1
 */
public enum ForestContainer {
	INSTANCE;

	/**
	 * forest架构元素组
	 */
	private Map<Class<? extends ForestRebar>, ForestRebarContainer<? extends ForestRebar>> forestRebarContainers = new HashMap<>();

	private ForestContainer() {
		// 组合forest架构元素容器【初始化】
		for (ForestRebarContainerCategory forestRebarContainerCategory : ForestRebarContainerCategory.values()) {
			forestRebarContainers.put(forestRebarContainerCategory.getZclass(),
					forestRebarContainerCategory.getForestRebarContainer());
		}
	}

	/**
	 * 
	 * @description 添加forest架构元素
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年10月10日下午5:41:36
	 * @version 1.0.1
	 * @param forestRebar
	 * @throws Exception
	 */
	public synchronized void addForestRebar(ForestRebar forestRebar) throws Exception {
		for (ForestRebarContainerCategory forestRebarContainerCategory : ForestRebarContainerCategory.values()) {
			Class<? extends ForestRebar> zclass = forestRebarContainerCategory.getZclass();
			if (zclass.isInstance(forestRebar)) {
				// 去除容器类型的泛型类型
				List<? extends ForestRebar> container = forestRebarContainers.get(zclass).getContainer();
				if (container.stream().filter(p1 -> p1.hashCode() == forestRebar.hashCode()).count() == 0) {
					addForestRebarToList(container, forestRebar);
				} else {
					GlobalLogger.INSTANCE.getLOGGER()
							.warn("forest容器中存在：" + forestRebar.getForestRebarName() + "，无法重复添加");
				}
				return;
			}
		}
		throw new Exception("forest架构元素的容器分类缺少对应的类型，无法添加进容器");
	}

	/**
	 * 
	 * @description 将对象强转称集合的类型并添加进去【内部调用方法，插入之前已经校验了类型可以强转】
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年10月10日下午5:42:00
	 * @version 1.0.1
	 * @param <T>
	 * @param list
	 * @param forestRebar
	 */
	@SuppressWarnings("unchecked")
	private <T> void addForestRebarToList(List<T> list, ForestRebar forestRebar) {
		list.add((T) forestRebar);
	}

	/**
	 * 
	 * @description 从容器中取出对应的架构元素
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年10月10日下午5:42:14
	 * @version 1.0.1
	 * @param rebarScutcheon
	 * @return
	 */
	public ForestRebar getForestRebar(RebarScutcheon<?> rebarScutcheon) {
		return getForestRebar(rebarScutcheon, true);
	}

	/**
	 * 
	 * @description 从容器中取出对应的架构元素
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年10月10日下午5:42:30
	 * @version 1.0.1
	 * @param rebarScutcheon
	 * @param first
	 * @return
	 */
	private synchronized ForestRebar getForestRebar(RebarScutcheon<?> rebarScutcheon, boolean first) {
		// 获得需要取出的架构元素所在的容器的类型
		Class<? extends ForestRebar> zclassOfForestRebarContainer = rebarScutcheon.getForestRebarContainerCategory()
				.getZclass();
		// 获得容器中存储对应架构元素的容器
		List<? extends ForestRebar> forestRebarContainer = forestRebarContainers.get(zclassOfForestRebarContainer)
				.getContainer();
		// 获得和要取得的架构元素基本类型相同的对象
		List<? extends ForestRebar> collect = forestRebarContainer.stream()
				.filter(p1 -> p1.getClass() == rebarScutcheon.getForestRebarClass()).collect(Collectors.toList());
		// 容器中必须是单例
		if (collect == null || collect.size() > 1) {
			throw new RuntimeException("forest容器崩溃");
		}
		// 如果容器里面不存在元素则通过入库组件加入
		if (collect.size() == 0) {
			if (!first) {
				throw new RuntimeException("无法从容器中取出对应的架构元素");
			}
			try {
				// 执行入库指令
				rebarScutcheon.getForestRebarStorage().putInStorage();
			} catch (Exception e) {
				throw new RuntimeException(rebarScutcheon.getScutcheonName() + "执行【入库组件】失败", e);
			}
			return getForestRebar(rebarScutcheon, false);
		}
		return collect.get(0);
	}

}
