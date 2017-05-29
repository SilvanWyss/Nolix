//package declaration
package ch.nolix.core.sequencer;

//own imports
import ch.nolix.core.functionInterfaces.IBooleanGetter;
import ch.nolix.core.functionInterfaces.IRunner;
import ch.nolix.core.validator.Validator;

//class
public class AfterAllMediator {

	//attribute
	final int timeIntervalInMilliseconds;
	
	//constructor
	public AfterAllMediator(final int timeIntervalInMilliseconds) {
		
		Validator.throwExceptionIfValueIsNegative("time interval in milliseconds", timeIntervalInMilliseconds);
		
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
	}
	
	//method
	public final AsLongMediator asLong(final IBooleanGetter booleanGetter) {
		return new AsLongMediator(this.timeIntervalInMilliseconds, booleanGetter);
	}
	
	public final Runner runInBackground(IRunner runner) {
		return new Runner(timeIntervalInMilliseconds, ()->{return true;},runner);
	}
}
