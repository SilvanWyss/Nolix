//package declaration
package ch.nolix.core.provider.implproviderapi;

//interface
public interface IImplRegistratorMediator<IN> {
	
	//method declaration
	boolean containsImplementation();
	
	//method declaration
	<IM extends IN> void registerImplementation(Class<IM> implementation);
}
