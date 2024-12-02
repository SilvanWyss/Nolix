package ch.nolix.applicationapi.serverdashboardapi.backendapi.datamodelapi;

import ch.nolix.coreapi.netapi.targetapi.IApplicationInstanceTarget;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

public interface IWebApplicationInfo {

  IApplicationInstanceTarget getApplicationInstanceTarget();

  IImage getApplicationLogo();

  boolean hasApplicationLogo();
}
