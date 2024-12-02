package ch.nolix.applicationapi.serverdashboardapi.frontendapi.applicationapi;

import ch.nolix.applicationapi.serverdashboardapi.backendapi.datamodelapi.IWebApplicationInfo;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IServerDashboardService {

  IContainer<IWebApplicationInfo> getWebApplicationInfos();
}
