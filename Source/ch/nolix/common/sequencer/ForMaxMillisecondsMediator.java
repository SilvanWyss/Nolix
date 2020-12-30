//package declaration
package ch.nolix.common.sequencer;

//own imports
import ch.nolix.common.constant.TimeUnitCatalogue;
import ch.nolix.common.functionapi.IBooleanGetter;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.NegativeArgumentException;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link ForMaxMillisecondsMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2019-11
 * @lines 120
 */
public final class ForMaxMillisecondsMediator {
	
	//attribute
	private final int maxDurationInMilliseconds;
	
	//static method
	/**
	 * @param maxDurationInMilliseconds
	 * @return a new {@link ForMaxMillisecondsMediator} for the given maxDurationInMilliseconds.
	 * @throws NegativeArgumentException if the given maxDurationInMilliseconds is negative. 
	 */
	static ForMaxMillisecondsMediator forMaxMilliseconds(final int maxDurationInMilliseconds) {
		
		//Creates and returns a new ForMaxMillisecondsMediator.
		return new ForMaxMillisecondsMediator(maxDurationInMilliseconds);
	}
	
	//static method
	/**
	 * @param maxDurationInSeconds
	 * @return a new {@link ForMaxMillisecondsMediator} for the given maxDurationInSeconds.
	 * @throws NegativeArgumentException if the given maxDurationInSeconds is negative. 
	 */
	static ForMaxMillisecondsMediator forMaxSeconds(final int maxDurationInSeconds) {
		
		//Asserts that the given maxDurationInSeconds is not negative.
		Validator.assertThat(maxDurationInSeconds).thatIsNamed("max duration in seconds").isNotNegative();
		
		//Creates and returns a new ForMaxMillisecondsMediator.
		return new ForMaxMillisecondsMediator(maxDurationInSeconds * TimeUnitCatalogue.MILLISECONDS_PER_SECOND);
	}
	
	//constructor
	/**
	 * Creates a new {@link ForMaxMillisecondsMediator} for the given maxDurationInMilliseconds.
	 * 
	 * @param maxDurationInMilliseconds
	 * @throws NegativeArgumentException if the given maxDurationInMilliseconds is negative. 
	 */
	private ForMaxMillisecondsMediator(final int maxDurationInMilliseconds) {
		
		//Asserts that the given maxDurationInMilliseconds is not negative.
		Validator.assertThat(maxDurationInMilliseconds).thatIsNamed("max duration in milliseconds").isNotNegative();
		
		//Sets the maxDurationInMilliseconds of the current ForMaxMillisecondsMediator.
		this.maxDurationInMilliseconds = maxDurationInMilliseconds;
	}
	
	//method
	/**
	 * Creates 
	 * 
	 * @param condition
	 * @return a new {@link AsLongAsMediator}
	 * for the maxDurationInMilliseconds of the current {@link ForMaxMillisecondsMediator} and for the given condition.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public AsLongAsMediator asLongAs(final IBooleanGetter condition) {
		
		//Asserts that the given condition is not null.
		Validator.assertThat(condition).thatIsNamed("condition").isNotNull();
		
		final var startTimeInMilliseconds = System.currentTimeMillis();
		final var endTimeInMilliseconds = startTimeInMilliseconds + maxDurationInMilliseconds;
		
		return new AsLongAsMediator(() -> System.currentTimeMillis() < endTimeInMilliseconds || condition.getOutput());
	}
	
	//method
	/**
	 * @param condition
	 * @return a new {@link AsLongAsMediator}
	 * for the maxDurationInMilliseconds of the current {@link ForMaxMillisecondsMediator} and for the given condition.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public AsLongAsMediator until(final IBooleanGetter condition) {
		
		//Calls other method.
		return asLongAs(IBooleanGetter.createNegator(condition));
	}
	
	//method
	/**
	 * Waits until the maxDurationInMilliseconds of the current {@link ForMaxMillisecondsMediator} is reached
	 * or as long as the given condition is fulfilled.
	 * 
	 * @param condition
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public void waitAsLongAs(final IBooleanGetter condition) {
		
		final var startTimeInMilliseconds = System.currentTimeMillis();
		final var endTimeInMilliseconds = startTimeInMilliseconds + maxDurationInMilliseconds;
		
		Sequencer.waitAsLongAs(() -> System.currentTimeMillis() < endTimeInMilliseconds && condition.getOutput());
	}
	
	//method
	/**
	 * Waits until the maxDurationInMilliseconds of the current {@link ForMaxMillisecondsMediator} is reached
	 * or until the given condition is fulfilled.
	 * 
	 * @param condition
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public void waitUntil(final IBooleanGetter condition) {
		
		//Calls other method.
		waitAsLongAs(IBooleanGetter.createNegator(condition));
	}
}
