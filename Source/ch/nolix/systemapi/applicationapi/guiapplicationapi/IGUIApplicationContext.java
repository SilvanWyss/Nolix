//package declaration
package ch.nolix.systemapi.applicationapi.guiapplicationapi;

//own imports
import ch.nolix.core.container.SingleContainer;
import ch.nolix.systemapi.guiapi.imageapi.IImage;

//interface
public interface IGUIApplicationContext {
	
	//method declaration
	SingleContainer<String> getOptionalApplicationDescription();
	
	//method declaration
	SingleContainer<IImage<?>> getOptionalApplicationLogo();
}
