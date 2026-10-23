/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webapplication.webapplicationservice;

import ch.nolix.systemapi.graphic.image.Image;

/**
 * @author Silvan Wyss
 */
public interface WebApplicationService {
  /**
   * @return the logo of the application of the current
   *         {@link WebApplicationService}
   */
  Image getApplicationLogo();
}
