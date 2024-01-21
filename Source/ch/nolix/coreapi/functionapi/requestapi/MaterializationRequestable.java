//package declaration
package ch.nolix.coreapi.functionapi.requestapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link MaterializationRequestable} can be asked if it is either
 * materialized or a view. A materialized object manages its content by itself.
 * A view relies on the content of another object. The other object itself can
 * either be materialized or a view.
 * 
 * When there is created a materialized object from another object, the
 * materialized object will not be affected when the origin object changes. A
 * materialized object is not affected by the later changes of the origin
 * object.
 * 
 * When there is created a view from another object, the view can be affected
 * when the origin object changes. A view can be affected by the later changes
 * of the origin object.
 * 
 * @author Silvan Wyss
 * @date 2023-02-12
 */
@AllowDefaultMethodsAsDesignPattern
public interface MaterializationRequestable {

  //method declaration
  /**
   * @return true if the current {@link MaterializationRequestable} is
   *         materialized, false otherwise.
   */
  boolean isMaterialized();

  //method
  /**
   * @return true if the current {@link MaterializationRequestable} is a view,
   *         false otherwise.
   */
  default boolean isView() {
    return !isMaterialized();
  }
}
