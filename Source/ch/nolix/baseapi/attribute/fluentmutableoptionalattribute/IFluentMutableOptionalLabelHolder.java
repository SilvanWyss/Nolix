/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.IOptionalLabelHolder;

/**
 * A {@link IFluentMutableOptionalLabelHolder} is a {@link IOptionalLabelHolder}
 * whose label can be set programmatically and fluently and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link IFluentMutableOptionalLabelHolder}
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
   * @return the current {@link IFluentMutableOptionalLabelHolder}
   * @throws RuntimeException if the given label is null or blank
   */
  H setLabel(String label);
}
