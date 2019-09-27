package org.aerie.forest.core.element.rebar.bootable;
//package org.aerie.forest.core.element.rebar.bootable;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.aerie.forest.core.element.brick.exception.CustomException;
//import org.aerie.forest.core.element.brick.log.GlobalLogger;
//import org.aerie.forest.core.element.rebar.processer.exception.ExceptionProcessor;
//
///**
// * 
// * @description 启动器的管理器
// * @author trotkFalcon
// * @company aerie
// * @date 2019年8月25日上午8:24:26
// * @version 1.0.1
// */
//public enum BootSupervisor {
//	INSTANCE;
//	/**
//	 * 启动队列
//	 */
//	private List<ForestBootable> bootables = new ArrayList<>();
//
//	private BootSupervisor() {
//		// Do Nothing
//	}
//
//	/**
//	 * 
//	 * @description 添加启动器进入启动管理队列【如果没有抛异常，就是加进去的启动器已经启动成功，无论是不是之前已经启动】
//	 * @author trotkFalcon
//	 * @company aerie
//	 * @date 2019年8月25日下午1:10:00
//	 * @version 1.0.1
//	 * @param bootableToAdd
//	 * @throws Exception
//	 */
//	public void addBoot(ForestBootable bootableToAdd) throws Exception {
//		Optional.ofNullable(bootableToAdd).orElseThrow(() -> new CustomException("启动器的管理器禁止添加空启动器"));
//		// 判重
//		for (ForestBootable bootable : bootables) {
//			if (bootable.hashCode() == bootableToAdd.hashCode()) {
//				if (bootable.hasStarted() == true) {
//					GlobalLogger.INSTANCE.getLOGGER().info("该启动器已经启动，无法重复启动");
//					return;
//				} else {
//					// 存在未启动的直接启动
//					bootable.run();
//					return;
//				}
//			}
//		}
//		// 添加进启动队列立马启动
//		// 校验成功添加进启动队列
//		bootables.add(bootableToAdd);
//		// 立马启动
//		bootableToAdd.run();
//	}
//
//	/**
//	 * 
//	 * @description 启动所以的启动器
//	 * @author trotkFalcon
//	 * @company aerie
//	 * @date 2019年8月25日下午4:33:42
//	 * @version 1.0.1
//	 */
//	public void startAllBoot() {
//		for (int num = bootables.size(); num > 0; num--) {
//			// 为空的启动器直接干掉
//			if (bootables.get(num) == null) {
//				bootables.remove(num);
//			} else {
//				try {
//					bootables.get(num).run();
//				} catch (Exception e) {
//					// 遇到无法启动直接打印异常然后启动下一个
////					ExceptionProcessor.INSTANCE.recordException("存在无法启动的启动器", e, ExceptionGradeEnum.WARN);
//				}
//			}
//		}
//	}
//
//	/**
//	 * 
//	 * @description 获得已经启动的数量
//	 * @author trotkFalcon
//	 * @company aerie
//	 * @date 2019年8月25日下午8:59:29
//	 * @version 1.0.1
//	 * @return
//	 */
//	public Long getNumOfStarted() {
//		return bootables.stream().filter(p1 -> p1 != null && p1.hasStarted() == true).count();
//	}
//
//}
