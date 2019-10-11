package org.aerie.forest.processer.config.container;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.aerie.forest.core.element.brick.exception.CustomException;

/**
 * 
 * @description 环境配置容器
 * @author falconTrotk
 * @company aerie
 * @date 2019年8月19日下午5:03:55
 * @version 1.0.1
 */
public abstract class ConfigContainer {
	/**
	 * 
	 * @description 获得实例里面的属性哪些是空
	 * @author falconTrotk
	 * @param object
	 * @return
	 */
	public final List<String> getFieldsNameIsNull() throws Exception {
		List<String> result = new ArrayList<>();
		// 获得类的所有字段
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object object = field.get(this);
			// 将字段是空的属性的名称加入返回集合里
			if (object == null) {
				result.add(field.getName());
				continue;
			}
			// 不为空的对象进行判断
			if (!isBaseType(object) && !(object instanceof String) && !(object instanceof Enum)) {
				if (object instanceof ConfigContainer) {
					result.addAll(((ConfigContainer) object).getFieldsNameIsNull());
				} else {
					// 只能是ConfigContainer的子类或者基本数据类型和其包装里或者String
					throw new CustomException("forest配置文件中" + field.getName() + "不是合法类型");
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @description 判断对象是不是基本数据类型
	 * @param object
	 * @return
	 */
	private boolean isBaseType(Object object) {
		if (object instanceof Byte) {
			return true;
		}
		if (object instanceof Short) {
			return true;
		}
		if (object instanceof Byte) {
			return true;
		}
		if (object instanceof Long) {
			return true;
		}
		if (object instanceof Float) {
			return true;
		}
		if (object instanceof Double) {
			return true;
		}
		if (object instanceof Boolean) {
			return true;
		}
		if (object instanceof Character) {
			return true;
		}
		if (object instanceof Integer) {
			return true;
		}
		return false;
	}
}
