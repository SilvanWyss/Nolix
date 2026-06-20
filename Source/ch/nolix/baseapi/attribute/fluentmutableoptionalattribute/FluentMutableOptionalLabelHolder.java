/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalLabelHolder;

/**
 * A {@link FluentMutableOptionalLabelHolder} is a {@link OptionalLabelHolder}
 * whose label can be set programmatically and fluently and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableOptionalLabelHolder}
 */
public interface FluentMutableOptionalLabelHolder<H extends FluentMutableOptionalLabelHolder<H>>
extends OptionalLabelHolder {
  /**
   * Removes the label of the current {@link FluentMutableOptionalLabelHolder}.
   */
  void removeLabel();

  /**
   * Sets the label of the current {@link FluentMutableOptionalLabelHolder}.
   * 
   * @param label
   * @return the current {@link FluentMutableOptionalLabelHolder}
   * @throws RuntimeException if the given label is null or blank
   */
  H setLabel(String label);
}
