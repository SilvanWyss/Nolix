//package declaration
package ch.nolix.tech.serverdashboardapplication.webapplicationcomponent;

//own imports
import ch.nolix.system.application.component.Component;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.graphic.image.MutableImage;
import ch.nolix.system.webgui.atomiccontrol.ImageControl;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.applicationapi.componentapi.RefreshBehavior;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.LabelRole;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.techapi.serverdashboardlogicapi.IServerDashboardContext;
import ch.nolix.techapi.serverdashboardlogicapi.IWebApplicationSheet;

//class
public final class WebApplicationComponent extends Component<WebApplicationController, IServerDashboardContext> {

  // constant
  public static final int APPLICATION_LOGO_IMAGE_WIDTH = 250;

  // constant
  public static final int APPLICATION_LOGO_IMAGE_HEIGHT = 200;

  // constant
  private static final String DEFAULT_APPLICATION_LOGO_RESOURCE_PATH = "ch/nolix/tech/serverdashboardapplication/resource/default_application_logo.jpg";

  // constant
  public static final IImage DEFAULT_APPLICATION_LOGO = MutableImage
      .fromResource(DEFAULT_APPLICATION_LOGO_RESOURCE_PATH)
      .withWidthAndHeight(APPLICATION_LOGO_IMAGE_WIDTH, APPLICATION_LOGO_IMAGE_HEIGHT);

  // constructor
  public WebApplicationComponent(
      final WebApplicationController webApplicationController,
      final WebClientSession<IServerDashboardContext> session) {
    super(webApplicationController, session);
  }

  // method
  @Override
  public RefreshBehavior getRefreshBehavior() {
    return RefreshBehavior.REFRESH_SELF;
  }

  // method
  @Override
  protected IControl<?, ?> createControl(final WebApplicationController controller) {
    return new VerticalStack()
        .addControl(
            new ImageControl()
                .setImage(getApplicationLogoOrDefaultApplicationLogo(controller.getWebApplicationSheet()))
                .setLeftMouseButtonPressAction(
                    ic -> ic
                        .getStoredParentGui()
                        .onFrontEnd()
                        .redirectTo(controller.getWebApplicationSheet().getApplicationInstanceTarget())),
            new Label()
                .setRole(LabelRole.LEVEL1_HEADER)
                .setText(
                    controller.getWebApplicationSheet().getApplicationInstanceTarget().getApplicationInstanceName()));
  }

  @Override
  protected void doRegistrations(final WebApplicationController controller) {
    // Does nothing.
  }

  // method
  private IImage getApplicationLogoOrDefaultApplicationLogo(final IWebApplicationSheet webApplicationSheet) {

    if (!webApplicationSheet.hasApplicationLogo()) {
      return DEFAULT_APPLICATION_LOGO;
    }

    return webApplicationSheet
        .getApplicationLogo()
        .withWidthAndHeight(APPLICATION_LOGO_IMAGE_WIDTH, APPLICATION_LOGO_IMAGE_HEIGHT);
  }
}
