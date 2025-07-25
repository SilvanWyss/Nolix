package ch.nolix.coreapi.state.staterequest;

import ch.nolix.coreapi.structureapi.typemarkerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link TransparencyRequestable} can be asked if it is transparent.
 * 
 * @author Silvan Wyss
 * @version 2022-07-15
 */
@AllowDefaultMethodsAsDesignPattern
public interface TransparencyRequestable {

  /**
   * @return true if the current {@link TransparencyRequestable} is opaque.
   */
  default boolean isOpaque() {
    return !isTransparent();
  }

  /**
   * @return true if the current {@link TransparencyRequestable} is transparent.
   */
  boolean isTransparent();
}
