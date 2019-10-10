package org.aerie.forest.core.element.brick.exception;

import java.util.Optional;

/**
 * 
 * @description 自定义异常
 * @author falconTrotk
 * @company aerie
 * @date 2019年9月12日下午1:08:32
 * @version 1.0.1
 */
public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @description 自定义异常【禁止无参构造和异常信息禁止为空】
	 * @param message 异常信息
	 */
	public CustomException(String message) {
		super(Optional.ofNullable(message).filter(p1 -> !p1.isEmpty()).orElse("丢失异常信息"));
	}
}
