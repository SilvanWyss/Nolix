//package declaration
package ch.nolix.businessapi.serverdashboardcontextapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//interface
public interface IApplicationContext {
	
	//method declaration
	IContainer<IApplicationSheet> getGUIApplicationSheets();
}
