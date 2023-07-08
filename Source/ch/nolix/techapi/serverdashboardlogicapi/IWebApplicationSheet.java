//package declaration
package ch.nolix.techapi.serverdashboardlogicapi;

//own imports
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IApplicationInstanceTarget;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

//interface
public interface IWebApplicationSheet {
	
	//method declaration
	IApplicationInstanceTarget getApplicationInstanceTarget();
	
	//method declaration
	IImage getApplicationLogo();
	
	//method declaration
	boolean hasApplicationLogo();
}
