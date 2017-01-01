//package declaration
package ch.nolix.common.sequencer;

//own imports
import ch.nolix.common.functional.IBooleanGetter;
import ch.nolix.common.functional.IRunner;
import ch.nolix.common.util.Validator;

//package-visible class
public class AsLongMediator {

	//attributes
	private final int timeIntervalInMilliseconds;
	private final IBooleanGetter booleanGetter;
	
	//constructor
	public AsLongMediator(final int timeIntervalInMilliseconds, final IBooleanGetter booleanGetter) {
		
		Validator.throwExceptionIfValueIsNegative("time interval in milliseconds", timeIntervalInMilliseconds);
		Validator.throwExceptionIfValueIsNull("boolean getter", booleanGetter);
		
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
		this.booleanGetter = booleanGetter;
	}
	
	//method
	public final void run(IRunner runner) {
		new Runner(timeIntervalInMilliseconds, booleanGetter, runner);
	}
}
