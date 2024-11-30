package ch.nolix.applicationapi.serverdashboardapi.contextapi;

import ch.nolix.applicationapi.serverdashboardapi.datamodelapi.IWebApplicationInfo;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IServerDashboardContext {

  IContainer<IWebApplicationInfo> getWebApplicationInfos();
}
