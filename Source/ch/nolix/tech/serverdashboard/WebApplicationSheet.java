//package declaration
package ch.nolix.tech.serverdashboard;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.programcontrolapi.targetapi.IApplicationInstanceTarget;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.webapplication.WebClient;
import ch.nolix.systemapi.applicationapi.webapplicationapi.IWebApplicationContext;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.techapi.serverdashboardapi.IWebApplicationSheet;

//class
public final class WebApplicationSheet implements IWebApplicationSheet {

  //attribute
  private final IApplicationInstanceTarget applicationInstanceTarget;

  //optional attribute
  private final IImage applicationLogo;

  //constructor
  private <AC> WebApplicationSheet(final Application<WebClient<AC>, AC> webApplication) {

    applicationInstanceTarget = webApplication.asTarget();

    final var applicationContext = webApplication.getStoredApplicationContext();

    if (applicationContext instanceof IWebApplicationContext webApplicationContext) {
      applicationLogo = webApplicationContext.getApplicationLogo();
    } else {
      applicationLogo = null;
    }
  }

  //static method
  public static <AC> WebApplicationSheet forWebApplication(final Application<WebClient<AC>, AC> webApplication) {
    return new WebApplicationSheet(webApplication);
  }

  //method
  @Override
  public IApplicationInstanceTarget getApplicationInstanceTarget() {
    return applicationInstanceTarget;
  }

  //method
  @Override
  public IImage getApplicationLogo() {

    assertHasApplicationLogo();

    return applicationLogo;
  }

  //method
  @Override
  public boolean hasApplicationLogo() {
    return (applicationLogo != null);
  }

  //method
  private void assertHasApplicationLogo() {
    if (!hasApplicationLogo()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "application logo");
    }
  }
}
