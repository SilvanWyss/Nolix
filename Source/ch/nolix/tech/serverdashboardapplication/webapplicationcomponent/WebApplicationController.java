package ch.nolix.tech.serverdashboardapplication.webapplicationcomponent;

import ch.nolix.applicationapi.serverdashboardapi.contextapi.IServerDashboardContext;
import ch.nolix.applicationapi.serverdashboardapi.datamodelapi.IWebApplicationInfo;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.application.component.Controller;

public class WebApplicationController extends Controller<IServerDashboardContext> {

  private final IWebApplicationInfo webApplicationInfo;

  public WebApplicationController(final IWebApplicationInfo webApplicationInfo) {

    GlobalValidator.assertThat(webApplicationInfo).thatIsNamed(IWebApplicationInfo.class).isNotNull();

    this.webApplicationInfo = webApplicationInfo;
  }

  public IWebApplicationInfo getWebApplicationSheet() {
    return webApplicationInfo;
  }
}
