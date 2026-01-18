/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalLabelHolder;

/**
 * A {@link IFluentMutableOptionalLabelHolder} is a {@link IOptionalLabelHolder}
 * whose label can be set and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
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
