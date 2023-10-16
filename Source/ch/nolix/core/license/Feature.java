//package declaration
package ch.nolix.core.license;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//class
/**
 * A {@link Feature} can be required for certain functionalities.
 * 
 * @author Silvan Wyss
 * @date 2019-11-16
 */
public abstract class Feature implements Named {

  //method declaration
  /**
   * @return the authorized {@link License} types of the current {@link Feature}.
   */
  public abstract IContainer<Class<?>> getAuthorizedLicenseTypes();

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final String getName() {
    return getClass().getName();
  }
}
