//package declaration
package ch.nolix.core.jobPool;

//own imports
import ch.nolix.core.functionAPI.IFunction;
import ch.nolix.core.futureAPI.IFuture;

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
	 * @throws NullArgumentException if the given job is null.
	 */
	public static IFuture enqueue(final IFunction job) {
		return jobPool.enqueue(job);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link CentralJobPool} can be created.
	 */
	private CentralJobPool() {}
}
