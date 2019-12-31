//package declaration
package ch.nolix.common.jobPool;

import ch.nolix.common.validator.Validator;

//class
final class Worker extends Thread {
	
	//attribute
	private final JobPool parentJobPool;
	
	//constructor
	public Worker(final JobPool parentJobPool) {
		
		this.parentJobPool = Validator.suppose(parentJobPool).thatIsNamed("parent job bool").isNotNull().andReturn();
	
		start();
	}
	
	//method
	@Override
	public void run() {
		
		var dryRuns = 0;	
		while (true) {
			
			final var jobWrapper = parentJobPool.removeAndGetNextJobWrapperOrNull();
			
			if (jobWrapper == null) {
				
				if (dryRuns > 1000000) {
					break;
				}
				
				dryRuns++;
			}
			else {
				jobWrapper.run();
				dryRuns = 0;
			}
		}
		
		parentJobPool.removeWorker(this);
	}
}
