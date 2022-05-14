//package declaration
package ch.nolix.businessapi.serverdashboardaccessapi;

//own imports
import ch.nolix.core.container.IContainer;

//interface
public interface IServerDashboardAccess {
	
	//method declaration
	IContainer<IApplicationSheet> getGUIApplicationSheets();
}
