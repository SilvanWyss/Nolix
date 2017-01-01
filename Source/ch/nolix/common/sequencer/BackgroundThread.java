/*
 * file:	BackgroundThread.java
 * author:	Silvan Wyss
 * month:	2016-09
 * lines:	10
 */

//package declaration
package ch.nolix.common.sequencer;

import ch.nolix.common.functional.IRunner;

final class BackgroundThread extends Thread {

	private final IRunner runner;
	
	public BackgroundThread(final IRunner runner) {
		this.runner = runner;
		start();
	}
	
	public final void run() {
		runner.run();
	}
}
