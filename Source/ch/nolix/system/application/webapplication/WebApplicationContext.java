package ch.nolix.system.application.webapplication;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.applicationapi.webapplicationapi.IWebApplicationContext;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

public class WebApplicationContext implements IWebApplicationContext {

  private IImage applicationLogo;

  @Override
  public final IImage getApplicationLogo() {
    return applicationLogo;
  }

  public final WebApplicationContext setApplicationLogo(final IImage applicationLogo) {

    GlobalValidator.assertThat(applicationLogo).thatIsNamed("application logo").isNotNull();

    this.applicationLogo = applicationLogo;

    return this;
  }
}
