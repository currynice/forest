package org.aerie.forest.core.element.rebar.bootable.timecrystal;

import java.time.LocalDateTime;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.ForestRebar;
import org.aerie.forest.core.element.rebar.ForestRebarLedalCheck;
import org.aerie.forest.core.element.rebar.bootable.ForestBootable;

/**
 * 
 * @description 时间晶振【单例】测试
 * @author falconTrotk
 * @company aerie
 * @date 2019年2月7日上午9:54:16
 */
public final class TimeCrystal extends ForestBootable {
	/**
	 * 时间 刷新频率【1秒刷新下一次】
	 */
	private final static int timeFrequency = 1000;
	/**
	 * 当前系统时间
	 */
	private static LocalDateTime systemTime;
	/**
	 * 单例
	 */
	private static TimeCrystal timeCrystal;

	private TimeCrystal(boolean allowBootFlag) {
		super(allowBootFlag, "时间晶振", new RunnableFunction() {

			@Override
			public void action() throws Exception {
				// 刷新时间
				systemTime = LocalDateTime.now();
				// nano为0到9位的数，补全并取出倒数9到6位（共三位）数及为毫秒数
				String nano = "000000000" + String.valueOf(systemTime.getNano());
				// 获取时间的毫秒
				// 负反馈稳定时间晶振
				Thread.sleep(timeFrequency - Integer.valueOf(nano.substring(nano.length() - 9, nano.length() - 6)));
				GlobalLogger.INSTANCE.getLOGGER().trace("刷新系统时间");
			}
		});

		synchronized (TimeCrystal.class) {
			if (singletonFlag == true) {
				throw new RuntimeException(forestRebarName + "受到反射攻击");
			}
			singletonFlag = true;
			GlobalLogger.INSTANCE.getLOGGER().info("【单例】" + forestRebarName + "初始化");
		}
		thread = new Thread(bootModule);

	}

	/**
	 * 
	 * @description 获得单例
	 * @return
	 * @throws Exception
	 */
	protected final static TimeCrystal getInstance() {
		/**
		 * 双重锁
		 */
		if (timeCrystal == null) {
			synchronized (TimeCrystal.class) {
				if (timeCrystal == null) {
					timeCrystal = new TimeCrystal(true);
				}
			}
		}
		return timeCrystal;
	}

	protected ForestRebar getForestRebar() {
		return null;

	}

	public LocalDateTime getSystemTime() {
		return systemTime;
	}

	/**
	 * 处理异常
	 */
	@Override
	protected void disposeException(Exception exception) {
//		ExceptionProcessor.INSTANCE.recordException("时间晶振中断", exception, new ExceptionDisposeFunction() {
//
//			@Override
//			public void action() {
//				// 设置中断标识
//				Thread.currentThread().interrupt();
//			}
//		});
	}

	@Override
	protected void beforeRun() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void afterRun() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean customAllowStart() {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * 添加校验法则
	 */
	public void addLedalCheck() {
		super.addLedalCheck();
		forestRebarLedalChecks.add(new ForestRebarLedalCheck() {

			@Override
			public void isLegal(ForestRebar forestRebar) {
				System.out.println("开始校验规则三");

			}
		});
	}

}
