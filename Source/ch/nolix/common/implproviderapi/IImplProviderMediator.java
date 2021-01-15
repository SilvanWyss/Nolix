//package declaration
package ch.nolix.common.implproviderapi;

//interface
public interface IImplProviderMediator<O> {
	
	//method declaration
	O createInstance();
	
	//method declaration
	O createInstance(final Object... arguments);
}
