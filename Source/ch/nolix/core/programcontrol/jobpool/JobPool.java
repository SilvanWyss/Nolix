//package declaration
package ch.nolix.core.programcontrol.jobpool;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.main.SingleContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.programcontrolapi.futureuniversalapi.IFuture;

//class
/**
 * A {@link JobPool} runs jobs in the background.
 * A {@link JobPool} uses an optimal number of {@link Worker}s to run several jobs efficiently.
 * 
 * @author Silvan Wyss
 * @date 2019-04-14
 */
public final class JobPool {
	
	//constant
	private static final int OPTIMAL_WORKER_COUNT = 100;
	
	//multi-attributes
	private final LinkedList<Worker> workers = new LinkedList<>();
	private final LinkedList<JobWrapper> jobWrappers = new LinkedList<>();
	
	//method
	/**
	 * Enqueues the given job to the current {@link JobPool}.
	 * 
	 * @param job
	 * @return a {@link IFuture} for the given job.
	 * @throws ArgumentIsNullException if the given job is null.
	 */
	public IFuture enqueue(final IAction job) {
		
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
		return jobWrappers.containsAny(JobWrapper::isFresh);
	}
	
	//method
	/**
	 * @return true if the current {@link JobPool} is idle.
	 */
	public boolean isIdle() {
		return jobWrappers.containsAny();
	}
	
	//method
	synchronized SingleContainer<JobWrapper> getOptionalRefNextFreshJobWrapper() {
		return jobWrappers.getOptionalRefFirst(JobWrapper::isFresh);
	}
		
	//method
	synchronized void removeJobWrapper(final JobWrapper jobWrapper) {
		jobWrappers.removeFirst(jobWrapper);
	}
	
	//method
	synchronized void removeWorker(final Worker worker) {
		workers.removeFirst(worker);
	}
	
	//method
	private synchronized void createNewWorkerIfNeeded() {
		
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
	private int getWorkerCount() {
		return workers.getElementCount();
	}
	
	//method
	private synchronized boolean newWorkerIsNeeded() {
		
		final var workerCount = getWorkerCount();
		
		return
		jobWrappers.containsAny()
		&& workerCount < getOptimalWorkerCount()
		&& 10 * workerCount < jobWrappers.getElementCount();
	}
}
