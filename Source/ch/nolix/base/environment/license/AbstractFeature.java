/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.environment.license;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * A {@link AbstractFeature} can be required for certain functionalities.
 * 
 * @author Silvan Wyss
 */
public abstract class AbstractFeature implements NameHolder {
  /**
   * @return the authorized {@link AbstractLicense} types of the current
   *         {@link AbstractFeature}.
   */
  public abstract ExtendedIterable<Class<?>> getAuthorizedLicenseTypes();

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getName() {
    return getClass().getName();
  }
}
