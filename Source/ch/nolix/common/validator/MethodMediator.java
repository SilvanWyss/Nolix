//package declaration
package ch.nolix.common.validator;

//Java import
import java.lang.reflect.Method;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;

//class
public class MethodMediator extends ArgumentMediator<Method> {
	
	//constructor
	MethodMediator(final Method argument) {
		super(VariableNameCatalogue.METHOD, argument);
	}
	
	//constructor
	MethodMediator(final String argumentName, final Method argument) {
		super(argumentName, argument);
	}
	
	//method
	public void hasParametersOfTypeOnly(final Class<String> type) {
		
		if (type == null) {
			throw new ArgumentIsNullException(VariableNameCatalogue.TYPE);
		}
		
		isNotNull();
		
		for (final var p : getRefArgument().getParameters()) {
			if (!p.getType().isAssignableFrom(type)) {
				throw
				new InvalidArgumentException(
					getRefArgument(),
					"has a parameter '" + p.getName() + "', that is not a " + type.getName()
				);
			}
		}
	}
}
