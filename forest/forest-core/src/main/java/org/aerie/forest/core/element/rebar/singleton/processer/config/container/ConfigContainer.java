package org.aerie.forest.core.element.rebar.singleton.processer.config.container;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.aerie.forest.core.element.brick.exception.CustomException;
import org.aerie.forest.core.element.rebar.unsingleton.tool.ClassTool;

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
			if (!ClassTool.INSTANCE.isBaseType(object) && !(object instanceof String) && !(object instanceof Enum)) {
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
}
