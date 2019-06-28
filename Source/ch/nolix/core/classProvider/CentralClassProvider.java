//package declaration
package ch.nolix.core.classProvider;

//own imports
import ch.nolix.core.enums.WriteMode;

//class
public final class CentralClassProvider {
	
	//static attribute
	private static final ClassProvider classProvider =	new ClassProvider();
	
	//static method
	public static boolean containsClassFor(final Class<?> interface_) {
		return classProvider.containsClassFor(interface_);
	}
	
	//static method
	public static <I, C extends I> C create(final Class<I> interface_, final Object... arguments) {
		return classProvider.create(interface_, arguments);
	}
	
	//static method
	public static <I, C extends I> RegistrationMediator register(final Class<I> interface_,	final Class<C> class_) {		
		return classProvider.register(interface_, class_);
	}
	
	//static method
	public static <I, C extends I> RegistrationMediator register(
		final Class<I> interface_,
		final Class<C> class_,
		final WriteMode writeMode
	) {
		return classProvider.register(interface_, class_, writeMode);
	}
	
	//private constructor
	private CentralClassProvider() {}
}
