//package declaration
package ch.nolix.common.validator;

//Java import
import java.lang.reflect.Method;

//class
public final class ExtendedMethodMediator extends MethodMediator {
	
	//constructor
	ExtendedMethodMediator(final Method argument) {
		super(argument);
	}
	
	//method
	public MethodMediator thatIsNamed(final String argumentName) {
		return new MethodMediator(argumentName, getRefArgument());
	}
}
