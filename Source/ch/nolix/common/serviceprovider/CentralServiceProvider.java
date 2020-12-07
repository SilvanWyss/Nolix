//package declaration
package ch.nolix.common.serviceprovider;

//own imports
import ch.nolix.common.processproperty.WriteMode;

//class
public final class CentralServiceProvider {
	
	//static attribute
	private static final ServiceProvider coreServiceProdiver = new ServiceProvider();
	
	//static method
	public static <S> S get(final Class<S> pInterface) {
		return coreServiceProdiver.get(pInterface);
	}
	
	//static method
	public static <I, S extends I> void register(final Class<I> pInterface,	final S service) {
		coreServiceProdiver.register(pInterface, service);
	}
	
	//static method
	public static <I, S extends I> void register(
		final Class<I> pInterface,
		final S service,
		final WriteMode writeMode
	) {
		coreServiceProdiver.register(pInterface, service, writeMode);
	}
	
	//visibility-reduced constructor
	private CentralServiceProvider() {}
}
