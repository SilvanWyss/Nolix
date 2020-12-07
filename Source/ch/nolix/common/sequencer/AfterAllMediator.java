//package declaration
package ch.nolix.common.sequencer;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.functionapi.IAction;
import ch.nolix.common.functionapi.IBooleanGetter;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link AfterAllMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-01-01
 * @lines 220
 */
public final class AfterAllMediator {

	//attribute
	private final int timeIntervalInMilliseconds;
	
	//optional attributes
	private final Integer maxRunCount;
	private final IBooleanGetter condition;
	
	//visibility-reduced constructor
	/**
	 * Creates a new {@link AfterAllMediator} with the given condition and time interval in milliseconds.
	 * 
	 * @param condition
	 * @param timeIntervalInMilliseconds
	 * @throws ArgumentIsNullException if the given condition is null.
	 * @throws NegativeArgumentException if the given timeIntervalInMilliseconds is negative.
	 */
	AfterAllMediator(final IBooleanGetter condition, final int timeIntervalInMilliseconds) {
		
		Validator.assertThat(condition).thatIsNamed(VariableNameCatalogue.CONDITION).isNotNull();
		Validator.assertThat(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseconds").isNotNegative();
		
		maxRunCount = null;
		this.condition = condition;
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
	}
	
	//constructor
	/**
	 * Creates a new {@link AfterAllMediator} with the given maxRunCount, condition and time interval in milliseconds.
	 * 
	 * @param maxRunCount
	 * @param condition
	 * @param timeIntervalInMilliseconds
	 * @throws NegativeArgumentException if the given max run count is negative.
	 * @throws ArgumentIsNullException if the given condition is null.
	 * @throws NegativeArgumentException if the given timeIntervalInMillisecondss is negative.
	 */
	AfterAllMediator(
		final int maxRunCount,
		final IBooleanGetter condition,
		final int timeIntervalInMilliseconds
	) {
		
		Validator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();
		Validator.assertThat(condition).thatIsNamed("condition").isNotNull();
		Validator.assertThat(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseconds").isNotNegative();
		
		this.maxRunCount = maxRunCount;
		this.condition = condition;
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
	}
	
	//method
	/**
	 * Lets the current {@link AfterAllMediator} run the given job.
	 * 
	 * @param job
	 */
	public void run(final IAction job) {
		
		//Handles the case that the current AfterAllMediator does not have a max run count.
		if (!hasMaxRunCount()) {
			runWhenDoesNotHaveMaxRunCount(job);
		}
		
		//Handles the case that the current AfterAllMediator has a max run count.
		else {
			runWhenHasMaxRunCount(job);
		}
	}
	
	//method
	/**
	 * Lets the current {@link AfterAllMediator} run the given job in background.
	 * 
	 * @param job
	 * @return a new {@link Future}.
	 * @throws ArgumentIsNullException if the given job is null.
	 */
	public Future runInBackground(final IAction job) {
		
		//Handles the case that the current AfterAllMediator does not have a max count.
		if (!hasMaxRunCount()) {
			return runInBackgroundWhenDoesNotHaveMaxRunConunt(job);
		}
		
		//Handles the case that the current AfterAllMediator has a max count.
		return runInBackgroundWhenHasMaxRunConunt(job);
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link AfterAllMediator} does not have condition.
	 */
	private void assertHasCondition() {
		if (!hasCondition()) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.CONDITION);
		}
	}
	
	//method
	/**
	 * @return true if the current {@link AfterAllMediator} has a condition.
	 */
	private boolean hasCondition() {
		return (condition != null);
	}
	
	//method
	/**
	 * @return true if the current {@link AfterAllMediator} has a max run count.
	 */
	private boolean hasMaxRunCount() {
		return (maxRunCount != null);
	}
	
	//method
	/**
	 * Lets the current {@link AfterAllMediator} run the given job in background
	 * for the case when the current {@link AfterAllMediator} does not have a max run count.
	 * 
	 * @param job
	 * @return a new {@link Future}.
	 * @throws ArgumentIsNullException if the given job is null.
	 */
	private Future runInBackgroundWhenDoesNotHaveMaxRunConunt(final IAction job) {
		
		//Handles the case that the current AfterAllMediator does not have a condition.
		if (!hasCondition()) {
			return new Future(new JobRunner(job, timeIntervalInMilliseconds, () -> true));
		}
		
		//Handles the case that the current AfterAllMediator has a condition.
		return new Future(new JobRunner(job, condition, timeIntervalInMilliseconds));
	}
	
	//method
	/**
	 * Lets the current {@link AfterAllMediator} run the given job in background
	 * for the case when the current {@link AfterAllMediator} has a max run count.
	 * 
	 * @param job
	 * @return a new {@link Future}.
	 * @throws ArgumentIsNullException if the given job is null.
	 */
	private Future runInBackgroundWhenHasMaxRunConunt(final IAction job) {
		
		//Handles the case that the current AfterAllMediator does not have a condition.
		if (!hasCondition()) {
			return new Future(new JobRunner(job, maxRunCount, timeIntervalInMilliseconds));
		}
		
		//Handles the case that the current AfterAllMediator has a condition.
		return new Future(new JobRunner(job, maxRunCount, condition, timeIntervalInMilliseconds));
	}
	
	//method
	/**
	 * Lets the current {@link AfterAllMediator} run the given job
	 * for the case when the current {@link AfterAllMediator} does not have a max run count.
	 * 
	 * @param job
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link AfterAllMediator} does not have condition.
	 */
	private void runWhenDoesNotHaveMaxRunCount(IAction job) {
		
		assertHasCondition();
		
		while (condition.getOutput()) {
			job.run();
			Waiter.waitForMilliseconds(timeIntervalInMilliseconds);
		}		
	}
	
	//method
	/**
	 * Lets the current {@link AfterAllMediator} run the given job
	 * for the case when the current {@link AfterAllMediator} has a max run count.
	 * 
	 * @param job
	 */
	private void runWhenHasMaxRunCount(IAction job) {
		
		//Handles the case that the current AfterAllMediator does not have a condition.
		if (!hasCondition()) {
			for (int i = 1; i <= maxRunCount; i++) {
				job.run();
				Waiter.waitForMilliseconds(timeIntervalInMilliseconds);
			}
		}
		
		//Handles the case that the current AfterAllMediator has a condition.
		else {
			for (int i = 1; i <= maxRunCount; i++) {
				
				if (!condition.getOutput()) {
					break;
				}
				
				job.run();
				Waiter.waitForMilliseconds(timeIntervalInMilliseconds);
			}
		}
	}
}
