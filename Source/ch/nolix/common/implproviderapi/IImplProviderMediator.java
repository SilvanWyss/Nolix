//package declaration
package ch.nolix.common.implproviderapi;

//interface
public interface IImplProviderMediator {
	
	//method declaration
	boolean containsImpl();
	
	//method declaration
	<O> O createInstance();
	
	//method declaration
	<O> O createInstance(final Object... arguments);
}
