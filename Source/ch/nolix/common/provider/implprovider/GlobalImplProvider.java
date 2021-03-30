//package declaration
package ch.nolix.common.provider.implprovider;

import ch.nolix.common.provider.implproviderapi.IImplProvider;
import ch.nolix.common.provider.implproviderapi.IImplProviderMediator;

//class
public final class GlobalImplProvider {
	
	//static attribute
	private static final ImplProvider implProvider = new ImplProvider();
	
	//static method
	public static <O> ExtendedImplRegistratorMediator<O> forInterface(final Class<O> pInterface) {
		return implProvider.forInterface(pInterface);
	}
	
	//static method
	public static IImplProvider getRefInstance() {
		return implProvider;
	}
	
	//static method
	public static <IN> IImplProviderMediator<IN> ofInterface(final Class<IN> pInterface) {
		return implProvider.ofInterface(pInterface);
	}
	
	//constructor
	private GlobalImplProvider() {}
}
