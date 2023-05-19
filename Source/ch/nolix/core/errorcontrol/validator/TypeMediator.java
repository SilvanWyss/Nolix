//package declaration
package ch.nolix.core.errorcontrol.validator;

//Java imports
import java.lang.reflect.Modifier;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

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
		
		if (!Modifier.isAbstract(getOriArgument().getModifiers())) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				getArgumentName(),
				getOriArgument(),
				"is not abstract"
			);
		}
	}
	
	//method
	public final void isClass() {
		
		isNotNull();
		
		if (
			getOriArgument().isInterface()
			|| getOriArgument().isEnum()
			|| getOriArgument().isArray()
		) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				getArgumentName(),
				getOriArgument(),
				"is not a class"
			);
		}
	}
	
	//method
	public final void isEnum() {
		
		isNotNull();
		
		if (!getOriArgument().isEnum()) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				getArgumentName(),
				getOriArgument(),
				"is not an enum"
			);
		}
	}
	
	//method
	public final void isImplementing(final Class<?> pInterface) {
		
		new TypeMediator<>(pInterface).isInterface();
		
		isClass();
		
		if (!pInterface.isAssignableFrom(getOriArgument())) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				getArgumentName(),
				getOriArgument(),
				"does not implement " + pInterface.getName()
			);
		}
	}
	
	//method
	public final void isInterface() {
		
		isNotNull();
		
		if (!getOriArgument().isInterface()) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				getArgumentName(),
				getOriArgument(),
				"is not an interface"
			);
		}
	}
	
	//method
	public final void isNotAbstract() {
		
		isNotNull();
		
		if (Modifier.isAbstract(getOriArgument().getModifiers())) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				getArgumentName(),
				getOriArgument(),
				"is abstract"
			);
		}
	}
	
	//method
	public final void isSubClassOf(final Class<?> pClass) {
		
		new TypeMediator<>(pClass).isClass();
		
		isClass();
		
		if (
			!pClass.isAssignableFrom(getOriArgument())
			|| getOriArgument().isAssignableFrom(pClass)
		) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				getArgumentName(),
				getOriArgument(),
				"is not a sub class of " + pClass.getName()
			);
		}
	}
	
	//method
	public final void isSuperClassOf(final Class<?> pClass) {
		
		new TypeMediator<>(pClass).isClass();
		
		isClass();
		
		if (
			!getOriArgument().isAssignableFrom(pClass)
			|| pClass.isAssignableFrom(getOriArgument())
		) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				getArgumentName(),
				getOriArgument(),
				"is not a super class of " + pClass.getName()
			);
		}
	}
}
