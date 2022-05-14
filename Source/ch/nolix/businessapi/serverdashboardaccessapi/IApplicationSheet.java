//package declaration
package ch.nolix.businessapi.serverdashboardaccessapi;

//own imports
import ch.nolix.core.net.targetapi.IServerTarget;
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
	IServerTarget getServer();
	
	//method declaration
	boolean hasApplicationDescription();
	
	//method declaration
	boolean hasApplicationLogo();
}
