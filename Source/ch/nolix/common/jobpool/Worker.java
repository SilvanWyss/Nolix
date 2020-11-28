//package declaration
package ch.nolix.common.jobpool;

//own import
import ch.nolix.common.validator.Validator;

//class
final class Worker extends Thread {
	
	//attribute
	private final JobPool parentJobPool;
	
	//constructor
	public Worker(final JobPool parentJobPool) {
		
		this.parentJobPool = Validator.assertThat(parentJobPool).thatIsNamed("parent job bool").isNotNull().andReturn();
	
		start();
	}
	
	//method
	@Override
	public void run() {
			
		while (true) {
			
			final var jobWrapperContainer = parentJobPool.getRefNextFreshJobWrapperOptionally();
			
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
