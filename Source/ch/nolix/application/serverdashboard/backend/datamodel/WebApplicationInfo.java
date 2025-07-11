package ch.nolix.application.serverdashboard.backend.datamodel;

import ch.nolix.applicationapi.serverdashboardapi.backendapi.datamodelapi.IWebApplicationInfo;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.netapi.targetapi.IApplicationInstanceTarget;
import ch.nolix.systemapi.applicationapi.mainapi.IApplication;
import ch.nolix.systemapi.applicationapi.webapplicationapi.IWebApplicationService;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

public final class WebApplicationInfo implements IWebApplicationInfo {

  private final IApplicationInstanceTarget applicationInstanceTarget;

  private final IImage applicationLogo;

  private <S> WebApplicationInfo(final IApplication<?, ?> webApplication) {

    applicationInstanceTarget = webApplication.asTarget();

    final var applicationService = webApplication.getStoredApplicationService();

    if (applicationService instanceof final IWebApplicationService webApplicationService) {
      applicationLogo = webApplicationService.getApplicationLogo();
    } else {
      applicationLogo = null;
    }
  }

  public static WebApplicationInfo forWebApplication(final IApplication<?, ?> webApplication) {
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
