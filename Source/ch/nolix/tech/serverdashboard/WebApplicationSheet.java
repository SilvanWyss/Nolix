package ch.nolix.tech.serverdashboard;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.netapi.targetapi.IApplicationInstanceTarget;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.webapplication.WebClient;
import ch.nolix.systemapi.applicationapi.webapplicationapi.IWebApplicationContext;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.techapi.serverdashboardapi.IWebApplicationSheet;

public final class WebApplicationSheet implements IWebApplicationSheet {

  private final IApplicationInstanceTarget applicationInstanceTarget;

  private final IImage applicationLogo;

  private <AC> WebApplicationSheet(final Application<WebClient<AC>, AC> webApplication) {

    applicationInstanceTarget = webApplication.asTarget();

    final var applicationContext = webApplication.getStoredApplicationContext();

    if (applicationContext instanceof IWebApplicationContext webApplicationContext) {
      applicationLogo = webApplicationContext.getApplicationLogo();
    } else {
      applicationLogo = null;
    }
  }

  public static <AC> WebApplicationSheet forWebApplication(final Application<WebClient<AC>, AC> webApplication) {
    return new WebApplicationSheet(webApplication);
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
