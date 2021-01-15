//package declaration
package ch.nolix.common.implproviderapi;

//interface
public interface IImplRegistratorMediator<O> {
	
	//method declaration
	boolean containsImplementation();
	
	//method declaration
	void registerImplementation(Class<O> implementation);
}
