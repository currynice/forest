package org.aerie.forest.rebar.tool.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.tool.ForestTool;

/**
 * 
 * @description 字符串工具类
 * @author falconTrotk
 * @company aerie
 * @date 2019年2月10日下午10:32:46
 * @version 1.0.1
 */
public final class StringTool extends ForestTool {

	/**
	 * 单例
	 */
	private static StringTool stringTool;

	private StringTool() {
		synchronized (StringTool.class) {
			if (singletonFlag == true) {
				throw new RuntimeException(forestRebarName + "受到反射攻击");
			}
			singletonFlag = true;
			GlobalLogger.INSTANCE.getLOGGER().info("【单例】" + forestRebarName + "初始化");
		}
	}

	/**
	 * 
	 * @description 获得单例
	 * @return
	 * @throws Exception
	 */
	protected final static StringTool getInstance() {
		/**
		 * 双重锁
		 */
		if (stringTool == null) {
			synchronized (StringTool.class) {
				if (stringTool == null) {
					stringTool = new StringTool();
				}
			}
		}
		return stringTool;
	}

	/**
	 * 
	 * @description 获取字符在字符串中第index次出现的位置
	 * @author falconTrotk
	 * @company aerie
	 * @date 2019年10月10日下午5:39:42
	 * @version 1.0.1
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
