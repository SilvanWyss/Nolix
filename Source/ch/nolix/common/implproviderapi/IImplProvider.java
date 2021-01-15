//package declaration
package ch.nolix.common.implproviderapi;

//interface
public interface IImplProvider {
	
	//method declaration
	<I> IImplRegistratorMediator forInterface(Class<I> pInterface);
	
	//method declaration
	<I> IImplProviderMediator ofInterface(Class<I> pInterface);
}
