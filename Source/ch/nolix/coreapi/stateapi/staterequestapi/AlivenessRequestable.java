//package declaration
package ch.nolix.coreapi.stateapi.staterequestapi;

//interface
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link AlivenessRequestable} can be asked if it is alive or outdated.
 * 
 * @author Silvan Wyss
 * @date 2023-08-02
 */
@AllowDefaultMethodsAsDesignPattern
public interface AlivenessRequestable {

  //method declaration
  /**
   * @return true if the current {@link AlivenessRequestable} is alive.
   */
  boolean isAlive();

  //method
  /**
   * @return true if the current {@link AlivenessRequestable} is outdated.
   */
  default boolean isOutdated() {
    return !isAlive();
  }
}
