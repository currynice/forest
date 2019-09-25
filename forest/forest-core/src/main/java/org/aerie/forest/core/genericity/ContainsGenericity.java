package org.aerie.forest.core.genericity;

import java.lang.reflect.ParameterizedType;

/**
 * 
 * @description 包含泛型的类
 * @author falconTrotk
 * @company aerie
 * @date 2019年9月24日下午1:21:55
 * @version 1.0.1
 */
public abstract class ContainsGenericity<T>{
	/**
	 * 
	 * @description 获得泛型的类型
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年9月24日下午12:50:26
	 * @version 1.0.1
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Class<T> getGenericityZclass() {
		ParameterizedType p = (ParameterizedType) (getClass().getGenericSuperclass());
		return (Class<T>) p.getActualTypeArguments()[0];
	}
}
