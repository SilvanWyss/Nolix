package ch.nolix.system.application.webapplication;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webapplicationapi.mainapi.IWebApplicationService;

public class WebApplicationService implements IWebApplicationService {

  public static final IImage DEFAULT_APPLICATION_LOGO = Image.fromResource("image/default_application_logo.jpg");

  private IImage applicationLogo = DEFAULT_APPLICATION_LOGO;

  @Override
  public final IImage getApplicationLogo() {
    return applicationLogo;
  }

  public final WebApplicationService setApplicationLogo(final IImage applicationLogo) {

    Validator.assertThat(applicationLogo).thatIsNamed("application logo").isNotNull();

    this.applicationLogo = applicationLogo;

    return this;
  }
}
