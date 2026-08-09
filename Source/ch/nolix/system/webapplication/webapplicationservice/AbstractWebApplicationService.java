/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.webapplicationservice;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.webapplication.webapplicationservice.WebApplicationService;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractWebApplicationService implements WebApplicationService {
  private IImage applicationLogo;

  /**
   * Creates a new {@link AbstractWebApplicationService} with the given
   * applicationLogo.
   * 
   * @param applicationLogo
   * @throws RuntimeException if the given applicationLogo is null
   */
  protected AbstractWebApplicationService(final IImage applicationLogo) {
    Validator.assertThat(applicationLogo).thatIsNamed("application logo").isNotNull();

    this.applicationLogo = applicationLogo;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IImage getApplicationLogo() {
    return applicationLogo;
  }
}
