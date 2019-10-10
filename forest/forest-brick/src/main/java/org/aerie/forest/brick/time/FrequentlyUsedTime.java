package org.aerie.forest.brick.time;

/**
 * 
 * @description 常用时间
 * @author falconTrotk
 * @company aerie
 * @date 2019年10月10日下午3:25:27
 * @version 1.0.1
 */
public enum FrequentlyUsedTime {
	// 一秒
	ONE_SECOND(1000),
	// 两秒
	TWO_SECONDS(1000 * 2),
	// 五秒
	FIVE_SECONDS(1000 * 5),
	// 十秒
	TEN_SECONDS(1000 * 10),
	// 三十秒
	THIRTY_SECONDS(1000 * 30),
	// 一分钟
	ONE_MINUTE(1000 * 60),
	// 五分钟
	FIVE_MINUTE(1000 * 60 * 5),
	// 三十分钟
	THIRTY_MINUTE(1000 * 60 * 30),
	// 一小时
	ONE_HOUR(1000 * 60 * 60),
	// 六小时
	SIX_HOUR(1000 * 60 * 60 * 6),
	// 十二小时
	TWELVE_HOUR(1000 * 60 * 60 * 12),
	// 一天
	ONE_DAY(1000 * 60 * 60 * 24),
	// 一周
	ONE_WEEK(1000 * 60 * 60 * 24 * 7);
	/**
	 * 时间
	 */
	private long durationTime;

	private FrequentlyUsedTime(long durationTime) {
		this.durationTime = durationTime;
	}

	public long getDurationTime() {
		return durationTime;
	}

	
}
