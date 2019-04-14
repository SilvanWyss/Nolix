//package declaration
package ch.nolix.core.jobPool;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.futureAPI.IFuture;
import ch.nolix.core.validator.Validator;

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
