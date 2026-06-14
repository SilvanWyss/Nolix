/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.environment.licenseapi;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * @author Silvan Wyss
 */
public interface IFeature {
  /**
   * @return the authorized {@link ILicense} types of the current
   *         {@link IFeature}.
   */
  IWellOrderContainer<Class<ILicense>> getAuthorizedLicenseTypes();

  /**
   * @return the name of the current {@link IFeature}.
   */
  String getName();
}
