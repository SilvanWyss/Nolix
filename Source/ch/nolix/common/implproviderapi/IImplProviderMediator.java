//package declaration
package ch.nolix.common.implproviderapi;

//interface
public interface IImplProviderMediator<IN> {
	
	//method declaration
	IN createInstance();
	
	//method declaration
	IN createInstance(final Object... arguments);
}
