package ch.nolix.coreapi.environment.licenseapi;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 * @version 2025-07-30
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
