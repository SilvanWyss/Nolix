//package declaration
package ch.nolix.core.provider.implproviderapi;

//interface
public interface IImplProviderMediator<IN> {
	
	//method declaration
	IN createInstance();
	
	//method declaration
	IN createInstance(final Object... arguments);
}
