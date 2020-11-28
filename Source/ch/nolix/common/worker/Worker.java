//package declaration
package ch.nolix.common.worker;

import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.skillapi.Startable;

//class
public abstract class Worker implements Startable {
	
	//attribute
	private boolean started = false;
	
	//method
	@Override
	public final boolean isStarted() {
		return started;
	}
	
	//method
	@Override
	public final void start() {
		
		setStarted();
		
		Sequencer.runInBackground(this::run);
	}
	
	//method declaration
	protected abstract void run();
	
	//method
	private void assertIsNotStarted() {
		if (isStarted()) {
			throw new InvalidArgumentException(this, "is already started");
		}
	}
	
	//method
	private void setStarted() {
		
		assertIsNotStarted();
		
		started = true;
	}
}
