package org.aerie.forest.core.element.rebar;

import org.aerie.forest.core.init.ForestConfig;

/**
 * 
 * @description forest架构元素
 * @author trotkFalcon
 * @company aerie
 * @date 2019年8月29日下午4:12:16
 * @version 1.0.1
 */
public abstract class ForestRebar {
	
	/**
	 * forest架构元素的名称
	 */
	protected String forestRebarName = "未知元素";

	/**
	 * 
	 * @description 构造【forestInit初始化完成才能构造】
	 * @author trotkFalcon
	 * @company aerie
	 * @date 2019年8月29日下午4:25:17
	 * @version 1.0.1
	 * @throws Exception
	 */
	protected ForestRebar() {
		if (!ForestConfig.INSTANCE.isLogInitConfig()) {
			throw new Error("forest未完成初始化");
		}
	}

	public String getForestRebarName() {
		return forestRebarName;
	}

}
