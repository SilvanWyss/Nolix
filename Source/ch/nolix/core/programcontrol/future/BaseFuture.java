//package declaration
package ch.nolix.core.programcontrol.future;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programcontrol.futureuniversalapi.IFuture;

//class
/**
 * @author Silvan Wyss
 * @date 2022-06-18
 */
public abstract class BaseFuture implements IFuture {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final boolean isFinishedSuccessfully() {
		return (isFinished() && !caughtError());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void waitUntilIsFinishedSuccessfully() {
		
		waitUntilIsFinished();
		
		handleProbableError();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void waitUntilIsFinishedSuccessfully(final int timeoutInMilliseconds) {
		
		waitUntilIsFinished(timeoutInMilliseconds);
		
		handleProbableError();
	}
	
	//method
	private void handleError() {
		
		if (getError().getMessage() == null || getError().getMessage().isBlank()) {
			throw new InvalidArgumentException(this, "has caught a '" + getError().getClass().getName() + "'");
		}
		
		throw
		new InvalidArgumentException(
			this,
			"has caught the error '" + getError().getClass().getName() + ": " + getError().getMessage() + "'"
		);
	}
	
	//method
	private void handleProbableError() {
		if (caughtError()) {
			handleError();
		}
	}
}
