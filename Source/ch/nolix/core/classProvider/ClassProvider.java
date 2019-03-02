//package declaration
package ch.nolix.core.classProvider;

//own import
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;

//class
public final class ClassProvider {
	
	//static attribute
	private static final CoreClassProvider coreClassProvider =	new CoreClassProvider();
	
	//static method
	public static boolean containsClassFor(final Class<?> interface_) {
		return coreClassProvider.containsClassFor(interface_);
	}
	
	//static method
	public static <I, C extends I> C create(
		final Class<I> interface_,
		final Object... arguments
	) {
		return coreClassProvider.create(interface_, arguments);
	}
	
	//static method
	public static <I, C extends I> RegistrationMediator register(final Class<I> interface_,	final Class<C> class_) {
		
		coreClassProvider.register(interface_, class_);
		
		return new RegistrationMediator();
	}
	
	//static method
	public static <I, C extends I> RegistrationMediator register(
		final Class<I> interface_,
		final Class<C> class_,
		boolean overwrite
	) {
		
		coreClassProvider.register(interface_, class_, overwrite);
		
		return new RegistrationMediator();
	}
	
	//private constructor
	private ClassProvider() { 
		throw new UninstantiableClassException(ClassProvider.class);
	}
}
