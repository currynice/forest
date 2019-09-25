package org.aerie.forest.core.element.rebar.singleton.bootable.uuidpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.aerie.forest.core.element.brick.log.GlobalLogger;
import org.aerie.forest.core.element.rebar.singleton.bootable.ForestBootable;

/**
 * 
 * @description UUID池
 * @author falconTrotk
 * @company aerie
 * @date 2019年7月31日上午9:59:19
 * @version 1.0.1
 */
public final class UuidPool extends ForestBootable {
	/**
	 * 存储池
	 */
	private static List<String> uuids = new ArrayList<>();
	/**
	 * 容量
	 */
	private static int capacity = 20;
	/**
	 * 负反馈间隔时间【ms】
	 */
	private static int interval = 10000;
	/**
	 * 随机
	 */
	private static Random random = new Random();
	/**
	 * 字符库
	 */
	private final static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
			"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
			"S", "T", "U", "V", "W", "X", "Y", "Z" };

	/**
	 * 单例
	 */
	private static UuidPool uuidPool;

	private UuidPool(boolean allowBootFlag) {
		super(allowBootFlag, "UUID池", new RunnableFunction() {

			@Override
			public void action() throws Exception {
				GlobalLogger.INSTANCE.getLOGGER().debug("UUID池启动");
				// 如果库存不足补充库存【如果库存清空启动内部自调节】
				if (uuids.size() < capacity / 3) {
					if (uuids.isEmpty()) {
						// 时间间隔缩短10%
						interval = interval / 10 * 9;
						// 库存扩容5
						capacity += 5;
						GlobalLogger.INSTANCE.getLOGGER().debug("UUID池扩容");
					}
				} else if (uuids.size() > capacity / 3 * 2) {
					// 逆向调节
					interval *= 1.1;
					capacity -= 1;
				}
				// 补充库存
				supplement();
				Thread.sleep(interval);
			}
		});
		synchronized (UuidPool.class) {
			if (this.singletonFlag == true) {
				throw new RuntimeException("时间晶振受到反射攻击");
			}
			this.singletonFlag = true;
			GlobalLogger.INSTANCE.getLOGGER().info("【单例】时间晶振初始化");
		}
		thread = new Thread(bootModule);
	}

	/**
	 * 
	 * @description 获得单例
	 * @return
	 * @throws Exception
	 */
	static UuidPool getInstance() {
		if (uuidPool == null) {
			synchronized (UuidPool.class) {
				if (uuidPool == null) {
					uuidPool = new UuidPool(true);
				}
			}
		}
		return uuidPool;
	}

	/**
	 * 
	 * @description 增补数据
	 */
	private static void supplement() {
		while (uuids.size() < capacity) {
			uuids.add(UUID.randomUUID().toString());
		}
		GlobalLogger.INSTANCE.getLOGGER().debug("UUID池增补完成");
	}

	/**
	 * 
	 * @description 获取UUID,并将其从存储池中移除
	 * @return
	 */
	public synchronized String getValue() {
		if (uuids.isEmpty()) {
			return UUID.randomUUID().toString();
		}
		String result = uuids.get(0);
		uuids.remove(0);
		return result;
	}

	/**
	 * 
	 * @description 获得短八位UUID【集群服务不可用】
	 * @return
	 */
	public synchronized String getUUIDEightBit() {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			String str = getValue().replace("-", "").substring(i * 4, i * 4 + 4);
			int index = Integer.parseInt(str, 16);
			stringBuffer.append(chars[index % 0x3E]);
		}
		return stringBuffer.toString();
	}

	/**
	 * 
	 * @description 获得短十二位UUID【集群服务不可用】
	 * @return
	 */
	public synchronized String getUUIDTwelveBit() {
		StringBuffer stringBuffer = new StringBuffer("-" + getUUIDEightBit());
		stringBuffer.insert(8, random.nextInt(9)).insert(5, "-").insert(5, random.nextInt(9));
		return stringBuffer.toString();
	}

	/**
	 * 处理异常
	 */
	@Override
	protected void disposeException(Exception exception) {
//		ExceptionProcessor.INSTANCE.recordException("UUID池", exception, new ExceptionDisposeFunction() {
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

}
