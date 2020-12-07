//package declaration
package ch.nolix.common.instanceprovider;

//own imports
import ch.nolix.common.processproperty.WriteMode;

//class
public final class CentralInstanceProvider {
	
	//static attribute
	private static final InstanceProvider instanceProvider =	new InstanceProvider();
	
	//static method
	public static boolean containsClassFor(final Class<?> pInterface) {
		return instanceProvider.containsClassFor(pInterface);
	}
	
	//static method
	public static <I, C extends I> C create(final Class<I> pInterface, final Object... arguments) {
		return instanceProvider.create(pInterface, arguments);
	}
	
	//static method
	public static <I, C extends I> RegistrationMediator register(final Class<I> pInterface,	final Class<C> pClass) {		
		return instanceProvider.register(pInterface, pClass);
	}
	
	//static method
	public static <I, C extends I> RegistrationMediator register(
		final Class<I> pInterface,
		final Class<C> pClass,
		final WriteMode writeMode
	) {
		return instanceProvider.register(pInterface, pClass, writeMode);
	}
	
	//visibility-reduced constructor
	private CentralInstanceProvider() {}
}
