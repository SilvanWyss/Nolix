//package declaration
package ch.nolix.core.provider.implprovider;

//own imports
import ch.nolix.coreapi.providerapi.implproviderapi.IImplProvider;
import ch.nolix.coreapi.providerapi.implproviderapi.IImplProviderMediator;

//class
public final class GlobalImplProvider {
	
	//constant
	private static final ImplProvider IMPL_PROVIDER = new ImplProvider();
	
	//static method
	public static <O> ExtendedImplRegistratorMediator<O> forInterface(final Class<O> pInterface) {
		return IMPL_PROVIDER.forInterface(pInterface);
	}
	
	//static method
	public static IImplProvider getOriInstance() {
		return IMPL_PROVIDER;
	}
	
	//static method
	public static <IN> IImplProviderMediator<IN> ofInterface(final Class<IN> pInterface) {
		return IMPL_PROVIDER.ofInterface(pInterface);
	}
	
	//constructor
	private GlobalImplProvider() {}
}
