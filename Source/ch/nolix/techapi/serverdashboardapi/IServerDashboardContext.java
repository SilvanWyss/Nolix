package ch.nolix.techapi.serverdashboardapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IServerDashboardContext {

  IContainer<IWebApplicationSheet> getWebApplicationSheets();
}
