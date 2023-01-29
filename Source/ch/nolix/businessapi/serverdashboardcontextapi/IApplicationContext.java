//package declaration
package ch.nolix.businessapi.serverdashboardcontextapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IApplicationContext {
	
	//method declaration
	IContainer<IApplicationSheet> getGUIApplicationSheets();
}
