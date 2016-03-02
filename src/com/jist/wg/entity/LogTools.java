package com.jist.wg.entity;

import java.util.logging.Logger;

/**
 * 作者：jist 时间：下午05:41:50 操作：TODO（描述操作原因）
 **/
public class LogTools {

	private static Logger log = Logger.getLogger("log");

	private LogTools() {

	}

	public static synchronized Logger getInstance() {

		if (log == null) {
			log = Logger.getLogger("log");
		}
		return log;
	}

}
