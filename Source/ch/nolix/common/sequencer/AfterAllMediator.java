//package declaration
package ch.nolix.common.sequencer;

//own imports
import ch.nolix.common.functional.IBooleanGetter;
import ch.nolix.common.functional.IRunner;
import ch.nolix.common.util.Validator;

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
