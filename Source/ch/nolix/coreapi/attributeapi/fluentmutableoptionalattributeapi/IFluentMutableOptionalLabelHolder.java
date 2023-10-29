//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalLabelHolder;

//interface
/**
 * A {@link IFluentMutableOptionalLabelHolder} is a {@link IOptionalLabelHolder}
 * whose label can be set and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @date 2023-10-25
 * @param <FMOLH> is the type of a {@link IFluentMutableOptionalLabelHolder}.
 */
public interface IFluentMutableOptionalLabelHolder<FMOLH extends IFluentMutableOptionalLabelHolder<FMOLH>>
extends IOptionalLabelHolder {

  //method declaration
  /**
   * Removes the label of the current {@link IFluentMutableOptionalLabelHolder}.
   * 
   * @return the current {@link IFluentMutableOptionalLabelHolder}.
   */
  FMOLH removeLabel();

  //method declaration
  /**
   * Sets the label of the current {@link IFluentMutableOptionalLabelHolder}.
   * 
   * @param label
   * @return the current {@link IFluentMutableOptionalLabelHolder}.
   * @throws RuntimeException if the given label is null.
   * @throws RuntimeException if the given label is blank.
   */
  FMOLH setLabel(String label);
}
