//package declaration
package ch.nolix.common.jobPool;

import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.futureAPI.IFuture;
import ch.nolix.common.validator.Validator;

//package-visible class
final class Future implements IFuture {
	
	//attribute
	private final JobWrapper jobWrapper;
	
	//constructor
	public Future(final JobWrapper jobWrapper) {
		this.jobWrapper = Validator.suppose(jobWrapper).thatIsNamed(VariableNameCatalogue.JOB).isNotNull().andReturn();
	}
	
	//method
	@Override
	public boolean caughtError() {
		return jobWrapper.caughtError();
	}
	
	//method
	@Override
	public Throwable getError() {
		return jobWrapper.getError();
	}
	
	//method
	@Override
	public boolean isFinished() {
		return jobWrapper.isFinished();
	}
	
	//method
	@Override
	public void waitUntilIsFinished() {
		jobWrapper.waitUntilIsFinished();
	}
}
