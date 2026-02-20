/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.environment.license;

import ch.nolix.baseapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.baseapi.container.base.IContainer;

/**
 * A {@link AbstractFeature} can be required for certain functionalities.
 * 
 * @author Silvan Wyss
 */
public abstract class AbstractFeature implements INameHolder {
  /**
   * @return the authorized {@link License} types of the current
   *         {@link AbstractFeature}.
   */
  public abstract IContainer<Class<?>> getAuthorizedLicenseTypes();

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getName() {
    return getClass().getName();
  }
}
