//package declaration
package ch.nolix.businessapi.serverdashboardlogicapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IServerDashboardContext {
	
	//method declaration
	IContainer<IApplicationSheet> getGuiApplicationSheets();
}
