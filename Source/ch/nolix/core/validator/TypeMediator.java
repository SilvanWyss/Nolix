//package declaration
package ch.nolix.core.validator;

//Java import
import java.lang.reflect.Modifier;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;

//class
public class TypeMediator<T> extends ArgumentMediator<Class<T>> {
	
	//package-visible constructor
	TypeMediator(final Class<T> argument) {
		super(VariableNameCatalogue.TYPE, argument);
	}
	
	//package-visible constructor
	TypeMediator(final String argumentName, final Class<T> argument) {
		super(argumentName, argument);
	}
	
	//method
	public final void isAbstract() {
		
		isNotNull();
		
		if (!Modifier.isAbstract(getRefArgument().getModifiers())) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"is not abstract"
			);
		}
	}
	
	//method
	public final void isClass() {
		
		isNotNull();
		
		if (
			getRefArgument().isInterface()
			|| getRefArgument().isEnum()
			|| getRefArgument().isArray()
		) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"is not a class"
			);
		}
	}
	
	//method
	public final void isEnum() {
		
		isNotNull();
		
		if (!getRefArgument().isEnum()) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"is not an enum"
			);
		}
	}
	
	//method
	public final void isImplementing(final Class<?> interface_) {
		
		new TypeMediator<>(interface_).isInterface();
		
		isClass();
		
		if (!interface_.isAssignableFrom(getRefArgument())) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"does not implement " + interface_.getName()
			);
		}
	}
	
	//method
	public final void isInterface() {
		
		isNotNull();
		
		if (!getRefArgument().isInterface()) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"is not an interface"
			);
		}
	}
	
	//method
	public final void isNotAbstract() {
		
		isNotNull();
		
		if (Modifier.isAbstract(getRefArgument().getModifiers())) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"is abstract"
			);
		}
	}
	
	//method
	public final void isSubClassOf(final Class<?> class_) {
		
		new TypeMediator<>(class_).isClass();
		
		isClass();
		
		if (
			!class_.isAssignableFrom(getRefArgument())
			|| getRefArgument().isAssignableFrom(class_)
		) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"is not a sub class of " + class_.getName()
			);
		}
	}
	
	//method
	public final void isSuperClassOf(final Class<?> class_) {
		
		new TypeMediator<>(class_).isClass();
		
		isClass();
		
		if (
			!getRefArgument().isAssignableFrom(class_)
			|| class_.isAssignableFrom(getRefArgument())
		) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"is not a super class of " + class_.getName()
			);
		}
	}
}
