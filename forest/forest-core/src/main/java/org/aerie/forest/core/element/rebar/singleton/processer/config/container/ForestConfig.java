package org.aerie.forest.core.element.rebar.singleton.processer.config.container;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @description 配置文件的配置类
 * @author falconTrotk
 * @company aerie
 * @date 2019年8月4日下午5:42:16
 * @version 1.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ForestConfig extends ConfigContainer {
	/**
	 * 异常的环境配置
	 */
	private ExceptionConfig exceptionConfig = new ExceptionConfig();

	private boolean timeCrystal = false;
}