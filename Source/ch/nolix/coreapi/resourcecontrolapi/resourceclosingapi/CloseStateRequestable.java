package ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi;

import ch.nolix.coreapi.structureapi.typemarkerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link CloseStateRequestable} can be asked if it is closed or open.
 * 
 * @author Silvan Wyss
 * @version 2020-07-01
 */
@AllowDefaultMethodsAsDesignPattern
public interface CloseStateRequestable {

  /**
   * @return true if the current {@link CloseStateRequestable} is closed.
   */
  boolean isClosed();

  /**
   * @return true if the current {@link CloseStateRequestable} is not closed.
   */
  default boolean isOpen() {
    return !isClosed();
  }
}
