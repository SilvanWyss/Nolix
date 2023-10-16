//package declaration
package ch.nolix.techapi.serverdashboardlogicapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IServerDashboardContext {

  //method declaration
  IContainer<IWebApplicationSheet> getWebApplicationSheets();
}
