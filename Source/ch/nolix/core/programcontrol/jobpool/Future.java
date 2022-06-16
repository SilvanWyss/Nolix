//package declaration
package ch.nolix.core.programcontrol.jobpool;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.name.LowerCaseCatalogue;
import ch.nolix.core.programcontrol.futureuniversalapi.IFuture;

//class
final class Future implements IFuture {
	
	//attribute
	private final JobWrapper jobWrapper;
	
	//constructor
	public Future(final JobWrapper jobWrapper) {
		
		GlobalValidator.assertThat(jobWrapper).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();
		
		this.jobWrapper = jobWrapper;
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
