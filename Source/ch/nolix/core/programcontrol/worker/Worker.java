//package declaration
package ch.nolix.core.programcontrol.worker;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.core.skillapi.Startable;

//class
public abstract class Worker implements Startable {
	
	//attribute
	private boolean started;
	
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
