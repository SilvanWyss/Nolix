/*
 * file:	Sequencer.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	30
 */

//package declaration
package ch.nolix.common.sequencer;

import ch.nolix.common.functional.IRunner;

//class
/**
 * A sequencer provides methods to control the program flow.
 */
public final class Sequencer {
	
	//static method
	public static final AfterAllMediator afterAllMilliseconds(int timeIntervalInMilliseconds) {
		return new AfterAllMediator(timeIntervalInMilliseconds);
	}
	
	public static final AfterAllMediator afterAllSeconds() {
		return new AfterAllMediator(1000);
	}
	
	//static method
	/**
	 * @param count
	 * @return new doer with the given count
	 * @throws Exception if the given count is negative
	 */
	public static final ForCountMediator forCount(int count) {
		return new ForCountMediator(count);
	}
	
	public static final void runInBackground(final IRunner runner) {
		new BackgroundThread(runner);
	}

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Sequencer() {}
}
