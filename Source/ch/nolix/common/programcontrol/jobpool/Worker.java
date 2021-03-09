//package declaration
package ch.nolix.common.programcontrol.jobpool;

import ch.nolix.common.errorcontrol.validator.Validator;

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
