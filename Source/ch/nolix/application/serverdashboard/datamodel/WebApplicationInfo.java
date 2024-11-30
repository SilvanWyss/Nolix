package ch.nolix.application.serverdashboard.datamodel;

import ch.nolix.applicationapi.serverdashboardapi.datamodelapi.IWebApplicationInfo;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.netapi.targetapi.IApplicationInstanceTarget;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.webapplication.WebClient;
import ch.nolix.systemapi.applicationapi.webapplicationapi.IWebApplicationContext;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

public final class WebApplicationInfo implements IWebApplicationInfo {

  private final IApplicationInstanceTarget applicationInstanceTarget;

  private final IImage applicationLogo;

  private <AC> WebApplicationInfo(final Application<WebClient<AC>, AC> webApplication) {

    applicationInstanceTarget = webApplication.asTarget();

    final var applicationContext = webApplication.getStoredApplicationContext();

    if (applicationContext instanceof IWebApplicationContext webApplicationContext) {
      applicationLogo = webApplicationContext.getApplicationLogo();
    } else {
      applicationLogo = null;
    }
  }

  public static <AC> WebApplicationInfo forWebApplication(final Application<WebClient<AC>, AC> webApplication) {
    return new WebApplicationInfo(webApplication);
  }

  @Override
  public IApplicationInstanceTarget getApplicationInstanceTarget() {
    return applicationInstanceTarget;
  }

  @Override
  public IImage getApplicationLogo() {

    assertHasApplicationLogo();

    return applicationLogo;
  }

  @Override
  public boolean hasApplicationLogo() {
    return (applicationLogo != null);
  }

  private void assertHasApplicationLogo() {
    if (!hasApplicationLogo()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "application logo");
    }
  }
}
