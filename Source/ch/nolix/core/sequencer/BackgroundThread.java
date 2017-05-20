/*
 * file:	BackgroundThread.java
 * author:	Silvan Wyss
 * month:	2016-09
 * lines:	10
 */

//package declaration
package ch.nolix.core.sequencer;

import ch.nolix.core.functionInterfaces.IRunner;

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
