//package declaration
package ch.nolix.businessapi.serverdashboardaccessapi;

import ch.nolix.coreapi.containerapi.IContainer;

//interface
public interface IServerDashboardAccess {
	
	//method declaration
	IContainer<IApplicationSheet> getGUIApplicationSheets();
}
