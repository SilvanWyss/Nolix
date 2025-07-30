package ch.nolix.core.misc.license;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.container.base.IContainer;

/**
 * A {@link AbstractFeature} can be required for certain functionalities.
 * 
 * @author Silvan Wyss
 * @version 2019-11-16
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
