package ch.nolix.coreapi.datamodel.entityrequest;

import ch.nolix.coreapi.structureapi.typemarkerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link AbstractnessRequestable} can be asked if it is either abstract or
 * concrete.
 * 
 * @author Silvan Wyss
 * @version 2023-08-25
 */
@AllowDefaultMethodsAsDesignPattern
public interface AbstractnessRequestable {

  /**
   * @return true if the current {@link AbstractnessRequestable} is abstract,
   *         false otherwise.
   */
  boolean isAbstract();

  /**
   * @return true if the current {@link AbstractnessRequestable} is concreate,
   *         false otherwise.
   */
  default boolean isConcrete() {
    return !isAbstract();
  }
}
