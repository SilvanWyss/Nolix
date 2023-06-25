//package declaration
package ch.nolix.businessapi.serverdashboardlogicapi;

import ch.nolix.coreapi.programcontrolapi.processproperty.SecurityLevel;
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IApplicationInstanceTarget;
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IServerTarget;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

//interface
public interface IApplicationSheet {
	
	//method declaration
	IImage getApplicationLogo();
	
	//method declaration
	String getApplicationName();
	
	//method declaration
	IApplicationInstanceTarget getApplicationTarget(final SecurityLevel securityLevelForConnections);
	
	//method declaration
	IServerTarget getServer();
	
	//method declaration
	boolean hasApplicationLogo();
}
