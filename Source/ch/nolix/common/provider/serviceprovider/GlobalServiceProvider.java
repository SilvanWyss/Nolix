//package declaration
package ch.nolix.common.provider.serviceprovider;

import ch.nolix.common.programcontrol.processproperty.WriteMode;

//class
public final class GlobalServiceProvider {
	
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
	
	//constructor
	private GlobalServiceProvider() {}
}
