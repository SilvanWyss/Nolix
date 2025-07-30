package ch.nolix.coreapi.misc.licenseapi;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 * @version 2025-07-30
 */
public interface IFeature extends INameHolder {

  /**
   * @return the authorized {@link ILicense} types of the current
   *         {@link IFeature}.
   */
  IContainer<Class<ILicense>> getAuthorizedLicenseTypes();
}
