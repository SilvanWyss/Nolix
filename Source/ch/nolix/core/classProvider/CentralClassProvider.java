//package declaration
package ch.nolix.core.classProvider;

//own imports
import ch.nolix.core.enums.WriteMode;

//class
public final class CentralClassProvider {
	
	//static attribute
	private static final ClassProvider classProvider =	new ClassProvider();
	
	//static method
	public static boolean containsClassFor(final Class<?> _interface) {
		return classProvider.containsClassFor(_interface);
	}
	
	//static method
	public static <I, C extends I> C create(final Class<I> _interface, final Object... arguments) {
		return classProvider.create(_interface, arguments);
	}
	
	//static method
	public static <I, C extends I> RegistrationMediator register(final Class<I> _interface,	final Class<C> _class) {		
		return classProvider.register(_interface, _class);
	}
	
	//static method
	public static <I, C extends I> RegistrationMediator register(
		final Class<I> _interface,
		final Class<C> _class,
		final WriteMode writeMode
	) {
		return classProvider.register(_interface, _class, writeMode);
	}
	
	//private constructor
	private CentralClassProvider() {}
}
