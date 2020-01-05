//package declaration
package ch.nolix.common.validator;

//Java imports
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.Node;

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
	public final MethodMediator doesNotReturnAnything() {
		
		isNotNull();
		
		if (getRefArgument().getReturnType() != void.class) {
			throw new InvalidArgumentException(getArgumentName(), getRefArgument(), "can return something");
		}
		
		return this;
	}
	
	//method
	public final <A extends Annotation> MethodMediator hasAnnotation(final Class<A> annotationType) {
		
		if (annotationType == null) {
			throw new ArgumentIsNullException("annotation type");
		}
		
		isNotNull();
		
		if (getRefArgument().getAnnotation(annotationType) == null) {
			throw new
			InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"does not have the annotation '" + annotationType.getName() + "'"
			);
		}
		
		return this;
	}
	
	//method
	public final MethodMediator hasParametersOfTypeOnly(final Class<String> type) {
		
		if (type == null) {
			throw new ArgumentIsNullException(getArgumentName());
		}
		
		isNotNull();
		
		for (final var p : getRefArgument().getParameters()) {
			if (!p.getType().isAssignableFrom(type)) {
				throw
				new InvalidArgumentException(
					getArgumentName(),
					getRefArgument(),
					"has a parameter '" + p.getName() + "', that is not a " + type.getName()
				);
			}
		}
		
		return this;
	}
	
	//method
	public final MethodMediator hasReturnType(final Class<Node> returnType) {
		
		if (returnType == null) {
			throw new ArgumentIsNullException("return type");
		}
		
		if (getRefArgument().getReturnType() != returnType) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"does not have the return type '" + returnType.getName() +  "'"
			);
		}
		
		return this;
	}
}
