//package declaration
package ch.nolix.common.implproviderapi;

//interface
public interface IImplRegistratorMediator {
	
	//method declaration
	boolean containsImplementation();
	
	//method declaration
	<I> void registerImplementation(Class<I> implementation);
}
