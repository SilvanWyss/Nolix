package ch.nolix.tech.serverdashboardapplication.webapplicationcomponent;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.application.component.Controller;
import ch.nolix.techapi.serverdashboardapi.IServerDashboardContext;
import ch.nolix.techapi.serverdashboardapi.IWebApplicationSheet;

public class WebApplicationController extends Controller<IServerDashboardContext> {

  private final IWebApplicationSheet webApplicationSheet;

  public WebApplicationController(final IWebApplicationSheet webApplicationSheet) {

    GlobalValidator.assertThat(webApplicationSheet).thatIsNamed(IWebApplicationSheet.class).isNotNull();

    this.webApplicationSheet = webApplicationSheet;
  }

  public IWebApplicationSheet getWebApplicationSheet() {
    return webApplicationSheet;
  }
}
