//package declaration
package ch.nolix.techapi.serverdashboardapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IServerDashboardContext {

  //method declaration
  IContainer<IWebApplicationSheet> getWebApplicationSheets();
}
