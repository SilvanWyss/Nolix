//package declaration
package ch.nolix.common.instanceProvider;

//own imports
import ch.nolix.common.processProperties.WriteMode;

//class
public final class CentralInstanceProvider {
	
	//static attribute
	private static final InstanceProvider instanceProvider =	new InstanceProvider();
	
	//static method
	public static boolean containsClassFor(final Class<?> _interface) {
		return instanceProvider.containsClassFor(_interface);
	}
	
	//static method
	public static <I, C extends I> C create(final Class<I> _interface, final Object... arguments) {
		return instanceProvider.create(_interface, arguments);
	}
	
	//static method
	public static <I, C extends I> RegistrationMediator register(final Class<I> _interface,	final Class<C> _class) {		
		return instanceProvider.register(_interface, _class);
	}
	
	//static method
	public static <I, C extends I> RegistrationMediator register(
		final Class<I> _interface,
		final Class<C> _class,
		final WriteMode writeMode
	) {
		return instanceProvider.register(_interface, _class, writeMode);
	}
	
	//visibility-reducing constructor
	private CentralInstanceProvider() {}
}
