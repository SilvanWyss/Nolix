//package declaration
package ch.nolix.common.errorcontrol.validator;

//Java imports
import java.lang.reflect.Modifier;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
public class TypeMediator<T> extends ArgumentMediator<Class<T>> {
	
	//constructor
	TypeMediator(final Class<T> argument) {
		super(LowerCaseCatalogue.TYPE, argument);
	}
	
	//constructor
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
	public final void isImplementing(final Class<?> pInterface) {
		
		new TypeMediator<>(pInterface).isInterface();
		
		isClass();
		
		if (!pInterface.isAssignableFrom(getRefArgument())) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"does not implement " + pInterface.getName()
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
	public final void isSubClassOf(final Class<?> pClass) {
		
		new TypeMediator<>(pClass).isClass();
		
		isClass();
		
		if (
			!pClass.isAssignableFrom(getRefArgument())
			|| getRefArgument().isAssignableFrom(pClass)
		) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"is not a sub class of " + pClass.getName()
			);
		}
	}
	
	//method
	public final void isSuperClassOf(final Class<?> pClass) {
		
		new TypeMediator<>(pClass).isClass();
		
		isClass();
		
		if (
			!getRefArgument().isAssignableFrom(pClass)
			|| pClass.isAssignableFrom(getRefArgument())
		) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"is not a super class of " + pClass.getName()
			);
		}
	}
}
