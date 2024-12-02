package ch.nolix.applicationapi.serverdashboardapi.frontendapi.contextapi;

import ch.nolix.applicationapi.serverdashboardapi.backendapi.datamodelapi.IWebApplicationInfo;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IServerDashboardContext {

  IContainer<IWebApplicationInfo> getWebApplicationInfos();
}
