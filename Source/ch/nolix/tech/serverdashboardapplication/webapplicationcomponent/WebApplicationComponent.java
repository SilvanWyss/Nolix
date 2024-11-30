package ch.nolix.tech.serverdashboardapplication.webapplicationcomponent;

import ch.nolix.applicationapi.serverdashboardapi.contextapi.IServerDashboardContext;
import ch.nolix.applicationapi.serverdashboardapi.datamodelapi.IWebApplicationInfo;
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

public final class WebApplicationComponent //NOSONAR: A WebApplicationComponent is a Component.
extends Component<WebApplicationController, IServerDashboardContext> {

  public static final int APPLICATION_LOGO_IMAGE_WIDTH = 250;

  public static final int APPLICATION_LOGO_IMAGE_HEIGHT = 200;

  private static final String DEFAULT_APPLICATION_LOGO_RESOURCE_PATH = "image/default_application_logo.jpg";

  public static final IImage DEFAULT_APPLICATION_LOGO = MutableImage
    .fromResource(DEFAULT_APPLICATION_LOGO_RESOURCE_PATH)
    .withWidthAndHeight(APPLICATION_LOGO_IMAGE_WIDTH, APPLICATION_LOGO_IMAGE_HEIGHT);

  public WebApplicationComponent(
    final WebApplicationController webApplicationController,
    final WebClientSession<IServerDashboardContext> webClientSession) {
    super(webApplicationController, webClientSession);
  }

  @Override
  public RefreshBehavior getRefreshBehavior() {
    return RefreshBehavior.REFRESH_SELF;
  }

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

  private IImage getApplicationLogoOrDefaultApplicationLogo(final IWebApplicationInfo webApplicationInfo) {

    if (!webApplicationInfo.hasApplicationLogo()) {
      return DEFAULT_APPLICATION_LOGO;
    }

    return webApplicationInfo
      .getApplicationLogo()
      .withWidthAndHeight(APPLICATION_LOGO_IMAGE_WIDTH, APPLICATION_LOGO_IMAGE_HEIGHT);
  }
}
