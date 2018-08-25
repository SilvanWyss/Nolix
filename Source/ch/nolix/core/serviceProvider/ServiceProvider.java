//package declaration
package ch.nolix.core.serviceProvider;

//class
public final class ServiceProvider {
	
	//static attribute
	private static final CoreServiceProvider coreServiceProdiver =
	new CoreServiceProvider();
	
	//static method
	public static <S> S get(final Class<S> interface_) {
		return coreServiceProdiver.get(interface_);
	}
	
	//static method
	public static <I, S extends I> void register(
		final Class<I> interface_,
		final S service
	) {
		coreServiceProdiver.register(interface_, service);
	}
	
	//static method
	public static <I, S extends I> void register(
		final Class<I> interface_,
		final S service,
		final boolean overwrite
	) {
		coreServiceProdiver.register(interface_, service, overwrite);
	}
	
	//private constructor
	private ServiceProvider() {}
}
