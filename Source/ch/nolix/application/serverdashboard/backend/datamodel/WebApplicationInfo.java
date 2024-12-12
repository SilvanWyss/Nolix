package ch.nolix.application.serverdashboard.backend.datamodel;

import ch.nolix.applicationapi.serverdashboardapi.backendapi.datamodelapi.IWebApplicationInfo;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.netapi.targetapi.IApplicationInstanceTarget;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.webapplication.WebClient;
import ch.nolix.systemapi.applicationapi.webapplicationapi.IWebApplicationService;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

public final class WebApplicationInfo implements IWebApplicationInfo {

  private final IApplicationInstanceTarget applicationInstanceTarget;

  private final IImage applicationLogo;

  private <S> WebApplicationInfo(final Application<WebClient<S>, S> webApplication) {

    applicationInstanceTarget = webApplication.asTarget();

    final var applicationService = webApplication.getStoredApplicationService();

    if (applicationService instanceof IWebApplicationService webApplicationService) {
      applicationLogo = webApplicationService.getApplicationLogo();
    } else {
      applicationLogo = null;
    }
  }

  public static <S> WebApplicationInfo forWebApplication(final Application<WebClient<S>, S> webApplication) {
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
