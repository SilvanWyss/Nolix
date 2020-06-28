//package declaration
package ch.nolix.common.jobPool;

//own imports
import ch.nolix.common.functionAPI.IAction;
import ch.nolix.common.futureAPI.IFuture;

//class
/**
 * @author Silvan Wyss
 * @month 2019-04
 * @lines 40
 */
public final class CentralJobPool {
	
	//static attribute
	private static final JobPool jobPool = new JobPool();
	
	//static method
	/**
	 * @return true if the {@link CentralJobPool} contains waiting jobs.
	 */
	public static boolean containsWaitingJobs() {
		return jobPool.containsWaitingJobs();
	}
	
	//static method
	/**
	 * Enqueues the given job to the {@link CentralJobPool}.
	 * 
	 * @param job
	 * @return a {@link IFuture} for the given job.
	 * @throws ArgumentIsNullException if the given job is null.
	 */
	public static IFuture enqueue(final IAction job) {
		return jobPool.enqueue(job);
	}
	
	//visibility-reducing constructor
	/**
	 * Avoids that an instance of the {@link CentralJobPool} can be created.
	 */
	private CentralJobPool() {}
}
