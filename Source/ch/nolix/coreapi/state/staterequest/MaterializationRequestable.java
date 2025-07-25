package ch.nolix.coreapi.state.staterequest;

import ch.nolix.coreapi.structure.typemarker.AllowDefaultMethodsAsDesignPattern;

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
 * @version 2023-02-12
 */
@AllowDefaultMethodsAsDesignPattern
public interface MaterializationRequestable {

  /**
   * @return true if the current {@link MaterializationRequestable} is
   *         materialized, false otherwise.
   */
  boolean isMaterialized();

  /**
   * @return true if the current {@link MaterializationRequestable} is a view,
   *         false otherwise.
   */
  default boolean isView() {
    return !isMaterialized();
  }
}
