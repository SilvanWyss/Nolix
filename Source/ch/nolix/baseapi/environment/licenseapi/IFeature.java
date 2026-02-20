/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.environment.licenseapi;

import ch.nolix.baseapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public interface IFeature {
  /**
   * @return the authorized {@link ILicense} types of the current
   *         {@link IFeature}.
   */
  IContainer<Class<ILicense>> getAuthorizedLicenseTypes();

  /**
   * @return the name of the current {@link IFeature}.
   */
  String getName();
}
