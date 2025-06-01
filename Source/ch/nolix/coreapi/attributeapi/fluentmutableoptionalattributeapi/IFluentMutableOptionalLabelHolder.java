package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalLabelHolder;

/**
 * A {@link IFluentMutableOptionalLabelHolder} is a {@link IOptionalLabelHolder}
 * whose label can be set and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2023-10-25
 * @param <H> is the type of a {@link IFluentMutableOptionalLabelHolder}.
 */
public interface IFluentMutableOptionalLabelHolder<H extends IFluentMutableOptionalLabelHolder<H>>
extends IOptionalLabelHolder {

  /**
   * Removes the label of the current {@link IFluentMutableOptionalLabelHolder}.
   */
  void removeLabel();

  /**
   * Sets the label of the current {@link IFluentMutableOptionalLabelHolder}.
   * 
   * @param label
   * @return the current {@link IFluentMutableOptionalLabelHolder}.
   * @throws RuntimeException if the given label is null.
   * @throws RuntimeException if the given label is blank.
   */
  H setLabel(String label);
}
