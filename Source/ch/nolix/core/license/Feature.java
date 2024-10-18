package ch.nolix.core.license;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

/**
 * A {@link Feature} can be required for certain functionalities.
 * 
 * @author Silvan Wyss
 * @version 2019-11-16
 */
public abstract class Feature implements INameHolder {

  /**
   * @return the authorized {@link License} types of the current {@link Feature}.
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
