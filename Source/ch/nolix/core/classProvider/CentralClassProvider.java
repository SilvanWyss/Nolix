//package declaration
package ch.nolix.core.classProvider;

//own import
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;

//class
public final class CentralClassProvider {
	
	//static attribute
	private static final ClassProvider classProvider =	new ClassProvider();
	
	//static method
	public static boolean containsClassFor(final Class<?> interface_) {
		return classProvider.containsClassFor(interface_);
	}
	
	//static method
	public static <I, C extends I> C create(
		final Class<I> interface_,
		final Object... arguments
	) {
		return classProvider.create(interface_, arguments);
	}
	
	//static method
	public static <I, C extends I> RegistrationMediator register(final Class<I> interface_,	final Class<C> class_) {
		
		classProvider.register(interface_, class_);
		
		return new RegistrationMediator();
	}
	
	//static method
	public static <I, C extends I> RegistrationMediator register(
		final Class<I> interface_,
		final Class<C> class_,
		boolean overwrite
	) {
		
		classProvider.register(interface_, class_, overwrite);
		
		return new RegistrationMediator();
	}
	
	//private constructor
	private CentralClassProvider() { 
		throw new UninstantiableClassException(CentralClassProvider.class);
	}
}
