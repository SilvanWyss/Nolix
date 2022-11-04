//package declaration
package ch.nolix.businessapi.serverdashboardaccessapi;

import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IApplicationTarget;
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IServerTarget;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

//interface
public interface IApplicationSheet {
	
	//method declaration
	String getApplicationDescription();
	
	//method declaration
	IImage getApplicationLogo();
	
	//method declaration
	String getApplicationName();
	
	//method declaration
	IApplicationTarget getApplicationTarget();
	
	//method declaration
	IServerTarget getServer();
	
	//method declaration
	boolean hasApplicationDescription();
	
	//method declaration
	boolean hasApplicationLogo();
}
