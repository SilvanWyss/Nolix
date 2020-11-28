//package declaration
package ch.nolix.common.jobpool;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.futureapi.IFuture;
import ch.nolix.common.validator.Validator;

//class
final class Future implements IFuture {
	
	//attribute
	private final JobWrapper jobWrapper;
	
	//constructor
	public Future(final JobWrapper jobWrapper) {
		this.jobWrapper = Validator.assertThat(jobWrapper).thatIsNamed(VariableNameCatalogue.JOB).isNotNull().andReturn();
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
	
	//method
	@Override
	public void waitUntilIsFinished(final int timeoutInMilliseconds) {
		jobWrapper.waitUntilIsFinished(timeoutInMilliseconds);
	}
}
