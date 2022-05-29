//package declaration
package ch.nolix.businessapi.serverdashboardaccessapi;

import ch.nolix.core.net.targetuniversalapi.IApplicationTarget;
import ch.nolix.core.net.targetuniversalapi.IServerTarget;
import ch.nolix.systemapi.guiapi.imageapi.IImage;

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
