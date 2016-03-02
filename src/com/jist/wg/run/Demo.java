package com.jist.wg.run;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jist.wg.entity.LogTools;
import com.jist.wg.service.InputAndOutput;

/**
	作者：jist
	时间：下午12:43:00
	操作：TODO（描述操作原因）
 **/
public class Demo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main( String[] args ) throws IOException {

		Logger log = LogTools.getInstance();
		log.setLevel(Level.WARNING);
		InputAndOutput id = new InputAndOutput();
		id.calculateResults();

	}

}
