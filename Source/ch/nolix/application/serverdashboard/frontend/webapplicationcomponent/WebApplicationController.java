package ch.nolix.application.serverdashboard.frontend.webapplicationcomponent;

import ch.nolix.applicationapi.serverdashboardapi.backendapi.datamodelapi.IWebApplicationInfo;
import ch.nolix.applicationapi.serverdashboardapi.frontendapi.applicationapi.IServerDashboardService;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.application.component.Controller;

public class WebApplicationController extends Controller<IServerDashboardService> {

  private final IWebApplicationInfo webApplicationInfo;

  public WebApplicationController(final IWebApplicationInfo webApplicationInfo) {

    GlobalValidator.assertThat(webApplicationInfo).thatIsNamed(IWebApplicationInfo.class).isNotNull();

    this.webApplicationInfo = webApplicationInfo;
  }

  public IWebApplicationInfo getWebApplicationSheet() {
    return webApplicationInfo;
  }
}
