//package declaration
package ch.nolix.core.validator2;

//class
public final class ExtendedTypeMediator<T> extends TypeMediator<T> {
	
	//package-visible constructor
	ExtendedTypeMediator(final Class<T> argument) {
		super(argument);
	}
	
	//method
	public TypeMediator<T> thatIsNamed(final String arguemtName) {
		return new TypeMediator<T>(arguemtName, getRefArgument());
	}
}
