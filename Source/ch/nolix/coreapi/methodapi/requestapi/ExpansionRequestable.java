//package declaration
package ch.nolix.coreapi.methodapi.requestapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link ExpansionRequestable} can be asked if it is expanded or collapsed.
 * 
 * @author Silvan Wyss
 * @date 2020-10-02
 */
@AllowDefaultMethodsAsDesignPattern
public interface ExpansionRequestable {

  //method
  /**
   * @return true if the current {@link ExpansionRequestable} is collapsed.
   */
  default boolean isCollapsed() {
    return !isExpanded();
  }

  //method declaration
  /**
   * @return true if the current {@link ExpansionRequestable} is expanded.
   */
  boolean isExpanded();
}
