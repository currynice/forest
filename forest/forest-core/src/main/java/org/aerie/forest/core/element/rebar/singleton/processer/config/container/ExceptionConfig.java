package org.aerie.forest.core.element.rebar.singleton.processer.config.container;

import org.aerie.forest.core.element.brick.exception.ExceptionGradeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @description 异常配置
 * @author falconTrotk
 * @company aerie
 * @date 2019年8月19日下午2:45:02
 * @version 1.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionConfig extends ConfigContainer {
	/**
	 * 默认的异常等级【无配置默认为warn】
	 */
	private ExceptionGradeEnum defaultExceptionGrade = ExceptionGradeEnum.ERROR;
	/**
	 * 崩溃自动退出
	 */
	private boolean fatalExit = false;
}
