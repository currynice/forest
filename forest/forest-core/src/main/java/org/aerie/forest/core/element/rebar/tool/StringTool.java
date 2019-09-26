package org.aerie.forest.core.element.rebar.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @description 字符串工具类
 * @author falconTrotk
 * @company aerie
 * @date 2019年2月10日下午10:32:46
 * @version 1.0.1
 */
public enum StringTool {
	INSTANCE;
	/**
	 * 
	 * @description 获取字符在字符串中的位置
	 * @param string
	 * @param appoint
	 * @param index
	 * @return
	 */
	public int getCharacterPosition(String string, String appoint, int index) {
		// 这里是获取appoint符号的位置
		Matcher slashMatcher = Pattern.compile(appoint).matcher(string);
		int mIdx = 0;
		while (slashMatcher.find()) {
			mIdx++;
			// 当appoint符号第index次出现的位置
			if (mIdx == index) {
				return slashMatcher.start();
			}
		}
		return -1;
	}
}
