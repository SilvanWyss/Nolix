//package declaration
package ch.nolix.core.sequencer;

//own imports
import ch.nolix.core.functionInterfaces.IBooleanGetter;
import ch.nolix.core.functionInterfaces.IRunner;
import ch.nolix.core.util.Validator;

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
