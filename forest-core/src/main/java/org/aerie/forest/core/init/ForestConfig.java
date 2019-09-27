package org.aerie.forest.core.init;

/**
 * 
 * @description forest的环境变量
 * @author falconTrotk
 * @company aerie
 * @date 2019年9月25日下午5:01:08
 * @version 1.0.1
 */
public enum ForestConfig {
	INSTANCE;

	private boolean logInitConfig = false;

	private ForestConfig() {
		// Do Nothing
	}

	public boolean isLogInitConfig() {
		return logInitConfig;
	}

	void setLogInitConfig(boolean logInitConfig) {
		this.logInitConfig = logInitConfig;
	}

}
