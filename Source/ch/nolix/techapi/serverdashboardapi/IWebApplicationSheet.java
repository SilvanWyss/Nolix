package ch.nolix.techapi.serverdashboardapi;

import ch.nolix.coreapi.netapi.targetapi.IApplicationInstanceTarget;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

public interface IWebApplicationSheet {

  IApplicationInstanceTarget getApplicationInstanceTarget();

  IImage getApplicationLogo();

  boolean hasApplicationLogo();
}
