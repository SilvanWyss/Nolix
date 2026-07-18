/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.environment.license;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface Feature {
  /**
   * @return the authorized {@link License} types of the current {@link Feature}
   */
  ExtendedIterable<Class<License>> getAuthorizedLicenseTypes();

  /**
   * @return the name of the current {@link Feature}
   */
  String getName();
}
