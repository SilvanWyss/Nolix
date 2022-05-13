//package declaration
package ch.nolix.businessapi.serverdashboardaccessapi;

//own imports
import ch.nolix.systemapi.guiapi.imageapi.IImage;

//interface
public interface IApplicationSheet {
	
	//method declaration
	String getApplicationDescription();
	
	//method declaration
	IImage<?> getApplicationLogo();
	
	//method declaration
	String getApplicationName();
	
	//method declaration
	boolean hasApplicationDescription();
	
	//method declaration
	boolean hasApplicationLogo();
}
