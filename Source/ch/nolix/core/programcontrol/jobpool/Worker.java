//package declaration
package ch.nolix.core.programcontrol.jobpool;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;

//class
final class Worker extends Thread {
	
	//attribute
	private final JobPool parentJobPool;
	
	//constructor
	public Worker(final JobPool parentJobPool) {
		
		Validator.assertThat(parentJobPool).thatIsNamed("parent job bool");
		
		this.parentJobPool = parentJobPool;
	
		start();
	}
	
	//method
	@Override
	public void run() {
			
		while (true) {
			
			final var jobWrapperContainer = parentJobPool.getOptionalRefNextFreshJobWrapper();
			
			if (jobWrapperContainer.isEmpty()) {
				break;
			}
			
			final var jobWrapper = jobWrapperContainer.getRefElement();
			jobWrapper.run();
			parentJobPool.removeJobWrapper(jobWrapper);
		}
		
		parentJobPool.removeWorker(this);
	}
}
