//package declaration
package ch.nolix.core.sequencer;

//own imports
import ch.nolix.core.skillAPI.IFuture;
import ch.nolix.core.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 60
 */
public final class Future implements IFuture {
	
	//attribute
	private final JobRunner jobRunner;
	
	//package-visible constructor
	/**
	 * Creates a new {@link Future} with the given jobRunner.
	 * 
	 * @param jobRunner
	 * @throws NullArgumentException if the given jobRunner is null.
	 */
	Future(final JobRunner jobRunner) {
		
		//Checks if the given jobRunner is not null.
		Validator.suppose(jobRunner).isOfType(JobRunner.class);
		
		//Sets the jobRunner of the current Future.
		this.jobRunner = jobRunner;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean caughtError() {
		return jobRunner.caughtError();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Throwable getError() {
		return jobRunner.getError();
	}
	
	//method
	/**
	 * @return the number of finished jobs of the current {@IFuture}.
	 */
	public int getFinishedJobCount() {
		return jobRunner.getFinishedJobCount();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFinished() {
		return jobRunner.isFinished();
	}
}
