/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalTitleHolder;

/**
 * A {@link FluentMutableOptionalTitleHolder} is a {@link OptionalTitleHolder}
 * whose title can be set programmatically and fluently and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableOptionalTitleHolder}
 */
public interface FluentMutableOptionalTitleHolder<H extends FluentMutableOptionalTitleHolder<H>>
extends OptionalTitleHolder {
  /**
   * Removes the title of the current {@link FluentMutableOptionalTitleHolder}.
   */
  void removeTitle();

  /**
   * Sets the title of the current {@link FluentMutableOptionalTitleHolder}.
   * 
   * @param title
   * @return the current {@link FluentMutableOptionalTitleHolder}
   * @throws RuntimeException if the given title is null or blank
   */
  H setTitle(String title);
}
