//package declaration
package ch.nolix.common.implproviderapi;

//interface
public interface IImplProvider {
	
	//method declaration
	<O> IExtendedImplRegistratorMediator<O> forInterface(Class<O> pInterface);
	
	//method declaration
	<O> IImplProviderMediator<O> ofInterface(Class<O> pInterface);
}
