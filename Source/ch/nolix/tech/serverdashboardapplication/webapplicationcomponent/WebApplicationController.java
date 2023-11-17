//package declaration
package ch.nolix.tech.serverdashboardapplication.webapplicationcomponent;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.application.component.Controller;
import ch.nolix.techapi.serverdashboardapi.IServerDashboardContext;
import ch.nolix.techapi.serverdashboardapi.IWebApplicationSheet;

//class
public class WebApplicationController extends Controller<IServerDashboardContext> {

  //attribute
  private final IWebApplicationSheet webApplicationSheet;

  //constructor
  public WebApplicationController(final IWebApplicationSheet webApplicationSheet) {

    GlobalValidator.assertThat(webApplicationSheet).thatIsNamed(IWebApplicationSheet.class).isNotNull();

    this.webApplicationSheet = webApplicationSheet;
  }

  //method
  public IWebApplicationSheet getWebApplicationSheet() {
    return webApplicationSheet;
  }
}
