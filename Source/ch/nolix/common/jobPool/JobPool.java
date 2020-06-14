//package declaration
package ch.nolix.common.jobPool;

import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionAPI.IFunction;
import ch.nolix.common.futureAPI.IFuture;

//class
/**
 * A {@link JobPool} runs jobs in the background.
 * A {@link JobPool uses an optimal number of {@link Thread}s to run several jobs efficiently.
 * 
 * @author Silvan Wyss
 * @month 2019-04
 * @lines 80
 */
public final class JobPool {
	
	//constant
	private static final int OPTIMAL_WORKER_COUNT = 100;
	
	//multi-attributes
	private final LinkedList<JobWrapper> jobWrappers = new LinkedList<>();
	private final LinkedList<Worker> workers = new LinkedList<>();
	
	//method
	/**
	 * Enqueues the given job to the current {@link JobPool}.
	 * 
	 * @param job
	 * @return a {@link IFuture} for the given job.
	 * @throws ArgumentIsNullException if the given job is null.
	 */
	public IFuture enqueue(final IFunction job) {
		
		final var jobWrapper = new JobWrapper(job);
		jobWrappers.addAtEnd(jobWrapper);
		createNewWorkerIfNeeded();
		
		return new Future(jobWrapper);
	}
	
	//method
	/**
	 * @return true if the current {@link JobPool} contains waiting jobs.
	 */
	public boolean containsWaitingJobs() {
		return jobWrappers.containsAny();
	}

	//method
	synchronized JobWrapper removeAndGetNextJobWrapperOrNull() {
		
		//Handles the case that the current JobPool does not contain job wrappers.
		if (jobWrappers.isEmpty()) {
			return null;
		}
		
		//Handles the case that the current JobPool contains job wrappers.
		return jobWrappers.removeAndGetRefLast();
	}
	
	//method
	void removeWorker(final Worker worker) {
		workers.removeFirst(worker);
	}
	
	//method
	private void createNewWorkerIfNeeded() {
		
		//Handles the case that a new worker is needed.
		if (newWorkerIsNeeded()) {
			workers.addAtEnd(new Worker(this));
		}
	}
	
	//method
	private int getOptimalWorkerCount() {
		return OPTIMAL_WORKER_COUNT;
	}
	
	//method
	private boolean newWorkerIsNeeded() {
		return
		jobWrappers.containsAny()
		&& workers.getElementCount() < getOptimalWorkerCount()
		&& 10 * workers.getElementCount() < jobWrappers.getElementCount();
	}
}
