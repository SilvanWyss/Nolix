//package declaration
package ch.nolix.businessapi.serverdashboardaccessapi;

//own imports
import ch.nolix.core.container.SingleContainer;
import ch.nolix.systemapi.guiapi.imageapi.IImage;

//interface
public interface IApplicationSheet {
	
	//method declaration
	SingleContainer<String> getOptionalApplicationDescription();
	
	//method declaration
	SingleContainer<IImage<?>> getOptionalApplicationLogo();
	
	//method declaration
	String getApplicationName();
}
